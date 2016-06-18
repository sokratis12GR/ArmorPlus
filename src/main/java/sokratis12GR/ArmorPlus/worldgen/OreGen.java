package sokratis12GR.ArmorPlus.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import sokratis12GR.ArmorPlus.registry.ModBlocks;

import java.util.Random;

/**
 * sokratis12GR.ArmorPlus.worldgen
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:30 PM.
 */
public class OreGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: //Overworld Dimension
                this.runGenerator(lavaCrystalGenerator, world, random, chunkX, chunkZ, 4, 6, 26);
                break;
            case 1: //The End

                break;
            case -1: //The Nether

                break;
        }
    }

    public WorldGenerator lavaCrystalGenerator;

    public OreGen() {
        lavaCrystalGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), 4);
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
