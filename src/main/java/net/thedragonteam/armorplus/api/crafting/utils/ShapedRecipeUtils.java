package net.thedragonteam.armorplus.api.crafting.utils;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

public class ShapedRecipeUtils {

    @Nonnull
    public static NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        IntStream.range(0, nonnulllist.size()).forEachOrdered(i -> {
            ItemStack itemstack = inv.getStackInSlot(i);
            nonnulllist.set(i, ForgeHooks.getContainerItem(itemstack));
        });

        return nonnulllist;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Nonnull
    public static ItemStack getCraftingResult(ItemStack output, boolean copyIngredientNBT, InventoryCrafting inv) {
        ItemStack itemstack = output.copy();

        if (copyIngredientNBT) {
            IntStream.range(0, inv.getSizeInventory()).mapToObj(inv::getStackInSlot).filter(
                itemstack1 -> !itemstack1.isEmpty() && itemstack1.hasTagCompound()
            ).map(itemstack1 -> requireNonNull(itemstack1.getTagCompound()).copy()).forEachOrdered(itemstack::setTagCompound);
        }

        return itemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public static boolean matches(int xy, int recipeWidth, int recipeHeight, ItemStack[] input, InventoryCrafting inv) {
        return IntStream.rangeClosed(0, xy - recipeWidth).anyMatch(i -> IntStream.rangeClosed(0, xy - recipeHeight).anyMatch(j -> checkMatch(xy, recipeWidth, recipeHeight, input, inv, i, j, true) || checkMatch(xy, recipeWidth, recipeHeight, input, inv, i, j, false)));
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private static boolean checkMatch(int xy, int recipeWidth, int recipeHeight, ItemStack[] input, InventoryCrafting inv, int width, int height, boolean isMirrored) {
        for (int i = 0; i < xy; ++i) {
            for (int j = 0; j < xy; ++j) {
                int k = i - width;
                int l = j - height;
                ItemStack itemstack = ItemStack.EMPTY;

                if (k >= 0 && l >= 0 && k < recipeWidth && l < recipeHeight) {
                    itemstack = isMirrored ? input[recipeWidth - k - 1 + l * recipeWidth] : input[k + l * recipeWidth];
                }

                ItemStack itemstack1 = inv.getStackInRowAndColumn(i, j);

                if ((!itemstack1.isEmpty() || !itemstack.isEmpty()) && (itemstack1.isEmpty() != itemstack.isEmpty() || itemstack.getItem() != itemstack1.getItem() || itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata())) {
                    return false;
                }
            }
        }

        return true;
    }
}
