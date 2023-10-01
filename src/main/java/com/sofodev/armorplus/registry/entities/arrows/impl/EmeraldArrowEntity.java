package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.packets.SpawnEntity;

import static com.sofodev.armorplus.registry.ModEntities.EMERALD_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.EMERALD_ARROW_PROP;

public class EmeraldArrowEntity extends APArrowEntity {

    public EmeraldArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public EmeraldArrowEntity(Level world) {
        super(EMERALD_ARROW.get(), world, EMERALD_ARROW_PROP);
    }

    public EmeraldArrowEntity(double x, double y, double z, Level world) {
        super(EMERALD_ARROW.get(), x, y, z, world, EMERALD_ARROW_PROP);
    }

    public EmeraldArrowEntity(LivingEntity shooter, Level world) {
        super(EMERALD_ARROW.get(), shooter, world, EMERALD_ARROW_PROP);
    }

    public EmeraldArrowEntity(SpawnEntity packet, Level world) {
        super(EMERALD_ARROW.get(), packet, world, EMERALD_ARROW_PROP);
    }
}