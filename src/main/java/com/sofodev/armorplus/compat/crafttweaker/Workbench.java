/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.crafttweaker;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.compat.crafttweaker.actions.AddShaped;
import com.sofodev.armorplus.compat.crafttweaker.actions.AddShapeless;
import com.sofodev.armorplus.compat.crafttweaker.actions.Remove;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static com.sofodev.armorplus.compat.crafttweaker.CTArmorPlusPlugin.toWorkbenchShapedObjects;
import static com.sofodev.armorplus.compat.crafttweaker.InputHelper.toStack;

@ZenClass("mods.armorplus.Workbench")
public class Workbench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new AddShapeless(BaseCraftingManager.getWBInstance(), output, ingredients));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new AddShaped(BaseCraftingManager.getWBInstance(), 3, output, toWorkbenchShapedObjects(ingredients)));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(BaseCraftingManager.getWBInstance(), toStack(target)));
    }
}