/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.caps.abilities.MaterialType;
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
import com.sofodev.armorplus.worldgen.nbt.StructureGenNBT;
import net.minecraft.util.text.TextFormatting;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.items.enums.Cosmetics.*;
import static com.sofodev.armorplus.registry.ModRegistryUtils.register;
import static com.sofodev.armorplus.registry.ModRegistryUtils.registerAll;

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
    public static ItemAdvanced theUltimateMaterial = new ItemAdvanced("the_ultimate_material", TextFormatting.GREEN, "The Ultimate MaterialType");
    // public static Swords[] swordType = new Swords[]{
    //     Swords.COAL, Swords.LAPIS, Swords.REDSTONE, Swords.EMERALD, Swords.OBSIDIAN, Swords.INFUSED_LAVA, Swords.GUARDIAN, Swords.SUPER_STAR, Swords.ENDER_DRAGON
    // };
    // public static BattleAxes[] battleAxeType = new BattleAxes[]{
    //     BattleAxes.COAL, BattleAxes.LAPIS, BattleAxes.REDSTONE, BattleAxes.EMERALD, BattleAxes.OBSIDIAN, BattleAxes.INFUSED_LAVA, BattleAxes.GUARDIAN, BattleAxes.SUPER_STAR, BattleAxes.ENDER_DRAGON
    // };
    // public static Bows[] bowType = new Bows[]{
    //     Bows.COAL, Bows.LAPIS, Bows.REDSTONE, Bows.EMERALD, Bows.OBSIDIAN, Bows.INFUSED_LAVA, Bows.GUARDIAN, Bows.SUPER_STAR, Bows.ENDER_DRAGON
    // };
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
    //public static ItemBase obsidianStick,guardianStone,dragonBornStick, witheredStick;

    public static void registerItems() {
        //HorseArmors
        IntStream.range(0, horseArmors.length).forEach(i -> horseArmors[i] = new ItemBaseHorseArmor(horseMaterial[i]));
        //Armors
        register(chicken, APArmorMaterial.CHICKEN);
        register(slime, APArmorMaterial.SLIME);
        register(coal, APArmorMaterial.COAL);
        register(lapis, APArmorMaterial.LAPIS);
        register(redstone, APArmorMaterial.REDSTONE);
        register(emerald, APArmorMaterial.EMERALD);
        register(obsidian, APArmorMaterial.OBSIDIAN);
        register(lava, APArmorMaterial.INFUSED_LAVA);
        register(guardian, APArmorMaterial.GUARDIAN);
        register(superStar, APArmorMaterial.SUPER_STAR);
        register(enderDragon, APArmorMaterial.ENDER_DRAGON);
        registerAll(theUltimate);
        registerAll(sword);
        registerAll(battleAxe);
        registerAll(bow);

        if (enableExperimentalMode) {
            register(chickenExp, MaterialType.CHICKEN);
            register(slimeExp, MaterialType.SLIME);
            register(coalExp, MaterialType.COAL);
            register(lapisExp, MaterialType.LAPIS);
            register(redstoneExp, MaterialType.REDSTONE);
            register(emeraldExp, MaterialType.EMERALD);
            register(obsidianExp, MaterialType.OBSIDIAN);
            register(lavaExp, MaterialType.INFUSED_LAVA);
            register(guardianExp, MaterialType.GUARDIAN);
            register(superStarExp, MaterialType.SUPER_STAR);
            register(enderDragonExp, MaterialType.ENDER_DRAGON);
            register(ultimateExp, MaterialType.ULTIMATE);
            register(arditeExp, MaterialType.ARDITE);
            register(cobaltExp, MaterialType.COBALT);
            register(manyullynExp, MaterialType.MANYULLYN);
            register(pigIronExp, MaterialType.PIG_IRON);
            register(knightSlimeExp, MaterialType.KNIGHT_SLIME);
        }
    }

    public static void registerTCItems() {
        register(ardite, APArmorMaterial.ARDITE);
        register(cobalt, APArmorMaterial.COBALT);
        register(manyullyn, APArmorMaterial.MANYULLYN);
        register(pigIron, APArmorMaterial.PIG_IRON);
        register(knightSlime, APArmorMaterial.KNIGHT_SLIME);
    }
}
