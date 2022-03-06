package com.sofodev.armorplus.registry.entities.arrows.impl;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

import static com.sofodev.armorplus.registry.ModEntities.OBSIDIAN_ARROW;
import static com.sofodev.armorplus.registry.entities.arrows.APArrowProperty.OBSIDIAN_ARROW_PROP;


public class ObsidianArrowEntity extends APArrowEntity {

    public ObsidianArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
    }

    public ObsidianArrowEntity(Level world) {
        super(OBSIDIAN_ARROW.get(), world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(double x, double y, double z, Level world) {
        super(OBSIDIAN_ARROW.get(), x, y, z, world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(LivingEntity shooter, Level world) {
        super(OBSIDIAN_ARROW.get(), shooter, world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(OBSIDIAN_ARROW.get(), packet, world, OBSIDIAN_ARROW_PROP);
    }

}