package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import static com.sofodev.armorplus.registry.ModPotions.EMPTY;

public class EffectData {

    private final MobEffect effect;
    private final int duration;
    private final int amplifier;

    public EffectData(MobEffect effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public EffectData(MobEffect effect, int amplifier) {
        this(effect, 180, amplifier);
    }

    public EffectData(MobEffect effect) {
        this(effect, 180, 0);
    }

    public static EffectData create(MobEffect effect, int duration, int amplifier) {
        return new EffectData(effect, duration, amplifier);
    }

    public static EffectData create(MobEffect effect, int amplifier) {
        return new EffectData(effect, amplifier);
    }

    public static EffectData create(MobEffect effect) {
        return new EffectData(effect);
    }

    public MobEffectInstance getInstance() {
        return new MobEffectInstance(effect, duration, amplifier);
    }

    public MobEffect getEffect() {
        return effect != null ? effect : EMPTY.get();
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