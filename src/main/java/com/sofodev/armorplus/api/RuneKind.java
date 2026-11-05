package com.sofodev.armorplus.api;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import com.mojang.serialization.Codec;
import org.jspecify.annotations.Nullable;

import java.util.Locale;

/**
 * Armor runes hold worn effects/abilities ("armorBuffs"), weapon runes hold on-hit effects ("weaponBuffs"), tool runes
 * hold effects/abilities that work while the tool is held in the main hand (e.g. Haste, armorplus:auto_smelt), ranged runes
 * hold abilities and on-hit effects of the arrows a bow or crossbow fires (e.g. armorplus:piercing, Poison).
 */
public enum RuneKind implements StringRepresentable {
    ARMOR,
    WEAPON,
    TOOL,
    RANGED;

    public static final Codec<RuneKind> CODEC = StringRepresentable.fromEnum(RuneKind::values);
    public static final StreamCodec<ByteBuf, RuneKind> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(i -> values()[i], RuneKind::ordinal);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    /** {@return the kind with this serialized name, e.g. "armor", or null} */
    public static @Nullable RuneKind byName(String name) {
        for (RuneKind kind : values()) {
            if (kind.getSerializedName().equals(name)) return kind;
        }
        return null;
    }
}
