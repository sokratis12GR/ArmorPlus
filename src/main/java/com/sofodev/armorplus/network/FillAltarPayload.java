package com.sofodev.armorplus.network;

import com.sofodev.armorplus.registry.rune.RitualMenu;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Sent by JEI's recipe transfer button: fills the open Rune Altar screen from the player's inventory. The stacks only say what
 * to look for, everything comes out of the inventory on the server.
 *
 * @param containerId the screen it's meant for
 * @param tab         the {@link RitualMenu.Tab} to switch to, unused on the extraction screen
 * @param first       for the first input slot, or EMPTY to leave it empty
 * @param second      for the second input slot, or EMPTY to leave it empty
 * @param catalyst    the catalyst and how many are still needed, or EMPTY
 * @param typeCatalyst the catalyst of the rune's type and how many are still needed, or EMPTY, unused on the extraction screen
 * @param buffId      the buff to select on the extraction screen, unused in the ritual
 */
public record FillAltarPayload(int containerId, int tab, ItemStack first, ItemStack second, ItemStack catalyst, ItemStack typeCatalyst, String buffId) implements CustomPacketPayload {

    public static final Type<FillAltarPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MODID, "fill_altar"));
    public static final StreamCodec<RegistryFriendlyByteBuf, FillAltarPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, FillAltarPayload::containerId,
            ByteBufCodecs.VAR_INT, FillAltarPayload::tab,
            ItemStack.OPTIONAL_STREAM_CODEC, FillAltarPayload::first,
            ItemStack.OPTIONAL_STREAM_CODEC, FillAltarPayload::second,
            ItemStack.OPTIONAL_STREAM_CODEC, FillAltarPayload::catalyst,
            ItemStack.OPTIONAL_STREAM_CODEC, FillAltarPayload::typeCatalyst,
            ByteBufCodecs.stringUtf8(256), FillAltarPayload::buffId,
            FillAltarPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(FillAltarPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player) || player.containerMenu.containerId != payload.containerId() || !player.containerMenu.stillValid(player)) return;
            if (player.containerMenu instanceof RitualMenu menu) {
                menu.fillFromInventory(payload.tab(), payload.first(), payload.second(), payload.catalyst(), payload.typeCatalyst());
            } else if (player.containerMenu instanceof RuneAltarMenu menu) {
                menu.fillFromInventory(payload.first(), payload.second(), payload.catalyst(), payload.buffId());
            }
        });
    }
}
