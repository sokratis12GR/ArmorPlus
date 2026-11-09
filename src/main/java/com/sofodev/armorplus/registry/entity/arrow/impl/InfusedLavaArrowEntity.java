package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.INFUSED_LAVA_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.INFUSED_LAVA_ARROW_PROP;

public class InfusedLavaArrowEntity extends APArrowEntity {

    public InfusedLavaArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, INFUSED_LAVA_ARROW_PROP);
    }

    public InfusedLavaArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(INFUSED_LAVA_ARROW.get(), shooter, world, ammo, weapon, INFUSED_LAVA_ARROW_PROP);
    }

}
