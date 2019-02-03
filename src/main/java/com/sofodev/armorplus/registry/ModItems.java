/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.caps.abilities.Material;
import com.sofodev.armorplus.items.ItemUltimateParts;
import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.items.armors.base.ItemArmorBase;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import com.sofodev.armorplus.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.items.armors.horse.ItemBaseHorseArmor;
import com.sofodev.armorplus.items.arrows.ArrowType;
import com.sofodev.armorplus.items.arrows.ItemSpecialArrow;
import com.sofodev.armorplus.items.base.*;
import com.sofodev.armorplus.items.books.ItemAPBook;
import com.sofodev.armorplus.items.consumables.ItemRedstoneApple;
import com.sofodev.armorplus.items.consumables.ItemTGOTG;
import com.sofodev.armorplus.items.dev.ItemDevTool;
import com.sofodev.armorplus.items.dev.ItemSpawnStructure;
import com.sofodev.armorplus.items.enums.MetalItems;
import com.sofodev.armorplus.items.materials.ItemLavaCrystal;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import com.sofodev.armorplus.items.weapons.BattleAxes;
import com.sofodev.armorplus.items.weapons.Bows;
import com.sofodev.armorplus.items.weapons.Swords;
import com.sofodev.armorplus.worldgen.nbt.StructureGenNBT;
import net.minecraft.util.text.TextFormatting;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
import static com.sofodev.armorplus.items.enums.Cosmetics.*;
import static com.sofodev.armorplus.registry.ModRegistryUtils.register;

/**
 * @author Sokratis Fotkatzikis
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
        Swords.COAL, Swords.LAPIS, Swords.REDSTONE, Swords.EMERALD, Swords.OBSIDIAN, Swords.INFUSED_LAVA, Swords.GUARDIAN, Swords.SUPER_STAR, Swords.ENDER_DRAGON
    };
    public static BattleAxes[] battleAxeType = new BattleAxes[]{
        BattleAxes.COAL, BattleAxes.LAPIS, BattleAxes.REDSTONE, BattleAxes.EMERALD, BattleAxes.OBSIDIAN, BattleAxes.INFUSED_LAVA, BattleAxes.GUARDIAN, BattleAxes.SUPER_STAR, BattleAxes.ENDER_DRAGON
    };
    public static Bows[] bowType = new Bows[]{
        Bows.COAL, Bows.LAPIS, Bows.REDSTONE, Bows.EMERALD, Bows.OBSIDIAN, Bows.INFUSED_LAVA, Bows.GUARDIAN, Bows.SUPER_STAR, Bows.ENDER_DRAGON
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
        lapis = new ItemArmorBase[4],
        redstone = new ItemArmorBase[4],
        obsidian = new ItemArmorBase[4],
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
    public static APArmorMaterial[] horseMaterial = new APArmorMaterial[]{APArmorMaterial.COAL, APArmorMaterial.LAPIS, APArmorMaterial.REDSTONE, APArmorMaterial.EMERALD, APArmorMaterial.OBSIDIAN, APArmorMaterial.INFUSED_LAVA, APArmorMaterial.GUARDIAN, APArmorMaterial.SUPER_STAR, APArmorMaterial.ENDER_DRAGON};
    public static ItemSpawnStructure towerSpawnItem = new ItemSpawnStructure("tower_spawn_item", StructureGenNBT.TOWER);
    public static ItemSpawnStructure enderDungeonFloor1SpawnItem = new ItemSpawnStructure("ender_dungeon_floor_1_spawn_item", StructureGenNBT.ENDER_DUNGEON_FLOOR_1);
    public static ItemArmorV2[] coalExp = new ItemArmorV2[4],
        chickenExp = new ItemArmorV2[4],
        slimeExp = new ItemArmorV2[4],
        emeraldExp = new ItemArmorV2[4],
        lapisExp = new ItemArmorV2[4],
        redstoneExp = new ItemArmorV2[4],
        obsidianExp = new ItemArmorV2[4],
        lavaExp = new ItemArmorV2[4],
        guardianExp = new ItemArmorV2[4],
        superStarExp = new ItemArmorV2[4],
        enderDragonExp = new ItemArmorV2[4],
        arditeExp = new ItemArmorV2[4],
        cobaltExp = new ItemArmorV2[4],
        manyullynExp = new ItemArmorV2[4],
        pigIronExp = new ItemArmorV2[4],
        knightSlimeExp = new ItemArmorV2[4],
        ultimateExp = new ItemArmorV2[4];

    public static void registerItems() {
        //HorseArmors
        IntStream.range(0, horseArmors.length).forEach(i -> horseArmors[i] = new ItemBaseHorseArmor(horseMaterial[i]));
        //Armors
        register(global_registry.enableChickenArmor, chicken, APArmorMaterial.CHICKEN);
        register(global_registry.enableSlimeArmor, slime, APArmorMaterial.SLIME);
        register(global_registry.enableCoalArmor, coal, APArmorMaterial.COAL);
        register(global_registry.enableLapisArmor, lapis, APArmorMaterial.LAPIS);
        register(global_registry.enableRedstoneArmor, redstone, APArmorMaterial.REDSTONE);
        register(global_registry.enableEmeraldArmor, emerald, APArmorMaterial.EMERALD);
        register(global_registry.enableObsidianArmor, obsidian, APArmorMaterial.OBSIDIAN);
        register(global_registry.enableLavaArmor, lava, APArmorMaterial.INFUSED_LAVA);
        register(global_registry.enableGuardianArmor, guardian, APArmorMaterial.GUARDIAN);
        register(global_registry.enableSuperStarArmor, superStar, APArmorMaterial.SUPER_STAR);
        register(global_registry.enableEnderDragonArmor, enderDragon, APArmorMaterial.ENDER_DRAGON);
        register(global_registry.enableTheUltimateArmor, theUltimate);

        if (enableExperimentalMode) {
            register(chickenExp, Material.CHICKEN);
            register(slimeExp, Material.SLIME);
            register(coalExp, Material.COAL);
            register(lapisExp, Material.LAPIS);
            register(redstoneExp, Material.REDSTONE);
            register(emeraldExp, Material.EMERALD);
            register(obsidianExp, Material.OBSIDIAN);
            register(lavaExp, Material.INFUSED_LAVA);
            register(guardianExp, Material.GUARDIAN);
            register(superStarExp, Material.SUPER_STAR);
            register(enderDragonExp, Material.ENDER_DRAGON);
            register(ultimateExp, Material.ULTIMATE);
            register(arditeExp, Material.ARDITE);
            register(cobaltExp, Material.COBALT);
            register(manyullynExp, Material.MANYULLYN);
            register(pigIronExp, Material.PIG_IRON);
            register(knightSlimeExp, Material.KNIGHT_SLIME);
        }

        //Swords
        register(isSwordEnabled, sword, swordType);
        //BattleAxes
        register(isBattleAxeEnabled, battleAxe, battleAxeType);
        //Bows
        register(isBowEnabled, bow, bowType);
    }

    public static void registerTCItems() {
        //    if (!enableExperimentalMode) {
        register(global_registry.enableArditeArmor, ardite, APArmorMaterial.ARDITE);
        register(global_registry.enableCobaltArmor, cobalt, APArmorMaterial.COBALT);
        register(global_registry.enableManyullynArmor, manyullyn, APArmorMaterial.MANYULLYN);
        register(global_registry.enablePigIronArmor, pigIron, APArmorMaterial.PIG_IRON);
        register(global_registry.enableKnightSlimeArmor, knightSlime, APArmorMaterial.KNIGHT_SLIME);
        //    }
    }
}
