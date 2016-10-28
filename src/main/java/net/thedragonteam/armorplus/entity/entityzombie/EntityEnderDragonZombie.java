/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity.entityzombie;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
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
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.thedragonlib.util.LogHelper;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.entity.entityzombie
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class EntityEnderDragonZombie extends EntityMob {

    public static final ResourceLocation LOOT = new ResourceLocation(ArmorPlus.MODID, "entities/ender_dragon_zombie");
    // We reuse the zombie model which has arms that need to be raised when the zombie is attacking:
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityEnderDragonZombie.class, DataSerializers.BOOLEAN);

    public EntityEnderDragonZombie(World worldIn) {
        super(worldIn);
        setSize(0.6F, 1.95F);
        setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.enderDragonHelmet));
        setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.enderDragonChestplate));
        setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ModItems.enderDragonLeggings));
        setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ModItems.enderDragonBoots));
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.enderDragonBattleAxe));
        dropEquipment(false, 0);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(ARMS_RAISED, Boolean.valueOf(false));
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, @Nullable ItemStack stack) {
        super.setItemStackToSlot(slotIn, stack);
    }

    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
        super.dropEquipment(false, lootingModifier);
    }

    @Override
    public void setDropChance(EntityEquipmentSlot slotIn, float chance) {
        super.setDropChance(slotIn, 0);
    }

    @Override
    public void setDropItemsWhenDead(boolean dropWhenDead) {
        super.setDropItemsWhenDead(false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(enderDragonZombieFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(enderDragonZombieMovementSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(enderDragonZombieAttackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(enderDragonZombieArmor);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(enderDragonZombieHealth);
        if (debugMode) {
            LogHelper.info("EnderDragon Zombie Follow Range: " + enderDragonZombieFollowRange);
            LogHelper.info("EnderDragon Zombie Movement Speed: " + enderDragonZombieMovementSpeed);
            LogHelper.info("EnderDragon Zombie Attack Damage: " + enderDragonZombieAttackDamage);
            LogHelper.info("EnderDragon Zombie Armor: " + enderDragonZombieArmor);
            LogHelper.info("EnderDragon Zombie Max Health: " + enderDragonZombieHealth);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED).booleanValue();
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(armsRaised));
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIEnderDragonZombieAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            // This zombie gives wither 4 when it attacks
            if (entityIn instanceof EntityLivingBase) if (enableEnderDragonZombieWithering)
                ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, enderDragonZombieWitheringEffectDuration, enderDragonZombieWitheringEffectLevel));
            return true;
        } else return false;
    }

    @Override
    @Nullable
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