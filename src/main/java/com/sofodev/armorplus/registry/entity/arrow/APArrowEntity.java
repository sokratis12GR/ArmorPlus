package com.sofodev.armorplus.registry.entity.arrow;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public abstract class APArrowEntity extends AbstractArrow {

    private EntityType<? extends APArrowEntity> type;
    private ArrowProperty prop;

    public APArrowEntity(EntityType<? extends APArrowEntity> type, Level world) {
        super(type, world);
        this.type = type;
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, Level world, ArrowProperty property) {
        super(type, world);
        this.type = type;
        this.setProp(property);
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, double x, double y, double z, Level world, ArrowProperty property) {
        super(type, x, y, z, world, property.getPickupItem(), null);
        this.type = type;
        this.setProp(property);
        this.setPos(x, y, z);
    }

    /**
     * @param ammo   the stack the arrow was made from, an Infinity or creative one can't be picked up
     * @param weapon the bow or crossbow that fired it, whose enchantments apply to the arrow
     */
    public APArrowEntity(EntityType<? extends APArrowEntity> type, LivingEntity shooter, Level world, ItemStack ammo, @Nullable ItemStack weapon, ArrowProperty property) {
        super(type, shooter, world, ammo.copyWithCount(1), weapon);
        this.type = type;
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
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProp());
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected ItemStack getPickupItem() {
        return prop == null ? getDefaultPickupItem() : prop.getPickupItem();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return prop == null ? new ItemStack(Items.ARROW) : prop.getPickupItem();
    }

    @Override
    public EntityType<?> getType() {
        return this.type == null ? EntityType.ARROW : this.type;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        if (!level().isClientSide() && this.prop != null) {
            this.prop.hit(target);
        }
    }

}