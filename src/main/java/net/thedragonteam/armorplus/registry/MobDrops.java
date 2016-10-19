/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus Created by sokratis12GR on 4/4/2016.
 * - TheDragonTeam
 */
public class MobDrops {
    Random random = new Random();
    int min = 0;
    int max = 1;

    @SubscribeEvent
    public void playerKilledEnderDragon(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityDragon) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.enderDragonScale, enderdragonScaleDropAmount);
            if (debugMode) {
                LogHelper.info("Ender Dragon Dropped: " + ModItems.enderDragonScale.getRegistryName() + " x " + enderdragonScaleDropAmount);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledWither(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityWither) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.witherBone, witherBoneDropAmount);
            if (debugMode) {
                LogHelper.info("Wither Boss Dropped: " + ModItems.witherBone.getRegistryName() + " x " + witherBoneDropAmount);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledElderGuardian(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityGuardian && ((EntityGuardian) event.getEntity()).isElder()) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.guardianScale, guardianScaleElderDropAmount);
            if (debugMode) {
                LogHelper.info("Elder Guardian Dropped:" + ModItems.guardianScale.getRegistryName() + " x " + guardianScaleElderDropAmount);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledWitherSkeleton(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntitySkeleton && ((EntitySkeleton) event.getEntity()).func_189771_df() == SkeletonType.WITHER) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.witherBone, random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Wither Skeleton Dropped: " + ModItems.witherBone.getRegistryName() + " x " + random.nextInt(max - min + 1) + min);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledGuardian(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityGuardian && !((EntityGuardian) event.getEntity()).isElder()) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.guardianScale, random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Guardian Dropped: " + ModItems.guardianScale.getRegistryName() + " x " + random.nextInt(max - min + 1) + min);
            }
        }
    }

}
