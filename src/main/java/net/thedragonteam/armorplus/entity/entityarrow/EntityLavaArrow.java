/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.util.ParticlesHelper.spawnParticle;

public class EntityLavaArrow extends EntityArrow implements IArrowHelper {

    private EnumParticleTypes particle;

    public EntityLavaArrow(World worldIn) {
        super(worldIn);
    }

    public EntityLavaArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setParticle(EnumParticleTypes.FLAME);
    }

    public EntityLavaArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(5.5D);
    }

    @Override
    public void setParticle(EnumParticleTypes particleIn) {
        this.particle = particleIn;
    }

    @Override
    public EnumParticleTypes getParticle() {
        return this.particle;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && !this.inGround) {
            spawnParticle(this, getParticle(), this.posX, this.posY, this.posZ);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.itemLavaArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        if (living != shootingEntity) {
            living.setFire(6);
        }
    }

}