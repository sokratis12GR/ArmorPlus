package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.ENDER_DRAGON_ARROW;
import static com.sofodev.armorplus.registry.ModEntities.GUARDIAN_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.ENDER_DRAGON_ARROW_PROP;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.GUARDIAN_ARROW_PROP;

public class GuardianArrowEntity extends APArrowEntity {

    public GuardianArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public GuardianArrowEntity(Level world) {
        super(GUARDIAN_ARROW.get(), world, GUARDIAN_ARROW_PROP);
    }

    public GuardianArrowEntity(double x, double y, double z, Level world) {
        super(GUARDIAN_ARROW.get(), x, y, z, world, GUARDIAN_ARROW_PROP);
    }

    public GuardianArrowEntity(LivingEntity shooter, Level world) {
        super(GUARDIAN_ARROW.get(), shooter, world, GUARDIAN_ARROW_PROP);
    }

    public GuardianArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(GUARDIAN_ARROW.get(), packet, world, GUARDIAN_ARROW_PROP);
    }
}