/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.dev.DevBoots;
import net.thedragonteam.armorplus.armors.dev.DevChestplate;
import net.thedragonteam.armorplus.armors.dev.DevHelmet;
import net.thedragonteam.armorplus.armors.dev.DevLeggings;
import net.thedragonteam.armorplus.armors.origin.coal.CoalBoots;
import net.thedragonteam.armorplus.armors.origin.coal.CoalChestplate;
import net.thedragonteam.armorplus.armors.origin.coal.CoalHelmet;
import net.thedragonteam.armorplus.armors.origin.coal.CoalLeggings;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldBoots;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldChestplate;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldHelmet;
import net.thedragonteam.armorplus.armors.origin.emerald.EmeraldLeggings;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisBoots;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisChestplate;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisHelmet;
import net.thedragonteam.armorplus.armors.origin.lapis.LapisLeggings;
import net.thedragonteam.armorplus.armors.origin.lava.LavaBoots;
import net.thedragonteam.armorplus.armors.origin.lava.LavaChestplate;
import net.thedragonteam.armorplus.armors.origin.lava.LavaHelmet;
import net.thedragonteam.armorplus.armors.origin.lava.LavaLeggings;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianBoots;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianChestplate;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianHelmet;
import net.thedragonteam.armorplus.armors.origin.obsidian.ObsidianLeggings;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneBoots;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneChestplate;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneHelmet;
import net.thedragonteam.armorplus.armors.origin.redstone.RedstoneLeggings;
import net.thedragonteam.armorplus.armors.reinforced.rcarmor.RCBoots;
import net.thedragonteam.armorplus.armors.reinforced.rcarmor.RCChestplate;
import net.thedragonteam.armorplus.armors.reinforced.rcarmor.RCHelmet;
import net.thedragonteam.armorplus.armors.reinforced.rcarmor.RCLeggings;
import net.thedragonteam.armorplus.armors.reinforced.rdarmor.RDBoots;
import net.thedragonteam.armorplus.armors.reinforced.rdarmor.RDChestplate;
import net.thedragonteam.armorplus.armors.reinforced.rdarmor.RDHelmet;
import net.thedragonteam.armorplus.armors.reinforced.rdarmor.RDLeggings;
import net.thedragonteam.armorplus.armors.reinforced.rgarmor.RGBoots;
import net.thedragonteam.armorplus.armors.reinforced.rgarmor.RGChestplate;
import net.thedragonteam.armorplus.armors.reinforced.rgarmor.RGHelmet;
import net.thedragonteam.armorplus.armors.reinforced.rgarmor.RGLeggings;
import net.thedragonteam.armorplus.armors.reinforced.riarmor.RIBoots;
import net.thedragonteam.armorplus.armors.reinforced.riarmor.RIChestplate;
import net.thedragonteam.armorplus.armors.reinforced.riarmor.RIHelmet;
import net.thedragonteam.armorplus.armors.reinforced.riarmor.RILeggings;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonBoots;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonChestplate;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonHelmet;
import net.thedragonteam.armorplus.armors.special.enderdragon.EnderDragonLeggings;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianBoots;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianChestplate;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianHelmet;
import net.thedragonteam.armorplus.armors.special.guardian.GuardianLeggings;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenBoots;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenChestplate;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenHelmet;
import net.thedragonteam.armorplus.armors.special.mob.chicken.ChickenLeggings;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeBoots;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeChestplate;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeHelmet;
import net.thedragonteam.armorplus.armors.special.mob.slime.SlimeLeggings;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarBoots;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarChestplate;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarHelmet;
import net.thedragonteam.armorplus.armors.special.superstar.SuperStarLeggings;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateBoots;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateChestplate;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateHelmet;
import net.thedragonteam.armorplus.armors.special.theultimate.TheUltimateLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeBoots;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.ardite.ArditeLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltBoots;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.cobalt.CobaltLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeBoots;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.knightslime.KnightSlimeLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynBoots;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.manyullyn.ManyullynLeggings;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronBoots;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronChestplate;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronHelmet;
import net.thedragonteam.armorplus.armors.tconstruct.pigiron.PigIronLeggings;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalBoots;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalChestplate;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalHelmet;
import net.thedragonteam.armorplus.armors.v2.electrical.ElectricalLeggings;
import net.thedragonteam.armorplus.armors.v2.steel.SteelBoots;
import net.thedragonteam.armorplus.armors.v2.steel.SteelChestplate;
import net.thedragonteam.armorplus.armors.v2.steel.SteelHelmet;
import net.thedragonteam.armorplus.armors.v2.steel.SteelLeggings;
import net.thedragonteam.armorplus.items.arrows.ItemCoalArrow;
import net.thedragonteam.armorplus.items.arrows.ItemLapisArrow;
import net.thedragonteam.armorplus.items.arrows.ItemLavaArrow;
import net.thedragonteam.armorplus.items.arrows.ItemRedstoneArrow;
import net.thedragonteam.armorplus.items.battleaxes.*;
import net.thedragonteam.armorplus.items.books.ArmorPlusBook;
import net.thedragonteam.armorplus.items.books.ArmorPlusInfoBook;
import net.thedragonteam.armorplus.items.bows.*;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.materials.*;
import net.thedragonteam.armorplus.items.swords.*;
import net.thedragonteam.armorplus.items.theultimate.*;
import net.thedragonteam.armorplus.util.NameUtil;

public class ModItems {
    /**
     * Swords
     * Float damageVsEntity + 4.0F
     * public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial("MATERIAL", int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability);
     */
    public static Item.ToolMaterial SWORD_COAL_MATERIAL = EnumHelper.addToolMaterial("SWORD_COAL_MATERIAL", 1, 59, 1.0F, 0.5F, 15);
    public static Item.ToolMaterial SWORD_LAPIS_MATERIAL = EnumHelper.addToolMaterial("SWORD_LAPIS_MATERIAL", 1, 250, 1.0F, 1.0F, 30);
    public static Item.ToolMaterial SWORD_REDSTONE_MATERIAL = EnumHelper.addToolMaterial("SWORD_REDSTONE_MATERIAL", 1, 200, 1.0F, 1.5F, 20);
    public static Item.ToolMaterial SWORD_EMERALD_MATERIAL = EnumHelper.addToolMaterial("SWORD_EMERALD_MATERIAL", 1, 1561, 1.0F, 3.5F, 20);
    public static Item.ToolMaterial SWORD_OBSIDIAN_MATERIAL = EnumHelper.addToolMaterial("SWORD_OBSIDIAN_MATERIAL", 1, 1500, 1.0F, 4.0F, 20);
    public static Item.ToolMaterial SWORD_LAVA_MATERIAL = EnumHelper.addToolMaterial("SWORD_LAVA_MATERIAL", 1, 1750, 1.0F, 4.5F, 20);
    public static Item.ToolMaterial SWORD_SUPER_STAR_MATERIAL = EnumHelper.addToolMaterial("SWORD_SUPER_STAR_MATERIAL", 1, 1750, 0.0F, 6.0F, 20);
    public static Item.ToolMaterial SWORD_GUARDIAN_MATERIAL = EnumHelper.addToolMaterial("SWORD_GUARDIAN_MATERIAL", 1, 500, 1.0F, 3.0F, 30);
    public static Item.ToolMaterial SWORD_ENDER_DRAGON_MATERIAL = EnumHelper.addToolMaterial("SWORD_ENDER_DRAGON_MATERIAL", 1, 2000, 1.0F, 8.0F, 20);
    public static Item.ToolMaterial BATTLE_AXE_COAL_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_COAL_MATERIAL", 1, 59, 1.0F, 2.5F, 15);
    public static Item.ToolMaterial BATTLE_AXE_LAPIS_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_LAPIS_MATERIAL", 1, 250, 1.0F, 3.0F, 30);
    public static Item.ToolMaterial BATTLE_AXE_REDSTONE_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_REDSTONE_MATERIAL", 1, 200, 1.0F, 3.5F, 20);
    public static Item.ToolMaterial BATTLE_AXE_EMERALD_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_EMERALD_MATERIAL", 1, 1561, 1.0F, 5.5F, 20);
    public static Item.ToolMaterial BATTLE_AXE_OBSIDIAN_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_OBSIDIAN_MATERIAL", 1, 1500, 1.0F, 6.0F, 20);
    public static Item.ToolMaterial BATTLE_AXE_LAVA_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_LAVA_MATERIAL", 1, 1750, 1.0F, 6.5F, 20);
    public static Item.ToolMaterial BATTLE_AXE_SUPER_STAR_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_SUPER_STAR_MATERIAL", 1, 1750, 0.0F, 8.0F, 20);
    public static Item.ToolMaterial BATTLE_AXE_GUARDIAN_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_GUARDIAN_MATERIAL", 1, 500, 1.0F, 5.0F, 30);
    public static Item.ToolMaterial BATTLE_AXE_ENDER_DRAGON_MATERIAL = EnumHelper.addToolMaterial("BATTLE_AXE_ENDER_DRAGON_MATERIAL", 1, 2000, 1.0F, 10.0F, 20);

    public static ItemArmor.ArmorMaterial DEV_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("DEVARMOR", ArmorPlus.MODID + ":" + "DevArmor", 100000, new int[]
            {100, 100, 100, 100}, 100, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 100.0F);

    public static ItemArmor.ArmorMaterial COAL_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("COALARMOR", ArmorPlus.MODID + ":" + "CoalArmor", 7, new int[]
            {1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial EMERALD_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("EMERALDARMOR", ArmorPlus.MODID + ":" + "EmeraldArmor", 35, new int[]
            {3, 6, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial LAPIS_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("LAPISARMOR", ArmorPlus.MODID + ":" + "LapisArmor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial LAVA_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("LAVAARMOR", ArmorPlus.MODID + ":" + "LavaArmor", 45, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial OBSIDIAN_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("OBSIDIANARMOR", ArmorPlus.MODID + ":" + "ObsidianArmor", 40, new int[]
            {3, 7, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

    public static ItemArmor.ArmorMaterial REDSTONE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("REDSTONEARMOR", ArmorPlus.MODID + ":" + "RedstoneArmor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial RC_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RCARMOR", ArmorPlus.MODID + ":" + "RCArmor", 18, new int[]
            {2, 5, 6, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);

    public static ItemArmor.ArmorMaterial RD_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RDARMOR", ArmorPlus.MODID + ":" + "RDArmor", 35, new int[]
            {4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial RG_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RGARMOR", ArmorPlus.MODID + ":" + "RGArmor", 10, new int[]
            {2, 4, 6, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial RI_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RIARMOR", ArmorPlus.MODID + ":" + "RIArmor", 18, new int[]
            {3, 6, 7, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static ItemArmor.ArmorMaterial STEEL_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("STEELARMOR", ArmorPlus.MODID + ":" + "SteelArmor", 15, new int[]
            {2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static ItemArmor.ArmorMaterial ELECTRICAL_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("ELECTRICALARMOR", ArmorPlus.MODID + ":" + "ElectricalArmor", 19, new int[]
            {3, 6, 7, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static ItemArmor.ArmorMaterial CHICKEN_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("CHICKENARMOR", ArmorPlus.MODID + ":" + "ChickenArmor", 3, new int[]
            {1, 1, 2, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial SLIME_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("SLIMEARMOR", ArmorPlus.MODID + ":" + "SlimeArmor", 3, new int[]
            {1, 1, 2, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial ENDER_DRAGON_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("ENDERDRAGONARMOR", ArmorPlus.MODID + ":" + "EnderDragonArmor", 60, new int[]
            {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial GUARDIAN_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("GUARDIANARMOR", ArmorPlus.MODID + ":" + "GuardianArmor", 50, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial SUPER_STAR_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("SUPERSTARARMOR", ArmorPlus.MODID + ":" + "SuperStarArmor", 50, new int[]
            {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial THE_ULTIMATE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("THEULTIMATEARMOR", ArmorPlus.MODID + ":" + "TheUltimateArmor", 160, new int[]
            {10, 20, 30, 15}, 1, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.0F);

    public static ItemArmor.ArmorMaterial ARDITE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("ARDITEARMOR", ArmorPlus.MODID + ":" + "ArditeArmor", 55, new int[]
            {4, 8, 10, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial COBALT_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("COBALTARMOR", ArmorPlus.MODID + ":" + "CobaltArmor", 44, new int[]
            {3, 7, 9, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial KNIGHT_SLIME_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("KNIGHTSLIMEARMOR", ArmorPlus.MODID + ":" + "KnightSlimeArmor", 33, new int[]
            {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);

    public static ItemArmor.ArmorMaterial MANYULLYN_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("MANYULLYNARMOR", ArmorPlus.MODID + ":" + "ManyullynArmor", 66, new int[]
            {5, 10, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);

    public static ItemArmor.ArmorMaterial PIG_IRON_MATERIAL = EnumHelper.addArmorMaterial("PIGIRONARMOR", ArmorPlus.MODID + ":" + "PigIronArmor", 33, new int[]
            {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);

    public static Chainmail CHAINMAIL;
    public static ReinforcingMaterial REINFORCING_MATERIAL;
    public static GuardianScale GUARDIAN_SCALE;
    public static WitherBone WITHER_BONE;
    public static EnderDragonScale ENDER_DRAGON_SCALE;
    public static TheUltimateMaterial THE_ULTIMATE_MATERIAL;
    public static LavaCrystal LAVA_CRYSTAL;
    public static TheGiftOfTheGods THE_GIFT_OF_THE_GODS;
    public static ArmorPlusBook ARMORPLUS_BOOK;
    public static SteelIngot STEEL_INGOT;
    public static ElectricalIngot ELECTRICAL_INGOT;
    public static RedstoneApple REDSTONE_APPLE;
    public static ArmorPlusInfoBook ARMORPLUS_INFO_BOOK;

    public static DevHelmet DEV_HELMET;
    public static DevChestplate DEV_CHESTPLATE;
    public static DevLeggings DEV_LEGGINGS;
    public static DevBoots DEV_BOOTS;

    public static CoalHelmet COAL_HELMET;
    public static CoalChestplate COAL_CHESTPLATE;
    public static CoalLeggings COAL_LEGGINGS;
    public static CoalBoots COAL_BOOTS;

    public static EmeraldHelmet EMERALD_HELMET;
    public static EmeraldChestplate EMERALD_CHESTPLATE;
    public static EmeraldLeggings EMERALD_LEGGINGS;
    public static EmeraldBoots EMERALD_BOOTS;

    public static LapisHelmet LAPIS_HELMET;
    public static LapisChestplate LAPIS_CHESTPLATE;
    public static LapisLeggings LAPIS_LEGGINGS;
    public static LapisBoots LAPIS_BOOTS;

    public static LavaHelmet LAVA_HELMET;
    public static LavaChestplate LAVA_CHESTPLATE;
    public static LavaLeggings LAVA_LEGGINGS;
    public static LavaBoots LAVA_BOOTS;

    public static ObsidianHelmet OBSIDIAN_HELMET;
    public static ObsidianChestplate OBSIDIAN_CHESTPLATE;
    public static ObsidianLeggings OBSIDIAN_LEGGINGS;
    public static ObsidianBoots OBSIDIAN_BOOTS;

    public static RedstoneHelmet REDSTONE_HELMET;
    public static RedstoneChestplate REDSTONE_CHESTPLATE;
    public static RedstoneLeggings REDSTONE_LEGGINGS;
    public static RedstoneBoots REDSTONE_BOOTS;

    public static RCHelmet RC_HELMET;
    public static RCChestplate RC_CHESTPLATE;
    public static RCLeggings RC_LEGGINGS;
    public static RCBoots RC_BOOTS;

    public static RDHelmet RD_HELMET;
    public static RDChestplate RD_CHESTPLATE;
    public static RDLeggings RD_LEGGINGS;
    public static RDBoots RD_BOOTS;

    public static RGHelmet RG_HELMET;
    public static RGChestplate RG_CHESTPLATE;
    public static RGLeggings RG_LEGGINGS;
    public static RGBoots RG_BOOTS;

    public static RIHelmet RI_HELMET;
    public static RIChestplate RI_CHESTPLATE;
    public static RILeggings RI_LEGGINGS;
    public static RIBoots RI_BOOTS;

    public static SteelHelmet STEEL_HELMET;
    public static SteelChestplate STEEL_CHESTPLATE;
    public static SteelLeggings STEEL_LEGGINGS;
    public static SteelBoots STEEL_BOOTS;

    public static ElectricalHelmet ELECTRICAL_HELMET;
    public static ElectricalChestplate ELECTRICAL_CHESTPLATE;
    public static ElectricalLeggings ELECTRICAL_LEGGINGS;
    public static ElectricalBoots ELECTRICAL_BOOTS;

    public static ChickenHelmet CHICKEN_HELMET;
    public static ChickenChestplate CHICKEN_CHESTPLATE;
    public static ChickenLeggings CHICKEN_LEGGINGS;
    public static ChickenBoots CHICKEN_BOOTS;

    public static SlimeHelmet SLIME_HELMET;
    public static SlimeChestplate SLIME_CHESTPLATE;
    public static SlimeLeggings SLIME_LEGGINGS;
    public static SlimeBoots SLIME_BOOTS;

    public static EnderDragonHelmet ENDER_DRAGON_HELMET;
    public static EnderDragonChestplate ENDER_DRAGON_CHESTPLATE;
    public static EnderDragonLeggings ENDER_DRAGON_LEGGINGS;
    public static EnderDragonBoots ENDER_DRAGON_BOOTS;

    public static GuardianHelmet GUARDIAN_HELMET;
    public static GuardianChestplate GUARDIAN_CHESTPLATE;
    public static GuardianLeggings GUARDIAN_LEGGINGS;
    public static GuardianBoots GUARDIAN_BOOTS;

    public static SuperStarHelmet SUPER_STAR_HELMET;
    public static SuperStarChestplate SUPER_STAR_CHESTPLATE;
    public static SuperStarLeggings SUPER_STAR_LEGGINGS;
    public static SuperStarBoots SUPER_STAR_BOOTS;

    public static TheUltimateHelmet THE_ULTIMATE_HELMET;
    public static TheUltimateChestplate THE_ULTIMATE_CHESTPLATE;
    public static TheUltimateLeggings THE_ULTIMATE_LEGGINGS;
    public static TheUltimateBoots THE_ULTIMATE_BOOTS;

    public static ArditeHelmet ARDITE_HELMET;
    public static ArditeChestplate ARDITE_CHESTPLATE;
    public static ArditeLeggings ARDITE_LEGGINGS;
    public static ArditeBoots ARDITE_BOOTS;

    public static CobaltHelmet COBALT_HELMET;
    public static CobaltChestplate COBALT_CHESTPLATE;
    public static CobaltLeggings COBALT_LEGGINGS;
    public static CobaltBoots COBALT_BOOTS;

    public static ManyullynHelmet MANYULLYN_HELMET;
    public static ManyullynChestplate MANYULLYN_CHESTPLATE;
    public static ManyullynLeggings MANYULLYN_LEGGINGS;
    public static ManyullynBoots MANYULLYN_BOOTS;

    public static PigIronHelmet PIG_IRON_HELMET;
    public static PigIronChestplate PIG_IRON_CHESTPLATE;
    public static PigIronLeggings PIG_IRON_LEGGINGS;
    public static PigIronBoots PIG_IRON_BOOTS;

    public static KnightSlimeHelmet KNIGHT_SLIME_HELMET;
    public static KnightSlimeChestplate KNIGHT_SLIME_CHESTPLATE;
    public static KnightSlimeLeggings KNIGHT_SLIME_LEGGINGS;
    public static KnightSlimeBoots KNIGHT_SLIME_BOOTS;

    public static CoalSword COAL_SWORD;
    public static LapisSword LAPIS_SWORD;
    public static RedstoneSword REDSTONE_SWORD;
    public static EmeraldSword EMERALD_SWORD;
    public static ObsidianSword OBSIDIAN_SWORD;
    public static LavaSword LAVA_SWORD;
    public static SuperStarSword SUPER_STAR_SWORD;
    public static GuardianSword GUARDIAN_SWORD;
    public static EnderDragonSword ENDER_DRAGON_SWORD;

    public static CoalBattleAxe COAL_BATTLE_AXE;
    public static LapisBattleAxe LAPIS_BATTLE_AXE;
    public static RedstoneBattleAxe REDSTONE_BATTLE_AXE;
    public static EmeraldBattleAxe EMERALD_BATTLE_AXE;
    public static ObsidianBattleAxe OBSIDIAN_BATTLE_AXE;
    public static LavaBattleAxe LAVA_BATTLE_AXE;
    public static SuperStarBattleAxe SUPER_STAR_BATTLE_AXE;
    public static GuardianBattleAxe GUARDIAN_BATTLE_AXE;
    public static EnderDragonBattleAxe ENDER_DRAGON_BATTLE_AXE;

    public static CoalBow COAL_BOW;
    public static LapisBow LAPIS_BOW;
    public static RedstoneBow REDSTONE_BOW;
    public static EmeraldBow EMERALD_BOW;
    public static ObsidianBow OBSIDIAN_BOW;
    public static LavaBow LAVA_BOW;
    public static SuperStarBow SUPER_STAR_BOW;
    public static GuardianBow GUARDIAN_BOW;
    public static EnderDragonBow ENDER_DRAGON_BOW;

    public static TheUltimateHelmetLeft THE_ULTIMATE_HELMET_LEFT;
    public static TheUltimateHelmetMiddle THE_ULTIMATE_HELMET_MIDDLE;
    public static TheUltimateHelmetRight THE_ULTIMATE_HELMET_RIGHT;
    public static TheUltimateChestplateLeft THE_ULTIMATE_CHESTPLATE_LEFT;
    public static TheUltimateChestplateMiddle THE_ULTIMATE_CHESTPLATE_MIDDLE;
    public static TheUltimateChestplateRight THE_ULTIMATE_CHESTPLATE_RIGHT;
    public static TheUltimateLeggingsLeft THE_ULTIMATE_LEGGINGS_LEFT;
    public static TheUltimateLeggingsMiddle THE_ULTIMATE_LEGGINGS_MIDDLE;
    public static TheUltimateLeggingsRight THE_ULTIMATE_LEGGINGS_RIGHT;
    public static TheUltimateBootsLeft THE_ULTIMATE_BOOTS_LEFT;
    public static TheUltimateBootsMiddle THE_ULTIMATE_BOOTS_MIDDLE;
    public static TheUltimateBootsRight THE_ULTIMATE_BOOTS_RIGHT;

    public static DevTool DEV_TOOL;

    public static Item COAL_ARROW, LAPIS_ARROW, REDSTONE_ARROW, LAVA_ARROW;

    public ModItems() {
        register();
    }

    public static void init() {
        CHAINMAIL = new Chainmail();
        REINFORCING_MATERIAL = new ReinforcingMaterial();
        GUARDIAN_SCALE = new GuardianScale();
        WITHER_BONE = new WitherBone();
        ENDER_DRAGON_SCALE = new EnderDragonScale();
        THE_ULTIMATE_MATERIAL = new TheUltimateMaterial();
        LAVA_CRYSTAL = new LavaCrystal();
        THE_GIFT_OF_THE_GODS = new TheGiftOfTheGods();
        ARMORPLUS_BOOK = new ArmorPlusBook();
        STEEL_INGOT = new SteelIngot();
        ELECTRICAL_INGOT = new ElectricalIngot();
        REDSTONE_APPLE = new RedstoneApple(4, 2.0f, false);
        ARMORPLUS_INFO_BOOK = new ArmorPlusInfoBook();

        DEV_HELMET = new DevHelmet();
        DEV_CHESTPLATE = new DevChestplate();
        DEV_LEGGINGS = new DevLeggings();
        DEV_BOOTS = new DevBoots();

        COAL_HELMET = new CoalHelmet();
        COAL_CHESTPLATE = new CoalChestplate();
        COAL_LEGGINGS = new CoalLeggings();
        COAL_BOOTS = new CoalBoots();

        EMERALD_HELMET = new EmeraldHelmet();
        EMERALD_CHESTPLATE = new EmeraldChestplate();
        EMERALD_LEGGINGS = new EmeraldLeggings();
        EMERALD_BOOTS = new EmeraldBoots();

        LAPIS_HELMET = new LapisHelmet();
        LAPIS_CHESTPLATE = new LapisChestplate();
        LAPIS_LEGGINGS = new LapisLeggings();
        LAPIS_BOOTS = new LapisBoots();

        LAVA_HELMET = new LavaHelmet();
        LAVA_CHESTPLATE = new LavaChestplate();
        LAVA_LEGGINGS = new LavaLeggings();
        LAVA_BOOTS = new LavaBoots();

        OBSIDIAN_HELMET = new ObsidianHelmet();
        OBSIDIAN_CHESTPLATE = new ObsidianChestplate();
        OBSIDIAN_LEGGINGS = new ObsidianLeggings();
        OBSIDIAN_BOOTS = new ObsidianBoots();

        REDSTONE_HELMET = new RedstoneHelmet();
        REDSTONE_CHESTPLATE = new RedstoneChestplate();
        REDSTONE_LEGGINGS = new RedstoneLeggings();
        REDSTONE_BOOTS = new RedstoneBoots();

        RC_HELMET = new RCHelmet();
        RC_CHESTPLATE = new RCChestplate();
        RC_LEGGINGS = new RCLeggings();
        RC_BOOTS = new RCBoots();

        RD_HELMET = new RDHelmet();
        RD_CHESTPLATE = new RDChestplate();
        RD_LEGGINGS = new RDLeggings();
        RD_BOOTS = new RDBoots();

        RG_HELMET = new RGHelmet();
        RG_CHESTPLATE = new RGChestplate();
        RG_LEGGINGS = new RGLeggings();
        RG_BOOTS = new RGBoots();

        RI_HELMET = new RIHelmet();
        RI_CHESTPLATE = new RIChestplate();
        RI_LEGGINGS = new RILeggings();
        RI_BOOTS = new RIBoots();

        STEEL_HELMET = new SteelHelmet();
        STEEL_CHESTPLATE = new SteelChestplate();
        STEEL_LEGGINGS = new SteelLeggings();
        STEEL_BOOTS = new SteelBoots();

        ELECTRICAL_HELMET = new ElectricalHelmet();
        ELECTRICAL_CHESTPLATE = new ElectricalChestplate();
        ELECTRICAL_LEGGINGS = new ElectricalLeggings();
        ELECTRICAL_BOOTS = new ElectricalBoots();

        CHICKEN_HELMET = new ChickenHelmet();
        CHICKEN_CHESTPLATE = new ChickenChestplate();
        CHICKEN_LEGGINGS = new ChickenLeggings();
        CHICKEN_BOOTS = new ChickenBoots();

        SLIME_HELMET = new SlimeHelmet();
        SLIME_CHESTPLATE = new SlimeChestplate();
        SLIME_LEGGINGS = new SlimeLeggings();
        SLIME_BOOTS = new SlimeBoots();

        ENDER_DRAGON_HELMET = new EnderDragonHelmet();
        ENDER_DRAGON_CHESTPLATE = new EnderDragonChestplate();
        ENDER_DRAGON_LEGGINGS = new EnderDragonLeggings();
        ENDER_DRAGON_BOOTS = new EnderDragonBoots();

        GUARDIAN_HELMET = new GuardianHelmet();
        GUARDIAN_CHESTPLATE = new GuardianChestplate();
        GUARDIAN_LEGGINGS = new GuardianLeggings();
        GUARDIAN_BOOTS = new GuardianBoots();

        SUPER_STAR_HELMET = new SuperStarHelmet();
        SUPER_STAR_CHESTPLATE = new SuperStarChestplate();
        SUPER_STAR_LEGGINGS = new SuperStarLeggings();
        SUPER_STAR_BOOTS = new SuperStarBoots();

        THE_ULTIMATE_HELMET = new TheUltimateHelmet();
        THE_ULTIMATE_CHESTPLATE = new TheUltimateChestplate();
        THE_ULTIMATE_LEGGINGS = new TheUltimateLeggings();
        THE_ULTIMATE_BOOTS = new TheUltimateBoots();

        ARDITE_HELMET = new ArditeHelmet();
        ARDITE_CHESTPLATE = new ArditeChestplate();
        ARDITE_LEGGINGS = new ArditeLeggings();
        ARDITE_BOOTS = new ArditeBoots();

        COBALT_HELMET = new CobaltHelmet();
        COBALT_CHESTPLATE = new CobaltChestplate();
        COBALT_LEGGINGS = new CobaltLeggings();
        COBALT_BOOTS = new CobaltBoots();

        MANYULLYN_HELMET = new ManyullynHelmet();
        MANYULLYN_CHESTPLATE = new ManyullynChestplate();
        MANYULLYN_LEGGINGS = new ManyullynLeggings();
        MANYULLYN_BOOTS = new ManyullynBoots();

        PIG_IRON_HELMET = new PigIronHelmet();
        PIG_IRON_CHESTPLATE = new PigIronChestplate();
        PIG_IRON_LEGGINGS = new PigIronLeggings();
        PIG_IRON_BOOTS = new PigIronBoots();

        KNIGHT_SLIME_HELMET = new KnightSlimeHelmet();
        KNIGHT_SLIME_CHESTPLATE = new KnightSlimeChestplate();
        KNIGHT_SLIME_LEGGINGS = new KnightSlimeLeggings();
        KNIGHT_SLIME_BOOTS = new KnightSlimeBoots();

        COAL_SWORD = new CoalSword(SWORD_COAL_MATERIAL);
        LAPIS_SWORD = new LapisSword(SWORD_LAPIS_MATERIAL);
        REDSTONE_SWORD = new RedstoneSword(SWORD_REDSTONE_MATERIAL);
        EMERALD_SWORD = new EmeraldSword(SWORD_EMERALD_MATERIAL);
        OBSIDIAN_SWORD = new ObsidianSword(SWORD_OBSIDIAN_MATERIAL);
        LAVA_SWORD = new LavaSword(SWORD_LAVA_MATERIAL);
        SUPER_STAR_SWORD = new SuperStarSword(SWORD_SUPER_STAR_MATERIAL);
        GUARDIAN_SWORD = new GuardianSword(SWORD_GUARDIAN_MATERIAL);
        ENDER_DRAGON_SWORD = new EnderDragonSword(SWORD_ENDER_DRAGON_MATERIAL);
        COAL_BATTLE_AXE = new CoalBattleAxe(BATTLE_AXE_COAL_MATERIAL);
        LAPIS_BATTLE_AXE = new LapisBattleAxe(BATTLE_AXE_LAPIS_MATERIAL);
        REDSTONE_BATTLE_AXE = new RedstoneBattleAxe(BATTLE_AXE_REDSTONE_MATERIAL);
        EMERALD_BATTLE_AXE = new EmeraldBattleAxe(BATTLE_AXE_EMERALD_MATERIAL);
        OBSIDIAN_BATTLE_AXE = new ObsidianBattleAxe(BATTLE_AXE_OBSIDIAN_MATERIAL);
        LAVA_BATTLE_AXE = new LavaBattleAxe(BATTLE_AXE_LAVA_MATERIAL);
        SUPER_STAR_BATTLE_AXE = new SuperStarBattleAxe(BATTLE_AXE_SUPER_STAR_MATERIAL);
        GUARDIAN_BATTLE_AXE = new GuardianBattleAxe(BATTLE_AXE_GUARDIAN_MATERIAL);
        ENDER_DRAGON_BATTLE_AXE = new EnderDragonBattleAxe(BATTLE_AXE_ENDER_DRAGON_MATERIAL);
        COAL_BOW = new CoalBow();
        LAPIS_BOW = new LapisBow();
        REDSTONE_BOW = new RedstoneBow();
        EMERALD_BOW = new EmeraldBow();
        OBSIDIAN_BOW = new ObsidianBow();
        LAVA_BOW = new LavaBow();
        SUPER_STAR_BOW = new SuperStarBow();
        GUARDIAN_BOW = new GuardianBow();
        ENDER_DRAGON_BOW = new EnderDragonBow();

        THE_ULTIMATE_HELMET_LEFT = new TheUltimateHelmetLeft();
        THE_ULTIMATE_HELMET_MIDDLE = new TheUltimateHelmetMiddle();
        THE_ULTIMATE_HELMET_RIGHT = new TheUltimateHelmetRight();
        THE_ULTIMATE_CHESTPLATE_LEFT = new TheUltimateChestplateLeft();
        THE_ULTIMATE_CHESTPLATE_MIDDLE = new TheUltimateChestplateMiddle();
        THE_ULTIMATE_CHESTPLATE_RIGHT = new TheUltimateChestplateRight();
        THE_ULTIMATE_LEGGINGS_LEFT = new TheUltimateLeggingsLeft();
        THE_ULTIMATE_LEGGINGS_MIDDLE = new TheUltimateLeggingsMiddle();
        THE_ULTIMATE_LEGGINGS_RIGHT = new TheUltimateLeggingsRight();
        THE_ULTIMATE_BOOTS_LEFT = new TheUltimateBootsLeft();
        THE_ULTIMATE_BOOTS_MIDDLE = new TheUltimateBootsMiddle();
        THE_ULTIMATE_BOOTS_RIGHT = new TheUltimateBootsRight();

        DEV_TOOL = new DevTool();

        COAL_ARROW = new ItemCoalArrow().setCreativeTab(ArmorPlus.TAB_ARMORPLUS_WEAPONS);
        NameUtil.setNames(COAL_ARROW, "coal_arrow");
        LAPIS_ARROW = new ItemLapisArrow().setCreativeTab(ArmorPlus.TAB_ARMORPLUS_WEAPONS);
        NameUtil.setNames(LAPIS_ARROW, "lapis_arrow");
        REDSTONE_ARROW = new ItemRedstoneArrow().setCreativeTab(ArmorPlus.TAB_ARMORPLUS_WEAPONS);
        NameUtil.setNames(REDSTONE_ARROW, "redstone_arrow");
        LAVA_ARROW = new ItemLavaArrow().setCreativeTab(ArmorPlus.TAB_ARMORPLUS_WEAPONS);
        NameUtil.setNames(LAVA_ARROW, "lava_arrow");
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        CHAINMAIL.initModel();
        REINFORCING_MATERIAL.initModel();
        GUARDIAN_SCALE.initModel();
        WITHER_BONE.initModel();
        ENDER_DRAGON_SCALE.initModel();
        THE_ULTIMATE_MATERIAL.initModel();
        LAVA_CRYSTAL.initModel();
        THE_GIFT_OF_THE_GODS.initModel();
        ARMORPLUS_BOOK.initModel();
        STEEL_INGOT.initModel();
        ELECTRICAL_INGOT.initModel();
        REDSTONE_APPLE.initModel();
        ARMORPLUS_INFO_BOOK.initModel();

        DEV_HELMET.initModel();
        DEV_CHESTPLATE.initModel();
        DEV_LEGGINGS.initModel();
        DEV_BOOTS.initModel();

        COAL_HELMET.initModel();
        COAL_CHESTPLATE.initModel();
        COAL_LEGGINGS.initModel();
        COAL_BOOTS.initModel();

        EMERALD_HELMET.initModel();
        EMERALD_CHESTPLATE.initModel();
        EMERALD_LEGGINGS.initModel();
        EMERALD_BOOTS.initModel();

        LAPIS_HELMET.initModel();
        LAPIS_CHESTPLATE.initModel();
        LAPIS_LEGGINGS.initModel();
        LAPIS_BOOTS.initModel();

        LAVA_HELMET.initModel();
        LAVA_CHESTPLATE.initModel();
        LAVA_LEGGINGS.initModel();
        LAVA_BOOTS.initModel();

        OBSIDIAN_HELMET.initModel();
        OBSIDIAN_CHESTPLATE.initModel();
        OBSIDIAN_LEGGINGS.initModel();
        OBSIDIAN_BOOTS.initModel();

        REDSTONE_HELMET.initModel();
        REDSTONE_CHESTPLATE.initModel();
        REDSTONE_LEGGINGS.initModel();
        REDSTONE_BOOTS.initModel();

        RC_HELMET.initModel();
        RC_CHESTPLATE.initModel();
        RC_LEGGINGS.initModel();
        RC_BOOTS.initModel();

        RD_HELMET.initModel();
        RD_CHESTPLATE.initModel();
        RD_LEGGINGS.initModel();
        RD_BOOTS.initModel();

        RG_HELMET.initModel();
        RG_CHESTPLATE.initModel();
        RG_LEGGINGS.initModel();
        RG_BOOTS.initModel();

        RI_HELMET.initModel();
        RI_CHESTPLATE.initModel();
        RI_LEGGINGS.initModel();
        RI_BOOTS.initModel();

        STEEL_HELMET.initModel();
        STEEL_CHESTPLATE.initModel();
        STEEL_LEGGINGS.initModel();
        STEEL_BOOTS.initModel();

        ELECTRICAL_HELMET.initModel();
        ELECTRICAL_CHESTPLATE.initModel();
        ELECTRICAL_LEGGINGS.initModel();
        ELECTRICAL_BOOTS.initModel();

        CHICKEN_HELMET.initModel();
        CHICKEN_CHESTPLATE.initModel();
        CHICKEN_LEGGINGS.initModel();
        CHICKEN_BOOTS.initModel();

        SLIME_HELMET.initModel();
        SLIME_CHESTPLATE.initModel();
        SLIME_LEGGINGS.initModel();
        SLIME_BOOTS.initModel();

        ENDER_DRAGON_HELMET.initModel();
        ENDER_DRAGON_CHESTPLATE.initModel();
        ENDER_DRAGON_LEGGINGS.initModel();
        ENDER_DRAGON_BOOTS.initModel();

        GUARDIAN_HELMET.initModel();
        GUARDIAN_CHESTPLATE.initModel();
        GUARDIAN_LEGGINGS.initModel();
        GUARDIAN_BOOTS.initModel();

        SUPER_STAR_HELMET.initModel();
        SUPER_STAR_CHESTPLATE.initModel();
        SUPER_STAR_LEGGINGS.initModel();
        SUPER_STAR_BOOTS.initModel();

        THE_ULTIMATE_HELMET.initModel();
        THE_ULTIMATE_CHESTPLATE.initModel();
        THE_ULTIMATE_LEGGINGS.initModel();
        THE_ULTIMATE_BOOTS.initModel();

        ARDITE_HELMET.initModel();
        ARDITE_CHESTPLATE.initModel();
        ARDITE_LEGGINGS.initModel();
        ARDITE_BOOTS.initModel();

        COBALT_HELMET.initModel();
        COBALT_CHESTPLATE.initModel();
        COBALT_LEGGINGS.initModel();
        COBALT_BOOTS.initModel();

        MANYULLYN_HELMET.initModel();
        MANYULLYN_CHESTPLATE.initModel();
        MANYULLYN_LEGGINGS.initModel();
        MANYULLYN_BOOTS.initModel();

        PIG_IRON_HELMET.initModel();
        PIG_IRON_CHESTPLATE.initModel();
        PIG_IRON_LEGGINGS.initModel();
        PIG_IRON_BOOTS.initModel();

        KNIGHT_SLIME_HELMET.initModel();
        KNIGHT_SLIME_CHESTPLATE.initModel();
        KNIGHT_SLIME_LEGGINGS.initModel();
        KNIGHT_SLIME_BOOTS.initModel();

        COAL_SWORD.initModel();
        LAPIS_SWORD.initModel();
        REDSTONE_SWORD.initModel();
        EMERALD_SWORD.initModel();
        OBSIDIAN_SWORD.initModel();
        LAVA_SWORD.initModel();
        SUPER_STAR_SWORD.initModel();
        GUARDIAN_SWORD.initModel();
        ENDER_DRAGON_SWORD.initModel();
        COAL_BATTLE_AXE.initModel();
        LAPIS_BATTLE_AXE.initModel();
        REDSTONE_BATTLE_AXE.initModel();
        EMERALD_BATTLE_AXE.initModel();
        OBSIDIAN_BATTLE_AXE.initModel();
        LAVA_BATTLE_AXE.initModel();
        SUPER_STAR_BATTLE_AXE.initModel();
        GUARDIAN_BATTLE_AXE.initModel();
        ENDER_DRAGON_BATTLE_AXE.initModel();
        COAL_BOW.initModel();
        LAPIS_BOW.initModel();
        REDSTONE_BOW.initModel();
        EMERALD_BOW.initModel();
        OBSIDIAN_BOW.initModel();
        LAVA_BOW.initModel();
        SUPER_STAR_BOW.initModel();
        GUARDIAN_BOW.initModel();
        ENDER_DRAGON_BOW.initModel();

        THE_ULTIMATE_HELMET_LEFT.initModel();
        THE_ULTIMATE_HELMET_MIDDLE.initModel();
        THE_ULTIMATE_HELMET_RIGHT.initModel();
        THE_ULTIMATE_CHESTPLATE_LEFT.initModel();
        THE_ULTIMATE_CHESTPLATE_MIDDLE.initModel();
        THE_ULTIMATE_CHESTPLATE_RIGHT.initModel();
        THE_ULTIMATE_LEGGINGS_LEFT.initModel();
        THE_ULTIMATE_LEGGINGS_MIDDLE.initModel();
        THE_ULTIMATE_LEGGINGS_RIGHT.initModel();
        THE_ULTIMATE_BOOTS_LEFT.initModel();
        THE_ULTIMATE_BOOTS_MIDDLE.initModel();
        THE_ULTIMATE_BOOTS_RIGHT.initModel();

        DEV_TOOL.initModel();
    }

    private void register() {
        registerItem(COAL_ARROW);
        registerItem(LAPIS_ARROW);
        registerItem(REDSTONE_ARROW);
        registerItem(LAVA_ARROW);
    }

    private void registerItem(Item item) {
        GameRegistry.register(item);
    }

}
