package com.sofodev.armorplus.network;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.events.ModGlobalEvents;
import com.sofodev.armorplus.events.RuneEvents;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Sent by the effect screens: switches one of the player's effects off, or back on.
 *
 * @param scope the screen it's from, each one saves its own toggles
 * @param key   the armor toggle key, "set|effect", see {@link ArmorEffects.Entry#toggleKey()}, or the effect id of a
 *              weapon effect, see {@link WeaponEffects.Entry#id()} (those stay off for every item of that kind)
 */
public record ToggleEffectPayload(Scope scope, String key) implements CustomPacketPayload {

    public enum Scope {
        ARMOR, TOOL, WEAPON, RANGED;

        static final StreamCodec<ByteBuf, Scope> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(i -> values()[Math.floorMod(i, values().length)], Scope::ordinal);

        AttachmentType<List<String>> attachment() {
            return switch (this) {
                case ARMOR -> ModAttachments.DISABLED_EFFECTS.get();
                case TOOL -> ModAttachments.DISABLED_TOOL_EFFECTS.get();
                case WEAPON -> ModAttachments.DISABLED_WEAPON_EFFECTS.get();
                case RANGED -> ModAttachments.DISABLED_RANGED_EFFECTS.get();
            };
        }

        /** @return the effect with the key, if the player has it right now */
        private Optional<BuffInstance> find(ServerPlayer player, String key) {
            return switch (this) {
                case ARMOR -> ArmorEffects.entries(player).stream().filter(e -> e.toggleKey().equals(key)).map(ArmorEffects.Entry::buff).findFirst();
                case TOOL -> ToolEffects.entries(player).stream().filter(e -> e.id().equals(key)).map(ToolEffects.Entry::buff).findFirst();
                case WEAPON -> WeaponEffects.entries(player).stream().filter(e -> e.id().equals(key)).map(WeaponEffects.Entry::buff).findFirst();
                case RANGED -> RangedEffects.entries(player).stream().filter(e -> e.id().equals(key)).map(RangedEffects.Entry::buff).findFirst();
            };
        }
    }

    public static final Type<ToggleEffectPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "toggle_effect"));
    public static final StreamCodec<ByteBuf, ToggleEffectPayload> STREAM_CODEC = StreamCodec.composite(
            Scope.STREAM_CODEC, ToggleEffectPayload::scope,
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

            Scope scope = payload.scope();
            List<String> disabled = new ArrayList<>(player.getData(scope.attachment()));
            boolean turnOff = !disabled.contains(payload.key());
            // only effects the player actually has can be switched off, turning one back on always works
            Optional<BuffInstance> buff = scope.find(player, payload.key());
            if (turnOff && buff.isEmpty()) return;
            if (turnOff && disabled.size() >= MAX_DISABLED) {
                player.displayClientMessage(Component.translatable("armorplus.effects.toggle_limit", MAX_DISABLED).withStyle(ChatFormatting.RED), true);
                return;
            }

            if (turnOff) disabled.add(payload.key());
            else disabled.remove(payload.key());
            player.setData(scope.attachment(), List.copyOf(disabled));
            SyncDisabledEffectsPayload.sync(player, scope);

            // on-hit effects are applied to the target, there is nothing to change on the player
            switch (scope) {
                case ARMOR -> {
                    ArmorEffects.invalidate(player);
                    RuneEvents.syncEffects(player, turnOff ? List.of(buff.get()) : List.of());
                    ModGlobalEvents.checkAndApplyBuffs(player);
                }
                case TOOL -> {
                    ToolEffects.invalidate(player);
                    RuneEvents.syncEffects(player, turnOff ? List.of(buff.get()) : List.of());
                }
                case WEAPON, RANGED -> {
                }
            }
        });
    }
}
