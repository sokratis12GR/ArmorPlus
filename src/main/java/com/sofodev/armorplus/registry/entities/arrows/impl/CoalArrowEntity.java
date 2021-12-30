package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.COAL_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.COAL_ARROW_PROP;

public class CoalArrowEntity extends APArrowEntity {

    public CoalArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public CoalArrowEntity(Level world) {
        super(COAL_ARROW.get(), world, COAL_ARROW_PROP);
    }

    public CoalArrowEntity(double x, double y, double z, Level world) {
        super(COAL_ARROW.get(), x, y, z, world, COAL_ARROW_PROP);
    }

    public CoalArrowEntity(LivingEntity shooter, Level world) {
        super(COAL_ARROW.get(), shooter, world, COAL_ARROW_PROP);
    }

    public CoalArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(COAL_ARROW.get(), packet, world, COAL_ARROW_PROP);
    }
}