package net.thedragonteam.armorplus.entity.dungeon.guardian;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class EntityGuardianOverlord extends EntityGuardian implements IRangedAttackMob {

    private final BossInfoServerGuardianOverlord bossInfo;
    private final int[] nextHeadUpdate = new int[2];
    private final int[] idleHeadUpdates = new int[2];

    public EntityGuardianOverlord(World worldIn) {
        super(worldIn);
        //  this.setSize(this.width * 2.35F, this.height * 2.35F); Old stuffs - I wonder if I will ever need to change it back
        this.setSize(this.width * 5.00F, this.height * 5.00F);
        this.enablePersistence();
        if (this.wander != null) {
            this.wander.setExecutionChance(100);
        }
        bossInfo = new BossInfoServerGuardianOverlord(this.getDisplayName());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(2, new EntityAIGuardianOverlordAttack(this, 0.5D, 10, 3.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        super.initEntityAI();
    }

    @Override
    public int getAttackDuration() {
        return 30;
    }

    @SideOnly(Side.CLIENT)
    public void setGhost() {
        this.clientSideSpikesAnimation = 1.0F;
        this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;
    }

    public static void registerFixesElderGuardian(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityGuardianOverlord.class);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(37.5D);
    }

    @Override
    public void onUpdate() {
        if (!world.isRemote) {
            //noinspection MethodCallSideOnly
            this.setGhost();
        }
        super.onUpdate();
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

        for (int i = 1; i < 3; ++i) {
            if (this.ticksExisted >= this.nextHeadUpdate[i - 1]) {
                this.nextHeadUpdate[i - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);

                if (this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) {
                    int j3 = i - 1;
                    int k3 = this.idleHeadUpdates[i - 1];
                    this.idleHeadUpdates[j3] = this.idleHeadUpdates[i - 1] + 1;

                    if (k3 > 15) {
                        double d0 = MathHelper.nextDouble(this.rand, this.posX - 10.0D, this.posX + 10.0D);
                        double d1 = MathHelper.nextDouble(this.rand, this.posY - 5.0D, this.posY + 5.0D);
                        double d2 = MathHelper.nextDouble(this.rand, this.posZ - 10.0D, this.posZ + 10.0D);
                        this.launchFreezeBombToCoords(i + 1, d0, d1, d2);
                        this.idleHeadUpdates[i - 1] = 0;
                    }
                }
            }
        }

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

    private void launchFreezeBombToEntity(int pos, EntityLivingBase entity) {
        this.launchFreezeBombToCoords(pos, entity.posX, entity.posY + (double) entity.getEyeHeight() * 0.5D, entity.posZ);
    }

    /**
     * Launches a Freeze Bomb toward (par2, par4, par6)
     */
    private void launchFreezeBombToCoords(int pos, double x, double y, double z) {
        this.world.playEvent(null, 1024, new BlockPos(this), 0);
        double d0 = this.getHeadX(pos);
        double d1 = this.getHeadY(pos);
        double d2 = this.getHeadZ(pos);
        double d3 = x - d0;
        double d4 = y - d1;
        double d5 = z - d2;
        EntityFreezeBomb entityFreezeBomb = new EntityFreezeBomb(this.world, this, d3, d4, d5);

        entityFreezeBomb.posY = d1;
        entityFreezeBomb.posX = d0;
        entityFreezeBomb.posZ = d2;
        this.world.spawnEntity(entityFreezeBomb);
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.launchFreezeBombToEntity(0, target);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

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
