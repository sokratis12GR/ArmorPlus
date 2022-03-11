package com.sofodev.armorplus.registry.entities.normal;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static net.minecraft.potion.Effects.WITHER;

public class WitherlingEntity extends MonsterEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public WitherlingEntity(EntityType<? extends WitherlingEntity> type, World worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }

    @Override
    public EntitySize getDimensions(Pose pose) {
        return new EntitySize(this.getBbWidth() * 2f, this.getBbHeight() * 1.1f, false);
    }

    @Override
    public ITextComponent getCustomName() {
        return new StringTextComponent("Witherling");
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D);
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
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_AXE));
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
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.WITHER, 200));
            }
            return true;
        }
    }

    //  @Override
    //  protected AbstractArrowEntity fireArrow(ItemStack arrowStack, float distanceFactor) {
    //      AbstractArrowEntity entityarrow = super.fireArrow(arrowStack, distanceFactor);
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