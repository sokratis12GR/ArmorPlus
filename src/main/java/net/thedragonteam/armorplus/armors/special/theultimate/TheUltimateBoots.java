/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.special.theultimate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

import static net.thedragonteam.armorplus.ARPConfig.enableTheUltimateArmorDeBuffs;
import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.special.theultimate
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class TheUltimateBoots extends BaseArmor {

    public TheUltimateBoots() {
        super(ModItems.theUltimateArmor, 0, EntityEquipmentSlot.FEET, "the_ultimate_boots_full");
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("\2479Ability: " + "\247rThe Most OverPowered Armor");
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (ARPConfig.enableFlightAbility) {

            if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                entity.capabilities.allowFlying = true;
            } else {
                entity.capabilities.isFlying = false;
                entity.capabilities.allowFlying = false;
            }
        }
        if (ARPConfig.enableTheUltimateArmorInvincibility) {
            if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                entity.capabilities.disableDamage = true;
            } else {
                entity.capabilities.disableDamage = false;
            }
        }
        if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
        } else {
            if (entity instanceof EntityLivingBase && enableTheUltimateArmorDeBuffs) {
                entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 2, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 2, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0, true, true));

                entity.motionX = 0;
                if (((EntityLivingBase) entity).onGround)
                    entity.motionY = 0;
                entity.motionZ = 0;
                ((EntityPlayer) entity).velocityChanged = true; // assumes that entity instanceof EntityPlayer
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GREEN + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.theUltimateMaterial;
    }
}