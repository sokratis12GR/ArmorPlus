/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.benches.Benches;
import net.thedragonteam.armorplus.blocks.benches.BlockBench;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickTower;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStonebrickWall;
import net.thedragonteam.armorplus.blocks.dungeon.BlockDungeonEnder;
import net.thedragonteam.armorplus.blocks.dungeon.EnumEnderBlocks;
import net.thedragonteam.armorplus.blocks.lava.BlockLavaInfusedObsidian;
import net.thedragonteam.armorplus.blocks.lava.BlockLavaInfuser;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.ritual.BlockRitualAltar;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;

import static net.thedragonteam.armorplus.blocks.benches.Benches.*;
import static net.thedragonteam.armorplus.blocks.castle.StoneBricks.*;
import static net.thedragonteam.armorplus.blocks.dungeon.EnumEnderBlocks.*;
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
    public static Benches[] benchTypes = new Benches[]{WORKBENCH, HIGH_TECH, ULTI_TECH, CHAMPION, WORKBENCH_NEW};
    public static StoneBricks[] stoneBrickTypes = new StoneBricks[]{WHITE, RED, BLACK, BLUE, GREEN, YELLOW, PURPLE};
    public static BlockStoneBrick[] stoneBricks = new BlockStoneBrick[7];
    public static BlockStoneBrickTower[] stoneBrickTowers = new BlockStoneBrickTower[7];
    public static BlockStoneBrickCorner[] stoneBrickCorners = new BlockStoneBrickCorner[7];
    public static BlockStonebrickWall[] stonebrickWalls = new BlockStonebrickWall[7];
    public static BlockBench[] benches = new BlockBench[5];
    public static BlockLavaInfuser lavaInfuser = new BlockLavaInfuser("lava_infuser", false);
    public static BlockLavaInfuser lavaInfuserCharging = new BlockLavaInfuser("lava_infuser_infusing", true);
    public static BlockLavaInfusedObsidian lavaInfusedObsidian = new BlockLavaInfusedObsidian();
    public static BlockRitualAltar ritualAltar = new BlockRitualAltar();
    public static EnumEnderBlocks[] enumEnderBlocks = new EnumEnderBlocks[]{ENDER_STONE, ENDER_STONE_BRICKS, ENDER_PILLAR, ENDER_GLOWSTONE, ENDER_FLOOR_1, ENDER_FLOOR_2};
    public static BlockDungeonEnder[] enderBlocks = new BlockDungeonEnder[6];

    public static void init() {
        for (int s = 0; s < stoneBricks.length && s < stoneBrickTowers.length && s < stoneBrickCorners.length && s < stonebrickWalls.length; s++) {
            stoneBricks[s] = new BlockStoneBrick(stoneBrickTypes[s]);
            stoneBrickTowers[s] = new BlockStoneBrickTower(stoneBrickTypes[s]);
            stoneBrickCorners[s] = new BlockStoneBrickCorner(stoneBrickTypes[s], stoneBricks[s].getDefaultState());
            stonebrickWalls[s] = new BlockStonebrickWall(stoneBricks[s]);
        }
        for (int b = 0; b < benches.length; b++) {
            benches[b] = new BlockBench(benchTypes[b]);
        }
        for (int c = 0; c < enumEnderBlocks.length; c++) {
            enderBlocks[c] = new BlockDungeonEnder(enumEnderBlocks[c]);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        lavaInfuser.initModel();
        lavaInfuserCharging.initModel();
        lavaInfusedObsidian.initModel();
        blockLavaCrystal.initModel();
        compressedObsidian.initModel();
        steelBlock.initModel();
        electricalBlock.initModel();
        lavaCactus.initModel();
        lavaNetherBrick.initModel();
        for (BlockStoneBrick stoneBrick : stoneBricks) stoneBrick.initModel();
        for (BlockStoneBrickTower stoneBrickTower : stoneBrickTowers) stoneBrickTower.initModel();
        for (BlockStoneBrickCorner stoneBrickCorner : stoneBrickCorners) stoneBrickCorner.initModel();
        for (BlockStonebrickWall stonebrickWall : stonebrickWalls) stonebrickWall.initModel();
        for (BlockBench blockBench : benches) blockBench.initModel();
        ritualAltar.initModel();
        for (BlockDungeonEnder enderBlock : enderBlocks) enderBlock.initModel();
    }
}