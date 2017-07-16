package net.thedragonteam.armorplus.entity.dungeon.wither;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.entity.dungeon.base.BossInfoServerDungeon;
import net.thedragonteam.armorplus.entity.dungeon.base.EntityAIRangedDungeonAttack;
import net.thedragonteam.armorplus.entity.dungeon.base.EntityAIRangedDungeonAttack.EntityAIType;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;

import static net.thedragonteam.armorplus.entity.dungeon.base.BossInfoServerDungeon.*;

/**
 * Created by sokratis12GR on 6/18/2017.
 */
public class EntitySkeletalKing extends EntityWitherSkeleton implements IRangedAttackMob {

    private final BossInfoServerDungeon bossInfo;

    public EntitySkeletalKing(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 7.0F, this.height * 7.0F);
        this.bossInfo = new BossInfoServerDungeon(this.getDisplayName(), BossInfoDungeonType.WITHER);
        this.enablePersistence();
        this.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
        this.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
    }


    public static void registerFixesSkeletalKing(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntitySkeletalKing.class);
    }

    @Override
    public void onUpdate() {
        this.removePotionEffect(MobEffects.WITHER);
        super.onUpdate();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(2, new EntityAIRangedDungeonAttack(this, EntityAIType.WITHER));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        super.initEntityAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.5D);
    }

    @Override
    public Iterable<ItemStack> getEquipmentAndArmor() {
        return super.getEquipmentAndArmor();
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment() {
        return super.getHeldEquipment();
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
        int spawnCount = rand.nextInt(4 - 1 + 1) + 1;
        EntityWitherMinion witherMinion = new EntityWitherMinion(this.world, this, spawnCount, d3, d4, d5);
        witherMinion.setPosition(d1, d0, d2);
        this.world.spawnEntity(witherMinion);
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
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
