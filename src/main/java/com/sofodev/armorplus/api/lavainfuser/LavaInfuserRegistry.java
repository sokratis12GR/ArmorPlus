/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.lavainfuser;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class LavaInfuserRegistry {

    public static void addInfusingRecipe(ItemStack input, ItemStack output, Double exp) {
        LavaInfuserManager.getInstance().addInfusingRecipe(input, output, exp);
    }

    public static void addInfusingRecipe(ItemStack input, ItemStack output) {
        LavaInfuserManager.getInstance().addInfusingRecipe(input, output);
    }

    public static void addInfusingRecipe(Block input, ItemStack output, Double exp) {
        LavaInfuserManager.getInstance().addInfusingRecipe(new ItemStack(input), output, exp);
    }

    public static void addInfusingRecipe(Item input, ItemStack output, Double exp) {
        LavaInfuserManager.getInstance().addInfusingRecipe(new ItemStack(input), output, exp);
    }

    public static void removeInfusingRecipe(ItemStack recipe) {
        LavaInfuserManager.getInstance().getInfusingList().remove(recipe);
    }
}
