/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.ItemUltimateParts;
import net.thedragonteam.armorplus.items.arrows.*;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.books.ItemAPBook;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.enums.*;
import net.thedragonteam.armorplus.items.materials.ItemMaterial;
import net.thedragonteam.armorplus.items.materials.LavaCrystal;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.APArmorMaterial.*;
import static net.thedragonteam.armorplus.registry.ModRegistryUtils.*;

public class ModItems {

    public static ItemAPBook bookInfo = new ItemAPBook();
    public static ItemMaterial materials = new ItemMaterial();
    public static BaseItem steelIngot = new BaseItem(Items.STEEL_INGOT), electricalIngot = new BaseItem(Items.ELECTRICAL_INGOT);
    public static RedstoneApple redstoneApple = new RedstoneApple();
    public static LavaCrystal lavaCrystal = new LavaCrystal();
    public static TheGiftOfTheGods theGiftOfTheGods = new TheGiftOfTheGods();
  //  public static boolean[] isEnabled = new boolean[]{
  //          enableCoalArmor, enableEmeraldArmor, enableLapisArmor, enableLavaArmor, enableObsidianArmor, enableRedstoneArmor,
  //          enableChickenArmor, enableSlimeArmor, enableEnderDragonArmor, enableGuardianArmor, enableSuperStarArmor,
  //  };
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
    public static DevTool devTool = new DevTool();
    public static BaseDevItem twitchItem = new BaseDevItem(DevItems.TWITCH), beamItem = new BaseDevItem(DevItems.BEAM), theDragonTeamItem = new BaseDevItem(DevItems.THE_DRAGON_TEAM), moddedCityItem = new BaseDevItem(DevItems.MODDED_CITY), jonBamsItem = new BaseDevItem(DevItems.JON_BAMS);
    public static ItemCoalArrow itemCoalArrow = new ItemCoalArrow();
    public static ItemLapisArrow itemLapisArrow = new ItemLapisArrow();
    public static ItemRedstoneArrow itemRedstoneArrow = new ItemRedstoneArrow();
    public static ItemLavaArrow itemLavaArrow = new ItemLavaArrow();
    public static ItemEnderDragonArrow itemEnderDragonArrow = new ItemEnderDragonArrow();
    public static EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[]{HEAD, CHEST, LEGS, FEET};

    //  public static BaseItem[] templates = new BaseItem[16];
    //  public static String[] templateNames = new String[]{
    //          "coal_template", "lapis_template", "redstone_template",
    //          "emerald_template", "obsidian_template", "lava_template",
    //          "chicken_template", "slime_template",
    //          "guardian_template", "super_star_template", "ender_dragon_template", "the_ultimate_template",
    //          "cobalt_template", "ardite_template", "manyullyn_template", "knight_slime_template", "pig_iron_template"};

    public static void registerItems() {
        //Armors
        registerArmor(enableCoalArmor, coal, COAL);
        registerArmor(enableEmeraldArmor, emerald, EMERALD);
        registerArmor(enableLapisArmor, lapis, LAPIS);
        registerArmor(enableLavaArmor, lava, LAVA);
        registerArmor(enableObsidianArmor, obsidian, OBSIDIAN);
        registerArmor(enableRedstoneArmor, redstone, REDSTONE);
        registerArmor(enableChickenArmor, chicken, CHICKEN);
        registerArmor(enableSlimeArmor, slime, SLIME);
        registerArmor(enableEnderDragonArmor, enderDragon, ENDER_DRAGON);
        registerArmor(enableGuardianArmor, guardian, GUARDIAN);
        registerArmor(enableSuperStarArmor, superStar, SUPER_STAR);
        registerArmor(enableArditeArmor, ardite, ARDITE);
        registerArmor(enableCobaltArmor, cobalt, COBALT);
        registerArmor(enableManyullynArmor, manyullyn, MANYULLYN);
        registerArmor(enablePigIronArmor, pigIron, PIG_IRON);
        registerArmor(enableKnightSlimeArmor, knightSlime, KNIGHT_SLIME);
        registerArmor(enableTheUltimateArmor, theUltimate);
        //Swords
        registerSword(isSwordEnabled, sword, swordType);
        //BattleAxes
        registerBattleAxe(isBattleAxeEnabled, battleAxe, battleAxeType);
        //Bows
        registerBow(isBowEnabled, bow, bowType);
        // Arrays.setAll(templates, i -> (new BaseItem(templateNames[i])));
    }
}
