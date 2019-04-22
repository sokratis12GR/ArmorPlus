/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileTrophy extends TileEntity {

    public WeightedSpawnerEntity entityData = new WeightedSpawnerEntity();
    /**
     * Cached instance of the entity to render as a trophy.
     */
    private Entity cachedEntity;
    /**
     * The scale for the entity that will be displayed
     */
    private float scale;

    private String customName;

    public TileTrophy() {
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.trophy";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String name) {
        this.customName = name;
    }

    public ResourceLocation getEntityId() {
        String s = this.entityData.getNbt().getString("id");
        return StringUtils.isNullOrEmpty(s) ? new ResourceLocation("minecraft:pig") : new ResourceLocation(s);
    }

    public void setEntityId(@Nullable ResourceLocation id) {
        if (id != null && ForgeRegistries.ENTITIES.containsKey(id)) {
            this.entityData.getNbt().setString("id", id.toString());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.loadFromNbt(nbt);
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.saveToNbt(nbt);
        return nbt;
    }

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


    private String name = "CustomName";
    private String displayedEntity = "DisplayEntity";
    private String entityScale = "EntityScale";

    public void loadFromNbt(NBTTagCompound nbt) {
        if (nbt.hasKey(name, 8)) {
            this.customName = nbt.getString(name);
        }
        if (nbt.hasKey(displayedEntity, 10)) {
            this.setNextEntityData(new WeightedSpawnerEntity(1, nbt.getCompoundTag(displayedEntity)));
        }
        if (nbt.hasKey(entityScale, 99)) {
            this.scale = nbt.getFloat(entityScale);
        }
        this.cachedEntity = null;
    }

    public NBTTagCompound saveToNbt(NBTTagCompound nbt) {
        if (this.hasCustomName()) {
            nbt.setString(name, this.customName);
        }
        ResourceLocation resourcelocation = this.getEntityId();
        if (resourcelocation == null) {
            return nbt;
        }
        nbt.setFloat("EntityScale", this.scale);
        nbt.setTag(displayedEntity, this.entityData.getNbt().copy());

        return nbt;
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
        if (!this.getWorld().isRemote) {
            return;
        }
        if (this.getWorld() != null) {
            IBlockState iblockstate = this.getWorld().getBlockState(this.getPos());
            this.getWorld().notifyBlockUpdate(this.pos, iblockstate, iblockstate, 4);
        }
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 12, this.getUpdateTag());
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
        return false;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return super.receiveClientEvent(id, type);
    }
}
