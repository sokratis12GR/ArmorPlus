package com.sofodev.armorplus.common.dimension.arena;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFloatingIsland extends WorldGenerator {
    private final IBlockState blockState;

    public WorldGenFloatingIsland(IBlockState blockState) {
        this.blockState = blockState;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        float bound = (float) (rand.nextInt(1) + 2);

        for (int y = 0; bound > 0.25F; --y) {
            for (int x = MathHelper.floor(-bound); x <= MathHelper.ceil(bound); ++x) {
                for (int z = MathHelper.floor(-bound); z <= MathHelper.ceil(bound); ++z) {
                    if ((float) (x * x + z * z) <= (bound + 1.0F) * (bound + 1.0F)) {
                        this.setBlockAndNotifyAdequately(worldIn, position.add(x, y, z), blockState);
                    }
                }
            }

            bound = (float) ((double) bound - ((double) rand.nextInt(2) + 0.5D));
        }

        return true;
    }
}