/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.entity.dungeon.skeletalking;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.common.entity.dungeon.base.BossInfoServerDungeon;
import com.sofodev.armorplus.common.entity.dungeon.base.EntityAIRangedDungeonAttack;
import com.sofodev.armorplus.common.entity.dungeon.base.EntityAIRangedDungeonAttack.EntityAIType;
import com.sofodev.armorplus.common.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;

import static com.sofodev.armorplus.common.entity.dungeon.base.BossInfoServerDungeon.BossInfoDungeonType;
import static com.sofodev.armorplus.common.util.TextUtils.translatedText;
import static net.minecraft.entity.SharedMonsterAttributes.*;
import static net.minecraft.init.MobEffects.WITHER;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.ITALIC;

/**
 * @author Sokratis Fotkatzikis
 **/
public class EntitySkeletalKing extends EntityWitherSkeleton implements IRangedAttackMob {

    public static final Predicate<Entity> ANY_ENTITY = entity -> entity instanceof EntityLivingBase && ((EntityLivingBase) entity).attackable() && !(entity instanceof EntityWitherSkeleton);
    private final BossInfoServerDungeon bossInfo;
    //  public static List<EntityPlayer> playersReadDeathDialog = new ArrayList<>();
    //  private static TextComponentTranslation KING_NAME = translate(GOLD, "dialogs.armorplus.skeletal_king.name", BOLD.toString());
    //  public static ITextComponent PHASE_ONE = setDialog(KING_NAME, "line_one.a", "line_one.b", "line_one.c");
    //  public static ITextComponent PHASE_TWO = setDialog(KING_NAME, "line_two.a");
    //  public static ITextComponent PHASE_THREE = setDialog(KING_NAME, "line_three.a", "line_three.b", "line_three.c");
    //  public static ITextComponent PHASE_FOUR = setDialog(KING_NAME, "line_four.a", "line_four.b");
    //  public static ITextComponent PHASE_FIVE = setDialog(KING_NAME, "line_five.a", "line_five.b");
    //  public static ITextComponent PHASE_SIX = setDialog(KING_NAME, "line_six.a", "line_six.b", "line_six.c");

    public EntitySkeletalKing(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 7.0F, this.height * 7.0F);
        this.bossInfo = new BossInfoServerDungeon(this.getDisplayName(), BossInfoDungeonType.SKELETAL_KING);
        this.enablePersistence();
        this.isImmuneToFire = true;
    }

    @Override
    public void onUpdate() {
        if (this.isPotionActive(WITHER)) {
            this.removePotionEffect(WITHER);
        }
        super.onUpdate();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRangedDungeonAttack(this, EntityAIType.WITHER));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 20.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 0, false, true, ANY_ENTITY));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(MAX_HEALTH).setBaseValue(1200.0D);
        this.getEntityAttribute(MOVEMENT_SPEED).setBaseValue(0.6000000238418579D);
        this.getEntityAttribute(ARMOR).setBaseValue(4.0F);
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

    //Dialog
    //   private int sendDialog = 0;


    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!world.isRemote) {
            String[] names = new String[]{"Lefty", "Central", "Righty"};
            for (int i = 0; i < 3; i++) {
                EntityWither split = new EntityWither(world);
                split.setPosition(posX, posY, posZ);
                split.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(this)), null);
                this.world.spawnEntity(split);
                split.setCustomNameTag(names[i]);
                split.setAlwaysRenderNameTag(true);
                split.setInvisible(false);
                split.setEntityInvulnerable(false);
                split.setCanPickUpLoot(true);
                split.forceSpawn = true;
            }
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

    private static ITextComponent setDialog(ITextComponent parent, String... appended) {
        ITextComponent copy = parent.createCopy();
        for (String part : appended) {
            copy.appendText(dialog(part));
        }
        return copy;
    }

    private static String dialog(String part, Object... args) {
        return "\n" + translatedText(ITALIC, "dialogs.armorplus.skeletal_king." + part, args);
    }


    public static void registerFixesSkeletalKing(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntitySkeletalKing.class);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //We override this so the Skeletal King spawns with no equipment in its possession
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        if (!this.getHeldItemMainhand().isEmpty()) {
            this.setHeldItem(EnumHand.MAIN_HAND, EMPTY);
        }
        if (!this.getHeldItemOffhand().isEmpty()) {
            this.setHeldItem(EnumHand.OFF_HAND, EMPTY);
        }
        return super.onInitialSpawn(difficulty, livingdata);
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

}
