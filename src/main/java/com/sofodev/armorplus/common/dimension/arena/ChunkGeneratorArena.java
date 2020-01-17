package com.sofodev.armorplus.common.dimension.arena;

import com.sofodev.armorplus.common.util.Utils;
import com.sofodev.armorplus.common.util.WorldGenUtils;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import javax.annotation.Nullable;
import java.util.List;

public class ChunkGeneratorArena implements IChunkGenerator {

    protected static final IBlockState FILLER_BLOCK = Blocks.QUARTZ_BLOCK.getDefaultState();
    private final World world;

    public ChunkGeneratorArena(World world, boolean isMapFeaturesEnabled, long seed, BlockPos spawnPoint) {
        this.world = world;
    }

    /**
     * Generates the chunk at the specified position, from scratch
     */
    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        ResourceLocation tower = Utils.setRL("tower");
      //  BlockPos posXZ = new BlockPos(posX, 1, posZ);
        if (!(world instanceof WorldServer)) return chunk;

        WorldServer serverworld = (WorldServer) world;
        MinecraftServer server = serverworld.getMinecraftServer();

        PlacementSettings settings = new PlacementSettings();
        Template template = serverworld.getSaveHandler().getStructureTemplateManager().getTemplate(server, tower);
        template.setAuthor("sokratis12GR");
      //  template.addBlocksToWorld(serverworld, chunk.pos, settings);
        chunk.generateSkylightMap();
        return chunk;
    }


    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     *
     * @param chunkX Chunk x coordinate
     * @param chunkZ Chunk z coordinate
     */
    @Override
    public void populate(int chunkX, int chunkZ) {
        setFallingInstantly(true);
        BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

        this.world.getBiome(blockpos.add(16, 0, 16)).decorate(this.world, this.world.rand, blockpos);
        setFallingInstantly(false);
    }

    private static void setFallingInstantly(boolean value) {
        BlockFalling.fallInstantly = value;
    }

    /**
     * Called to generate additional structures after initial worldgen, used by ocean monuments
     */
    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return this.world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    /**
     * Recreates data about structures intersecting given chunk (used for example by getPossibleCreatures), without
     * placing any blocks. When called for the first time before any chunk is generated - also initializes the internal
     * state needed by getPossibleCreatures.
     */
    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}