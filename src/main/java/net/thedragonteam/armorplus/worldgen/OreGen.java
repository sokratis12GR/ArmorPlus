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

    private WorldGenerator lavaCrystalOverworldGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lava_crystal.overworldVeinAmountWorking);
    private WorldGenerator lavaCrystalTheEndGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lava_crystal.theEndVeinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
    private WorldGenerator lavaCrystalTheNetherGenerator = new WorldGenMinable(ModBlocks.oreLavaCrystal.getDefaultState(), lava_crystal.theNetherVeinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int i = world.provider.getDimension();
        if (i == 0) {
            if (lava_crystal.enableOverworldGen) {
                runGenerator(lavaCrystalOverworldGenerator, world, random, chunkX, chunkZ, lava_crystal.overworldRarityWorkingOne, lava_crystal.overworldMinYSpawn, lava_crystal.overworldMaxYSpawn);
            }
        } else if (i == 1) {
            if (lava_crystal.enableTheEndGen) {
                runGenerator(lavaCrystalTheEndGenerator, world, random, chunkX, chunkZ, lava_crystal.theEndRarity, lava_crystal.theEndMinYSpawn, lava_crystal.theEndMaxYSpawn);
            }
        } else if (i == -1) {
            if (lava_crystal.enableTheNetherGen) {
                runGenerator(lavaCrystalTheNetherGenerator, world, random, chunkX, chunkZ, lava_crystal.theNetherRarity, lava_crystal.theNetherMinYSpawn, lava_crystal.theNetherMaxYSpawn);
            }
        }
    }
}