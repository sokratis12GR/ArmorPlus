/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.worldgen
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class OreGen implements IWorldGenerator {

 //   private WorldGenerator lavaCrystalOverworldGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lavaCrystalOverworldVeinAmountWorking);
 //   private WorldGenerator lavaCrystalTheEndGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lavaCrystalTheEndVeinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
 //   private WorldGenerator lavaCrystalTheNetherGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lavaCrystalTheNetherVeinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
  //      switch (world.provider.getDimension()) {
  //          //The End
  //          case 1:
  //              if (enableLavaCrystalTheEndGen) {
  //                  this.runGenerator(lavaCrystalTheEndGenerator, world, random, chunkX, chunkZ, lavaCrystalTheEndRarity, lavaCrystalTheEndMinYSpawn, lavaCrystalTheEndMaxYSpawn);
  //              }
  //              //Overworld Dimension
  //          case 0:
  //              if (enableLavaCrystalOverworldGen) {
  //                  this.runGenerator(lavaCrystalOverworldGenerator, world, random, chunkX, chunkZ, lavaCrystalOverworldRarityWorkingOne, lavaCrystalOverworldMinYSpawn, lavaCrystalOverworldMaxYSpawn);
  //              }
  //              //The Nether
  //          case -1:
  //              if (enableLavaCrystalTheNetherGen) {
  //                  this.runGenerator(lavaCrystalTheNetherGenerator, world, random, chunkX, chunkZ, lavaCrystalTheNetherRarity, lavaCrystalTheNetherMinYSpawn, lavaCrystalTheNetherMaxYSpawn);
  //              }
  //      }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        assert !(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) : "Illegal Height Arguments for WorldGenerator";
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn - 1; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            BlockPos orePos = new BlockPos(x, y, z);
            generator.generate(world, rand, orePos);
        }
    }
}