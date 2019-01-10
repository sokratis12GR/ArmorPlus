/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.armors.APArmorMaterial;
import com.sofodev.armorplus.armors.base.ItemArmorBase;
import com.sofodev.armorplus.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.armors.horse.ItemBaseHorseArmor;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.items.ItemUltimateParts;
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
        ModConfig.RegistryConfig.global_registry.enableCoalArmor, ModConfig.RegistryConfig.global_registry.enableEmeraldArmor, ModConfig.RegistryConfig.global_registry.enableLapisArmor, ModConfig.RegistryConfig.global_registry.enableLavaArmor, ModConfig.RegistryConfig.global_registry.enableObsidianArmor, ModConfig.RegistryConfig.global_registry.enableRedstoneArmor,
        ModConfig.RegistryConfig.global_registry.enableChickenArmor, ModConfig.RegistryConfig.global_registry.enableSlimeArmor, ModConfig.RegistryConfig.global_registry.enableGuardianArmor, ModConfig.RegistryConfig.global_registry.enableSuperStarArmor, ModConfig.RegistryConfig.global_registry.enableEnderDragonArmor,
        ModConfig.RegistryConfig.global_registry.enableArditeArmor, ModConfig.RegistryConfig.global_registry.enableCobaltArmor, ModConfig.RegistryConfig.global_registry.enableManyullynArmor, ModConfig.RegistryConfig.global_registry.enablePigIronArmor, ModConfig.RegistryConfig.global_registry.enableKnightSlimeArmor
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
        ModConfig.RegistryConfig.global_registry.enableCoalWeapons[0], ModConfig.RegistryConfig.global_registry.enableLapisWeapons[0], ModConfig.RegistryConfig.global_registry.enableRedstoneWeapons[0], ModConfig.RegistryConfig.global_registry.enableEmeraldWeapons[0], ModConfig.RegistryConfig.global_registry.enableObsidianWeapons[0], ModConfig.RegistryConfig.global_registry.enableLavaWeapons[0], ModConfig.RegistryConfig.global_registry.enableGuardianWeapons[0], ModConfig.RegistryConfig.global_registry.enableSuperStarWeapons[0], ModConfig.RegistryConfig.global_registry.enableEnderDragonWeapons[0]
    };
    public static boolean[] isBattleAxeEnabled = new boolean[]{
        ModConfig.RegistryConfig.global_registry.enableCoalWeapons[1], ModConfig.RegistryConfig.global_registry.enableLapisWeapons[1], ModConfig.RegistryConfig.global_registry.enableRedstoneWeapons[1], ModConfig.RegistryConfig.global_registry.enableEmeraldWeapons[1], ModConfig.RegistryConfig.global_registry.enableObsidianWeapons[1], ModConfig.RegistryConfig.global_registry.enableLavaWeapons[1], ModConfig.RegistryConfig.global_registry.enableGuardianWeapons[1], ModConfig.RegistryConfig.global_registry.enableSuperStarWeapons[1], ModConfig.RegistryConfig.global_registry.enableEnderDragonWeapons[1]
    };
    public static boolean[] isBowEnabled = new boolean[]{
        ModConfig.RegistryConfig.global_registry.enableCoalWeapons[2], ModConfig.RegistryConfig.global_registry.enableLapisWeapons[2], ModConfig.RegistryConfig.global_registry.enableRedstoneWeapons[2], ModConfig.RegistryConfig.global_registry.enableEmeraldWeapons[2], ModConfig.RegistryConfig.global_registry.enableObsidianWeapons[2], ModConfig.RegistryConfig.global_registry.enableLavaWeapons[2], ModConfig.RegistryConfig.global_registry.enableGuardianWeapons[2], ModConfig.RegistryConfig.global_registry.enableSuperStarWeapons[2], ModConfig.RegistryConfig.global_registry.enableEnderDragonWeapons[2]
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
    public static APArmorMaterial[] horseMaterial = new APArmorMaterial[]{APArmorMaterial.COAL, APArmorMaterial.LAPIS, APArmorMaterial.REDSTONE, APArmorMaterial.EMERALD, APArmorMaterial.OBSIDIAN, APArmorMaterial.INFUSED_LAVA, APArmorMaterial.GUARDIAN, APArmorMaterial.SUPER_STAR, APArmorMaterial.ENDER_DRAGON};
    public static ItemSpawnStructure towerSpawnItem = new ItemSpawnStructure("tower_spawn_item", StructureGenNBT.TOWER);

    public static void registerItems() {
        //HorseArmors
        IntStream.range(0, horseArmors.length).forEach(i -> horseArmors[i] = new ItemBaseHorseArmor(horseMaterial[i]));
        //Armors
        register(ModConfig.RegistryConfig.global_registry.enableChickenArmor, chicken, APArmorMaterial.CHICKEN);
        register(ModConfig.RegistryConfig.global_registry.enableSlimeArmor, slime, APArmorMaterial.SLIME);
        register(ModConfig.RegistryConfig.global_registry.enableCoalArmor, coal, APArmorMaterial.COAL);
        register(ModConfig.RegistryConfig.global_registry.enableLapisArmor, lapis, APArmorMaterial.LAPIS);
        register(ModConfig.RegistryConfig.global_registry.enableRedstoneArmor, redstone, APArmorMaterial.REDSTONE);
        register(ModConfig.RegistryConfig.global_registry.enableEmeraldArmor, emerald, APArmorMaterial.EMERALD);
        register(ModConfig.RegistryConfig.global_registry.enableObsidianArmor, obsidian, APArmorMaterial.OBSIDIAN);
        register(ModConfig.RegistryConfig.global_registry.enableLavaArmor, lava, APArmorMaterial.INFUSED_LAVA);
        register(ModConfig.RegistryConfig.global_registry.enableGuardianArmor, guardian, APArmorMaterial.GUARDIAN);
        register(ModConfig.RegistryConfig.global_registry.enableSuperStarArmor, superStar, APArmorMaterial.SUPER_STAR);
        register(ModConfig.RegistryConfig.global_registry.enableEnderDragonArmor, enderDragon, APArmorMaterial.ENDER_DRAGON);
        register(ModConfig.RegistryConfig.global_registry.enableTheUltimateArmor, theUltimate);
        //Swords
        register(isSwordEnabled, sword, swordType);
        //BattleAxes
        register(isBattleAxeEnabled, battleAxe, battleAxeType);
        //Bows
        register(isBowEnabled, bow, bowType);
    }

    public static void registerTCItems() {
        register(ModConfig.RegistryConfig.global_registry.enableArditeArmor, ardite, APArmorMaterial.ARDITE);
        register(ModConfig.RegistryConfig.global_registry.enableCobaltArmor, cobalt, APArmorMaterial.COBALT);
        register(ModConfig.RegistryConfig.global_registry.enableManyullynArmor, manyullyn, APArmorMaterial.MANYULLYN);
        register(ModConfig.RegistryConfig.global_registry.enablePigIronArmor, pigIron, APArmorMaterial.PIG_IRON);
        register(ModConfig.RegistryConfig.global_registry.enableKnightSlimeArmor, knightSlime, APArmorMaterial.KNIGHT_SLIME);
    }
}
