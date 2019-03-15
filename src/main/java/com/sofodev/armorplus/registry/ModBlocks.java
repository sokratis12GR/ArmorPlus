/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.blocks.benches.BlockBench;
import com.sofodev.armorplus.blocks.lava.*;
import com.sofodev.armorplus.blocks.normal.BlockCompressedObsidian;
import com.sofodev.armorplus.blocks.v2.BlockMetal;
import net.minecraftforge.registries.ObjectHolder;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModBlocks {

    @ObjectHolder(MODID + ":ore_lava_crystal")
    public static BlockCrystalOre blockCrystalOre;
    @ObjectHolder(MODID + ":compressed_obsidian")
    public static BlockCompressedObsidian blockCompressedObsidian;
    @ObjectHolder(MODID + ":lava_cactus")
    public static BlockLavaCactus blockLavaCactus;
    @ObjectHolder(MODID + ":block_steel")
    public static BlockMetal steelBlock;
    @ObjectHolder(MODID + ":block_electrical")
    public static BlockMetal electricalBlock;
    @ObjectHolder(MODID + ":block_lava_nether_brick")
    public static BlockLavaNetherBrick blockLavaNetherBrick;
    @ObjectHolder(MODID + ":workbench")
    public static BlockBench workbench;
    @ObjectHolder(MODID + ":high_tech_bench")
    public static BlockBench highTechBench;
    @ObjectHolder(MODID + ":ulti_tech_bench")
    public static BlockBench ultiTechBench;
    @ObjectHolder(MODID + ":champion_bench")
    public static BlockBench championBench;
    //  public static Benches[] benchTypes;
    //  public static BlockBench[] benches;
    //  public static BrickColor[] stoneBrickTypes;
    //  public static BlockStoneBrick[] stoneBricks;
    //  public static BlockStoneBrickTower[] stoneBrickTowers;
    //  public static BlockStoneBrickCorner[] stoneBrickCorners;
    //  public static BlockStoneBrickWall[] stonebrickWalls;
    @ObjectHolder(MODID + ":lava_infuser")
    public static BlockLavaInfuser lavaInfuser;
    @ObjectHolder(MODID + ":lava_infuser_infusing")
    public static BlockLavaInfuser lavaInfuserInfusing;
    @ObjectHolder(MODID + ":block_lava_crystal")
    public static BlockLavaMaterial blockLavaCrystal;
    @ObjectHolder(MODID + ":block_infused_lava_crystal")
    public static BlockLavaMaterial blockInfusedLavaCrystal;
    @ObjectHolder(MODID + ":block_compressed_lava_crystal")
    public static BlockLavaMaterial blockCompressedLavaCrystal;
    @ObjectHolder(MODID + ":block_compressed_infused_lava_crystal")
    public static BlockLavaMaterial blockCompressedInfusedLavaCrystal;
    @ObjectHolder(MODID + ":block_infused_obsidian")
    public static BlockLavaMaterial blockLavaInfusedObsidian;
    //  public static EnderType[] enderTypes;
    //  public static BlockDungeonEnder[] enderBlocks;
    //  public static BlockTrophy[] trophies;
    //  public static Trophy[] types;
    //  public static BlockMeltingObsidian blockMeltingObsidian;
}