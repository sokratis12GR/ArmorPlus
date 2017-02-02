/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.armors.base.BaseElectricalArmor;
import net.thedragonteam.armorplus.armors.base.BaseSteelArmor;
import net.thedragonteam.armorplus.armors.base.BaseUltimateArmor;
import net.thedragonteam.armorplus.items.*;
import net.thedragonteam.armorplus.items.arrows.*;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.baubles.ItemBaubleDragon;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.dev.NBTItem;
import net.thedragonteam.armorplus.items.energy.rf.*;
import net.thedragonteam.armorplus.items.energy.tesla.*;
import net.thedragonteam.armorplus.items.materials.LavaCrystal;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.hasTesla;
import static net.thedragonteam.armorplus.armors.APArmorMaterial.*;
import static net.thedragonteam.armorplus.items.UltimateItems.*;

public class ModItems {

    public static BaseItem chainmail, guardianScale, witherBone,
            enderDragonScale, theUltimateMaterial, armorPlusBook,
            steelIngot, electricalIngot, armorPlusInfoBook;
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
    public static BaseSpecialSword coalSword, lapisSword, redstoneSword,
            emeraldSword, obsidianSword, lavaSword,
            guardianSword, superStarSword, enderDragonSword;
    public static BaseBattleAxe coalBattleAxe, lapisBattleAxe, redstoneBattleAxe,
            emeraldBattleAxe, obsidianBattleAxe, lavaBattleAxe,
            guardianBattleAxe, superStarBattleAxe, enderDragonBattleAxe;
    public static BaseBow coalBow, lapisBow, redstoneBow,
            emeraldBow, obsidianBow, lavaBow,
            guardianBow, superStarBow, enderDragonBow;
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
    public static ItemCoalArrow itemCoalArrow;
    public static ItemLapisArrow itemLapisArrow;
    public static ItemRedstoneArrow itemRedstoneArrow;
    public static ItemLavaArrow itemLavaArrow;
    public static ItemEnderDragonArrow itemEnderDragonArrow;
    public static ItemEnergyStorage itemEnergyStorage;
    public static ItemBaubleDragon itemBaubleDragon;

    public static void init() {
        if (Loader.isModLoaded("Baubles")) {
            itemBaubleDragon = new ItemBaubleDragon();
        }
        itemEnderDragonArrow = new ItemEnderDragonArrow();
        itemEnergyStorage = new ItemEnergyStorage();
        itemRFShovel = new ItemRFShovel();
        itemRFHoe = new ItemRFHoe();
        itemRFAxe = new ItemRFAxe();
        itemRFPickaxe = new ItemRFPickaxe();
        itemRFSword = new ItemRFSword();
        itemRFRod = new ItemRFRod();
        if (hasTesla()) {
            itemTeslaShovel = new ItemTeslaShovel();
            itemTeslaHoe = new ItemTeslaHoe();
            itemTeslaAxe = new ItemTeslaAxe();
            itemTeslaPickaxe = new ItemTeslaPickaxe();
            itemTeslaSword = new ItemTeslaSword();
        }
        if (APConfig.enableSteelArmor) {
            steelHelmet = new BaseSteelArmor(HEAD, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
            steelChestplate = new BaseSteelArmor(CHEST, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
            steelLeggings = new BaseSteelArmor(LEGS, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
            steelBoots = new BaseSteelArmor(FEET, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
        }
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
        if (APConfig.enableCoalArmor) {
            coalHelmet = new BaseArmor(COAL, HEAD);
            coalChestplate = new BaseArmor(COAL, CHEST);
            coalLeggings = new BaseArmor(COAL, LEGS);
            coalBoots = new BaseArmor(COAL, FEET);
        }
        if (APConfig.enableEmeraldArmor) {
            emeraldHelmet = new BaseArmor(EMERALD, HEAD);
            emeraldChestplate = new BaseArmor(EMERALD, CHEST);
            emeraldLeggings = new BaseArmor(EMERALD, LEGS);
            emeraldBoots = new BaseArmor(EMERALD, FEET);
        }
        if (APConfig.enableLapisArmor) {
            lapisHelmet = new BaseArmor(LAPIS, HEAD);
            lapisChestplate = new BaseArmor(LAPIS, CHEST);
            lapisLeggings = new BaseArmor(LAPIS, LEGS);
            lapisBoots = new BaseArmor(LAPIS, FEET);
        }
        if (APConfig.enableLavaArmor) {
            lavaHelmet = new BaseArmor(LAVA, HEAD);
            lavaChestplate = new BaseArmor(LAVA, CHEST);
            lavaLeggings = new BaseArmor(LAVA, LEGS);
            lavaBoots = new BaseArmor(LAVA, FEET);
        }
        if (APConfig.enableObsidianArmor) {
            obsidianHelmet = new BaseArmor(OBSIDIAN, HEAD);
            obsidianChestplate = new BaseArmor(OBSIDIAN, CHEST);
            obsidianLeggings = new BaseArmor(OBSIDIAN, LEGS);
            obsidianBoots = new BaseArmor(OBSIDIAN, FEET);
        }
        if (APConfig.enableRedstoneArmor) {
            redstoneHelmet = new BaseArmor(REDSTONE, HEAD);
            redstoneChestplate = new BaseArmor(REDSTONE, CHEST);
            redstoneLeggings = new BaseArmor(REDSTONE, LEGS);
            redstoneBoots = new BaseArmor(REDSTONE, FEET);
        }
        if (APConfig.enableElectricalArmor) {
            electricalHelmet = new BaseElectricalArmor(HEAD, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
            electricalChestplate = new BaseElectricalArmor(CHEST, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
            electricalLeggings = new BaseElectricalArmor(LEGS, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
            electricalBoots = new BaseElectricalArmor(FEET, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        }
        if (APConfig.enableChickenArmor) {
            chickenHelmet = new BaseArmor(CHICKEN, HEAD);
            chickenChestplate = new BaseArmor(CHICKEN, CHEST);
            chickenLeggings = new BaseArmor(CHICKEN, LEGS);
            chickenBoots = new BaseArmor(CHICKEN, FEET);
        }
        if (APConfig.enableSlimeArmor) {
            slimeHelmet = new BaseArmor(SLIME, HEAD);
            slimeChestplate = new BaseArmor(SLIME, CHEST);
            slimeLeggings = new BaseArmor(SLIME, LEGS);
            slimeBoots = new BaseArmor(SLIME, FEET);
        }
        if (APConfig.enableEnderDragonArmor) {
            enderDragonHelmet = new BaseArmor(ENDER_DRAGON, HEAD);
            enderDragonChestplate = new BaseArmor(ENDER_DRAGON, CHEST);
            enderDragonLeggings = new BaseArmor(ENDER_DRAGON, LEGS);
            enderDragonBoots = new BaseArmor(ENDER_DRAGON, FEET);
        }
        if (APConfig.enableGuardianArmor) {
            guardianHelmet = new BaseArmor(GUARDIAN, HEAD);
            guardianChestplate = new BaseArmor(GUARDIAN, CHEST);
            guardianLeggings = new BaseArmor(GUARDIAN, LEGS);
            guardianBoots = new BaseArmor(GUARDIAN, FEET);
        }
        if (APConfig.enableSuperStarArmor) {
            superStarHelmet = new BaseArmor(SUPER_STAR, HEAD);
            superStarChestplate = new BaseArmor(SUPER_STAR, CHEST);
            superStarLeggings = new BaseArmor(SUPER_STAR, LEGS);
            superStarBoots = new BaseArmor(SUPER_STAR, FEET);
        }
        if (APConfig.enableTheUltimateArmor) {
            theUltimateHelmet = new BaseUltimateArmor(HEAD);
            theUltimateChestplate = new BaseUltimateArmor(CHEST);
            theUltimateLeggings = new BaseUltimateArmor(LEGS);
            theUltimateBoots = new BaseUltimateArmor(FEET);
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
        }
        if (APConfig.enableArditeArmor) {
            arditeHelmet = new BaseArmor(ARDITE, HEAD);
            arditeChestplate = new BaseArmor(ARDITE, CHEST);
            arditeLeggings = new BaseArmor(ARDITE, LEGS);
            arditeBoots = new BaseArmor(ARDITE, FEET);
        }
        if (APConfig.enableCobaltArmor) {
            cobaltHelmet = new BaseArmor(COBALT, HEAD);
            cobaltChestplate = new BaseArmor(COBALT, CHEST);
            cobaltLeggings = new BaseArmor(COBALT, LEGS);
            cobaltBoots = new BaseArmor(COBALT, FEET);
        }
        if (APConfig.enableManyullynArmor) {
            manyullynHelmet = new BaseArmor(MANYULLYN, HEAD);
            manyullynChestplate = new BaseArmor(MANYULLYN, CHEST);
            manyullynLeggings = new BaseArmor(MANYULLYN, LEGS);
            manyullynBoots = new BaseArmor(MANYULLYN, FEET);
        }
        if (APConfig.enablePigIronArmor) {
            pigIronHelmet = new BaseArmor(PIG_IRON, HEAD);
            pigIronChestplate = new BaseArmor(PIG_IRON, CHEST);
            pigIronLeggings = new BaseArmor(PIG_IRON, LEGS);
            pigIronBoots = new BaseArmor(PIG_IRON, FEET);
        }
        if (APConfig.enableKnightSlimeArmor) {
            knightSlimeHelmet = new BaseArmor(KNIGHT_SLIME, HEAD);
            knightSlimeChestplate = new BaseArmor(KNIGHT_SLIME, CHEST);
            knightSlimeLeggings = new BaseArmor(KNIGHT_SLIME, LEGS);
            knightSlimeBoots = new BaseArmor(KNIGHT_SLIME, FEET);
        }
        if (APConfig.enableCoalSword)
            coalSword = new BaseSpecialSword(Swords.COAL);
        if (APConfig.enableLapisSword)
            lapisSword = new BaseSpecialSword(Swords.LAPIS);
        if (APConfig.enableRedstoneSword)
            redstoneSword = new BaseSpecialSword(Swords.REDSTONE);
        if (APConfig.enableEmeraldSword)
            emeraldSword = new BaseSpecialSword(Swords.EMERALD);
        if (APConfig.enableObsidianSword)
            obsidianSword = new BaseSpecialSword(Swords.OBSIDIAN);
        if (APConfig.enableLavaSword)
            lavaSword = new BaseSpecialSword(Swords.LAVA);
        if (APConfig.enableGuardianSword)
            guardianSword = new BaseSpecialSword(Swords.GUARDIAN);
        if (APConfig.enableSuperStarSword)
            superStarSword = new BaseSpecialSword(Swords.SUPER_STAR);
        if (APConfig.enableEnderDragonSword)
            enderDragonSword = new BaseSpecialSword(Swords.ENDER_DRAGON);
        if (APConfig.enableCoalBattleAxe)
            coalBattleAxe = new BaseBattleAxe(BattleAxes.COAL);
        if (APConfig.enableLapisBattleAxe)
            lapisBattleAxe = new BaseBattleAxe(BattleAxes.LAPIS);
        if (APConfig.enableRedstoneBattleAxe)
            redstoneBattleAxe = new BaseBattleAxe(BattleAxes.REDSTONE);
        if (APConfig.enableEmeraldBattleAxe)
            emeraldBattleAxe = new BaseBattleAxe(BattleAxes.EMERALD);
        if (APConfig.enableObsidianBattleAxe)
            obsidianBattleAxe = new BaseBattleAxe(BattleAxes.OBSIDIAN);
        if (APConfig.enableLavaBattleAxe)
            lavaBattleAxe = new BaseBattleAxe(BattleAxes.LAVA);
        if (APConfig.enableGuardianBattleAxe)
            guardianBattleAxe = new BaseBattleAxe(BattleAxes.GUARDIAN);
        if (APConfig.enableSuperStarBattleAxe)
            superStarBattleAxe = new BaseBattleAxe(BattleAxes.SUPER_STAR);
        if (APConfig.enableEnderDragonBattleAxe)
            enderDragonBattleAxe = new BaseBattleAxe(BattleAxes.ENDER_DRAGON);
        if (APConfig.enableCoalBow)
            coalBow = new BaseBow(Bows.COAL);
        if (APConfig.enableLapisBow)
            lapisBow = new BaseBow(Bows.LAPIS);
        if (APConfig.enableRedstoneBow)
            redstoneBow = new BaseBow(Bows.REDSTONE);
        if (APConfig.enableEmeraldBow)
            emeraldBow = new BaseBow(Bows.EMERALD);
        if (APConfig.enableObsidianBow)
            obsidianBow = new BaseBow(Bows.OBSIDIAN);
        if (APConfig.enableLavaBow)
            lavaBow = new BaseBow(Bows.LAVA);
        if (APConfig.enableGuardianBow)
            guardianBow = new BaseBow(Bows.GUARDIAN);
        if (APConfig.enableSuperStarBow)
            superStarBow = new BaseBow(Bows.SUPER_STAR);
        if (APConfig.enableEnderDragonBow)
            enderDragonBow = new BaseBow(Bows.ENDER_DRAGON);
        devTool = new DevTool();
        itemCoalArrow = new ItemCoalArrow();
        itemLapisArrow = new ItemLapisArrow();
        itemRedstoneArrow = new ItemRedstoneArrow();
        itemLavaArrow = new ItemLavaArrow();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemEnderDragonArrow.initModel();
        if (Loader.isModLoaded("Baubles")) {
            itemBaubleDragon.initModel();
        }
        itemEnergyStorage.initModel();
        itemRFShovel.initModel();
        itemRFHoe.initModel();
        itemRFAxe.initModel();
        itemRFPickaxe.initModel();
        itemRFSword.initModel();
        itemRFRod.initModel();
        if (hasTesla()) {
            itemTeslaShovel.initModel();
            itemTeslaHoe.initModel();
            itemTeslaAxe.initModel();
            itemTeslaPickaxe.initModel();
            itemTeslaSword.initModel();
        }
        if (APConfig.enableSteelArmor) {
            steelHelmet.initModel();
            steelChestplate.initModel();
            steelLeggings.initModel();
            steelBoots.initModel();
        }
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
        if (APConfig.enableCoalArmor) {
            coalHelmet.initModel();
            coalChestplate.initModel();
            coalLeggings.initModel();
            coalBoots.initModel();
        }
        if (APConfig.enableEmeraldArmor) {
            emeraldHelmet.initModel();
            emeraldChestplate.initModel();
            emeraldLeggings.initModel();
            emeraldBoots.initModel();
        }
        if (APConfig.enableLapisArmor) {
            lapisHelmet.initModel();
            lapisChestplate.initModel();
            lapisLeggings.initModel();
            lapisBoots.initModel();
        }
        if (APConfig.enableLavaArmor) {
            lavaHelmet.initModel();
            lavaChestplate.initModel();
            lavaLeggings.initModel();
            lavaBoots.initModel();
        }
        if (APConfig.enableObsidianArmor) {
            obsidianHelmet.initModel();
            obsidianChestplate.initModel();
            obsidianLeggings.initModel();
            obsidianBoots.initModel();
        }
        if (APConfig.enableRedstoneArmor) {
            redstoneHelmet.initModel();
            redstoneChestplate.initModel();
            redstoneLeggings.initModel();
            redstoneBoots.initModel();
        }
        if (APConfig.enableElectricalArmor) {
            electricalHelmet.initModel();
            electricalChestplate.initModel();
            electricalLeggings.initModel();
            electricalBoots.initModel();
        }
        if (APConfig.enableChickenArmor) {
            chickenHelmet.initModel();
            chickenChestplate.initModel();
            chickenLeggings.initModel();
            chickenBoots.initModel();
        }
        if (APConfig.enableSlimeArmor) {
            slimeHelmet.initModel();
            slimeChestplate.initModel();
            slimeLeggings.initModel();
            slimeBoots.initModel();
        }
        if (APConfig.enableEnderDragonArmor) {
            enderDragonHelmet.initModel();
            enderDragonChestplate.initModel();
            enderDragonLeggings.initModel();
            enderDragonBoots.initModel();
        }
        if (APConfig.enableGuardianArmor) {
            guardianHelmet.initModel();
            guardianChestplate.initModel();
            guardianLeggings.initModel();
            guardianBoots.initModel();
        }
        if (APConfig.enableSuperStarArmor) {
            superStarHelmet.initModel();
            superStarChestplate.initModel();
            superStarLeggings.initModel();
            superStarBoots.initModel();
        }
        if (APConfig.enableTheUltimateArmor) {
            theUltimateHelmet.initModel();
            theUltimateChestplate.initModel();
            theUltimateLeggings.initModel();
            theUltimateBoots.initModel();
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
        }
        if (APConfig.enableArditeArmor) {
            arditeHelmet.initModel();
            arditeChestplate.initModel();
            arditeLeggings.initModel();
            arditeBoots.initModel();
        }
        if (APConfig.enableCobaltArmor) {
            cobaltHelmet.initModel();
            cobaltChestplate.initModel();
            cobaltLeggings.initModel();
            cobaltBoots.initModel();
        }
        if (APConfig.enableManyullynArmor) {
            manyullynHelmet.initModel();
            manyullynChestplate.initModel();
            manyullynLeggings.initModel();
            manyullynBoots.initModel();
        }
        if (APConfig.enablePigIronArmor) {
            pigIronHelmet.initModel();
            pigIronChestplate.initModel();
            pigIronLeggings.initModel();
            pigIronBoots.initModel();
        }
        if (APConfig.enableKnightSlimeArmor) {
            knightSlimeHelmet.initModel();
            knightSlimeChestplate.initModel();
            knightSlimeLeggings.initModel();
            knightSlimeBoots.initModel();
        }
        if (APConfig.enableCoalSword)
            coalSword.initModel();
        if (APConfig.enableLapisSword)
            lapisSword.initModel();
        if (APConfig.enableRedstoneSword)
            redstoneSword.initModel();
        if (APConfig.enableEmeraldSword)
            emeraldSword.initModel();
        if (APConfig.enableObsidianSword)
            obsidianSword.initModel();
        if (APConfig.enableLavaSword)
            lavaSword.initModel();
        if (APConfig.enableGuardianSword)
            guardianSword.initModel();
        if (APConfig.enableSuperStarSword)
            superStarSword.initModel();
        if (APConfig.enableEnderDragonSword)
            enderDragonSword.initModel();
        if (APConfig.enableCoalBattleAxe)
            coalBattleAxe.initModel();
        if (APConfig.enableLapisBattleAxe)
            lapisBattleAxe.initModel();
        if (APConfig.enableRedstoneBattleAxe)
            redstoneBattleAxe.initModel();
        if (APConfig.enableEmeraldBattleAxe)
            emeraldBattleAxe.initModel();
        if (APConfig.enableObsidianBattleAxe)
            obsidianBattleAxe.initModel();
        if (APConfig.enableLavaBattleAxe)
            lavaBattleAxe.initModel();
        if (APConfig.enableGuardianBattleAxe)
            guardianBattleAxe.initModel();
        if (APConfig.enableSuperStarBattleAxe)
            superStarBattleAxe.initModel();
        if (APConfig.enableEnderDragonBattleAxe)
            enderDragonBattleAxe.initModel();
        if (APConfig.enableCoalBow)
            coalBow.initModel();
        if (APConfig.enableLapisBow)
            lapisBow.initModel();
        if (APConfig.enableRedstoneBow)
            redstoneBow.initModel();
        if (APConfig.enableEmeraldBow)
            emeraldBow.initModel();
        if (APConfig.enableObsidianBow)
            obsidianBow.initModel();
        if (APConfig.enableLavaBow)
            lavaBow.initModel();
        if (APConfig.enableGuardianBow)
            guardianBow.initModel();
        if (APConfig.enableSuperStarBow)
            superStarBow.initModel();
        if (APConfig.enableEnderDragonBow)
            enderDragonBow.initModel();
        devTool.initModel();
        nbtItem.initModel();
        itemCoalArrow.initModel();
        itemLapisArrow.initModel();
        itemRedstoneArrow.initModel();
        itemLavaArrow.initModel();
    }
}
