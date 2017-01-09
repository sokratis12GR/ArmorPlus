/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class LavaInfuserCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final LavaInfuserCraftingManager INSTANCE = new LavaInfuserCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    private LavaInfuserCraftingManager() {
        (new InfuserRecipes()).addRecipes(this);
        (this.recipes).sort((pCompare1, pCompare2) -> ((pCompare2.getRecipeSize() < pCompare1.getRecipeSize() ? -1 : (pCompare2.getRecipeSize() > pCompare1.getRecipeSize() ? 1 : 0))));
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static LavaInfuserCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.newArrayList();

        for (Object object : recipeComponents) {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item) object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block) object));
            }
        }

        this.recipes.add(new ShapelessRecipes(stack, list));
    }


    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Removes an IRecipe to the list of crafting recipes.
     */
    public void removeRecipe(IRecipe recipe) {
        this.recipes.remove(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    public ItemStack findMatchingRecipe(IItemHandler itemHandler, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(itemHandler, worldIn)) {
                return irecipe.getCraftingResult(itemHandler);
            }
        }

        return ItemStack.EMPTY;
    }

    public NonNullList<ItemStack> getRemainingItems(IItemHandler itemHandler, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(itemHandler, worldIn)) {
                return irecipe.getRemainingItems(itemHandler);
            }
        }

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            nonnulllist.set(i, itemHandler.getStackInSlot(i));
        }

        return nonnulllist;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}