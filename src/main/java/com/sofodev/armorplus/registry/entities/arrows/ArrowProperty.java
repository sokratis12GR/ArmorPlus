package com.sofodev.armorplus.registry.entities.arrows;

import com.sofodev.armorplus.registry.ModPotions;
import com.sofodev.armorplus.registry.items.extras.EffectData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

import static net.minecraft.item.Items.ARROW;

public class ArrowProperty implements IArrow {

    private final String name;
    private final IParticleData particleType;
    private final double dmg;
    private final EffectData data;

    public ArrowProperty(String name, double dmg, IParticleData particleType, EffectData data) {
        this.name = name;
        this.dmg = dmg;
        this.particleType = particleType;
        this.data = data;
    }

    public ArrowProperty(String name, double dmg, IParticleData particleType) {
        this(name, dmg, particleType, new EffectData(ModPotions.EMPTY.get()));
    }

    public static ArrowProperty create(String name, double dmg, IParticleData particle, EffectData data) {
        return new ArrowProperty(name, dmg, particle, data);
    }

    public static ArrowProperty create(String name, double dmg, IParticleData particle) {
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
    public IParticleData getParticle() {
        return this.particleType;
    }

    @Override
    public EffectData getData() {
        return data;
    }

    @Override
    public IArrow hit(LivingEntity living, Entity shootingEntity) {
        EffectData effData = this.getData();
        Effect eff = effData.getEffect();
        if (eff != null) { // APPotions.EMPTY
            living.addPotionEffect(new EffectInstance(eff, effData.getDuration(), effData.getAmplifier()));
        }
        return this;
    }
}