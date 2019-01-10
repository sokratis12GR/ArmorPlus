/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.crafttweaker.actions;

import com.sofodev.armorplus.api.crafting.IRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;

import static com.sofodev.armorplus.compat.crafttweaker.InputHelper.toStack;
import static java.lang.String.format;

public class AddShaped implements IAction {
    private BaseCraftingManager manager;
    private String name;
    private IRecipe recipe;

    public AddShaped(BaseCraftingManager manager, int size, IItemStack output, Object[] ingredients) {
        this.manager = manager;
        this.name = manager.getName();
        this.recipe = new BaseShapedOreRecipe(size, toStack(output), ingredients);
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
