package com.sofodev.armorplus.network;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Sent by the Weapon Effects screen: switches one of the held weapon's on-hit effects off (for every weapon), or back on.
 *
 * @param id the effect id, see {@link WeaponEffects.Entry#id()}
 */
public record ToggleWeaponEffectPayload(String id) implements CustomPacketPayload {

    public static final Type<ToggleWeaponEffectPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MODID, "toggle_weapon_effect"));
    public static final StreamCodec<ByteBuf, ToggleWeaponEffectPayload> STREAM_CODEC = ByteBufCodecs.stringUtf8(256)
            .map(ToggleWeaponEffectPayload::new, ToggleWeaponEffectPayload::id);
    /** Keeps the saved list from growing without limit. */
    private static final int MAX_DISABLED = 64;

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleWeaponEffectPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player) || !ArmorPlusConfig.allowEffectToggles.get()) return;

            List<String> disabled = new ArrayList<>(player.getData(ModAttachments.DISABLED_WEAPON_EFFECTS.get()));
            boolean turnOff = !disabled.contains(payload.id());
            // only effects of the held weapon can be switched off, turning one back on always works
            boolean held = WeaponEffects.entries(player).stream().anyMatch(e -> e.id().equals(payload.id()));
            if (turnOff && (!held || disabled.size() >= MAX_DISABLED)) return;

            if (turnOff) disabled.add(payload.id());
            else disabled.remove(payload.id());
            // on-hit effects are applied to the target, there is nothing to remove from the player
            player.setData(ModAttachments.DISABLED_WEAPON_EFFECTS.get(), List.copyOf(disabled));
        });
    }
}
