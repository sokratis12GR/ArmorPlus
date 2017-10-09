/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.entityzombie

import net.minecraft.entity.ai.EntityAIAttackMelee

/**
 * Custom AI attack class to support raising of the arms when the zombie attacks.
 *
 * @author Sokratis Fotkatzikis - TheDragonTeam
*/
class EntityAIEnderDragonZombieAttack(private val enderDragonZombie: EntityEnderDragonZombie, speedIn: Double, longMemoryIn: Boolean) : EntityAIAttackMelee(enderDragonZombie, speedIn, longMemoryIn) {
    private var raiseArmTicks: Int = 0

    /**
     * Execute a one shot task or start executing a continuous task
     */
    override fun startExecuting() {
        super.startExecuting()
        this.raiseArmTicks = 0
    }

    /**
     * Resets the task
     */
    override fun resetTask() {
        super.resetTask()
        this.enderDragonZombie.isArmsRaised = false
    }

    /**
     * Updates the task
     */
    override fun updateTask() {
        super.updateTask()
        this.raiseArmTicks++

        this.enderDragonZombie.isArmsRaised = this.raiseArmTicks >= 5 && this.attackTick < 10
    }
}