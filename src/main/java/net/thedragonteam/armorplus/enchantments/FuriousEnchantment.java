/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;

public class FuriousEnchantment extends EnchantmentBase {

    public FuriousEnchantment() {
        super("furious", Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{
                        EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},
                1, 3, 10, 15, true, true);
    }

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        Levels levelIn = Levels.values()[level];
        switch (levelIn) {
            case ZERO:
                break;
            case ONE:
                user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 460, 0));
                break;
            case TWO:
                user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 460, 0));
                user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 460, 0));
                break;
            case THREE:
                user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 460, 1));
                user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 920, 0));
                break;
        }
    }
}
