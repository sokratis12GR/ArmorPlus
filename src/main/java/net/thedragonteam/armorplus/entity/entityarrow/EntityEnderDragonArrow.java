/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ParticlesHelper;

public class EntityEnderDragonArrow extends EntityArrow {

    public EntityEnderDragonArrow(World worldIn) {
        super(worldIn);
    }

    public EntityEnderDragonArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityEnderDragonArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(10.5D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EnumParticleTypes dragonBreath = EnumParticleTypes.DRAGON_BREATH;
        if (this.worldObj.isRemote && !this.inGround) {
            ParticlesHelper.spawnParticle(this, dragonBreath, this.posX, this.posY, this.posZ);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.itemEnderDragonArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        World world = living.getEntityWorld();
        if (living != shootingEntity) {
            living.addPotionEffect(new PotionEffect(MobEffects.WITHER, 180, 4, false, true));
        }
    }

}