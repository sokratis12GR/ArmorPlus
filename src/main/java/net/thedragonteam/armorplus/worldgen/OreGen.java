/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.worldgen;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.armorplus.ModConfig.WorldGenConfig.OreLavaCrystal.DimensionOre;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.util.WorldGenUtils;

import java.util.Random;

import static net.thedragonteam.armorplus.ModConfig.WorldGenConfig.lava_crystal;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class OreGen implements IWorldGenerator {

    private WorldGenerator overworldGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lava_crystal.overworld.veinAmount);
    private WorldGenerator theEndGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lava_crystal.the_end.veinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
    private WorldGenerator theNetherGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lava_crystal.the_nether.veinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int i = world.provider.getDimension();
        if (i == 0) {
            if (lava_crystal.overworld.enable) {
                runGenerator(overworldGenerator, world, random, chunkX, chunkZ, lava_crystal.overworld);
            }
        } else if (i == 1) {
            if (lava_crystal.the_end.enable) {
                runGenerator(theEndGenerator, world, random, chunkX, chunkZ, lava_crystal.the_end);
            }
        } else if (i == -1) {
            if (lava_crystal.the_nether.enable) {
                runGenerator(theNetherGenerator, world, random, chunkX, chunkZ, lava_crystal.the_nether);
            }
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, DimensionOre dimensionOre) {
        WorldGenUtils.runGenerator(generator, world, random, chunkX, chunkZ, dimensionOre.rarity, dimensionOre.minYSpawn, dimensionOre.maxYSpawn);
    }
}