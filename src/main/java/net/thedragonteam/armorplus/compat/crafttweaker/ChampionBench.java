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
import net.thedragonteam.armorplus.api.crafting.championbench.CBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.championbench.CBShapelessOreRecipe;
import net.thedragonteam.armorplus.api.crafting.championbench.ChampionBenchCraftingManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin.toChampionShapedObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

@ZenClass("mods.armorplus.ChampionBench")
public class ChampionBench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new Add(new CBShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new Add(new CBShapedOreRecipe(toStack(output), toChampionShapedObjects(ingredients))));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(toStack(target)));
    }

    private static class Add implements IAction {
        IRecipe recipe;

        public Add(IRecipe add) {
            this.recipe = add;
        }

        @Override
        public void apply() {
            ChampionBenchCraftingManager.getInstance().getRecipeList().add(recipe);
        }

        @Override
        public String describe() {
            return format("Adding %s Recipe for Champion Bench", recipe.getRecipeOutput().getDisplayName());
        }

    }

    private static class Remove implements IAction {
        ItemStack remove;
        List<IRecipe> recipes = ChampionBenchCraftingManager.getInstance().getRecipeList();

        public Remove(ItemStack remove) {
            this.remove = remove;
        }

        @Override
        public void apply() {
            CTArmorPlusPlugin.removeRecipe(recipes, remove);
        }

        @Override
        public String describe() {
            return format("Removing %s Recipe for Champion Bench", remove.getDisplayName());
        }
    }
}