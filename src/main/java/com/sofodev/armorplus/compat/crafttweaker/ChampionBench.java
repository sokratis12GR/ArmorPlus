/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.crafttweaker;

import com.sofodev.armorplus.compat.crafttweaker.actions.AddShaped;
import com.sofodev.armorplus.compat.crafttweaker.actions.AddShapeless;
import com.sofodev.armorplus.compat.crafttweaker.actions.Remove;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static com.sofodev.armorplus.api.crafting.base.BaseCraftingManager.getCBInstance;

@ZenClass("mods.armorplus.ChampionBench")
public class ChampionBench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new AddShapeless(getCBInstance(), output, ingredients));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new AddShaped(getCBInstance(), 9, output, CTArmorPlusPlugin.toChampionShapedObjects(ingredients)));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(getCBInstance(), InputHelper.toStack(target)));
    }
}