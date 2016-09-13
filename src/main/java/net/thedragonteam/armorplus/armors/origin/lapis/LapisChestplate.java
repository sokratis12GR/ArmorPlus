/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.origin.lapis;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.origin.lapis
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class LapisChestplate extends BaseArmor {

    public LapisChestplate() {
        super(ModItems.lapisArmor, 0, EntityEquipmentSlot.CHEST, "lapis_chestplate");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (ARPConfig.enableLapisHBreathing) {
            tooltip.add("\2479Ability: " + "\247rWater Breathing ");
            tooltip.add("\2473Use: " + "\247rEquip A Piece");
        }
        if (ARPConfig.enableFullLapisArmorEffect) {
            tooltip.add("\2479Ability: " + "\247rWater Breathing ");
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableLapisCBreathing && entity instanceof EntityLivingBase && !ARPConfig.enableFullLapisArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_BLUE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == getItemFromBlock(Blocks.LAPIS_BLOCK);
    }
}