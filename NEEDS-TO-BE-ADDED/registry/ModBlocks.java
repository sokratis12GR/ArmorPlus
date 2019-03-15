/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.blocks.benches.Benches;
import com.sofodev.armorplus.blocks.benches.BlockBench;
import com.sofodev.armorplus.blocks.castle.BrickColor;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrick;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrickTower;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrickWall;
import com.sofodev.armorplus.blocks.dungeon.BlockDungeonEnder;
import com.sofodev.armorplus.blocks.dungeon.EnderType;
import com.sofodev.armorplus.blocks.lava.*;
import com.sofodev.armorplus.blocks.normal.BlockCompressedObsidian;
import com.sofodev.armorplus.blocks.special.BlockTrophy;
import com.sofodev.armorplus.blocks.special.Trophy;
import com.sofodev.armorplus.blocks.v2.BlockMetal;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModBlocks {

    public static BlockCrystalOre blockCrystalOre;
    public static BlockCompressedObsidian blockCompressedObsidian;
    public static BlockLavaCactus blockLavaCactus;
    public static BlockMetal steelBlock;
    public static BlockMetal electricalBlock;
    public static BlockLavaNetherBrick blockLavaNetherBrick;
    public static Benches[] benchTypes;
    public static BlockBench[] benches;
    public static BrickColor[] stoneBrickTypes;
    public static BlockStoneBrick[] stoneBricks;
    public static BlockStoneBrickTower[] stoneBrickTowers;
    public static BlockStoneBrickCorner[] stoneBrickCorners;
    public static BlockStoneBrickWall[] stonebrickWalls;
    public static BlockLavaInfuser lavaInfuser;
    public static BlockLavaInfuser lavaInfuserInfusing;
    public static BlockLavaMaterial blockLavaCrystal;
    public static BlockLavaMaterial blockInfusedLavaCrystal;
    public static BlockLavaMaterial blockCompressedLavaCrystal;
    public static BlockLavaMaterial blockCompressedInfusedLavaCrystal;
    public static BlockLavaMaterial blockLavaInfusedObsidian;
    public static EnderType[] enderTypes;
    public static BlockDungeonEnder[] enderBlocks;
    public static BlockTrophy[] trophies;
    public static Trophy[] types;
    public static BlockMeltingObsidian blockMeltingObsidian;
}