package com.sofodev.armorplus.network;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

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
        return channelBuilder.serverAcceptedVersions(protocolVersion::equals).networkProtocolVersion(BasePacketHandler::getProtocolVersion).simpleChannel();
    }

    private static String getProtocolVersion() {
        return ArmorPlus.instance == null ? "999.999.999" : ArmorPlus.VERSION.replace("-", ".");
    }

    public static String readString(PacketBuffer buffer) {
        return buffer.readUtf(32767);
    }

    public static Vector3d readVector3d(PacketBuffer buffer) {
        return new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    public static void writeVector3d(PacketBuffer buffer, Vector3d vector) {
        buffer.writeDouble(vector.x());
        buffer.writeDouble(vector.y());
        buffer.writeDouble(vector.z());
    }

    protected abstract SimpleChannel getChannel();

    public abstract void initialize();

    protected <MSG> void registerClientToServer(Class<MSG> type, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<Context>> consumer) {
        this.getChannel().registerMessage(this.index++, type, encoder, decoder, consumer, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    protected <MSG> void registerServerToClient(Class<MSG> type, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<Context>> consumer) {
        this.getChannel().registerMessage(this.index++, type, encoder, decoder, consumer, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public <MSG> void sendTo(MSG message, ServerPlayerEntity player) {
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

    public <MSG> void sendToDimension(MSG message, RegistryKey<World> dimension) {
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

    public <MSG> void sendToAllTracking(MSG message, TileEntity tile) {
        this.sendToAllTracking(message, tile.getLevel(), tile.getBlockPos());
    }

    public <MSG> void sendToAllTracking(MSG message, World world, BlockPos pos) {
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach((p) -> {
                this.sendTo(message, p);
            });
        } else {
            this.getChannel().send(PacketDistributor.TRACKING_CHUNK.with(() -> {
                return world.getChunk(pos.getX() >> 4, pos.getZ() >> 4);
            }), message);
        }

    }
}
