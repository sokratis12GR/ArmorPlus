package com.sofodev.armorplus.registry.features.surface;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.ValleySurfaceBuilder;

public class SoulValleySurfaceBuilder extends ValleySurfaceBuilder {
    private static final BlockState SOUL_SOIL = Blocks.SOUL_SOIL.defaultBlockState();
    private static final BlockState SOUL_SAND = Blocks.SOUL_SAND.defaultBlockState();
    private static final ImmutableList<BlockState> BLOCK_STATES = ImmutableList.of(SOUL_SOIL, SOUL_SAND);

    public SoulValleySurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    protected ImmutableList<BlockState> getFloorBlockStates() {
        return BLOCK_STATES;
    }

    protected ImmutableList<BlockState> getCeilingBlockStates() {
        return BLOCK_STATES;
    }

    protected BlockState getPatchBlockState() {
        return SOUL_SAND;
    }
}
