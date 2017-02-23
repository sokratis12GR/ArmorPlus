/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.resources

import net.minecraft.init.MobEffects
import net.minecraft.inventory.EntityEquipmentSlot.*
import net.minecraft.item.Item
import net.minecraft.util.DamageSource
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.ArmorPlus.configuration
import net.thedragonteam.armorplus.registry.APBlocks
import net.thedragonteam.armorplus.registry.APItems.*
import net.thedragonteam.armorplus.registry.ModAchievements
import net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD
import net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD
import net.thedragonteam.armorplus.util.PotionUtils.addPotion
import net.thedragonteam.armorplus.util.PotionUtils.getPotion
import net.thedragonteam.armorplus.util.PotionUtils.removePotion
import net.thedragonteam.thedragonlib.util.LogHelper

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class GlobalEventsArmorPlus {
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    fun onPlayerCraftedItem(event: PlayerEvent.ItemCraftedEvent) {
        val i = event.crafting.item
        if (i === Item.getItemFromBlock(APBlocks.workbench)) event.player.addStat(ModAchievements.welcomeToArmorPlus)
    }

    @SubscribeEvent
    fun onArmorTick(event: TickEvent.PlayerTickEvent) {
        val entity = event.player
        val head = entity.getItemStackFromSlot(HEAD)
        val chest = entity.getItemStackFromSlot(CHEST)
        val legs = entity.getItemStackFromSlot(LEGS)
        val feet = entity.getItemStackFromSlot(FEET)

        if (head.count > 0 && chest.count > 0 && legs.count > 0 && feet.count > 0) {
            when {
                head.item === lavaHelmet && chest.item === lavaChestplate && legs.item === lavaLeggings && feet.item === lavaBoots && entity.isInWater && enableFullLavaArmorEffect -> {
                    entity.extinguish()
                    if (entity.isInLava)
                        entity.absorptionAmount = 4.0f
                    if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                        addPotion(entity, MobEffects.SLOWNESS, 120, 1, BAD)
                        head.damageItem(1, entity)
                        chest.damageItem(1, entity)
                        legs.damageItem(1, entity)
                        feet.damageItem(1, entity)
                        entity.attackEntityFrom(DamageSource.DROWN, 1f)
                    }
                }
                head.item === theUltimateHelmet && chest.item === theUltimateChestplate && legs.item === theUltimateLeggings && feet.item === theUltimateBoots -> {
                    addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[0]), 120, ultimateArmorEffectLevels[0], GOOD)
                    addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[1]), 120, ultimateArmorEffectLevels[1], GOOD)
                    addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[2]), 120, ultimateArmorEffectLevels[2], GOOD)
                    removePotion(entity, getPotion(theUltimateArmorRemovePotionEffect))
                }
                enableFullSuperStarArmorEffect && head.item === superStarHelmet && chest.item === superStarChestplate && legs.item === superStarLeggings && feet.item === superStarBoots -> {
                    if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                        addPotion(entity, getPotion(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD)
                    removePotion(entity, getPotion(superStarArmorRemovePotionEffect))
                }
                enablePigIronArmorEffect && head.item === pigIronHelmet && chest.item === pigIronChestplate && legs.item === pigIronLeggings && feet.item === pigIronBoots && entity.foodStats.needFood() -> {
                    addPotion(entity, getPotion(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel, GOOD)
                    head.damageItem(1, entity)
                    chest.damageItem(1, entity)
                    legs.damageItem(1, entity)
                    feet.damageItem(1, entity)
                }
            }
            addEffects(event, enableFullCoalArmorEffect, coalHelmet, coalChestplate, coalLeggings, coalBoots, coalArmorAddPotionEffect, coalArmorEffectLevel)
            addEffects(event, enableFullLapisArmorEffect, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots, lapisArmorAddPotionEffect, lapisArmorEffectLevel)
            addEffects(event, enableFullEmeraldArmorEffect, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel)
            addEffects(event, enableFullObsidianArmorEffect, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel)
            addEffects(event, enableFullRedstoneArmorEffect, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel)
            addEffects(event, true, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots, guardianArmorAddPotionEffect, guardianArmorEffectLevel)
            addEffects(event, true, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots, chickenArmorAddPotionEffect, chickenArmorEffectLevel)
            addEffects(event, true, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots, slimeArmorAddPotionEffect, slimeArmorEffectLevel)
            addEffects(event, enableManyullynArmorEffect, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel)
            addEffects(event, enableKnightSlimeArmorEffect, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel)
            addEffects(event, enableArditeArmorEffect, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots, arditeArmorAddPotionEffect, arditeArmorEffectLevel)
            addEffects(event, enableCobaltArmorEffect, cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel)
        }
    }

    private fun addEffects(event: TickEvent.PlayerTickEvent, isEnabled: Boolean, helmet: Item, chestplate: Item, leggings: Item, boots: Item, addEffect: String, addEffectAmplifier: Int) {
        val entity = event.player
        val head = entity.getItemStackFromSlot(HEAD)
        val chest = entity.getItemStackFromSlot(CHEST)
        val legs = entity.getItemStackFromSlot(LEGS)
        val feet = entity.getItemStackFromSlot(FEET)
        if (isEnabled && head.item === helmet && chest.item === chestplate && legs.item === leggings && feet.item === boots) {
            if (entity.getActivePotionEffect(getPotion(addEffect)) == null)
                addPotion(entity, getPotion(addEffect), addEffectAmplifier, GOOD)
        }
    }

    @SubscribeEvent
    fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
        syncConfig()
        LogHelper.info("Refreshing configuration file")
    }

    companion object {

        fun syncConfig() {
            if (configuration.hasChanged())
                configuration.save()
        }
    }
}