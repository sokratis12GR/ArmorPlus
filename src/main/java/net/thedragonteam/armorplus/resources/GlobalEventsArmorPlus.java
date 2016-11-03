/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.resources;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.thedragonteam.armorplus.ARPConfig.enableFullLavaArmorEffect;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;

public class GlobalEventsArmorPlus {

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();

        if (i == Item.getItemFromBlock(ModBlocks.arpWorkbench)) {
            event.player.addStat(ARPAchievements.welcomeToArmorPlus, 1);
            event.player.inventory.addItemStackToInventory(new ItemStack(ModItems.armorPlusBook, 1));
        }
    }

    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

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
            if (head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots && entity.isInWater() && enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                entity.extinguish();
                entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
                entity.attackEntityFrom(DamageSource.drown, 1F);
            } else if (head.getItem() == ModItems.theUltimateHelmet && chest.getItem() == ModItems.theUltimateChestplate && legs.getItem() == ModItems.theUltimateLeggings && feet.getItem() == ModItems.theUltimateBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.ultimateArmorEffectlevel, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 120, 0, true, true));
                entity.removePotionEffect(MobEffects.WITHER);
            } else if (ARPConfig.enableFullSuperStarArmorEffect && head.getItem() == ModItems.superStarHelmet && chest.getItem() == ModItems.superStarChestplate && legs.getItem() == ModItems.superStarLeggings && feet.getItem() == ModItems.superStarBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.superstarArmorEffectlevel, true, true));
                entity.removePotionEffect(MobEffects.WITHER);
            } else if (ARPConfig.enableFullCoalArmorEffect && head.getItem() == ModItems.coalHelmet && chest.getItem() == ModItems.coalChestplate && legs.getItem() == ModItems.coalLeggings && feet.getItem() == ModItems.coalBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
            } else if (ARPConfig.enablePigIronArmorEffects && head.getItem() == ModItems.pigIronHelmet && chest.getItem() == ModItems.pigIronChestplate && legs.getItem() == ModItems.pigIronLeggings && feet.getItem() == ModItems.pigIronBoots && entity.getFoodStats().needFood()) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0, true, true));
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (ARPConfig.enableFullLapisArmorEffect && head.getItem() == ModItems.lapisHelmet && chest.getItem() == ModItems.lapisChestplate && legs.getItem() == ModItems.lapisLeggings && feet.getItem() == ModItems.lapisBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
            } else if (ARPConfig.enableFullLavaArmorEffect && head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
            } else if (ARPConfig.enableFullEmeraldArmorEffect && head.getItem() == ModItems.emeraldHelmet && chest.getItem() == ModItems.emeraldChestplate && legs.getItem() == ModItems.emeraldLeggings && feet.getItem() == ModItems.emeraldBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ARPConfig.emeraldArmorEffectlevel, true, true));
            } else if (ARPConfig.enableFullObsidianArmorEffect && head.getItem() == ModItems.obsidianHelmet && chest.getItem() == ModItems.obsidianChestplate && legs.getItem() == ModItems.obsidianLeggings && feet.getItem() == ModItems.obsidianBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.obsidianArmorEffectlevel, true, true));
            } else if (ARPConfig.enableFullRedstoneArmorEffect && head.getItem() == ModItems.redstoneHelmet && chest.getItem() == ModItems.redstoneChestplate && legs.getItem() == ModItems.redstoneLeggings && feet.getItem() == ModItems.redstoneBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 240, ARPConfig.redstoneArmorEffectlevel, true, true));
            } else if (head.getItem() == ModItems.guardianHelmet && chest.getItem() == ModItems.guardianChestplate && legs.getItem() == ModItems.guardianLeggings && feet.getItem() == ModItems.guardianBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
            } else if (head.getItem() == ModItems.chickenHelmet && chest.getItem() == ModItems.chickenChestplate && legs.getItem() == ModItems.chickenLeggings && feet.getItem() == ModItems.chickenBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, 4, true, true));
            } else if (head.getItem() == ModItems.slimeHelmet && chest.getItem() == ModItems.slimeChestplate && legs.getItem() == ModItems.slimeLeggings && feet.getItem() == ModItems.slimeBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 2, true, true));
            } else if (ARPConfig.enableManyullynArmorEffects && head.getItem() == ModItems.manyullynHelmet && chest.getItem() == ModItems.manyullynChestplate && legs.getItem() == ModItems.manyullynLeggings && feet.getItem() == ModItems.manyullynBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120, 1, true, true));
            } else if (ARPConfig.enableKnightSlimeArmorEffects && head.getItem() == ModItems.knightSlimeHelmet && chest.getItem() == ModItems.knightSlimeChestplate && legs.getItem() == ModItems.knightSlimeLeggings && feet.getItem() == ModItems.knightSlimeBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 1, true, true));
            } else if (ARPConfig.enableCobaltArmorEffects && head.getItem() == ModItems.cobaltHelmet && chest.getItem() == ModItems.cobaltChestplate && legs.getItem() == ModItems.cobaltLeggings && feet.getItem() == ModItems.cobaltBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 2, true, true));
            } else if (ARPConfig.enableArditeArmorEffects && head.getItem() == ModItems.arditeHelmet && chest.getItem() == ModItems.arditeChestplate && legs.getItem() == ModItems.arditeLeggings && feet.getItem() == ModItems.arditeBoots) {
                entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
            }
        }
    }

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }
}