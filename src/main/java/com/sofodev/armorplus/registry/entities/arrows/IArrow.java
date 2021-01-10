package com.sofodev.armorplus.registry.entities.arrows;

import com.sofodev.armorplus.registry.items.extras.EffectData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;

public interface IArrow {

    double getDmg();

    IParticleData getParticle();

    EffectData getData();

    default IArrow hit(LivingEntity living) {
        return this;
    }

}