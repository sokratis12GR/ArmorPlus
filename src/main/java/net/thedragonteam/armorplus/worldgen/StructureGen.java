/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.worldgen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.armorplus.worldgen.structures.StructureCastle;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.castleGenSpawnChance;
import static net.thedragonteam.armorplus.ARPConfig.enableCastleGen;

public class StructureGen implements IWorldGenerator {

    /**
     * HELPER METHODS
     **/
    // find a grass or dirt block to place the bush on
    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            // "ground" for our bush is grass or dirt
            foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt != Blocks.AIR;
        }

        return y;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int blockX = chunkX * 16;
        int blockZ = chunkZ * 16;

        switch (world.provider.getDimension()) {
            case 0: //Overworld Dimension
                generateOverworldStructures(world, random, blockX, blockZ);
                break;
            case 1: //The End
                break;
            case -1: //The Nether
                break;
        }
    }

    private void generateOverworldStructures(World world, Random rand, int blockX, int blockZ) {
        if (enableCastleGen) {
            WorldGenerator genCastle = new StructureCastle();
            // 4% of chunks can have a castle
            if (rand.nextInt(1000) < castleGenSpawnChance) {
                // get a random position in the chunk
                int randX = blockX + rand.nextInt(16);
                int randZ = blockZ + rand.nextInt(16);
                // use our custom function to get the ground height
                int groundY = getGroundFromAbove(world, randX, randZ);
                genCastle.generate(world, rand, new BlockPos(randX, groundY + 1, randZ));
            }
        }
    }
}
