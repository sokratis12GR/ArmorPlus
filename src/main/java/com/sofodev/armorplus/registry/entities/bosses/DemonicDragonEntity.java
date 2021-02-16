package com.sofodev.armorplus.registry.entities.bosses;

import com.sofodev.armorplus.registry.ModEntities;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal.EntityAIType;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Collections;

import static net.minecraft.entity.ai.attributes.Attributes.ARMOR;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

/**
 * @author Sokratis Fotkatzikis
 **/
public class DemonicDragonEntity extends MonsterEntity implements IRangedAttackMob, IAnimatable {

    private final SpecificServerBossInfo bossInfo = new SpecificServerBossInfo(this.getDisplayName(), SpecificServerBossInfo.BossInfoDungeonType.DEMONIC_DRAGON);
    private final EntityType<? extends DemonicDragonEntity> type;
    private AnimationFactory factory = new AnimationFactory(this);

    public DemonicDragonEntity(EntityType<? extends DemonicDragonEntity> type, World world) {
        super(type, world);
        this.type = type;
        this.enablePersistence();
        this.setHealth(this.getMaxHealth());
        this.noClip = true;
        this.ignoreFrustumCheck = true;
        this.recalculateSize();
    }

    public DemonicDragonEntity(World world) {
        super(ModEntities.DEMONIC_DRAGON.get(), world);
        this.type = ModEntities.DEMONIC_DRAGON.get();
        this.enablePersistence();
        this.setHealth(this.getMaxHealth());
        this.ignoreFrustumCheck = true;
        this.recalculateSize();
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return EntitySize.flexible(7, 7).scale(1f, 1f);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 6F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new SpecificRangedAttackGoal(this, EntityAIType.GUARDIAN));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 20.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 1024.0D)
                .createMutableAttribute(MOVEMENT_SPEED, 1D)
                .createMutableAttribute(ARMOR, 8.0D);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source != DamageSource.DROWN && !(source.getTrueSource() instanceof WitherEntity)) {

            Entity entity = source.getImmediateSource();
            if (entity instanceof AbstractArrowEntity) {
                return false;
            }

            Entity trueSource = source.getTrueSource();
            if (trueSource != null && !(trueSource instanceof PlayerEntity) && trueSource instanceof LivingEntity && ((LivingEntity) trueSource).getCreatureAttribute() == this.getCreatureAttribute()) {
                return false;
            } else {
                return super.attackEntityFrom(source, damage);
            }
        } else {
            return false;
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

        super.writeAdditional(compound);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void handleStatusUpdate(byte id) {
        super.handleStatusUpdate(id);
    }

    @Override
    public ITextComponent getCustomName() {
        return new StringTextComponent("Demonic Dragon");
    }

    @Override
    public void setCustomName(ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //      if (!event.isMoving()) {
        //          return PlayState.CONTINUE;
        //      }
        //      return PlayState.STOP;
        event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    @Override
    public boolean isNonBoss() {
        return false;
    }
    //Management Stuff

    @Override
    public Iterable<ItemStack> getEquipmentAndArmor() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment() {
        return Collections.emptyList();
    }

    @Override
    public EntityType<? extends DemonicDragonEntity> getType() {
        return type;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}