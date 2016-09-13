/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.origin.redstone;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
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
 * net.thedragonteam.armorplus.armors.origin.redstone
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class RedstoneLeggings extends BaseArmor {

    public RedstoneLeggings() {
        super(ModItems.redstoneArmor, 0, EntityEquipmentSlot.LEGS, "redstone_leggings");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int redstoneArmorEffectlevel = ARPConfig.redstoneArmorEffectlevel + 1;
        if (ARPConfig.enableRedstoneLSpeed) {
            tooltip.add("\2479Ability: " + "\247rSpeed " + redstoneArmorEffectlevel);
            tooltip.add("\2473Use: " + "\247rEquip A Piece");
        }
        if (ARPConfig.enableFullRedstoneArmorEffect) {
            tooltip.add("\2479Ability: " + "\247rSpeed " + redstoneArmorEffectlevel);
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableRedstoneLSpeed && entity instanceof EntityLivingBase && !ARPConfig.enableFullRedstoneArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 240, ARPConfig.redstoneArmorEffectlevel, true, true));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_RED + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (ARPConfig.recipes == 0) {
            return repair.getItem() == Items.REDSTONE;
        }
        if (ARPConfig.recipes == 1) {
            return repair.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK);
        }
        return true;
    }
}