package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.rune.RuneData;
import com.sofodev.armorplus.registry.rune.Sockets;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

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
}
