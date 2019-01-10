/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.consumables;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.stream.IntStream;

import static net.minecraft.item.EnumRarity.EPIC;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemRedstoneApple extends ItemFood implements IModdedItem {

    public ItemRedstoneApple() {
        super(4, 2.0f, false);
        this.setHasSubtypes(true);
        this.setRegistryName(Utils.setRL("redstone_apple"));
        this.setTranslationKey(Utils.setName("redstone_apple"));
        this.setAlwaysEdible();
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getMetadata() > 0;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return (stack.getMetadata() == 0) ? EnumRarity.RARE : EPIC;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote && !stack.isEmpty()) {
            player.addPotionEffect(stack.getMetadata() > 0 ? new PotionEffect(MobEffects.SPEED, 6000, 1) : new PotionEffect(MobEffects.SPEED, 200, 1));
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            items.add(getItemStack(this));
            items.add(getItemStack(this, 1));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        IntStream.range(0, 2).forEach(this::initModel);
    }
}