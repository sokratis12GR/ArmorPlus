package net.thedragonteam.armorplus.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import java.util.stream.IntStream;

import static net.minecraftforge.common.ForgeHooks.getContainerItem;


public interface IRecipe {
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCraftingImproved inv, World worldIn);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(InventoryCraftingImproved inv);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();

    default NonNullList<ItemStack> getRemainingItems(InventoryCraftingImproved inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        IntStream.range(0, ret.size()).forEach(i -> ret.set(i, getContainerItem(inv.getStackInSlot(i))));
        return ret;
    }
}