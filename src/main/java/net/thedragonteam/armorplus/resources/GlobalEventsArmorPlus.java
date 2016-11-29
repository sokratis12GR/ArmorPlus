/*
 * Copyright (c) TheDragonTeam 2016.
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
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.potion.Potion.getPotionFromResourceLocation;
import static net.minecraft.util.EnumParticleTypes.REDSTONE;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.armorplus.util.PotionUtils.EffectType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.EffectType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.addEffect;
import static net.thedragonteam.armorplus.util.PotionUtils.removeEffect;

public class GlobalEventsArmorPlus {

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
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
                    addEffect(entity, MobEffects.SLOWNESS, 120, 1, BAD);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.DROWN, 1F);
                }
            } else if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots) {
                addEffect(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[0]), 120, ultimateArmorEffectLevels[0], GOOD);
                addEffect(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[1]), 120, ultimateArmorEffectLevels[1], GOOD);
                addEffect(entity, getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[2]), 120, ultimateArmorEffectLevels[2], GOOD);
                removeEffect(entity, getPotionFromResourceLocation(theUltimateArmorRemovePotionEffect));
            } else if (enableFullSuperStarArmorEffect && head.getItem() == superStarHelmet && chest.getItem() == superStarChestplate && legs.getItem() == superStarLeggings && feet.getItem() == superStarBoots) {
                if (entity.getActivePotionEffect(getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                    addEffect(entity, getPotionFromResourceLocation(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
                removeEffect(entity, getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
            } else if (enableFullCoalArmorEffect && head.getItem() == coalHelmet && chest.getItem() == coalChestplate && legs.getItem() == coalLeggings && feet.getItem() == coalBoots) {
                addEffect(entity, getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
            } else if (enablePigIronArmorEffect && head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots && entity.getFoodStats().needFood()) {
                addEffect(entity, getPotionFromResourceLocation(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel, GOOD);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (enableFullLapisArmorEffect && head.getItem() == lapisHelmet && chest.getItem() == lapisChestplate && legs.getItem() == lapisLeggings && feet.getItem() == lapisBoots) {
                addEffect(entity, getPotionFromResourceLocation(lapisArmorAddPotionEffect), 120, lapisArmorEffectLevel, GOOD);
            } else if (enableFullLavaArmorEffect && head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots) {
                addEffect(entity, getPotionFromResourceLocation(lavaArmorAddPotionEffect), 120, lavaArmorEffectLevel, GOOD);
            } else if (enableFullEmeraldArmorEffect && head.getItem() == emeraldHelmet && chest.getItem() == emeraldChestplate && legs.getItem() == emeraldLeggings && feet.getItem() == emeraldBoots) {
                addEffect(entity, getPotionFromResourceLocation(emeraldArmorAddPotionEffect), 120, emeraldArmorEffectLevel, GOOD);
            } else if (enableFullObsidianArmorEffect && head.getItem() == obsidianHelmet && chest.getItem() == obsidianChestplate && legs.getItem() == obsidianLeggings && feet.getItem() == obsidianBoots) {
                addEffect(entity, getPotionFromResourceLocation(obsidianArmorAddPotionEffect), 120, obsidianArmorEffectLevel, GOOD);
            } else if (enableFullRedstoneArmorEffect && head.getItem() == redstoneHelmet && chest.getItem() == redstoneChestplate && legs.getItem() == redstoneLeggings && feet.getItem() == redstoneBoots) {
                addEffect(entity, getPotionFromResourceLocation(redstoneArmorAddPotionEffect), 240, redstoneArmorEffectLevel, GOOD);
                if (entity.world.isRemote) {
                    spawnParticle(entity, REDSTONE, entity.posX, entity.posY, entity.posZ);
                }
            } else if (head.getItem() == guardianHelmet && chest.getItem() == guardianChestplate && legs.getItem() == guardianLeggings && feet.getItem() == guardianBoots) {
                addEffect(entity, getPotionFromResourceLocation(guardianArmorAddPotionEffect), 120, guardianArmorEffectLevel, GOOD);
            } else if (head.getItem() == chickenHelmet && chest.getItem() == chickenChestplate && legs.getItem() == chickenLeggings && feet.getItem() == chickenBoots) {
                addEffect(entity, getPotionFromResourceLocation(chickenArmorAddPotionEffect), 120, chickenArmorEffectLevel, GOOD);
            } else if (head.getItem() == slimeHelmet && chest.getItem() == slimeChestplate && legs.getItem() == slimeLeggings && feet.getItem() == slimeBoots) {
                addEffect(entity, getPotionFromResourceLocation(slimeArmorAddPotionEffect), 120, slimeArmorEffectLevel, GOOD);
            } else if (enableManyullynArmorEffect && head.getItem() == manyullynHelmet && chest.getItem() == manyullynChestplate && legs.getItem() == manyullynLeggings && feet.getItem() == manyullynBoots) {
                addEffect(entity, getPotionFromResourceLocation(manyullynArmorAddPotionEffect), 120, manyullynArmorEffectLevel, GOOD);
            } else if (enableKnightSlimeArmorEffect && head.getItem() == knightSlimeHelmet && chest.getItem() == knightSlimeChestplate && legs.getItem() == knightSlimeLeggings && feet.getItem() == knightSlimeBoots) {
                addEffect(entity, getPotionFromResourceLocation(knightSlimeArmorAddPotionEffect), 120, knightSlimeArmorEffectLevel, GOOD);
            } else if (enableCobaltArmorEffect && head.getItem() == cobaltHelmet && chest.getItem() == cobaltChestplate && legs.getItem() == cobaltLeggings && feet.getItem() == cobaltBoots) {
                addEffect(entity, getPotionFromResourceLocation(cobaltArmorAddPotionEffect), 120, cobaltArmorEffectLevel, GOOD);
            } else if (enableArditeArmorEffect && head.getItem() == arditeHelmet && chest.getItem() == arditeChestplate && legs.getItem() == arditeLeggings && feet.getItem() == arditeBoots) {
                addEffect(entity, getPotionFromResourceLocation(arditeArmorAddPotionEffect), 120, arditeArmorEffectLevel, GOOD);
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }
}