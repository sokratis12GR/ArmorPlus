/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.events;

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
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.ModConfig.DebugConfig.debugMode;
import static net.thedragonteam.armorplus.ModConfig.EntitiesConfig.mob_drops;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class MobDropsEventHandler {
    private static Random random = new Random();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        int min = 0, max = 1;
        int randomDrop = random.nextInt(max - min + 1) + min;
        if (event.getEntity() instanceof EntityDragon) {
            registerMobDrop(event, mob_drops.ender_dragon_scale.drop, getItemStack(materials, mob_drops.ender_dragon_scale.dropAmount, 3));
            registerTrophyDrop(event, 3);
        }
        if (event.getEntity() instanceof EntityWither) {
            registerMobDrop(event, mob_drops.wither_bone.bossDrop, getItemStack(materials, mob_drops.wither_bone.dropAmount, 2));
            registerTrophyDrop(event, 2);
        }
        if (event.getEntity() instanceof EntityWitherSkeleton) {
            registerMobDrop(event, mob_drops.wither_bone.witherSkeletonDrop, getItemStack(materials, randomDrop, 2));
        }
        if (event.getEntity() instanceof EntityGuardian) {
            registerMobDrop(event, mob_drops.guardian_scale.guardianDrop, getItemStack(materials, randomDrop, 1));
        }
        if (event.getEntity() instanceof EntityElderGuardian) {
            registerMobDrop(event, mob_drops.guardian_scale.elderDrop, getItemStack(materials, mob_drops.guardian_scale.dropAmount, 1));
            registerTrophyDrop(event, 1);
        }
        if (event.getEntity() instanceof EntitySkeletalKing) {
            registerTrophyDrop(event, 4);
        }
    }

    private static void registerTrophyDrop(LivingDropsEvent event, int trophy) {
        registerMobDrop(event, mob_drops.trophy.enableTrophyDrops, getItemStack(ModBlocks.trophies[trophy]));
    }

    private static void registerMobDrop(LivingDropsEvent event, boolean enableDrop, ItemStack drop) {
        if (enableDrop) {
            event.getEntityLiving().entityDropItem(drop, 0.0f);
            if (debugMode) {
                LogHelper.info(event.getEntity().getName() + " dropped:" + drop + " x " + drop.getCount());
            }
        }
    }
}