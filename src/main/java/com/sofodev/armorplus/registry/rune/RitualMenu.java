package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.SocketResult;
import com.sofodev.armorplus.api.event.NativeEffectUpgradeEvent;
import com.sofodev.armorplus.api.event.RuneSocketEvent;
import com.sofodev.armorplus.api.event.RuneUpgradeEvent;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModMenus;
import com.sofodev.armorplus.registry.block.special.RuneAltarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

/**
 * The Rune Altar with a complete circle (see {@link RuneRitual}), the one place to socket and upgrade runes. Each tab uses the
 * same two input slots; the rules are in {@link Runes}.
 * <ul>
 *     <li>{@link Tab#SOCKET}: equipment and a rune.</li>
 *     <li>{@link Tab#UPGRADE_RUNE}: two identical runes, the second one can also be on a pedestal.</li>
 *     <li>{@link Tab#UPGRADE_EFFECT}: equipment and a rune of one of its own effects, which can also be on a pedestal.</li>
 * </ul>
 * Catalysts ("ritualCatalysts" config, "runeOverrides" socket_catalyst=..., upgrade_catalyst=... and upgrade_catalyst@level=... for specific buffs) are used up from the pedestals.
 */
public class RitualMenu extends AbstractContainerMenu {

    public enum Tab {
        SOCKET, UPGRADE_RUNE, UPGRADE_EFFECT;

        public String key() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    /**
     * What the current inputs and tab do.
     *
     * @param result   what it makes, EMPTY if it can't be done
     * @param cost     the experience levels it takes
     * @param material the rune it uses up besides the first slot, or null
     * @param catalyst the catalyst it uses up from the pedestals, or EMPTY
     * @param typeCatalyst the catalyst of the rune's type it uses up on top, or EMPTY
     * @param problem  why it can't be done, or null
     */
    public record Plan(ItemStack result, int cost, @Nullable RuneData material, ItemStack catalyst, ItemStack typeCatalyst, @Nullable Component problem) {

        private static Plan blocked(String key) {
            return blocked(Component.translatable("container.armorplus.ritual_altar." + key));
        }

        private static Plan blocked(Component problem) {
            return new Plan(ItemStack.EMPTY, 0, null, ItemStack.EMPTY, ItemStack.EMPTY, problem);
        }
    }

    public static final int PRIMARY_SLOT = 0;
    public static final int SECONDARY_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    private static final int INV_SLOT_START = 3;
    private static final int INV_SLOT_END = 30;
    private static final int USE_ROW_SLOT_END = 39;
    /** The buttons below it select a {@link Tab}. */
    public static final int EXTRACT_BUTTON = Tab.values().length;
    public static final int INVENTORY_Y = 124;

    private final ContainerLevelAccess access;
    private final Player player;
    private final Level level;
    private final @Nullable BlockPos altar;
    private final DataSlot tab = DataSlot.standalone();
    private int lastRitualState;
    private long lastSoundTime;

    private final Container input = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            RitualMenu.this.slotsChanged(this);
        }
    };
    private final ResultContainer resultContainer = new ResultContainer();
    private final Slot primarySlot;
    private final Slot secondarySlot;
    private final Slot resultSlot;

    public RitualMenu(int containerId, Inventory inventory, @Nullable RegistryFriendlyByteBuf data) {
        this(containerId, inventory, ContainerLevelAccess.NULL, data == null ? null : data.readBlockPos());
    }

    public RitualMenu(int containerId, Inventory inventory, ContainerLevelAccess access, @Nullable BlockPos altar) {
        super(ModMenus.RITUAL_ALTAR.get(), containerId);
        this.access = access;
        this.player = inventory.player;
        this.level = inventory.player.level();
        this.altar = altar;
        this.primarySlot = this.addSlot(new Slot(this.input, PRIMARY_SLOT, 14, 54) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return Runes.gearKind(stack) != null || Runes.runeData(stack) != null;
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.secondarySlot = this.addSlot(new Slot(this.input, SECONDARY_SLOT, 50, 54) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return Runes.runeData(stack) != null;
            }
        });
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 0, 161, 54) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return this.hasItem() && canAfford(player) && !getPlan().result().isEmpty();
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                RitualMenu.this.perform(player);
                super.onTake(player, carried);
            }
        });
        addPlayerInventory(inventory, 16, INVENTORY_Y);
        this.addDataSlot(this.tab);
    }

    public Tab getTab() {
        Tab[] tabs = Tab.values();
        int index = this.tab.get();
        return index >= 0 && index < tabs.length ? tabs[index] : Tab.SOCKET;
    }

    /** @return the stack on each pedestal, see {@link RuneRitual#pedestalItems} */
    public List<ItemStack> getPedestalItems() {
        return this.altar == null ? List.of() : RuneRitual.pedestalItems(this.level, this.altar);
    }

    /** @return what the inputs do on the current tab. It fires the rune events, so it's worked out on both sides for the screen */
    public Plan getPlan() {
        return switch (getTab()) {
            case SOCKET -> socketPlan();
            case UPGRADE_RUNE -> upgradeRunePlan();
            case UPGRADE_EFFECT -> upgradeEffectPlan();
        };
    }

    private Plan socketPlan() {
        ItemStack gear = this.primarySlot.getItem();
        if (Runes.gearKind(gear) == null) return Plan.blocked("place_gear");
        RuneData rune = Runes.runeData(this.secondarySlot.getItem());
        if (rune == null) return Plan.blocked("place_rune");
        SocketResult fit = Runes.canSocket(gear, rune);
        if (fit != SocketResult.OK) return Plan.blocked(Component.translatable("commands.armorplus.rune.socket." + fit.name().toLowerCase(Locale.ENGLISH)));
        RuneSocketEvent event = NeoForge.EVENT_BUS.post(new RuneSocketEvent(this.player, gear, rune, Runes.socketCost(rune), Runes.socketCatalyst(rune)));
        if (event.isCanceled()) return Plan.blocked("canceled");
        return withCatalyst(Runes.withRune(gear, rune), event.getCost(), rune, event.getCatalyst(), ItemStack.EMPTY);
    }

    private Plan upgradeRunePlan() {
        RuneData rune = Runes.runeData(this.primarySlot.getItem());
        if (rune == null) return Plan.blocked("place_runes");
        if (!Runes.canUpgrade(rune)) return Plan.blocked("rune_maxed");
        RuneData second = Runes.runeData(this.secondarySlot.getItem());
        if (second != null && !second.equals(rune)) return Plan.blocked("runes_differ");
        if (second == null && pedestalCount(stack -> rune.equals(Runes.runeData(stack))) == 0) return Plan.blocked("needs_second_rune");
        RuneData upgraded = Runes.upgraded(rune);
        RuneUpgradeEvent event = NeoForge.EVENT_BUS.post(new RuneUpgradeEvent(this.player, rune, upgraded, Runes.upgradeCost(rune), Runes.upgradeCatalyst(rune), Runes.upgradeTypeCatalyst(rune)));
        if (event.isCanceled()) return Plan.blocked("canceled");
        return withCatalyst(RuneItem.create(upgraded.kind(), upgraded.entry()), event.getCost(), rune, event.getCatalyst(), event.getTypeCatalyst());
    }

    private Plan upgradeEffectPlan() {
        ItemStack gear = this.primarySlot.getItem();
        if (Runes.gearKind(gear) == null) return Plan.blocked("place_gear");
        RuneData rune = Runes.runeData(this.secondarySlot.getItem());
        if (rune == null && this.altar != null) {
            rune = Runes.runeData(RuneRitual.find(this.level, this.altar, stack -> {
                RuneData candidate = Runes.runeData(stack);
                return candidate != null && Runes.canUpgradeNative(gear, candidate);
            }));
        }
        if (rune == null) return Plan.blocked("place_rune");
        if (!Runes.canUpgradeNative(gear, rune)) return Plan.blocked("rune_not_own_effect");
        String id = rune.buffId();
        int level = Runes.nativeLevel(gear, id) + 1;
        NativeEffectUpgradeEvent event = NeoForge.EVENT_BUS.post(new NativeEffectUpgradeEvent(this.player, gear, rune, level, Runes.nativeUpgradeCost(gear, id), Runes.upgradeCatalyst(rune, level), Runes.upgradeTypeCatalyst(rune)));
        if (event.isCanceled()) return Plan.blocked("canceled");
        return withCatalyst(Runes.withUpgradedNative(gear, id), event.getCost(), rune, event.getCatalyst(), event.getTypeCatalyst());
    }

    /** @return the plan, unless the pedestals don't hold enough of the catalysts. Each holds one item, which limits what events can ask for */
    private Plan withCatalyst(ItemStack result, int cost, RuneData material, ItemStack wanted, ItemStack wantedType) {
        ItemStack catalyst = wanted.copyWithCount(Math.min(wanted.getCount(), RuneRitual.PEDESTAL_COUNT));
        ItemStack typeCatalyst = RitualCatalysts.onTop(catalyst, wantedType);
        for (ItemStack needed : List.of(catalyst, typeCatalyst)) {
            if (!needed.isEmpty() && pedestalCount(stack -> stack.is(needed.getItem())) < needed.getCount()) {
                return new Plan(ItemStack.EMPTY, cost, material, catalyst, typeCatalyst,
                        Component.translatable("container.armorplus.ritual_altar.needs_catalyst", needed.getCount(), needed.getHoverName()));
            }
        }
        return new Plan(result, cost, material, catalyst, typeCatalyst, null);
    }

    private int pedestalCount(Predicate<ItemStack> matches) {
        return this.altar == null ? 0 : RuneRitual.count(this.level, this.altar, matches);
    }

    public boolean canAfford(Player player) {
        return player.hasInfiniteMaterials() || player.experienceLevel >= getPlan().cost();
    }

    /**
     * Switches to the tab and fills the inputs from the player's inventory, for JEI's transfer button. What the inputs held goes
     * back first, if it fits. Catalysts only go on empty pedestals, nothing is taken off one.
     */
    public void fillFromInventory(int tabIndex, ItemStack first, ItemStack second, ItemStack catalyst, ItemStack typeCatalyst) {
        if (tabIndex < 0 || tabIndex >= EXTRACT_BUTTON || this.altar == null || !returnToInventory(this.primarySlot) || !returnToInventory(this.secondarySlot)) return;
        clickMenuButton(this.player, tabIndex);
        fill(this.primarySlot, first);
        fill(this.secondarySlot, second);
        placeOnPedestals(catalyst);
        placeOnPedestals(typeCatalyst);
    }

    private void placeOnPedestals(ItemStack catalyst) {
        for (int placed = 0; placed < catalyst.getCount() && RuneRitual.emptyPedestals(this.level, this.altar) > 0; placed++) {
            ItemStack taken = RuneRitual.takeFromSlots(this, INV_SLOT_START, USE_ROW_SLOT_END, catalyst, 1);
            if (taken.isEmpty()) return;
            RuneRitual.placeOnEmptyPedestal(this.level, this.altar, taken);
        }
    }

    private boolean returnToInventory(Slot slot) {
        ItemStack stack = slot.getItem();
        if (stack.isEmpty()) return true;
        this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false);
        slot.set(stack.isEmpty() ? ItemStack.EMPTY : stack);
        return stack.isEmpty();
    }

    private void fill(Slot slot, ItemStack wanted) {
        if (slot.mayPlace(wanted)) slot.set(RuneRitual.takeFromSlots(this, INV_SLOT_START, USE_ROW_SLOT_END, wanted, 1));
    }

    /** Pays for the result that was just taken and uses up what it came from. */
    private void perform(Player player) {
        Plan plan = getPlan();
        if (plan.result().isEmpty()) return;
        if (!player.hasInfiniteMaterials()) player.giveExperienceLevels(-plan.cost());
        useMaterial(plan.material());
        if (!plan.catalyst().isEmpty()) consumeFromPedestals(stack -> stack.is(plan.catalyst().getItem()), plan.catalyst().getCount());
        if (!plan.typeCatalyst().isEmpty()) consumeFromPedestals(stack -> stack.is(plan.typeCatalyst().getItem()), plan.typeCatalyst().getCount());
        this.primarySlot.set(ItemStack.EMPTY);
        this.access.execute((level, pos) -> {
            long gameTime = level.getGameTime();
            if (this.lastSoundTime != gameTime) {
                level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                this.lastSoundTime = gameTime;
            }
        });
    }

    /** Uses up the rune from the second slot if it's there, otherwise from the pedestals. */
    private void useMaterial(@Nullable RuneData rune) {
        if (rune == null) return;
        if (rune.equals(Runes.runeData(this.secondarySlot.getItem()))) {
            this.secondarySlot.remove(1);
        } else {
            consumeFromPedestals(stack -> rune.equals(Runes.runeData(stack)), 1);
        }
    }

    /** Pedestals only change on the server, clients get the new contents through their sync. */
    private void consumeFromPedestals(Predicate<ItemStack> matches, int count) {
        if (this.altar != null && !this.level.isClientSide()) RuneRitual.consume(this.level, this.altar, matches, count);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.RUNE_ALTAR.get()) && this.access.evaluate(RuneRitual::isActive, true);
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (buttonId == EXTRACT_BUTTON) {
            this.access.execute((level, pos) -> RuneAltarBlock.openMenu(player, level, pos, false));
            return false;
        }
        if (buttonId < 0 || buttonId >= EXTRACT_BUTTON || buttonId == this.tab.get()) return false;
        this.tab.set(buttonId);
        setupResultSlot();
        return true;
    }

    /** Other players (or the ritual itself) can change the pedestals while the screen is open. */
    @Override
    public void broadcastChanges() {
        if (this.altar != null) {
            int state = RuneRitual.stateHash(this.level, this.altar);
            if (state != this.lastRitualState) {
                this.lastRitualState = state;
                setupResultSlot();
            }
        }
        super.broadcastChanges();
    }

    @Override
    public void slotsChanged(Container container) {
        setupResultSlot();
    }

    private void setupResultSlot() {
        this.resultSlot.set(getPlan().result());
        this.broadcastChanges();
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        return target.container != this.resultContainer && super.canTakeItemForPickAll(carried, target);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == RESULT_SLOT) {
                // Shift-clicking repeats quickMoveStack without checking mayPickup again, so the cost is checked here too
                if (!slot.mayPickup(player)) return ItemStack.EMPTY;
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, true)) return ItemStack.EMPTY;
                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex < RESULT_SLOT) {
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) return ItemStack.EMPTY;
            } else if (this.primarySlot.mayPlace(stack) && !this.primarySlot.hasItem() && (Runes.runeData(stack) == null || getTab() == Tab.UPGRADE_RUNE)) {
                if (!this.moveItemStackTo(stack, PRIMARY_SLOT, PRIMARY_SLOT + 1, false)) return ItemStack.EMPTY;
            } else if (this.secondarySlot.mayPlace(stack) && !this.secondarySlot.hasItem()) {
                if (!this.moveItemStackTo(stack, SECONDARY_SLOT, SECONDARY_SLOT + 1, false)) return ItemStack.EMPTY;
            } else if (slotIndex < INV_SLOT_END) {
                if (!this.moveItemStackTo(stack, INV_SLOT_END, USE_ROW_SLOT_END, false)) return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(stack, INV_SLOT_START, INV_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }
            slot.setChanged();
            if (stack.getCount() == clicked.getCount()) return ItemStack.EMPTY;

            slot.onTake(player, stack);
            if (slotIndex == RESULT_SLOT) player.drop(stack, false);
            this.broadcastChanges();
        }
        return clicked;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(0);
        this.access.execute((level, pos) -> this.clearContainer(player, this.input));
    }

    private void addPlayerInventory(Inventory inventory, int x, int y) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(inventory, column + row * 9 + 9, x + column * 18, y + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(inventory, column, x + column * 18, y + 58));
        }
    }
}
