/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.crafttweaker.actions;

import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;

import static com.sofodev.armorplus.common.compat.crafttweaker.InputHelper.toObjects;
import static com.sofodev.armorplus.common.compat.crafttweaker.InputHelper.toStack;
import static java.lang.String.format;

public class AddShapeless implements IAction {
    private BaseCraftingManager manager;
    private String name;
    private IRecipe recipe;

    public AddShapeless(BaseCraftingManager manager, IItemStack output, IIngredient[] ingredients) {
        this.manager = manager;
        this.name = manager.getName();
        this.recipe = new BaseShapelessOreRecipe(toStack(output), toObjects(ingredients));
    }

    @Override
    public void apply() {
        manager.getRecipeList().add(recipe);
    }

    @Override
    public String describe() {
        return format("Adding %s recipe for %s", recipe.getRecipeOutput().getDisplayName(), name);
    }
}
