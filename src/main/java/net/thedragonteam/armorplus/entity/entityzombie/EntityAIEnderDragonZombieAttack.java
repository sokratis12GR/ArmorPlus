/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity.entityzombie;

/**
 * net.thedragonteam.armorplus.entity.entityzombie
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */

import net.minecraft.entity.ai.EntityAIAttackMelee;

/**
 * Custom AI attack class to support raising of the arms when the zombie attacks
 */
public class EntityAIEnderDragonZombieAttack extends EntityAIAttackMelee {
    private final EntityEnderDragonZombie enderDragonZombie;
    private int raiseArmTicks;

    public EntityAIEnderDragonZombieAttack(EntityEnderDragonZombie zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.enderDragonZombie = zombieIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        super.resetTask();
        this.enderDragonZombie.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.enderDragonZombie.setArmsRaised(true);
        } else {
            this.enderDragonZombie.setArmsRaised(false);
        }
    }
}