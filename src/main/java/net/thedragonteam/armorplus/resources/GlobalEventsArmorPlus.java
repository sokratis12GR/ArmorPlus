/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.resources;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
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
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.core.util.TextHelper;

import static net.thedragonteam.armorplus.ARPConfig.enableFullLavaArmorEffect;
import static net.thedragonteam.armorplus.ArmorPlus.nightVisionEnabled;

public class GlobalEventsArmorPlus {

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
        ItemStack itemStack = event.crafting;
        if (i == ModItems.lapisSword)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (i == ModItems.guardianSword)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
        if (i == ModItems.lapisBattleAxe)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (i == ModItems.guardianBattleAxe)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
        if (i == ModItems.lapisBow)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (i == ModItems.guardianBow)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("power"), 1);

        if (i == Item.getItemFromBlock(ModBlocks.armorForge)) {
            event.player.addStat(ARPAchievements.welcomeToArmorPlus, 1);
            event.player.inventory.addItemStackToInventory(new ItemStack(ModItems.armorPlusBook, 1));
        }
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer entity = event.player;
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots) {
            if (entity instanceof EntityLivingBase) {
                if (nightVisionEnabled) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                } else if (!nightVisionEnabled) {
                    entity.removePotionEffect(MobEffects.NIGHT_VISION);
                }
            }
        }

        if (head != null && head.getItem() == ModItems.lavaHelmet && chest != null && chest.getItem() == ModItems.lavaChestplate && legs != null && legs.getItem() == ModItems.lavaLeggings && feet != null && feet.getItem() == ModItems.lavaBoots) {
            entity.extinguish();
            if (entity.isInLava()) {
                entity.setAbsorptionAmount(4.0F);
            } else
                entity.setAbsorptionAmount(0.0F);
            if (entity.isInWater() && enableFullLavaArmorEffect) {
                if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.drown, 1F);
                }
            }
        }

        /*The Ultimate Armor Armor*/
        if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots)

        {
            if (entity instanceof EntityLivingBase) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.ultimateArmorEffectlevel, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 120, 0, true, true));
                entity.removePotionEffect(MobEffects.WITHER);
            }
        }

        /*Full Super Star Armor*/
        if (ARPConfig.enableFullSuperStarArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.superStarHelmet && chest != null && chest.getItem() == ModItems.superStarChestplate && legs != null && legs.getItem() == ModItems.superStarLeggings && feet != null && feet.getItem() == ModItems.superStarBoots) {
                if (entity instanceof EntityLivingBase) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.superstarArmorEffectlevel, true, true));
                    entity.removePotionEffect(MobEffects.WITHER);
                }
            }
        }
        /*Full Coal Armor*/
        if (ARPConfig.enableFullCoalArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.coalHelmet && chest != null && chest.getItem() == ModItems.coalChestplate && legs != null && legs.getItem() == ModItems.coalLeggings && feet != null && feet.getItem() == ModItems.coalBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
            }
        }
        /*Full Lapis Armor*/
        if (ARPConfig.enableFullLapisArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.lapisHelmet && chest != null && chest.getItem() == ModItems.lapisChestplate && legs != null && legs.getItem() == ModItems.lapisLeggings && feet != null && feet.getItem() == ModItems.lapisBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
            }
        }
        /*Full Lava Armor*/
        if (ARPConfig.enableFullLavaArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.lavaHelmet && chest != null && chest.getItem() == ModItems.lavaChestplate && legs != null && legs.getItem() == ModItems.lavaLeggings && feet != null && feet.getItem() == ModItems.lavaBoots) {
                if (entity instanceof EntityLivingBase) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                }
            }
        }
        /*Full Emerald Armor*/
        if (ARPConfig.enableFullEmeraldArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.emeraldHelmet && chest != null && chest.getItem() == ModItems.emeraldChestplate && legs != null && legs.getItem() == ModItems.emeraldLeggings && feet != null && feet.getItem() == ModItems.emeraldBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ARPConfig.emeraldArmorEffectlevel, true, true));
            }
        }
        /*Full Obsidian Armor*/
        if (ARPConfig.enableFullObsidianArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.obsidianHelmet && chest != null && chest.getItem() == ModItems.obsidianChestplate && legs != null && legs.getItem() == ModItems.obsidianLeggings && feet != null && feet.getItem() == ModItems.obsidianBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.obsidianArmorEffectlevel, true, true));
            }
        }
        /*Full Redstone Armor*/
        if (ARPConfig.enableFullRedstoneArmorEffect)

        {
            if (head != null && head.getItem() == ModItems.redstoneHelmet && chest != null && chest.getItem() == ModItems.redstoneChestplate && legs != null && legs.getItem() == ModItems.redstoneLeggings && feet != null && feet.getItem() == ModItems.redstoneBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 240, ARPConfig.redstoneArmorEffectlevel, true, true));
            }
        }
        /*Full Guardian Armor*/
        if (head != null && head.getItem() == ModItems.guardianHelmet && chest != null && chest.getItem() == ModItems.guardianChestplate && legs != null && legs.getItem() == ModItems.guardianLeggings && feet != null && feet.getItem() == ModItems.guardianBoots)

        {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        }
        /*Full Chicken Armor*/
        if (head != null && head.getItem() == ModItems.chickenHelmet && chest != null && chest.getItem() == ModItems.chickenChestplate && legs != null && legs.getItem() == ModItems.chickenLeggings && feet != null && feet.getItem() == ModItems.chickenBoots)

        {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, 4, true, true));
        }
        /*Full Slime Armor*/
        if (head != null && head.getItem() == ModItems.slimeHelmet && chest != null && chest.getItem() == ModItems.slimeChestplate && legs != null && legs.getItem() == ModItems.slimeLeggings && feet != null && feet.getItem() == ModItems.slimeBoots)

        {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 2, true, true));
        }

        /*
         * Tinkers' Construct Armors
         */
        /*Full Manyullym Armor*/
        if (ARPConfig.enableManyullynArmorEffects)

        {
            if (head != null && head.getItem() == ModItems.manyullynHelmet && chest != null && chest.getItem() == ModItems.manyullynChestplate && legs != null && legs.getItem() == ModItems.manyullynLeggings && feet != null && feet.getItem() == ModItems.manyullynBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120, 1, true, true));
            }
        }
        /*Full Pig Iron Armor*/
        if (ARPConfig.enablePigIronArmorEffects)

        {
            if (head != null && head.getItem() == ModItems.pigIronHelmet && chest != null && chest.getItem() == ModItems.pigIronChestplate && legs != null && legs.getItem() == ModItems.pigIronLeggings && feet != null && feet.getItem() == ModItems.pigIronBoots) {
                if (entity instanceof EntityLivingBase && entity.getFoodStats().needFood()) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0, true, true));
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                }
            }
        }
        /*Full Knight Slime Armor*/
        if (ARPConfig.enableKnightSlimeArmorEffects)

        {
            if (head != null && head.getItem() == ModItems.knightSlimeHelmet && chest != null && chest.getItem() == ModItems.knightSlimeChestplate && legs != null && legs.getItem() == ModItems.knightSlimeLeggings && feet != null && feet.getItem() == ModItems.knightSlimeBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 1, true, true));
            }
        }
        /*Cobalt Armor*/
        if (ARPConfig.enableCobaltArmorEffects)

        {
            if (head != null && head.getItem() == ModItems.cobaltHelmet && chest != null && chest.getItem() == ModItems.cobaltChestplate && legs != null && legs.getItem() == ModItems.cobaltLeggings && feet != null && feet.getItem() == ModItems.cobaltBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 2, true, true));
            }
        }
        /*Ardite Armor*/
        if (ARPConfig.enableArditeArmorEffects)

        {
            if (head != null && head.getItem() == ModItems.arditeHelmet && chest != null && chest.getItem() == ModItems.arditeChestplate && legs != null && legs.getItem() == ModItems.arditeLeggings && feet != null && feet.getItem() == ModItems.arditeBoots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
            }
        }

    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        ArmorPlus.logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.config.refresh"));
    }
}