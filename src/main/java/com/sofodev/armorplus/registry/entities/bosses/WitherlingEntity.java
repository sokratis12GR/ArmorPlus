package com.sofodev.armorplus.registry.entities.bosses;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.registry.ModEntities.WITHERLING;
import static net.minecraft.potion.Effects.WITHER;

public class WitherlingEntity extends AbstractSkeletonEntity {

    private final EntityType<? extends AbstractSkeletonEntity> type;

    public WitherlingEntity(EntityType<? extends AbstractSkeletonEntity> type, World worldIn) {
        super(type, worldIn);
        this.type = type;
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return new EntitySize(this.getWidth() * 2f, this.getHeight() * 1.1f, false);
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
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_WITHER_SKELETON_STEP;
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
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        ILivingEntityData ientitylivingdata = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.setCombatTask();
        return ientitylivingdata;
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

    @Override
    protected AbstractArrowEntity fireArrow(ItemStack arrowStack, float distanceFactor) {
        AbstractArrowEntity entityarrow = super.fireArrow(arrowStack, distanceFactor);
        entityarrow.setFire(100);
        return entityarrow;
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