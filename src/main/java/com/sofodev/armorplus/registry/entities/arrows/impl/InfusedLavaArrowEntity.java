package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.INFUSED_LAVA_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.INFUSED_LAVA_ARROW_PROP;

public class InfusedLavaArrowEntity extends APArrowEntity {

    public InfusedLavaArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public InfusedLavaArrowEntity(World world) {
        super(INFUSED_LAVA_ARROW.get(), world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(double x, double y, double z, World world) {
        super(INFUSED_LAVA_ARROW.get(), x, y, z, world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(LivingEntity shooter, World world) {
        super(INFUSED_LAVA_ARROW.get(), shooter, world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(INFUSED_LAVA_ARROW.get(), packet, world, INFUSED_LAVA_ARROW_PROP);
    }

}