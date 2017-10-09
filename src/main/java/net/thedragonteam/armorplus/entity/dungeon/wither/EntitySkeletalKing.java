package net.thedragonteam.armorplus.entity.dungeon.wither;

import com.google.common.base.Predicate;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.entity.dungeon.base.BossInfoServerDungeon;
import net.thedragonteam.armorplus.entity.dungeon.base.EntityAIRangedDungeonAttack;
import net.thedragonteam.armorplus.entity.dungeon.base.EntityAIRangedDungeonAttack.EntityAIType;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;

import javax.annotation.Nullable;
import java.util.Collections;

import static net.minecraft.item.ItemStack.EMPTY;
import static net.thedragonteam.armorplus.entity.dungeon.base.BossInfoServerDungeon.BossInfoDungeonType;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class EntitySkeletalKing extends EntityWitherSkeleton implements IRangedAttackMob {

    private final BossInfoServerDungeon bossInfo;

    private static final Predicate<Entity> PLAYER = target -> target instanceof EntityPlayer &&
            ((EntityLivingBase) target).attackable();

    public EntitySkeletalKing(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 7.0F, this.height * 7.0F);
        this.bossInfo = new BossInfoServerDungeon(this.getDisplayName(), BossInfoDungeonType.WITHER);
        this.enablePersistence();
        this.isImmuneToFire = true;
    }


    public static void registerFixesSkeletalKing(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntitySkeletalKing.class);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        if (!this.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
            this.setHeldItem(EnumHand.MAIN_HAND, EMPTY);
        }
        if (!this.getHeldItem(EnumHand.OFF_HAND).isEmpty()) {
            this.setHeldItem(EnumHand.OFF_HAND, EMPTY);
        }
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void onUpdate() {
        this.removePotionEffect(MobEffects.WITHER);
        super.onUpdate();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRangedDungeonAttack(this, EntityAIType.WITHER));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 20.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 0, false, false, PLAYER));

        super.initEntityAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
    }

    @Override
    public Iterable<ItemStack> getEquipmentAndArmor() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment() {
        return Collections.emptyList();
    }

    /**
     * Sets the custom name tag for this entity
     */
    @Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
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
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private void launchWitherMinionsToEntity(int pos, EntityLivingBase entity) {
        this.launchWitherMinions(pos, entity.posX, entity.posY + (double) entity.getEyeHeight() * 0.5D, entity.posZ);
    }

    /**
     * Launches a Freeze Bomb toward (par2, par4, par6)
     */
    private void launchWitherMinions(int pos, double x, double y, double z) {
        this.world.playEvent(null, 1024, new BlockPos(this), 0);
        double d0 = this.getHeadX(pos);
        double d1 = this.getHeadY(pos);
        double d2 = this.getHeadZ(pos);
        double d3 = x - d0;
        double d4 = y - d1;
        double d5 = z - d2;
        EntityWitherMinion witherMinion = new EntityWitherMinion(this.world, this, d3, d4, d5);
        witherMinion.posY = d1;
        witherMinion.posX = d0;
        witherMinion.posZ = d2;
        this.world.spawnEntity(witherMinion);
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.launchWitherMinionsToEntity(0, target);
    }

    private double getHeadX(int xPos) {
        if (xPos <= 0) {
            return this.posX;
        }
        float f = (this.renderYawOffset + (float) (180 * (xPos - 1))) * 0.017453292F;
        float f1 = MathHelper.cos(f);
        return this.posX + (double) f1 * 1.3D;
    }

    private double getHeadY(int yPos) {
        return yPos <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
    }

    private double getHeadZ(int zPos) {
        if (zPos <= 0) {
            return this.posZ;
        }
        float f = (this.renderYawOffset + (float) (180 * (zPos - 1))) * 0.017453292F;
        float f1 = MathHelper.sin(f);
        return this.posZ + (double) f1 * 1.3D;
    }

}
