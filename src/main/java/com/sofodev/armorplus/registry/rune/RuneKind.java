package com.sofodev.armorplus.registry.rune;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import com.mojang.serialization.Codec;

import java.util.Locale;

/**
 * Armor runes hold worn effects/abilities ("armorBuffs"), weapon runes hold on-hit effects ("weaponBuffs"), tool runes
 * hold effects/abilities that work while the tool is held in the main hand (e.g. Haste, armorplus:auto_smelt).
 */
public enum RuneKind implements StringRepresentable {
    ARMOR,
    WEAPON,
    TOOL;

    public static final Codec<RuneKind> CODEC = StringRepresentable.fromEnum(RuneKind::values);
    public static final StreamCodec<ByteBuf, RuneKind> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(i -> values()[i], RuneKind::ordinal);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public static RuneKind byName(String name) {
        for (RuneKind kind : values()) {
            if (kind.getSerializedName().equals(name)) return kind;
        }
        return null;
    }
}
