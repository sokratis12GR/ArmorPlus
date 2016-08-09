package net.thedragonteam.armorplus.resources;

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
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.client.ClientTickHandler;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.core.util.TextHelper;

public class GlobalEventsArmorPlus {
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        ClientTickHandler handler = new ClientTickHandler();
        handler.onTick(event);
    }

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {

        Item i = event.crafting.getItem();

        ItemStack itemStack = event.crafting;

        /** Swords */
        if (i == ModItems.LAPIS_SWORD)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (i == ModItems.GUARDIAN_SWORD)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
        /** BattleAxes */
        if (i == ModItems.LAPIS_BATTLE_AXE)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (i == ModItems.GUARDIAN_BATTLE_AXE)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
        /** Bows */
        if (i == ModItems.LAPIS_BOW)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
        if (i == ModItems.GUARDIAN_BOW)
            itemStack.addEnchantment(Enchantment.getEnchantmentByLocation("power"), 1);

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
        if (head != null && head.getItem() == ModItems.THE_ULTIMATE_HELMET && chest != null && chest.getItem() == ModItems.THE_ULTIMATE_CHESTPLATE && legs != null && legs.getItem() == ModItems.THE_ULTIMATE_LEGGINGS && feet != null && feet.getItem() == ModItems.THE_ULTIMATE_BOOTS) {
            if (entity instanceof EntityLivingBase) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ARPConfig.ultimateArmorEffectlevel, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 120, 0, true, true));
                entity.removePotionEffect(MobEffects.WITHER);
            }
        }
        /**Full Super Star Armor*/
        if (ARPConfig.enableFullSuperStarArmorEffect) {
            if (head != null && head.getItem() == ModItems.SUPER_STAR_HELMET && chest != null && chest.getItem() == ModItems.SUPER_STAR_CHESTPLATE && legs != null && legs.getItem() == ModItems.SUPER_STAR_LEGGINGS && feet != null && feet.getItem() == ModItems.SUPER_STAR_BOOTS) {
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
        if (head != null && head.getItem() == ModItems.GUARDIAN_HELMET && chest != null && chest.getItem() == ModItems.GUARDIAN_CHESTPLATE && legs != null && legs.getItem() == ModItems.GUARDIAN_LEGGINGS && feet != null && feet.getItem() == ModItems.GUARDIAN_BOOTS) {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        }
        /**Full Chicken Armor*/
        if (head != null && head.getItem() == ModItems.CHICKEN_HELMET && chest != null && chest.getItem() == ModItems.CHICKEN_CHESTPLATE && legs != null && legs.getItem() == ModItems.CHICKEN_LEGGINGS && feet != null && feet.getItem() == ModItems.CHICKEN_BOOTS) {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120, 4, true, true));
        }
        /**Full Slime Armor*/
        if (head != null && head.getItem() == ModItems.SLIME_HELMET && chest != null && chest.getItem() == ModItems.SLIME_CHESTPLATE && legs != null && legs.getItem() == ModItems.SLIME_LEGGINGS && feet != null && feet.getItem() == ModItems.SLIME_BOOTS) {
            if (entity instanceof EntityLivingBase)
                entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 2, true, true));
        }

        /**
         * Tinkers' Construct Armors
         */
        /**Full Manyullym Armor*/
        if (ARPConfig.enableManyullynArmorEffects) {
            if (head != null && head.getItem() == ModItems.MANYULLYN_HELMET && chest != null && chest.getItem() == ModItems.MANYULLYN_CHESTPLATE && legs != null && legs.getItem() == ModItems.MANYULLYN_LEGGINGS && feet != null && feet.getItem() == ModItems.MANYULLYN_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120, 1, true, true));
            }
        }
        /**Full Pig Iron Armor*/
        if (ARPConfig.enablePigIronArmorEffects) {
            if (head != null && head.getItem() == ModItems.PIG_IRON_HELMET && chest != null && chest.getItem() == ModItems.PIG_IRON_CHESTPLATE && legs != null && legs.getItem() == ModItems.PIG_IRON_LEGGINGS && feet != null && feet.getItem() == ModItems.PIG_IRON_BOOTS) {
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
            if (head != null && head.getItem() == ModItems.KNIGHT_SLIME_HELMET && chest != null && chest.getItem() == ModItems.KNIGHT_SLIME_CHESTPLATE && legs != null && legs.getItem() == ModItems.KNIGHT_SLIME_LEGGINGS && feet != null && feet.getItem() == ModItems.KNIGHT_SLIME_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 1, true, true));
            }
        }
        /**Cobalt Armor*/
        if (ARPConfig.enableCobaltArmorEffects) {
            if (head != null && head.getItem() == ModItems.COBALT_HELMET && chest != null && chest.getItem() == ModItems.COBALT_CHESTPLATE && legs != null && legs.getItem() == ModItems.COBALT_LEGGINGS && feet != null && feet.getItem() == ModItems.COBALT_BOOTS) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, 2, true, true));
            }
        }
        /**Ardite Armor*/
        if (ARPConfig.enableArditeArmorEffects) {
            if (head != null && head.getItem() == ModItems.ARDITE_HELMET && chest != null && chest.getItem() == ModItems.ARDITE_CHESTPLATE && legs != null && legs.getItem() == ModItems.ARDITE_LEGGINGS && feet != null && feet.getItem() == ModItems.ARDITE_BOOTS) {
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