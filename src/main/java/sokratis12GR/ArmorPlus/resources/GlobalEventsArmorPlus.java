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
import sokratis12GR.ArmorPlus.armors.reinforced.RCArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RDArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RGArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RIArmor;
import sokratis12GR.ArmorPlus.armors.special.EnderDragonArmor;
import sokratis12GR.ArmorPlus.armors.special.GuardianArmor;
import sokratis12GR.ArmorPlus.armors.special.SuperStarArmor;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.ChickenArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.SlimeArmor;
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
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("thorns"), 3);
        /**Guardian Armor Unbreaking 3*/
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots || i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 3);
        /**Full of Thorns! - Achievement Trigger*/
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_GUARDIAN_ARMOR, 1);
        /** Guardian Boots Enchantments*/
        if (i == GuardianArmor.boots || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("depth_strider"), 3);
        /**Vision Like A Bat! - Achievement Trigger*/
        if (i == CoalArmor.helmet || i == CoalArmor.chestplate || i == CoalArmor.legs || i == CoalArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_COAL_ARMOR, 1);
        /**Never Drown Again - Achievement Trigger*/
        if (i == LapisArmor.helmet || i == LapisArmor.chestplate || i == LapisArmor.legs || i == LapisArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_LAPIS_ARMOR, 1);
        /**Speeedy! - Achievement Trigger*/
        if (i == RedstoneArmor.helmet || i == RedstoneArmor.chestplate || i == RedstoneArmor.legs || i == RedstoneArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_REDSTONE_ARMOR, 1);
        /**Swing Swing Faster! - Achievement Trigger*/
        if (i == EmeraldArmor.helmet || i == EmeraldArmor.chestplate || i == EmeraldArmor.legs || i == EmeraldArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_EMERALD_ARMOR, 1);
        /**Undestructable! - Achievement Trigger*/
        if (i == ObsidianArmor.helmet || i == ObsidianArmor.chestplate || i == ObsidianArmor.legs || i == ObsidianArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_OBSIDIAN_ARMOR, 1);
        /**The Overpowered! - Achievement Trigger*/
        if (i == LavaArmor.helmet || i == LavaArmor.chestplate || i == LavaArmor.legs || i == LavaArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_LAVA_ARMOR, 1);
        /**Godlike! - Achievement Trigger*/
        if (i == SuperStarArmor.helmet || i == SuperStarArmor.chestplate || i == SuperStarArmor.legs || i == SuperStarArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_SUPER_STAR_ARMOR, 1);
        /**The Power of the Ender Dragon! - Achievement Trigger*/
        if (i == EnderDragonArmor.helmet || i == EnderDragonArmor.chestplate || i == EnderDragonArmor.legs || i == SuperStarArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_ENDER_DRAGON_ARMOR, 1);
        /**The Ultimate Power! - Achievement Trigger*/
        if (i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_THE_ULTIMATE_ARMOR, 1);
        /**Jump Jump Jump! - Achievement Trigger*/
        if (i == SlimeArmor.helmet || i == SlimeArmor.chestplate || i == SlimeArmor.legs || i == SlimeArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_SLIME_ARMOR, 1);
        /**Its time to go Faster! - Achievement Trigger*/
        if (i == ChickenArmor.helmet || i == ChickenArmor.chestplate || i == ChickenArmor.legs || i == ChickenArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_CHICKEN_ARMOR, 1);
        /** Reinforcing Armors! - Achievement Trigger*/
        if (i == RCArmor.helmet || i == RCArmor.chestplate || i == RCArmor.legs || i == RCArmor.boots
                || i == RDArmor.helmet || i == RDArmor.chestplate || i == RDArmor.legs || i == RDArmor.boots
                || i == RGArmor.helmet || i == RGArmor.chestplate || i == RGArmor.legs || i == RGArmor.boots
                || i == RIArmor.helmet || i == RIArmor.chestplate || i == RIArmor.legs || i == RIArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_REINFORCED_ARMOR, 1);

        /** Tinkers' Armors*/
        /**The Tinkers' Armors! - Achievement Trigger*/
        if (i == CobaltArmor.helmet || i == CobaltArmor.chestplate || i == CobaltArmor.legs || i == CobaltArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_COBALT_ARMOR, 1);
        /**The Stronger The Better! - Achievement Trigger*/
        if (i == ArditeArmor.helmet || i == ArditeArmor.chestplate || i == ArditeArmor.legs || i == ArditeArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_ARDITE_ARMOR, 1);
        /**The Tinkers' Armors God! - Achievement Trigger*/
        if (i == ManyullynArmor.helmet || i == ManyullynArmor.chestplate || i == ManyullynArmor.legs || i == ManyullynArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_MANYULLYN_ARMOR, 1);
        /** Oink! - Achievemnt Trigger*/
        if (i == PigIronArmor.helmet || i == PigIronArmor.chestplate || i == PigIronArmor.legs || i == PigIronArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_PIG_IRON_ARMOR, 1);
        /** Fascinating! - Achievemnt Trigger*/
        if (i == KnightSlimeArmor.helmet || i == KnightSlimeArmor.chestplate || i == KnightSlimeArmor.legs || i == KnightSlimeArmor.boots)
            event.player.addStat(ARPAchievements.CRAFT_KNIGHT_SLIME_ARMOR, 1);
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        {

            EntityPlayer entity = event.player;

            ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            /**The Ultimate Armor Armor*/
            if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots) {
                if (entity instanceof EntityLivingBase) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ConfigHandler.ultimateArmorEffectlevel, true, true));
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                    ((EntityLivingBase) entity).removePotionEffect(MobEffects.WITHER);
                }
            }
            /**Full Super Star Armor*/
            if (ConfigHandler.enableFullSuperStarArmorEffect) {
                if (head != null && head.getItem() == SuperStarArmor.helmet && chest != null && chest.getItem() == SuperStarArmor.chestplate && legs != null && legs.getItem() == SuperStarArmor.legs && feet != null && feet.getItem() == SuperStarArmor.boots) {
                    if (entity instanceof EntityLivingBase) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ConfigHandler.superstarArmorEffectlevel, true, true));
                        ((EntityLivingBase) entity).removePotionEffect(MobEffects.WITHER);
                    }
                }
            }
            /**Full Coal Armor*/
            if (ConfigHandler.enableFullCoalArmorEffect) {
                if (head != null && head.getItem() == CoalArmor.helmet && chest != null && chest.getItem() == CoalArmor.chestplate && legs != null && legs.getItem() == CoalArmor.legs && feet != null && feet.getItem() == CoalArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                }
            }
            /**Full Lapis Armor*/
            if (ConfigHandler.enableFullLapisArmorEffect) {
                if (head != null && head.getItem() == LapisArmor.helmet && chest != null && chest.getItem() == LapisArmor.chestplate && legs != null && legs.getItem() == LapisArmor.legs && feet != null && feet.getItem() == LapisArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }
            /**Full Lava Armor*/
            if (ConfigHandler.enableFullLavaArmorEffect) {
                if (head != null && head.getItem() == LavaArmor.helmet && chest != null && chest.getItem() == LavaArmor.chestplate && legs != null && legs.getItem() == LavaArmor.legs && feet != null && feet.getItem() == LavaArmor.boots) {
                    if (entity instanceof EntityLivingBase) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ConfigHandler.lavaArmorEffectlevel, true, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                    }
                }
            }
            /**Full Emerald Armor*/
            if (ConfigHandler.enableFullEmeraldArmorEffect) {
                if (head != null && head.getItem() == EmeraldArmor.helmet && chest != null && chest.getItem() == EmeraldArmor.chestplate && legs != null && legs.getItem() == EmeraldArmor.legs && feet != null && feet.getItem() == EmeraldArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ConfigHandler.emeraldArmorEffectlevel, true, true));
                }
            }
            /**Full Obsidian Armor*/
            if (ConfigHandler.enableFullObsidianArmorEffect) {
                if (head != null && head.getItem() == ObsidianArmor.helmet && chest != null && chest.getItem() == ObsidianArmor.chestplate && legs != null && legs.getItem() == ObsidianArmor.legs && feet != null && feet.getItem() == ObsidianArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ConfigHandler.obsidianArmorEffectlevel, true, true));
                }
            }
            /**Full Redstone Armor*/
            if (ConfigHandler.enableFullRedstoneArmorEffect) {
                if (head != null && head.getItem() == RedstoneArmor.helmet && chest != null && chest.getItem() == RedstoneArmor.chestplate && legs != null && legs.getItem() == RedstoneArmor.legs && feet != null && feet.getItem() == RedstoneArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, ConfigHandler.redstoneArmorEffectlevel, true, true));
                }
            }
            /**Full Guardian Armor*/
            if (ConfigHandler.enableFullGuardianArmorEffect) {
                if (head != null && head.getItem() == GuardianArmor.helmet && chest != null && chest.getItem() == GuardianArmor.chestplate && legs != null && legs.getItem() == GuardianArmor.legs && feet != null && feet.getItem() == GuardianArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }
            /**Chicken Armor*/
            if (head != null && head.getItem() == ChickenArmor.helmet && chest != null && chest.getItem() == ChickenArmor.chestplate && legs != null && legs.getItem() == ChickenArmor.legs && feet != null && feet.getItem() == ChickenArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, 4, true, true));
            }
            /**Slime Armor*/
            if (head != null && head.getItem() == SlimeArmor.helmet && chest != null && chest.getItem() == SlimeArmor.chestplate && legs != null && legs.getItem() == SlimeArmor.legs && feet != null && feet.getItem() == SlimeArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 2, true, true));
            }

            /**
             * Tinkers' Construct Armors
             */
            /**Full Manyullym Armor*/
            if (ConfigHandler.enableManyullynArmorEffects) {
                if (head != null && head.getItem() == ManyullynArmor.helmet && chest != null && chest.getItem() == ManyullynArmor.chestplate && legs != null && legs.getItem() == ManyullynArmor.legs && feet != null && feet.getItem() == ManyullynArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120, 1, true, true));
                }
            }
            /**Full Pig Iron Armor*/
            if (ConfigHandler.enablePigIronArmorEffects) {
                if (head != null && head.getItem() == PigIronArmor.helmet && chest != null && chest.getItem() == PigIronArmor.chestplate && legs != null && legs.getItem() == PigIronArmor.legs && feet != null && feet.getItem() == PigIronArmor.boots) {
                    if (entity instanceof EntityLivingBase && entity.getFoodStats().needFood()) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0, true, true));
                        head.damageItem(1, entity);
                        chest.damageItem(1, entity);
                        legs.damageItem(1, entity);
                        feet.damageItem(1, entity);
                    }
                }
            }
            /**Full Knight Slime Armor*/
            if (ConfigHandler.enableKnightSlimeArmorEffects) {
                if (head != null && head.getItem() == KnightSlimeArmor.helmet && chest != null && chest.getItem() == KnightSlimeArmor.chestplate && legs != null && legs.getItem() == KnightSlimeArmor.legs && feet != null && feet.getItem() == KnightSlimeArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 1, true, true));
                }
            }
            /**Cobalt Armor*/
            if (ConfigHandler.enableCobaltArmorEffects) {
                if (head != null && head.getItem() == CobaltArmor.helmet && chest != null && chest.getItem() == CobaltArmor.chestplate && legs != null && legs.getItem() == CobaltArmor.legs && feet != null && feet.getItem() == CobaltArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 2, true, true));
                }
            }
            /**Ardite Armor*/
            if (ConfigHandler.enableArditeArmorEffects) {
                if (head != null && head.getItem() == CobaltArmor.helmet && chest != null && chest.getItem() == CobaltArmor.chestplate && legs != null && legs.getItem() == CobaltArmor.legs && feet != null && feet.getItem() == CobaltArmor.boots) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                }
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        ConfigHandler.syncConfig();
        ArmorPlus.logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.config.refresh"));
    }
}