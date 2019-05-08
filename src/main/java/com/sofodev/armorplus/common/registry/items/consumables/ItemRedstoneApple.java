/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.consumables;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemRedstoneApple extends ItemFood {

    public ItemRedstoneApple(Properties properties) {
        super(4, 2.0f, false, properties);
        this.setAlwaysEdible();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote && !stack.isEmpty()) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 6000, 1));
        }
    }

}