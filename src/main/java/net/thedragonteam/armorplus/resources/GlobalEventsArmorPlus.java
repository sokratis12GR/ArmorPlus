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

        if (head != null && head.getItem() == ModItems.lavaHelmet && chest != null && chest.getItem() == ModItems.lavaChestplate && legs != null && legs.getItem() == ModItems.lavaLeggings && feet != null && feet.getItem() == ModItems.lavaBoots && entity.isInWater() && enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
            entity.extinguish();
            entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
            head.damageItem(1, entity);
            chest.damageItem(1, entity);
            legs.damageItem(1, entity);
            feet.damageItem(1, entity);
            entity.attackEntityFrom(DamageSource.drown, 1F);
        } else if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.ultimateArmorEffectlevel, true, true));
            entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 120, 0, true, true));
            entity.removePotionEffect(MobEffects.WITHER);
        } else if (ARPConfig.enableFullSuperStarArmorEffect && head != null && head.getItem() == ModItems.superStarHelmet && chest != null && chest.getItem() == ModItems.superStarChestplate && legs != null && legs.getItem() == ModItems.superStarLeggings && feet != null && feet.getItem() == ModItems.superStarBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.superstarArmorEffectlevel, true, true));
            entity.removePotionEffect(MobEffects.WITHER);
        } else if (ARPConfig.enableFullCoalArmorEffect && head != null && head.getItem() == ModItems.coalHelmet && chest != null && chest.getItem() == ModItems.coalChestplate && legs != null && legs.getItem() == ModItems.coalLeggings && feet != null && feet.getItem() == ModItems.coalBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
        } else if (ARPConfig.enablePigIronArmorEffects && head != null && head.getItem() == ModItems.pigIronHelmet && chest != null && chest.getItem() == ModItems.pigIronChestplate && legs != null && legs.getItem() == ModItems.pigIronLeggings && feet != null && feet.getItem() == ModItems.pigIronBoots && entity.getFoodStats().needFood()) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0, true, true));
            head.damageItem(1, entity);
            chest.damageItem(1, entity);
            legs.damageItem(1, entity);
            feet.damageItem(1, entity);
        } else if (ARPConfig.enableFullLapisArmorEffect && head != null && head.getItem() == ModItems.lapisHelmet && chest != null && chest.getItem() == ModItems.lapisChestplate && legs != null && legs.getItem() == ModItems.lapisLeggings && feet != null && feet.getItem() == ModItems.lapisBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        } else if (ARPConfig.enableFullLavaArmorEffect && head != null && head.getItem() == ModItems.lavaHelmet && chest != null && chest.getItem() == ModItems.lavaChestplate && legs != null && legs.getItem() == ModItems.lavaLeggings && feet != null && feet.getItem() == ModItems.lavaBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
        } else if (ARPConfig.enableFullEmeraldArmorEffect && head != null && head.getItem() == ModItems.emeraldHelmet && chest != null && chest.getItem() == ModItems.emeraldChestplate && legs != null && legs.getItem() == ModItems.emeraldLeggings && feet != null && feet.getItem() == ModItems.emeraldBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ARPConfig.emeraldArmorEffectlevel, true, true));
        } else if (ARPConfig.enableFullObsidianArmorEffect && head != null && head.getItem() == ModItems.obsidianHelmet && chest != null && chest.getItem() == ModItems.obsidianChestplate && legs != null && legs.getItem() == ModItems.obsidianLeggings && feet != null && feet.getItem() == ModItems.obsidianBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.obsidianArmorEffectlevel, true, true));
        } else if (ARPConfig.enableFullRedstoneArmorEffect && head != null && head.getItem() == ModItems.redstoneHelmet && chest != null && chest.getItem() == ModItems.redstoneChestplate && legs != null && legs.getItem() == ModItems.redstoneLeggings && feet != null && feet.getItem() == ModItems.redstoneBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 240, ARPConfig.redstoneArmorEffectlevel, true, true));
        } else if (head != null && head.getItem() == ModItems.guardianHelmet && chest != null && chest.getItem() == ModItems.guardianChestplate && legs != null && legs.getItem() == ModItems.guardianLeggings && feet != null && feet.getItem() == ModItems.guardianBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        } else if (head != null && head.getItem() == ModItems.chickenHelmet && chest != null && chest.getItem() == ModItems.chickenChestplate && legs != null && legs.getItem() == ModItems.chickenLeggings && feet != null && feet.getItem() == ModItems.chickenBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, 4, true, true));
        } else if (head != null && head.getItem() == ModItems.slimeHelmet && chest != null && chest.getItem() == ModItems.slimeChestplate && legs != null && legs.getItem() == ModItems.slimeLeggings && feet != null && feet.getItem() == ModItems.slimeBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 2, true, true));
        } else if (ARPConfig.enableManyullynArmorEffects && head != null && head.getItem() == ModItems.manyullynHelmet && chest != null && chest.getItem() == ModItems.manyullynChestplate && legs != null && legs.getItem() == ModItems.manyullynLeggings && feet != null && feet.getItem() == ModItems.manyullynBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120, 1, true, true));
        } else if (ARPConfig.enableKnightSlimeArmorEffects && head != null && head.getItem() == ModItems.knightSlimeHelmet && chest != null && chest.getItem() == ModItems.knightSlimeChestplate && legs != null && legs.getItem() == ModItems.knightSlimeLeggings && feet != null && feet.getItem() == ModItems.knightSlimeBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 1, true, true));
        } else if (ARPConfig.enableCobaltArmorEffects && head != null && head.getItem() == ModItems.cobaltHelmet && chest != null && chest.getItem() == ModItems.cobaltChestplate && legs != null && legs.getItem() == ModItems.cobaltLeggings && feet != null && feet.getItem() == ModItems.cobaltBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 2, true, true));
        } else if (ARPConfig.enableArditeArmorEffects && head != null && head.getItem() == ModItems.arditeHelmet && chest != null && chest.getItem() == ModItems.arditeChestplate && legs != null && legs.getItem() == ModItems.arditeLeggings && feet != null && feet.getItem() == ModItems.arditeBoots) {
            entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
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