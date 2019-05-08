/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.jei.lavainfuser;

import com.sofodev.armorplus.api.lavainfuser.LavaInfuserManager;
import com.sofodev.armorplus.common.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfuserRecipeMaker {

    public InfuserRecipeMaker() {
    }

    public static List<LavaInfuserRecipe> getInfuserRecipes() {
        LavaInfuserManager lavaInfuserManager = LavaInfuserManager.getInstance();
        Map<ItemStack, ItemStack> infusingMap = lavaInfuserManager.getInfusingList();

        List<LavaInfuserRecipe> recipes = new ArrayList<>();

        infusingMap.forEach((input, output) -> {
            LavaInfuserRecipe recipe = new LavaInfuserRecipe(input, output);
            recipes.add(recipe);
        });

        return recipes;
    }

}