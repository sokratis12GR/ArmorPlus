/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.PotionUtils;

import static net.thedragonteam.armorplus.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;

public class EntityCoalArrow extends EntityArrow implements IArrowHelper {

    private EnumParticleTypes particle;

    public EntityCoalArrow(World worldIn) {
        super(worldIn);
    }

    public EntityCoalArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setParticle(EnumParticleTypes.CLOUD);
    }

    public EntityCoalArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(2.0D);
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
        return new ItemStack(ModItems.itemCoalArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        if (living != shootingEntity) {
            PotionUtils.addPotion(living, MobEffects.BLINDNESS, 180, 0, BAD);
        }
    }
}