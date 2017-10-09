/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.Utils.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class GlobalEventArmorPlus {

    public static void addEffects(TickEvent.PlayerTickEvent event, boolean isEnabled, String addEffect, int addEffectAmplifier, Item... armor) {
        EntityPlayer entity = event.player;
        ItemStack head = entity.getItemStackFromSlot(HEAD);
        ItemStack chest = entity.getItemStackFromSlot(CHEST);
        ItemStack legs = entity.getItemStackFromSlot(LEGS);
        ItemStack feet = entity.getItemStackFromSlot(FEET);
        if (armor.length != 4) {
            return;
        }
        for (Item piece : armor) {
            if (isNull(piece)) {
                return;
            }
        }
        if (!isNotNull(armor[0]) || !isNotNull(armor[1]) || !isNotNull(armor[2]) || !isNotNull(armor[3])) {
            return;
        }
        if (isArmorEmpty(head, chest, legs, feet)) {
            return;
        }
        if (isEnabled) {
            if (areSame(head, armor[0]) && areSame(chest, armor[1]) && areSame(legs, armor[2]) && areSame(feet, armor[3])) {
                if (entity.getActivePotionEffect(getPotion(addEffect)) == null || getPotion(addEffect) == MobEffects.NIGHT_VISION) {
                    addPotion(entity, getPotion(addEffect), addEffectAmplifier, GOOD);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(ArmorPlus.MODID)) {
            //        ConfigManager.sync(event.getModID(), Config.Type.INSTANCE); // Sync config values
        }

        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }

    private static void syncConfig() {
        if (configuration.hasChanged()) configuration.save();
    }

    @SubscribeEvent
    public static void onArmorTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer entity = event.player;
        ItemStack head = entity.getItemStackFromSlot(HEAD);
        ItemStack chest = entity.getItemStackFromSlot(CHEST);
        ItemStack legs = entity.getItemStackFromSlot(LEGS);
        ItemStack feet = entity.getItemStackFromSlot(FEET);

        if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty()) return;
        if (head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots && entity.isInWater() && enableFullLavaArmorEffect) {
            entity.extinguish();
            if (entity.isInLava()) {
                entity.setAbsorptionAmount(4.0f);
            }
            if (enableLavaArmorOnWaterTouchDeBuff) {
                if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(entity, MobEffects.SLOWNESS, 120, 1, BAD);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.DROWN, 1f);
                }
            }
        }
        if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots) {
            addPotion(entity, getPotion(theUltimateArmorAddPotionEffect[0]), 120, ultimateArmorEffectLevels[0], GOOD);
            addPotion(entity, getPotion(theUltimateArmorAddPotionEffect[1]), 120, ultimateArmorEffectLevels[1], GOOD);
            addPotion(entity, getPotion(theUltimateArmorAddPotionEffect[2]), 120, ultimateArmorEffectLevels[2], GOOD);
            removePotion(entity, getPotion(theUltimateArmorRemovePotionEffect));
        } else if (head.getItem() != theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots) {
            ItemUltimateArmor.onArmorTick(entity);
        } else if (head.getItem() == theUltimateHelmet && chest.getItem() != theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots) {
            ItemUltimateArmor.onArmorTick(entity);
        } else if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() != theUltimateLeggings && feet.getItem() == theUltimateBoots) {
            ItemUltimateArmor.onArmorTick(entity);
        } else if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() != theUltimateBoots) {
            ItemUltimateArmor.onArmorTick(entity);
        }
        if (enableFullSuperStarArmorEffect && head.getItem() == superStarHelmet && chest.getItem() == superStarChestplate && legs.getItem() == superStarLeggings && feet.getItem() == superStarBoots) {
            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                addPotion(entity, getPotion(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
            removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
        }
        if (enablePigIronArmorEffect && head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots && entity.getFoodStats().needFood()) {
            addPotion(entity, getPotion(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel, GOOD);
            head.damageItem(1, entity);
            chest.damageItem(1, entity);
            legs.damageItem(1, entity);
            feet.damageItem(1, entity);
        }
        if (enableCoalArmor) {
            addEffects(event, enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel, coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }
        if (enableLapisArmor) {
            addEffects(event, enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }
        if (enableEmeraldArmor) {
            addEffects(event, enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }
        if (enableObsidianArmor) {
            addEffects(event, enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }
        if (enableLavaArmor) {
            addEffects(event, enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }
        if (enableRedstoneArmor) {
            addEffects(event, enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }
        if (enableGuardianArmor) {
            addEffects(event, enableFullGuardianArmorEffect, guardianArmorAddPotionEffect, guardianArmorEffectLevel, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }
        if (enableChickenArmor) {
            addEffects(event, enableFullChickenArmorEffect, chickenArmorAddPotionEffect, chickenArmorEffectLevel, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
        }
        if (enableSlimeArmor) {
            addEffects(event, enableFullSlimeArmorEffect, slimeArmorAddPotionEffect, slimeArmorEffectLevel, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }
        if (enableManyullynArmor) {
            addEffects(event, enableManyullynArmorEffect, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }
        if (enableKnightSlimeArmor) {
            addEffects(event, enableKnightSlimeArmorEffect, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }
        if (enableArditeArmor) {
            addEffects(event, enableArditeArmorEffect, arditeArmorAddPotionEffect, arditeArmorEffectLevel, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }
        if (enableCobaltArmor) {
            addEffects(event, enableCobaltArmorEffect, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel, cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
        }
    }
}