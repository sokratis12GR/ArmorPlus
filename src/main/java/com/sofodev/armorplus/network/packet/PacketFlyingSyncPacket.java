package com.sofodev.armorplus.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.jetbrains.annotations.Nullable;

public class PacketFlyingSyncPacket  {

    private final boolean allowFlying;
    private final boolean isFlying;

    public PacketFlyingSyncPacket(boolean allowFlying, boolean isFlying) {
        this.allowFlying = allowFlying;
        this.isFlying = isFlying;
    }

    public void handle(CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            @Nullable ServerPlayer player = ctx.getSender();
            if (player != null) {
                player.getAbilities().mayfly = this.allowFlying;
                player.getAbilities().flying = this.isFlying;
            }
        });
        ctx.setPacketHandled(true);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(this.allowFlying);
        buf.writeBoolean(this.isFlying);
    }

    public static PacketFlyingSyncPacket decode(FriendlyByteBuf buffer) {
        return new PacketFlyingSyncPacket(buffer.readBoolean(), buffer.readBoolean());
    }
}