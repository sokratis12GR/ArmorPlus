package com.sofodev.armorplus.network;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BasePacketHandler {
    private int index = 0;

    public BasePacketHandler() {
    }

    protected static SimpleChannel createChannel(ResourceLocation name) {
        NetworkRegistry.ChannelBuilder channelBuilder = NetworkRegistry.ChannelBuilder.named(name);
        String protocolVersion = getProtocolVersion();
        protocolVersion.getClass();
        channelBuilder = channelBuilder.clientAcceptedVersions(protocolVersion::equals);
        protocolVersion = getProtocolVersion();
        protocolVersion.getClass();
        return channelBuilder.serverAcceptedVersions(protocolVersion::equals)
                .networkProtocolVersion(BasePacketHandler::getProtocolVersion)
                .simpleChannel();
    }

    private static String getProtocolVersion() {
        return ArmorPlus.instance == null ? "999.999.999" : ArmorPlus.VERSION.replace("-", ".");
    }

    public static String readString(FriendlyByteBuf buffer) {
        return buffer.readUtf(32767);
    }

    public static Vector3d readVector3d(FriendlyByteBuf buffer) {
        return new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    public static void writeVector3d(FriendlyByteBuf buffer, Vector3f vector) {
        buffer.writeDouble(vector.x());
        buffer.writeDouble(vector.y());
        buffer.writeDouble(vector.z());
    }

    protected abstract SimpleChannel getChannel();

    public abstract void initialize();

    protected <MSG> void registerClientToServer(Class<MSG> type, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {
        this.getChannel()
                .registerMessage(this.index++, type, encoder, decoder, consumer, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    protected <MSG> void registerServerToClient(Class<MSG> type, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {
        this.getChannel()
                .registerMessage(this.index++, type, encoder, decoder, consumer, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public <MSG> void sendTo(MSG message, ServerPlayer player) {
        this.getChannel().sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public <MSG> void sendToAll(MSG message) {
        this.getChannel().send(PacketDistributor.ALL.noArg(), message);
    }

    public <MSG> void sendToAllIfLoaded(MSG message) {
        if (ServerLifecycleHooks.getCurrentServer() != null) {
            this.sendToAll(message);
        }

    }

    public <MSG> void sendToDimension(MSG message, ResourceKey<Level> dimension) {
        this.getChannel().send(PacketDistributor.DIMENSION.with(() -> {
            return dimension;
        }), message);
    }

    public <MSG> void sendToServer(MSG message) {
        this.getChannel().sendToServer(message);
    }

    public <MSG> void sendToAllTracking(MSG message, Entity entity) {
        this.getChannel().send(PacketDistributor.TRACKING_ENTITY.with(() -> {
            return entity;
        }), message);
    }

    public <MSG> void sendToAllTrackingAndSelf(MSG message, Entity entity) {
        this.getChannel().send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> {
            return entity;
        }), message);
    }

    public <MSG> void sendToAllTracking(MSG message, BlockEntity tile) {
        this.sendToAllTracking(message, tile.getLevel(), tile.getBlockPos());
    }

    public <MSG> void sendToAllTracking(MSG message, Level world, BlockPos pos) {
        if (world instanceof ServerLevel) {
            ((ServerLevel) world).getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach((p) -> {
                this.sendTo(message, p);
            });
        } else {
            this.getChannel().send(PacketDistributor.TRACKING_CHUNK.with(() -> {
                return world.getChunk(pos.getX() >> 4, pos.getZ() >> 4);
            }), message);
        }

    }
}
