package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumParticleTypes;

import static com.sofodev.armorplus.common.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.common.util.PotionUtils.addPotion;

public interface IArrow {

    ItemStack getItem();

    double getDmg();

    EnumParticleTypes getParticle();

    Potion getPotion();

    int getAmplifier();

    default IArrow hit(EntityLivingBase living, Entity shootingEntity) {
        return this;
    }
}
