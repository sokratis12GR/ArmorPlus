package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import static com.sofodev.armorplus.registry.ModEntities.HONEY_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.HONEY_ARROW_PROP;

public class HoneyArrowEntity extends APArrowEntity {

    public HoneyArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, HONEY_ARROW_PROP);
    }

    public HoneyArrowEntity(Level world) {
        super(HONEY_ARROW.get(), world, HONEY_ARROW_PROP);
    }

    public HoneyArrowEntity(double x, double y, double z, Level world) {
        super(HONEY_ARROW.get(), x, y, z, world, HONEY_ARROW_PROP);
    }

    public HoneyArrowEntity(LivingEntity shooter, Level world) {
        super(HONEY_ARROW.get(), shooter, world, HONEY_ARROW_PROP);
    }

}
