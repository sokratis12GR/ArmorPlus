/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.tweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.armorplus.api.crafting.workbench.IRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.armorplus.WorkbenchCrafting")
public class WorkbenchCrafting {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        MineTweakerAPI.apply(new Add(new ShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        int height = ingredients.length;
        int width = 0;
        for (IIngredient[] row : ingredients) {
            if (width < row.length)
                width = row.length;
        }
        Object[] input = new Object[width * height];
        int x = 0;
        for (IIngredient[] row : ingredients) {
            for (IIngredient ingredient : row) {
                input[x++] = toActualObject(ingredient);
            }
        }

        MineTweakerAPI.apply(new Add(new ShapedOreRecipe(toStack(output), input, width, height)));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        MineTweakerAPI.apply(new Remove(toStack(target)));
    }

    private static ItemStack toStack(IItemStack item) {
        if (item == null) return null;
        else {
            Object internal = item.getInternal();
            if (internal == null || !(internal instanceof ItemStack)) {
                MineTweakerAPI.getLogger().logError("Not a valid item stack: " + item);
            }
            return (ItemStack) internal;
        }
    }

    private static Object toObject(IIngredient ingredient) {
        if (ingredient == null) return null;
        else {
            if (ingredient instanceof IOreDictEntry) {
                return toString((IOreDictEntry) ingredient);
            } else if (ingredient instanceof IItemStack) {
                return toStack((IItemStack) ingredient);
            } else return null;
        }
    }

    private static Object[] toObjects(IIngredient[] list) {
        if (list == null)
            return null;
        Object[] ingredients = new Object[list.length];
        for (int x = 0; x < list.length; x++) {
            ingredients[x] = toObject(list[x]);
        }
        return ingredients;
    }

    private static Object toActualObject(IIngredient ingredient) {
        if (ingredient == null) return null;
        else {
            if (ingredient instanceof IOreDictEntry) {
                return OreDictionary.getOres(toString((IOreDictEntry) ingredient));
            } else if (ingredient instanceof IItemStack) {
                return toStack((IItemStack) ingredient);
            } else return null;
        }
    }

    private static String toString(IOreDictEntry entry) {
        return ((IOreDictEntry) entry).getName();
    }

    private static class Add implements IUndoableAction {
        IRecipe recipe;

        public Add(IRecipe add) {
            recipe = add;
        }

        @Override
        public void apply() {

            WorkbenchCraftingManager.getInstance().getRecipeList().add(recipe);
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            WorkbenchCraftingManager.getInstance().getRecipeList().remove(recipe);
        }

        @Override
        public String describe() {
            return "Adding Workbench Crafting Recipe for " + recipe.getRecipeOutput().getDisplayName();
        }

        @Override
        public String describeUndo() {
            return "Un-adding Workbench Crafting Recipe for " + recipe.getRecipeOutput().getDisplayName();
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

    }

    private static class Remove implements IUndoableAction {
        IRecipe recipe = null;
        ItemStack remove;

        public Remove(ItemStack rem) {
            remove = rem;
        }

        @Override
        public void apply() {

            for (Object obj : WorkbenchCraftingManager.getInstance().getRecipeList()) {
                if (obj instanceof IRecipe) {
                    IRecipe craft = (IRecipe) obj;
                    if (craft.getRecipeOutput().isItemEqual(remove)) {
                        recipe = craft;
                        WorkbenchCraftingManager.getInstance().getRecipeList().remove(obj);
                        break;
                    }
                }
            }
        }

        @Override
        public boolean canUndo() {
            return recipe != null;
        }

        @Override
        public void undo() {
            WorkbenchCraftingManager.getInstance().getRecipeList().add(recipe);
        }

        @Override
        public String describe() {
            return "Removing Workbench Crafting Recipe for " + remove.getDisplayName();
        }

        @Override
        public String describeUndo() {
            return "Un-removing Workbench Crafting Recipe for " + remove.getDisplayName();
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

    }

}