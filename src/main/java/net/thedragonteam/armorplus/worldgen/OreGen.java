package net.thedragonteam.armorplus.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.armorplus.registry.ModBlocks;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * sokratis12gr.armorplus.worldgen
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:30 PM.
 */
public class OreGen implements IWorldGenerator {

    private void nether(Random random, int x, int z, World world) {
        generateOre(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), Blocks.NETHERRACK, random, x, z, world, lavaCrystalTheNetherRarity, lavaCrystalTheNetherMinYSpawn, lavaCrystalTheNetherMaxYSpawn, lavaCrystalTheNetherVeinAmount);
    }

    private void overworld(Random random, int x, int z, World world) {
        generateOre(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), Blocks.STONE, random, x, z, world, lavaCrystalOverworldRarity, lavaCrystalOverworldMinYSpawn, lavaCrystalOverworldMaxYSpawn, lavaCrystalOverworldVeinAmount);
    }


    private void end(Random random, int x, int z, World world) {
        generateOre(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), Blocks.END_STONE, random, x, z, world, lavaCrystalTheEndRarity, lavaCrystalTheEndMinYSpawn, lavaCrystalTheEndMaxYSpawn, lavaCrystalTheEndVeinAmount);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        switch (world.provider.getDimension()) {
            case -1:
                if (enableLavaCrystalTheNetherGen)
                    nether(random, x, z, world);
                break;
            case 0:
                if (enableLavaCrystalOverworldGen)
                    overworld(random, x, z, world);
                break;
            case 1:
                if (enableLavaCrystalTheEndGen)
                    end(random, x, z, world);
                break;
        }
    }

    private void generateOre(IBlockState state, Block blockin, Random random, int x, int z, World world, int chance, int minY, int maxY, int vienSize) {
        int hightRange = maxY - minY + 1;

        for (int i = 0; i < chance; i++) {
            int posX = x * 16 + random.nextInt(16);
            int posY = minY + random.nextInt(hightRange);
            int posZ = z * 16 + random.nextInt(16);
            new WorldGenMinable(state, vienSize, BlockMatcher.forBlock(blockin)).generate(world, random, new BlockPos(posX, posY, posZ));
        }
    }
}