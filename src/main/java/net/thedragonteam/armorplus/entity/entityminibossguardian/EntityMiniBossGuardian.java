/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.entity.entityminibossguardian;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nullable;

public class EntityMiniBossGuardian extends EntityMob {
    /**
     * Random offset used in floating behaviour
     */
    private float heightOffset = 0.5F;
    /**
     * ticks until heightOffset is randomized
     */
    private int heightOffsetUpdateTime;

    public static final ResourceLocation LOOT = new ResourceLocation(ArmorPlus.MODID, "entities/mini_boss_guardian");

    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.<Byte>createKey(EntityMiniBossGuardian.class, DataSerializers.BYTE);

    public EntityMiniBossGuardian(World worldIn) {
        super(worldIn);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 10;
    }

    public static void func_189761_b(DataFixer p_189761_0_) {
        EntityLiving.func_189752_a(p_189761_0_, "MiniBossGuardian");
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(4, new EntityMiniBossGuardian.AIFireballAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(44.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ON_FIRE, Byte.valueOf((byte) 0));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_SHULKER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness(float partialTicks) {
        return 0.0F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        if (this.worldObj.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
                this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i) {
                this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    protected void updateAITasks() {
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        }

        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + (double) entitylivingbase.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isBurning() {
        return this.isCharged();
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    public boolean isCharged() {
        return (((Byte) this.dataManager.get(ON_FIRE)).byteValue() & 1) != 0;
    }

    public void setOnFire(boolean onFire) {
        byte b0 = ((Byte) this.dataManager.get(ON_FIRE)).byteValue();

        if (onFire) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }

        this.dataManager.set(ON_FIRE, Byte.valueOf(b0));
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    static class AIFireballAttack extends EntityAIBase {
        private final EntityMiniBossGuardian miniBossGuardian;
        private int attackStep;
        private int attackTime;

        public AIFireballAttack(EntityMiniBossGuardian miniBossGuardianIn) {
            this.miniBossGuardian = miniBossGuardianIn;
            this.setMutexBits(4);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.miniBossGuardian.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            this.attackStep = 0;
        }

        /**
         * Resets the task
         */
        @Override
        public void resetTask() {
            this.miniBossGuardian.setOnFire(false);
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.miniBossGuardian.getAttackTarget();
            double d0 = this.miniBossGuardian.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.miniBossGuardian.attackEntityAsMob(entitylivingbase);
                }

                this.miniBossGuardian.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            } else if (d0 < 256.0D) {
                double d1 = entitylivingbase.posX - this.miniBossGuardian.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (this.miniBossGuardian.posY + (double) (this.miniBossGuardian.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.miniBossGuardian.posZ;

                if (this.attackTime <= 0) {
                    ++this.attackStep;

                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                        this.miniBossGuardian.setOnFire(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                        this.miniBossGuardian.setOnFire(false);
                    }

                    if (this.attackStep > 1) {
                        float f = MathHelper.sqrt_float(MathHelper.sqrt_double(d0)) * 0.5F;
                        this.miniBossGuardian.worldObj.playEvent((EntityPlayer) null, 1018, new BlockPos((int) this.miniBossGuardian.posX, (int) this.miniBossGuardian.posY, (int) this.miniBossGuardian.posZ), 0);

                        for (int i = 0; i < 1; ++i) {
                            EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.miniBossGuardian.worldObj, this.miniBossGuardian, d1 + this.miniBossGuardian.getRNG().nextGaussian() * (double) f, d2, d3 + this.miniBossGuardian.getRNG().nextGaussian() * (double) f);
                            entitysmallfireball.posY = this.miniBossGuardian.posY + (double) (this.miniBossGuardian.height / 2.0F) + 0.5D;
                            this.miniBossGuardian.worldObj.spawnEntityInWorld(entitysmallfireball);
                        }
                    }
                }

                this.miniBossGuardian.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else {
                this.miniBossGuardian.getNavigator().clearPathEntity();
                this.miniBossGuardian.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }
    }

}
