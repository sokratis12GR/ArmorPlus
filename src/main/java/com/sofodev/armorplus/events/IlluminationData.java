package com.sofodev.armorplus.events;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * The light blocks the Illumination ability placed in a level. They are saved with the level, so the ones left behind by a
 * crash or in a chunk that was unloaded can still be removed once their chunk is loaded.
 */
final class IlluminationData extends SavedData {

    private static final Codec<IlluminationData> CODEC = BlockPos.CODEC.listOf()
            .xmap(spots -> new IlluminationData(new HashSet<>(spots)), data -> List.copyOf(data.spots));
    private static final SavedDataType<IlluminationData> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath(MODID, "illumination"), IlluminationData::new, CODEC);

    private final Set<BlockPos> spots;

    private IlluminationData() {
        this(new HashSet<>());
    }

    private IlluminationData(Set<BlockPos> spots) {
        this.spots = spots;
    }

    static IlluminationData of(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    /** @return true if a light block can be placed in the block: air, or a water source (the light is waterlogged) */
    static boolean canLight(BlockState state) {
        return state.isAir() || isWaterSource(state);
    }

    private static boolean isWaterSource(BlockState state) {
        return state.is(Blocks.WATER) && state.getFluidState().isSource();
    }

    void place(ServerLevel level, BlockPos pos) {
        boolean water = isWaterSource(level.getBlockState(pos));
        level.setBlockAndUpdate(pos, Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 15).setValue(LightBlock.WATERLOGGED, water));
        this.spots.add(pos);
        setDirty();
    }

    /** Removes the light block if its chunk is loaded, otherwise {@link #clearStale} does it once it's loaded again. */
    void clear(ServerLevel level, BlockPos pos) {
        if (!level.isLoaded(pos)) return;
        // Only clear if it's still our light block - a player may have replaced it with their own block already.
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.LIGHT)) {
            level.setBlockAndUpdate(pos, (state.getValue(LightBlock.WATERLOGGED) ? Blocks.WATER : Blocks.AIR).defaultBlockState());
        }
        this.spots.remove(pos);
        setDirty();
    }

    /** Clears the saved light blocks in loaded chunks that no player is using. */
    void clearStale(ServerLevel level, Predicate<BlockPos> inUse) {
        for (BlockPos pos : List.copyOf(this.spots)) {
            if (!inUse.test(pos)) clear(level, pos);
        }
    }
}
