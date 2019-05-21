package com.sofodev.armorplus.common.dimension.arena;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ChunkGeneratorArena implements IChunkGenerator {

    private final Random rand;
    protected static final IBlockState FILLER_BLOCK = Blocks.QUARTZ_BLOCK.getDefaultState();
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves scaleNoise;
    private final World world;
    private final boolean mapFeaturesEnabled;
    private final BlockPos spawnPoint;
    // private MapGenEndCity endCityGen = new MapGenEndCity(this);
    private NoiseGeneratorSimplex islandNoise;
    private double[] depthBuffer;
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    private final WorldGenFloatingIsland endIslands = new WorldGenFloatingIsland(FILLER_BLOCK);
    // temporary variables used during event handling
    private int chunkX = 0;
    private int chunkZ = 0;

    public ChunkGeneratorArena(World world, boolean isMapFeaturesEnabled, long seed, BlockPos spawnPoint) {
        this.world = world;
        this.mapFeaturesEnabled = isMapFeaturesEnabled;
        this.spawnPoint = spawnPoint;
        this.rand = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.islandNoise = new NoiseGeneratorSimplex(this.rand);

        InitNoiseGensEvent.ContextEnd ctx =
            new InitNoiseGensEvent.ContextEnd(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, depthNoise, scaleNoise, islandNoise);
        ctx = TerrainGen.getModdedNoiseGenerators(world, this.rand, ctx);
        this.minLimitPerlinNoise = ctx.getLPerlin1();
        this.maxLimitPerlinNoise = ctx.getLPerlin2();
        this.mainPerlinNoise = ctx.getPerlin();
        this.depthNoise = ctx.getDepth();
        this.scaleNoise = ctx.getScale();
        this.islandNoise = ctx.getIsland();
        //   this.endCityGen = (MapGenEndCity) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(this.endCityGen, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.END_CITY);
    }

    /**
     * Generates the chunk at the specified position, from scratch
     */
    @Override
    public Chunk generateChunk(int x, int z) {
        this.chunkX = x;
        this.chunkZ = z;
        this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.setBlocksInChunk(x, z, chunkprimer);

        //  if (this.mapFeaturesEnabled) {
        //      this.endCityGen.generate(this.world, x, z, chunkprimer);
        //  }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] biomeArray = chunk.getBiomeArray();
        IntStream.range(0, biomeArray.length).forEach(i -> biomeArray[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]));

        chunk.generateSkylightMap();
        return chunk;
    }


    /**
     * Generates a bare-bones chunk of nothing but stone or ocean blocks, formed, but featureless.
     */
    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
        long xzOffsetPos = (long) x * (long) x + (long) z * (long) z;
        long xOffsetPos = (long) x * (long) x;
        long zOffsetPos = (long) z * (long) z;
        for (int xOffset = 0; xOffset < 16; ++xOffset) {
            for (int zOffset = 0; zOffset < 16; ++zOffset) {
                if (xzOffsetPos < 16) {
                    primer.setBlockState(xOffset, 17, zOffset, FILLER_BLOCK);
                }
                if (xzOffsetPos < 32) {
                    primer.setBlockState(xOffset, 16, zOffset, FILLER_BLOCK);
                }
                if (xzOffsetPos == 1) {
                    primer.setBlockState(xOffset, 18, zOffset, Blocks.OBSIDIAN.getDefaultState());
                }
            }
        }

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
        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);
        BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

        this.world.getBiome(blockpos.add(16, 0, 16)).decorate(this.world, this.world.rand, blockpos);
        long xzOffsetPos = (long) chunkX * (long) chunkX + (long) chunkZ * (long) chunkZ;

        if (xzOffsetPos > 4096L) {
        }

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, false);
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
        return null;//"EndCity".equals(structureName) && this.endCityGen != null ? this.endCityGen.getNearestStructurePos(worldIn, position, findUnexplored) : null;
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;//("EndCity".equals(structureName) && this.endCityGen != null) && this.endCityGen.isInsideStructure(pos);
    }

    /**
     * Recreates data about structures intersecting given chunk (used for example by getPossibleCreatures), without
     * placing any blocks. When called for the first time before any chunk is generated - also initializes the internal
     * state needed by getPossibleCreatures.
     */
    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }
}