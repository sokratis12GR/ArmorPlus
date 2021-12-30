package com.sofodev.armorplus.registry.entities.bosses;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal.EntityAIType;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Collections;

import static net.minecraft.ChatFormatting.BOLD;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.world.entity.EntityType.BLAZE;
import static net.minecraft.world.entity.ai.attributes.Attributes.ARMOR;
import static net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SkeletalKingEntity extends Monster implements RangedAttackMob, IAnimatable {

    public static final Predicate<LivingEntity> ANY_ENTITY = entity ->
            entity != null && entity.attackable() && !(entity instanceof SkeletalKingEntity);
    private final SpecificServerBossInfo bossInfo = new SpecificServerBossInfo(this.getDisplayName(), SpecificServerBossInfo.BossInfoDungeonType.SKELETAL_KING);
    private AnimationFactory factory = new AnimationFactory(this);

    public SkeletalKingEntity(EntityType<? extends SkeletalKingEntity> type, Level world) {
        super(type, world);
        this.setPersistenceRequired();
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.noCulling = true;
        this.refreshDimensions();
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1024.0D)
                .add(MOVEMENT_SPEED, 0.6000000238418579D)
                .add(ARMOR, 8.0D);
    }

    @Override
    public void refreshDimensions() {
        super.refreshDimensions();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return new EntityDimensions(2.4f, 8, false).scale(1f, 1f);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 7F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new SpecificRangedAttackGoal(this, EntityAIType.WITHER));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 20.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, true, ANY_ENTITY));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }

    }

    //  @Override
    //  public void handleStatusUpdate(byte id) {
    //      super.handleStatusUpdate(id);
    //  }

    @Override
    public Component getCustomName() {
        return new TextComponent("Skeletal King");
    }

    @Override
    public void setCustomName(Component name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    protected void updateControlFlags() {
        super.updateControlFlags();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#startSeenByPlayer} for
     * more information on tracking.
     */
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean trueSources = source.getEntity() instanceof SkeletalKingEntity || source.getEntity() instanceof WitherBoss;
        if (source != DamageSource.DROWN && !(trueSources)) {
            Entity sourceEntity = source.getDirectEntity();
            if (!(sourceEntity instanceof Player) && sourceEntity instanceof LivingEntity && ((LivingEntity) sourceEntity).getMobType() == this.getMobType()) {
                return false;
            } else {
                return super.hurt(source, amount);
            }
        } else {
            return false;
        }
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if (!level.isClientSide) {
            String[] names = new String[]{"D", "E", "A", "T", "H"};
            for (String name : names) {
                Blaze split = new Blaze(BLAZE, level);
                split.setPos(getX(), getY(), getZ());
                split.finalizeSpawn((ServerLevelAccessor) level, this.level.getCurrentDifficultyAt(new BlockPos(this.blockPosition())), MobSpawnType.NATURAL, null, null);
                this.level.addFreshEntity(split);
                split.setCustomName(new TextComponent(GOLD + "" + BOLD + name));
                split.setCustomNameVisible(true);
                split.setInvisible(false);
                split.setInvulnerable(false);
                split.setCanPickUpLoot(true);
                //split.forceSpawn = true;
            }

            this.spawnAtLocation(new ItemStack(Items.NETHER_STAR, 2 + random.nextInt(1)), 5f);
            this.spawnAtLocation(new ItemStack(ModItems.WITHER_BONE.get(), 1 + random.nextInt(1)), 5f);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.skeletal_king.move", true));
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
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float damage) {

    }
}