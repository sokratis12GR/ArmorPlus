package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import static com.sofodev.armorplus.registry.ModEntities.WARDEN_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.WARDEN_ARROW_PROP;

public class WardenArrowEntity extends APArrowEntity {

    public WardenArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, WARDEN_ARROW_PROP);
    }

    public WardenArrowEntity(Level world) {
        super(WARDEN_ARROW.get(), world, WARDEN_ARROW_PROP);
    }

    public WardenArrowEntity(double x, double y, double z, Level world) {
        super(WARDEN_ARROW.get(), x, y, z, world, WARDEN_ARROW_PROP);
    }

    public WardenArrowEntity(LivingEntity shooter, Level world) {
        super(WARDEN_ARROW.get(), shooter, world, WARDEN_ARROW_PROP);
    }


}
