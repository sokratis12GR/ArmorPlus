/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.events;

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
        int min = 0, max = 1;
        int randomDrop = random.nextInt(max - min + 1) + min;
        if (event.getEntity() instanceof EntityDragon) {
            registerMobDrop(event, event.getEntity(), enderDragonScaleDrop, getItemStack(materials, enderDragonScaleDropAmount, 3));
        } else if (event.getEntity() instanceof EntityWither) {
            registerMobDrop(event, event.getEntity(), witherBoneWitherBossDrop, getItemStack(materials, witherBoneWitherBossDropAmount, 2));
        } else if (event.getEntity() instanceof EntityWitherSkeleton) {
            registerMobDrop(event, event.getEntity(), witherBoneWitherSkeletonDrop, getItemStack(materials, randomDrop, 2));
        } else if (event.getEntity() instanceof EntityGuardian) {
            registerMobDrop(event, event.getEntity(), guardianScaleGuardianDrop, getItemStack(materials, randomDrop, 1));
        } else if (event.getEntity() instanceof EntityElderGuardian) {
            registerMobDrop(event, event.getEntity(), guardianScaleElderGuardianDrop, getItemStack(materials, guardianScaleElderDropAmount, 1));
        }
    }

    private static void registerMobDrop(LivingDropsEvent event, Entity entity, boolean enableDrop, ItemStack drop) {
        if (enableDrop) {
            event.getEntityLiving().entityDropItem(drop, 0.0f);
        }
        if (debugMode) {
            LogHelper.info(entity.getName() + " dropped:" + drop + " x " + drop.getCount());
        }
    }
}