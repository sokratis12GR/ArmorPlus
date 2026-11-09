package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.SUPER_STAR_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.SUPER_STAR_ARROW_PROP;

public class SuperStarArrowEntity extends APArrowEntity {

    public SuperStarArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, SUPER_STAR_ARROW_PROP);
    }

    public SuperStarArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(SUPER_STAR_ARROW.get(), shooter, world, ammo, weapon, SUPER_STAR_ARROW_PROP);
    }

}
