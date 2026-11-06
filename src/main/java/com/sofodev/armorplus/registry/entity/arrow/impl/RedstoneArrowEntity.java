package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.REDSTONE_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.REDSTONE_ARROW_PROP;

public class RedstoneArrowEntity extends APArrowEntity {

    public RedstoneArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, REDSTONE_ARROW_PROP);
    }

    public RedstoneArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(REDSTONE_ARROW.get(), shooter, world, ammo, weapon, REDSTONE_ARROW_PROP);
    }


}