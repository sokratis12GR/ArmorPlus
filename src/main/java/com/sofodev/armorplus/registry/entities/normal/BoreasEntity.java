//package com.sofodev.armorplus.registry.entities.normal;
//
//import net.minecraft.entity.Pose;
//import net.minecraft.entity.ai.goal.*;
//import net.minecraft.entity.passive.IronGolemEntity;
//import net.minecraft.entity.passive.TurtleEntity;
//import net.minecraft.entity.passive.WolfEntity;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.*;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import software.bernie.geckolib3.core.IAnimatable;
//import software.bernie.geckolib3.core.PlayState;
//import software.bernie.geckolib3.core.builder.AnimationBuilder;
//import software.bernie.geckolib3.core.controller.AnimationController;
//import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
//import software.bernie.geckolib3.core.manager.AnimationData;
//import software.bernie.geckolib3.core.manager.AnimationFactory;
//
//import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;
//
//public class BoreasEntity extends Monster implements IAnimatable {
//
//    private AnimationFactory factory = new AnimationFactory(this);
//
//    public BoreasEntity(EntityType<? extends BoreasEntity> type, Level worldIn) {
//        super(type, worldIn);
//    }
//
//    public static AttributeSupplier.Builder registerAttributes() {
//        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 10.0D).add(MAX_HEALTH, 60D);
//    }
//
//    @Override
//    public void registerGoals() {
//        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
//        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
//        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
//        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
//        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.PIGLIN_BRUTE_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundEvents.PIGLIN_BRUTE_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.PIGLIN_BRUTE_DEATH;
//    }
//
//    @Override
//    public float getEyeHeight(Pose pose) {
//        return 2.1f;
//    }
//
//    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        if (event.isMoving()) {
//            event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", true));
//            return PlayState.CONTINUE;
//        }
//        return PlayState.STOP;
//    }
//
//    @Override
//    public void registerControllers(AnimationData data) {
//        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
//    }
//
//    @Override
//    public AnimationFactory getFactory() {
//        return this.factory;
//    }
//
//}