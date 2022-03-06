package com.sofodev.armorplus.registry.entities.arrows;

import com.sofodev.armorplus.registry.items.extras.EffectData;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.LivingEntity;

public interface IArrow {

    double getDmg();

    SimpleParticleType getParticle();

    EffectData getData();

    default IArrow hit(LivingEntity living) {
        return this;
    }

}