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
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.util.APAchievements;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.EnumParticleTypes.REDSTONE;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;

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
            event.player.addStat(APAchievements.welcomeToArmorPlus);
        }
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
            if (head.getItem() == lava[0] && chest.getItem() == lava[1] && legs.getItem() == lava[2] && feet.getItem() == lava[3] && entity.isInWater() && enableFullLavaArmorEffect) {
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
            } else if (head.getItem() == theUltimate[0] && chest.getItem() == theUltimate[1] && legs.getItem() == theUltimate[2] && feet.getItem() == theUltimate[3]) {
                addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[0]), 120, ultimateArmorEffectLevels[0], GOOD);
                addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[1]), 120, ultimateArmorEffectLevels[1], GOOD);
                addPotion(entity, getPotion(theUltimateArmorAddPotionEffects[2]), 120, ultimateArmorEffectLevels[2], GOOD);
                removePotion(entity, getPotion(theUltimateArmorRemovePotionEffect));
            } else if (enableFullSuperStarArmorEffect && head.getItem() == superStar[0] && chest.getItem() == superStar[1] && legs.getItem() == superStar[2] && feet.getItem() == superStar[3]) {
                if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                    addPotion(entity, getPotion(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
                removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
            } else if (enableFullCoalArmorEffect && head.getItem() == coal[0] && chest.getItem() == coal[1] && legs.getItem() == coal[2] && feet.getItem() == coal[3]) {
                addPotion(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
            } else if (enablePigIronArmorEffect && head.getItem() == pigIron[0] && chest.getItem() == pigIron[1] && legs.getItem() == pigIron[2] && feet.getItem() == pigIron[3] && entity.getFoodStats().needFood()) {
                addPotion(entity, getPotion(pigIronArmorAddPotionEffect), 20, pigIronArmorEffectLevel, GOOD);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (enableFullLapisArmorEffect && head.getItem() == lapis[0] && chest.getItem() == lapis[1] && legs.getItem() == lapis[2] && feet.getItem() == lapis[3]) {
                addPotion(entity, getPotion(lapisArmorAddPotionEffect), 120, lapisArmorEffectLevel, GOOD);
            } else if (enableFullLavaArmorEffect && head.getItem() == lava[0] && chest.getItem() == lava[1] && legs.getItem() == lava[2] && feet.getItem() == lava[3]) {
                addPotion(entity, getPotion(lavaArmorAddPotionEffect), 120, lavaArmorEffectLevel, GOOD);
            } else if (enableFullEmeraldArmorEffect && head.getItem() == emerald[0] && chest.getItem() == emerald[1] && legs.getItem() == emerald[2] && feet.getItem() == emerald[3]) {
                addPotion(entity, getPotion(emeraldArmorAddPotionEffect), 120, emeraldArmorEffectLevel, GOOD);
            } else if (enableFullObsidianArmorEffect && head.getItem() == obsidian[0] && chest.getItem() == obsidian[1] && legs.getItem() == obsidian[2] && feet.getItem() == obsidian[3]) {
                addPotion(entity, getPotion(obsidianArmorAddPotionEffect), 120, obsidianArmorEffectLevel, GOOD);
            } else if (enableFullRedstoneArmorEffect && head.getItem() == redstone[0] && chest.getItem() == redstone[1] && legs.getItem() == redstone[2] && feet.getItem() == redstone[3]) {
                addPotion(entity, getPotion(redstoneArmorAddPotionEffect), 240, redstoneArmorEffectLevel, GOOD);
                if (entity.world.isRemote) {
                    spawnParticle(entity, REDSTONE, entity.posX, entity.posY, entity.posZ);
                }
            } else if (head.getItem() == guardian[0] && chest.getItem() == guardian[1] && legs.getItem() == guardian[3] && feet.getItem() == guardian[4]) {
                addPotion(entity, getPotion(guardianArmorAddPotionEffect), 120, guardianArmorEffectLevel, GOOD);
            } else if (head.getItem() == chicken[0] && chest.getItem() == chicken[1] && legs.getItem() == chicken[2] && feet.getItem() == chicken[3]) {
                addPotion(entity, getPotion(chickenArmorAddPotionEffect), 120, chickenArmorEffectLevel, GOOD);
            } else if (head.getItem() == slime[0] && chest.getItem() == slime[1] && legs.getItem() == slime[2] && feet.getItem() == slime[3]) {
                addPotion(entity, getPotion(slimeArmorAddPotionEffect), 120, slimeArmorEffectLevel, GOOD);
            } else if (enableManyullynArmorEffect && head.getItem() == manyullyn[0] && chest.getItem() == manyullyn[1] && legs.getItem() == manyullyn[2] && feet.getItem() == manyullyn[3]) {
                addPotion(entity, getPotion(manyullynArmorAddPotionEffect), 120, manyullynArmorEffectLevel, GOOD);
            } else if (enableKnightSlimeArmorEffect && head.getItem() == knightSlime[0] && chest.getItem() == knightSlime[1] && legs.getItem() == knightSlime[2] && feet.getItem() == knightSlime[3]) {
                addPotion(entity, getPotion(knightSlimeArmorAddPotionEffect), 120, knightSlimeArmorEffectLevel, GOOD);
            } else if (enableCobaltArmorEffect && head.getItem() == cobalt[0] && chest.getItem() == cobalt[1] && legs.getItem() == cobalt[2] && feet.getItem() == cobalt[3]) {
                addPotion(entity, getPotion(cobaltArmorAddPotionEffect), 120, cobaltArmorEffectLevel, GOOD);
            } else if (enableArditeArmorEffect && head.getItem() == ardite[0] && chest.getItem() == ardite[1] && legs.getItem() == ardite[2] && feet.getItem() == ardite[3]) {
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