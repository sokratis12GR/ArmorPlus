package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.GLOWSTONE_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.GLOWSTONE_ARROW_PROP;

public class GlowstoneArrowEntity extends APArrowEntity {

    public GlowstoneArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, GLOWSTONE_ARROW_PROP);
    }

    public GlowstoneArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(GLOWSTONE_ARROW.get(), shooter, world, ammo, weapon, GLOWSTONE_ARROW_PROP);
    }

}
