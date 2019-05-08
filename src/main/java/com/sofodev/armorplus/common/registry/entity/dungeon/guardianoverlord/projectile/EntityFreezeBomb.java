/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.sofodev.armorplus.ArmorPlus.RegistryEvents.ENTITY_FREEZE_BOMB;
import static net.minecraft.world.EnumDifficulty.HARD;
import static net.minecraft.world.EnumDifficulty.NORMAL;

/**
 * @author Sokratis Fotkatzikis
 **/
public class EntityFreezeBomb extends EntityThrowable {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityFreezeBomb.class, DataSerializers.BOOLEAN);

    private Entity shooter;

    public EntityFreezeBomb(World world) {
        super(ENTITY_FREEZE_BOMB, world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityFreezeBomb(World world, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(ENTITY_FREEZE_BOMB, shooter, world);
        this.setSize(0.3125F, 0.3125F);
        this.setVelocity(accelX, accelY, accelZ);
        this.shooter = shooter;
    }

    @OnlyIn(Dist.CLIENT)
    public EntityFreezeBomb(World world, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ENTITY_FREEZE_BOMB, x, y, z, world);
        this.setSize(0.3125F, 0.3125F);
        this.setVelocity(accelX, accelY, accelZ);
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isBurning() {
        return false;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult result) {
        if (this.world.isRemote || result.entity == null) {
            return;
        }
//        if (this.shootingEntity == null) {
//            result.entityHit.attackEntityFrom(DamageSource.MAGIC, 0.0F);
//        } else if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)) {
//            if (result.entityHit.isEntityAlive()) {
//                this.applyEnchantments(this.shootingEntity, result.entityHit);
//            }
//            this.shootingEntity.heal(5.0F);
//        }

        if (result.entity instanceof EntityLivingBase) {
            EnumDifficulty difficulty = this.world.getDifficulty();
            int durationMultiplier = 0;
            if (difficulty == NORMAL) {
                durationMultiplier = 10;
            } else if (difficulty == HARD) {
                durationMultiplier = 40;
            }

            if (durationMultiplier > 0) {
                ((EntityLivingBase) result.entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * durationMultiplier, 1));
            }
        }
        this.remove();
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(INVULNERABLE, false);
    }

}