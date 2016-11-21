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

public class EntityCoalArrow extends EntityArrow {

    public EntityCoalArrow(World worldIn) {
        super(worldIn);
    }

    public EntityCoalArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityCoalArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(2.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        EnumParticleTypes cloud = EnumParticleTypes.CLOUD;
        if (this.world.isRemote && !this.inGround) {
            ParticlesHelper.spawnParticle(this, cloud, this.posX, this.posY, this.posZ);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.itemCoalArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        if (living != shootingEntity) {
            living.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 180, 0, false, true));
        }
    }

}