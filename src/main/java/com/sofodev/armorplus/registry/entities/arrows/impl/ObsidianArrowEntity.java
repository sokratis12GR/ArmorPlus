package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.OBSIDIAN_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.OBSIDIAN_ARROW_PROP;


public class ObsidianArrowEntity extends APArrowEntity {

    public ObsidianArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
    }

    public ObsidianArrowEntity(World world) {
        super(OBSIDIAN_ARROW.get(), world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(double x, double y, double z, World world) {
        super(OBSIDIAN_ARROW.get(), x, y, z, world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(LivingEntity shooter, World world) {
        super(OBSIDIAN_ARROW.get(), shooter, world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(OBSIDIAN_ARROW.get(), packet, world, OBSIDIAN_ARROW_PROP);
    }

}