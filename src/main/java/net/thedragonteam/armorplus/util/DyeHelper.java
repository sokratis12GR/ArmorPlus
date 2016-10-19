/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import com.google.common.collect.Lists;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.List;

public class DyeHelper {

    private final List<ItemStack> dyes = Lists.<ItemStack>newArrayList();

    public List<ItemStack> getDyes() {
        return dyes;
    }

    public void init() {
        dyes.add(0, new ItemStack(Items.DYE, 1, 0));
        dyes.add(1, new ItemStack(Items.DYE, 1, 1));
        dyes.add(2, new ItemStack(Items.DYE, 1, 2));
        dyes.add(3, new ItemStack(Items.DYE, 1, 3));
        dyes.add(4, new ItemStack(Items.DYE, 1, 4));
        dyes.add(5, new ItemStack(Items.DYE, 1, 5));
        dyes.add(6, new ItemStack(Items.DYE, 1, 6));
        dyes.add(7, new ItemStack(Items.DYE, 1, 7));
        dyes.add(8, new ItemStack(Items.DYE, 1, 8));
        dyes.add(9, new ItemStack(Items.DYE, 1, 9));
        dyes.add(10, new ItemStack(Items.DYE, 1, 10));
        dyes.add(11, new ItemStack(Items.DYE, 1, 11));
        dyes.add(12, new ItemStack(Items.DYE, 1, 12));
        dyes.add(13, new ItemStack(Items.DYE, 1, 13));
        dyes.add(14, new ItemStack(Items.DYE, 1, 14));
        dyes.add(15, new ItemStack(Items.DYE, 1, 15));
        dyes.add(16, new ItemStack(Items.DYE, 1, 16));
    }
}
