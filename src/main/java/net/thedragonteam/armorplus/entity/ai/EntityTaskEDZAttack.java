package net.thedragonteam.armorplus.entity.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.thedragonteam.armorplus.entity.mobs.EntityEnderDragonZombie;

/**
 * Custom AI attack class to support raising of the arms when the zombie attacks.
 *
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityTaskEDZAttack extends EntityAIAttackMelee {

    private EntityEnderDragonZombie entity;
    private int raiseArmTicks = 0;

    public EntityTaskEDZAttack(EntityEnderDragonZombie entity, double speedIn, boolean longMemoryIn) {
        super(entity, speedIn, longMemoryIn);
        this.entity = entity;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.setRaiseArmTicks(0);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.entity.setArmsRaised(false);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        this.raiseArmTicks++;

        this.entity.setArmsRaised(this.raiseArmTicks >= 5 && this.attackTick < 10);
    }

    private void setRaiseArmTicks(int raiseArmTicks) {
        this.raiseArmTicks = raiseArmTicks;
    }
}