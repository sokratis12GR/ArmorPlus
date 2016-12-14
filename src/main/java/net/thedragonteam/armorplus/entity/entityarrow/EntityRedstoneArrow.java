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
import net.thedragonteam.armorplus.util.ParticlesHelper;
import net.thedragonteam.armorplus.util.PotionUtils;

import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;

public class EntityRedstoneArrow extends EntityArrow {

    private EnumParticleTypes particle;

    public EntityRedstoneArrow(World worldIn) {
        super(worldIn);
    }

    public EntityRedstoneArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityRedstoneArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(3.5D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && !this.inGround) {
            ParticlesHelper.spawnParticle(this, EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.itemRedstoneArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        if (living != shootingEntity) {
            PotionUtils.addPotion(living, MobEffects.SLOWNESS, 180, 0, BAD);
        }
    }
}