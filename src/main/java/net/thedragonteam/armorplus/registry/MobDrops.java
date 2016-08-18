/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.core.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * Created by sokratis12GR on 4/4/2016.
 */
public class MobDrops {
    Random random = new Random();
    int min = 0;
    int max = 1;

    @SubscribeEvent
    public void playerKilledEnderDragon(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityDragon) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.ENDER_DRAGON_SCALE, enderdragonScaleDropAmount);
            if (debugMode) {
                LogHelper.info("Ender Dragon Dropped: " + ModItems.ENDER_DRAGON_SCALE.getRegistryName() + " x " + enderdragonScaleDropAmount);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledWither(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityWither) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.WITHER_BONE, witherBoneDropAmount);
            if (debugMode) {
                LogHelper.info("Wither Boss Dropped: " + ModItems.WITHER_BONE.getRegistryName() + " x " + witherBoneDropAmount);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledElderGuardian(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityGuardian && ((EntityGuardian) event.getEntity()).isElder()) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.GUARDIAN_SCALE, guardianScaleElderDropAmount);
            if (debugMode) {
                LogHelper.info("Elder Guardian Dropped:" + ModItems.GUARDIAN_SCALE.getRegistryName() + " x " + guardianScaleElderDropAmount);
            }
        }
    }

    @SubscribeEvent
    public void playerKilledGuardian(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityGuardian && !((EntityGuardian) event.getEntity()).isElder()) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.GUARDIAN_SCALE, random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Guardian Dropped: " + ModItems.GUARDIAN_SCALE.getRegistryName() + " x " + random.nextInt(max - min + 1) + min);
            }
        }
    }

}
