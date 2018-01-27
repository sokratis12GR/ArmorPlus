/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.thedragonteam.armorplus.blocks.benches.Benches;
import net.thedragonteam.armorplus.blocks.benches.BlockBench;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickTower;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStonebrickWall;
import net.thedragonteam.armorplus.blocks.dungeon.BlockDungeonEnder;
import net.thedragonteam.armorplus.blocks.special.BlockTrophy;
import net.thedragonteam.armorplus.blocks.dungeon.EnderBlockType;
import net.thedragonteam.armorplus.blocks.special.TrophyType;
import net.thedragonteam.armorplus.blocks.lava.*;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;

import static java.util.Arrays.setAll;
import static net.thedragonteam.armorplus.blocks.benches.Benches.*;
import static net.thedragonteam.armorplus.blocks.castle.StoneBricks.*;
import static net.thedragonteam.armorplus.blocks.dungeon.EnderBlockType.*;
import static net.thedragonteam.armorplus.blocks.special.TrophyType.*;
import static net.thedragonteam.armorplus.blocks.lava.BlockLavaType.EnumLavaType.*;
import static net.thedragonteam.armorplus.blocks.v2.Metals.ELECTRICAL;
import static net.thedragonteam.armorplus.blocks.v2.Metals.STEEL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ModBlocks {

    public static OreLavaCrystal oreLavaCrystal = new OreLavaCrystal();
    public static CompressedObsidian compressedObsidian = new CompressedObsidian();
    public static LavaCactus lavaCactus = new LavaCactus();
    public static BaseMetalBlock steelBlock = new BaseMetalBlock(STEEL);
    public static BaseMetalBlock electricalBlock = new BaseMetalBlock(ELECTRICAL);
    public static LavaNetherBrick lavaNetherBrick = new LavaNetherBrick();
    public static Benches[] benchTypes = new Benches[]{WORKBENCH, HIGH_TECH, ULTI_TECH, CHAMPION};
    public static BlockBench[] benches = new BlockBench[4];
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
    public static EnderBlockType[] enderBlockTypes = new EnderBlockType[]{
        ENDER_STONE, ENDER_STONE_BRICKS, ENDER_PILLAR, ENDER_GLOWSTONE, ENDER_FLOOR_1, ENDER_FLOOR_2, ENDER_STONE_TRAP
    };
    public static BlockDungeonEnder[] enderBlocks = new BlockDungeonEnder[7];
    public static BlockTrophy[] trophies = new BlockTrophy[8];
    public static TrophyType[] types = new TrophyType[]{
        ANY, ELDER_GUARDIAN, WITHER_BOSS, ENDER_DRAGON, SKELETAL_KING, GUARDIAN_OVERLORD, DEMONIC_DRAGON, THE_LORD_OF_EVERYTHING
    };
    // public static BlockBTMMoon blockBTMMoon = new BlockBTMMoon();

    public static void registerBlocks() {
        setAll(benches, type -> new BlockBench(benchTypes[type]));
        setAll(stoneBricks, type -> new BlockStoneBrick(stoneBrickTypes[type]));
        setAll(stoneBrickTowers, type -> new BlockStoneBrickTower(stoneBrickTypes[type]));
        setAll(stoneBrickCorners, type -> new BlockStoneBrickCorner(stoneBrickTypes[type], stoneBricks[type].getDefaultState()));
        setAll(stonebrickWalls, type -> new BlockStonebrickWall(stoneBricks[type]));
        setAll(enderBlocks, type -> new BlockDungeonEnder(enderBlockTypes[type]));
        setAll(trophies, type -> new BlockTrophy(types[type]));
    }
}