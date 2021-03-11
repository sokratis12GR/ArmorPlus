package com.sofodev.armorplus.registry.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

import java.util.BitSet;
import java.util.Random;
import java.util.stream.IntStream;

public class APOreFeature extends Feature<APOreFeatureConfig> {
    public APOreFeature(Codec<APOreFeatureConfig> codec) {
        super(codec);
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, APOreFeatureConfig config) {
        float randomNextPI = rand.nextFloat() * (float) Math.PI;
        float size = (float) config.size / 8.0F;
        int veinSize = MathHelper.ceil(((float) config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
        double startX = (double) pos.getX() + Math.sin(randomNextPI) * (double) size;
        double endX = (double) pos.getX() - Math.sin(randomNextPI) * (double) size;
        double startZ = (double) pos.getZ() + Math.cos(randomNextPI) * (double) size;
        double endZ = (double) pos.getZ() - Math.cos(randomNextPI) * (double) size;
        double startY = pos.getY() + rand.nextInt(3) - 2;
        double endY = pos.getY() + rand.nextInt(3) - 2;
        int posX = pos.getX() - MathHelper.ceil(size) - veinSize;
        int posY = pos.getY() - 2 - veinSize;
        int posZ = pos.getZ() - MathHelper.ceil(size) - veinSize;
        int maxSize = 2 * (MathHelper.ceil(size) + veinSize);
        int minSize = 2 * (2 + veinSize);

        for (int nextX = posX; nextX <= posX + maxSize; ++nextX) {
            for (int nextZ = posZ; nextZ <= posZ + maxSize; ++nextZ) {
                if (posY <= reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, nextX, nextZ)) {
                    return this.place(reader, rand, config, startX, endX, startZ, endZ, startY, endY, posX, posY, posZ, maxSize, minSize);
                }
            }
        }

        return false;
    }

    protected boolean place(IWorld worldIn, Random random, APOreFeatureConfig config, double startX, double endX, double startZ, double endZ, double startY, double endY, int posX, int posY, int posZ, int maxSize, int minSize) {
        int i = 0;
        BitSet bitset = new BitSet(maxSize * minSize * maxSize);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int size = config.size;
        double[] veinSize = new double[size * 4];

        IntStream.range(0, size).forEach(k -> {
            float f = (float) k / (float) size;
            double x = MathHelper.lerp(f, startX, endX);
            double y = MathHelper.lerp(f, startY, endY);
            double z = MathHelper.lerp(f, startZ, endZ);
            double d6 = random.nextDouble() * (double) size / 16.0D;
            double d7 = ((double) (MathHelper.sin((float) Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            veinSize[k * 4 + 0] = x;
            veinSize[k * 4 + 1] = y;
            veinSize[k * 4 + 2] = z;
            veinSize[k * 4 + 3] = d7;
        });

        for (int i3 = 0; i3 < size - 1; ++i3) {
            if (!(veinSize[i3 * 4 + 3] <= 0.0D)) {
                for (int k3 = i3 + 1; k3 < size; ++k3) {
                    if (!(veinSize[k3 * 4 + 3] <= 0.0D)) {
                        double d12 = veinSize[i3 * 4 + 0] - veinSize[k3 * 4 + 0];
                        double d13 = veinSize[i3 * 4 + 1] - veinSize[k3 * 4 + 1];
                        double d14 = veinSize[i3 * 4 + 2] - veinSize[k3 * 4 + 2];
                        double d15 = veinSize[i3 * 4 + 3] - veinSize[k3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                            if (d15 > 0.0D) {
                                veinSize[k3 * 4 + 3] = -1.0D;
                            } else {
                                veinSize[i3 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for (int j3 = 0; j3 < size; ++j3) {
            double d11 = veinSize[j3 * 4 + 3];
            if (!(d11 < 0.0D)) {
                double d1 = veinSize[j3 * 4 + 0];
                double d3 = veinSize[j3 * 4 + 1];
                double d5 = veinSize[j3 * 4 + 2];
                int l = Math.max(MathHelper.floor(d1 - d11), posX);
                int l3 = Math.max(MathHelper.floor(d3 - d11), posY);
                int i1 = Math.max(MathHelper.floor(d5 - d11), posZ);
                int j1 = Math.max(MathHelper.floor(d1 + d11), l);
                int k1 = Math.max(MathHelper.floor(d3 + d11), l3);
                int l1 = Math.max(MathHelper.floor(d5 + d11), i1);

                for (int i2 = l; i2 <= j1; ++i2) {
                    double d8 = ((double) i2 + 0.5D - d1) / d11;
                    if (d8 * d8 < 1.0D) {
                        for (int j2 = l3; j2 <= k1; ++j2) {
                            double d9 = ((double) j2 + 0.5D - d3) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D) {
                                for (int k2 = i1; k2 <= l1; ++k2) {
                                    double d10 = ((double) k2 + 0.5D - d5) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                                        int l2 = i2 - posX + (j2 - posY) * maxSize + (k2 - posZ) * maxSize * minSize;
                                        if (!bitset.get(l2)) {
                                            bitset.set(l2);
                                            blockpos$mutable.set(i2, j2, k2);
                                            if (config.target.test(worldIn.getBlockState(blockpos$mutable), random)) {
                                                worldIn.setBlock(blockpos$mutable, config.state, 2);
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
