package com.sofodev.armorplus.registry.entity.arrow;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
        super(type, x, y, z, world, property.getPickupItem(), property.getPickupItem());
        this.type = type;
        this.setProp(property);
        this.setPos(x, y, z);
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, LivingEntity shooter, Level world, ArrowProperty property) {
        super(type, shooter, world, property.getPickupItem(), property.getPickupItem());
        this.type = type;
        this.setProp(property);
        this.setOwner(shooter);
        if (shooter instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
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
    public double getBaseDamage() {
        return super.getBaseDamage();
    }

    @Override
    public void setBaseDamage(double damageIn) {
        super.setBaseDamage(this.prop.getDmg());
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
        return prop.getPickupItem();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return prop.getPickupItem();
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
        if (!level().isClientSide) {
            this.prop.hit(target);
        }
    }

}