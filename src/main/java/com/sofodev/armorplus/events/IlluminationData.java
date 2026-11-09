package com.sofodev.armorplus.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;

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

    private static final String NAME = MODID + "_illumination";
    private static final String SPOTS_KEY = "spots";
    private static final SavedData.Factory<IlluminationData> FACTORY = new SavedData.Factory<>(IlluminationData::new, IlluminationData::load);

    private final Set<BlockPos> spots;

    private IlluminationData() {
        this(new HashSet<>());
    }

    private IlluminationData(Set<BlockPos> spots) {
        this.spots = spots;
    }

    private static IlluminationData load(CompoundTag tag, HolderLookup.Provider registries) {
        Set<BlockPos> spots = new HashSet<>();
        for (long packed : tag.getLongArray(SPOTS_KEY)) {
            spots.add(BlockPos.of(packed));
        }
        return new IlluminationData(spots);
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put(SPOTS_KEY, new LongArrayTag(spots.stream().mapToLong(BlockPos::asLong).toArray()));
        return tag;
    }

    static IlluminationData of(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(FACTORY, NAME);
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
