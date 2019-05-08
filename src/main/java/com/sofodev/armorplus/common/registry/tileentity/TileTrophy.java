/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.registry.ModTileEntities.TROPHY;

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
        super(TROPHY.type());
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
            this.entityData.getNbt().putString("id", id.toString());
        }
    }

    @Override
    public void read(NBTTagCompound nbt) {
        super.read(nbt);
        this.loadFromNbt(nbt);
    }

    @Override
    @Nonnull
    public NBTTagCompound write(NBTTagCompound nbt) {
        super.write(nbt);
        this.saveToNbt(nbt);
        return nbt;
    }

    public Entity getCachedEntity() {
        if (this.cachedEntity == null) {
            this.cachedEntity = AnvilChunkLoader.readWorldEntity(this.entityData.getNbt(), this.getWorld(), false);

            if (this.entityData.getNbt().size() == 1 && this.entityData.getNbt().contains("id", 8) && this.cachedEntity instanceof EntityLiving) {
                ((EntityLiving) this.cachedEntity).onInitialSpawn(this.getWorld().getDifficultyForLocation(new BlockPos(this.cachedEntity)), null, getUpdateTag());
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
        if (nbt.contains(name, 8)) {
            this.customName = nbt.getString(name);
        }
        if (nbt.contains(displayedEntity, 10)) {
            this.setNextEntityData(new WeightedSpawnerEntity(1, nbt.getCompound(displayedEntity)));
        }
        if (nbt.contains(entityScale, 99)) {
            this.scale = nbt.getFloat(entityScale);
        }
        this.cachedEntity = null;
    }

    public NBTTagCompound saveToNbt(NBTTagCompound nbt) {
        if (this.hasCustomName()) {
            nbt.putString(name, this.customName);
        }
        ResourceLocation resourcelocation = this.getEntityId();
        if (resourcelocation == null) {
            return nbt;
        }
        nbt.putFloat("EntityScale", this.scale);
        nbt.put(displayedEntity, this.entityData.getNbt().copy());

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
        return this.write(new NBTTagCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        read(pkt.getNbtCompound());
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
