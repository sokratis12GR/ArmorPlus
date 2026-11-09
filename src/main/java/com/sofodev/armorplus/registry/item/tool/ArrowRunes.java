package com.sofodev.armorplus.registry.item.tool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.RangedBuff;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

/**
 * The ranged runes an arrow was fired with, kept on the arrow so switching weapons mid-flight changes nothing.
 *
 * @param runes   the runes that weren't switched off when it was fired
 * @param origin  where it was fired from, for Long Shot
 * @param bounces the Ricochet bounces it has left
 */
public record ArrowRunes(List<RuneData> runes, Vec3 origin, int bounces) {

    public static final ArrowRunes EMPTY = new ArrowRunes(List.of(), Vec3.ZERO, 0);

    public static final MapCodec<ArrowRunes> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RuneData.CODEC.listOf().fieldOf("runes").forGetter(ArrowRunes::runes),
            Vec3.CODEC.fieldOf("origin").forGetter(ArrowRunes::origin),
            Codec.INT.fieldOf("bounces").forGetter(ArrowRunes::bounces)
    ).apply(instance, ArrowRunes::new));

    /** @return the level of the ability on this arrow, 0 if it doesn't have it */
    public int level(RangedBuff buff) {
        for (RuneData rune : runes) {
            BuffInstance instance = Runes.resolve(rune);
            if (instance != null && instance.getBuff() == buff) return RangedEffects.level(instance);
        }
        return 0;
    }

    public ArrowRunes withBounces(int bounces) {
        return new ArrowRunes(runes, origin, bounces);
    }

    /** @return a copy without the runes of the ability */
    public ArrowRunes without(RangedBuff buff) {
        return filtered(rune -> {
            BuffInstance instance = Runes.resolve(rune);
            return instance == null || instance.getBuff() != buff;
        });
    }

    public ArrowRunes filtered(Predicate<RuneData> kept) {
        return new ArrowRunes(runes.stream().filter(kept).toList(), origin, bounces);
    }
}
