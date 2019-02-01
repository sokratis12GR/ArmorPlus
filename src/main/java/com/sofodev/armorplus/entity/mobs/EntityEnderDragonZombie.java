/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.mobs;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.entity.ai.EntityTaskEDZAttack;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.thedragonlib.util.LogHelper;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityEnderDragonZombie extends EntityMob {

    public static final Predicate<Entity> ANY_ENTITY = entity -> entity instanceof EntityLivingBase && ((EntityLivingBase) entity).attackable() && !(entity instanceof EntityEnderDragonZombie || entity instanceof EntityEnderman || entity instanceof EntityDragon)  ;
    public static final ResourceLocation LOOT = Utils.setRL("entities/ender_dragon_zombie");
    // We reuse the zombie model which has arms that need to be raised when the zombie is attacking:
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityEnderDragonZombie.class, DataSerializers.BOOLEAN);
    @SideOnly(Side.CLIENT)
    public boolean armsRaised;

    public EntityEnderDragonZombie(World world) {
        super(world);
        setSize(0.6f, 1.95f);
        setItemStackToSlot(EntityEquipmentSlot.HEAD, getItemStack(APItems.enderDragonHelmet));
        setItemStackToSlot(EntityEquipmentSlot.CHEST, getItemStack(APItems.enderDragonChestplate));
        setItemStackToSlot(EntityEquipmentSlot.LEGS, getItemStack(APItems.enderDragonLeggings));
        setItemStackToSlot(EntityEquipmentSlot.FEET, getItemStack(APItems.enderDragonBoots));
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, getItemStack(APItems.enderDragonBattleAxe));
        dropEquipment(false, 0);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(ARMS_RAISED, java.lang.Boolean.FALSE);
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        super.setItemStackToSlot(slotIn, stack);
    }

    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
        super.dropEquipment(false, lootingModifier);
    }

    @Override
    public void setDropChance(EntityEquipmentSlot slotIn, float chance) {
        super.setDropChance(slotIn, 0f);
    }

    @Override
    public void setDropItemsWhenDead(boolean dropWhenDead) {
        super.setDropItemsWhenDead(false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.followRange);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.movementSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.armor);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.health);
        if (ModConfig.DebugConfig.debugMode && ModConfig.DebugConfig.debugModeEnderDragonZombie) {
            LogHelper.getLogger(MODID).info("EnderDragon Zombie Follow Range: " + ModConfig.EntitiesConfig.ender_dragon_zombie.followRange);
            LogHelper.getLogger(MODID).info("EnderDragon Zombie Movement Speed: " + ModConfig.EntitiesConfig.ender_dragon_zombie.movementSpeed);
            LogHelper.getLogger(MODID).info("EnderDragon Zombie Attack Damage: " + ModConfig.EntitiesConfig.ender_dragon_zombie.attackDamage);
            LogHelper.getLogger(MODID).info("EnderDragon Zombie Armor: " + ModConfig.EntitiesConfig.ender_dragon_zombie.armor);
            LogHelper.getLogger(MODID).info("EnderDragon Zombie Max Health: " + ModConfig.EntitiesConfig.ender_dragon_zombie.health);
        }
    }

    public boolean areArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, armsRaised);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityTaskEDZAttack(this, 1.0, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPigZombie.class));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 0, false, false, ANY_ENTITY));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            // This zombie gives wither 4 when it attacks
            if (entityIn instanceof EntityLivingBase && ModConfig.EntitiesConfig.ender_dragon_zombie.enableWithering) {
                ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, ModConfig.EntitiesConfig.ender_dragon_zombie.witheringEffectDuration, ModConfig.EntitiesConfig.ender_dragon_zombie.witheringEffectLevel));
            }
            return true;
        }
        return false;
    }


    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

}