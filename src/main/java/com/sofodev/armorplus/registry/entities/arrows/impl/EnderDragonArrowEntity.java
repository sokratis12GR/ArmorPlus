package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.ENDER_DRAGON_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.ENDER_DRAGON_ARROW_PROP;

public class EnderDragonArrowEntity extends APArrowEntity {

    public EnderDragonArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public EnderDragonArrowEntity(World world) {
        super(ENDER_DRAGON_ARROW.get(), world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(double x, double y, double z, World world) {
        super(ENDER_DRAGON_ARROW.get(), x, y, z, world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(LivingEntity shooter, World world) {
        super(ENDER_DRAGON_ARROW.get(), shooter, world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(ENDER_DRAGON_ARROW.get(), packet, world, ENDER_DRAGON_ARROW_PROP);
    }

}