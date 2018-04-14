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
import net.thedragonteam.armorplus.registry.ModBlocks;

import java.util.Random;

import static net.thedragonteam.armorplus.ModConfig.WorldGenConfig.lava_crystal;
import static net.thedragonteam.armorplus.util.WorldGenUtils.runGenerator;

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
                runGenerator(overworldGenerator, world, random, chunkX, chunkZ, lava_crystal.overworld.rarity, lava_crystal.overworld.minYSpawn, lava_crystal.overworld.maxYSpawn);
            }
        } else if (i == 1) {
            if (lava_crystal.the_end.enable) {
                runGenerator(theEndGenerator, world, random, chunkX, chunkZ, lava_crystal.the_end.rarity, lava_crystal.the_end.minYSpawn, lava_crystal.the_end.maxYSpawn);
            }
        } else if (i == -1) {
            if (lava_crystal.the_nether.enable) {
                runGenerator(theNetherGenerator, world, random, chunkX, chunkZ, lava_crystal.the_nether.rarity, lava_crystal.the_nether.minYSpawn, lava_crystal.the_nether.maxYSpawn);
            }
        }
    }
}