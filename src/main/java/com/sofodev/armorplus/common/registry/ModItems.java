/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.api.caps.abilities.MaterialType;
import com.sofodev.armorplus.common.registry.items.ItemCombinedMap;
import com.sofodev.armorplus.common.registry.items.ItemFragment;
import com.sofodev.armorplus.common.registry.items.ItemFragment.Fragments;
import com.sofodev.armorplus.common.registry.items.ItemUltimateParts;
import com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemArmorV2;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemEnhancedArmor;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemSpecialArmor;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.common.registry.items.armors.horse.ItemBaseHorseArmor;
import com.sofodev.armorplus.common.registry.items.arrows.ArrowType;
import com.sofodev.armorplus.common.registry.items.arrows.ItemSpecialArrow;
import com.sofodev.armorplus.common.registry.items.base.*;
import com.sofodev.armorplus.common.registry.items.base.special.Pickaxes;
import com.sofodev.armorplus.common.registry.items.books.ItemAPBook;
import com.sofodev.armorplus.common.registry.items.books.ItemLoreBook;
import com.sofodev.armorplus.common.registry.items.consumables.ItemRedstoneApple;
import com.sofodev.armorplus.common.registry.items.consumables.ItemTGOTG;
import com.sofodev.armorplus.common.registry.items.dev.ItemDevTool;
import com.sofodev.armorplus.common.registry.items.dev.ItemSpawnStructure;
import com.sofodev.armorplus.common.registry.items.enums.MetalItems;
import com.sofodev.armorplus.common.registry.items.materials.ItemLavaCrystal;
import com.sofodev.armorplus.common.registry.items.materials.ItemMaterial;
import com.sofodev.armorplus.common.util.Utils;
import com.sofodev.armorplus.common.worldgen.nbt.StructureGenNBT;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.common.registry.ModBlocks.*;
import static com.sofodev.armorplus.common.registry.items.ItemCombinedMap.Variants;
import static com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial.*;
import static com.sofodev.armorplus.common.registry.items.armors.ArmorMaterials.*;
import static com.sofodev.armorplus.common.registry.items.enums.Cosmetics.*;
import static com.sofodev.armorplus.common.util.ModRegistryUtils.register;
import static com.sofodev.armorplus.common.util.ModRegistryUtils.registerAll;
import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = MODID)
public class ModItems {

    public static ItemAPBook bookInfo = new ItemAPBook();
    public static ItemLoreBook bookLore = new ItemLoreBook();
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
    public static ItemSpecialArmor[] coal = new ItemSpecialArmor[4],
        emerald = new ItemSpecialArmor[4],
        lapis = new ItemSpecialArmor[4],
        redstone = new ItemSpecialArmor[4],
        obsidian = new ItemSpecialArmor[4],
        lava = new ItemSpecialArmor[4],
        chicken = new ItemSpecialArmor[4],
        slime = new ItemSpecialArmor[4],
        guardian = new ItemSpecialArmor[4],
        superStar = new ItemSpecialArmor[4],
        enderDragon = new ItemSpecialArmor[4],
        ardite = new ItemSpecialArmor[4],
        cobalt = new ItemSpecialArmor[4],
        manyullyn = new ItemSpecialArmor[4],
        pigIron = new ItemSpecialArmor[4],
        knightSlime = new ItemSpecialArmor[4];
    public static ItemUltimateArmor[] theUltimate = new ItemUltimateArmor[4];
    public static ItemSpecialSword[] sword = new ItemSpecialSword[9];
    public static ItemSpecialBattleAxe[] battleAxe = new ItemSpecialBattleAxe[9];
    public static ItemSpecialBow[] bow = new ItemSpecialBow[9];
    public static ItemSpecialPickaxe[] pickaxe = new ItemSpecialPickaxe[9];
    public static ItemUltimateParts theUltimateParts = new ItemUltimateParts();
    public static ItemDevTool itemDevTool = new ItemDevTool();
    public static ItemCosmetic twitchItem = new ItemCosmetic(TWITCH), beamItem = new ItemCosmetic(BEAM),
        theDragonTeamItem = new ItemCosmetic(THE_DRAGON_TEAM), moddedCityItem = new ItemCosmetic(MODDED_CITY),
        jonBamsItem = new ItemCosmetic(JON_BAMS), btmMoon = new ItemCosmetic(BTM_MOON), m1Jordan = new ItemCosmetic(M1JORDAN),
        teamRapture = new ItemCosmetic(TEAM_RAPTURE);
    public static ItemSpecialArrow itemCoalArrow = new ItemSpecialArrow(ArrowType.COAL),
        itemLapisArrow = new ItemSpecialArrow(ArrowType.LAPIS),
        itemRedstoneArrow = new ItemSpecialArrow(ArrowType.REDSTONE),
        itemEmeraldArrow = new ItemSpecialArrow(ArrowType.EMERALD),
        itemObsidianArrow = new ItemSpecialArrow(ArrowType.OBSIDIAN),
        itemLavaArrow = new ItemSpecialArrow(ArrowType.INFUSED_LAVA),
        itemGuardianArrow = new ItemSpecialArrow(ArrowType.GUARDIAN),
        itemSuperStarArrow = new ItemSpecialArrow(ArrowType.SUPER_STAR),
        itemEnderDragonArrow = new ItemSpecialArrow(ArrowType.ENDER_DRAGON);
    public static ItemBaseHorseArmor[] horseArmors = new ItemBaseHorseArmor[9];
    public static APArmorMaterial[] horseMaterial = new APArmorMaterial[]{COAL, LAPIS, REDSTONE, EMERALD, OBSIDIAN, INFUSED_LAVA, GUARDIAN, SUPER_STAR, ENDER_DRAGON};
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
    public static ItemEnhancedArmor[] iron = new ItemEnhancedArmor[4],
        chain = new ItemEnhancedArmor[4],
        gold = new ItemEnhancedArmor[4],
        diamond = new ItemEnhancedArmor[4];
    public static ItemFragment[] fragments = new ItemFragment[4];
    public static ItemCombinedMap[] maps = new ItemCombinedMap[Variants.values().length];
    //public static ItemBase obsidianStick,guardianStone,dragonBornStick, witheredStick;

    public static void registerItems() {
        //HorseArmors
        IntStream.range(0, horseArmors.length).forEach(i -> horseArmors[i] = new ItemBaseHorseArmor(horseMaterial[i]));
        //Armors
        register(chicken, CHICKEN);
        register(slime, SLIME);
        register(coal, COAL);
        register(lapis, LAPIS);
        register(redstone, REDSTONE);
        register(emerald, EMERALD);
        register(obsidian, OBSIDIAN);
        register(lava, INFUSED_LAVA);
        register(guardian, GUARDIAN);
        register(superStar, SUPER_STAR);
        register(enderDragon, ENDER_DRAGON);
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

        register(chain, ENHANCED_CHAIN_ARMOR, "chainmail");
        register(iron, ENHANCED_IRON_ARMOR, "iron");
        register(gold, ENHANCED_GOLD_ARMOR, "gold");
        register(diamond, ENHANCED_DIAMOND_ARMOR, "diamond");

        IntStream.range(0, pickaxe.length).forEach(i -> pickaxe[i] = new ItemSpecialPickaxe(Pickaxes.values()[i]));
        IntStream.range(0, fragments.length).forEach(i -> fragments[i] = new ItemFragment(Fragments.values()[i]));
        IntStream.range(0, maps.length).forEach(i -> maps[i] = new ItemCombinedMap(Variants.values()[i]));
    }

    public static void registerTCItems() {
        register(ardite, ARDITE);
        register(cobalt, COBALT);
        register(manyullyn, MANYULLYN);
        register(pigIron, PIG_IRON);
        register(knightSlime, KNIGHT_SLIME);
    }


    ////////////////////////////
    //   Items & ItemBlocks   //
    ////////////////////////////
    private static void registerItemBlock(RegistryEvent.Register<Item> event, ResourceLocation... registryNames) {
        Arrays.stream(registryNames).forEachOrdered(regName -> {
            Block block = ForgeRegistries.BLOCKS.getValue(regName);
            if (Utils.areNotNull(block, block.getRegistryName())) {
                ItemBlock itemBlock = new ItemBlock(block);
                itemBlock.setRegistryName(block.getRegistryName());
                event.getRegistry().register(itemBlock);
            }
        });
    }

    private static void registerItemBlock(RegistryEvent.Register<Item> event, String... locations) {
        Arrays.stream(locations).forEach(name -> registerItemBlock(event, setRL(name)));
    }

    private static void registerItemBlock(RegistryEvent.Register<Item> event, Block... blocks) {
        Arrays.stream(blocks).forEach(block -> registerItemBlock(event, block.getRegistryName()));
    }

    private static void registerAllItemBlocks(RegistryEvent.Register<Item> event, Block[]... blockArray) {
        Arrays.stream(blockArray).forEachOrdered(blockList -> registerItemBlock(event, blockList));
    }

    private static void registerAllItems(RegistryEvent.Register<Item> event, Item[]... itemsArray) {
        Arrays.stream(itemsArray).forEachOrdered(itemList -> registerAllItems(event, itemList));
    }

    private static void registerAllItems(RegistryEvent.Register<Item> event, Item... itemsArray) {
        Arrays.stream(itemsArray).filter(Utils::isNotNull).forEachOrdered(anItemsArray -> event.getRegistry().register(anItemsArray));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // ==== BLOCKS ==== \\
        registerItemBlock(event, benches);
        registerItemBlock(event,
            oreLavaCrystal, blockCompressedObsidian, steelBlock, electricalBlock, blockLavaNetherBrick, lavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal, blockMeltingObsidian
        );
        registerAllItemBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        registerItemBlock(event, trophies);
        registerItemBlock(event, blockSwordDisplays);
        registerItemBlock(event, blockEmptyDisplay);
        // ==== DUNGEON BLOCKS ==== \\
        registerItemBlock(event, enderBlocks);
        // ==== ITEMS ==== \\
        registerAllItems(event,
            bookInfo, bookLore, steelIngot, electricalIngot, itemRedstoneApple, itemLavaCrystal, itemTGOTG, itemDevTool, theUltimateParts,
            itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemEmeraldArrow, itemObsidianArrow, itemLavaArrow, itemGuardianArrow, itemSuperStarArrow, itemEnderDragonArrow
        );
        registerAllItems(event, materials);
        // ==== SPECIAL ITEMS ===\\
        registerAllItems(event, towerSpawnItem, enderDungeonFloor1SpawnItem);
        // ==== COSMETICS ==== \\
        registerAllItems(event, twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, btmMoon, m1Jordan, teamRapture);
        // ==== GEAR ==== \\
        registerAllItems(event,
            coal, lapis, redstone, emerald, obsidian, lava, chicken, slime, guardian, superStar, enderDragon, theUltimate, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        if (enableExperimentalMode) {
            registerAllItems(event,
                coalExp, lapisExp, redstoneExp, emeraldExp, obsidianExp, lavaExp,
                chickenExp, slimeExp, guardianExp, superStarExp, enderDragonExp, ultimateExp,
                arditeExp, cobaltExp, manyullynExp, pigIronExp, knightSlimeExp
            );
        }
        registerAllItems(event, horseArmors);
        registerAllItems(event, sword, battleAxe, bow);
        registerAllItems(event, chain, iron, gold, diamond);
        registerAllItems(event, pickaxe, fragments, maps);
    }
}
