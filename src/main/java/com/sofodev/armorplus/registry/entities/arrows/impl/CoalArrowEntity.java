package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.COAL_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.COAL_ARROW_PROP;

public class CoalArrowEntity extends APArrowEntity {

    public CoalArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public CoalArrowEntity(World world) {
        super(COAL_ARROW.get(), world, COAL_ARROW_PROP);
    }

    public CoalArrowEntity(double x, double y, double z, World world) {
        super(COAL_ARROW.get(), x, y, z, world, COAL_ARROW_PROP);
    }

    public CoalArrowEntity(LivingEntity shooter, World world) {
        super(COAL_ARROW.get(), shooter, world, COAL_ARROW_PROP);
    }

    public CoalArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(COAL_ARROW.get(), packet, world, COAL_ARROW_PROP);
    }
}