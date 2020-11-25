package com.sofodev.armorplus.registry.entities.bosses;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.registry.APItems;
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
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Collections;

import static net.minecraft.entity.EntityType.BLAZE;
import static net.minecraft.entity.ai.attributes.Attributes.ARMOR;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;
import static net.minecraft.util.text.TextFormatting.BOLD;
import static net.minecraft.util.text.TextFormatting.GOLD;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SkeletalKingEntity extends MonsterEntity implements IRangedAttackMob {

    public static final Predicate<Entity> ANY_ENTITY = entity ->
            entity instanceof LivingEntity && ((LivingEntity) entity).attackable() && !(entity instanceof SkeletalKingEntity);
    private final SpecificServerBossInfo bossInfo = new SpecificServerBossInfo(this.getDisplayName(), SpecificServerBossInfo.BossInfoDungeonType.SKELETAL_KING);
    private final EntityType<? extends SkeletalKingEntity> type;
    //  public static List<EntityPlayer> playersReadDeathDialog = new ArrayList<>();
    //  private static TextComponentTranslation KING_NAME = translate(GOLD, "dialogs.armorplus.skeletal_king.name", BOLD.toString());
    //  public static ITextComponent PHASE_ONE = setDialog(KING_NAME, "line_one.a", "line_one.b", "line_one.c");
    //  public static ITextComponent PHASE_TWO = setDialog(KING_NAME, "line_two.a");
    //  public static ITextComponent PHASE_THREE = setDialog(KING_NAME, "line_three.a", "line_three.b", "line_three.c");
    //  public static ITextComponent PHASE_FOUR = setDialog(KING_NAME, "line_four.a", "line_four.b");
    //  public static ITextComponent PHASE_FIVE = setDialog(KING_NAME, "line_five.a", "line_five.b");
    //  public static ITextComponent PHASE_SIX = setDialog(KING_NAME, "line_six.a", "line_six.b", "line_six.c");

    public SkeletalKingEntity(EntityType<? extends SkeletalKingEntity> type, World world) {
        super(type, world);
        this.type = type;
        this.enablePersistence();
        this.setHealth(this.getMaxHealth());
        this.getNavigator().setCanSwim(true);
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return new EntitySize(this.getWidth() * 7.0F, this.getHeight() * 7.0F, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SpecificRangedAttackGoal(this, EntityAIType.WITHER));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 20.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, LivingEntity.class, 0, false, true, ANY_ENTITY));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 1024.0D)
                .createMutableAttribute(MOVEMENT_SPEED, 0.6000000238418579D)
                .createMutableAttribute(ARMOR, 8.0D);
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
    public ITextComponent getCustomName() {
        return new StringTextComponent("Skeletal King");
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

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        boolean trueSources = source.getTrueSource() instanceof SkeletalKingEntity || source.getTrueSource() instanceof WitherEntity;
        if (source != DamageSource.DROWN && !(trueSources)) {
            Entity entity1 = source.getTrueSource();
            if (!(entity1 instanceof PlayerEntity) && entity1 instanceof LivingEntity && ((LivingEntity) entity1).getCreatureAttribute() == this.getCreatureAttribute()) {
                return false;
            } else {
                return super.attackEntityFrom(source, amount);
            }
        } else {
            return false;
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!world.isRemote) {
            String[] names = new String[]{"D", "E", "A", "T", "H"};
            for (String name : names) {
                BlazeEntity split = new BlazeEntity(BLAZE, world);
                split.setPosition(getPosX(), getPosY(), getPosZ());
                split.onInitialSpawn((IServerWorld) world, this.world.getDifficultyForLocation(new BlockPos(this.getPosition())), SpawnReason.NATURAL, null, null);
                this.world.addEntity(split);
                split.setCustomName(new StringTextComponent(GOLD + "" + BOLD + name));
                split.setCustomNameVisible(true);
                split.setInvisible(false);
                split.setInvulnerable(false);
                split.setCanPickUpLoot(true);
                split.forceSpawn = true;
            }

            this.entityDropItem(new ItemStack(Items.NETHER_STAR, 2 + rand.nextInt(1)), 5f);
            this.entityDropItem(new ItemStack(APItems.WITHER_BONE.get(), 1 + rand.nextInt(1)), 5f);
        }
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    public boolean isNonBoss() {
        return false;
    }

    @Override
    public float getCollisionBorderSize() {
        return super.getCollisionBorderSize();
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return super.getBoundingBox();
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
    public EntityType<? extends SkeletalKingEntity> getType() {
        return type;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}