package com.sofodev.armorplus.registry.entity.arrow;

import com.sofodev.armorplus.registry.item.extra.EffectData;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;

public interface IArrow {

    double getDmg();

    ParticleOptions getParticle();

    EffectData getData();

    default IArrow hit(LivingEntity living) {
        return this;
    }

}