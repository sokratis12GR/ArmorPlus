/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.ItemUltimateParts;
import net.thedragonteam.armorplus.items.arrows.*;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.baubles.ItemBaubleDragon;
import net.thedragonteam.armorplus.items.books.ItemAPBook;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.dev.NBTItem;
import net.thedragonteam.armorplus.items.energy.tesla.*;
import net.thedragonteam.armorplus.items.enums.*;
import net.thedragonteam.armorplus.items.materials.ItemMaterial;
import net.thedragonteam.armorplus.items.materials.LavaCrystal;

import java.util.Arrays;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.isBaublesLoaded;
import static net.thedragonteam.armorplus.ArmorPlus.isTeslaLoaded;
import static net.thedragonteam.armorplus.armors.APArmorMaterial.*;
import static net.thedragonteam.armorplus.registry.ModRegistryUtils.INSTANCE;

public class ModItems {

    public static ItemAPBook bookInfo;
    public static ItemMaterial materials;
    public static BaseItem steelIngot, electricalIngot;
    public static RedstoneApple redstoneApple;
    public static LavaCrystal lavaCrystal;
    public static TheGiftOfTheGods theGiftOfTheGods;
    public static NBTItem nbtItem;
    public static boolean[] isEnabled = new boolean[]{
            enableCoalArmor, enableEmeraldArmor, enableLapisArmor, enableLavaArmor, enableObsidianArmor, enableRedstoneArmor,
            enableChickenArmor, enableSlimeArmor, enableEnderDragonArmor, enableGuardianArmor, enableSuperStarArmor,
    };
    public static Swords[] swordType = new Swords[]{
            Swords.COAL, Swords.LAPIS, Swords.REDSTONE, Swords.EMERALD, Swords.OBSIDIAN, Swords.LAVA, Swords.GUARDIAN, Swords.SUPER_STAR, Swords.ENDER_DRAGON
    };
    public static BattleAxes[] battleAxeType = new BattleAxes[]{
            BattleAxes.COAL, BattleAxes.LAPIS, BattleAxes.REDSTONE, BattleAxes.EMERALD, BattleAxes.OBSIDIAN, BattleAxes.LAVA, BattleAxes.GUARDIAN, BattleAxes.SUPER_STAR, BattleAxes.ENDER_DRAGON
    };
    public static Bows[] bowType = new Bows[]{
            Bows.COAL, Bows.LAPIS, Bows.REDSTONE, Bows.EMERALD, Bows.OBSIDIAN, Bows.LAVA, Bows.GUARDIAN, Bows.SUPER_STAR, Bows.ENDER_DRAGON
    };
    public static boolean[] isSwordEnabled = new boolean[]{
            enableCoalWeapons[0], enableLapisWeapons[0], enableRedstoneWeapons[0], enableEmeraldWeapons[0], enableObsidianWeapons[0], enableLavaWeapons[0], enableGuardianWeapons[0], enableSuperStarWeapons[0], enableEnderDragonWeapons[0]
    };
    public static boolean[] isBattleAxeEnabled = new boolean[]{
            enableCoalWeapons[1], enableLapisWeapons[1], enableRedstoneWeapons[1], enableEmeraldWeapons[1], enableObsidianWeapons[1], enableLavaWeapons[1], enableGuardianWeapons[1], enableSuperStarWeapons[1], enableEnderDragonWeapons[1]
    };
    public static boolean[] isBowEnabled = new boolean[]{
            enableCoalWeapons[2], enableLapisWeapons[2], enableRedstoneWeapons[2], enableEmeraldWeapons[2], enableObsidianWeapons[2], enableLavaWeapons[2], enableGuardianWeapons[2], enableSuperStarWeapons[2], enableEnderDragonWeapons[2]
    };
    public static ItemArmorBase[] coal = new ItemArmorBase[4];
    public static ItemArmorBase[] emerald = new ItemArmorBase[4];
    public static ItemArmorBase[] obsidian = new ItemArmorBase[4];
    public static ItemArmorBase[] redstone = new ItemArmorBase[4];
    public static ItemArmorBase[] lapis = new ItemArmorBase[4];
    public static ItemArmorBase[] lava = new ItemArmorBase[4];
    public static ItemArmorBase[] chicken = new ItemArmorBase[4];
    public static ItemArmorBase[] slime = new ItemArmorBase[4];
    public static ItemArmorBase[] enderDragon = new ItemArmorBase[4];
    public static ItemArmorBase[] guardian = new ItemArmorBase[4];
    public static ItemArmorBase[] superStar = new ItemArmorBase[4];
    public static ItemArmorBase[] ardite = new ItemArmorBase[4];
    public static ItemArmorBase[] cobalt = new ItemArmorBase[4];
    public static ItemArmorBase[] manyullyn = new ItemArmorBase[4];
    public static ItemArmorBase[] pigIron = new ItemArmorBase[4];
    public static ItemArmorBase[] knightSlime = new ItemArmorBase[4];
    public static ItemUltimateArmor[] theUltimate = new ItemUltimateArmor[4];
    public static ItemSpecialSword[] sword = new ItemSpecialSword[9];
    public static ItemSpecialBattleAxe[] battleAxe = new ItemSpecialBattleAxe[9];
    public static ItemSpecialBow[] bow = new ItemSpecialBow[9];
    public static ItemUltimateParts theUltimateParts;
    public static DevTool devTool;
    public static BaseDevItem twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem;
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
    public static ItemBaubleDragon itemBaubleDragon;
    public static EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[]{HEAD, CHEST, LEGS, FEET};

    public static BaseItem[] templates = new BaseItem[16];
    public static String[] templateNames = new String[]{
            "coal_template", "lapis_template", "redstone_template",
            "emerald_template", "obsidian_template", "lava_template",
            "chicken_template", "slime_template",
            "guardian_template", "super_star_template", "ender_dragon_template", "the_ultimate_template",
            "cobalt_template", "ardite_template", "manyullyn_template", "knight_slime_template", "pig_iron_template"};

    public static void init() {
        itemTeslaRod = new ItemTeslaRod();
        twitchItem = new BaseDevItem(DevItems.TWITCH);
        beamItem = new BaseDevItem(DevItems.BEAM);
        theDragonTeamItem = new BaseDevItem(DevItems.THE_DRAGON_TEAM);
        moddedCityItem = new BaseDevItem(DevItems.MODDED_CITY);
        jonBamsItem = new BaseDevItem(DevItems.JON_BAMS);
        materials = new ItemMaterial();
        lavaCrystal = new LavaCrystal();
        theGiftOfTheGods = new TheGiftOfTheGods();
        bookInfo = new ItemAPBook();
        steelIngot = new BaseItem(Items.STEEL_INGOT);
        electricalIngot = new BaseItem(Items.ELECTRICAL_INGOT);
        redstoneApple = new RedstoneApple();
        nbtItem = new NBTItem();
        if (isBaublesLoaded()) {
            itemBaubleDragon = new ItemBaubleDragon();
        }
        if (isTeslaLoaded()) {
            if (enableEnergyItems[4]) itemTeslaHoe = new ItemTeslaHoe();
            if (enableEnergyItems[3]) itemTeslaShovel = new ItemTeslaShovel();
            if (enableEnergyItems[2]) itemTeslaAxe = new ItemTeslaAxe();
            if (enableEnergyItems[1]) itemTeslaPickaxe = new ItemTeslaPickaxe();
            if (enableEnergyItems[0]) itemTeslaSword = new ItemTeslaSword();
        }
        //Armors
        INSTANCE.registerArmor(enableCoalArmor, coal, COAL);
        INSTANCE.registerArmor(enableEmeraldArmor, emerald, EMERALD);
        INSTANCE.registerArmor(enableLapisArmor, lapis, LAPIS);
        INSTANCE.registerArmor(enableLavaArmor, lava, LAVA);
        INSTANCE.registerArmor(enableObsidianArmor, obsidian, OBSIDIAN);
        INSTANCE.registerArmor(enableRedstoneArmor, redstone, REDSTONE);
        INSTANCE.registerArmor(enableChickenArmor, chicken, CHICKEN);
        INSTANCE.registerArmor(enableSlimeArmor, slime, SLIME);
        INSTANCE.registerArmor(enableEnderDragonArmor, enderDragon, ENDER_DRAGON);
        INSTANCE.registerArmor(enableGuardianArmor, guardian, GUARDIAN);
        INSTANCE.registerArmor(enableSuperStarArmor, superStar, SUPER_STAR);
        INSTANCE.registerArmor(enableArditeArmor, ardite, ARDITE);
        INSTANCE.registerArmor(enableCobaltArmor, cobalt, COBALT);
        INSTANCE.registerArmor(enableManyullynArmor, manyullyn, MANYULLYN);
        INSTANCE.registerArmor(enablePigIronArmor, pigIron, PIG_IRON);
        INSTANCE.registerArmor(enableKnightSlimeArmor, knightSlime, KNIGHT_SLIME);
        INSTANCE.registerArmor(enableTheUltimateArmor, theUltimate);
        //Swords
        INSTANCE.registerSword(isSwordEnabled, sword, swordType);
        //BattleAxes
        INSTANCE.registerBattleAxe(isBattleAxeEnabled, battleAxe, battleAxeType);
        //Bows
        INSTANCE.registerBow(isBowEnabled, bow, bowType);
        devTool = new DevTool();
        itemCoalArrow = new ItemCoalArrow();
        itemLapisArrow = new ItemLapisArrow();
        itemRedstoneArrow = new ItemRedstoneArrow();
        itemLavaArrow = new ItemLavaArrow();
        itemEnderDragonArrow = new ItemEnderDragonArrow();
        Arrays.setAll(templates, i -> (new BaseItem(templateNames[i])));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemEnderDragonArrow.initModel();
        if (isBaublesLoaded()) {
            itemBaubleDragon.initModel();
        }
        if (isTeslaLoaded()) {
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
        materials.initModel();
        lavaCrystal.initModel();
        theGiftOfTheGods.initModel();
        bookInfo.initModel();
        steelIngot.initModel();
        electricalIngot.initModel();
        redstoneApple.initModel();
        INSTANCE.registerArmorModel(enableCoalArmor, coal);
        INSTANCE.registerArmorModel(enableEmeraldArmor, emerald);
        INSTANCE.registerArmorModel(enableLapisArmor, lapis);
        INSTANCE.registerArmorModel(enableLavaArmor, lava);
        INSTANCE.registerArmorModel(enableObsidianArmor, obsidian);
        INSTANCE.registerArmorModel(enableRedstoneArmor, redstone);
        INSTANCE.registerArmorModel(enableChickenArmor, chicken);
        INSTANCE.registerArmorModel(enableSlimeArmor, slime);
        INSTANCE.registerArmorModel(enableEnderDragonArmor, enderDragon);
        INSTANCE.registerArmorModel(enableGuardianArmor, guardian);
        INSTANCE.registerArmorModel(enableSuperStarArmor, superStar);
        INSTANCE.registerArmorModel(enableArditeArmor, ardite);
        INSTANCE.registerArmorModel(enableCobaltArmor, cobalt);
        INSTANCE.registerArmorModel(enableManyullynArmor, manyullyn);
        INSTANCE.registerArmorModel(enablePigIronArmor, pigIron);
        INSTANCE.registerArmorModel(enableKnightSlimeArmor, knightSlime);
        INSTANCE.registerArmorModel(enableTheUltimateArmor, theUltimate);
        INSTANCE.registerSwordModel(isSwordEnabled, sword);
        INSTANCE.registerBattleAxeModel(isBattleAxeEnabled, battleAxe);
        INSTANCE.registerBowModel(isBowEnabled, bow);
        devTool.initModel();
        nbtItem.initModel();
        itemCoalArrow.initModel();
        itemLapisArrow.initModel();
        itemRedstoneArrow.initModel();
        itemLavaArrow.initModel();
        for (BaseItem template : templates) template.initModel();
    }
}
