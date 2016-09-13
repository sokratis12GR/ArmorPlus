/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.special.enderdragon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.special.enderdragon
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class EnderDragonBoots extends BaseArmor {

    public EnderDragonBoots() {
        super(ModItems.enderDragonArmor, 0, EntityEquipmentSlot.FEET, "ender_dragon_boots");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("\2479Ability: " + "\247rFlight");
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (ARPConfig.enableFlightAbility) {
            if (head != null && head.getItem() == ModItems.enderDragonHelmet && chest != null && chest.getItem() == ModItems.enderDragonChestplate && legs != null && legs.getItem() == ModItems.enderDragonLeggings && feet != null && feet.getItem() == ModItems.enderDragonBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                entity.capabilities.allowFlying = true;
                entity.removePotionEffect(MobEffects.WITHER);
            } else {
                entity.capabilities.isFlying = false;
                entity.capabilities.allowFlying = false;
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_PURPLE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.enderDragonScale;
    }
}