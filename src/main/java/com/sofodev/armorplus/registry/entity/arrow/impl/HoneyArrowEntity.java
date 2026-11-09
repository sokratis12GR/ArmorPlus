package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.HONEY_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.HONEY_ARROW_PROP;

public class HoneyArrowEntity extends APArrowEntity {

    public HoneyArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, HONEY_ARROW_PROP);
    }

    public HoneyArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(HONEY_ARROW.get(), shooter, world, ammo, weapon, HONEY_ARROW_PROP);
    }

}
