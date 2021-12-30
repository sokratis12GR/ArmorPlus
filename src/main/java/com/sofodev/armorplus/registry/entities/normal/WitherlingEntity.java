package com.sofodev.armorplus.registry.entities.normal;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static net.minecraft.world.effect.MobEffects.WITHER;

public class WitherlingEntity extends Monster implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public WitherlingEntity(EntityType<? extends WitherlingEntity> type, Level worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return new EntityDimensions(this.getBbWidth() * 2f, this.getBbHeight() * 1.1f, false);
    }

    @Override
    public Component getCustomName() {
        return new TextComponent("Witherling");
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public void tick() {
        if (this.hasEffect(WITHER)) {
            this.removeEffect(WITHER);
        }
        super.tick();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_AXE));
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.1f;
    }

    @Override
    public boolean skipAttackInteraction(Entity entityIn) {
        if (!super.skipAttackInteraction(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.WITHER, 200));
            }
            return true;
        }
    }

    //  @Override
    //  protected AbstractArrow fireArrow(ItemStack arrowStack, float distanceFactor) {
    //      AbstractArrow entityarrow = super.fireArrow(arrowStack, distanceFactor);
    //      entityarrow.setFire(100);
    //      return entityarrow;
    //  }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.skeletal_king.move", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}