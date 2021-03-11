package com.sofodev.armorplus.registry.entities.bosses;

import com.google.common.collect.Lists;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal.EntityAIType;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo;
import com.sofodev.armorplus.registry.entities.bosses.manager.DragonFightManager;
import com.sofodev.armorplus.registry.entities.bosses.manager.IPhase;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseManager;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathHeap;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.IServerConfiguration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static net.minecraft.entity.ai.attributes.Attributes.ARMOR;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

/**
 * @author Sokratis Fotkatzikis
 **/
public class DemonicDragonEntity extends MonsterEntity implements IRangedAttackMob, IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    public static final DataParameter<Integer> PHASE = EntityDataManager.defineId(DemonicDragonEntity.class, DataSerializers.INT);
    private final SpecificServerBossInfo bossInfo = new SpecificServerBossInfo(this.getDisplayName(), SpecificServerBossInfo.BossInfoDungeonType.DEMONIC_DRAGON);
    public float rotationYaw = 1.0f;
    @Nullable
    private final DragonFightManager dragonFight;
    private final PhaseManager phaseManager;
    private final PathPoint[] nodes = new PathPoint[24];
    private final int[] nodeAdjacency = new int[24];
    private final PathHeap openSet = new PathHeap();

    public DemonicDragonEntity(EntityType<? extends DemonicDragonEntity> type, World world) {
        super(type, world);
        this.setPersistenceRequired();
        this.setHealth(this.getMaxHealth());
        this.noCulling = true;
        this.refreshDimensions();
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            MinecraftServer server = serverWorld.getServer();
            IServerConfiguration configuration = server.getWorldData();
            this.dragonFight = new DragonFightManager(serverWorld, configuration.worldGenSettings().seed(), configuration.endDragonFightData());
        } else {
            this.dragonFight = null;
        }

        this.phaseManager = new PhaseManager(this);
    }

    @Override
    public EntitySize getDimensions(Pose pose) {
        return EntitySize.scalable(4, 4).scale(1f, 1f);
    }

    @Override
    public void checkDespawn() {
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
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1024.0D)
                .add(MOVEMENT_SPEED, 1D)
                .add(ARMOR, 8.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(PHASE, PhaseType.HOVER.getId());
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source != DamageSource.DROWN && !(source.getEntity() instanceof WitherEntity)) {

            Entity entity = source.getDirectEntity();
            if (entity instanceof AbstractArrowEntity) {
                return false;
            }

            Entity trueSource = source.getEntity();
            if (trueSource != null && !(trueSource instanceof PlayerEntity) && trueSource instanceof LivingEntity && ((LivingEntity) trueSource).getMobType() == this.getMobType()) {
                return false;
            } else {
                return super.hurt(source, damage);
            }
        } else {
            return false;
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
        if (compound.contains("DragonPhase")) {
            this.phaseManager.setPhase(PhaseType.getById(compound.getInt("DragonPhase")));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("DragonPhase", this.phaseManager.getCurrentPhase().getPhase().getId());
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    //  @Override
    //  public void handleStatusUpdate(byte id) {
    //      super.handleStatusUpdate(id);
    //  }

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
    protected void updateControlFlags() {
        super.updateControlFlags();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#startSeenByPlayer} for
     * more information on tracking.
     */
    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        // if (!event.isMoving()) {
        //     event.getController().setAnimation((new AnimationBuilder()).addAnimation("asleep", true));
        //     return PlayState.CONTINUE;
        // }
        if (!event.isMoving()) {
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
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
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList();
    }

    public PhaseManager getPhaseManager() {
        return phaseManager;
    }

    @Nullable
    public DragonFightManager getDragonFight() {
        return dragonFight;
    }

    //=======\\
    //PATHING\\
    //=======\\

    public int findClosestNode() {
        if (this.nodes[0] == null) {
            for (int i = 0; i < 24; ++i) {
                int j = 5;
                int l;
                int i1;
                if (i < 12) {
                    l = MathHelper.floor(60.0F * MathHelper.cos(2.0F * (-(float) Math.PI + 0.2617994F * (float) i)));
                    i1 = MathHelper.floor(60.0F * MathHelper.sin(2.0F * (-(float) Math.PI + 0.2617994F * (float) i)));
                } else if (i < 20) {
                    int lvt_3_1_ = i - 12;
                    l = MathHelper.floor(40.0F * MathHelper.cos(2.0F * (-(float) Math.PI + ((float) Math.PI / 8F) * (float) lvt_3_1_)));
                    i1 = MathHelper.floor(40.0F * MathHelper.sin(2.0F * (-(float) Math.PI + ((float) Math.PI / 8F) * (float) lvt_3_1_)));
                    j += 10;
                } else {
                    int k1 = i - 20;
                    l = MathHelper.floor(20.0F * MathHelper.cos(2.0F * (-(float) Math.PI + ((float) Math.PI / 4F) * (float) k1)));
                    i1 = MathHelper.floor(20.0F * MathHelper.sin(2.0F * (-(float) Math.PI + ((float) Math.PI / 4F) * (float) k1)));
                }

                int j1 = Math.max(this.level.getSeaLevel() + 10, this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(l, 0, i1)).getY() + j);
                this.nodes[i] = new PathPoint(l, j1, i1);
            }

            this.nodeAdjacency[0] = 6146;
            this.nodeAdjacency[1] = 8197;
            this.nodeAdjacency[2] = 8202;
            this.nodeAdjacency[3] = 16404;
            this.nodeAdjacency[4] = 32808;
            this.nodeAdjacency[5] = 32848;
            this.nodeAdjacency[6] = 65696;
            this.nodeAdjacency[7] = 131392;
            this.nodeAdjacency[8] = 131712;
            this.nodeAdjacency[9] = 263424;
            this.nodeAdjacency[10] = 526848;
            this.nodeAdjacency[11] = 525313;
            this.nodeAdjacency[12] = 1581057;
            this.nodeAdjacency[13] = 3166214;
            this.nodeAdjacency[14] = 2138120;
            this.nodeAdjacency[15] = 6373424;
            this.nodeAdjacency[16] = 4358208;
            this.nodeAdjacency[17] = 12910976;
            this.nodeAdjacency[18] = 9044480;
            this.nodeAdjacency[19] = 9706496;
            this.nodeAdjacency[20] = 15216640;
            this.nodeAdjacency[21] = 13688832;
            this.nodeAdjacency[22] = 11763712;
            this.nodeAdjacency[23] = 8257536;
        }

        return this.findClosestNode(this.getX(), this.getY(), this.getZ());
    }

    public int findClosestNode(double p_184663_1_, double p_184663_3_, double p_184663_5_) {
        float f = 10000.0F;
        int i = 0;
        PathPoint pathpoint = new PathPoint(MathHelper.floor(p_184663_1_), MathHelper.floor(p_184663_3_), MathHelper.floor(p_184663_5_));
        int j = 0;
        if (this.dragonFight == null) {
            j = 12;
        }

        for (int k = j; k < 24; ++k) {
            if (this.nodes[k] != null) {
                float f1 = this.nodes[k].distanceToSqr(pathpoint);
                if (f1 < f) {
                    f = f1;
                    i = k;
                }
            }
        }

        return i;
    }

    @Nullable
    public Path findPath(int p_184666_1_, int p_184666_2_, @Nullable PathPoint p_184666_3_) {
        for (int i = 0; i < 24; ++i) {
            PathPoint pathpoint = this.nodes[i];
            pathpoint.closed = false;
            pathpoint.f = 0.0F;
            pathpoint.g = 0.0F;
            pathpoint.h = 0.0F;
            pathpoint.cameFrom = null;
            pathpoint.heapIdx = -1;
        }

        PathPoint pathpoint4 = this.nodes[p_184666_1_];
        PathPoint pathpoint5 = this.nodes[p_184666_2_];
        pathpoint4.g = 0.0F;
        pathpoint4.h = pathpoint4.distanceTo(pathpoint5);
        pathpoint4.f = pathpoint4.h;
        this.openSet.clear();
        this.openSet.insert(pathpoint4);
        PathPoint pathpoint1 = pathpoint4;
        int j = 0;
        if (this.dragonFight == null) {
            j = 12;
        }

        while (!this.openSet.isEmpty()) {
            PathPoint pathpoint2 = this.openSet.pop();
            if (pathpoint2.equals(pathpoint5)) {
                if (p_184666_3_ != null) {
                    p_184666_3_.cameFrom = pathpoint5;
                    pathpoint5 = p_184666_3_;
                }

                return this.reconstructPath(pathpoint4, pathpoint5);
            }

            if (pathpoint2.distanceTo(pathpoint5) < pathpoint1.distanceTo(pathpoint5)) {
                pathpoint1 = pathpoint2;
            }

            pathpoint2.closed = true;
            int k = 0;

            for (int l = 0; l < 24; ++l) {
                if (this.nodes[l] == pathpoint2) {
                    k = l;
                    break;
                }
            }

            for (int i1 = j; i1 < 24; ++i1) {
                if ((this.nodeAdjacency[k] & 1 << i1) > 0) {
                    PathPoint pathpoint3 = this.nodes[i1];
                    if (!pathpoint3.closed) {
                        float f = pathpoint2.g + pathpoint2.distanceTo(pathpoint3);
                        if (!pathpoint3.inOpenSet() || f < pathpoint3.g) {
                            pathpoint3.cameFrom = pathpoint2;
                            pathpoint3.g = f;
                            pathpoint3.h = pathpoint3.distanceTo(pathpoint5);
                            if (pathpoint3.inOpenSet()) {
                                this.openSet.changeCost(pathpoint3, pathpoint3.g + pathpoint3.h);
                            } else {
                                pathpoint3.f = pathpoint3.g + pathpoint3.h;
                                this.openSet.insert(pathpoint3);
                            }
                        }
                    }
                }
            }
        }

        if (pathpoint1 == pathpoint4) {
            return null;
        } else {
            LOGGER.debug("Failed to find path from {} to {}", p_184666_1_, p_184666_2_);
            if (p_184666_3_ != null) {
                p_184666_3_.cameFrom = pathpoint1;
                pathpoint1 = p_184666_3_;
            }

            return this.reconstructPath(pathpoint4, pathpoint1);
        }
    }

    private Path reconstructPath(PathPoint p_184669_1_, PathPoint p_184669_2_) {
        List<PathPoint> list = Lists.newArrayList();
        PathPoint pathpoint = p_184669_2_;
        list.add(0, p_184669_2_);

        while (pathpoint.cameFrom != null) {
            pathpoint = pathpoint.cameFrom;
            list.add(0, pathpoint);
        }

        return new Path(list, new BlockPos(p_184669_2_.x, p_184669_2_.y, p_184669_2_.z), true);
    }


    public boolean isPickable() {
        return false;
    }

    public SoundCategory getSoundSource() {
        return SoundCategory.HOSTILE;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    protected float getSoundVolume() {
        return 5.0F;
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadPartYOffset(int p_184667_1_, double[] p_184667_2_, double[] p_184667_3_) {
        IPhase iphase = this.phaseManager.getCurrentPhase();
        PhaseType<? extends IPhase> phasetype = iphase.getPhase();
        double d0;
        if (phasetype != PhaseType.LANDING && phasetype != PhaseType.TAKEOFF) {
            if (iphase.isSitting()) {
                d0 = (double) p_184667_1_;
            } else if (p_184667_1_ == 6) {
                d0 = 0.0D;
            } else {
                d0 = p_184667_3_[1] - p_184667_2_[1];
            }
        } else {
            BlockPos blockpos = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
            float f = Math.max(MathHelper.sqrt(blockpos.distSqr(this.position(), true)) / 4.0F, 1.0F);
            d0 = (double) ((float) p_184667_1_ / f);
        }

        return (float) d0;
    }

    public Vector3d getHeadLookVector(float pos) {
        IPhase iphase = this.phaseManager.getCurrentPhase();
        PhaseType<? extends IPhase> phasetype = iphase.getPhase();
        Vector3d vector3d;
        if (phasetype != PhaseType.LANDING && phasetype != PhaseType.TAKEOFF) {
            if (iphase.isSitting()) {
                float f4 = this.xRot;
                float f5 = 1.5F;
                this.xRot = -45.0F;
                vector3d = this.getViewVector(pos);
                this.xRot = f4;
            } else {
                vector3d = this.getViewVector(pos);
            }
        } else {
            BlockPos blockpos = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
            float f = Math.max(MathHelper.sqrt(blockpos.distSqr(this.position(), true)) / 4.0F, 1.0F);
            float f1 = 6.0F / f;
            float f2 = this.xRot;
            float f3 = 1.5F;
            this.xRot = -f1 * 1.5F * 5.0F;
            vector3d = this.getViewVector(pos);
            this.xRot = f2;
        }

        return vector3d;
    }

    public void onSyncedDataUpdated(DataParameter<?> phase) {
        if (PHASE.equals(phase) && this.level.isClientSide) {
            this.phaseManager.setPhase(PhaseType.getById(this.getEntityData().get(PHASE)));
        }

        super.onSyncedDataUpdated(phase);
    }

    public boolean addEffect(EffectInstance instance) {
        return false;
    }

    protected boolean canRide(Entity entity) {
        return false;
    }

    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {

    }
}