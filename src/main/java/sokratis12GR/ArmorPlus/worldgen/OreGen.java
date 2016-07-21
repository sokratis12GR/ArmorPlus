package sokratis12gr.armorplus.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.resources.ConfigHandler;

import java.util.Random;

/**
 * sokratis12gr.armorplus.worldgen
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:30 PM.
 */
public class OreGen implements IWorldGenerator {

    public WorldGenerator lavaCrystalGenerator;
    public WorldGenerator steelOreGenerator;
    public OreGen() {
        lavaCrystalGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), ConfigHandler.lavaCrystalVeinAmount);
        steelOreGenerator = new WorldGenMinable(ModBlocks.STEEL_ORE.getDefaultState(), ConfigHandler.steelOreVeinAmount);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: //Overworld Dimension
                if (ConfigHandler.enableLavaCrystalOverworldGen) {
                    this.runGenerator(lavaCrystalGenerator, world, random, chunkX, chunkZ, ConfigHandler.lavaCrystalOverworldRarity, ConfigHandler.lavaCrystalOverworldMinYSpawn, ConfigHandler.lavaCrystalOverworldMaxYSpawn);
                }
                if (ConfigHandler.enableSteelOreOverworldGen) {
                    this.runGenerator(steelOreGenerator, world, random, chunkX, chunkZ, ConfigHandler.steelOreOverworldRarity, ConfigHandler.steelOreOverworldMinYSpawn, ConfigHandler.steelOreOverworldMaxYSpawn);
                }
                break;
            case 1: //The End
                if (ConfigHandler.enableLavaCrystalTheEndGen) {
                    this.runGenerator(lavaCrystalGenerator, world, random, chunkX, chunkZ, ConfigHandler.lavaCrystalTheEndRarity, ConfigHandler.lavaCrystalTheEndMinYSpawn, ConfigHandler.lavaCrystalTheEndMaxYSpawn);
                }
                if (ConfigHandler.enableSteelOreTheEndGen) {
                    this.runGenerator(steelOreGenerator, world, random, chunkX, chunkZ, ConfigHandler.steelOreTheEndRarity, ConfigHandler.steelOreTheEndMinYSpawn, ConfigHandler.steelOreTheEndMaxYSpawn);
                }
                break;
            case -1: //The Nether
                if (ConfigHandler.enableLavaCrystalTheNetherGen) {
                    this.runGenerator(lavaCrystalGenerator, world, random, chunkX, chunkZ, ConfigHandler.lavaCrystalTheNetherRarity, ConfigHandler.lavaCrystalTheNetherMinYSpawn, ConfigHandler.lavaCrystalTheNetherMaxYSpawn);
                }
                if (ConfigHandler.enableSteelOreTheNetherGen) {
                    this.runGenerator(steelOreGenerator, world, random, chunkX, chunkZ, ConfigHandler.steelOreTheNetherRarity, ConfigHandler.steelOreTheNetherMinYSpawn, ConfigHandler.steelOreTheNetherMaxYSpawn);
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
