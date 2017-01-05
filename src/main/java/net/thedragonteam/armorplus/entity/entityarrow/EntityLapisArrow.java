/*
 * Copyright (c) TheDragonTeam 2016-2017.
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

public class EntityLapisArrow extends EntityArrow{

    private EnumParticleTypes particle;

    public EntityLapisArrow(World worldIn) {
        super(worldIn);
    }

    public EntityLapisArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityLapisArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(3.5D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        ParticlesHelper.spawnParticle(this,EnumParticleTypes.WATER_DROP, this.posX, this.posY, this.posZ);
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.itemLapisArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);

        if (living != shootingEntity) {
            PotionUtils.addPotion(living, MobEffects.NAUSEA, 180, 0, BAD);
        }
    }

}