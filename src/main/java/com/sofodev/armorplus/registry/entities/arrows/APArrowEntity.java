package com.sofodev.armorplus.registry.entities.arrows;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.world.item.Items.ARROW;

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
        super(type, x, y, z, world);
        this.type = type;
        this.setProp(property);
        this.setPos(x, y, z);
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, LivingEntity shooter, Level world, ArrowProperty property) {
        super(type, shooter, world);
        this.type = type;
        this.setProp(property);
        this.setOwner(shooter);
        if (shooter instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, PlayMessages.SpawnEntity packet, Level world, ArrowProperty property) {
        this(type, packet.getPosX(), packet.getPosY(), packet.getPosZ(), world, property);
        this.type = type;
        this.setRot(packet.getHeadYaw(), packet.getPitch());
        this.setUUID(packet.getUuid());
        this.setId(packet.getEntityId());
        this.setDeltaMovement(packet.getVelX(), packet.getVelY(), packet.getVelZ());
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
        ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(setRL(prop.getName() + "_arrow")));
        return stack.isEmpty() ? new ItemStack(ARROW) : stack;
    }

    @Override
    public EntityType<?> getType() {
        return this.type == null ? EntityType.ARROW : this.type;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        if (!level.isClientSide) {
            this.prop.hit(target);
        }
    }

}