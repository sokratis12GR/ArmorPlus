/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfuserRecipeMaker {

    public static List<InfusingRecipe> getFurnaceRecipes(IJeiHelpers helpers) {
        IStackHelper stackHelper = helpers.getStackHelper();
        LavaInfuserManager lavaInfuserManager = LavaInfuserManager.getInstance();
        Map<ItemStack, ItemStack> smeltingMap = lavaInfuserManager.getInfusingList();

        List<InfusingRecipe> recipes = new ArrayList<>();

        for (Map.Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet()) {
            ItemStack input = entry.getKey();
            ItemStack output = entry.getValue();

            List<ItemStack> inputs = stackHelper.getSubtypes(input);
            InfusingRecipe recipe = new InfusingRecipe(inputs, output);
            recipes.add(recipe);
        }

        return recipes;
    }

}