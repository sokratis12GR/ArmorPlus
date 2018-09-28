/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.armors.horse.ItemBaseHorseArmor;
import net.thedragonteam.armorplus.items.ItemUltimateParts;
import net.thedragonteam.armorplus.items.arrows.ArrowType;
import net.thedragonteam.armorplus.items.arrows.ItemSpecialArrow;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.books.ItemAPBook;
import net.thedragonteam.armorplus.items.consumables.ItemRedstoneApple;
import net.thedragonteam.armorplus.items.consumables.ItemTGOTG;
import net.thedragonteam.armorplus.items.dev.ItemDevTool;
import net.thedragonteam.armorplus.items.dev.ItemSpawnStructure;
import net.thedragonteam.armorplus.items.enums.MetalItems;
import net.thedragonteam.armorplus.items.materials.ItemLavaCrystal;
import net.thedragonteam.armorplus.items.materials.ItemMaterial;
import net.thedragonteam.armorplus.items.weapons.BattleAxes;
import net.thedragonteam.armorplus.items.weapons.Bows;
import net.thedragonteam.armorplus.items.weapons.Swords;

import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.armors.APArmorMaterial.*;
import static net.thedragonteam.armorplus.items.enums.Cosmetics.*;
import static net.thedragonteam.armorplus.registry.ModRegistryUtils.register;
import static net.thedragonteam.armorplus.worldgen.nbt.StructureGenNBT.TOWER;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModItems {

    public static ItemAPBook bookInfo = new ItemAPBook();
    public static ItemBase steelIngot = new ItemBase(MetalItems.STEEL_INGOT), electricalIngot = new ItemBase(MetalItems.ELECTRICAL_INGOT);
    public static ItemRedstoneApple itemRedstoneApple = new ItemRedstoneApple();
    public static ItemLavaCrystal itemLavaCrystal = new ItemLavaCrystal();
    public static ItemTGOTG itemTGOTG = new ItemTGOTG();
    public static ItemMaterial materials = new ItemMaterial();
    public static ItemAdvanced chainmail = new ItemAdvanced("chainmail", TextFormatting.GRAY, "Chainmail");
    public static ItemAdvanced guardianScale = new ItemAdvanced("guardian_scale", TextFormatting.AQUA, "Guardian Scale");
    public static ItemAdvanced witherBone = new ItemAdvanced("wither_bone", TextFormatting.WHITE, "Wither Bone");
    public static ItemAdvanced enderDragonScale = new ItemAdvanced("ender_dragon_scale", TextFormatting.DARK_PURPLE, "Ender Dragon Scale");
    public static ItemAdvanced theUltimateMaterial = new ItemAdvanced("the_ultimate_material", TextFormatting.GREEN, "The Ultimate Material");
    public static boolean[] isArmorEnabled = new boolean[]{
        global_registry.enableCoalArmor, global_registry.enableEmeraldArmor, global_registry.enableLapisArmor, global_registry.enableLavaArmor, global_registry.enableObsidianArmor, global_registry.enableRedstoneArmor,
        global_registry.enableChickenArmor, global_registry.enableSlimeArmor, global_registry.enableGuardianArmor, global_registry.enableSuperStarArmor, global_registry.enableEnderDragonArmor,
        global_registry.enableArditeArmor, global_registry.enableCobaltArmor, global_registry.enableManyullynArmor, global_registry.enablePigIronArmor, global_registry.enableKnightSlimeArmor
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
        global_registry.enableCoalWeapons[0], global_registry.enableLapisWeapons[0], global_registry.enableRedstoneWeapons[0], global_registry.enableEmeraldWeapons[0], global_registry.enableObsidianWeapons[0], global_registry.enableLavaWeapons[0], global_registry.enableGuardianWeapons[0], global_registry.enableSuperStarWeapons[0], global_registry.enableEnderDragonWeapons[0]
    };
    public static boolean[] isBattleAxeEnabled = new boolean[]{
        global_registry.enableCoalWeapons[1], global_registry.enableLapisWeapons[1], global_registry.enableRedstoneWeapons[1], global_registry.enableEmeraldWeapons[1], global_registry.enableObsidianWeapons[1], global_registry.enableLavaWeapons[1], global_registry.enableGuardianWeapons[1], global_registry.enableSuperStarWeapons[1], global_registry.enableEnderDragonWeapons[1]
    };
    public static boolean[] isBowEnabled = new boolean[]{
        global_registry.enableCoalWeapons[2], global_registry.enableLapisWeapons[2], global_registry.enableRedstoneWeapons[2], global_registry.enableEmeraldWeapons[2], global_registry.enableObsidianWeapons[2], global_registry.enableLavaWeapons[2], global_registry.enableGuardianWeapons[2], global_registry.enableSuperStarWeapons[2], global_registry.enableEnderDragonWeapons[2]
    };
    public static ItemArmorBase[] coal = new ItemArmorBase[4],
        emerald = new ItemArmorBase[4],
        obsidian = new ItemArmorBase[4],
        redstone = new ItemArmorBase[4],
        lapis = new ItemArmorBase[4],
        lava = new ItemArmorBase[4],
        chicken = new ItemArmorBase[4],
        slime = new ItemArmorBase[4],
        guardian = new ItemArmorBase[4],
        superStar = new ItemArmorBase[4],
        enderDragon = new ItemArmorBase[4],
        ardite = new ItemArmorBase[4],
        cobalt = new ItemArmorBase[4],
        manyullyn = new ItemArmorBase[4],
        pigIron = new ItemArmorBase[4],
        knightSlime = new ItemArmorBase[4];
    public static ItemUltimateArmor[] theUltimate = new ItemUltimateArmor[4];
    public static ItemSpecialSword[] sword = new ItemSpecialSword[9];
    public static ItemSpecialBattleAxe[] battleAxe = new ItemSpecialBattleAxe[9];
    public static ItemSpecialBow[] bow = new ItemSpecialBow[9];
    public static ItemUltimateParts theUltimateParts = new ItemUltimateParts();
    public static ItemDevTool itemDevTool = new ItemDevTool();
    public static ItemCosmetic twitchItem = new ItemCosmetic(TWITCH), beamItem = new ItemCosmetic(BEAM),
        theDragonTeamItem = new ItemCosmetic(THE_DRAGON_TEAM), moddedCityItem = new ItemCosmetic(MODDED_CITY),
        jonBamsItem = new ItemCosmetic(JON_BAMS), btmMoon = new ItemCosmetic(BTM_MOON), m1Jordan = new ItemCosmetic(M1JORDAN),
        teamRapture = new ItemCosmetic(TEAM_RAPTURE);
    public static ItemSpecialArrow itemCoalArrow = new ItemSpecialArrow(ArrowType.COAL),
        itemLapisArrow = new ItemSpecialArrow(ArrowType.LAPIS),
        itemRedstoneArrow = new ItemSpecialArrow(ArrowType.REDSTONE),
        itemLavaArrow = new ItemSpecialArrow(ArrowType.INFUSED_LAVA),
        itemEnderDragonArrow = new ItemSpecialArrow(ArrowType.ENDER_DRAGON);
    public static ItemBaseHorseArmor[] horseArmors = new ItemBaseHorseArmor[9];
    public static APArmorMaterial[] horseMaterial = new APArmorMaterial[]{COAL, LAPIS, REDSTONE, EMERALD, OBSIDIAN, LAVA, GUARDIAN, SUPER_STAR, ENDER_DRAGON};
    public static ItemSpawnStructure towerSpawnItem = new ItemSpawnStructure("tower_spawn_item", TOWER);

    public static void registerItems() {
        //HorseArmors
        IntStream.range(0, horseArmors.length).forEach(i -> horseArmors[i] = new ItemBaseHorseArmor(horseMaterial[i]));
        //Armors
        register(global_registry.enableChickenArmor, chicken, CHICKEN);
        register(global_registry.enableSlimeArmor, slime, SLIME);
        register(global_registry.enableCoalArmor, coal, COAL);
        register(global_registry.enableLapisArmor, lapis, LAPIS);
        register(global_registry.enableRedstoneArmor, redstone, REDSTONE);
        register(global_registry.enableEmeraldArmor, emerald, EMERALD);
        register(global_registry.enableObsidianArmor, obsidian, OBSIDIAN);
        register(global_registry.enableLavaArmor, lava, LAVA);
        register(global_registry.enableGuardianArmor, guardian, GUARDIAN);
        register(global_registry.enableSuperStarArmor, superStar, SUPER_STAR);
        register(global_registry.enableEnderDragonArmor, enderDragon, ENDER_DRAGON);
        register(global_registry.enableTheUltimateArmor, theUltimate);
        //Swords
        register(isSwordEnabled, sword, swordType);
        //BattleAxes
        register(isBattleAxeEnabled, battleAxe, battleAxeType);
        //Bows
        register(isBowEnabled, bow, bowType);
    }

    public static void registerTCItems() {
        register(global_registry.enableArditeArmor, ardite, ARDITE);
        register(global_registry.enableCobaltArmor, cobalt, COBALT);
        register(global_registry.enableManyullynArmor, manyullyn, MANYULLYN);
        register(global_registry.enablePigIronArmor, pigIron, PIG_IRON);
        register(global_registry.enableKnightSlimeArmor, knightSlime, KNIGHT_SLIME);
    }
}
