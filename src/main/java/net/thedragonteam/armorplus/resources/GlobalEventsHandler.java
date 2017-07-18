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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.registry.APBlocks;
import net.thedragonteam.armorplus.registry.ModAchievements;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.Utils.isNotNull;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class GlobalEventsHandler {
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    public static void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
        if (i == Item.getItemFromBlock(APBlocks.workbench)) {
            event.player.addStat(ModAchievements.welcomeToArmorPlus);
        }
    }

    public static void addEffects(TickEvent.PlayerTickEvent event, boolean isEnabled, Item helmet, Item chestplate, Item leggings, Item boots, String addEffect, int addEffectAmplifier) {
        EntityPlayer entity = event.player;
        ItemStack head = entity.getItemStackFromSlot(HEAD);
        ItemStack chest = entity.getItemStackFromSlot(CHEST);
        ItemStack legs = entity.getItemStackFromSlot(LEGS);
        ItemStack feet = entity.getItemStackFromSlot(FEET);
        if (isEnabled) {
            if (!head.isEmpty() && !chest.isEmpty() && !legs.isEmpty() && !feet.isEmpty()) {
                if (isNotNull(helmet) && isNotNull(chestplate) && isNotNull(leggings) && isNotNull(boots)) {
                    if (head.getItem() == helmet && chest.getItem() == chestplate && legs.getItem() == leggings && feet.getItem() == boots) {
                        if (entity.getActivePotionEffect(getPotion(addEffect)) == null || getPotion(addEffect) == MobEffects.NIGHT_VISION) {
                            addPotion(entity, getPotion(addEffect), addEffectAmplifier, GOOD);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.INSTANCE.info("Refreshing configuration file");
    }

    private static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
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
        if (enableCoalArmor)
            addEffects(event, enableFullCoalArmorEffect, coalHelmet, coalChestplate, coalLeggings, coalBoots, coalArmorAddPotionEffect, coalArmorEffectLevel);
        if (enableLapisArmor)
            addEffects(event, enableFullLapisArmorEffect, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots, lapisArmorAddPotionEffect, lapisArmorEffectLevel);
        if (enableEmeraldArmor)
            addEffects(event, enableFullEmeraldArmorEffect, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel);
        if (enableObsidianArmor)
            addEffects(event, enableFullObsidianArmorEffect, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel);
        if (enableLavaArmor)
            addEffects(event, enableFullLavaArmorEffect, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots, lavaArmorAddPotionEffect, lavaArmorEffectLevel);
        if (enableRedstoneArmor)
            addEffects(event, enableFullRedstoneArmorEffect, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel);
        if (enableGuardianArmor)
            addEffects(event, enableFullGuardianArmorEffect, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots, guardianArmorAddPotionEffect, guardianArmorEffectLevel);
        if (enableChickenArmor)
            addEffects(event, enableFullChickenArmorEffect, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots, chickenArmorAddPotionEffect, chickenArmorEffectLevel);
        if (enableSlimeArmor)
            addEffects(event, enableFullSlimeArmorEffect, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots, slimeArmorAddPotionEffect, slimeArmorEffectLevel);
        if (enableManyullynArmor)
            addEffects(event, enableManyullynArmorEffect, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel);
        if (enableKnightSlimeArmor)
            addEffects(event, enableKnightSlimeArmorEffect, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel);
        if (enableArditeArmor)
            addEffects(event, enableArditeArmorEffect, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots, arditeArmorAddPotionEffect, arditeArmorEffectLevel);
        if (enableCobaltArmor)
            addEffects(event, enableCobaltArmorEffect, cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel);
    }
}