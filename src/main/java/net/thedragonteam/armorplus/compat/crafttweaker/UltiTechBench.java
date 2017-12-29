/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UTBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UTBShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

import static net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin.toUltiTechShapedObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

@ZenClass("mods.armorplus.UltiTechBench")
public class UltiTechBench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new Add(new UTBShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new Add(new UTBShapedOreRecipe(toStack(output), toUltiTechShapedObjects(ingredients))));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(toStack(target)));
    }

    private static class Add implements IAction {
        IRecipe recipe;

        public Add(IRecipe add) {
            recipe = add;
        }

        @Override
        public void apply() {
            UltiTechBenchCraftingManager.getInstance().getRecipeList().add(recipe);
        }

        @Override
        public String describe() {
            return "Adding Ulti-Tech Bench Recipe for " + recipe.getRecipeOutput().getDisplayName();
        }

    }

    private static class Remove implements IAction {
        ItemStack remove;
        List<IRecipe> recipes = UltiTechBenchCraftingManager.getInstance().getRecipeList();

        public Remove(ItemStack rem) {
            remove = rem;
        }

        @Override
        public void apply() {
            CTArmorPlusPlugin.removeRecipe(recipes, remove);
        }

        @Override
        public String describe() {
            return "Removing Ulti-Tech Bench Recipe for " + remove.getDisplayName();
        }

    }
}