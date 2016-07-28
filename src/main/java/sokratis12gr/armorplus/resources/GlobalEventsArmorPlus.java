package sokratis12gr.armorplus.resources;

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
import sokratis12gr.armorplus.ARPConfig;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.armors.special.GuardianArmor;
import sokratis12gr.armorplus.armors.special.SuperStarArmor;
import sokratis12gr.armorplus.armors.special.TheUltimateArmor;
import sokratis12gr.armorplus.armors.special.mob.ChickenArmor;
import sokratis12gr.armorplus.armors.special.mob.SlimeArmor;
import sokratis12gr.armorplus.armors.tconstruct.*;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;
import sokratis12gr.armorplus.util.ARPAchievements;
import sokratis12gr.armorplus.util.TextHelper;

public class GlobalEventsArmorPlus {

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {

        Item i = event.crafting.getItem();

        ItemStack itemStack = event.crafting;

        /**Guardian Armor Thorns*/
        if (i == Item.getItemFromBlock(ModBlocks.ARMOR_FORGE)) {
            event.player.addStat(ARPAchievements.WELCOME_TO_ARMORPLUS, 1);
            event.player.inventory.addItemStackToInventory(new ItemStack(ModItems.ARMORPLUS_BOOK, 1));
        }
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer entity = event.player;
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        /**The Ultimate Armor Armor*/
        if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots) {
            if (entity instanceof EntityLivingBase) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.ultimateArmorEffectlevel, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 120, 0, true, true));
                entity.removePotionEffect(MobEffects.WITHER);
            }
        }
        /**Full Super Star Armor*/
        if (ARPConfig.enableFullSuperStarArmorEffect) {
            if (head != null && head.getItem() == SuperStarArmor.helmet && chest != null && chest.getItem() == SuperStarArmor.chestplate && legs != null && legs.getItem() == SuperStarArmor.legs && feet != null && feet.getItem() == SuperStarArmor.boots) {
                if (entity instanceof EntityLivingBase) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.superstarArmorEffectlevel, true, true));
                    entity.removePotionEffect(MobEffects.WITHER);
                }
            }
        }
        /**Full Coal Armor*/
        if (ARPConfig.enableFullCoalArmorEffect) {
            if (head != null && head.getItem() == ModItems.COAL_HELMET && chest != null && chest.getItem() == ModItems.COAL_CHESTPLATE && legs != null && legs.getItem() == ModItems.COAL_LEGGINGS && feet != null && feet.getItem() == ModItems.COAL_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
            }
        }
        /**Full Lapis Armor*/
        if (ARPConfig.enableFullLapisArmorEffect) {
            if (head != null && head.getItem() == ModItems.LAPIS_HELMET && chest != null && chest.getItem() == ModItems.LAPIS_CHESTPLATE && legs != null && legs.getItem() == ModItems.LAPIS_LEGGINGS && feet != null && feet.getItem() == ModItems.LAPIS_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
            }
        }
        /**Full Lava Armor*/
        if (ARPConfig.enableFullLavaArmorEffect) {
            if (head != null && head.getItem() == ModItems.LAVA_HELMET && chest != null && chest.getItem() == ModItems.LAVA_CHESTPLATE && legs != null && legs.getItem() == ModItems.LAVA_LEGGINGS && feet != null && feet.getItem() == ModItems.LAVA_BOOTS) {
                if (entity instanceof EntityLivingBase) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                }
            }
        }
        /**Full Emerald Armor*/
        if (ARPConfig.enableFullEmeraldArmorEffect) {
            if (head != null && head.getItem() == ModItems.EMERALD_HELMET && chest != null && chest.getItem() == ModItems.EMERALD_CHESTPLATE && legs != null && legs.getItem() == ModItems.EMERALD_LEGGINGS && feet != null && feet.getItem() == ModItems.EMERALD_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ARPConfig.emeraldArmorEffectlevel, true, true));
            }
        }
        /**Full Obsidian Armor*/
        if (ARPConfig.enableFullObsidianArmorEffect) {
            if (head != null && head.getItem() == ModItems.OBSIDIAN_HELMET && chest != null && chest.getItem() == ModItems.OBSIDIAN_CHESTPLATE && legs != null && legs.getItem() == ModItems.OBSIDIAN_LEGGINGS && feet != null && feet.getItem() == ModItems.OBSIDIAN_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.obsidianArmorEffectlevel, true, true));
            }
        }
        /**Full Redstone Armor*/
        if (ARPConfig.enableFullRedstoneArmorEffect) {
            if (head != null && head.getItem() == ModItems.REDSTONE_HELMET && chest != null && chest.getItem() == ModItems.REDSTONE_CHESTPLATE && legs != null && legs.getItem() == ModItems.REDSTONE_LEGGINGS && feet != null && feet.getItem() == ModItems.REDSTONE_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, ARPConfig.redstoneArmorEffectlevel, true, true));
            }
        }
        /**Full Guardian Armor*/
        if (head != null && head.getItem() == GuardianArmor.helmet && chest != null && chest.getItem() == GuardianArmor.chestplate && legs != null && legs.getItem() == GuardianArmor.legs && feet != null && feet.getItem() == GuardianArmor.boots) {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        }
        /**Full Chicken Armor*/
        if (head != null && head.getItem() == ChickenArmor.helmet && chest != null && chest.getItem() == ChickenArmor.chestplate && legs != null && legs.getItem() == ChickenArmor.legs && feet != null && feet.getItem() == ChickenArmor.boots) {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, 4, true, true));
        }
        /**Full Slime Armor*/
        if (head != null && head.getItem() == SlimeArmor.helmet && chest != null && chest.getItem() == SlimeArmor.chestplate && legs != null && legs.getItem() == SlimeArmor.legs && feet != null && feet.getItem() == SlimeArmor.boots) {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 2, true, true));
        }

        /**
         * Tinkers' Construct Armors
         */
        /**Full Manyullym Armor*/
        if (ARPConfig.enableManyullynArmorEffects) {
            if (head != null && head.getItem() == ManyullynArmor.helmet && chest != null && chest.getItem() == ManyullynArmor.chestplate && legs != null && legs.getItem() == ManyullynArmor.legs && feet != null && feet.getItem() == ManyullynArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120, 1, true, true));
            }
        }
        /**Full Pig Iron Armor*/
        if (ARPConfig.enablePigIronArmorEffects) {
            if (head != null && head.getItem() == PigIronArmor.helmet && chest != null && chest.getItem() == PigIronArmor.chestplate && legs != null && legs.getItem() == PigIronArmor.legs && feet != null && feet.getItem() == PigIronArmor.boots) {
                if (entity instanceof EntityLivingBase && entity.getFoodStats().needFood()) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0, true, true));
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                }
            }
        }
        /**Full Knight Slime Armor*/
        if (ARPConfig.enableKnightSlimeArmorEffects) {
            if (head != null && head.getItem() == KnightSlimeArmor.helmet && chest != null && chest.getItem() == KnightSlimeArmor.chestplate && legs != null && legs.getItem() == KnightSlimeArmor.legs && feet != null && feet.getItem() == KnightSlimeArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 1, true, true));
            }
        }
        /**Cobalt Armor*/
        if (ARPConfig.enableCobaltArmorEffects) {
            if (head != null && head.getItem() == CobaltArmor.helmet && chest != null && chest.getItem() == CobaltArmor.chestplate && legs != null && legs.getItem() == CobaltArmor.legs && feet != null && feet.getItem() == CobaltArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 2, true, true));
            }
        }
        /**Ardite Armor*/
        if (ARPConfig.enableArditeArmorEffects) {
            if (head != null && head.getItem() == ArditeArmor.helmet && chest != null && chest.getItem() == ArditeArmor.chestplate && legs != null && legs.getItem() == ArditeArmor.legs && feet != null && feet.getItem() == ArditeArmor.boots) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
            }
        }
        /*if (head != null && head.getItem() == SteelArmor.helmet && chest != null && chest.getItem() == SteelArmor.chestplate && legs != null && legs.getItem() == SteelArmor.legs && feet != null && feet.getItem() == SteelArmor.boots) {
            /**  entity.replaceItemInInventory(100 + EntityEquipmentSlot.HEAD.getIndex(), new ItemStack(ElectricalArmor.helmet, 1));
             entity.replaceItemInInventory(100 + EntityEquipmentSlot.CHEST.getIndex(), new ItemStack(ElectricalArmor.chestplate, 1));
             entity.replaceItemInInventory(100 + EntityEquipmentSlot.LEGS.getIndex(), new ItemStack(ElectricalArmor.legs, 1));
             entity.replaceItemInInventory(100 + EntityEquipmentSlot.FEET.getIndex(), new ItemStack(ElectricalArmor.boots, 1));
        }*/
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        ArmorPlus.logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.config.refresh"));
    }
}