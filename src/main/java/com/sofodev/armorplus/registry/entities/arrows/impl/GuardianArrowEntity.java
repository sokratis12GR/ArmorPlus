package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.GUARDIAN_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.GUARDIAN_ARROW_PROP;

public class GuardianArrowEntity extends APArrowEntity {

    public GuardianArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public GuardianArrowEntity(World world) {
        super(GUARDIAN_ARROW.get(), world, GUARDIAN_ARROW_PROP);
    }

    public GuardianArrowEntity(double x, double y, double z, World world) {
        super(GUARDIAN_ARROW.get(), x, y, z, world, GUARDIAN_ARROW_PROP);
    }

    public GuardianArrowEntity(LivingEntity shooter, World world) {
        super(GUARDIAN_ARROW.get(), shooter, world, GUARDIAN_ARROW_PROP);
    }

    public GuardianArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(GUARDIAN_ARROW.get(), packet, world, GUARDIAN_ARROW_PROP);
    }

}