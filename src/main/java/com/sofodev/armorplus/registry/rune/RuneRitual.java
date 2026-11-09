package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.registry.block.special.EmpoweringPedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * The ritual circle of a Rune Altar: a block of {@link #PEDESTALS} on each of the 4 sides, {@value #PEDESTAL_DISTANCE} blocks
 * from the altar at the same height. A complete circle unlocks the {@link RitualMenu} and lets the altar use the items on the
 * pedestals.
 */
public final class RuneRitual {

    /** The blocks that count for the circle, only Empowering Pedestals can hold items. */
    public static final TagKey<Block> PEDESTALS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(MODID, "ritual_pedestals"));
    public static final int PEDESTAL_DISTANCE = 2;
    public static final int PEDESTAL_COUNT = 4;

    private RuneRitual() {
    }

    /** @return where the pedestals of the altar go: north, east, south and west */
    public static List<BlockPos> pedestalPositions(BlockPos altar) {
        List<BlockPos> result = new ArrayList<>(PEDESTAL_COUNT);
        for (Direction direction : Direction.Plane.HORIZONTAL) result.add(altar.relative(direction, PEDESTAL_DISTANCE));
        return List.copyOf(result);
    }

    /** @return how many pedestals of the circle are in place */
    public static int pedestalCount(Level level, BlockPos altar) {
        int count = 0;
        for (BlockPos pos : pedestalPositions(altar)) {
            if (level.getBlockState(pos).is(PEDESTALS)) count++;
        }
        return count;
    }

    /** @return true if the circle around the altar is complete */
    public static boolean isActive(Level level, BlockPos altar) {
        return pedestalCount(level, altar) == PEDESTAL_COUNT;
    }

    /** @return the stack on each position of {@link #pedestalPositions(BlockPos)}, EMPTY where there is none (don't modify) */
    public static List<ItemStack> pedestalItems(Level level, BlockPos altar) {
        List<ItemStack> result = new ArrayList<>(PEDESTAL_COUNT);
        for (BlockPos pos : pedestalPositions(altar)) {
            result.add(level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal ? pedestal.getItem() : ItemStack.EMPTY);
        }
        return result;
    }

    /** @return how many matching items are on the pedestals */
    public static int count(Level level, BlockPos altar, Predicate<ItemStack> matches) {
        int count = 0;
        for (ItemStack stack : pedestalItems(level, altar)) {
            if (!stack.isEmpty() && matches.test(stack)) count += stack.getCount();
        }
        return count;
    }

    /** @return the first matching stack on the pedestals, or EMPTY */
    public static ItemStack find(Level level, BlockPos altar, Predicate<ItemStack> matches) {
        for (ItemStack stack : pedestalItems(level, altar)) {
            if (!stack.isEmpty() && matches.test(stack)) return stack;
        }
        return ItemStack.EMPTY;
    }

    /** Uses up to the count of matching items from the pedestals, on the server the clients see it through the pedestals' sync. */
    public static void consume(Level level, BlockPos altar, Predicate<ItemStack> matches, int count) {
        int left = count;
        for (BlockPos pos : pedestalPositions(altar)) {
            if (left <= 0) return;
            if (level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal && !pedestal.getItem().isEmpty() && matches.test(pedestal.getItem())) {
                left -= pedestal.remove(left).getCount();
            }
        }
    }

    /** @return how many pedestals of the circle can hold another item */
    public static int emptyPedestals(Level level, BlockPos altar) {
        int count = 0;
        for (BlockPos pos : pedestalPositions(altar)) {
            if (level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal && pedestal.getItem().isEmpty()) count++;
        }
        return count;
    }

    /** Takes one item off the stack and puts it on the first empty pedestal. @return false if there is none */
    public static boolean placeOnEmptyPedestal(Level level, BlockPos altar, ItemStack stack) {
        for (BlockPos pos : pedestalPositions(altar)) {
            if (level.getBlockEntity(pos) instanceof EmpoweringPedestalBlockEntity pedestal && pedestal.getItem().isEmpty()) {
                pedestal.setItem(stack.split(1));
                return true;
            }
        }
        return false;
    }

    /** Takes up to the count of the wanted item from the menu's slots in the range. @return what it took */
    static ItemStack takeFromSlots(AbstractContainerMenu menu, int start, int end, ItemStack wanted, int count) {
        if (wanted.isEmpty()) return ItemStack.EMPTY;
        int left = count;
        for (int index = start; index < end && left > 0; index++) {
            Slot slot = menu.getSlot(index);
            if (slot.hasItem() && ItemStack.isSameItemSameComponents(slot.getItem(), wanted)) {
                left -= slot.remove(left).getCount();
                slot.setChanged();
            }
        }
        return left == count ? ItemStack.EMPTY : wanted.copyWithCount(count - left);
    }

    /** @return a value that changes with the circle and what's on it, so open menus can notice */
    static int stateHash(Level level, BlockPos altar) {
        int hash = pedestalCount(level, altar);
        for (ItemStack stack : pedestalItems(level, altar)) {
            hash = 31 * (31 * hash + ItemStack.hashItemAndComponents(stack)) + stack.getCount();
        }
        return hash;
    }
}
