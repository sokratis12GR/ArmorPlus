/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;

import java.util.List;

import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemSpecialArrow extends ItemArrow implements IModelHelper {

    private ArrowType type;
    private EnumRarity arrowColor;

    public ItemSpecialArrow(ArrowType type) {
        this.type = type;
        this.setRegistryName(type.getItemArrowName());
        this.setUnlocalizedName(setName(type.getItemArrowName()));
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        this.arrowColor = addRarity("ARROW_TYPE", type.getFormatting(), "ARROW_TYPE");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return arrowColor;
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