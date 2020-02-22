/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.crafttweaker.actions;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.common.compat.crafttweaker.CTArmorPlusPlugin;
import crafttweaker.IAction;
import net.minecraft.item.ItemStack;

import static java.lang.String.format;

public class Remove implements IAction {
    private BaseCraftingManager manager;
    private String name;
    private ItemStack remove;

    public Remove(BaseCraftingManager manager, ItemStack remove) {
        this.manager = manager;
        this.name = manager.getName();
        this.remove = remove;
    }

    @Override
    public void apply() {
        //   manager.getRecipeList().stream().filter(
        //       iRecipe -> iRecipe != null && areItemsEqual(iRecipe.getRecipeOutput(), remove)
        //   ).forEach(
        //       iRecipe -> manager.getRecipeList().remove(iRecipe)
        //   );
        CTArmorPlusPlugin.removeRecipe(manager.getRecipeList(), remove);
    }

    @Override
    public String describe() {
        return format("Removing %s recipe for %s", remove.getDisplayName(), name);
    }
}