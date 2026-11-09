package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.LAPIS_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.LAPIS_ARROW_PROP;

public class LapisArrowEntity extends APArrowEntity {

    public LapisArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, LAPIS_ARROW_PROP);
    }

    public LapisArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(LAPIS_ARROW.get(), shooter, world, ammo, weapon, LAPIS_ARROW_PROP);
    }

}
