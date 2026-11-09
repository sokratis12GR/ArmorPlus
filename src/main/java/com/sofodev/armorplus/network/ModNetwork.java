package com.sofodev.armorplus.network;

import com.sofodev.armorplus.ArmorPlus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = ArmorPlus.MODID)
public final class ModNetwork {

    private static final String PROTOCOL_VERSION = "1";

    private ModNetwork() {
    }

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar(PROTOCOL_VERSION)
                .playToServer(ToggleEffectPayload.TYPE, ToggleEffectPayload.STREAM_CODEC, ToggleEffectPayload::handle)
                .playToClient(SyncDisabledEffectsPayload.TYPE, SyncDisabledEffectsPayload.STREAM_CODEC, SyncDisabledEffectsPayload::handle);
    }
}
