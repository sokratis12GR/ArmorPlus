/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus Created by sokratis12GR on 4/4/2016.
 * - TheDragonTeam
 */
public class MobDrops {
    private Random random = new Random();
    private int min = 0;
    private int max = 1;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void playerKilledEntity(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityDragon) {
            event.getEntityLiving().entityDropItem(getItemStack(materials, enderdragonScaleDropAmount, 3), 0.0F);
            if (debugMode) {
                LogHelper.info("Ender Dragon Dropped: " + getItemStack(materials, 3) + " x " + enderdragonScaleDropAmount);
            }
        } else if (event.getEntity() instanceof EntityWither) {
            event.getEntityLiving().entityDropItem(getItemStack(materials, witherBoneDropAmount, 2), 0.0F);
            if (debugMode) {
                LogHelper.info("Wither Boss Dropped: " + getItemStack(materials, 2) + " x " + witherBoneDropAmount);
            }
        } else if (event.getEntity() instanceof EntityWitherSkeleton) {
            event.getEntityLiving().entityDropItem(getItemStack(materials, random.nextInt(max - min + 1) + min, 2), 0.0F);
            if (debugMode) {
                LogHelper.info("Wither Skeleton Dropped: " + getItemStack(materials, 2) + " x " + random.nextInt(max - min + 1) + min);
            }
        } else if (event.getEntity() instanceof EntityGuardian) {
            event.getEntityLiving().entityDropItem(getItemStack(materials, random.nextInt(max - min + 1) + min, 1), 0.0F);
            if (debugMode) {
                LogHelper.info("Guardian Dropped: " + getItemStack(materials, 1) + " x " + random.nextInt(max - min + 1) + min);
            }
        } else if (event.getEntity() instanceof EntityElderGuardian) {
            event.getEntityLiving().entityDropItem(getItemStack(materials, guardianScaleElderDropAmount, 1), 0.0F);
            if (debugMode) {
                LogHelper.info("Elder Guardian Dropped:" + getItemStack(materials, 1) + " x " + guardianScaleElderDropAmount);
            }
        }
    }
}