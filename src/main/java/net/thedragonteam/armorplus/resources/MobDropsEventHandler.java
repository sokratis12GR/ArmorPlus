/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.resources;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

@EventBusSubscriber(modid = ArmorPlus.MODID)
public class MobDropsEventHandler {
    private static Random random = new Random();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        int min = 0;
        int max = 1;
        if (event.getEntityLiving() instanceof EntityDragon) {
            if (enderDragonScaleDrop) {
                event.getEntityLiving().entityDropItem(getItemStack(materials, enderDragonScaleDropAmount, 3), 0.0f);
            }
            if (debugMode) {
                LogHelper.INSTANCE.info("Ender Dragon Dropped: " + getItemStack(materials, 3) + " x " + enderDragonScaleDropAmount);
            }
        }
        if (event.getEntityLiving() instanceof EntityWither) {
            if (witherBoneWitherBossDrop) {
                event.getEntityLiving().entityDropItem(getItemStack(materials, witherBoneWitherBossDropAmount, 2), 0.0f);
            }
            if (debugMode) {
                LogHelper.INSTANCE.info("Wither Boss Dropped: " + getItemStack(materials, 2) + " x " + witherBoneWitherBossDropAmount);
            }
        }

        if (event.getEntityLiving() instanceof EntityWitherSkeleton) {
            int droppedWitherBoneAmount = random.nextInt(max - min + 1) + min;
            if (witherBoneWitherSkeletonDrop) {
                event.getEntityLiving().entityDropItem(getItemStack(materials, droppedWitherBoneAmount, 2), 0.0f);
            }
            if (debugMode) {
                LogHelper.INSTANCE.info("Wither Skeleton Dropped: " + getItemStack(materials, 2) + " x " + droppedWitherBoneAmount);
            }
        }
        if (event.getEntityLiving() instanceof EntityElderGuardian) {
            if (guardianScaleElderGuardianDrop) {
                event.getEntityLiving().entityDropItem(getItemStack(materials, guardianScaleElderDropAmount, 1), 0.0f);
            }
            if (debugMode) {
                LogHelper.INSTANCE.info("Elder Guardian Dropped:" + getItemStack(materials, 1) + " x " + guardianScaleElderDropAmount);
            }
        } else if (event.getEntityLiving() instanceof EntityGuardian) {
            int droppedGuardianScaleAmount = random.nextInt(max - min + 1) + min;
            if (guardianScaleGuardianDrop) {
                event.getEntityLiving().entityDropItem(getItemStack(materials, droppedGuardianScaleAmount, 1), 0.0f);
            }
            if (debugMode) {
                LogHelper.INSTANCE.info("Guardian Dropped: " + getItemStack(materials, 1) + " x " + droppedGuardianScaleAmount);
            }
        }
    }
}