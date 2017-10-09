/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.entityzombie

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityIronGolem
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.monster.EntityPigZombie
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.registry.APItems
import net.thedragonteam.thedragonlib.util.LogHelper

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
class EntityEnderDragonZombie(worldIn: World) : EntityMob(worldIn) {

    init {
        setSize(0.6f, 1.95f)
        setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack(APItems.enderDragonHelmet))
        setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStack(APItems.enderDragonChestplate))
        setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStack(APItems.enderDragonLeggings))
        setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStack(APItems.enderDragonBoots))
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack(APItems.enderDragonBattleAxe))
        dropEquipment(false, 0)
    }

    override fun entityInit() {
        super.entityInit()
        this.getDataManager().register(ARMS_RAISED, java.lang.Boolean.FALSE)
    }

    override fun setItemStackToSlot(slotIn: EntityEquipmentSlot, stack: ItemStack) {
        super.setItemStackToSlot(slotIn, stack)
    }

    override fun dropEquipment(wasRecentlyHit: Boolean, lootingModifier: Int) {
        super.dropEquipment(false, lootingModifier)
    }

    override fun setDropChance(slotIn: EntityEquipmentSlot, chance: Float) {
        super.setDropChance(slotIn, 0f)
    }

    override fun setDropItemsWhenDead(dropWhenDead: Boolean) {
        super.setDropItemsWhenDead(false)
    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).baseValue = enderDragonZombieFollowRange
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = enderDragonZombieMovementSpeed
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = enderDragonZombieAttackDamage
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).baseValue = enderDragonZombieArmor
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = enderDragonZombieHealth
        if (debugMode) {
            LogHelper.info("EnderDragon Zombie Follow Range: " + enderDragonZombieFollowRange)
            LogHelper.info("EnderDragon Zombie Movement Speed: " + enderDragonZombieMovementSpeed)
            LogHelper.info("EnderDragon Zombie Attack Damage: " + enderDragonZombieAttackDamage)
            LogHelper.info("EnderDragon Zombie Armor: " + enderDragonZombieArmor)
            LogHelper.info("EnderDragon Zombie Max Health: " + enderDragonZombieHealth)
        }
    }

    var isArmsRaised: Boolean
        @SideOnly(Side.CLIENT)
        get() = this.getDataManager().get(ARMS_RAISED)
        set(armsRaised) = this.getDataManager().set(ARMS_RAISED, armsRaised)

    override fun initEntityAI() {
        this.tasks.addTask(0, EntityAISwimming(this))
        this.tasks.addTask(2, EntityAIEnderDragonZombieAttack(this, 1.0, false))
        this.tasks.addTask(5, EntityAIMoveTowardsRestriction(this, 1.0))
        this.tasks.addTask(7, EntityAIWander(this, 1.0))
        this.tasks.addTask(8, EntityAIWatchClosest(this, EntityPlayer::class.java, 8.0f))
        this.tasks.addTask(8, EntityAILookIdle(this))
        this.applyEntityAI()
    }

    private fun applyEntityAI() {
        this.tasks.addTask(6, EntityAIMoveThroughVillage(this, 1.0, false))
        this.targetTasks.addTask(1, EntityAIHurtByTarget(this, true, EntityPigZombie::class.java))
        this.targetTasks.addTask(2, EntityAINearestAttackableTarget(this, EntityPlayer::class.java, true))
        this.targetTasks.addTask(3, EntityAINearestAttackableTarget(this, EntityVillager::class.java, false))
        this.targetTasks.addTask(3, EntityAINearestAttackableTarget(this, EntityIronGolem::class.java, true))
    }

    override fun attackEntityAsMob(entityIn: Entity): Boolean {
        if (super.attackEntityAsMob(entityIn)) {
            // This zombie gives wither 4 when it attacks
            if (entityIn is EntityLivingBase && enableEnderDragonZombieWithering)
                entityIn.addPotionEffect(PotionEffect(MobEffects.WITHER, enderDragonZombieWitheringEffectDuration, enderDragonZombieWitheringEffectLevel))
            return true
        }
        return false
    }

    override fun getLootTable(): ResourceLocation? {
        return LOOT
    }

    override fun isValidLightLevel(): Boolean {
        return true
    }

    override fun getMaxSpawnedInChunk(): Int {
        return 1
    }

    companion object {
        val LOOT = ResourceLocation(ArmorPlus.MODID, "entities/ender_dragon_zombie")
        // We reuse the zombie model which has arms that need to be raised when the zombie is attacking:
        private val ARMS_RAISED = EntityDataManager.createKey(EntityEnderDragonZombie::class.java, DataSerializers.BOOLEAN)
    }
}