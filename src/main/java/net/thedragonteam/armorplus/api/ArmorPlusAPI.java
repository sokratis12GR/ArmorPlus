/*
 * Copyright (c) TheDragonTeam 2016.
 */
package net.thedragonteam.armorplus.api;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.recipe.WorkbenchRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * net.thedragonteam.armorplus.api
 * ArmorPlus created by sokratis12GR on 6/26/2016 12:42 PM.
 * - TheDragonTeam
 */
public class ArmorPlusAPI {


    public static final List<WorkbenchRecipe> WORKBENCH_RECIPES = new ArrayList<>();


    /**
     * Adds a Recipe to the Workbench Recipe Registry
     *
     * @param output The first output as an ItemStack
     * @param recipe The recipe for the ItemStack
     */
    public static void addCrusherRecipe(ItemStack output, Object... recipe) {
        WORKBENCH_RECIPES.add(new WorkbenchRecipe(output, recipe));
    }

}
