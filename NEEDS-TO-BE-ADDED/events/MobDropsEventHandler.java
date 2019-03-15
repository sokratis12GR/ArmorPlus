/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * @author Sokratis Fotkatzikis
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class MobDropsEventHandler {
    //  private static Random random = new Random();
//
    //  @SubscribeEvent(priority = EventPriority.HIGHEST)
    //  public static void onLivingDrops(LivingDropsEvent event) {
    //      int min = 0;
    //      int max = 1;
    //      int randomDrop = random.nextInt(max - min + 1) + min;
    //      Entity entity = event.getEntity();
    //      if (entity instanceof EntityDragon) {
    //          registerMobDrop(event, mob_drops.ender_dragon_scale.drop, new ItemStack(materials, mob_drops.ender_dragon_scale.dropAmount, 3));
    //          registerTrophyDrop(event, 3);
    //      }
    //      if (entity instanceof EntityWither) {
    //          registerMobDrop(event, mob_drops.wither_bone.bossDrop, new ItemStack(materials, mob_drops.wither_bone.dropAmount, 2));
    //          registerTrophyDrop(event, 2);
    //      }
    //      if (entity instanceof EntityWitherSkeleton) {
    //          registerMobDrop(event, mob_drops.wither_bone.witherSkeletonDrop, new ItemStack(materials, randomDrop, 2));
    //      }
    //      if (entity instanceof EntityGuardian) {
    //          registerMobDrop(event, mob_drops.guardian_scale.guardianDrop, new ItemStack(materials, randomDrop, 1));
    //      }
    //      if (entity instanceof EntityElderGuardian) {
    //          registerMobDrop(event, mob_drops.guardian_scale.elderDrop, new ItemStack(materials, mob_drops.guardian_scale.dropAmount, 1));
    //          registerTrophyDrop(event, 1);
    //      }
    //      if (entity instanceof EntitySkeletalKing) {
    //          registerTrophyDrop(event, 4);
    //      }
    //  }
//
    //  private static void registerTrophyDrop(LivingDropsEvent event, int trophy) {
    //      registerMobDrop(event, mob_drops.trophy.enableTrophyDrops, new ItemStack(ModBlocks.trophies[trophy]));
    //  }
//
    //  private static void registerMobDrop(LivingDropsEvent event, boolean enableDrop, ItemStack drop) {
    //      if (enableDrop) {
    //          event.getEntityLiving().entityDropItem(drop, 0.0f);
    //          if (debugMode) {
    //              LogHelper.info(event.getEntity().getName() + " dropped:" + drop + " x " + drop.getCount());
    //          }
    //      }
    //  }
}