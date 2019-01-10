/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import net.minecraft.entity.Entity;
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

    private final ArrowProperties arrowProperties;

    public EntityModdedArrow(World worldIn) {
        super(worldIn);
        this.arrowProperties = new ArrowProperties(0.0, CLOUD, ARROW) {
            @Override
            public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            }
        };
    }

    public EntityModdedArrow(World worldIn, ArrowProperties properties) {
        super(worldIn);
        this.arrowProperties = properties;
    }

    public EntityModdedArrow(World worldIn, double x, double y, double z, ArrowProperties properties) {
        super(worldIn, x, y, z);
        this.arrowProperties = properties;
    }

    public EntityModdedArrow(World worldIn, EntityLivingBase shooter, ArrowProperties properties) {
        super(worldIn, shooter);
        this.arrowProperties = properties;
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(this.arrowProperties.getDmg());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && !this.inGround) {
            spawnParticle(this, this.arrowProperties.getParticleType(), this.posX, this.posY, this.posZ);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return this.arrowProperties.getArrowItem();
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        this.arrowProperties.arrowHit(living, shootingEntity);
    }
}
