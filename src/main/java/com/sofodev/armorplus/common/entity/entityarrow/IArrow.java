package com.sofodev.armorplus.common.entity.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;

public interface IArrow {

    ItemStack getItem();

    double getDmg();

    EnumParticleTypes getParticle();

    default void hit(EntityLivingBase living, Entity shootingEntity) {
    }
}
