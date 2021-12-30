package com.sofodev.armorplus.registry.entities.arrows;

import com.sofodev.armorplus.registry.ModPotions;
import com.sofodev.armorplus.registry.items.extras.EffectData;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class ArrowProperty implements IArrow {

    private final String name;
    private final SimpleParticleType particleType;
    private final double dmg;
    private final EffectData data;

    public ArrowProperty(String name, double dmg, SimpleParticleType particleType, EffectData data) {
        this.name = name;
        this.dmg = dmg;
        this.particleType = particleType;
        this.data = data;
    }

    public ArrowProperty(String name, double dmg, SimpleParticleType particleType) {
        this(name, dmg, particleType, new EffectData(ModPotions.EMPTY.get()));
    }

    public static ArrowProperty create(String name, double dmg, SimpleParticleType particle, EffectData data) {
        return new ArrowProperty(name, dmg, particle, data);
    }

    public static ArrowProperty create(String name, double dmg, SimpleParticleType particle) {
        return new ArrowProperty(name, dmg, particle, new EffectData(ModPotions.EMPTY.get()));
    }

    public String getName() {
        return name;
    }

    @Override
    public double getDmg() {
        return this.dmg;
    }

    @Override
    public SimpleParticleType getParticle() {
        return this.particleType;
    }

    @Override
    public EffectData getData() {
        return data;
    }

    @Override
    public IArrow hit(LivingEntity living) {
        EffectData effData = this.getData();
        MobEffect eff = effData.getEffect();
        if (eff != null) { // APPotions.EMPTY
            living.addEffect(new MobEffectInstance(eff, effData.getDuration(), effData.getAmplifier()));
        }
        return this;
    }

    @Override
    public String toString() {
        return "ArrowProperty{" +
                "name='" + name + '\'' +
                ", particleType=" + particleType +
                ", dmg=" + dmg +
                ", data=" + data +
                '}';
    }
}