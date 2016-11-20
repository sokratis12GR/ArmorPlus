/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;

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
            event.getEntityLiving().dropItem(new ItemStack(materials, 1, 3).getItem(), enderdragonScaleDropAmount);
            if (debugMode) {
                LogHelper.info("Ender Dragon Dropped: " + new ItemStack(materials, 1, 3).getItem().getRegistryName() + " x " + enderdragonScaleDropAmount);
            }
        } else if (event.getEntity() instanceof EntityWither) {
            event.getEntityLiving().dropItem(new ItemStack(materials, 1, 2).getItem(), witherBoneDropAmount);
            if (debugMode) {
                LogHelper.info("Wither Boss Dropped: " + new ItemStack(materials, 1, 2).getItem().getRegistryName() + " x " + witherBoneDropAmount);
            }
        } else if (event.getEntity() instanceof EntityWitherSkeleton) {
            event.getEntityLiving().dropItem(new ItemStack(materials, 1, 2).getItem(), random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Wither Skeleton Dropped: " + new ItemStack(materials, 1, 2).getItem().getRegistryName() + " x " + random.nextInt(max - min + 1) + min);
            }
        } else if (event.getEntity() instanceof EntityGuardian) {
            event.getEntityLiving().dropItem(new ItemStack(materials, 1, 1).getItem(), random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Guardian Dropped: " + new ItemStack(materials, 1, 1).getItem().getRegistryName() + " x " + random.nextInt(max - min + 1) + min);
            }
        } else if (event.getEntity() instanceof EntityElderGuardian) {
            event.getEntityLiving().dropItem(new ItemStack(materials, 1, 1).getItem(), guardianScaleElderDropAmount);
            if (debugMode) {
                LogHelper.info("Elder Guardian Dropped:" + new ItemStack(materials, 1, 1).getItem().getRegistryName() + " x " + guardianScaleElderDropAmount);
            }
        }
    }
}