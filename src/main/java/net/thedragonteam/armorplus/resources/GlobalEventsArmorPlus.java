/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.resources;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thedragonteam.armorplus.registry.ModAchievements;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.potion.Potion.getPotionFromResourceLocation;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.armorplus.util.PotionUtils.removePotion;

public class GlobalEventsArmorPlus {

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
        if (i == Item.getItemFromBlock(ModBlocks.benches[0])) {
            event.player.addStat(ModAchievements.welcomeToArmorPlus, 1);
            event.player.inventory.addItemStackToInventory(new ItemStack(ModItems.armorPlusBook, 1));
        }
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer entity;
        entity = event.player;
        ItemStack head;
        head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest;
        chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs;
        legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet;
        feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        if (head != null && chest != null && legs != null && feet != null) {
            if (head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots && entity.isInWater() && enableFullLavaArmorEffect) {
                entity.extinguish();
                if (entity.isInLava())
                    entity.setAbsorptionAmount(4.0F);
                if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(entity, MobEffects.SLOWNESS, 120, 1, BAD);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.drown, 1F);
                }
            } else if (head.getItem() == ModItems.theUltimateHelmet && chest.getItem() == ModItems.theUltimateChestplate && legs.getItem() == ModItems.theUltimateLeggings && feet.getItem() == ModItems.theUltimateBoots) {
                addPotion(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[0]), 120, ultimateArmorEffectLevels[0], GOOD);
                addPotion(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[1]), 120, ultimateArmorEffectLevels[1], GOOD);
                addPotion(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[2]), 120, ultimateArmorEffectLevels[2], GOOD);
                removePotion(entity, getPotionFromResourceLocation(theUltimateArmorRemovePotionEffect));
            } else if (enableFullSuperStarArmorEffect && head.getItem() == ModItems.superStarHelmet && chest.getItem() == ModItems.superStarChestplate && legs.getItem() == ModItems.superStarLeggings && feet.getItem() == ModItems.superStarBoots) {
                if (entity.getActivePotionEffect(getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                    addPotion(entity, getPotionFromResourceLocation(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
                removePotion(entity, getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
            } else if (enableFullCoalArmorEffect && head.getItem() == ModItems.coalHelmet && chest.getItem() == ModItems.coalChestplate && legs.getItem() == ModItems.coalLeggings && feet.getItem() == ModItems.coalBoots) {
                addPotion(entity, getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
            } else if (enablePigIronArmorEffect && head.getItem() == ModItems.pigIronHelmet && chest.getItem() == ModItems.pigIronChestplate && legs.getItem() == ModItems.pigIronLeggings && feet.getItem() == ModItems.pigIronBoots && entity.getFoodStats().needFood()) {
                addPotion(entity, getPotionFromResourceLocation(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel, GOOD);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (enableFullLapisArmorEffect && head.getItem() == ModItems.lapisHelmet && chest.getItem() == ModItems.lapisChestplate && legs.getItem() == ModItems.lapisLeggings && feet.getItem() == ModItems.lapisBoots) {
                addPotion(entity, getPotionFromResourceLocation(lapisArmorAddPotionEffect), 120, lapisArmorEffectLevel, GOOD);
            } else if (enableFullLavaArmorEffect && head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots) {
                addPotion(entity, getPotionFromResourceLocation(lavaArmorAddPotionEffect), 120, lavaArmorEffectLevel, GOOD);
            } else if (enableFullEmeraldArmorEffect && head.getItem() == ModItems.emeraldHelmet && chest.getItem() == ModItems.emeraldChestplate && legs.getItem() == ModItems.emeraldLeggings && feet.getItem() == ModItems.emeraldBoots) {
                addPotion(entity, getPotionFromResourceLocation(emeraldArmorAddPotionEffect), 120, emeraldArmorEffectLevel, GOOD);
            } else if (enableFullObsidianArmorEffect && head.getItem() == ModItems.obsidianHelmet && chest.getItem() == ModItems.obsidianChestplate && legs.getItem() == ModItems.obsidianLeggings && feet.getItem() == ModItems.obsidianBoots) {
                addPotion(entity, getPotionFromResourceLocation(obsidianArmorAddPotionEffect), 120, obsidianArmorEffectLevel, GOOD);
            } else if (enableFullRedstoneArmorEffect && head.getItem() == ModItems.redstoneHelmet && chest.getItem() == ModItems.redstoneChestplate && legs.getItem() == ModItems.redstoneLeggings && feet.getItem() == ModItems.redstoneBoots) {
                addPotion(entity, getPotionFromResourceLocation(redstoneArmorAddPotionEffect), 240, redstoneArmorEffectLevel, GOOD);
                if (entity.world.isRemote) {
                    spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                }
            } else if (head.getItem() == ModItems.guardianHelmet && chest.getItem() == ModItems.guardianChestplate && legs.getItem() == ModItems.guardianLeggings && feet.getItem() == ModItems.guardianBoots) {
                addPotion(entity, getPotionFromResourceLocation(guardianArmorAddPotionEffect), 120, guardianArmorEffectLevel, GOOD);
            } else if (head.getItem() == ModItems.chickenHelmet && chest.getItem() == ModItems.chickenChestplate && legs.getItem() == ModItems.chickenLeggings && feet.getItem() == ModItems.chickenBoots) {
                addPotion(entity, getPotionFromResourceLocation(chickenArmorAddPotionEffect), 120, chickenArmorEffectLevel, GOOD);
            } else if (head.getItem() == ModItems.slimeHelmet && chest.getItem() == ModItems.slimeChestplate && legs.getItem() == ModItems.slimeLeggings && feet.getItem() == ModItems.slimeBoots) {
                addPotion(entity, getPotionFromResourceLocation(slimeArmorAddPotionEffect), 120, slimeArmorEffectLevel, GOOD);
            } else if (enableManyullynArmorEffect && head.getItem() == ModItems.manyullynHelmet && chest.getItem() == ModItems.manyullynChestplate && legs.getItem() == ModItems.manyullynLeggings && feet.getItem() == ModItems.manyullynBoots) {
                addPotion(entity, getPotionFromResourceLocation(manyullynArmorAddPotionEffect), 120, manyullynArmorEffectLevel, GOOD);
            } else if (enableKnightSlimeArmorEffect && head.getItem() == ModItems.knightSlimeHelmet && chest.getItem() == ModItems.knightSlimeChestplate && legs.getItem() == ModItems.knightSlimeLeggings && feet.getItem() == ModItems.knightSlimeBoots) {
                addPotion(entity, getPotionFromResourceLocation(knightSlimeArmorAddPotionEffect), 120, knightSlimeArmorEffectLevel, GOOD);
            } else if (enableCobaltArmorEffect && head.getItem() == ModItems.cobaltHelmet && chest.getItem() == ModItems.cobaltChestplate && legs.getItem() == ModItems.cobaltLeggings && feet.getItem() == ModItems.cobaltBoots) {
                addPotion(entity, getPotionFromResourceLocation(cobaltArmorAddPotionEffect), 120, cobaltArmorEffectLevel, GOOD);
            } else if (enableArditeArmorEffect && head.getItem() == ModItems.arditeHelmet && chest.getItem() == ModItems.arditeChestplate && legs.getItem() == ModItems.arditeLeggings && feet.getItem() == ModItems.arditeBoots) {
                addPotion(entity, getPotionFromResourceLocation(arditeArmorAddPotionEffect), 120, arditeArmorEffectLevel, GOOD);
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }
}