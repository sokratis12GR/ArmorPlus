package sokratis12GR.ArmorPlus.resources;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.armors.origin.*;
import sokratis12GR.ArmorPlus.armors.reinforced.*;
import sokratis12GR.ArmorPlus.armors.special.*;
import sokratis12GR.ArmorPlus.armors.tconstruct.*;
import sokratis12GR.ArmorPlus.util.ARPAchievements;
import sokratis12GR.ArmorPlus.util.TextHelper;

public class GlobalEventsArmorPlus {
    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {

        Item i = event.crafting.getItem();

        ItemStack itemStack = event.crafting;

        /**Guardian Armor Thorns*/
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots || i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByID(7), 3);
        /**Guardian Armor Unbreaking 3*/
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots || i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
        /**Full of Thorns! - Achievement Trigger*/
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots)
            event.player.addStat(ARPAchievements.craftGuardianArmor, 1);
        /** Guardian Boots Enchantments*/
        if (i == GuardianArmor.boots || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByID(8), 3);
        /**Vision Like A Bat! - Achievement Trigger*/
        if (i == CoalArmor.helmet || i == CoalArmor.chestplate || i == CoalArmor.legs || i == CoalArmor.boots)
            event.player.addStat(ARPAchievements.craftCoalArmor, 1);
        /**Never Drown Again - Achievement Trigger*/
        if (i == LapisArmor.helmet || i == LapisArmor.chestplate || i == LapisArmor.legs || i == LapisArmor.boots)
            event.player.addStat(ARPAchievements.craftLapisArmor, 1);
        /**Speeedy! - Achievement Trigger*/
        if (i == RedstoneArmor.helmet || i == RedstoneArmor.chestplate || i == RedstoneArmor.legs || i == RedstoneArmor.boots)
            event.player.addStat(ARPAchievements.craftRedstoneArmor, 1);
        /**Swing Swing Faster! - Achievement Trigger*/
        if (i == EmeraldArmor.helmet || i == EmeraldArmor.chestplate || i == EmeraldArmor.legs || i == EmeraldArmor.boots)
            event.player.addStat(ARPAchievements.craftEmeraldArmor, 1);
        /**Undestructable! - Achievement Trigger*/
        if (i == ObsidianArmor.helmet || i == ObsidianArmor.chestplate || i == ObsidianArmor.legs || i == ObsidianArmor.boots)
            event.player.addStat(ARPAchievements.craftObsidianArmor, 1);
        /**The Overpowered! - Achievement Trigger*/
        if (i == LavaArmor.helmet || i == LavaArmor.chestplate || i == LavaArmor.legs || i == LavaArmor.boots)
            event.player.addStat(ARPAchievements.craftLavaArmor, 1);
        /**Godlike! - Achievement Trigger*/
        if (i == SuperStarArmor.helmet || i == SuperStarArmor.chestplate || i == SuperStarArmor.legs || i == SuperStarArmor.boots)
            event.player.addStat(ARPAchievements.craftSuperStarArmor, 1);
        /**The Power of the Ender Dragon! - Achievement Trigger*/
        if (i == EnderDragonArmor.helmet || i == EnderDragonArmor.chestplate || i == EnderDragonArmor.legs || i == SuperStarArmor.boots)
            event.player.addStat(ARPAchievements.craftEnderDragonArmor, 1);
        /**The Ultimate Power! - Achievement Trigger*/
        if (i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            event.player.addStat(ARPAchievements.craftTheUltimateArmor, 1);
        /** Reinforcing Armors! - Achievement Trigger*/
        if (i == RCArmor.helmet || i == RCArmor.chestplate || i == RCArmor.legs || i == RCArmor.boots
                || i == RDArmor.helmet || i == RDArmor.chestplate || i == RDArmor.legs || i == RDArmor.boots
                || i == RGArmor.helmet || i == RGArmor.chestplate || i == RGArmor.legs || i == RGArmor.boots
                || i == RIArmor.helmet || i == RIArmor.chestplate || i == RIArmor.legs || i == RIArmor.boots)
            event.player.addStat(ARPAchievements.craftReinforcedArmor, 1);

        /** Tinkers' Armors*/
        /**The Tinkers' Armors! - Achievement Trigger*/
        if (i == CobaltArmor.helmet || i == CobaltArmor.chestplate || i == CobaltArmor.legs || i == CobaltArmor.boots)
            event.player.addStat(ARPAchievements.craftCobaltArmor, 1);
        /**The Stronger The Better! - Achievement Trigger*/
        if (i == ArditeArmor.helmet || i == ArditeArmor.chestplate || i == ArditeArmor.legs || i == ArditeArmor.boots)
            event.player.addStat(ARPAchievements.craftArditeArmor, 1);
        /**The Tinkers' Armors God! - Achievement Trigger*/
        if (i == ManyullymArmor.helmet || i == ManyullymArmor.chestplate || i == ManyullymArmor.legs || i == ManyullymArmor.boots)
            event.player.addStat(ARPAchievements.craftManyullymArmor, 1);
        /** Oink! - Achievemnt Trigger*/
        if (i == PigIronArmor.helmet || i == PigIronArmor.chestplate || i == PigIronArmor.legs || i == PigIronArmor.boots)
            event.player.addStat(ARPAchievements.craftPigIronArmor, 1);
        /** Fascinating! - Achievemnt Trigger*/
        if (i == KnightSlimeArmor.helmet || i == KnightSlimeArmor.chestplate || i == KnightSlimeArmor.legs || i == KnightSlimeArmor.boots)
            event.player.addStat(ARPAchievements.craftKnightSlimeArmor, 1);
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        {

            EntityPlayer entity = event.player;

            ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            /**
             if (ConfigHandler.enableFlightAbility) {
             if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots || head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
             entity.capabilities.allowFlying = true;
             } else {
             entity.capabilities.isFlying = false;
             entity.capabilities.allowFlying = false;
             }
             }
             */
            /**The Ultimate Armor Armor*/
            if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, ConfigHandler.ultimateArmorEffectlevel, true, true));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0, true, true));
            }
            /**Full Super Star Armor*/
            if (ConfigHandler.enableFullSuperStarArmorEffect) {
                if (head != null && head.getItem() == SuperStarArmor.helmet && chest != null && chest.getItem() == SuperStarArmor.chestplate && legs != null && legs.getItem() == SuperStarArmor.legs && feet != null && feet.getItem() == SuperStarArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, ConfigHandler.superstarArmorEffectlevel, true, true));
                }
            }
            /**Full Coal Armor*/
            if (ConfigHandler.enableFullCoalArmorEffect) {
                if (head != null && head.getItem() == CoalArmor.helmet && chest != null && chest.getItem() == CoalArmor.chestplate && legs != null && legs.getItem() == CoalArmor.legs && feet != null && feet.getItem() == CoalArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0, true, true));
                }
            }
            /**Full Lapis Armor*/
            if (ConfigHandler.enableFullLapisArmorEffect) {
                if (head != null && head.getItem() == LapisArmor.helmet && chest != null && chest.getItem() == LapisArmor.chestplate && legs != null && legs.getItem() == LapisArmor.legs && feet != null && feet.getItem() == LapisArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                }
            }
            /**Full Lava Armor*/
            if (ConfigHandler.enableFullLavaArmorEffect) {
                if (head != null && head.getItem() == LavaArmor.helmet && chest != null && chest.getItem() == LavaArmor.chestplate && legs != null && legs.getItem() == LavaArmor.legs && feet != null && feet.getItem() == LavaArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.resistance, 120, ConfigHandler.lavaArmorEffectlevel, true, true));
                }
            }
            /**Full Emerald Armor*/
            if (ConfigHandler.enableFullEmeraldArmorEffect) {
                if (head != null && head.getItem() == EmeraldArmor.helmet && chest != null && chest.getItem() == EmeraldArmor.chestplate && legs != null && legs.getItem() == EmeraldArmor.legs && feet != null && feet.getItem() == EmeraldArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, ConfigHandler.emeraldArmorEffectlevel, true, true));
                }
            }
            /**Full Obsidian Armor*/
            if (ConfigHandler.enableFullObsidianArmorEffect) {
                if (head != null && head.getItem() == ObsidianArmor.helmet && chest != null && chest.getItem() == ObsidianArmor.chestplate && legs != null && legs.getItem() == ObsidianArmor.legs && feet != null && feet.getItem() == ObsidianArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.resistance, 120, ConfigHandler.obsidianArmorEffectlevel, true, true));
                }
            }
            /**Full Redstone Armor*/
            if (ConfigHandler.enableFullRedstoneArmorEffect) {
                if (head != null && head.getItem() == RedstoneArmor.helmet && chest != null && chest.getItem() == RedstoneArmor.chestplate && legs != null && legs.getItem() == RedstoneArmor.legs && feet != null && feet.getItem() == RedstoneArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 120, ConfigHandler.redstoneArmorEffectlevel, true, true));
                }
            }
            /**Full Guardian Armor*/
            if (ConfigHandler.enableFullGuardianArmorEffect) {
                if (head != null && head.getItem() == GuardianArmor.helmet && chest != null && chest.getItem() == GuardianArmor.chestplate && legs != null && legs.getItem() == GuardianArmor.legs && feet != null && feet.getItem() == GuardianArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                }
            }
            /** Tinkers' Construct Armors*/
            /**Full Manyullym Armor*/
            if (head != null && head.getItem() == ManyullymArmor.helmet && chest != null && chest.getItem() == ManyullymArmor.chestplate && legs != null && legs.getItem() == ManyullymArmor.legs && feet != null && feet.getItem() == ManyullymArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.damageBoost, 120, 1, true, true));
            }
            /**Full Pig Iron Armor*/
            if (head != null && head.getItem() == PigIronArmor.helmet && chest != null && chest.getItem() == PigIronArmor.chestplate && legs != null && legs.getItem() == PigIronArmor.legs && feet != null && feet.getItem() == PigIronArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.saturation, 120, 0, true, true));
            }
            /**Full Knight Slime Armor*/
            if (head != null && head.getItem() == KnightSlimeArmor.helmet && chest != null && chest.getItem() == KnightSlimeArmor.chestplate && legs != null && legs.getItem() == KnightSlimeArmor.legs && feet != null && feet.getItem() == KnightSlimeArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, 1, true, true));
            }
            /**Cobalt Armor*/
            if (head != null && head.getItem() == CobaltArmor.helmet && chest != null && chest.getItem() == CobaltArmor.chestplate && legs != null && legs.getItem() == CobaltArmor.legs && feet != null && feet.getItem() == CobaltArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, 2, true, true));
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        ConfigHandler.syncConfig();
        ArmorPlus.logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.config.refresh"));
    }
}