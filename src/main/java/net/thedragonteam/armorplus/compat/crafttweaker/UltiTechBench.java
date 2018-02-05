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
import net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin.toUltiTechShapedObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

@ZenClass("mods.armorplus.UltiTechBench")
public class UltiTechBench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new Add(new BaseShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new Add(new BaseShapedOreRecipe(7, toStack(output), toUltiTechShapedObjects(ingredients))));
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
            BaseCraftingManager.getUTBInstance().getRecipeList().add(recipe);
        }

        @Override
        public String describe() {
            return format("Adding %s recipe for %s", recipe.getRecipeOutput().getDisplayName(), "Ulti-Tech Bench");
        }

    }

    private static class Remove implements IAction {
        ItemStack remove;
        List<IRecipe> recipes = BaseCraftingManager.getUTBInstance().getRecipeList();

        public Remove(ItemStack remove) {
            this.remove = remove;
        }

        @Override
        public void apply() {
            CTArmorPlusPlugin.removeRecipe(recipes, remove);
        }

        @Override
        public String describe() {
            return format("Removing %s recipe for %s", remove.getDisplayName(), "Ulti-Tech Bench");
        }
    }
}