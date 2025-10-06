package com.sofodev.armorplus.registry.item.extra;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public record EffectData(Holder<MobEffect> effect, int duration, int amplifier) {

    public EffectData(Holder<MobEffect> effect, int amplifier) {
        this(effect, 180, amplifier);
    }

    public EffectData(Holder<MobEffect> effect) {
        this(effect, 180, 0);
    }

    public static EffectData create(Holder<MobEffect> effect, int amplifier) {
        return new EffectData(effect, amplifier);
    }

    public static EffectData create(Holder<MobEffect> effect) {
        return new EffectData(effect);
    }

    public MobEffectInstance getInstance() {
        return new MobEffectInstance(effect, duration, amplifier);
    }

    @Override
    public Holder<MobEffect> effect() {
        return effect != null ? effect : MobEffects.BLINDNESS;
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