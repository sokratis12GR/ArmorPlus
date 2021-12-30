package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.SUPER_STAR_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.SUPER_STAR_ARROW_PROP;


public class SuperStarArrowEntity extends APArrowEntity {

    public SuperStarArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public SuperStarArrowEntity(Level world) {
        super(SUPER_STAR_ARROW.get(), world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(double x, double y, double z, Level world) {
        super(SUPER_STAR_ARROW.get(), x, y, z, world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(LivingEntity shooter, Level world) {
        super(SUPER_STAR_ARROW.get(), shooter, world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(SUPER_STAR_ARROW.get(), packet, world, SUPER_STAR_ARROW_PROP);
    }
}