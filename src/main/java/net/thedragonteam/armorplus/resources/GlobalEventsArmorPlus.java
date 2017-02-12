/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.resources;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thedragonteam.armorplus.registry.ModAchievements;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.EnumParticleTypes.REDSTONE;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.thedragonlib.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.thedragonlib.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.thedragonlib.util.PotionUtils.*;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class GlobalEventsArmorPlus {

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
        if (i == Item.getItemFromBlock(ModBlocks.benches[0])) event.player.addStat(ModAchievements.welcomeToArmorPlus);
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer entity;
        entity = event.player;
        ItemStack head;
        head = entity.getItemStackFromSlot(HEAD);
        ItemStack chest;
        chest = entity.getItemStackFromSlot(CHEST);
        ItemStack legs;
        legs = entity.getItemStackFromSlot(LEGS);
        ItemStack feet;
        feet = entity.getItemStackFromSlot(FEET);

        if (head.getCount() > 0 && chest.getCount() > 0 && legs.getCount() > 0 && feet.getCount() > 0) {
            if (head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots && entity.isInWater() && enableFullLavaArmorEffect) {
                entity.extinguish();
                if (entity.isInLava())
                    entity.setAbsorptionAmount(4.0F);
                if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(entity, MobEffects.SLOWNESS, 120, 1, BAD);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.DROWN, 1F);
                }
            } else if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots) {
                addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[0]), 120, ultimateArmorEffectLevels[0], GOOD);
                addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[1]), 120, ultimateArmorEffectLevels[1], GOOD);
                addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[2]), 120, ultimateArmorEffectLevels[2], GOOD);
                removePotion(entity, getPotion(theUltimateArmorRemovePotionEffect));
            } else if (enableFullSuperStarArmorEffect && head.getItem() == superStarHelmet && chest.getItem() == superStarChestplate && legs.getItem() == superStarLeggings && feet.getItem() == superStarBoots) {
                if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                    addPotion(entity, getPotion(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
                removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
            } else if (enableFullCoalArmorEffect && head.getItem() == coalHelmet && chest.getItem() == coalChestplate && legs.getItem() == coalLeggings && feet.getItem() == coalBoots) {
                addPotion(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
            } else if (enablePigIronArmorEffect && head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots && entity.getFoodStats().needFood()) {
                addPotion(entity, getPotion(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel, GOOD);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (enableFullLapisArmorEffect && head.getItem() == lapisHelmet && chest.getItem() == lapisChestplate && legs.getItem() == lapisLeggings && feet.getItem() == lapisBoots) {
                addPotion(entity, getPotion(lapisArmorAddPotionEffect), 120, lapisArmorEffectLevel, GOOD);
            } else if (enableFullLavaArmorEffect && head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots) {
                addPotion(entity, getPotion(lavaArmorAddPotionEffect), 120, lavaArmorEffectLevel, GOOD);
            } else if (enableFullEmeraldArmorEffect && head.getItem() == emeraldHelmet && chest.getItem() == emeraldChestplate && legs.getItem() == emeraldLeggings && feet.getItem() == emeraldBoots) {
                addPotion(entity, getPotion(emeraldArmorAddPotionEffect), 120, emeraldArmorEffectLevel, GOOD);
            } else if (enableFullObsidianArmorEffect && head.getItem() == obsidianHelmet && chest.getItem() == obsidianChestplate && legs.getItem() == obsidianLeggings && feet.getItem() == obsidianBoots) {
                addPotion(entity, getPotion(obsidianArmorAddPotionEffect), 120, obsidianArmorEffectLevel, GOOD);
            } else if (enableFullRedstoneArmorEffect && head.getItem() == redstoneHelmet && chest.getItem() == redstoneChestplate && legs.getItem() == redstoneLeggings && feet.getItem() == redstoneBoots) {
                addPotion(entity, getPotion(redstoneArmorAddPotionEffect), 240, redstoneArmorEffectLevel, GOOD);
                if (entity.world.isRemote) {
                    spawnParticle(entity, REDSTONE, entity.posX, entity.posY, entity.posZ);
                }
            } else if (head.getItem() == guardianHelmet && chest.getItem() == guardianChestplate && legs.getItem() == guardianLeggings && feet.getItem() == guardianBoots) {
                addPotion(entity, getPotion(guardianArmorAddPotionEffect), 120, guardianArmorEffectLevel, GOOD);
            } else if (head.getItem() == chickenHelmet && chest.getItem() == chickenChestplate && legs.getItem() == chickenLeggings && feet.getItem() == chickenBoots) {
                addPotion(entity, getPotion(chickenArmorAddPotionEffect), 120, chickenArmorEffectLevel, GOOD);
            } else if (head.getItem() == slimeHelmet && chest.getItem() == slimeChestplate && legs.getItem() == slimeLeggings && feet.getItem() == slimeBoots) {
                addPotion(entity, getPotion(slimeArmorAddPotionEffect), 120, slimeArmorEffectLevel, GOOD);
            } else if (enableManyullynArmorEffect && head.getItem() == manyullynHelmet && chest.getItem() == manyullynChestplate && legs.getItem() == manyullynLeggings && feet.getItem() == manyullynBoots) {
                addPotion(entity, getPotion(manyullynArmorAddPotionEffect), 120, manyullynArmorEffectLevel, GOOD);
            } else if (enableKnightSlimeArmorEffect && head.getItem() == knightSlimeHelmet && chest.getItem() == knightSlimeChestplate && legs.getItem() == knightSlimeLeggings && feet.getItem() == knightSlimeBoots) {
                addPotion(entity, getPotion(knightSlimeArmorAddPotionEffect), 120, knightSlimeArmorEffectLevel, GOOD);
            } else if (enableCobaltArmorEffect && head.getItem() == cobaltHelmet && chest.getItem() == cobaltChestplate && legs.getItem() == cobaltLeggings && feet.getItem() == cobaltBoots) {
                addPotion(entity, getPotion(cobaltArmorAddPotionEffect), 120, cobaltArmorEffectLevel, GOOD);
            } else if (enableArditeArmorEffect && head.getItem() == arditeHelmet && chest.getItem() == arditeChestplate && legs.getItem() == arditeLeggings && feet.getItem() == arditeBoots) {
                addPotion(entity, getPotion(arditeArmorAddPotionEffect), 120, arditeArmorEffectLevel, GOOD);
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }
}