package net.thedragonteam.armorplus.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityTrophy extends TileEntity {

    public WeightedSpawnerEntity entityData = new WeightedSpawnerEntity();
    /**
     * Cached instance of the entity to render as a trophy.
     */
    private Entity cachedEntity;
    /**
     * The scale for the entity that will be displayed
     */
    private float scale;

    public TileEntityTrophy() {
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return super.getDisplayName();
    }

    @Nullable
    public ResourceLocation getEntityId() {
        String s = this.entityData.getNbt().getString("id");
        return StringUtils.isNullOrEmpty(s) ? null : new ResourceLocation(s);
    }

    public void setEntityId(@Nullable ResourceLocation id) {
        if (id != null) {
            this.entityData.getNbt().setString("id", id.toString());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("DisplayEntity", 10)) {
            this.setNextEntityData(new WeightedSpawnerEntity(1, nbt.getCompoundTag("DisplayEntity")));
        }
        if (nbt.hasKey("EntityScale", 99)) {
            this.scale = nbt.getFloat("EntityScale");
        }
        this.cachedEntity = null;
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        ResourceLocation resourcelocation = this.getEntityId();
        if (resourcelocation == null) {
            return nbt;
        }
        nbt.setFloat("EntityScale", this.scale);
        nbt.setTag("DisplayEntity", this.entityData.getNbt().copy());
        return nbt;
    }

    @SideOnly(Side.CLIENT)
    public Entity getCachedEntity() {
        if (this.cachedEntity == null) {
            this.cachedEntity = AnvilChunkLoader.readWorldEntity(this.entityData.getNbt(), this.getWorld(), false);

            if (this.entityData.getNbt().getSize() == 1 && this.entityData.getNbt().hasKey("id", 8) && this.cachedEntity instanceof EntityLiving) {
                ((EntityLiving) this.cachedEntity).onInitialSpawn(this.getWorld().getDifficultyForLocation(new BlockPos(this.cachedEntity)), null);
            }
        }

        return this.cachedEntity;
    }

    public float getEntityScale() {
        return scale;
    }

    public void setEntityScale(float scale) {
        this.scale = scale;
    }

    public static void registerTrophyFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, (IDataFixer fixer1, NBTTagCompound compound, int versionIn) -> {
            if (TileEntity.getKey(TileEntityMobSpawner.class).equals(new ResourceLocation(compound.getString("id")))) {
                compound.setTag("DisplayEntity", fixer1.process(FixTypes.ENTITY, compound.getCompoundTag("DisplayEntity"), versionIn));
            }

            return compound;
        });
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public BlockPos getPos() {
        return this.pos;
    }

    public void setNextEntityData(WeightedSpawnerEntity entityData) {
        this.entityData = entityData;

        if (this.getWorld() != null) {
            IBlockState iblockstate = this.getWorld().getBlockState(this.getPos());
            this.getWorld().notifyBlockUpdate(this.pos, iblockstate, iblockstate, 4);
        }
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean onlyOpsCanSetNbt() {
        return true;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return super.receiveClientEvent(id, type);
    }
}
