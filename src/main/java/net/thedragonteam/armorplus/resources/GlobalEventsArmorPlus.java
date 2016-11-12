/*
 * Copyright (c) TheDragonTeam 2016.
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
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.armorplus.util.ParticlesHelper;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.potion.Potion.getPotionFromResourceLocation;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.util.ArmorUtils.addArmorEffect;
import static net.thedragonteam.armorplus.util.ArmorUtils.removeArmorEffect;

public class GlobalEventsArmorPlus {

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
        if (i == Item.getItemFromBlock(ModBlocks.arpWorkbench)) {
            event.player.addStat(ARPAchievements.welcomeToArmorPlus, 1);
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
                    addArmorEffect(entity, MobEffects.SLOWNESS, 120, 1);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.drown, 1F);
                }
            } else if (head.getItem() == ModItems.theUltimateHelmet && chest.getItem() == ModItems.theUltimateChestplate && legs.getItem() == ModItems.theUltimateLeggings && feet.getItem() == ModItems.theUltimateBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[0]), 120, ultimateArmorEffectLevels[0]);
                addArmorEffect(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[1]), 120, ultimateArmorEffectLevels[1]);
                addArmorEffect(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[2]), 120, ultimateArmorEffectLevels[2]);
                removeArmorEffect(entity, getPotionFromResourceLocation(theUltimateArmorRemovePotionEffect));
            } else if (enableFullSuperStarArmorEffect && head.getItem() == ModItems.superStarHelmet && chest.getItem() == ModItems.superStarChestplate && legs.getItem() == ModItems.superStarLeggings && feet.getItem() == ModItems.superStarBoots) {
                if (entity.getActivePotionEffect(getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                    addArmorEffect(entity, getPotionFromResourceLocation(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel);
                removeArmorEffect(entity, getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
            } else if (enableFullCoalArmorEffect && head.getItem() == ModItems.coalHelmet && chest.getItem() == ModItems.coalChestplate && legs.getItem() == ModItems.coalLeggings && feet.getItem() == ModItems.coalBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel);
            } else if (enablePigIronArmorEffect && head.getItem() == ModItems.pigIronHelmet && chest.getItem() == ModItems.pigIronChestplate && legs.getItem() == ModItems.pigIronLeggings && feet.getItem() == ModItems.pigIronBoots && entity.getFoodStats().needFood()) {
                addArmorEffect(entity, getPotionFromResourceLocation(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (enableFullLapisArmorEffect && head.getItem() == ModItems.lapisHelmet && chest.getItem() == ModItems.lapisChestplate && legs.getItem() == ModItems.lapisLeggings && feet.getItem() == ModItems.lapisBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(lapisArmorAddPotionEffect), 120, lapisArmorEffectLevel);
            } else if (enableFullLavaArmorEffect && head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(lavaArmorAddPotionEffect), 120, lavaArmorEffectLevel);
            } else if (enableFullEmeraldArmorEffect && head.getItem() == ModItems.emeraldHelmet && chest.getItem() == ModItems.emeraldChestplate && legs.getItem() == ModItems.emeraldLeggings && feet.getItem() == ModItems.emeraldBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(emeraldArmorAddPotionEffect), 120, emeraldArmorEffectLevel);
            } else if (enableFullObsidianArmorEffect && head.getItem() == ModItems.obsidianHelmet && chest.getItem() == ModItems.obsidianChestplate && legs.getItem() == ModItems.obsidianLeggings && feet.getItem() == ModItems.obsidianBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(obsidianArmorAddPotionEffect), 120, obsidianArmorEffectLevel);
            } else if (enableFullRedstoneArmorEffect && head.getItem() == ModItems.redstoneHelmet && chest.getItem() == ModItems.redstoneChestplate && legs.getItem() == ModItems.redstoneLeggings && feet.getItem() == ModItems.redstoneBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(redstoneArmorAddPotionEffect), 240, redstoneArmorEffectLevel);
                if (entity.worldObj.isRemote) {
                    ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                }
            } else if (head.getItem() == ModItems.guardianHelmet && chest.getItem() == ModItems.guardianChestplate && legs.getItem() == ModItems.guardianLeggings && feet.getItem() == ModItems.guardianBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(guardianArmorAddPotionEffect), 120, guardianArmorEffectLevel);
            } else if (head.getItem() == ModItems.chickenHelmet && chest.getItem() == ModItems.chickenChestplate && legs.getItem() == ModItems.chickenLeggings && feet.getItem() == ModItems.chickenBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(chickenArmorAddPotionEffect), 120, chickenArmorEffectLevel);
            } else if (head.getItem() == ModItems.slimeHelmet && chest.getItem() == ModItems.slimeChestplate && legs.getItem() == ModItems.slimeLeggings && feet.getItem() == ModItems.slimeBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(slimeArmorAddPotionEffect), 120, slimeArmorEffectLevel);
            } else if (enableManyullynArmorEffect && head.getItem() == ModItems.manyullynHelmet && chest.getItem() == ModItems.manyullynChestplate && legs.getItem() == ModItems.manyullynLeggings && feet.getItem() == ModItems.manyullynBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(manyullynArmorAddPotionEffect), 120, manyullynArmorEffectLevel);
            } else if (enableKnightSlimeArmorEffect && head.getItem() == ModItems.knightSlimeHelmet && chest.getItem() == ModItems.knightSlimeChestplate && legs.getItem() == ModItems.knightSlimeLeggings && feet.getItem() == ModItems.knightSlimeBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(knightSlimeArmorAddPotionEffect), 120, knightSlimeArmorEffectLevel);
            } else if (enableCobaltArmorEffect && head.getItem() == ModItems.cobaltHelmet && chest.getItem() == ModItems.cobaltChestplate && legs.getItem() == ModItems.cobaltLeggings && feet.getItem() == ModItems.cobaltBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(cobaltArmorAddPotionEffect), 120, cobaltArmorEffectLevel);
            } else if (enableArditeArmorEffect && head.getItem() == ModItems.arditeHelmet && chest.getItem() == ModItems.arditeChestplate && legs.getItem() == ModItems.arditeLeggings && feet.getItem() == ModItems.arditeBoots) {
                addArmorEffect(entity, getPotionFromResourceLocation(arditeArmorAddPotionEffect), 120, arditeArmorEffectLevel);
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }
}