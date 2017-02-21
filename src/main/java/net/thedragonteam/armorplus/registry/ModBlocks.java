/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.benches.BlockBench;
import net.thedragonteam.armorplus.blocks.benches.Benches;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickTower;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.spawners.Spawners;
import net.thedragonteam.armorplus.blocks.spawners.BlockSpawner;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;

import static net.thedragonteam.armorplus.blocks.benches.Benches.*;
import static net.thedragonteam.armorplus.blocks.castle.StoneBricks.*;
import static net.thedragonteam.armorplus.blocks.v2.Metals.ELECTRICAL;
import static net.thedragonteam.armorplus.blocks.v2.Metals.STEEL;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 * - TheDragonTeam
 */
public class ModBlocks {

    public static BlockLavaCrystal blockLavaCrystal = new BlockLavaCrystal();
    public static CompressedObsidian compressedObsidian = new CompressedObsidian();
    public static BaseMetalBlock steelBlock = new BaseMetalBlock(STEEL);
    public static BaseMetalBlock electricalBlock = new BaseMetalBlock(ELECTRICAL);
    public static LavaNetherBrick lavaNetherBrick = new LavaNetherBrick();
    public static LavaCactus lavaCactus = new LavaCactus();
    public static Benches[] benchTypes = new Benches[]{WORKBENCH, HIGH_TECH, ULTI_TECH};
    public static StoneBricks[] stoneBrickTypes = new StoneBricks[]{WHITE, RED, BLACK, BLUE, GREEN, YELLOW, PURPLE};
    public static BlockStoneBrick[] stoneBricks = new BlockStoneBrick[7];
    public static BlockStoneBrickTower[] stoneBrickTowers = new BlockStoneBrickTower[7];
    public static BlockStoneBrickCorner[] stoneBrickCorners = new BlockStoneBrickCorner[7];
    public static BlockBench[] benches = new BlockBench[3];
    public static Spawners[] spawnersTypes = new Spawners[]{Spawners.ENDER_DRAGON_ZOMBIE, Spawners.GUARDIAN};
    public static BlockSpawner[] spawners = new BlockSpawner[2];

    public static void init() {
        for (int s = 0; s < stoneBricks.length && s < stoneBrickTowers.length && s < stoneBrickCorners.length; s++) {
            stoneBricks[s] = new BlockStoneBrick(stoneBrickTypes[s]);
            stoneBrickTowers[s] = new BlockStoneBrickTower(stoneBrickTypes[s]);
            stoneBrickCorners[s] = new BlockStoneBrickCorner(stoneBrickTypes[s], stoneBricks[s].getDefaultState());
        }
        for (int b = 0; b < benches.length; b++) {
            benches[b] = new BlockBench(benchTypes[b]);
        }
        for (int b = 0; b < spawners.length; b++) {
            spawners[b] = new BlockSpawner(spawnersTypes[b]);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockLavaCrystal.initModel();
        compressedObsidian.initModel();
        steelBlock.initModel();
        electricalBlock.initModel();
        lavaCactus.initModel();
        lavaNetherBrick.initModel();
        for (BlockStoneBrick stoneBrick : stoneBricks) stoneBrick.initModel();
        for (BlockStoneBrickTower stoneBrickTower : stoneBrickTowers) stoneBrickTower.initModel();
        for (BlockStoneBrickCorner stoneBrickCorner : stoneBrickCorners) stoneBrickCorner.initModel();
        for (BlockBench blockBench : benches) blockBench.initModel();
        for (BlockSpawner blockSpawner : spawners) blockSpawner.initModel();
    }
}