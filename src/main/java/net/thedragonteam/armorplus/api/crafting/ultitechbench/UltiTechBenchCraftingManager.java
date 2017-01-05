/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes.*;

import java.util.List;
import java.util.Map;

/**
 * net.thedragonteam.armorplus.api.crafting.hightechbench
 * ArmorPlus created by sokratis12GR on 6/19/2016 12:29PM.
 * - TheDragonTeam
 */
public class UltiTechBenchCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final UltiTechBenchCraftingManager INSTANCE = new UltiTechBenchCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    private UltiTechBenchCraftingManager() {

        (new ModUltimateRecipes()).addRecipes(this);
        (new ModEnderDragonRecipes()).addRecipes(this);
        (new ModSuperStarRecipes()).addRecipes(this);
        (new ModGuardianRecipes()).addRecipes(this);
        (new ModWeaponTierThreeRecipes()).addRecipes(this);
        (new ModItemRecipes()).addRecipes(this);
        (this.recipes).sort((pCompare1, pCompare2) -> pCompare1 instanceof ShapelessRecipes && pCompare2 instanceof ShapedRecipes ? 1 : (pCompare2 instanceof ShapelessRecipes && pCompare1 instanceof ShapedRecipes ? -1 : (pCompare2.getRecipeSize() < pCompare1.getRecipeSize() ? -1 : (pCompare2.getRecipeSize() > pCompare1.getRecipeSize() ? 1 : 0))));
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static UltiTechBenchCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public ShapedRecipes addRecipe(ItemStack stack, Object... recipeComponents) {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[]) {
            String[] astring = (String[]) ((String[]) recipeComponents[i++]);

            for (String s2 : astring) {
                ++k;
                j = s2.length();
                s = s + s2;
            }
        } else {
            while (recipeComponents[i] instanceof String) {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.newHashMap(); i < recipeComponents.length; i += 2) {
            Character character = (Character) recipeComponents[i];
            ItemStack itemstack = ItemStack.EMPTY;

            if (recipeComponents[i + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[i + 1]);
            } else if (recipeComponents[i + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            } else if (recipeComponents[i + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int l = 0; l < j * k; ++l) {
            char c0 = s.charAt(l);

            if (map.containsKey(c0)) {
                aitemstack[l] = ((ItemStack) map.get(c0)).copy();
            } else {
                aitemstack[l] = ItemStack.EMPTY;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
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
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getCraftingResult(craftMatrix);
            }
        }

        return ItemStack.EMPTY;
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            nonnulllist.set(i, craftMatrix.getStackInSlot(i));
        }

        return nonnulllist;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}