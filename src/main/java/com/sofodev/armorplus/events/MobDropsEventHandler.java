/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class MobDropsEventHandler {
    private static Random random = new Random();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        int min = 0;
        int max = 1;
        int randomDrop = random.nextInt(max - min + 1) + min;
        Entity entity = event.getEntity();
        if (entity instanceof EntityDragon) {
            registerMobDrop(event, ModConfig.EntitiesConfig.mob_drops.ender_dragon_scale.drop, getItemStack(ModItems.materials, ModConfig.EntitiesConfig.mob_drops.ender_dragon_scale.dropAmount, 3));
            registerTrophyDrop(event, 3);
        }
        if (entity instanceof EntityWither) {
            registerMobDrop(event, ModConfig.EntitiesConfig.mob_drops.wither_bone.bossDrop, getItemStack(ModItems.materials, ModConfig.EntitiesConfig.mob_drops.wither_bone.dropAmount, 2));
            registerTrophyDrop(event, 2);
        }
        if (entity instanceof EntityWitherSkeleton) {
            registerMobDrop(event, ModConfig.EntitiesConfig.mob_drops.wither_bone.witherSkeletonDrop, getItemStack(ModItems.materials, randomDrop, 2));
        }
        if (entity instanceof EntityGuardian) {
            registerMobDrop(event, ModConfig.EntitiesConfig.mob_drops.guardian_scale.guardianDrop, getItemStack(ModItems.materials, randomDrop, 1));
        }
        if (entity instanceof EntityElderGuardian) {
            registerMobDrop(event, ModConfig.EntitiesConfig.mob_drops.guardian_scale.elderDrop, getItemStack(ModItems.materials, ModConfig.EntitiesConfig.mob_drops.guardian_scale.dropAmount, 1));
            registerTrophyDrop(event, 1);
        }
        if (entity instanceof EntitySkeletalKing) {
            registerTrophyDrop(event, 4);
        }
    }

    private static void registerTrophyDrop(LivingDropsEvent event, int trophy) {
        registerMobDrop(event, ModConfig.EntitiesConfig.mob_drops.trophy.enableTrophyDrops, getItemStack(ModBlocks.trophies[trophy]));
    }

    private static void registerMobDrop(LivingDropsEvent event, boolean enableDrop, ItemStack drop) {
        if (enableDrop) {
            event.getEntityLiving().entityDropItem(drop, 0.0f);
            if (ModConfig.DebugConfig.debugMode) {
                LogHelper.info(event.getEntity().getName() + " dropped:" + drop + " x " + drop.getCount());
            }
        }
    }
}