package com.sofodev.armorplus.registry.entity.arrow;

import com.sofodev.armorplus.registry.item.extra.EffectData;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;

import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.world.item.Items.ARROW;

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
        this(name, dmg, particleType, new EffectData(MobEffects.BLINDNESS));
    }

    public static ArrowProperty create(String name, double dmg, SimpleParticleType particle, EffectData data) {
        return new ArrowProperty(name, dmg, particle, data);
    }

    public static ArrowProperty create(String name, double dmg, SimpleParticleType particle) {
        return new ArrowProperty(name, dmg, particle, new EffectData(MobEffects.BLINDNESS));
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
        Holder<MobEffect> eff = effData.effect();
        if (eff != null) {
            living.addEffect(new MobEffectInstance(eff, effData.duration(), effData.amplifier()));
        }
        return this;
    }

    public ItemStack getPickupItem() {
        ItemStack stack = new ItemStack(BuiltInRegistries.ITEM.get(setRL(this.getName() + "_arrow")));
        return stack.isEmpty() ? new ItemStack(ARROW) : stack;
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