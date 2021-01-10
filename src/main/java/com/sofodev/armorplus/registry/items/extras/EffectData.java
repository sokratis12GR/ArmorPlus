package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

import static com.sofodev.armorplus.registry.ModPotions.EMPTY;

public class EffectData {

    private final Effect effect;
    private final int duration;
    private final int amplifier;

    public EffectData(Effect effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public EffectData(Effect effect, int amplifier) {
        this(effect, 180, amplifier);
    }

    public EffectData(Effect effect) {
        this(effect, 180, 0);
    }

    public static EffectData create(Effect effect, int duration, int amplifier) {
        return new EffectData(effect, duration, amplifier);
    }

    public static EffectData create(Effect effect, int amplifier) {
        return new EffectData(effect, amplifier);
    }

    public static EffectData create(Effect effect) {
        return new EffectData(effect);
    }

    public EffectInstance getInstance() {
        return new EffectInstance(effect, duration, amplifier);
    }

    public Effect getEffect() {
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