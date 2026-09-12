package com.sofodev.armorplus.registry.entity.arrow;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class APArrowEntity extends AbstractArrow {

    private ArrowProperty prop;

    public APArrowEntity(
            EntityType<? extends APArrowEntity> type,
            Level level
    ) {
        super(type, level);
    }

    public APArrowEntity(
            EntityType<? extends APArrowEntity> type,
            Level level,
            ArrowProperty property
    ) {
        super(type, level);
        this.prop = property;
        this.setBaseDamage(property.getDmg());
    }

    public APArrowEntity(
            EntityType<? extends APArrowEntity> type,
            double x,
            double y,
            double z,
            Level level,
            ArrowProperty property
    ) {
        super(
                type,
                x,
                y,
                z,
                level,
                property.getPickupItem(),
                null
        );

        this.prop = property;
        this.setBaseDamage(property.getDmg());
    }

    public APArrowEntity(
            EntityType<? extends APArrowEntity> type,
            LivingEntity shooter,
            Level level,
            ArrowProperty property
    ) {
        super(
                type,
                shooter,
                level,
                property.getPickupItem(),
                null
        );

        this.prop = property;
        this.setBaseDamage(property.getDmg());

        if (shooter instanceof Player) {
            this.pickup = Pickup.ALLOWED;
        }
    }

    public ArrowProperty getProp() {
        return prop;
    }

    public APArrowEntity setProp(ArrowProperty prop) {
        this.prop = prop;
        return this;
    }

    @Override
    protected ItemStack getPickupItem() {
        return prop.getPickupItem();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return prop != null
                ? prop.getPickupItem()
                : ItemStack.EMPTY;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);

        if (!level().isClientSide && prop != null) {
            prop.hit(target);
        }
    }
}