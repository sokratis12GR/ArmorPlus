/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class FuriousEnchantment extends Enchantment {

    public FuriousEnchantment() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
        setRegistryName("furious");
        setName("furious");
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }


    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack);
    }

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        if (level == 1) {
            user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 460, 0));
            user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 460, 0));
        }
        if (level == 2) {
            user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 460, 1));
            user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 460, 1));
        }
        if (level == 3) {
            user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 460, 2));
            user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 460, 2));
        }
    }
}
