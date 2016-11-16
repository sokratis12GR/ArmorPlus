/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.armors.base.BaseUltimateArmor;
import net.thedragonteam.armorplus.items.*;
import net.thedragonteam.armorplus.items.arrows.*;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.dev.NBTItem;
import net.thedragonteam.armorplus.items.energy.tesla.*;
import net.thedragonteam.armorplus.items.materials.LavaCrystal;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.ArmorPlus.hasTesla;
import static net.thedragonteam.armorplus.armors.ARPArmorMaterial.*;

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
    public static ItemUltimateParts theUltimateParts;
    public static DevTool devTool;
    public static BaseDevItem theDragonTeamItem, moddedCityItem, jonBamsItem, twitchItem, beamItem;
    public static ItemTeslaPickaxe itemTeslaPickaxe;
    public static ItemTeslaSword itemTeslaSword;
    public static ItemTeslaAxe itemTeslaAxe;
    public static ItemTeslaRod itemTeslaRod;
    public static ItemTeslaHoe itemTeslaHoe;
    public static ItemTeslaShovel itemTeslaShovel;
    public static ItemCoalArrow itemCoalArrow;
    public static ItemLapisArrow itemLapisArrow;
    public static ItemRedstoneArrow itemRedstoneArrow;
    public static ItemLavaArrow itemLavaArrow;
    public static ItemEnderDragonArrow itemEnderDragonArrow;
    //    public static ItemBaubleDragon itemBaubleDragon;

    public static void init() {
        if (Loader.isModLoaded("Baubles")) {
            //  itemBaubleDragon = new ItemBaubleDragon();
        }
        itemEnderDragonArrow = new ItemEnderDragonArrow();
        if (hasTesla()) {
            itemTeslaShovel = new ItemTeslaShovel();
            itemTeslaHoe = new ItemTeslaHoe();
            itemTeslaAxe = new ItemTeslaAxe();
            itemTeslaPickaxe = new ItemTeslaPickaxe();
            itemTeslaSword = new ItemTeslaSword();
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
        if (ARPConfig.enableCoalArmor) {
            coalHelmet = new BaseArmor(COAL, HEAD);
            coalChestplate = new BaseArmor(COAL, CHEST);
            coalLeggings = new BaseArmor(COAL, LEGS);
            coalBoots = new BaseArmor(COAL, FEET);
        }
        if (ARPConfig.enableEmeraldArmor) {
            emeraldHelmet = new BaseArmor(EMERALD, HEAD);
            emeraldChestplate = new BaseArmor(EMERALD, CHEST);
            emeraldLeggings = new BaseArmor(EMERALD, LEGS);
            emeraldBoots = new BaseArmor(EMERALD, FEET);
        }
        if (ARPConfig.enableLapisArmor) {
            lapisHelmet = new BaseArmor(LAPIS, HEAD);
            lapisChestplate = new BaseArmor(LAPIS, CHEST);
            lapisLeggings = new BaseArmor(LAPIS, LEGS);
            lapisBoots = new BaseArmor(LAPIS, FEET);
        }
        if (ARPConfig.enableLavaArmor) {
            lavaHelmet = new BaseArmor(LAVA, HEAD);
            lavaChestplate = new BaseArmor(LAVA, CHEST);
            lavaLeggings = new BaseArmor(LAVA, LEGS);
            lavaBoots = new BaseArmor(LAVA, FEET);
        }
        if (ARPConfig.enableObsidianArmor) {
            obsidianHelmet = new BaseArmor(OBSIDIAN, HEAD);
            obsidianChestplate = new BaseArmor(OBSIDIAN, CHEST);
            obsidianLeggings = new BaseArmor(OBSIDIAN, LEGS);
            obsidianBoots = new BaseArmor(OBSIDIAN, FEET);
        }
        if (ARPConfig.enableRedstoneArmor) {
            redstoneHelmet = new BaseArmor(REDSTONE, HEAD);
            redstoneChestplate = new BaseArmor(REDSTONE, CHEST);
            redstoneLeggings = new BaseArmor(REDSTONE, LEGS);
            redstoneBoots = new BaseArmor(REDSTONE, FEET);
        }
        if (ARPConfig.enableChickenArmor) {
            chickenHelmet = new BaseArmor(CHICKEN, HEAD);
            chickenChestplate = new BaseArmor(CHICKEN, CHEST);
            chickenLeggings = new BaseArmor(CHICKEN, LEGS);
            chickenBoots = new BaseArmor(CHICKEN, FEET);
        }
        if (ARPConfig.enableSlimeArmor) {
            slimeHelmet = new BaseArmor(SLIME, HEAD);
            slimeChestplate = new BaseArmor(SLIME, CHEST);
            slimeLeggings = new BaseArmor(SLIME, LEGS);
            slimeBoots = new BaseArmor(SLIME, FEET);
        }
        if (ARPConfig.enableEnderDragonArmor) {
            enderDragonHelmet = new BaseArmor(ENDER_DRAGON, HEAD);
            enderDragonChestplate = new BaseArmor(ENDER_DRAGON, CHEST);
            enderDragonLeggings = new BaseArmor(ENDER_DRAGON, LEGS);
            enderDragonBoots = new BaseArmor(ENDER_DRAGON, FEET);
        }
        if (ARPConfig.enableGuardianArmor) {
            guardianHelmet = new BaseArmor(GUARDIAN, HEAD);
            guardianChestplate = new BaseArmor(GUARDIAN, CHEST);
            guardianLeggings = new BaseArmor(GUARDIAN, LEGS);
            guardianBoots = new BaseArmor(GUARDIAN, FEET);
        }
        if (ARPConfig.enableSuperStarArmor) {
            superStarHelmet = new BaseArmor(SUPER_STAR, HEAD);
            superStarChestplate = new BaseArmor(SUPER_STAR, CHEST);
            superStarLeggings = new BaseArmor(SUPER_STAR, LEGS);
            superStarBoots = new BaseArmor(SUPER_STAR, FEET);
        }
        if (ARPConfig.enableTheUltimateArmor) {
            theUltimateHelmet = new BaseUltimateArmor(HEAD);
            theUltimateChestplate = new BaseUltimateArmor(CHEST);
            theUltimateLeggings = new BaseUltimateArmor(LEGS);
            theUltimateBoots = new BaseUltimateArmor(FEET);
            theUltimateParts = new ItemUltimateParts();
        }
        if (ARPConfig.enableArditeArmor) {
            arditeHelmet = new BaseArmor(ARDITE, HEAD);
            arditeChestplate = new BaseArmor(ARDITE, CHEST);
            arditeLeggings = new BaseArmor(ARDITE, LEGS);
            arditeBoots = new BaseArmor(ARDITE, FEET);
        }
        if (ARPConfig.enableCobaltArmor) {
            cobaltHelmet = new BaseArmor(COBALT, HEAD);
            cobaltChestplate = new BaseArmor(COBALT, CHEST);
            cobaltLeggings = new BaseArmor(COBALT, LEGS);
            cobaltBoots = new BaseArmor(COBALT, FEET);
        }
        if (ARPConfig.enableManyullynArmor) {
            manyullynHelmet = new BaseArmor(MANYULLYN, HEAD);
            manyullynChestplate = new BaseArmor(MANYULLYN, CHEST);
            manyullynLeggings = new BaseArmor(MANYULLYN, LEGS);
            manyullynBoots = new BaseArmor(MANYULLYN, FEET);
        }
        if (ARPConfig.enablePigIronArmor) {
            pigIronHelmet = new BaseArmor(PIG_IRON, HEAD);
            pigIronChestplate = new BaseArmor(PIG_IRON, CHEST);
            pigIronLeggings = new BaseArmor(PIG_IRON, LEGS);
            pigIronBoots = new BaseArmor(PIG_IRON, FEET);
        }
        if (ARPConfig.enableKnightSlimeArmor) {
            knightSlimeHelmet = new BaseArmor(KNIGHT_SLIME, HEAD);
            knightSlimeChestplate = new BaseArmor(KNIGHT_SLIME, CHEST);
            knightSlimeLeggings = new BaseArmor(KNIGHT_SLIME, LEGS);
            knightSlimeBoots = new BaseArmor(KNIGHT_SLIME, FEET);
        }
        if (ARPConfig.enableCoalSword)
            coalSword = new BaseSpecialSword(Swords.COAL);
        if (ARPConfig.enableLapisSword)
            lapisSword = new BaseSpecialSword(Swords.LAPIS);
        if (ARPConfig.enableRedstoneSword)
            redstoneSword = new BaseSpecialSword(Swords.REDSTONE);
        if (ARPConfig.enableEmeraldSword)
            emeraldSword = new BaseSpecialSword(Swords.EMERALD);
        if (ARPConfig.enableObsidianSword)
            obsidianSword = new BaseSpecialSword(Swords.OBSIDIAN);
        if (ARPConfig.enableLavaSword)
            lavaSword = new BaseSpecialSword(Swords.LAVA);
        if (ARPConfig.enableGuardianSword)
            guardianSword = new BaseSpecialSword(Swords.GUARDIAN);
        if (ARPConfig.enableSuperStarSword)
            superStarSword = new BaseSpecialSword(Swords.SUPER_STAR);
        if (ARPConfig.enableEnderDragonSword)
            enderDragonSword = new BaseSpecialSword(Swords.ENDER_DRAGON);
        if (ARPConfig.enableCoalBattleAxe)
            coalBattleAxe = new BaseBattleAxe(BattleAxes.COAL);
        if (ARPConfig.enableLapisBattleAxe)
            lapisBattleAxe = new BaseBattleAxe(BattleAxes.LAPIS);
        if (ARPConfig.enableRedstoneBattleAxe)
            redstoneBattleAxe = new BaseBattleAxe(BattleAxes.REDSTONE);
        if (ARPConfig.enableEmeraldBattleAxe)
            emeraldBattleAxe = new BaseBattleAxe(BattleAxes.EMERALD);
        if (ARPConfig.enableObsidianBattleAxe)
            obsidianBattleAxe = new BaseBattleAxe(BattleAxes.OBSIDIAN);
        if (ARPConfig.enableLavaBattleAxe)
            lavaBattleAxe = new BaseBattleAxe(BattleAxes.LAVA);
        if (ARPConfig.enableGuardianBattleAxe)
            guardianBattleAxe = new BaseBattleAxe(BattleAxes.GUARDIAN);
        if (ARPConfig.enableSuperStarBattleAxe)
            superStarBattleAxe = new BaseBattleAxe(BattleAxes.SUPER_STAR);
        if (ARPConfig.enableEnderDragonBattleAxe)
            enderDragonBattleAxe = new BaseBattleAxe(BattleAxes.ENDER_DRAGON);
        if (ARPConfig.enableCoalBow)
            coalBow = new BaseBow(Bows.COAL);
        if (ARPConfig.enableLapisBow)
            lapisBow = new BaseBow(Bows.LAPIS);
        if (ARPConfig.enableRedstoneBow)
            redstoneBow = new BaseBow(Bows.REDSTONE);
        if (ARPConfig.enableEmeraldBow)
            emeraldBow = new BaseBow(Bows.EMERALD);
        if (ARPConfig.enableObsidianBow)
            obsidianBow = new BaseBow(Bows.OBSIDIAN);
        if (ARPConfig.enableLavaBow)
            lavaBow = new BaseBow(Bows.LAVA);
        if (ARPConfig.enableGuardianBow)
            guardianBow = new BaseBow(Bows.GUARDIAN);
        if (ARPConfig.enableSuperStarBow)
            superStarBow = new BaseBow(Bows.SUPER_STAR);
        if (ARPConfig.enableEnderDragonBow)
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
            //          itemBaubleDragon.initModel();
        }
        if (hasTesla()) {
            itemTeslaShovel.initModel();
            itemTeslaHoe.initModel();
            itemTeslaAxe.initModel();
            itemTeslaPickaxe.initModel();
            itemTeslaSword.initModel();
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
        if (ARPConfig.enableCoalArmor) {
            coalHelmet.initModel();
            coalChestplate.initModel();
            coalLeggings.initModel();
            coalBoots.initModel();
        }
        if (ARPConfig.enableEmeraldArmor) {
            emeraldHelmet.initModel();
            emeraldChestplate.initModel();
            emeraldLeggings.initModel();
            emeraldBoots.initModel();
        }
        if (ARPConfig.enableLapisArmor) {
            lapisHelmet.initModel();
            lapisChestplate.initModel();
            lapisLeggings.initModel();
            lapisBoots.initModel();
        }
        if (ARPConfig.enableLavaArmor) {
            lavaHelmet.initModel();
            lavaChestplate.initModel();
            lavaLeggings.initModel();
            lavaBoots.initModel();
        }
        if (ARPConfig.enableObsidianArmor) {
            obsidianHelmet.initModel();
            obsidianChestplate.initModel();
            obsidianLeggings.initModel();
            obsidianBoots.initModel();
        }
        if (ARPConfig.enableRedstoneArmor) {
            redstoneHelmet.initModel();
            redstoneChestplate.initModel();
            redstoneLeggings.initModel();
            redstoneBoots.initModel();
        }
        if (ARPConfig.enableChickenArmor) {
            chickenHelmet.initModel();
            chickenChestplate.initModel();
            chickenLeggings.initModel();
            chickenBoots.initModel();
        }
        if (ARPConfig.enableSlimeArmor) {
            slimeHelmet.initModel();
            slimeChestplate.initModel();
            slimeLeggings.initModel();
            slimeBoots.initModel();
        }
        if (ARPConfig.enableEnderDragonArmor) {
            enderDragonHelmet.initModel();
            enderDragonChestplate.initModel();
            enderDragonLeggings.initModel();
            enderDragonBoots.initModel();
        }
        if (ARPConfig.enableGuardianArmor) {
            guardianHelmet.initModel();
            guardianChestplate.initModel();
            guardianLeggings.initModel();
            guardianBoots.initModel();
        }
        if (ARPConfig.enableSuperStarArmor) {
            superStarHelmet.initModel();
            superStarChestplate.initModel();
            superStarLeggings.initModel();
            superStarBoots.initModel();
        }
        if (ARPConfig.enableTheUltimateArmor) {
            theUltimateHelmet.initModel();
            theUltimateChestplate.initModel();
            theUltimateLeggings.initModel();
            theUltimateBoots.initModel();
            theUltimateParts.initModel();
        }
        if (ARPConfig.enableArditeArmor) {
            arditeHelmet.initModel();
            arditeChestplate.initModel();
            arditeLeggings.initModel();
            arditeBoots.initModel();
        }
        if (ARPConfig.enableCobaltArmor) {
            cobaltHelmet.initModel();
            cobaltChestplate.initModel();
            cobaltLeggings.initModel();
            cobaltBoots.initModel();
        }
        if (ARPConfig.enableManyullynArmor) {
            manyullynHelmet.initModel();
            manyullynChestplate.initModel();
            manyullynLeggings.initModel();
            manyullynBoots.initModel();
        }
        if (ARPConfig.enablePigIronArmor) {
            pigIronHelmet.initModel();
            pigIronChestplate.initModel();
            pigIronLeggings.initModel();
            pigIronBoots.initModel();
        }
        if (ARPConfig.enableKnightSlimeArmor) {
            knightSlimeHelmet.initModel();
            knightSlimeChestplate.initModel();
            knightSlimeLeggings.initModel();
            knightSlimeBoots.initModel();
        }
        if (ARPConfig.enableCoalSword)
            coalSword.initModel();
        if (ARPConfig.enableLapisSword)
            lapisSword.initModel();
        if (ARPConfig.enableRedstoneSword)
            redstoneSword.initModel();
        if (ARPConfig.enableEmeraldSword)
            emeraldSword.initModel();
        if (ARPConfig.enableObsidianSword)
            obsidianSword.initModel();
        if (ARPConfig.enableLavaSword)
            lavaSword.initModel();
        if (ARPConfig.enableGuardianSword)
            guardianSword.initModel();
        if (ARPConfig.enableSuperStarSword)
            superStarSword.initModel();
        if (ARPConfig.enableEnderDragonSword)
            enderDragonSword.initModel();
        if (ARPConfig.enableCoalBattleAxe)
            coalBattleAxe.initModel();
        if (ARPConfig.enableLapisBattleAxe)
            lapisBattleAxe.initModel();
        if (ARPConfig.enableRedstoneBattleAxe)
            redstoneBattleAxe.initModel();
        if (ARPConfig.enableEmeraldBattleAxe)
            emeraldBattleAxe.initModel();
        if (ARPConfig.enableObsidianBattleAxe)
            obsidianBattleAxe.initModel();
        if (ARPConfig.enableLavaBattleAxe)
            lavaBattleAxe.initModel();
        if (ARPConfig.enableGuardianBattleAxe)
            guardianBattleAxe.initModel();
        if (ARPConfig.enableSuperStarBattleAxe)
            superStarBattleAxe.initModel();
        if (ARPConfig.enableEnderDragonBattleAxe)
            enderDragonBattleAxe.initModel();
        if (ARPConfig.enableCoalBow)
            coalBow.initModel();
        if (ARPConfig.enableLapisBow)
            lapisBow.initModel();
        if (ARPConfig.enableRedstoneBow)
            redstoneBow.initModel();
        if (ARPConfig.enableEmeraldBow)
            emeraldBow.initModel();
        if (ARPConfig.enableObsidianBow)
            obsidianBow.initModel();
        if (ARPConfig.enableLavaBow)
            lavaBow.initModel();
        if (ARPConfig.enableGuardianBow)
            guardianBow.initModel();
        if (ARPConfig.enableSuperStarBow)
            superStarBow.initModel();
        if (ARPConfig.enableEnderDragonBow)
            enderDragonBow.initModel();
        devTool.initModel();
        nbtItem.initModel();
        itemCoalArrow.initModel();
        itemLapisArrow.initModel();
        itemRedstoneArrow.initModel();
        itemLavaArrow.initModel();
    }
}
