/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class ShapelessRecipes implements IRecipe {
    public final List<ItemStack> input;
    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;

    public ShapelessRecipes(ItemStack output, List<ItemStack> inputList) {
        this.recipeOutput = output;
        this.input = inputList;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    public NonNullList<ItemStack> getRemainingItems(IItemHandler itemHandler) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = itemHandler.getStackInSlot(i);
            nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(IItemHandler itemHandler, World worldIn) {
        List<ItemStack> list = Lists.newArrayList(this.input);

        ItemStack itemstack = itemHandler.getStackInSlot(1);

        if (!itemstack.isEmpty()) {
            boolean flag = false;

            for (ItemStack itemstack1 : list) {
                if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getMetadata() == 32767 || itemstack.getMetadata() == itemstack1.getMetadata())) {
                    flag = true;
                    list.remove(itemstack1);
                    break;
                }
            }

            if (!flag) {
                return false;
            }
        }

        return list.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(IItemHandler inv) {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize() {
        return this.input.size();
    }
}