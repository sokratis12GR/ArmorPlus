package com.sofodev.armorplus.network;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.events.ModGlobalEvents;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
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
 * Sent by the Armor Effects screen: switches one of the player's effects off for one set (or for their runes), or back on.
 *
 * @param key the toggle key, "set|effect", see {@link ArmorEffects.Entry#toggleKey()}
 */
public record ToggleEffectPayload(String key) implements CustomPacketPayload {

    public static final Type<ToggleEffectPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MODID, "toggle_effect"));
    public static final StreamCodec<ByteBuf, ToggleEffectPayload> STREAM_CODEC = ByteBufCodecs.stringUtf8(256)
            .map(ToggleEffectPayload::new, ToggleEffectPayload::key);
    /** More than any set and its runes can have, keeps the saved list from growing without limit. */
    private static final int MAX_DISABLED = 64;

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleEffectPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player) || !ArmorPlusConfig.allowEffectToggles.get()) return;

            List<String> disabled = new ArrayList<>(player.getData(ModAttachments.DISABLED_EFFECTS.get()));
            boolean turnOff = !disabled.contains(payload.key());
            // only effects the player actually has can be switched off, turning one back on always works
            Optional<ArmorEffects.Entry> entry = ArmorEffects.entries(player).stream()
                    .filter(e -> e.toggleKey().equals(payload.key())).findFirst();
            if (turnOff && (entry.isEmpty() || disabled.size() >= MAX_DISABLED)) return;

            if (turnOff) disabled.add(payload.key());
            else disabled.remove(payload.key());
            player.setData(ModAttachments.DISABLED_EFFECTS.get(), List.copyOf(disabled));
            ArmorEffects.invalidate(player);

            // remove the effect unless another source (set or runes) still gives it
            String id = entry.map(ArmorEffects.Entry::id).orElse(null);
            boolean stillActive = id != null && ArmorEffects.active(player).stream()
                    .anyMatch(buff -> BuffParser.idOf(buff.getBuff()).equals(id));
            if (turnOff && !stillActive) removeAppliedEffect(player, entry.get().buff());
            ModGlobalEvents.checkAndApplyBuffs(player);
        });
    }

    /** Removes the effect if it's one ArmorPlus applied (hidden, same level), so potions aren't touched. */
    private static void removeAppliedEffect(ServerPlayer player, BuffInstance buff) {
        if (!buff.getBuff().isEffect()) return;
        var effect = buff.getBuff().getEffect();
        var existing = effect == null ? null : player.getEffect(effect);
        if (existing != null && !existing.isVisible() && existing.getAmplifier() == buff.getAmplifier()) {
            player.removeEffect(effect);
        }
    }
}
