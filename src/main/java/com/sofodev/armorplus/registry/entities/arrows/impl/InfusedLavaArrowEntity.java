package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.INFUSED_LAVA_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.INFUSED_LAVA_ARROW_PROP;

public class InfusedLavaArrowEntity extends APArrowEntity {

    public InfusedLavaArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public InfusedLavaArrowEntity(Level world) {
        super(INFUSED_LAVA_ARROW.get(), world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(double x, double y, double z, Level world) {
        super(INFUSED_LAVA_ARROW.get(), x, y, z, world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(LivingEntity shooter, Level world) {
        super(INFUSED_LAVA_ARROW.get(), shooter, world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(INFUSED_LAVA_ARROW.get(), packet, world, INFUSED_LAVA_ARROW_PROP);
    }

}