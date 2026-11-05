package com.sofodev.armorplus.registry;

import com.mojang.serialization.Codec;
import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.registry.rune.Sockets;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModDataComponents {

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

    /** The buff held by a rune item. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<RuneData>> RUNE = DATA_COMPONENTS.registerComponentType("rune",
            builder -> builder.persistent(RuneData.CODEC).networkSynchronized(RuneData.STREAM_CODEC));

    /** The number of sockets a piece of equipment rolled (see the "randomSockets" config). */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SOCKET_SLOTS = DATA_COMPONENTS.registerComponentType("socket_slots",
            builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT));

    /** The runes socketed into a piece of equipment. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Sockets>> SOCKETS = DATA_COMPONENTS.registerComponentType("sockets",
            builder -> builder.persistent(Sockets.CODEC).networkSynchronized(Sockets.STREAM_CODEC));

    /** The ids of the buffs extracted from this piece of equipment, it no longer has them. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<String>>> EXTRACTED_EFFECTS = DATA_COMPONENTS.registerComponentType("extracted_effects",
            builder -> builder.persistent(Codec.STRING.listOf()).networkSynchronized(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list())));

    /** The levels the material's buffs were upgraded to on this piece of equipment, by buff id. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> NATIVE_LEVELS = DATA_COMPONENTS.registerComponentType("native_levels",
            builder -> builder.persistent(Codec.unboundedMap(Codec.STRING, ExtraCodecs.POSITIVE_INT))
                    .networkSynchronized(ByteBufCodecs.<ByteBuf, String, Integer, Map<String, Integer>>map(HashMap::new, ByteBufCodecs.STRING_UTF8, ByteBufCodecs.VAR_INT)));
}
