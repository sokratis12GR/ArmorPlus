package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import com.sofodev.armorplus.api.event.RuneExtractEvent;
import com.sofodev.armorplus.api.event.RuneRemoveEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Rune Altar: put in ArmorPlus equipment and a Blank Rune, pick one of the equipment's buffs and pay experience levels to
 * extract it into a rune. Strong buffs can also require a catalyst item ("runeOverrides" config, catalyst=...).
 * <p>
 * Runes socketed into any equipment are listed after the buffs: picking one takes it out intact for experience levels, with
 * no Blank Rune or catalyst, and the equipment isn't damaged.
 * <p>
 * With a complete circle (see {@link RuneRitual}) the Blank Rune and catalyst can also be on the pedestals, and the altar
 * can switch to the {@link RitualMenu}.
 * <p>
 * Works like the Stonecutter: the list is selected with menu button clicks.
 */
public class RuneAltarMenu extends AbstractContainerMenu {

    /**
     * A row of the list.
     *
     * @param rune     the rune taking the row gives
     * @param socketed true for a socketed rune to take out, false for a buff to extract (the rune is at its reduced level)
     */
    public record Option(RuneData rune, boolean socketed) {
    }

    public static final int GEAR_SLOT = 0;
    public static final int BLANK_RUNE_SLOT = 1;
    public static final int CATALYST_SLOT = 2;
    public static final int RESULT_SLOT = 3;
    private static final int INV_SLOT_START = 4;
    private static final int INV_SLOT_END = 31;
    private static final int USE_ROW_SLOT_END = 40;
    /** Far past any row of the list. */
    public static final int RITUAL_BUTTON = 1000;

    private final ContainerLevelAccess access;
    private final Player player;
    private final Level level;
    private final @Nullable BlockPos altar;
    private int lastRitualState;
    private final DataSlot selectedIndex = DataSlot.standalone();
    private List<Option> options = List.of();
    private Item lastGear = Items.AIR;
    private long lastSoundTime;
    private Runnable slotUpdateListener = () -> {
    };

    private final Container input = new SimpleContainer(3) {
        @Override
        public void setChanged() {
            super.setChanged();
            RuneAltarMenu.this.slotsChanged(this);
            RuneAltarMenu.this.slotUpdateListener.run();
        }
    };
    private final ResultContainer resultContainer = new ResultContainer();
    private final Slot gearSlot;
    private final Slot blankRuneSlot;
    private final Slot catalystSlot;
    private final Slot resultSlot;

    public RuneAltarMenu(int containerId, Inventory inventory, @Nullable RegistryFriendlyByteBuf data) {
        this(containerId, inventory, ContainerLevelAccess.NULL, data == null ? null : data.readBlockPos());
    }

    public RuneAltarMenu(int containerId, Inventory inventory, ContainerLevelAccess access, @Nullable BlockPos altar) {
        super(ModMenus.RUNE_ALTAR.get(), containerId);
        this.access = access;
        this.player = inventory.player;
        this.level = inventory.player.level();
        this.altar = altar;
        this.gearSlot = this.addSlot(new Slot(this.input, GEAR_SLOT, 10, 20) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !optionsOf(stack).isEmpty();
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.blankRuneSlot = this.addSlot(new Slot(this.input, BLANK_RUNE_SLOT, 10, 44) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isBlankRune(stack);
            }
        });
        this.catalystSlot = this.addSlot(new Slot(this.input, CATALYST_SLOT, 10, 68));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, RESULT_SLOT, 161, 44) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return this.hasItem() && canAfford(player) && canMake(getSelectedIndex());
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                RuneAltarMenu.this.take(player);
                super.onTake(player, carried);
            }
        });
        addPlayerInventory(inventory, 16, 104);
        this.addDataSlot(this.selectedIndex);
        this.selectedIndex.set(-1);
    }

    private static List<Option> optionsOf(ItemStack gear) {
        if (!Runes.enabled()) return List.of();
        List<Option> result = new ArrayList<>();
        RuneKind kind = Runes.extractionKind(gear);
        for (String entry : Runes.extractableEntries(gear)) result.add(new Option(new RuneData(kind, Runes.extractedEntry(entry)), false));
        for (RuneData rune : Runes.sockets(gear)) result.add(new Option(rune, true));
        return List.copyOf(result);
    }

    /** Pays for the rune that was just taken and uses up what it came from. */
    private void take(Player player) {
        int index = this.selectedIndex.get();
        if (!isValidIndex(index)) return;
        if (!player.hasInfiniteMaterials()) player.giveExperienceLevels(-getCost(index));
        Option option = this.options.get(index);
        if (option.socketed()) {
            this.gearSlot.set(Runes.withoutRune(this.gearSlot.getItem(), option.rune()));
        } else {
            extract(player, index);
        }
        setupResultSlot(this.selectedIndex.get());
        this.access.execute((level, pos) -> {
            long gameTime = level.getGameTime();
            if (this.lastSoundTime != gameTime) {
                level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                this.lastSoundTime = gameTime;
            }
        });
    }

    /** Consumes the inputs of an extraction. */
    private void extract(Player player, int index) {
        ItemStack catalyst = getCatalyst(index);
        if (!catalyst.isEmpty()) consume(this.catalystSlot, stack -> stack.is(catalyst.getItem()), catalyst.getCount());
        consume(this.blankRuneSlot, RuneAltarMenu::isBlankRune, 1);
        ItemStack gear = this.gearSlot.getItem();
        if (ArmorPlusConfig.destroySourceOnExtract.get()) {
            destroyGear(player, gear);
        } else {
            int cost = Runes.extractionDurabilityCost(gear);
            if (cost > 0) gear.setDamageValue(gear.getDamageValue() + cost);
            Runes.markExtracted(gear, this.options.get(index).rune().buffId());
            this.gearSlot.setChanged();
        }
    }

    /** Uses up the items from the slot first, the rest from the pedestals; those only change on the server, clients get them synced. */
    private void consume(Slot slot, Predicate<ItemStack> matches, int count) {
        int left = count;
        if (slot.hasItem() && matches.test(slot.getItem())) left -= slot.remove(count).getCount();
        if (left > 0 && !this.level.isClientSide() && getPedestalCount() == RuneRitual.PEDESTAL_COUNT) RuneRitual.consume(this.level, this.altar, matches, left);
    }

    /** @return how many matching items the pedestals offer, none without a complete circle */
    public int onPedestals(Predicate<ItemStack> matches) {
        return getPedestalCount() == RuneRitual.PEDESTAL_COUNT ? RuneRitual.count(this.level, this.altar, matches) : 0;
    }

    private static boolean isBlankRune(ItemStack stack) {
        return stack.is(ModItems.BLANK_RUNE.get());
    }

    /** @return how many pedestals of the altar's circle are in place */
    public int getPedestalCount() {
        return this.altar == null ? 0 : RuneRitual.pedestalCount(this.level, this.altar);
    }

    /** @return why the option can't be picked (extracted before, or the equipment wouldn't survive it), null if it can */
    public @Nullable Component getBlockReason(int index) {
        if (!isValidIndex(index) || this.options.get(index).socketed() || ArmorPlusConfig.destroySourceOnExtract.get()) return null;
        ItemStack gear = this.gearSlot.getItem();
        if (Runes.isExtracted(gear, this.options.get(index).rune().buffId())) return Component.translatable("container.armorplus.rune_altar.already_extracted");
        return Runes.survivesExtraction(gear) ? null : Component.translatable("container.armorplus.rune_altar.too_damaged");
    }

    /** @return true if picking the option destroys the equipment instead of damaging it */
    public boolean destroysGear(int index) {
        return isValidIndex(index) && !this.options.get(index).socketed() && ArmorPlusConfig.destroySourceOnExtract.get();
    }

    /** @return the durability the option takes from the equipment */
    public int getDurabilityCost(int index) {
        if (!isValidIndex(index) || this.options.get(index).socketed() || destroysGear(index)) return 0;
        return Runes.extractionDurabilityCost(this.gearSlot.getItem());
    }

    /** @return true if the option is a buff that was already extracted from the equipment, which no longer has it */
    public boolean isExtracted(int index) {
        return isValidIndex(index) && !this.options.get(index).socketed() && Runes.isExtracted(this.gearSlot.getItem(), this.options.get(index).rune().buffId());
    }

    /** @return a copy of the equipment as the option leaves it, without the buff or rune and with its durability cost; EMPTY if it can't be picked or nothing is left */
    public ItemStack getPreview(int index) {
        if (!isValidIndex(index) || getBlockReason(index) != null || destroysGear(index)) return ItemStack.EMPTY;
        Option option = this.options.get(index);
        ItemStack gear = this.gearSlot.getItem();
        if (option.socketed()) return Runes.withoutRune(gear, option.rune());
        ItemStack preview = gear.copy();
        preview.setDamageValue(preview.getDamageValue() + getDurabilityCost(index));
        Runes.markExtracted(preview, option.rune().buffId());
        return preview;
    }

    /** @return the altar this is open on, null if the client wasn't told */
    public @Nullable BlockPos getAltarPos() {
        return this.altar;
    }

    /** Uses up the source equipment. The runes socketed into it aren't what was extracted, so the player gets them back. */
    private void destroyGear(Player player, ItemStack gear) {
        if (!player.level().isClientSide()) {
            for (RuneData rune : Runes.sockets(gear)) {
                player.getInventory().placeItemBackInInventory(RuneItem.create(rune.kind(), rune.entry()));
            }
        }
        this.gearSlot.set(ItemStack.EMPTY);
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public int getSelectedIndex() {
        return this.selectedIndex.get();
    }

    public boolean hasGear() {
        return this.gearSlot.hasItem() && !this.options.isEmpty();
    }

    public boolean hasBlankRune() {
        return this.blankRuneSlot.hasItem() || onPedestals(RuneAltarMenu::isBlankRune) > 0;
    }

    /** @return the cost of the given option */
    public int getCost(int index) {
        RuneRemoveEvent removeEvent = removeEvent(index);
        if (removeEvent != null) return removeEvent.getCost();
        RuneExtractEvent event = extractEvent(index);
        return event == null ? 0 : event.getCost();
    }

    /** @return the {@link RuneExtractEvent} of the option (cost, catalyst, canceled), null if it isn't a buff to extract */
    private @Nullable RuneExtractEvent extractEvent(int index) {
        if (!isValidIndex(index) || this.options.get(index).socketed()) return null;
        RuneData rune = this.options.get(index).rune();
        return NeoForge.EVENT_BUS.post(new RuneExtractEvent(this.player, this.gearSlot.getItem(), rune,
                Runes.extractionCost(rune) + Runes.extractionUpgradeCost(this.gearSlot.getItem(), rune.buffId()), Runes.extractionCatalyst(rune)));
    }

    /** @return the {@link RuneRemoveEvent} of the option (cost, canceled), null if it isn't a socketed rune */
    private @Nullable RuneRemoveEvent removeEvent(int index) {
        if (!isValidIndex(index) || !this.options.get(index).socketed()) return null;
        return NeoForge.EVENT_BUS.post(new RuneRemoveEvent(this.player, this.gearSlot.getItem(), this.options.get(index).rune(), Runes.removalCost()));
    }

    public int getCost() {
        return getCost(this.selectedIndex.get());
    }

    /** @return the catalyst the option needs, or EMPTY */
    public ItemStack getCatalyst(int index) {
        RuneExtractEvent event = extractEvent(index);
        return event == null ? ItemStack.EMPTY : event.getCatalyst();
    }

    /** @return true if the option needs no catalyst or enough of it is in the catalyst slot and on the pedestals */
    public boolean hasCatalyst(int index) {
        ItemStack needed = getCatalyst(index);
        if (needed.isEmpty()) return true;
        ItemStack present = this.catalystSlot.getItem();
        int count = present.is(needed.getItem()) ? present.getCount() : 0;
        return count + onPedestals(stack -> stack.is(needed.getItem())) >= needed.getCount();
    }

    public boolean canAfford(Player player) {
        return player.hasInfiniteMaterials() || player.experienceLevel >= getCost();
    }

    /** Fills the slots from the player's inventory and selects the buff, for JEI's transfer button. What the slots held goes back first, if it fits. */
    public void fillFromInventory(ItemStack gear, ItemStack blankRune, ItemStack catalyst, String buffId) {
        if (!returnToInventory(this.gearSlot) || !returnToInventory(this.blankRuneSlot) || !returnToInventory(this.catalystSlot)) return;
        fill(this.gearSlot, gear, 1);
        fill(this.blankRuneSlot, blankRune, 1);
        fill(this.catalystSlot, catalyst, catalyst.getCount());
        for (int index = 0; index < this.options.size(); index++) {
            Option option = this.options.get(index);
            if (!option.socketed() && option.rune().buffId().equals(buffId)) {
                clickMenuButton(this.player, index);
                return;
            }
        }
    }

    private boolean returnToInventory(Slot slot) {
        ItemStack stack = slot.getItem();
        if (stack.isEmpty()) return true;
        this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false);
        slot.set(stack.isEmpty() ? ItemStack.EMPTY : stack);
        return stack.isEmpty();
    }

    private void fill(Slot slot, ItemStack wanted, int count) {
        if (!wanted.isEmpty() && slot.mayPlace(wanted)) slot.set(RuneRitual.takeFromSlots(this, INV_SLOT_START, USE_ROW_SLOT_END, wanted, count));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.RUNE_ALTAR.get());
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (buttonId == RITUAL_BUTTON) {
            this.access.execute((level, pos) -> {
                if (RuneRitual.isActive(level, pos)) RuneAltarBlock.openMenu(player, level, pos, true);
            });
            return false;
        }
        if (this.selectedIndex.get() == buttonId || !isValidIndex(buttonId) || getBlockReason(buttonId) != null) return false;
        this.selectedIndex.set(buttonId);
        setupResultSlot(buttonId);
        return true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.options.size();
    }

    /** Other players can change the circle and its items while the screen is open. */
    @Override
    public void broadcastChanges() {
        if (this.altar != null) {
            int state = RuneRitual.stateHash(this.level, this.altar);
            if (state != this.lastRitualState) {
                this.lastRitualState = state;
                slotsChanged(this.input);
            }
        }
        super.broadcastChanges();
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack gear = this.gearSlot.getItem();
        List<Option> current = gear.isEmpty() ? List.of() : optionsOf(gear);
        // the same item can list other runes, e.g. after taking one out
        if (!gear.is(this.lastGear) || !current.equals(this.options)) {
            this.lastGear = gear.getItem();
            this.options = current;
            this.selectedIndex.set(-1);
        }
        // e.g. the option that was just extracted, the list itself stays the same
        if (getBlockReason(this.selectedIndex.get()) != null) this.selectedIndex.set(-1);
        setupResultSlot(this.selectedIndex.get());
    }

    private void setupResultSlot(int index) {
        if (this.gearSlot.hasItem() && canMake(index)) {
            RuneData rune = this.options.get(index).rune();
            this.resultSlot.set(RuneItem.create(rune.kind(), rune.entry()));
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    /** @return true if the option has what it needs, apart from the experience levels */
    private boolean canMake(int index) {
        if (getBlockReason(index) != null) return false;
        RuneRemoveEvent removeEvent = removeEvent(index);
        if (removeEvent != null) return !removeEvent.isCanceled();
        RuneExtractEvent event = extractEvent(index);
        return event != null && !event.isCanceled() && hasBlankRune() && hasCatalyst(index);
    }

    public void registerUpdateListener(Runnable listener) {
        this.slotUpdateListener = listener;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        return target.container != this.resultContainer && super.canTakeItemForPickAll(carried, target);
    }

    /** @return true if the stack is the catalyst of any of the current options */
    private boolean isCatalyst(ItemStack stack) {
        for (int index = 0; index < this.options.size(); index++) {
            if (stack.is(getCatalyst(index).getItem()) && !getCatalyst(index).isEmpty()) return true;
        }
        return false;
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
            } else if (isBlankRune(stack)) {
                if (!this.moveItemStackTo(stack, BLANK_RUNE_SLOT, BLANK_RUNE_SLOT + 1, false)) return ItemStack.EMPTY;
            } else if (this.gearSlot.mayPlace(stack) && !this.gearSlot.hasItem()) {
                if (!this.moveItemStackTo(stack, GEAR_SLOT, GEAR_SLOT + 1, false)) return ItemStack.EMPTY;
            } else if (isCatalyst(stack)) {
                if (!this.moveItemStackTo(stack, CATALYST_SLOT, CATALYST_SLOT + 1, false)) return ItemStack.EMPTY;
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
        this.resultContainer.removeItemNoUpdate(RESULT_SLOT);
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
