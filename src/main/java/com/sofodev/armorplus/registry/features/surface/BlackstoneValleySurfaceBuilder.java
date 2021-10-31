package com.sofodev.armorplus.registry.features.surface;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.ValleySurfaceBuilder;

public class BlackstoneValleySurfaceBuilder extends ValleySurfaceBuilder {
    private static final BlockState OBSIDIAN = Blocks.OBSIDIAN.defaultBlockState();
    private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
    private static final ImmutableList<BlockState> BLOCK_STATES = ImmutableList.of(BLACKSTONE, OBSIDIAN);

    public BlackstoneValleySurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    protected ImmutableList<BlockState> getFloorBlockStates() {
        return BLOCK_STATES;
    }

    protected ImmutableList<BlockState> getCeilingBlockStates() {
        return BLOCK_STATES;
    }

    protected BlockState getPatchBlockState() {
        return BLACKSTONE;
    }
}
