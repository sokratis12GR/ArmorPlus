package com.sofodev.armorplus.network;

import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScope;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Sent by the effect screens: switches one of the player's effects off, or back on.
 *
 * @param scope the {@link EffectScope#name()} of the screen it's from, each scope saves its own toggles
 * @param key   the effect's {@link EffectEntry#toggleKey()}: "set|effect" for armor, the effect id for tool, weapon and ranged
 *              effects (those stay off for every item of that kind)
 */
public record ToggleEffectPayload(String scope, String key) implements CustomPacketPayload {

    public ToggleEffectPayload(EffectScope scope, String key) {
        this(scope.name(), key);
    }

    public static final Type<ToggleEffectPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MODID, "toggle_effect"));
    public static final StreamCodec<ByteBuf, ToggleEffectPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.stringUtf8(64), ToggleEffectPayload::scope,
            ByteBufCodecs.stringUtf8(256), ToggleEffectPayload::key,
            ToggleEffectPayload::new);
    /** Far more than a player normally uses, keeps the saved lists from growing without limit. */
    private static final int MAX_DISABLED = 256;

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleEffectPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player) || !ArmorPlusConfig.allowEffectToggles.get()) return;

            EffectScope scope = EffectScopes.byName(payload.scope());
            if (scope == null) return;
            List<String> disabled = new ArrayList<>(player.getData(scope.disabledEffects()));
            boolean turnOff = !disabled.contains(payload.key());
            // only effects the player actually has can be switched off, turning one back on always works
            Optional<BuffInstance> buff = scope.find(player, payload.key()).map(EffectEntry::buff);
            if (turnOff && buff.isEmpty()) return;
            if (turnOff && disabled.size() >= MAX_DISABLED) {
                player.sendOverlayMessage(Component.translatable("armorplus.effects.toggle_limit", MAX_DISABLED).withStyle(ChatFormatting.RED));
                return;
            }

            if (turnOff) disabled.add(payload.key());
            else disabled.remove(payload.key());
            player.setData(scope.disabledEffects(), List.copyOf(disabled));
            scope.toggled(player, turnOff ? buff : Optional.empty());
        });
    }
}
