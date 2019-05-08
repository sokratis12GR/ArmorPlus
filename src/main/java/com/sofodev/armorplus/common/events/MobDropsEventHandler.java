/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.registry.blocks.special.Trophy;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Random;

import static com.sofodev.armorplus.common.config.ModConfig.DebugConfig.debugMode;
import static com.sofodev.armorplus.common.config.ModConfig.EntitiesConfig.mob_drops;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

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
            registerMobDrop(event, mob_drops.ender_dragon_scale.drop, new ItemStack(ITEMS.getValue(setRL("ender_dragon_scale")), mob_drops.ender_dragon_scale.dropAmount));
            registerTrophyDrop(event, Trophy.ENDER_DRAGON);
        }
        if (entity instanceof EntityWither) {
            registerMobDrop(event, mob_drops.wither_bone.bossDrop, new ItemStack(ITEMS.getValue(setRL("wither_bone")), mob_drops.wither_bone.dropAmount));
            registerTrophyDrop(event, Trophy.WITHER_BOSS);
        }
        if (entity instanceof EntityWitherSkeleton) {
            registerMobDrop(event, mob_drops.wither_bone.witherSkeletonDrop, new ItemStack(ITEMS.getValue(setRL("wither_bone")), randomDrop));
        }
        if (entity instanceof EntityGuardian) {
            registerMobDrop(event, mob_drops.guardian_scale.guardianDrop, new ItemStack(ITEMS.getValue(setRL("guardian_scale")), randomDrop));
        }
        if (entity instanceof EntityElderGuardian) {
            registerMobDrop(event, mob_drops.guardian_scale.elderDrop, new ItemStack(ITEMS.getValue(setRL("guardian_scale")), mob_drops.guardian_scale.dropAmount));
            registerTrophyDrop(event, Trophy.ELDER_GUARDIAN);
        }
        if (entity instanceof EntitySkeletalKing) {
            registerTrophyDrop(event, Trophy.SKELETAL_KING);
        }
    }

    private static void registerTrophyDrop(LivingDropsEvent event, Trophy trophy) {
        registerMobDrop(event, mob_drops.trophy.enableTrophyDrops, new ItemStack(ITEMS.getValue(setRL(trophy.getName() + "_trophy"))));
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