/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static net.minecraft.init.Items.ARROW;
import static net.minecraft.util.EnumParticleTypes.CLOUD;
import static net.thedragonteam.thedragonlib.util.ParticlesHelper.spawnParticle;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityModdedArrow extends EntityArrow {

    private final ArrowProperties properties;

    public EntityModdedArrow(World worldIn) {
        super(worldIn);
        this.properties = new ArrowProperties(0.0, CLOUD, ARROW);
    }

    public EntityModdedArrow(World worldIn, ArrowProperties properties) {
        super(worldIn);
        this.properties = properties;
    }

    public EntityModdedArrow(World worldIn, double x, double y, double z, ArrowProperties properties) {
        super(worldIn, x, y, z);
        this.properties = properties;
    }

    public EntityModdedArrow(World worldIn, EntityLivingBase shooter, ArrowProperties properties) {
        super(worldIn, shooter);
        this.properties = properties;
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(this.properties.getDmg());
    }

    @Override
    public double getDamage() {
        return super.getDamage();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && !this.inGround) {
            spawnParticle(this, this.properties.getParticle(), this.posX, this.posY, this.posZ);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return this.properties.getItem();
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        this.properties.hit(living, shootingEntity);
    }
}
