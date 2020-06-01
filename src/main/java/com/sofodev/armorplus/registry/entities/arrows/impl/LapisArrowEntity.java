package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.LAPIS_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.LAPIS_ARROW_PROP;

public class LapisArrowEntity extends APArrowEntity {

    public LapisArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public LapisArrowEntity(World world) {
        super(LAPIS_ARROW.get(), world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(double x, double y, double z, World world) {
        super(LAPIS_ARROW.get(), x, y, z, world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(LivingEntity shooter, World world) {
        super(LAPIS_ARROW.get(), shooter, world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(LAPIS_ARROW.get(), packet, world, LAPIS_ARROW_PROP);
    }

}