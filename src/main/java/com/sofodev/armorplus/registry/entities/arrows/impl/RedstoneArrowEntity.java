package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.REDSTONE_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.REDSTONE_ARROW_PROP;

public class RedstoneArrowEntity extends APArrowEntity {

    public RedstoneArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public RedstoneArrowEntity(World world) {
        super(REDSTONE_ARROW.get(), world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(double x, double y, double z, World world) {
        super(REDSTONE_ARROW.get(), x, y, z, world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(LivingEntity shooter, World world) {
        super(REDSTONE_ARROW.get(), shooter, world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(REDSTONE_ARROW.get(), packet, world, REDSTONE_ARROW_PROP);
    }

}