/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityEnderDragonArrow;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.ArrowUtils;

import java.util.List;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemEnderDragonArrow extends ItemArrow implements IModelHelper {

    public ItemEnderDragonArrow() {
        this.setRegistryName("ender_dragon_arrow");
        this.setUnlocalizedName(setName("ender_dragon_arrow"));
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), "ender_dragon");
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityEnderDragonArrow(worldIn, shooter);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        ArrowUtils.addArrowInformation(tooltip, "Applies Wither 4", 8.5, TextFormatting.DARK_PURPLE);
    }

}