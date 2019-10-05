/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entities.entityarrow;

import com.sofodev.armorplus.common.registry.constants.APPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumParticleTypes;

import static com.sofodev.armorplus.common.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.common.util.PotionUtils.addPotion;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ArrowProperties implements IArrow {

    private final double dmg;
    private final EnumParticleTypes particleType;
    private final ItemStack arrowStack;
    private final Potion potion;
    private final int amplifier;

    public ArrowProperties(double dmg, EnumParticleTypes particleType, Item arrowItem, Potion potion, int amplifier) {
        this(dmg, particleType, getItemStack(arrowItem), potion, amplifier);
    }

    public ArrowProperties(double dmg, EnumParticleTypes particleType, ItemStack arrowStack, Potion potion, int amplifier) {
        this.dmg = dmg;
        this.particleType = particleType;
        this.arrowStack = arrowStack;
        this.potion = potion;
        this.amplifier = amplifier;
    }
    public ArrowProperties(double dmg, EnumParticleTypes particleType, Item arrowItem) {
        this(dmg, particleType, getItemStack(arrowItem));
    }

    public ArrowProperties(double dmg, EnumParticleTypes particleType, ItemStack arrowStack) {
        this.dmg = dmg;
        this.particleType = particleType;
        this.arrowStack = arrowStack;
        this.potion = APPotions.EMPTY;
        this.amplifier = 0;
    }

    public static ArrowProperties create(double dmg, EnumParticleTypes particle, ItemStack arrow, Potion potion) {
        return new ArrowProperties(dmg, particle, arrow, potion, 0);
    }

    public static ArrowProperties create(double dmg, EnumParticleTypes particle, Item arrow, Potion potion) {
        return new ArrowProperties(dmg, particle, arrow, potion, 0);
    }

    @Override
    public double getDmg() {
        return this.dmg;
    }

    @Override
    public EnumParticleTypes getParticle() {
        return this.particleType;
    }

    @Override
    public Potion getPotion() {
        return this.potion;
    }

    @Override
    public int getAmplifier() {
        return this.amplifier;
    }

    @Override
    public ItemStack getItem() {
        return arrowStack;
    }

    @Override
    public IArrow hit(EntityLivingBase living, Entity shootingEntity) {
        if (potion != APPotions.EMPTY) addPotion(living, potion, 180, amplifier, BAD);
        return this;
    }
}
