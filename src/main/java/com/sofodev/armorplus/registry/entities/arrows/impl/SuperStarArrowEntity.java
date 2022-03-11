package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.SUPER_STAR_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.SUPER_STAR_ARROW_PROP;


public class SuperStarArrowEntity extends APArrowEntity {

    public SuperStarArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public SuperStarArrowEntity(World world) {
        super(SUPER_STAR_ARROW.get(), world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(double x, double y, double z, World world) {
        super(SUPER_STAR_ARROW.get(), x, y, z, world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(LivingEntity shooter, World world) {
        super(SUPER_STAR_ARROW.get(), shooter, world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(SUPER_STAR_ARROW.get(), packet, world, SUPER_STAR_ARROW_PROP);
    }

}