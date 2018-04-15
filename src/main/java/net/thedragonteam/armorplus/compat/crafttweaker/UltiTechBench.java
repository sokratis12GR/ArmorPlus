/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import net.thedragonteam.armorplus.compat.crafttweaker.actions.Add;
import net.thedragonteam.armorplus.compat.crafttweaker.actions.Remove;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager.getUTBInstance;
import static net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin.toUltiTechShapedObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

@ZenClass("mods.armorplus.UltiTechBench")
public class UltiTechBench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new Add(getUTBInstance(), new BaseShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new Add(getUTBInstance(), new BaseShapedOreRecipe(7, toStack(output), toUltiTechShapedObjects(ingredients))));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(getUTBInstance(), toStack(target)));
    }
}