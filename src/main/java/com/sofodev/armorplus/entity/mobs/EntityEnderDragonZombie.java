/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.mobs;

import com.google.common.base.Predicate;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.entity.ai.EntityTaskEDZAttack;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.util.Utils;
import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.entity.*;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.ArmorPlus.RegistryEvents.ENTITY_ENDER_DRAGON_ZOMBIE;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityEnderDragonZombie extends EntityMob {

    public static final Predicate<Entity> ANY_ENTITY = entity -> entity instanceof EntityLivingBase && ((EntityLivingBase) entity).attackable() && !(entity instanceof EntityEnderDragonZombie || entity instanceof EntityEnderman || entity instanceof EntityDragon);
    public static final ResourceLocation LOOT = Utils.setRL("entities/ender_dragon_zombie");
    // We reuse the zombie model which has arms that need to be raised when the zombie is attacking:
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityEnderDragonZombie.class, DataSerializers.BOOLEAN);
    @OnlyIn(Dist.CLIENT)
    public boolean armsRaised;

    public EntityEnderDragonZombie(World world) {
        super(ENTITY_ENDER_DRAGON_ZOMBIE, world);
        setSize(0.6f, 1.95f);
        setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(APItems.enderDragonHelmet));
        setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(APItems.enderDragonChestplate));
        setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(APItems.enderDragonLeggings));
        setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(APItems.enderDragonBoots));
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(APItems.enderDragonBattleAxe));
        dropEquipment(false, 0);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData entityLivingData, @Nullable NBTTagCompound itemNbt) {
        this.getDataManager().register(ARMS_RAISED, Boolean.FALSE);
        return super.onInitialSpawn(difficulty, entityLivingData, itemNbt);
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
    protected void registerAttributes() {
        super.registerAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.movementSpeed);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.attackDamage);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.armor);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.EntitiesConfig.ender_dragon_zombie.health);
        if (ModConfig.DebugConfig.debugMode && ModConfig.DebugConfig.debugModeEnderDragonZombie) {
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