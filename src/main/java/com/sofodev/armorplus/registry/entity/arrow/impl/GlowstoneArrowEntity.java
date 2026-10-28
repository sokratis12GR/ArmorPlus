package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import static com.sofodev.armorplus.registry.ModEntities.GLOWSTONE_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.GLOWSTONE_ARROW_PROP;

public class GlowstoneArrowEntity extends APArrowEntity {

    public GlowstoneArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, GLOWSTONE_ARROW_PROP);
    }

    public GlowstoneArrowEntity(Level world) {
        super(GLOWSTONE_ARROW.get(), world, GLOWSTONE_ARROW_PROP);
    }

    public GlowstoneArrowEntity(double x, double y, double z, Level world) {
        super(GLOWSTONE_ARROW.get(), x, y, z, world, GLOWSTONE_ARROW_PROP);
    }

    public GlowstoneArrowEntity(LivingEntity shooter, Level world) {
        super(GLOWSTONE_ARROW.get(), shooter, world, GLOWSTONE_ARROW_PROP);
    }

}
