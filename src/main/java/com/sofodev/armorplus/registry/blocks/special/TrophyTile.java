package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.ResourceLocationException;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public class TrophyTile extends BlockEntity {

    private SpawnData entityData = new SpawnData();
    /**
     * Cached instance of the entity to render as a trophy.
     */
    @Nullable
    private Entity displayEntity;
    /**
     * The scale for the entity that will be displayed
     */
    private float scale = 0.2f;

    public TrophyTile(BlockPos pos, BlockState state) {
        super(ModBlocks.TROPHY_TYPE.get(), pos, state);
    }

    @Nullable
    private ResourceLocation getEntityId() {
        String id = this.entityData.getEntityToSpawn().getString("id");

        try {
            return StringUtil.isNullOrEmpty(id) ? null : new ResourceLocation(id);
        } catch (ResourceLocationException resourcelocationexception) {
            BlockPos pos = this.getBlockPos();
            ArmorPlus.LOGGER.warn("Invalid entity id '{}' at trophy {}:[{},{},{}]", id, this.getLevel().dimension().location(), pos.getX(), pos.getY(), pos.getZ());
            return null;
        }
    }

    public void setEntityId(EntityType<?> type) {
        this.entityData.getEntityToSpawn().putString("id", ForgeRegistries.ENTITIES.getKey(type).toString());
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
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("DisplayEntity", 10)) {
            this.setNextEntityData(new SpawnData(tag.getCompound("DisplayEntity"), Optional.empty()));
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
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        ResourceLocation resourcelocation = this.getEntityId();
        if (resourcelocation != null) {
            tag.putFloat("EntityScale", this.scale);
            tag.put("DisplayEntity", this.entityData.getEntityToSpawn().copy());
        }
        return tag;
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public Entity getDisplayEntity() {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.entityData.entityToSpawn(), this.getLevel(), Function.identity());
            if (this.entityData.entityToSpawn().size() == 1) {
                this.entityData.entityToSpawn().contains("id", 8);
            }
        }

        return this.displayEntity;
    }

    @Nullable
    @Override
    public Level getLevel() {
        return super.getLevel();
    }

    @Override
    public BlockPos getBlockPos() {
        return super.getBlockPos();
    }

    public void setNextEntityData(SpawnData data) {
        this.entityData = data;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

//    @Override
//    public Packet<ClientGamePacketListener> getUpdatePacket() {
//        return new Packet<ClientGamePacketListener>(this.getBlockPos(), -20, this.getUpdateTag());
//    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.save(new CompoundTag());
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return false;
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