/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.arrows;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedItem;
import com.sofodev.armorplus.common.iface.IModelHelper;
import com.sofodev.armorplus.common.util.EnumHelperUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.sofodev.armorplus.common.util.Utils.setName;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemSpecialArrow extends ItemArrow implements IModdedItem {

    private ArrowType type;

    public ItemSpecialArrow(ArrowType type) {
        this.type = type;
        this.setRegistryName(type.getItemArrowName());
        this.setTranslationKey(setName(type.getItemArrowName()));
        this.setCreativeTab(ArmorPlus.tabArmorPlusWeapons);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(type.getFormatting(), "ARROW_TYPE");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), type.getName());
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack itemstack, EntityLivingBase shooter) {
        return type.createArrow(world, shooter);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        type.addInformation(tooltip);
    }
}