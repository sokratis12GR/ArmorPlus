package com.sofodev.armorplus.common.dimension.arena;

import com.sofodev.armorplus.common.registry.ModDimensions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class ArenaProvider extends WorldProvider {

    @Override
    public void init() {
        this.biomeProvider = new BiomeProviderSingle(ForgeRegistries.BIOMES.getValue(setRL("arena")));
    }

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.arenaDimension;
    }

    @Override
    public String getSaveFolder() {
        return "Arena";
    }

    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorArena(this.world, false, this.world.getSeed(), this.getSpawnCoordinate());
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.0F;
    }

    /**
     * Returns array with sunrise/sunset colors
     */
    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    //  /**
    //   * Return Vec3D with biome specific fog color
    //   */
    //  @SideOnly(Side.CLIENT)
    //  @Override
    //  public Vec3d getFogColor(float partialTicks, float celestialTicks) {
    //      double red = 0.62;
    //      double blue = 0.15;
    //      double green = 0.163;
    //      return new Vec3d(red, green, blue);
    //  }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isSkyColored() {
        return false;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    @Override
    public boolean canRespawnHere() {
        return false;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    /**
     * the y level at which clouds are rendered.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight() {
        return 8.0F;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        BlockPos pos = new BlockPos(x, 15, z);
        boolean isSolid = this.world.getGroundAboveSeaLevel(pos).getMaterial().blocksMovement();
        return isSolid && this.world.getBlockState(pos) == Blocks.OBSIDIAN.getDefaultState();
    }

    @Override
    public BlockPos getSpawnCoordinate() {
        return new BlockPos(100, 50, 0);
    }

    @Override
    public int getAverageGroundLevel() {
        return 50;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    /**
     * Called when the world is performing a save. Only used to save the state of the Dragon Boss fight in
     * WorldProviderEnd in Vanilla.
     */
    @Override
    public void onWorldSave() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.world.getWorldInfo().setDimensionData(this.world.provider.getDimension(), nbttagcompound);
    }

    /**
     * Called when the world is updating entities. Only used in WorldProviderEnd to update the DragonFightManager in
     * Vanilla.
     */
    @Override
    public void onWorldUpdateEntities() {
    }

    /**
     * Called when a Player is added to the provider's world.
     */
    @Override
    public void onPlayerAdded(EntityPlayerMP player) {
    }

    /**
     * Called when a Player is removed from the provider's world.
     */
    @Override
    public void onPlayerRemoved(EntityPlayerMP player) {
    }
}
