package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import static com.sofodev.armorplus.registry.ModEntities.AMETHYST_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.AMETHYST_ARROW_PROP;

public class AmethystArrowEntity extends APArrowEntity {

    public AmethystArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, AMETHYST_ARROW_PROP);
    }

    public AmethystArrowEntity(Level world) {
        super(AMETHYST_ARROW.get(), world, AMETHYST_ARROW_PROP);
    }

    public AmethystArrowEntity(double x, double y, double z, Level world) {
        super(AMETHYST_ARROW.get(), x, y, z, world, AMETHYST_ARROW_PROP);
    }

    public AmethystArrowEntity(LivingEntity shooter, Level world) {
        super(AMETHYST_ARROW.get(), shooter, world, AMETHYST_ARROW_PROP);
    }

}
