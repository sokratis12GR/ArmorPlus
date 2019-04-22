/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.worldgen;

import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.util.WorldGenUtils;
import com.sofodev.armorplus.common.util.WorldGenUtils.ChunkPos;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static com.sofodev.armorplus.common.config.ModConfig.WorldGenConfig.lava_crystal;
import static com.sofodev.armorplus.common.registry.ModBlocks.oreLavaCrystal;

/**
 * @author Sokratis Fotkatzikis
 **/
public class OreGen implements IWorldGenerator {

    public WorldGenerator overworldGenerator = new WorldGenMinable(oreLavaCrystal.getDefaultState(), lava_crystal.overworld.veinAmount);
    public WorldGenerator theEndGenerator = new WorldGenMinable(oreLavaCrystal.getDefaultState(), lava_crystal.the_end.veinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
    public WorldGenerator theNetherGenerator = new WorldGenMinable(oreLavaCrystal.getDefaultState(), lava_crystal.the_nether.veinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int i = world.provider.getDimension();
        if (i == 0 && lava_crystal.overworld.enable) {
            runGenerator(overworldGenerator, world, random, new ChunkPos(chunkX, chunkZ), lava_crystal.overworld);
        } else if (i == 1 && lava_crystal.the_end.enable) {
            runGenerator(theEndGenerator, world, random, new ChunkPos(chunkX, chunkZ), lava_crystal.the_end);
        } else if (i == -1 && lava_crystal.the_nether.enable) {
            runGenerator(theNetherGenerator, world, random, new ChunkPos(chunkX, chunkZ), lava_crystal.the_nether);
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, ChunkPos chunk, ModConfig.WorldGenConfig.OreLavaCrystal.DimensionOre dimensionOre) {
        WorldGenUtils.runGenerator(generator, world, random, chunk, dimensionOre.rarity, dimensionOre.minYSpawn, dimensionOre.maxYSpawn);
    }
}