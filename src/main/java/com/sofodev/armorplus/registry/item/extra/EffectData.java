package com.sofodev.armorplus.registry.item.extra;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.function.Supplier;

import static com.sofodev.armorplus.registry.ModPotions.EMPTY;

public class EffectData {

    private final Supplier<MobEffect> effect;
    private final int duration;
    private final int amplifier;

    public EffectData(Supplier<MobEffect> effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public EffectData(Supplier<MobEffect> effect, int amplifier) {
        this(effect, 180, amplifier);
    }

    public EffectData(Supplier<MobEffect> effect) {
        this(effect, 180, 0);
    }

    public static EffectData create(Supplier<MobEffect> effect, int duration, int amplifier) {
        return new EffectData(effect, duration, amplifier);
    }

    public static EffectData create(Supplier<MobEffect> effect, int amplifier) {
        return new EffectData(effect, amplifier);
    }

    public static EffectData create(Holder<MobEffect> effect, int amplifier) {
        return new EffectData(effect, amplifier);
    }

    public static EffectData create(Supplier<MobEffect> effect) {
        return new EffectData(effect);
    }

    public static EffectData create(Holder<MobEffect> effect) {
        return new EffectData(effect);
    }

    public MobEffectInstance getInstance() {
        return new MobEffectInstance(Holder.direct(effect.get()), duration, amplifier);
    }

    public Supplier<MobEffect> getEffect() {
        return () -> effect != null ? effect.get() : EMPTY.get();
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public String toString() {
        return "EffectData{" +
                "effect=" + effect +
                ", duration=" + duration +
                ", amplifier=" + amplifier +
                '}';
    }
}