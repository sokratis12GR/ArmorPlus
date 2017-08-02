/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickTower;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStonebrickWall;
import net.thedragonteam.armorplus.blocks.dungeon.BlockDungeonEnder;
import net.thedragonteam.armorplus.blocks.dungeon.EnumEnderBlocks;
import net.thedragonteam.armorplus.blocks.lava.*;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;

import static java.util.Arrays.setAll;
import static net.thedragonteam.armorplus.blocks.castle.StoneBricks.*;
import static net.thedragonteam.armorplus.blocks.dungeon.EnumEnderBlocks.*;
import static net.thedragonteam.armorplus.blocks.lava.BlockLavaType.EnumLavaType.*;
import static net.thedragonteam.armorplus.blocks.v2.Metals.ELECTRICAL;
import static net.thedragonteam.armorplus.blocks.v2.Metals.STEEL;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 * - TheDragonTeam
 */
public class ModBlocks {

    public static OreLavaCrystal oreLavaCrystal = new OreLavaCrystal();
    public static CompressedObsidian compressedObsidian = new CompressedObsidian();
    public static BaseMetalBlock steelBlock = new BaseMetalBlock(STEEL);
    public static BaseMetalBlock electricalBlock = new BaseMetalBlock(ELECTRICAL);
    public static LavaNetherBrick lavaNetherBrick = new LavaNetherBrick();
    public static LavaCactus lavaCactus = new LavaCactus();
    public static StoneBricks[] stoneBrickTypes = new StoneBricks[]{
            WHITE, RED, BLACK, BLUE, GREEN, YELLOW, PURPLE
    };
    public static BlockStoneBrick[] stoneBricks = new BlockStoneBrick[7];
    public static BlockStoneBrickTower[] stoneBrickTowers = new BlockStoneBrickTower[7];
    public static BlockStoneBrickCorner[] stoneBrickCorners = new BlockStoneBrickCorner[7];
    public static BlockStonebrickWall[] stonebrickWalls = new BlockStonebrickWall[7];
    public static BlockLavaInfuser lavaInfuser = new BlockLavaInfuser("lava_infuser", false);
    public static BlockLavaInfuser lavaInfuserInfusing = new BlockLavaInfuser("lava_infuser_infusing", true);
    public static BlockLavaType blockLavaCrystal = new BlockLavaType(LAVA_CRYSTAL);
    public static BlockLavaType blockInfusedLavaCrystal = new BlockLavaType(INFUSED_LAVA_CRYSTAL);
    public static BlockLavaType blockCompressedLavaCrystal = new BlockLavaType(COMPRESSED_LAVA_CRYSTAL);
    public static BlockLavaType blockCompressedInfusedLavaCrystal = new BlockLavaType(COMPRESSED_INFUSED_LAVA_CRYSTAL);
    public static BlockLavaType blockLavaInfusedObsidian = new BlockLavaType(LAVA_INFUSED_OBSIDIAN);
    //  public static BlockRitualAltar ritualAltar = new BlockRitualAltar();
    public static EnumEnderBlocks[] enumEnderBlocks = new EnumEnderBlocks[]{
            ENDER_STONE, ENDER_STONE_BRICKS, ENDER_PILLAR, ENDER_GLOWSTONE, ENDER_FLOOR_1, ENDER_FLOOR_2, ENDER_STONE_TRAP
    };
    public static BlockDungeonEnder[] enderBlocks = new BlockDungeonEnder[7];

    public static void registerBlocks() {
        setAll(stoneBricks, b -> new BlockStoneBrick(stoneBrickTypes[b]));
        setAll(stoneBrickTowers, b -> new BlockStoneBrickTower(stoneBrickTypes[b]));
        setAll(stoneBrickCorners, b -> new BlockStoneBrickCorner(stoneBrickTypes[b], stoneBricks[b].getDefaultState()));
        setAll(stonebrickWalls, b -> new BlockStonebrickWall(stoneBricks[b]));
        setAll(enderBlocks, b -> new BlockDungeonEnder(enumEnderBlocks[b]));
    }
}