package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.packets.SpawnEntity;

import static com.sofodev.armorplus.registry.ModEntities.REDSTONE_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.REDSTONE_ARROW_PROP;

public class RedstoneArrowEntity extends APArrowEntity {

    public RedstoneArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public RedstoneArrowEntity(Level world) {
        super(REDSTONE_ARROW.get(), world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(double x, double y, double z, Level world) {
        super(REDSTONE_ARROW.get(), x, y, z, world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(LivingEntity shooter, Level world) {
        super(REDSTONE_ARROW.get(), shooter, world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(SpawnEntity packet, Level world) {
        super(REDSTONE_ARROW.get(), packet, world, REDSTONE_ARROW_PROP);
    }

}