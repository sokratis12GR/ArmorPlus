package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.ENDER_DRAGON_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.ENDER_DRAGON_ARROW_PROP;

public class EnderDragonArrowEntity extends APArrowEntity {

    public EnderDragonArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public EnderDragonArrowEntity(Level world) {
        super(ENDER_DRAGON_ARROW.get(), world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(double x, double y, double z, Level world) {
        super(ENDER_DRAGON_ARROW.get(), x, y, z, world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(LivingEntity shooter, Level world) {
        super(ENDER_DRAGON_ARROW.get(), shooter, world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ENDER_DRAGON_ARROW.get(), packet, world, ENDER_DRAGON_ARROW_PROP);
    }

}