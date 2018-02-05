package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class WorldGenUtils {

    /**
     * HELPER METHODS
     **/
    // find a grass or dirt block to place the structure on
    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == Blocks.WATER || blockAt == Blocks.FLOWING_WATER || blockAt == Blocks.LAVA || blockAt == Blocks.FLOWING_LAVA || blockAt != Blocks.AIR;
        }

        return y;
    }

    public static void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
            throw new AssertionError("Illegal Height Arguments for WorldGenerator");
        }
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x, y, z;
            x = chunk_X * 16 + rand.nextInt(16);
            y = minHeight + rand.nextInt(heightDiff);
            z = chunk_Z * 16 + rand.nextInt(16);
            BlockPos orePos = new BlockPos(x, y, z);
            generator.generate(world, rand, orePos);
        }
    }

    public static void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int posY) {
        for (int i = 0; i < chancesToSpawn; i++) {
            int x, z;
            x = chunk_X * 16 + rand.nextInt(16);
            z = chunk_Z * 16 + rand.nextInt(16);
            BlockPos orePos = new BlockPos(x, posY, z);
            generator.generate(world, rand, orePos);
        }
    }

    public static void runGenerator(WorldGenerator generator, World world, Random rand, int chancesToSpawn, BlockPos blockPos) {
        IntStream.range(0, chancesToSpawn).forEachOrdered(i ->
            generator.generate(world, rand, blockPos)
        );
    }
}
