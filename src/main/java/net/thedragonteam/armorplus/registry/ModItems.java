/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.armors.base.BaseElectricalArmor;
import net.thedragonteam.armorplus.armors.base.BaseSteelArmor;
import net.thedragonteam.armorplus.armors.base.BaseUltimateArmor;
import net.thedragonteam.armorplus.items.*;
import net.thedragonteam.armorplus.items.arrows.ItemCoalArrow;
import net.thedragonteam.armorplus.items.arrows.ItemLapisArrow;
import net.thedragonteam.armorplus.items.arrows.ItemLavaArrow;
import net.thedragonteam.armorplus.items.arrows.ItemRedstoneArrow;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.baubles.ItemBaubleDragon;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.dev.GuiTester;
import net.thedragonteam.armorplus.items.dev.NBTItem;
import net.thedragonteam.armorplus.items.energy.rf.*;
import net.thedragonteam.armorplus.items.energy.tesla.*;
import net.thedragonteam.armorplus.items.materials.LavaCrystal;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.getArmorPlusLocation;
import static net.thedragonteam.armorplus.armors.ARPArmorMaterial.*;
import static net.thedragonteam.armorplus.items.UltimateItems.*;

public class ModItems {

    /**
     * Swords
     * Float damageVsEntity + 4.0F
     * public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial("MATERIAL", int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability);
     */
    public static Item.ToolMaterial swordCoalMaterial = EnumHelper.addToolMaterial("swordCoalMaterial", 1, 59, 1.0F, 0.5F, 15);
    public static Item.ToolMaterial swordLapisMaterial = EnumHelper.addToolMaterial("swordLapisMaterial", 1, 250, 1.0F, 1.0F, 30);
    public static Item.ToolMaterial swordRedstoneMaterial = EnumHelper.addToolMaterial("swordRedstoneMaterial", 1, 200, 1.0F, 1.5F, 20);
    public static Item.ToolMaterial swordEmeraldMaterial = EnumHelper.addToolMaterial("swordEmeraldMaterial", 1, 1561, 1.0F, 3.5F, 20);
    public static Item.ToolMaterial swordObsidianMaterial = EnumHelper.addToolMaterial("swordObsidianMaterial", 1, 1500, 1.0F, 4.0F, 20);
    public static Item.ToolMaterial swordLavaMaterial = EnumHelper.addToolMaterial("swordLavaMaterial", 1, 1750, 1.0F, 4.5F, 20);
    public static Item.ToolMaterial swordSuperStarMaterial = EnumHelper.addToolMaterial("swordSuperStarMaterial", 1, 1750, 0.0F, 6.0F, 20);
    public static Item.ToolMaterial swordGuardianMaterial = EnumHelper.addToolMaterial("swordGuardianMaterial", 1, 500, 1.0F, 3.0F, 30);
    public static Item.ToolMaterial swordEnderDragonMaterial = EnumHelper.addToolMaterial("swordEnderDragonMaterial", 1, 2000, 1.0F, 8.0F, 20);
    public static Item.ToolMaterial battleAxeCoalMaterial = EnumHelper.addToolMaterial("battleAxeCoalMaterial", 1, 59, 1.0F, 2.5F, 15);
    public static Item.ToolMaterial battleAxeLapisMaterial = EnumHelper.addToolMaterial("battleAxeLapisMaterial", 1, 250, 1.0F, 3.0F, 30);
    public static Item.ToolMaterial battleAxeRedstoneMaterial = EnumHelper.addToolMaterial("battleAxeRedstoneMaterial", 1, 200, 1.0F, 3.5F, 20);
    public static Item.ToolMaterial battleAxeEmeraldMaterial = EnumHelper.addToolMaterial("battleAxeEmeraldMaterial", 1, 1561, 1.0F, 5.5F, 20);
    public static Item.ToolMaterial battleAxeObsidianMaterial = EnumHelper.addToolMaterial("battleAxeObsidianMaterial", 1, 1500, 1.0F, 6.0F, 20);
    public static Item.ToolMaterial battleAxeLavaMaterial = EnumHelper.addToolMaterial("battleAxeLavaMaterial", 1, 1750, 1.0F, 6.5F, 20);
    public static Item.ToolMaterial battleAxeSuperStarMaterial = EnumHelper.addToolMaterial("battleAxeSuperStarMaterial", 1, 1750, 0.0F, 8.0F, 20);
    public static Item.ToolMaterial battleAxeGuardianMaterial = EnumHelper.addToolMaterial("battleAxeGuardianMaterial", 1, 500, 1.0F, 5.0F, 30);
    public static Item.ToolMaterial battleAxeEnderDragonMaterial = EnumHelper.addToolMaterial("battleAxeEnderDragonMaterial", 1, 2000, 1.0F, 10.0F, 20);
    public static ItemArmor.ArmorMaterial coalArmor = EnumHelper.addArmorMaterial("coalArmor", getArmorPlusLocation() + "coal_armor", 7, new int[]
            {1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static ItemArmor.ArmorMaterial emeraldArmor = EnumHelper.addArmorMaterial("emeraldArmor", getArmorPlusLocation() + "emerald_armor", 35, new int[]
            {3, 6, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial lapisArmor = EnumHelper.addArmorMaterial("lapisArmor", getArmorPlusLocation() + "lapis_armor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
    public static ItemArmor.ArmorMaterial lavaArmor = EnumHelper.addArmorMaterial("lavaArmor", getArmorPlusLocation() + "lava_armor", 45, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial obsidianArmor = EnumHelper.addArmorMaterial("obsidianArmor", getArmorPlusLocation() + "obsidian_armor", 40, new int[]
            {3, 7, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
    public static ItemArmor.ArmorMaterial redstoneArmor = EnumHelper.addArmorMaterial("redstoneArmor", getArmorPlusLocation() + "redstone_armor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
    /* Steel Armor Not Powered */
    public static ItemArmor.ArmorMaterial steelArmorNotPowered = EnumHelper.addArmorMaterial("steelArmor", getArmorPlusLocation() + "steel_armor", 15, new int[]
            {2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    /* Electrical Armor Not Powered */
    public static ItemArmor.ArmorMaterial electricalArmor = EnumHelper.addArmorMaterial("electricalArmor", getArmorPlusLocation() + "electrical_armor", 19, new int[]
            {3, 6, 7, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static ItemArmor.ArmorMaterial chickenArmor = EnumHelper.addArmorMaterial("chickenArmor", getArmorPlusLocation() + "chicken_armor", 3, new int[]
            {1, 1, 2, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static ItemArmor.ArmorMaterial slimeArmor = EnumHelper.addArmorMaterial("slimeArmor", getArmorPlusLocation() + "slime_armor", 3, new int[]
            {1, 1, 2, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static ItemArmor.ArmorMaterial enderDragonArmor = EnumHelper.addArmorMaterial("enderDragonArmor", getArmorPlusLocation() + "ender_dragon_armor", 60, new int[]
            {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial guardianArmor = EnumHelper.addArmorMaterial("guardianArmor", getArmorPlusLocation() + "guardian_armor", 50, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial superStarArmor = EnumHelper.addArmorMaterial("superStarArmor", getArmorPlusLocation() + "super_star_armor", 50, new int[]
            {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial theUltimateArmor = EnumHelper.addArmorMaterial("theUltimateArmor", getArmorPlusLocation() + "the_ultimate_armor", 160, new int[]
            {10, 20, 30, 15}, 1, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.0F);
    public static ItemArmor.ArmorMaterial arditeArmor = EnumHelper.addArmorMaterial("arditeArmor", getArmorPlusLocation() + "ardite_armor", 55, new int[]
            {4, 8, 10, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial cobaltArmor = EnumHelper.addArmorMaterial("cobaltArmor", getArmorPlusLocation() + "cobalt_armor", 44, new int[]
            {3, 7, 9, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static ItemArmor.ArmorMaterial knightSlimeArmor = EnumHelper.addArmorMaterial("knightSlimeArmor", getArmorPlusLocation() + "knight_slime_armor", 33, new int[]
            {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
    public static ItemArmor.ArmorMaterial manyullynArmor = EnumHelper.addArmorMaterial("manyullynArmor", getArmorPlusLocation() + "manyullyn_armor", 66, new int[]
            {5, 10, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
    public static ItemArmor.ArmorMaterial pigIronArmor = EnumHelper.addArmorMaterial("pigIronArmor", getArmorPlusLocation() + "pig_iron_armor", 33, new int[]
            {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);

    public static BaseItem chainmail, guardianScale, witherBone, enderDragonScale, theUltimateMaterial, armorPlusBook, steelIngot, electricalIngot, armorPlusInfoBook;
    public static RedstoneApple redstoneApple;
    public static LavaCrystal lavaCrystal;
    public static TheGiftOfTheGods theGiftOfTheGods;
    public static NBTItem nbtItem;
    public static BaseArmor coalHelmet, coalChestplate, coalLeggings, coalBoots,
            emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots,
            obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots,
            redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots,
            lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots,
            lavaBoots, lavaHelmet, lavaChestplate, lavaLeggings,
            chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots,
            slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots,
            enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots,
            guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots,
            superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots,
            arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots,
            cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots,
            manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots,
            pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots,
            knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots;
    public static BaseSteelArmor steelHelmet, steelChestplate, steelLeggings, steelBoots;
    public static BaseElectricalArmor electricalHelmet, electricalChestplate, electricalLeggings, electricalBoots;
    public static BaseUltimateArmor theUltimateHelmet, theUltimateChestplate, theUltimateLeggings, theUltimateBoots;
    public static BaseSpecialSword coalSword, lapisSword, redstoneSword, emeraldSword, obsidianSword, lavaSword, superStarSword, guardianSword, enderDragonSword;
    public static BaseBattleAxe coalBattleAxe, lapisBattleAxe, redstoneBattleAxe, emeraldBattleAxe, obsidianBattleAxe, lavaBattleAxe, superStarBattleAxe, guardianBattleAxe, enderDragonBattleAxe;
    public static BaseBow coalBow, lapisBow, redstoneBow, emeraldBow, obsidianBow, lavaBow, superStarBow, guardianBow, enderDragonBow;
    public static BaseUltimateItem theUltimateHelmetLeft, theUltimateHelmetMiddle, theUltimateHelmetRight,
            theUltimateChestplateLeft, theUltimateChestplateMiddle, theUltimateChestplateRight,
            theUltimateLeggingsLeft, theUltimateLeggingsMiddle, theUltimateLeggingsRight,
            theUltimateBootsLeft, theUltimateBootsMiddle, theUltimateBootsRight;
    public static DevTool devTool;
    public static BaseDevItem theDragonTeamItem, moddedCityItem, jonBamsItem, twitchItem, beamItem;
    public static ItemTeslaPickaxe itemTeslaPickaxe;
    public static ItemTeslaSword itemTeslaSword;
    public static ItemTeslaAxe itemTeslaAxe;
    public static ItemTeslaRod itemTeslaRod;
    public static ItemTeslaHoe itemTeslaHoe;
    public static ItemTeslaShovel itemTeslaShovel;
    public static ItemRFAxe itemRFAxe;
    public static ItemRFRod itemRFRod;
    public static ItemRFPickaxe itemRFPickaxe;
    public static ItemRFSword itemRFSword;
    public static ItemRFHoe itemRFHoe;
    public static ItemRFShovel itemRFShovel;
    public static GuiTester guiTester;
    public static ItemCoalArrow itemCoalArrow;
    public static ItemLapisArrow itemLapisArrow;
    public static ItemRedstoneArrow itemRedstoneArrow;
    public static ItemLavaArrow itemLavaArrow;
    public static ItemEnergyStorage itemEnergyStorage;
    public static ItemBaubleDragon itemBaubleDragon;

    public static void init() {
        if (Loader.isModLoaded("Baubles")) {
            itemBaubleDragon = new ItemBaubleDragon();
        }
        itemEnergyStorage = new ItemEnergyStorage();
        guiTester = new GuiTester();
        itemRFShovel = new ItemRFShovel();
        itemRFHoe = new ItemRFHoe();
        itemRFAxe = new ItemRFAxe();
        itemRFPickaxe = new ItemRFPickaxe();
        itemRFSword = new ItemRFSword();
        itemRFRod = new ItemRFRod();
        if (Loader.isModLoaded("tesla")) {
            itemTeslaShovel = new ItemTeslaShovel();
            itemTeslaHoe = new ItemTeslaHoe();
            itemTeslaAxe = new ItemTeslaAxe();
            itemTeslaPickaxe = new ItemTeslaPickaxe();
            itemTeslaSword = new ItemTeslaSword();
        }
        steelHelmet = new BaseSteelArmor(HEAD, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
        steelChestplate = new BaseSteelArmor(CHEST, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
        steelLeggings = new BaseSteelArmor(LEGS, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
        steelBoots = new BaseSteelArmor(FEET, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
        itemTeslaRod = new ItemTeslaRod();
        twitchItem = new BaseDevItem(DevItems.TWITCH);
        beamItem = new BaseDevItem(DevItems.BEAM);
        theDragonTeamItem = new BaseDevItem(DevItems.THE_DRAGON_TEAM);
        moddedCityItem = new BaseDevItem(DevItems.MODDED_CITY);
        jonBamsItem = new BaseDevItem(DevItems.JON_BAMS);
        chainmail = new BaseItem(Items.CHAINMAIL);
        guardianScale = new BaseItem(Items.GUARDIAN_SCALE);
        witherBone = new BaseItem(Items.WITHER_BONE);
        enderDragonScale = new BaseItem(Items.ENDER_DRAGON_SCALE);
        theUltimateMaterial = new BaseItem(Items.THE_ULTIMATE_MATERIAL);
        lavaCrystal = new LavaCrystal();
        theGiftOfTheGods = new TheGiftOfTheGods();
        armorPlusBook = new BaseItem(Items.ARMORPLUS_BOOK);
        steelIngot = new BaseItem(Items.STEEL_INGOT);
        electricalIngot = new BaseItem(Items.ELECTRICAL_INGOT);
        redstoneApple = new RedstoneApple();
        armorPlusInfoBook = new BaseItem(Items.ARMORPLUS_INFO_BOOK);
        nbtItem = new NBTItem();
        coalHelmet = new BaseArmor(COAL, HEAD);
        coalChestplate = new BaseArmor(COAL, CHEST);
        coalLeggings = new BaseArmor(COAL, LEGS);
        coalBoots = new BaseArmor(COAL, FEET);
        emeraldHelmet = new BaseArmor(EMERALD, HEAD);
        emeraldChestplate = new BaseArmor(EMERALD, CHEST);
        emeraldLeggings = new BaseArmor(EMERALD, LEGS);
        emeraldBoots = new BaseArmor(EMERALD, FEET);
        lapisHelmet = new BaseArmor(LAPIS, HEAD);
        lapisChestplate = new BaseArmor(LAPIS, CHEST);
        lapisLeggings = new BaseArmor(LAPIS, LEGS);
        lapisBoots = new BaseArmor(LAPIS, FEET);
        lavaHelmet = new BaseArmor(LAVA, HEAD);
        lavaChestplate = new BaseArmor(LAVA, CHEST);
        lavaLeggings = new BaseArmor(LAVA, LEGS);
        lavaBoots = new BaseArmor(LAVA, FEET);
        obsidianHelmet = new BaseArmor(OBSIDIAN, HEAD);
        obsidianChestplate = new BaseArmor(OBSIDIAN, CHEST);
        obsidianLeggings = new BaseArmor(OBSIDIAN, LEGS);
        obsidianBoots = new BaseArmor(OBSIDIAN, FEET);
        redstoneHelmet = new BaseArmor(REDSTONE, HEAD);
        redstoneChestplate = new BaseArmor(REDSTONE, CHEST);
        redstoneLeggings = new BaseArmor(REDSTONE, LEGS);
        redstoneBoots = new BaseArmor(REDSTONE, FEET);
        electricalHelmet = new BaseElectricalArmor(HEAD, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        electricalChestplate = new BaseElectricalArmor(CHEST, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        electricalLeggings = new BaseElectricalArmor(LEGS, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        electricalBoots = new BaseElectricalArmor(FEET, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        chickenHelmet = new BaseArmor(CHICKEN, HEAD);
        chickenChestplate = new BaseArmor(CHICKEN, CHEST);
        chickenLeggings = new BaseArmor(CHICKEN, LEGS);
        chickenBoots = new BaseArmor(CHICKEN, FEET);
        slimeHelmet = new BaseArmor(SLIME, HEAD);
        slimeChestplate = new BaseArmor(SLIME, CHEST);
        slimeLeggings = new BaseArmor(SLIME, LEGS);
        slimeBoots = new BaseArmor(SLIME, FEET);
        enderDragonHelmet = new BaseArmor(ENDER_DRAGON, HEAD);
        enderDragonChestplate = new BaseArmor(ENDER_DRAGON, CHEST);
        enderDragonLeggings = new BaseArmor(ENDER_DRAGON, LEGS);
        enderDragonBoots = new BaseArmor(ENDER_DRAGON, FEET);
        guardianHelmet = new BaseArmor(GUARDIAN, HEAD);
        guardianChestplate = new BaseArmor(GUARDIAN, CHEST);
        guardianLeggings = new BaseArmor(GUARDIAN, LEGS);
        guardianBoots = new BaseArmor(GUARDIAN, FEET);
        superStarHelmet = new BaseArmor(SUPER_STAR, HEAD);
        superStarChestplate = new BaseArmor(SUPER_STAR, CHEST);
        superStarLeggings = new BaseArmor(SUPER_STAR, LEGS);
        superStarBoots = new BaseArmor(SUPER_STAR, FEET);
        theUltimateHelmet = new BaseUltimateArmor(HEAD);
        theUltimateChestplate = new BaseUltimateArmor(CHEST);
        theUltimateLeggings = new BaseUltimateArmor(LEGS);
        theUltimateBoots = new BaseUltimateArmor(FEET);
        arditeHelmet = new BaseArmor(ARDITE, HEAD);
        arditeChestplate = new BaseArmor(ARDITE, CHEST);
        arditeLeggings = new BaseArmor(ARDITE, LEGS);
        arditeBoots = new BaseArmor(ARDITE, FEET);
        cobaltHelmet = new BaseArmor(COBALT, HEAD);
        cobaltChestplate = new BaseArmor(COBALT, CHEST);
        cobaltLeggings = new BaseArmor(COBALT, LEGS);
        cobaltBoots = new BaseArmor(COBALT, FEET);
        manyullynHelmet = new BaseArmor(MANYULLYN, HEAD);
        manyullynChestplate = new BaseArmor(MANYULLYN, CHEST);
        manyullynLeggings = new BaseArmor(MANYULLYN, LEGS);
        manyullynBoots = new BaseArmor(MANYULLYN, FEET);
        pigIronHelmet = new BaseArmor(PIG_IRON, HEAD);
        pigIronChestplate = new BaseArmor(PIG_IRON, CHEST);
        pigIronLeggings = new BaseArmor(PIG_IRON, LEGS);
        pigIronBoots = new BaseArmor(PIG_IRON, FEET);
        knightSlimeHelmet = new BaseArmor(KNIGHT_SLIME, HEAD);
        knightSlimeChestplate = new BaseArmor(KNIGHT_SLIME, CHEST);
        knightSlimeLeggings = new BaseArmor(KNIGHT_SLIME, LEGS);
        knightSlimeBoots = new BaseArmor(KNIGHT_SLIME, FEET);
        coalSword = new BaseSpecialSword(Swords.COAL);
        lapisSword = new BaseSpecialSword(Swords.LAPIS);
        redstoneSword = new BaseSpecialSword(Swords.REDSTONE);
        emeraldSword = new BaseSpecialSword(Swords.EMERALD);
        obsidianSword = new BaseSpecialSword(Swords.OBSIDIAN);
        lavaSword = new BaseSpecialSword(Swords.LAVA);
        superStarSword = new BaseSpecialSword(Swords.SUPER_STAR);
        guardianSword = new BaseSpecialSword(Swords.GUARDIAN);
        enderDragonSword = new BaseSpecialSword(Swords.ENDER_DRAGON);
        coalBattleAxe = new BaseBattleAxe(BattleAxes.COAL);
        lapisBattleAxe = new BaseBattleAxe(BattleAxes.LAPIS);
        redstoneBattleAxe = new BaseBattleAxe(BattleAxes.REDSTONE);
        emeraldBattleAxe = new BaseBattleAxe(BattleAxes.EMERALD);
        obsidianBattleAxe = new BaseBattleAxe(BattleAxes.OBSIDIAN);
        lavaBattleAxe = new BaseBattleAxe(BattleAxes.LAVA);
        superStarBattleAxe = new BaseBattleAxe(BattleAxes.SUPER_STAR);
        guardianBattleAxe = new BaseBattleAxe(BattleAxes.GUARDIAN);
        enderDragonBattleAxe = new BaseBattleAxe(BattleAxes.ENDER_DRAGON);
        coalBow = new BaseBow(Bows.COAL);
        lapisBow = new BaseBow(Bows.LAPIS);
        redstoneBow = new BaseBow(Bows.REDSTONE);
        emeraldBow = new BaseBow(Bows.EMERALD);
        obsidianBow = new BaseBow(Bows.OBSIDIAN);
        lavaBow = new BaseBow(Bows.LAVA);
        superStarBow = new BaseBow(Bows.SUPER_STAR);
        guardianBow = new BaseBow(Bows.GUARDIAN);
        enderDragonBow = new BaseBow(Bows.ENDER_DRAGON);
        theUltimateHelmetLeft = new BaseUltimateItem(HELMET_LEFT);
        theUltimateHelmetMiddle = new BaseUltimateItem(HELMET_MIDDLE);
        theUltimateHelmetRight = new BaseUltimateItem(HELMET_RIGHT);
        theUltimateChestplateLeft = new BaseUltimateItem(CHESTPLATE_LEFT);
        theUltimateChestplateMiddle = new BaseUltimateItem(CHESTPLATE_MIDDLE);
        theUltimateChestplateRight = new BaseUltimateItem(CHESTPLATE_RIGHT);
        theUltimateLeggingsLeft = new BaseUltimateItem(LEGGINGS_LEFT);
        theUltimateLeggingsMiddle = new BaseUltimateItem(LEGGINGS_MIDDLE);
        theUltimateLeggingsRight = new BaseUltimateItem(LEGGINGS_RIGHT);
        theUltimateBootsLeft = new BaseUltimateItem(BOOTS_LEFT);
        theUltimateBootsMiddle = new BaseUltimateItem(BOOTS_MIDDLE);
        theUltimateBootsRight = new BaseUltimateItem(BOOTS_RIGHT);
        devTool = new DevTool();
        itemCoalArrow = new ItemCoalArrow();
        itemLapisArrow = new ItemLapisArrow();
        itemRedstoneArrow = new ItemRedstoneArrow();
        itemLavaArrow = new ItemLavaArrow();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        if (Loader.isModLoaded("Baubles")) {
            itemBaubleDragon.initModel();
        }
        itemEnergyStorage.initModel();
        guiTester.initModel();
        itemRFShovel.initModel();
        itemRFHoe.initModel();
        itemRFAxe.initModel();
        itemRFPickaxe.initModel();
        itemRFSword.initModel();
        itemRFRod.initModel();
        if (Loader.isModLoaded("tesla")) {
            itemTeslaShovel.initModel();
            itemTeslaHoe.initModel();
            itemTeslaAxe.initModel();
            itemTeslaPickaxe.initModel();
            itemTeslaSword.initModel();
        }
        steelHelmet.initModel();
        steelChestplate.initModel();
        steelLeggings.initModel();
        steelBoots.initModel();
        itemTeslaRod.initModel();
        twitchItem.initModel();
        beamItem.initModel();
        theDragonTeamItem.initModel();
        moddedCityItem.initModel();
        jonBamsItem.initModel();
        chainmail.initModel();
        guardianScale.initModel();
        witherBone.initModel();
        enderDragonScale.initModel();
        theUltimateMaterial.initModel();
        lavaCrystal.initModel();
        theGiftOfTheGods.initModel();
        armorPlusBook.initModel();
        steelIngot.initModel();
        electricalIngot.initModel();
        redstoneApple.initModel();
        armorPlusInfoBook.initModel();
        coalHelmet.initModel();
        coalChestplate.initModel();
        coalLeggings.initModel();
        coalBoots.initModel();
        emeraldHelmet.initModel();
        emeraldChestplate.initModel();
        emeraldLeggings.initModel();
        emeraldBoots.initModel();
        lapisHelmet.initModel();
        lapisChestplate.initModel();
        lapisLeggings.initModel();
        lapisBoots.initModel();
        lavaHelmet.initModel();
        lavaChestplate.initModel();
        lavaLeggings.initModel();
        lavaBoots.initModel();
        obsidianHelmet.initModel();
        obsidianChestplate.initModel();
        obsidianLeggings.initModel();
        obsidianBoots.initModel();
        redstoneHelmet.initModel();
        redstoneChestplate.initModel();
        redstoneLeggings.initModel();
        redstoneBoots.initModel();
        electricalHelmet.initModel();
        electricalChestplate.initModel();
        electricalLeggings.initModel();
        electricalBoots.initModel();
        chickenHelmet.initModel();
        chickenChestplate.initModel();
        chickenLeggings.initModel();
        chickenBoots.initModel();
        slimeHelmet.initModel();
        slimeChestplate.initModel();
        slimeLeggings.initModel();
        slimeBoots.initModel();
        enderDragonHelmet.initModel();
        enderDragonChestplate.initModel();
        enderDragonLeggings.initModel();
        enderDragonBoots.initModel();
        guardianHelmet.initModel();
        guardianChestplate.initModel();
        guardianLeggings.initModel();
        guardianBoots.initModel();
        superStarHelmet.initModel();
        superStarChestplate.initModel();
        superStarLeggings.initModel();
        superStarBoots.initModel();
        theUltimateHelmet.initModel();
        theUltimateChestplate.initModel();
        theUltimateLeggings.initModel();
        theUltimateBoots.initModel();
        arditeHelmet.initModel();
        arditeChestplate.initModel();
        arditeLeggings.initModel();
        arditeBoots.initModel();
        cobaltHelmet.initModel();
        cobaltChestplate.initModel();
        cobaltLeggings.initModel();
        cobaltBoots.initModel();
        manyullynHelmet.initModel();
        manyullynChestplate.initModel();
        manyullynLeggings.initModel();
        manyullynBoots.initModel();
        pigIronHelmet.initModel();
        pigIronChestplate.initModel();
        pigIronLeggings.initModel();
        pigIronBoots.initModel();
        knightSlimeHelmet.initModel();
        knightSlimeChestplate.initModel();
        knightSlimeLeggings.initModel();
        knightSlimeBoots.initModel();
        coalSword.initModel();
        lapisSword.initModel();
        redstoneSword.initModel();
        emeraldSword.initModel();
        obsidianSword.initModel();
        lavaSword.initModel();
        superStarSword.initModel();
        guardianSword.initModel();
        enderDragonSword.initModel();
        coalBattleAxe.initModel();
        lapisBattleAxe.initModel();
        redstoneBattleAxe.initModel();
        emeraldBattleAxe.initModel();
        obsidianBattleAxe.initModel();
        lavaBattleAxe.initModel();
        superStarBattleAxe.initModel();
        guardianBattleAxe.initModel();
        enderDragonBattleAxe.initModel();
        coalBow.initModel();
        lapisBow.initModel();
        redstoneBow.initModel();
        emeraldBow.initModel();
        obsidianBow.initModel();
        lavaBow.initModel();
        superStarBow.initModel();
        guardianBow.initModel();
        enderDragonBow.initModel();
        theUltimateHelmetLeft.initModel();
        theUltimateHelmetMiddle.initModel();
        theUltimateHelmetRight.initModel();
        theUltimateChestplateLeft.initModel();
        theUltimateChestplateMiddle.initModel();
        theUltimateChestplateRight.initModel();
        theUltimateLeggingsLeft.initModel();
        theUltimateLeggingsMiddle.initModel();
        theUltimateLeggingsRight.initModel();
        theUltimateBootsLeft.initModel();
        theUltimateBootsMiddle.initModel();
        theUltimateBootsRight.initModel();
        devTool.initModel();
        nbtItem.initModel();
        itemCoalArrow.initModel();
        itemLapisArrow.initModel();
        itemRedstoneArrow.initModel();
        itemLavaArrow.initModel();
    }
}
