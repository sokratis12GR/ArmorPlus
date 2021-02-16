package com.sofodev.armorplus.registry.entities.bosses;

import com.sofodev.armorplus.registry.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static net.minecraft.potion.Effects.WITHER;

public class WitherlingEntity extends MonsterEntity implements IAnimatable {

    private final EntityType<? extends WitherlingEntity> type;
    private AnimationFactory factory = new AnimationFactory(this);

    public WitherlingEntity(EntityType<? extends WitherlingEntity> type, World worldIn) {
        super(type, worldIn);
        this.type = type;
        this.ignoreFrustumCheck = true;
    }

    public WitherlingEntity(World worldIn) {
        super(ModEntities.WITHERLING.get(), worldIn);
        this.type = ModEntities.WITHERLING.get();
        this.ignoreFrustumCheck = true;
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return new EntitySize(this.getWidth() * 2f, this.getHeight() * 1.1f, false);
    }

    @Override
    public ITextComponent getCustomName() {
        return new StringTextComponent("Witherling");
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public void tick() {
        if (this.isPotionActive(WITHER)) {
            this.removePotionEffect(WITHER);
        }
        super.tick();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_AXE));
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.1f;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!super.attackEntityAsMob(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 200));
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

    @Override
    public EntityType<?> getType() {
        return this.type;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}