/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.blocks.benches.Benches;
import com.sofodev.armorplus.common.blocks.benches.BlockBench;
import com.sofodev.armorplus.common.blocks.castle.BrickColor;
import com.sofodev.armorplus.common.blocks.castle.base.BlockStoneBrick;
import com.sofodev.armorplus.common.blocks.castle.base.BlockStoneBrickCorner;
import com.sofodev.armorplus.common.blocks.castle.base.BlockStoneBrickTower;
import com.sofodev.armorplus.common.blocks.castle.base.BlockStoneBrickWall;
import com.sofodev.armorplus.common.blocks.dungeon.BlockDungeonEnder;
import com.sofodev.armorplus.common.blocks.dungeon.EnderType;
import com.sofodev.armorplus.common.blocks.lava.*;
import com.sofodev.armorplus.common.blocks.metals.BlockMetal;
import com.sofodev.armorplus.common.blocks.normal.BlockCompressedObsidian;
import com.sofodev.armorplus.common.blocks.special.BlockTrophy;
import com.sofodev.armorplus.common.blocks.special.Trophy;
import com.sofodev.armorplus.common.tileentity.*;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.blocks.benches.Benches.*;
import static com.sofodev.armorplus.common.blocks.castle.BrickColor.*;
import static com.sofodev.armorplus.common.blocks.dungeon.EnderType.*;
import static com.sofodev.armorplus.common.blocks.lava.BlockLavaMaterial.LavaMaterial.*;
import static com.sofodev.armorplus.common.blocks.metals.Metals.ELECTRICAL;
import static com.sofodev.armorplus.common.blocks.metals.Metals.STEEL;
import static com.sofodev.armorplus.common.blocks.special.Trophy.*;
import static com.sofodev.armorplus.common.tileentity.TileLavaInfuser.registerFixesLavaInfuser;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static java.util.Arrays.setAll;
import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

/**
 * @author Sokratis Fotkatzikis
 **/
@Mod.EventBusSubscriber(modid = MODID)
public class ModBlocks {

    public static BlockCrystalOre oreLavaCrystal = new BlockCrystalOre();
    public static BlockCompressedObsidian blockCompressedObsidian = new BlockCompressedObsidian();
    public static BlockLavaCactus lavaCactus = new BlockLavaCactus();
    public static BlockMetal steelBlock = new BlockMetal(STEEL);
    public static BlockMetal electricalBlock = new BlockMetal(ELECTRICAL);
    public static BlockLavaNetherBrick blockLavaNetherBrick = new BlockLavaNetherBrick();
    public static Benches[] benchTypes = new Benches[]{WORKBENCH, HIGH_TECH_BENCH, ULTI_TECH_BENCH, CHAMPION_BENCH};
    public static BlockBench[] benches = new BlockBench[4];
    public static BrickColor[] stoneBrickTypes = new BrickColor[]{
        WHITE, RED, BLACK, BLUE, GREEN, YELLOW, PURPLE
    };
    public static BlockStoneBrick[] stoneBricks = new BlockStoneBrick[7];
    public static BlockStoneBrickTower[] stoneBrickTowers = new BlockStoneBrickTower[7];
    public static BlockStoneBrickCorner[] stoneBrickCorners = new BlockStoneBrickCorner[7];
    public static BlockStoneBrickWall[] stonebrickWalls = new BlockStoneBrickWall[7];
    public static BlockLavaInfuser lavaInfuser = new BlockLavaInfuser("lava_infuser", false);
    public static BlockLavaInfuser lavaInfuserInfusing = new BlockLavaInfuser("lava_infuser_infusing", true);
    public static BlockLavaMaterial blockLavaCrystal = new BlockLavaMaterial(LAVA_CRYSTAL);
    public static BlockLavaMaterial blockInfusedLavaCrystal = new BlockLavaMaterial(INFUSED_LAVA_CRYSTAL);
    public static BlockLavaMaterial blockCompressedLavaCrystal = new BlockLavaMaterial(COMPRESSED_LAVA_CRYSTAL);
    public static BlockLavaMaterial blockCompressedInfusedLavaCrystal = new BlockLavaMaterial(COMPRESSED_INFUSED_LAVA_CRYSTAL);
    public static BlockLavaMaterial blockLavaInfusedObsidian = new BlockLavaMaterial(LAVA_INFUSED_OBSIDIAN);
    //  public static BlockRitualAltar ritualAltar = new BlockRitualAltar();
    public static EnderType[] enderTypes = new EnderType[]{
        ENDER_STONE, ENDER_STONE_BRICKS, ENDER_PILLAR, ENDER_GLOWSTONE, ENDER_FLOOR_1, ENDER_FLOOR_2, ENDER_STONE_TRAP
    };
    public static BlockDungeonEnder[] enderBlocks = new BlockDungeonEnder[7];
    public static BlockTrophy[] trophies = new BlockTrophy[8];
    public static Trophy[] types = new Trophy[]{
        ANY, ELDER_GUARDIAN, WITHER_BOSS, ENDER_DRAGON, SKELETAL_KING, GUARDIAN_OVERLORD, DEMONIC_DRAGON, THE_LORD_OF_EVERYTHING
    };
    public static BlockMeltingObsidian blockMeltingObsidian = new BlockMeltingObsidian();
    // public static BlockBTMMoon blockBTMMoon = new BlockBTMMoon();

    public static void registerBlocks() {
        setAll(benches, type -> new BlockBench(benchTypes[type]));
        setAll(stoneBricks, type -> new BlockStoneBrick(stoneBrickTypes[type]));
        setAll(stoneBrickTowers, type -> new BlockStoneBrickTower(stoneBrickTypes[type]));
        setAll(stoneBrickCorners, type -> new BlockStoneBrickCorner(stoneBrickTypes[type], stoneBricks[type].getDefaultState()));
        setAll(stonebrickWalls, type -> new BlockStoneBrickWall(stoneBricks[type]));
        setAll(enderBlocks, type -> new BlockDungeonEnder(enderTypes[type]));
        setAll(trophies, type -> new BlockTrophy(types[type]));
    }

    /**
     * Blocks
     */
    private static void registerAllBlocks(RegistryEvent.Register<Block> event, Block[]... blocksArray) {
        Arrays.stream(blocksArray).forEachOrdered(blockList -> registerAllBlocks(event, blockList));
    }

    private static void registerAllBlocks(RegistryEvent.Register<Block> event, Block... blockList) {
        Arrays.stream(blockList).filter(Utils::isNotNull).forEachOrdered(block -> event.getRegistry().register(block));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        registerAllBlocks(event, benches);
        registerAllBlocks(event,
            oreLavaCrystal, blockCompressedObsidian, steelBlock, electricalBlock, blockLavaNetherBrick, lavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal, blockMeltingObsidian
        );
        registerAllBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //registerAllBlocks(event, blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerAllBlocks(event, enderBlocks);
        registerAllBlocks(event, trophies);
        registerTileEntities();
        registerTEFixes();
    }

    private static void registerTEFixes() {
        DataFixer dataFixer = FMLCommonHandler.instance().getDataFixer();
        registerFixesLavaInfuser(dataFixer);
    }

    private static void registerTileEntities() {
        registerTileEntity(TileLavaInfuser.class, setRL("lava_infuser_tile_entity"));
        registerTileEntity(TileWB.class, setRL("workbench_tile_entity"));
        registerTileEntity(TileHTB.class, setRL("high_tech_bench_tile_entity"));
        registerTileEntity(TileUTB.class, setRL("ulti_tech_tile_entity"));
        registerTileEntity(TileCB.class, setRL("champion_tile_entity"));
        registerTileEntity(TileTrophy.class, setRL("trophy_tile_entity"));
    }
}