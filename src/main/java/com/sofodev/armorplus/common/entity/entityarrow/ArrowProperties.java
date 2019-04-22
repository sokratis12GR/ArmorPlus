/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.entity.entityarrow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ArrowProperties implements IArrow {

    private final double dmg;
    private final EnumParticleTypes particleType;
    private final ItemStack arrowItem;

    public ArrowProperties(double dmg, EnumParticleTypes particleType, Item arrowItem) {
        this(dmg, particleType, getItemStack(arrowItem));
    }

    public ArrowProperties(double dmg, EnumParticleTypes particleType, ItemStack arrowItem) {
        this.dmg = dmg;
        this.particleType = particleType;
        this.arrowItem = arrowItem;
    }

    @Override
    public double getDmg() {
        return dmg;
    }

    @Override
    public EnumParticleTypes getParticle() {
        return particleType;
    }

    @Override
    public ItemStack getItem() {
        return arrowItem;
    }
}
