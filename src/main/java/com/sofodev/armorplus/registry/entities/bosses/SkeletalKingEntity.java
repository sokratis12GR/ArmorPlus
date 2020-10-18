package com.sofodev.armorplus.registry.entities.bosses;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificRangedAttackGoal.EntityAIType;
import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Collections;

import static com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo.BossInfoDungeonType.SKELETAL_KING;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.entity.EntityType.BLAZE;
import static net.minecraft.entity.ai.attributes.Attributes.*;
import static net.minecraft.potion.Effects.RESISTANCE;
import static net.minecraft.potion.Effects.WITHER;
import static net.minecraft.util.text.Style.EMPTY;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SkeletalKingEntity extends WitherSkeletonEntity implements IRangedAttackMob {

    public static final Predicate<Entity> ANY_ENTITY = entity ->
            entity instanceof LivingEntity && ((LivingEntity) entity).attackable() && !(entity instanceof SkeletalKingEntity);
    private final SpecificServerBossInfo bossInfo;
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
        this.bossInfo = new SpecificServerBossInfo(this.getDisplayName(), SKELETAL_KING);
        this.enablePersistence();
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return new EntitySize(this.getWidth() * 7.0F, this.getHeight() * 7.0F, true);
    }

    @Override
    public void tick() {
        if (this.isPotionActive(WITHER)) {
            this.removePotionEffect(WITHER);
        }
        if (!this.isPotionActive(RESISTANCE)) {
            this.addPotionEffect(new EffectInstance(RESISTANCE, 240, 0));
        }
        super.tick();
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

    @Override
    protected void registerData() {
        super.registerData();
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

    private void launchWitherMinionsToEntity(int pos, LivingEntity entity) {
        this.launchWitherMinions(pos, entity.getPosX(), entity.getPosY() + (double) entity.getEyeHeight() * 0.5D, entity.getPosZ());
    }

    /**
     * Launches a Freeze Bomb toward (par2, par4, par6)
     */
    private void launchWitherMinions(int pos, double x, double y, double z) {
        this.world.playEvent(null, 1024, new BlockPos(this.getPosition()), 0);
        double headingX = this.getHeadX(pos);
        double headingY = this.getHeadY(pos);
        double headingZ = this.getHeadZ(pos);
        double estimatedX = x - headingX;
        double estimatedY = y - headingY;
        double estimatedZ = z - headingZ;
        WitherMinionEntity witherMinion = new WitherMinionEntity(this.world, this, estimatedX, estimatedY, estimatedZ);
        witherMinion.setPositionAndUpdate(headingX, headingY, headingZ);
        this.world.addEntity(witherMinion);
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        this.launchWitherMinionsToEntity(0, target);
    }

    private double getHeadX(int xPos) {
        if (xPos <= 0) {
            return this.getPosX();
        }
        float f = (this.renderYawOffset + (float) (180 * (xPos - 1))) * 0.017453292F;
        float f1 = MathHelper.cos(f);
        return this.getPosX() + (double) f1 * 1.3D;
    }

    private double getHeadY(int yPos) {
        return yPos <= 0 ? this.getPosY() + 3.0D : this.getPosY() + 2.2D;
    }

    private double getHeadZ(int zPos) {
        if (zPos <= 0) {
            return this.getPosZ();
        }
        float f = (this.renderYawOffset + (float) (180 * (zPos - 1))) * 0.017453292F;
        float f1 = MathHelper.sin(f);
        return this.getPosZ() + (double) f1 * 1.3D;
    }

    //Dialog
    //   private int sendDialog = 0;


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

    @Override
    protected void onDeathUpdate() {
        super.onDeathUpdate();
        //      if (getLastDamageSource() == null) {
        //          return;
        //      }
        //      EntityPlayer playerInRange = world.getClosestPlayerToEntity(this, 150);
        //      if (world.isRemote || playerInRange == null) {
        //          return;
        //      }
        //      if (!playersReadDeathDialog.contains(playerInRange) && sendDialog % (20 * 6) == 0) {
        //          playerInRange.sendMessage(KING_NAME.appendText(dialog("death_line.a", playerInRange.getName()))
        //              .appendText(dialog("death_line.b"))
        //              .appendText(dialog("death_line.c")));
        //          playersReadDeathDialog.add(playerInRange);
        //      }
    }

    //private static ITextComponent setDialog(ITextComponent parent, String... appended) {
    //    ITextComponent copy = parent.deepCopy();
    //    for (String part : appended) {
    //        copy.appendText(dialog(part));
    //    }
    //    return copy;
    //}

    private static String dialog(String part, Object... args) {
        return "\n" + translate("dialogs.armorplus.skeletal_king." + part, args).setStyle(EMPTY.setFormatting(ITALIC));
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //We override this so the Skeletal King spawns with no equipment in its possession
    }

    //  @Override
    //  public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
    //      if (!this.getHeldItemMainhand().isEmpty()) {
    //          this.setHeldItem(Hand.MAIN_HAND, EMPTY);
    //      }

    //      if (!this.getHeldItemOffhand().isEmpty()) {
    //          this.setHeldItem(Hand.OFF_HAND, EMPTY);
    //      }
    //      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    //  }

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
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        this.getAttribute(MAX_HEALTH).setBaseValue(1024.0D);
        this.getAttribute(MOVEMENT_SPEED).setBaseValue(0.6000000238418579D);
        this.getAttribute(ARMOR).setBaseValue(8.0);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}