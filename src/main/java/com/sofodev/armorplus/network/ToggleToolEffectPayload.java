package com.sofodev.armorplus.network;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import io.netty.buffer.ByteBuf;
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
 * Sent by the Tool Effects screen: switches one of the held tool's rune effects off (for every tool), or back on.
 *
 * @param id the effect id, see {@link ToolEffects.Entry#id()}
 */
public record ToggleToolEffectPayload(String id) implements CustomPacketPayload {

    public static final Type<ToggleToolEffectPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MODID, "toggle_tool_effect"));
    public static final StreamCodec<ByteBuf, ToggleToolEffectPayload> STREAM_CODEC = ByteBufCodecs.stringUtf8(256)
            .map(ToggleToolEffectPayload::new, ToggleToolEffectPayload::id);
    /** Keeps the saved list from growing without limit. */
    private static final int MAX_DISABLED = 64;

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleToolEffectPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player) || !ArmorPlusConfig.allowEffectToggles.get()) return;

            List<String> disabled = new ArrayList<>(player.getData(ModAttachments.DISABLED_TOOL_EFFECTS.get()));
            boolean turnOff = !disabled.contains(payload.id());
            // only effects of the held tool can be switched off, turning one back on always works
            Optional<ToolEffects.Entry> entry = ToolEffects.entries(player).stream()
                    .filter(e -> e.id().equals(payload.id())).findFirst();
            if (turnOff && (entry.isEmpty() || disabled.size() >= MAX_DISABLED)) return;

            if (turnOff) disabled.add(payload.id());
            else disabled.remove(payload.id());
            player.setData(ModAttachments.DISABLED_TOOL_EFFECTS.get(), List.copyOf(disabled));
            ToolEffects.invalidate(player);
            if (turnOff) removeAppliedEffect(player, entry.get().buff());
        });
    }

    /** Removes the effect if it's one ArmorPlus applied (hidden, same level), so potions and armor effects of a higher level aren't touched. */
    private static void removeAppliedEffect(ServerPlayer player, BuffInstance buff) {
        if (!buff.getBuff().isEffect()) return;
        var effect = buff.getBuff().getEffect();
        var existing = effect == null ? null : player.getEffect(effect);
        if (existing != null && !existing.isVisible() && existing.getAmplifier() == buff.getAmplifier()) {
            player.removeEffect(effect);
        }
    }
}
