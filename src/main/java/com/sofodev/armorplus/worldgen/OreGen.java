/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.worldgen;

import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.util.WorldGenUtils;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * @author Sokratis Fotkatzikis
 **/
public class OreGen implements IWorldGenerator {

    private WorldGenerator overworldGenerator = new WorldGenMinable(ModBlocks.blockCrystalOre.getDefaultState(), ModConfig.WorldGenConfig.lava_crystal.overworld.veinAmount);
    private WorldGenerator theEndGenerator = new WorldGenMinable(ModBlocks.blockCrystalOre.getDefaultState(), ModConfig.WorldGenConfig.lava_crystal.the_end.veinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
    private WorldGenerator theNetherGenerator = new WorldGenMinable(ModBlocks.blockCrystalOre.getDefaultState(), ModConfig.WorldGenConfig.lava_crystal.the_nether.veinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int i = world.provider.getDimension();
        if (i == 0) {
            if (ModConfig.WorldGenConfig.lava_crystal.overworld.enable) {
                runGenerator(overworldGenerator, world, random, chunkX, chunkZ, ModConfig.WorldGenConfig.lava_crystal.overworld);
            }
        } else if (i == 1) {
            if (ModConfig.WorldGenConfig.lava_crystal.the_end.enable) {
                runGenerator(theEndGenerator, world, random, chunkX, chunkZ, ModConfig.WorldGenConfig.lava_crystal.the_end);
            }
        } else if (i == -1) {
            if (ModConfig.WorldGenConfig.lava_crystal.the_nether.enable) {
                runGenerator(theNetherGenerator, world, random, chunkX, chunkZ, ModConfig.WorldGenConfig.lava_crystal.the_nether);
            }
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, ModConfig.WorldGenConfig.OreLavaCrystal.DimensionOre dimensionOre) {
        WorldGenUtils.runGenerator(generator, world, random, chunkX, chunkZ, dimensionOre.rarity, dimensionOre.minYSpawn, dimensionOre.maxYSpawn);
    }
}