/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.worldgen

import net.minecraft.block.state.pattern.BlockMatcher
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkGenerator
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraft.world.gen.feature.WorldGenerator
import net.minecraftforge.fml.common.IWorldGenerator
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.registry.ModBlocks
import java.util.*

/**
 * net.thedragonteam.armorplus.worldgen
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class OreGen : IWorldGenerator {

    private val lavaCrystalOverworldGenerator: WorldGenerator = WorldGenMinable(ModBlocks.blockLavaCrystal.defaultState, lavaCrystalOverworldVeinAmountWorking)
    private val lavaCrystalTheEndGenerator: WorldGenerator = WorldGenMinable(ModBlocks.blockLavaCrystal.defaultState, lavaCrystalTheEndVeinAmount, BlockMatcher.forBlock(Blocks.END_STONE))
    private val lavaCrystalTheNetherGenerator: WorldGenerator = WorldGenMinable(ModBlocks.blockLavaCrystal.defaultState, lavaCrystalTheNetherVeinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK))

    override fun generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider) {
        when (world.provider.dimension) {
        //The End
            1 -> if (enableLavaCrystalTheEndGen) {
                this.runGenerator(lavaCrystalTheEndGenerator, world, random, chunkX, chunkZ, lavaCrystalTheEndRarity, lavaCrystalTheEndMinYSpawn, lavaCrystalTheEndMaxYSpawn)
            }
        //Overworld Dimension
            0 -> if (enableLavaCrystalOverworldGen) {
                this.runGenerator(lavaCrystalOverworldGenerator, world, random, chunkX, chunkZ, lavaCrystalOverworldRarityWorkingOne, lavaCrystalOverworldMinYSpawn, lavaCrystalOverworldMaxYSpawn)
            }
        //The Nether
            -1 -> if (enableLavaCrystalTheNetherGen) {
                this.runGenerator(lavaCrystalTheNetherGenerator, world, random, chunkX, chunkZ, lavaCrystalTheNetherRarity, lavaCrystalTheNetherMinYSpawn, lavaCrystalTheNetherMaxYSpawn)
            }
        }
    }

    private fun runGenerator(generator: WorldGenerator, world: World, rand: Random, chunk_X: Int, chunk_Z: Int, chancesToSpawn: Int, minHeight: Int, maxHeight: Int) {
        assert(!(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)) { "Illegal Height Arguments for WorldGenerator" }
        val heightDiff = maxHeight - minHeight + 1
        for (i in 0 .. chancesToSpawn - 1) {
            val x = chunk_X * 16 + rand.nextInt(16)
            val y = minHeight + rand.nextInt(heightDiff)
            val z = chunk_Z * 16 + rand.nextInt(16)
            val orePos = BlockPos(x, y, z)
            generator.generate(world, rand, orePos)
        }
    }
}