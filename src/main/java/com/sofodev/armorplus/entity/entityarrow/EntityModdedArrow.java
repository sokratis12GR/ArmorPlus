/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.sofodev.thedragonlib.util.ParticlesHelper.addParticle;
import static net.minecraft.init.Items.ARROW;
import static net.minecraft.init.Particles.CLOUD;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityModdedArrow extends EntityArrow {

    private final ArrowProperties arrowProperties;

    public EntityModdedArrow(World world) {
        super(EntityType.ARROW, world);
        this.arrowProperties = new ArrowProperties(EntityType.ARROW, 0.0, CLOUD, ARROW) {
            @Override
            public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
                //
            }
        };
    }

    public EntityModdedArrow(World world, ArrowProperties properties) {
        super(properties.getType(), world);
        this.arrowProperties = properties;
    }

    public EntityModdedArrow(World world, double x, double y, double z, ArrowProperties properties) {
        super(properties.getType(), x, y, z, world);
        this.arrowProperties = properties;
    }

    public EntityModdedArrow(World world, EntityLivingBase shooter, ArrowProperties properties) {
        super(properties.getType(), shooter, world);
        this.arrowProperties = properties;
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(this.arrowProperties.getDmg());
    }


    @Override
    public void tick() {
        super.tick();
        if (this.world.isRemote) {
            if (this.inGround) {
                if (this.timeInGround % 5 == 0) {
                    this.spawnPotionParticles();
                }
            } else {
                this.spawnPotionParticles();
            }
        }
    }

    private void spawnPotionParticles() {
        addParticle(this, this.arrowProperties.getParticleType(), this.posX, this.posY, this.posZ);
    }

    @Override
    protected ItemStack getArrowStack() {
        return this.arrowProperties.getArrowItem();
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        this.arrowProperties.arrowHit(living, getShooter());
    }
}
