/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.util.TextUtils.translatedText;

/**
 * @author Sokratis Fotkatzikis
 **/
public class WorldGenUtils {

    public static final int CHUNK_SIZE = 16;

    /**
     * HELPER METHODS
     **/
    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == Blocks.WATER || blockAt == Blocks.FLOWING_WATER || blockAt == Blocks.LAVA || blockAt == Blocks.FLOWING_LAVA || blockAt != Blocks.AIR;
        }

        return y;
    }

    public static void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
            throw new AssertionError(translatedText("error.world_gen.armorplus.generator_height", maxHeight, minHeight, maxHeight));
        }
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int y;
            y = minHeight + rand.nextInt(heightDiff);
            generate(generator, world, rand, chunkX, chunkZ, y);
        }
    }

    public static void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int posY) {
        for (int i = 0; i < chancesToSpawn; i++) {
            generate(generator, world, rand, chunkX, chunkZ, posY);
        }
    }

    private static void generate(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int y) {
        int x;
        int z;
        x = getExactRandPos(chunkX, rand);
        z = getExactRandPos(chunkZ, rand);
        BlockPos orePos = new BlockPos(x, y, z);
        generator.generate(world, rand, orePos);
    }

    public static int getExactRandPos(int chunk, Random rand) {
        return x16(chunk) + rand.nextInt(CHUNK_SIZE);
    }

    public static int x16(int chunkCord) {
        return chunkCord * 16;
    }

    public static void runGenerator(WorldGenerator generator, World world, Random rand, int chancesToSpawn, BlockPos blockPos) {
        IntStream.range(0, chancesToSpawn).forEachOrdered(i ->
            generator.generate(world, rand, blockPos)
        );
    }
}
