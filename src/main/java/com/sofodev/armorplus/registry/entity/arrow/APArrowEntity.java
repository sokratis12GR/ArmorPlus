package com.sofodev.armorplus.registry.entity.arrow;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class APArrowEntity extends AbstractArrow {

    private ArrowProperty prop;

    public APArrowEntity(EntityType<? extends APArrowEntity> type, Level level) {
        super(type, level);
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, Level level, ArrowProperty property) {
        super(type, level);
        this.setProp(property);
    }

    /**
     * @param ammo   the stack the arrow was made from, an Infinity or creative one can't be picked up
     * @param weapon the bow or crossbow that fired it, whose enchantments apply to the arrow
     */
    public APArrowEntity(EntityType<? extends APArrowEntity> type, LivingEntity shooter, Level level, ItemStack ammo, @Nullable ItemStack weapon, ArrowProperty property) {
        super(type, shooter, level, ammo.copyWithCount(1), weapon);
        this.setProp(property);
    }

    public ArrowProperty getProp() {
        return prop;
    }

    public APArrowEntity setProp(ArrowProperty prop) {
        this.prop = prop;
        if (prop != null) this.setBaseDamage(prop.getDmg());
        return this;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return prop != null ? prop.getPickupItem() : ItemStack.EMPTY;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);

        if (!level().isClientSide && prop != null) {
            prop.hit(target);
        }
    }
}
