/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.common.registry.blocks.benches.Benches;
import com.sofodev.armorplus.common.registry.blocks.benches.BlockBench;
import com.sofodev.armorplus.common.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrick;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrickCorner;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrickTower;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrickWall;
import com.sofodev.armorplus.common.registry.blocks.dungeon.BlockDungeonEnder;
import com.sofodev.armorplus.common.registry.blocks.dungeon.EnderType;
import com.sofodev.armorplus.common.registry.blocks.lava.*;
import com.sofodev.armorplus.common.registry.blocks.metals.BlockMetal;
import com.sofodev.armorplus.common.registry.blocks.normal.BlockCompressedObsidian;
import com.sofodev.armorplus.common.registry.blocks.special.BlockEmptyDisplay;
import com.sofodev.armorplus.common.registry.blocks.special.BlockSwordDisplay;
import com.sofodev.armorplus.common.registry.blocks.special.BlockTrophy;
import com.sofodev.armorplus.common.registry.blocks.special.Trophy;
import com.sofodev.armorplus.common.registry.items.base.special.Swords;
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
import static com.sofodev.armorplus.common.registry.blocks.lava.BlockLavaMaterial.LavaMaterial.*;
import static com.sofodev.armorplus.common.registry.blocks.metals.Metals.ELECTRICAL;
import static com.sofodev.armorplus.common.registry.blocks.metals.Metals.STEEL;
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
    public static BlockBench[] benches = new BlockBench[4];
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
    public static BlockDungeonEnder[] enderBlocks = new BlockDungeonEnder[7];
    public static BlockTrophy[] trophies = new BlockTrophy[42];
    public static BlockMeltingObsidian blockMeltingObsidian = new BlockMeltingObsidian();
    public static BlockSwordDisplay[] blockSwordDisplays = new BlockSwordDisplay[Swords.values().length];
    public static BlockEmptyDisplay blockEmptyDisplay = new BlockEmptyDisplay();
    // public static BlockBTMMoon blockBTMMoon = new BlockBTMMoon();

    public static void registerBlocks() {
        setAll(benches, type -> new BlockBench(Benches.values()[type]));
        setAll(stoneBricks, type -> new BlockStoneBrick(BrickColor.values()[type]));
        setAll(stoneBrickTowers, type -> new BlockStoneBrickTower(BrickColor.values()[type]));
        setAll(stoneBrickCorners, type -> new BlockStoneBrickCorner(BrickColor.values()[type], stoneBricks[type].getDefaultState()));
        setAll(stonebrickWalls, type -> new BlockStoneBrickWall(stoneBricks[type]));
        setAll(enderBlocks, type -> new BlockDungeonEnder(EnderType.values()[type]));
        setAll(trophies, type -> new BlockTrophy(Trophy.values()[type]));
        Swords[] values = Swords.values();
        for (int index = 0; index < values.length; index++) {
            blockSwordDisplays[index] = new BlockSwordDisplay(values[index]);
        }
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
        registerAllBlocks(event, enderBlocks, trophies);
        registerAllBlocks(event, blockSwordDisplays);
        registerAllBlocks(event, blockEmptyDisplay);
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