package net.thedragonteam.armorplus.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.thedragonteam.armorplus.registry.ModBlocks;

import java.util.Random;

/**
 * net.thedragonteam.armorplus.worldgen
 * ArmorPlus created by sokratis12GR on 8/15/2016.
 * - TheDragonTeam
 */
public class WorldGenLavaCactus extends WorldGenerator {

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 10; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos)) {
                int j = 1 + rand.nextInt(rand.nextInt(3) + 1);

                for (int k = 0; k < j; ++k) {
                    worldIn.setBlockState(blockpos.up(k), ModBlocks.LAVA_CACTUS.getDefaultState(), 2);
                }
            }
        }

        return true;
    }
}