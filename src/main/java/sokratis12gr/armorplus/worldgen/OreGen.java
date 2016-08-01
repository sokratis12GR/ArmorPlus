package sokratis12gr.armorplus.worldgen;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import sokratis12gr.armorplus.ARPConfig;
import sokratis12gr.armorplus.registry.ModBlocks;

import java.util.Random;

/**
 * sokratis12gr.armorplus.worldgen
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:30 PM.
 */
public class OreGen implements IWorldGenerator {

    public WorldGenerator lavaCrystalOverworldGenerator;
    public WorldGenerator lavaCrystalTheEndGenerator;
    public WorldGenerator lavaCrystalTheNetherGenerator;

    public OreGen() {
        lavaCrystalOverworldGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), ARPConfig.lavaCrystalOverworldVeinAmount);
        lavaCrystalTheEndGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), ARPConfig.lavaCrystalTheEndVeinAmount);
        lavaCrystalTheNetherGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), ARPConfig.lavaCrystalTheNetherVeinAmount);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: //Overworld Dimension
                if (ARPConfig.enableLavaCrystalOverworldGen) {
                    this.runGenerator(lavaCrystalOverworldGenerator, world, random, chunkX, chunkZ, ARPConfig.lavaCrystalOverworldRarity, ARPConfig.lavaCrystalOverworldMinYSpawn, ARPConfig.lavaCrystalOverworldMaxYSpawn);
                }
                break;
            case 1: //The End
                if (ARPConfig.enableLavaCrystalTheEndGen) {
                    this.runGenerator(lavaCrystalTheEndGenerator, world, random, chunkX, chunkZ, ARPConfig.lavaCrystalTheEndRarity, ARPConfig.lavaCrystalTheEndMinYSpawn, ARPConfig.lavaCrystalTheEndMaxYSpawn);
                }
                break;
            case -1: //The Nether
                if (ARPConfig.enableLavaCrystalTheNetherGen) {
                    this.runGenerator(lavaCrystalTheNetherGenerator, world, random, chunkX, chunkZ, ARPConfig.lavaCrystalTheNetherRarity, ARPConfig.lavaCrystalTheNetherMinYSpawn, ARPConfig.lavaCrystalTheNetherMaxYSpawn);
                }
                break;
        }
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
