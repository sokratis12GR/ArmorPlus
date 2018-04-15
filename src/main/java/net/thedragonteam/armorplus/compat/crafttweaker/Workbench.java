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

import static net.thedragonteam.armorplus.api.crafting.base.BaseCraftingManager.getWBInstance;
import static net.thedragonteam.armorplus.compat.crafttweaker.CTArmorPlusPlugin.toWorkbenchShapedObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObjects;
import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toStack;

@ZenClass("mods.armorplus.Workbench")
public class Workbench {

    @ZenMethod
    public static void addShapeless(IItemStack output, IIngredient[] ingredients) {
        CraftTweakerAPI.apply(new Add(getWBInstance(), new BaseShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }

    @ZenMethod
    public static void addShaped(IItemStack output, IIngredient[][] ingredients) {
        CraftTweakerAPI.apply(new Add(getWBInstance(), new BaseShapedOreRecipe(3, toStack(output), toWorkbenchShapedObjects(ingredients))));
    }

    @ZenMethod
    public static void remove(IItemStack target) {
        CraftTweakerAPI.apply(new Remove(getWBInstance(), toStack(target)));
    }
}