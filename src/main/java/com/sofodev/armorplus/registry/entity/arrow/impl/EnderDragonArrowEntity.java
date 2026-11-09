package com.sofodev.armorplus.registry.entity.arrow.impl;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.ENDER_DRAGON_ARROW;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.ENDER_DRAGON_ARROW_PROP;

public class EnderDragonArrowEntity extends APArrowEntity {

    public EnderDragonArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world, ENDER_DRAGON_ARROW_PROP);
    }

    public EnderDragonArrowEntity(LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon) {
        super(ENDER_DRAGON_ARROW.get(), shooter, world, ammo, weapon, ENDER_DRAGON_ARROW_PROP);
    }

}
