package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TrophyTile extends TileEntity {

    public WeightedSpawnerEntity entityData = new WeightedSpawnerEntity();
    /**
     * Cached instance of the entity to render as a trophy.
     */
    @Nullable
    private Entity displayEntity;
    /**
     * The scale for the entity that will be displayed
     */
    private float scale = 0.2f;

    public TrophyTile() {
        super(ModBlocks.TROPHY_TYPE.get());
    }

    @Nullable
    private ResourceLocation getEntityId() {
        String id = this.entityData.getTag().getString("id");

        try {
            return StringUtils.isNullOrEmpty(id) ? null : new ResourceLocation(id);
        } catch (ResourceLocationException resourcelocationexception) {
            BlockPos pos = this.getBlockPos();
            ArmorPlus.LOGGER.warn("Invalid entity id '{}' at trophy {}:[{},{},{}]", id, this.getLevel().dimension().location(), pos.getX(), pos.getY(), pos.getZ());
            return null;
        }
    }

    public void setEntityId(EntityType<?> type) {
        this.entityData.getTag().putString("id", ForgeRegistries.ENTITIES.getKey(type).toString());
        this.sendBlockUpdate();
    }

    public float getEntityScale() {
        return scale;
    }

    public void setEntityScale(float scale) {
        this.scale = scale;
        this.sendBlockUpdate();
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        if (tag.contains("DisplayEntity", 10)) {
            this.setNextEntityData(new WeightedSpawnerEntity(1, tag.getCompound("DisplayEntity")));
        }
        if (tag.contains("EntityScale", 99)) {
            this.scale = tag.getFloat("EntityScale");
        }
        if (this.getLevel() != null) {
            ResourceLocation rl = new ResourceLocation(tag.getCompound("DisplayEntity").getString("id"));
            EntityType<?> type = ForgeRegistries.ENTITIES.getValue(rl);
            this.displayEntity = type != null ? type.create(this.getLevel()) : null;
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        ResourceLocation resourcelocation = this.getEntityId();
        if (resourcelocation != null) {
            tag.putFloat("EntityScale", this.scale);
            tag.put("DisplayEntity", this.entityData.getTag().copy());
        }
        return tag;
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public Entity getDisplayEntity() {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.entityData.getTag(), this.getLevel(), Function.identity());
            if (this.entityData.getTag().size() == 1) {
                this.entityData.getTag().contains("id", 8);
            }
        }

        return this.displayEntity;
    }

    @Nullable
    @Override
    public World getLevel() {
        return super.getLevel();
    }

    @Override
    public BlockPos getBlockPos() {
        return super.getBlockPos();
    }

    public void setNextEntityData(WeightedSpawnerEntity data) {
        this.entityData = data;
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getBlockPos(), -20, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.load(level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return false;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return super.triggerEvent(id, type);
    }

    public void sendBlockUpdate() {
        this.setChanged();
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}