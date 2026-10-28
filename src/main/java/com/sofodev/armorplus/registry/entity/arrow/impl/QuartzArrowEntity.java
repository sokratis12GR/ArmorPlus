package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import static com.sofodev.armorplus.registry.ModEntities.QUARTZ_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.QUARTZ_ARROW_PROP;

public class QuartzArrowEntity extends APArrowEntity {

    public QuartzArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, QUARTZ_ARROW_PROP);
    }

    public QuartzArrowEntity(Level world) {
        super(QUARTZ_ARROW.get(), world, QUARTZ_ARROW_PROP);
    }

    public QuartzArrowEntity(double x, double y, double z, Level world) {
        super(QUARTZ_ARROW.get(), x, y, z, world, QUARTZ_ARROW_PROP);
    }

    public QuartzArrowEntity(LivingEntity shooter, Level world) {
        super(QUARTZ_ARROW.get(), shooter, world, QUARTZ_ARROW_PROP);
    }

}
