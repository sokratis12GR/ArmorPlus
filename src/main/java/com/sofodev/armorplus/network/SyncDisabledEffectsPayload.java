package com.sofodev.armorplus.network;

import com.sofodev.armorplus.network.ToggleEffectPayload.Scope;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Sends one of the player's disabled effect lists to their own client, for the effect screens and tooltips. Needed because
 * attachments can't sync themselves on NeoForge 21.1.
 */
public record SyncDisabledEffectsPayload(Scope scope, List<String> disabled) implements CustomPacketPayload {

    public static final Type<SyncDisabledEffectsPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "sync_disabled_effects"));
    public static final StreamCodec<ByteBuf, SyncDisabledEffectsPayload> STREAM_CODEC = StreamCodec.composite(
            Scope.STREAM_CODEC, SyncDisabledEffectsPayload::scope,
            ByteBufCodecs.stringUtf8(256).apply(ByteBufCodecs.list()), SyncDisabledEffectsPayload::disabled,
            SyncDisabledEffectsPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /** Sends every list of the player. */
    public static void syncAll(ServerPlayer player) {
        for (Scope scope : Scope.values()) sync(player, scope);
    }

    public static void sync(ServerPlayer player, Scope scope) {
        PacketDistributor.sendToPlayer(player, new SyncDisabledEffectsPayload(scope, player.getData(scope.attachment())));
    }

    public static void handle(SyncDisabledEffectsPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> context.player().setData(payload.scope().attachment(), List.copyOf(payload.disabled())));
    }
}
