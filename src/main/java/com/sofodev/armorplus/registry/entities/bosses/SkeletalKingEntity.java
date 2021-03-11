package com.sofodev.armorplus.registry.entities.bosses;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal.EntityAIType;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Collections;

import static net.minecraft.entity.EntityType.BLAZE;
import static net.minecraft.entity.ai.attributes.Attributes.ARMOR;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;
import static net.minecraft.util.text.TextFormatting.BOLD;
import static net.minecraft.util.text.TextFormatting.GOLD;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SkeletalKingEntity extends MonsterEntity implements IRangedAttackMob, IAnimatable {

    public static final Predicate<LivingEntity> ANY_ENTITY = entity ->
            entity != null && entity.attackable() && !(entity instanceof SkeletalKingEntity);
    private final SpecificServerBossInfo bossInfo = new SpecificServerBossInfo(this.getDisplayName(), SpecificServerBossInfo.BossInfoDungeonType.SKELETAL_KING);
    private AnimationFactory factory = new AnimationFactory(this);

    public SkeletalKingEntity(EntityType<? extends SkeletalKingEntity> type, World world) {
        super(type, world);
        this.setPersistenceRequired();
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.noCulling = true;
        this.refreshDimensions();
    }

    @Override
    public EntitySize getDimensions(Pose pose) {
        return new EntitySize(2.4f, 8, false).scale(1f, 1f);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 7F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SpecificRangedAttackGoal(this, EntityAIType.WITHER));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 20.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, true, ANY_ENTITY));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1024.0D)
                .add(MOVEMENT_SPEED, 0.6000000238418579D)
                .add(ARMOR, 8.0D);
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

    }

  //  @Override
  //  public void handleStatusUpdate(byte id) {
  //      super.handleStatusUpdate(id);
  //  }

    @Override
    public ITextComponent getCustomName() {
        return new StringTextComponent("Skeletal King");
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

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean trueSources = source.getEntity() instanceof SkeletalKingEntity || source.getEntity() instanceof WitherEntity;
        if (source != DamageSource.DROWN && !(trueSources)) {
            Entity sourceEntity = source.getDirectEntity();
            if (!(sourceEntity instanceof PlayerEntity) && sourceEntity instanceof LivingEntity && ((LivingEntity) sourceEntity).getMobType() == this.getMobType()) {
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
                BlazeEntity split = new BlazeEntity(BLAZE, level);
                split.setPos(getX(), getY(), getZ());
                split.finalizeSpawn((IServerWorld) level, this.level.getCurrentDifficultyAt(new BlockPos(this.blockPosition())), SpawnReason.NATURAL, null, null);
                this.level.addFreshEntity(split);
                split.setCustomName(new StringTextComponent(GOLD + "" + BOLD + name));
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
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
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