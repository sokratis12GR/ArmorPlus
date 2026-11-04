package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.event.RuneExtractEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.ModMenus;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.NeoForge;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Rune Altar: put in ArmorPlus equipment and a Blank Rune, pick one of the equipment's buffs and pay experience levels to
 * extract it into a rune. Strong buffs can also require a catalyst item ("runeOverrides" config, catalyst=...).
 * Works like the Stonecutter: the buff list is selected with menu button clicks.
 */
public class RuneAltarMenu extends AbstractContainerMenu {

    public static final int GEAR_SLOT = 0;
    public static final int BLANK_RUNE_SLOT = 1;
    public static final int CATALYST_SLOT = 2;
    public static final int RESULT_SLOT = 3;
    private static final int INV_SLOT_START = 4;
    private static final int INV_SLOT_END = 31;
    private static final int USE_ROW_SLOT_END = 40;

    private final ContainerLevelAccess access;
    private final Player player;
    private final DataSlot selectedIndex = DataSlot.standalone();
    private List<String> options = List.of();
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

    public RuneAltarMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public RuneAltarMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenus.RUNE_ALTAR.get(), containerId);
        this.access = access;
        this.player = inventory.player;
        this.gearSlot = this.addSlot(new Slot(this.input, GEAR_SLOT, 10, 20) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return !Runes.extractableEntries(stack).isEmpty();
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.blankRuneSlot = this.addSlot(new Slot(this.input, BLANK_RUNE_SLOT, 10, 44) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.BLANK_RUNE.get());
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
                return this.hasItem() && canAfford(player);
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                RuneAltarMenu.this.extract(player);
                super.onTake(player, carried);
            }
        });
        this.addStandardInventorySlots(inventory, 16, 104);
        this.addDataSlot(this.selectedIndex);
        this.selectedIndex.set(-1);
    }

    /** Pays for and consumes the inputs of the rune that was just taken. */
    private void extract(Player player) {
        if (!player.hasInfiniteMaterials()) player.giveExperienceLevels(-getCost());
        ItemStack catalyst = getCatalyst(this.selectedIndex.get());
        if (!catalyst.isEmpty()) this.catalystSlot.remove(catalyst.getCount());
        this.blankRuneSlot.remove(1);
        ItemStack gear = this.gearSlot.getItem();
        if (ArmorPlusConfig.destroySourceOnExtract.get() || !gear.isDamageableItem()) {
            this.gearSlot.set(ItemStack.EMPTY);
        } else {
            int damage = gear.getDamageValue() + (int) Math.ceil(gear.getMaxDamage() * ArmorPlusConfig.extractionDurabilityCost.get());
            if (damage >= gear.getMaxDamage()) {
                this.gearSlot.set(ItemStack.EMPTY);
            } else {
                gear.setDamageValue(damage);
                this.gearSlot.setChanged();
            }
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

    public List<String> getOptions() {
        return this.options;
    }

    public int getSelectedIndex() {
        return this.selectedIndex.get();
    }

    public boolean hasGear() {
        return this.gearSlot.hasItem() && !this.options.isEmpty();
    }

    public boolean hasBlankRune() {
        return this.blankRuneSlot.hasItem();
    }

    /** @return the cost of the given option (already reduced entry) */
    public int getCost(int index) {
        RuneExtractEvent event = extractEvent(index);
        return event == null ? 0 : event.getCost();
    }

    /** @return the {@link RuneExtractEvent} of the option (cost, catalyst, canceled), null if there is no such option */
    private @Nullable RuneExtractEvent extractEvent(int index) {
        if (!isValidIndex(index)) return null;
        RuneData rune = extractedRune(index);
        return NeoForge.EVENT_BUS.post(new RuneExtractEvent(this.player, this.gearSlot.getItem(), rune,
                Runes.extractionCost(rune), Runes.extractionCatalyst(rune)));
    }

    /** @return the rune the option makes (reduced level) */
    private RuneData extractedRune(int index) {
        return new RuneData(Runes.extractionKind(this.gearSlot.getItem()), Runes.extractedEntry(this.options.get(index)));
    }

    public int getCost() {
        return getCost(this.selectedIndex.get());
    }

    /** @return the catalyst the option needs, or EMPTY */
    public ItemStack getCatalyst(int index) {
        RuneExtractEvent event = extractEvent(index);
        return event == null ? ItemStack.EMPTY : event.getCatalyst();
    }

    /** @return true if the option needs no catalyst or enough of it is in the catalyst slot */
    public boolean hasCatalyst(int index) {
        ItemStack needed = getCatalyst(index);
        if (needed.isEmpty()) return true;
        ItemStack present = this.catalystSlot.getItem();
        return present.is(needed.getItem()) && present.getCount() >= needed.getCount();
    }

    public boolean canAfford(Player player) {
        return player.hasInfiniteMaterials() || player.experienceLevel >= getCost();
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.RUNE_ALTAR.get());
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (this.selectedIndex.get() == buttonId || !isValidIndex(buttonId)) return false;
        this.selectedIndex.set(buttonId);
        setupResultSlot(buttonId);
        return true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.options.size();
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack gear = this.gearSlot.getItem();
        if (!gear.is(this.lastGear)) {
            this.lastGear = gear.getItem();
            this.options = gear.isEmpty() ? List.of() : Runes.extractableEntries(gear);
            this.selectedIndex.set(-1);
        }
        setupResultSlot(this.selectedIndex.get());
    }

    private void setupResultSlot(int index) {
        RuneExtractEvent event = this.gearSlot.hasItem() ? extractEvent(index) : null;
        if (event != null && !event.isCanceled() && this.blankRuneSlot.hasItem() && hasCatalyst(index)) {
            RuneKind kind = Runes.extractionKind(this.gearSlot.getItem());
            this.resultSlot.set(RuneItem.create(kind, Runes.extractedEntry(this.options.get(index))));
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
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
            } else if (stack.is(ModItems.BLANK_RUNE.get())) {
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
}
