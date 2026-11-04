package com.sofodev.armorplus.registry.rune;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.ArrayList;
import java.util.List;

/**
 * The runes socketed into a piece of equipment. Items without any runes don't have this component at all.
 */
public record Sockets(List<RuneData> runes) {

    public static final Codec<Sockets> CODEC = RuneData.CODEC.listOf().xmap(Sockets::new, Sockets::runes);
    public static final StreamCodec<ByteBuf, Sockets> STREAM_CODEC = RuneData.STREAM_CODEC
            .apply(ByteBufCodecs.list()).map(Sockets::new, Sockets::runes);

    public Sockets {
        runes = List.copyOf(runes);
    }

    public Sockets with(RuneData rune) {
        List<RuneData> list = new ArrayList<>(runes);
        list.add(rune);
        return new Sockets(list);
    }
}
