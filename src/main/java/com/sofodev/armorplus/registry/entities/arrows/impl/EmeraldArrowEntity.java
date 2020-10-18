package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.EMERALD_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.EMERALD_ARROW_PROP;

public class EmeraldArrowEntity extends APArrowEntity {

    public EmeraldArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public EmeraldArrowEntity(World world) {
        super(EMERALD_ARROW.get(), world, EMERALD_ARROW_PROP);
    }

    public EmeraldArrowEntity(double x, double y, double z, World world) {
        super(EMERALD_ARROW.get(), x, y, z, world, EMERALD_ARROW_PROP);
    }

    public EmeraldArrowEntity(LivingEntity shooter, World world) {
        super(EMERALD_ARROW.get(), shooter, world, EMERALD_ARROW_PROP);
    }

    public EmeraldArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(EMERALD_ARROW.get(), packet, world, EMERALD_ARROW_PROP);
    }
}