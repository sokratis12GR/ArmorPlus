/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.projectile;

import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

import static com.sofodev.armorplus.ArmorPlus.RegistryEvents.ENTITY_WITHER_MINION;
import static com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.projectile.Skeleton.*;
import static com.sofodev.armorplus.common.util.TextUtils.translate;
import static java.lang.String.format;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public class EntityWitherMinion extends EntityThrowable {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityWitherMinion.class, DataSerializers.BOOLEAN);

    public EntityWitherMinion(World world) {
        super(ENTITY_WITHER_MINION, world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityWitherMinion(World world, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(ENTITY_WITHER_MINION, shooter, world);
        this.setVelocity(accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
        this.thrower = shooter;
    }

    @OnlyIn(Dist.CLIENT)
    public EntityWitherMinion(World world, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ENTITY_WITHER_MINION, x, y, z, world);
        this.setVelocity(accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    private static void setDropChance(EntityWitherSkeleton minion, EntityEquipmentSlot... slots) {
        Arrays.stream(slots).forEachOrdered(slot -> minion.setDropChance(slot, 0.0F));
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
        if (this.world.isRemote || thrower == null || !(result.entity instanceof EntityLivingBase)) {
            return;
        }
        // Minion Data
        BlockPos blockPos = new BlockPos(result.entity);
        String phaseText = "%sRise Minions, Rise!!!";
        int amountWarriorMax = 4;
        int amountArcherMax = 3;
        int amountPaladinMax = 2;
        int warriorBound = rand.nextInt(amountWarriorMax) + 1;
        int archerBound = rand.nextInt(amountArcherMax) + 1;
        int paladinBound = rand.nextInt(amountPaladinMax) + 1;
        // Spawning Mechanic
        if (checkPhase(1200, 0)) {
            for (int min = 0; min < warriorBound; min++) {
                if (checkPhase(1200.0F)) {
                    EntityWitherSkeleton minionWarrior = create(blockPos);
                    this.setMinionStats(minionWarrior, WARRIOR_0);
                } else if (checkPhase(1000.0F)) {
                    EntityWitherSkeleton minionWarrior = create(blockPos);
                    this.setMinionStats(minionWarrior, WARRIOR_1);
                } else {
                    EntityWitherSkeleton minionWarrior = create(blockPos);
                    spawnMinions(minionWarrior, WARRIOR_2, WARRIOR_3, WARRIOR_4, WARRIOR_5);
                }
            }
        }
        if (checkPhase(800, 0)) {
            for (int min = 0; min < archerBound; min++) {
                EntityWitherSkeleton minionArcher = create(blockPos);
                spawnMinions(minionArcher, ARCHER_1, ARCHER_2, ARCHER_3, ARCHER_4);
            }
        }
        if (checkPhase(400, 0)) {
            for (int min = 0; min < paladinBound; min++) {
                if (checkPhase(400.0F)) {
                    EntityWitherSkeleton minionPaladin = create(blockPos);
                    this.setMinionStats(minionPaladin, PALADIN_1);
                } else if (checkPhase(200.0F)) {
                    EntityWitherSkeleton minionPaladin = create(blockPos);
                    this.setMinionStats(minionPaladin, PALADIN_2);
                }
            }
        }
        if (result.entity instanceof EntityPlayer) {
            ((EntityPlayer) result.entity).sendStatusMessage(translate(TextFormatting.RED, phaseText, TextFormatting.ITALIC), true);
        }
        this.remove();
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
        minion.targetTasks.addTask(1, new EntityAIHurtByTarget(minion, true));
        minion.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(minion, EntityLiving.class, 0, false, false, EntitySkeletalKing.ANY_ENTITY));
        minion.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        minion.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(minion)), null, new NBTTagCompound());
        this.world.spawnEntity(minion);
        return minion;
    }

    private boolean checkPhase(float phase) {
        return checkPhase(phase, phase - 200);
    }

    private boolean checkPhase(float phase, float rangeEnd) {
        float health = thrower.getHealth();
        return health <= phase && health > (rangeEnd);
    }

    private void setMinionStats(EntityWitherSkeleton witherSkeleton, Skeleton data) {
        setMinionStats(witherSkeleton, data.getName(), data.getHealth(), data.getWeapon(), data.getOffHand(), Utils.getItemStacks(data.getArmor()));
    }

    private void setMinionStats(EntityWitherSkeleton minion, String type, double maxHealth, ItemStack mainHand, ItemStack offHand, NonNullList<ItemStack> equipedArmor) {
        minion.setCustomName(new TextComponentString(format("%sSkeletal King's %s", TextFormatting.YELLOW, type)));
        minion.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
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
        minion.setCustomNameVisible(true);
        minion.setInvisible(false);
        minion.setInvulnerable(false);
        minion.setCanPickUpLoot(true);
        minion.forceSpawn = true;
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
    protected void registerData() {
        this.dataManager.register(INVULNERABLE, false);
        super.registerData();
    }
}