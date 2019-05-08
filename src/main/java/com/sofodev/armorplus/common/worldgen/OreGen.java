/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.worldgen;

import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.util.WorldGenUtils;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableFeature;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static com.sofodev.armorplus.common.config.ModConfig.WorldGenConfig.lava_crystal;
import static net.minecraft.world.dimension.DimensionType.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class OreGen implements IWorldGenerator {

    private Feature overworldGenerator = new MinableFeature();//(ModBlocks.blockCrystalOre.getDefaultState(), lava_crystal.overworld.veinAmount);
    private Feature theEndGenerator = new MinableFeature();//(ModBlocks.blockCrystalOre.getDefaultState(), lava_crystal.the_end.veinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
    private Feature theNetherGenerator = new MinableFeature();//(ModBlocks.blockCrystalOre.getDefaultState(), lava_crystal.the_nether.veinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        int i = world.getWorld().getDimension().getType().getId();
        DimensionType type = DimensionType.getById(i);

        if (type == OVERWORLD && lava_crystal.overworld.enable) {
            runGenerator(overworldGenerator, world, random, chunkX, chunkZ, lava_crystal.overworld);
        } else if (type == THE_END && lava_crystal.the_end.enable) {
            runGenerator(theEndGenerator, world, random, chunkX, chunkZ, lava_crystal.the_end);
        } else if (type == NETHER && lava_crystal.the_nether.enable) {
            runGenerator(theNetherGenerator, world, random, chunkX, chunkZ, lava_crystal.the_nether);
        }
    }

    private void runGenerator(Feature generator, World world, Random random, int chunkX, int chunkZ, ModConfig.WorldGenConfig.OreLavaCrystal.DimensionOre dimensionOre) {
        WorldGenUtils.runGenerator(generator, world, random, chunkX, chunkZ, dimensionOre.rarity, dimensionOre.minYSpawn, dimensionOre.maxYSpawn);
    }
}