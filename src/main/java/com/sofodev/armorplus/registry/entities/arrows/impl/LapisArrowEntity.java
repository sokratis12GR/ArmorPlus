package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.LAPIS_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.LAPIS_ARROW_PROP;

public class LapisArrowEntity extends APArrowEntity {

    public LapisArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public LapisArrowEntity(Level world) {
        super(LAPIS_ARROW.get(), world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(double x, double y, double z, Level world) {
        super(LAPIS_ARROW.get(), x, y, z, world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(LivingEntity shooter, Level world) {
        super(LAPIS_ARROW.get(), shooter, world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(LAPIS_ARROW.get(), packet, world, LAPIS_ARROW_PROP);
    }

}