package net.thedragonteam.armorplus.entity.dungeon.skeletalking;

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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.entity.dungeon.base.BossInfoServerDungeon;
import net.thedragonteam.armorplus.entity.dungeon.base.EntityAIRangedDungeonAttack;
import net.thedragonteam.armorplus.entity.dungeon.base.EntityAIRangedDungeonAttack.EntityAIType;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.*;
import static net.thedragonteam.armorplus.entity.dungeon.base.BossInfoServerDungeon.BossInfoDungeonType;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class EntitySkeletalKing extends EntityWitherSkeleton implements IRangedAttackMob {

    private static final Predicate<Entity> PLAYER = target -> target instanceof EntityPlayer && ((EntityLivingBase) target).attackable();
    private final BossInfoServerDungeon bossInfo;
    private List<EntityPlayer> playersReadDialog = new ArrayList<>();
    private List<EntityPlayer> playersReadDeathDialog = new ArrayList<>();

    public EntitySkeletalKing(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 7.0F, this.height * 7.0F);
        this.bossInfo = new BossInfoServerDungeon(this.getDisplayName(), BossInfoDungeonType.SKELETAL_KING);
        this.enablePersistence();
        this.isImmuneToFire = true;
    }

    public static void registerFixesSkeletalKing(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntitySkeletalKing.class);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
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

    private int sendDialog = 0;
    private boolean sentA, sentB, sentC, sentD, sentE, sentF, sentEnd;

    @Override
    public void onUpdate() {
        this.removePotionEffect(MobEffects.WITHER);
        super.onUpdate();
        //Some in-game dialogs
        EntityPlayer playerInRange = world.getClosestPlayerToEntity(this, 150);
        sendDialog++;
        if (world.isRemote || playerInRange == null) {
            return;
        }
        if (!playersReadDialog.contains(playerInRange) && (sendDialog % (20 * 6)) == 0) {
            if (checkPhase(1200.0F)) {
                if (!sentA) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sHa ha ha!" +
                                    "\n%sYou really think you even want to get me started with you?" +
                                    "\n%sThis fight will be over way before it even starts.", GOLD, BOLD, ITALIC, ITALIC, ITALIC
                    )));
                    sentA = true;
                }
            } else if (checkPhase(1000.0F)) {
                if (!sentB) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sThat's just the beginning.", GOLD, BOLD, ITALIC
                    )));
                    sentB = true;
                }
            } else if (checkPhase(800.0F)) {
                if (!sentC) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sIt seems that you are enjoying my minions..." +
                                    "\n%sWell, I got some news to you." +
                                    "\n%sI own this world", GOLD, BOLD, ITALIC, ITALIC, ITALIC
                    )));
                    sentC = true;
                }
            } else if (checkPhase(600.0F)) {
                if (!sentD) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sWhy do you keep fighting" +
                                    "\n%sThere is no escape from me!", GOLD, BOLD, ITALIC, ITALIC
                    )));
                    sentD = true;
                }
            } else if (checkPhase(400.0F)) {
                if (!sentE) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sWHY CAN'T YOU JUST DIE!?" +
                                    "\n%sPaladins, Rise!", GOLD, BOLD, ITALIC, ITALIC
                    )));
                    sentE = true;
                }
            } else if (checkPhase(200.0F)) {
                if (!sentF) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sNothing can stop me from destroying this world" +
                                    "\n%sIf my minions, cannot kill you. then I'll kill you myself!" +
                                    "\n%sMinions, CHARGE with your full power!!!", GOLD, BOLD, ITALIC, ITALIC, ITALIC
                    )));
                    sentF = true;
                }
            } else if (isDead) {
                if (sentEnd) {
                    playerInRange.sendMessage(new TextComponentTranslation(format(
                            "%s%sSkeletal King:" +
                                    "\n%sYou cannot win this battle, " + playerInRange.getName() +
                                    "\n%sThe end is near, but remember I'll be BACK " +
                                    "\n%sAnd there will be an END to this world.", GOLD, BOLD, ITALIC, ITALIC, ITALIC
                    )));
                    sentEnd = true;
                    playersReadDialog.add(playerInRange);
                }
            }
        }

    }

    @Override
    protected void onDeathUpdate() {
        super.onDeathUpdate();
        EntityPlayer playerInRange = world.getClosestPlayerToEntity(this, 150);
        if (world.isRemote || playerInRange == null) {
            return;
        }
        if (!playersReadDeathDialog.contains(playerInRange) && (sendDialog % (20 * 6)) == 0) {
            playerInRange.sendMessage(new TextComponentTranslation(format(
                    "%s%sSkeletal King:" +
                            "\n%sYou cannot win this battle, " + playerInRange.getName() +
                            "\n%sThe end is near, but remember I'll be BACK " +
                            "\n%sAnd there will be an END to this world.", GOLD, BOLD, ITALIC, ITALIC, ITALIC
            )));
            playersReadDeathDialog.add(playerInRange);
        }
    }

    private boolean checkPhase(float phase) {
        float health = this.getHealth();
        return health <= phase && health > (phase - 200);
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
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0F);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0F);
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
        double headingX = this.getHeadX(pos);
        double headingY = this.getHeadY(pos);
        double headingZ = this.getHeadZ(pos);
        double estimatedX = x - headingX;
        double estimatedY = y - headingY;
        double estimatedZ = z - headingZ;
        EntityWitherMinion witherMinion = new EntityWitherMinion(this.world, this, estimatedX, estimatedY, estimatedZ);
        witherMinion.posY = headingY;
        witherMinion.posX = headingX;
        witherMinion.posZ = headingZ;
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
