/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

/**
 * @author Sokratis Fotkatzikis
 */
public abstract class ArrowProperties {

    private final EntityType<?> type;
    private final double dmg;
    private final IParticleData particleType;
    private final ItemStack arrowItem;

    public ArrowProperties(EntityType<?> type, double dmg, IParticleData particleType, Item arrowItem) {
        this(type, dmg, particleType, new ItemStack(arrowItem));
    }

    public ArrowProperties(EntityType<?> type, double dmg, IParticleData particleType, ItemStack arrowItem) {
        this.type = type;
        this.dmg = dmg;
        this.particleType = particleType;
        this.arrowItem = arrowItem;
    }

    public EntityType<?> getType() {
        return type;
    }

    public double getDmg() {
        return dmg;
    }

    public IParticleData getParticleType() {
        return particleType;
    }

    public ItemStack getArrowItem() {
        return arrowItem;
    }

    public abstract void arrowHit(EntityLivingBase living, Entity shootingEntity);
}
