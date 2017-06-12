/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.entityarrow

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.world.World
import net.thedragonteam.armorplus.registry.ModItems
import net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD
import net.thedragonteam.armorplus.util.PotionUtils.addPotion
import net.thedragonteam.thedragonlib.util.ParticlesHelper.spawnParticle

class EntityEnderDragonArrow : EntityArrow {

    constructor(worldIn: World) : super(worldIn)

    constructor(worldIn: World, shooter: EntityLivingBase) : super(worldIn, shooter)

    constructor(worldIn: World, x: Double, y: Double, z: Double) : super(worldIn, x, y, z)

    override fun setDamage(damageIn: Double) {
        super.setDamage(10.5)
    }

    override fun onUpdate() {
        super.onUpdate()
        if (this.world.isRemote && !this.inGround) {
            spawnParticle(this, EnumParticleTypes.DRAGON_BREATH, this.posX, this.posY, this.posZ)
        }
    }

    public override fun getArrowStack(): ItemStack {
        return ItemStack(ModItems.itemEnderDragonArrow)
    }

    public override fun arrowHit(living: EntityLivingBase?) {
        super.arrowHit(living)
        if (living !== shootingEntity) addPotion(living as EntityLivingBase, MobEffects.WITHER, 180, 4, BAD)
    }

}