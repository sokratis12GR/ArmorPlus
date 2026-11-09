package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.OBSIDIAN_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.OBSIDIAN_ARROW_PROP;

public class ObsidianArrowEntity extends APArrowEntity {

    public ObsidianArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, OBSIDIAN_ARROW_PROP);
    }

    public ObsidianArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(OBSIDIAN_ARROW.get(), shooter, world, ammo, weapon, OBSIDIAN_ARROW_PROP);
    }

}
