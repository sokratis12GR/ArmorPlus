/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry

import net.minecraft.entity.boss.EntityDragon
import net.minecraft.entity.boss.EntityWither
import net.minecraft.entity.monster.EntityElderGuardian
import net.minecraft.entity.monster.EntityGuardian
import net.minecraft.entity.monster.EntityWitherSkeleton
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.registry.ModItems.materials
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack
import net.thedragonteam.thedragonlib.util.LogHelper
import java.util.*

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus Created by sokratis12GR on 4/4/2016.
 * - TheDragonTeam
 */
class MobDrops {
    private val random = Random()
    private val min = 0
    private val max = 1

    @SubscribeEvent(priority = EventPriority.NORMAL)
    fun playerKilledEntity(event: LivingDropsEvent) {
        when {
            event.entity is EntityDragon -> {
                event.entityLiving.entityDropItem(getItemStack(materials, enderdragonScaleDropAmount, 3), 0.0f)
                if (debugMode)
                    LogHelper.info("Ender Dragon Dropped: " + getItemStack(materials, 3) + " x " + enderdragonScaleDropAmount)
            }
            event.entity is EntityWither -> {
                event.entityLiving.entityDropItem(getItemStack(materials, witherBoneDropAmount, 2), 0.0f)
                if (debugMode)
                    LogHelper.info("Wither Boss Dropped: " + getItemStack(materials, 2) + " x " + witherBoneDropAmount)
            }
            event.entity is EntityWitherSkeleton -> {
                event.entityLiving.entityDropItem(getItemStack(materials, random.nextInt(max - min + 1) + min, 2), 0.0f)
                if (debugMode)
                    LogHelper.info("Wither Skeleton Dropped: " + getItemStack(materials, 2) + " x " + random.nextInt(max - min + 1) + min)
            }
            event.entity is EntityGuardian -> {
                event.entityLiving.entityDropItem(getItemStack(materials, random.nextInt(max - min + 1) + min, 1), 0.0f)
                if (debugMode)
                    LogHelper.info("Guardian Dropped: " + getItemStack(materials, 1) + " x " + random.nextInt(max - min + 1) + min)
            }
            event.entity is EntityElderGuardian -> {
                event.entityLiving.entityDropItem(getItemStack(materials, guardianScaleElderDropAmount, 1), 0.0f)
                if (debugMode)
                    LogHelper.info("Elder Guardian Dropped:" + getItemStack(materials, 1) + " x " + guardianScaleElderDropAmount)
            }
        }
    }
}