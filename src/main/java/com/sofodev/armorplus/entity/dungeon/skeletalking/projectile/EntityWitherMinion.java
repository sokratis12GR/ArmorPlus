/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.dungeon.skeletalking.projectile;

import com.sofodev.armorplus.util.TextUtils;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

import static java.lang.String.format;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class EntityWitherMinion extends EntityFireball implements IThrowableEntity {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityWitherMinion.class, DataSerializers.BOOLEAN);
    private Entity shooter;

    public EntityWitherMinion(World worldIn) {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityWitherMinion(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
        this.shooter = shooter;
    }

    @SideOnly(Side.CLIENT)
    public EntityWitherMinion(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesWitherMinion(DataFixer fixer) {
        EntityFireball.registerFixesFireball(fixer, "WitherMinion");
    }

    private static void setDropChance(EntityWitherSkeleton minion, EntityEquipmentSlot... slots) {
        Arrays.stream(slots).forEachOrdered(slot -> minion.setDropChance(slot, 0.0F));
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    @Override
    protected float getMotionFactor() {
        return super.getMotionFactor();
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (this.world.isRemote || shootingEntity == null || !(result.entityHit instanceof EntityPlayer)) {
            return;
        }
        // Minion Data
        BlockPos blockPos = new BlockPos(result.entityHit);
        String phaseText = "%sRise Minions, Rise!!!";
        int amountWarriorMax = 4;
        int amountArcherMax = 3;
        int amountPaladinMax = 2;
        int warriorBound = rand.nextInt(amountWarriorMax) + 1;
        int archerBound = rand.nextInt(amountArcherMax) + 1;
        int paladinBound = rand.nextInt(amountPaladinMax) + 1;
        // Spawning Mechanic
        for (int min = 0; min < warriorBound; min++) {
            EntityWitherSkeleton minionWarrior = create(blockPos);
            if (checkPhase(1200.0F)) {
                this.setMinionStats(minionWarrior, Skeleton.WARRIOR_0);
            } else if (checkPhase(1000.0F)) {
                this.setMinionStats(minionWarrior, Skeleton.WARRIOR_1);
            } else {
                spawnMinions(minionWarrior, Skeleton.WARRIOR_2, Skeleton.WARRIOR_3, Skeleton.WARRIOR_4, Skeleton.WARRIOR_5);
            }
        }
        for (int min = 0; min < archerBound; min++) {
            EntityWitherSkeleton minionArcher = create(blockPos);
            spawnMinions(minionArcher, Skeleton.ARCHER_1, Skeleton.ARCHER_2, Skeleton.ARCHER_3, Skeleton.ARCHER_4);
        }
        for (int min = 0; min < paladinBound; min++) {
            EntityWitherSkeleton minionPaladin = create(blockPos);
            if (checkPhase(400.0F)) {
                this.setMinionStats(minionPaladin, Skeleton.PALADIN_1);
            } else if (checkPhase(200.0F)) {
                this.setMinionStats(minionPaladin, Skeleton.PALADIN_2);
            }
        }
        result.entityHit.sendMessage(TextUtils.formatText(TextFormatting.RED, phaseText, TextFormatting.ITALIC));
        this.setDead();
    }

    private void spawnMinions(EntityWitherSkeleton minion, Skeleton one, Skeleton two, Skeleton three, Skeleton four) {
        if (checkPhase(800.0F)) {
            this.setMinionStats(minion, one);
        } else if (checkPhase(600.0F)) {
            this.setMinionStats(minion, two);
        } else if (checkPhase(400.0F)) {
            this.setMinionStats(minion, three);
        } else if (checkPhase(200.0F)) {
            this.setMinionStats(minion, four);
        }
    }

    private EntityWitherSkeleton create(BlockPos pos) {
        EntityWitherSkeleton minion = new EntityWitherSkeleton(this.world);
        minion.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        minion.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(minion)), null);
        this.world.spawnEntity(minion);
        return minion;
    }

    private boolean checkPhase(float phase) {
        float health = shootingEntity.getHealth();
        return health <= phase && health > (phase - 200);
    }

    private void setMinionStats(EntityWitherSkeleton witherSkeleton, Skeleton data) {
        setMinionStats(witherSkeleton, data.getName(), data.getHealth(), data.getWeapon(), data.getOffHand(), Utils.getItemStacks(data.getArmor()));
    }

    private void setMinionStats(EntityWitherSkeleton minion, String type, double maxHealth, ItemStack mainHand, ItemStack offHand, NonNullList<ItemStack> equipedArmor) {
        minion.setCustomNameTag(format("%sSkeletal King's %s", TextFormatting.YELLOW, type));
        minion.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        minion.heal(minion.getMaxHealth());
        minion.setHealth(minion.getMaxHealth());
        minion.setDropItemsWhenDead(false);
        minion.setHeldItem(EnumHand.MAIN_HAND, mainHand);
        minion.setHeldItem(EnumHand.OFF_HAND, offHand);
        minion.setItemStackToSlot(HEAD, equipedArmor.get(0));
        minion.setItemStackToSlot(CHEST, equipedArmor.get(1));
        minion.setItemStackToSlot(LEGS, equipedArmor.get(2));
        minion.setItemStackToSlot(FEET, equipedArmor.get(3));
        setDropChance(minion, MAINHAND, OFFHAND, HEAD, CHEST, LEGS, FEET);
        minion.setAlwaysRenderNameTag(true);
        minion.setInvisible(false);
        minion.setEntityInvulnerable(false);
        minion.setCanPickUpLoot(true);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(INVULNERABLE, false);
    }

    @Override
    protected boolean isFireballFiery() {
        return false;
    }

    /**
     * Gets the entity that threw/created this entity.
     *
     * @return The owner instance, Null if none.
     */
    @Override
    public Entity getThrower() {
        return shooter;
    }

    /**
     * Sets the entity that threw/created this entity.
     *
     * @param entity The new thrower/creator.
     */
    @Override
    public void setThrower(Entity entity) {
        shooter = entity;
    }
}