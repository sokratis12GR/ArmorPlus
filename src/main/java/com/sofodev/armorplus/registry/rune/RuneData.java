package com.sofodev.armorplus.registry.rune;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * A single rune: its kind and the buff it holds, stored as a config style entry ("id|level", see {@link BuffParser}).
 * <p>
 * The entry is kept as text and resolved when used, so runes survive config changes and removed mods; runes that can't be
 * resolved anymore are simply inactive (see {@link Runes#resolve(RuneData)}).
 */
public record RuneData(RuneKind kind, String entry) {

    public static final Codec<RuneData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RuneKind.CODEC.fieldOf("kind").forGetter(RuneData::kind),
            Codec.STRING.fieldOf("buff").forGetter(RuneData::entry)
    ).apply(instance, RuneData::new));

    public static final StreamCodec<ByteBuf, RuneData> STREAM_CODEC = StreamCodec.composite(
            RuneKind.STREAM_CODEC, RuneData::kind,
            ByteBufCodecs.STRING_UTF8, RuneData::entry,
            RuneData::new);

    /** @return the buff id, e.g. "minecraft:haste" for "minecraft:haste|2" */
    public String buffId() {
        return BuffParser.idOf(entry);
    }
}
