package net.thedragonteam.armorplus.entity.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.thedragonteam.armorplus.entity.mobs.EntityEnderDragonZombie;

/**
 * Custom AI attack class to support raising of the arms when the zombie attacks.
 *
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityAIEnderDragonZombieAttack extends EntityAIAttackMelee {

    private EntityEnderDragonZombie enderDragonZombie;
    private int raiseArmTicks = 0;

    public EntityAIEnderDragonZombieAttack(EntityEnderDragonZombie enderDragonZombie, double speedIn, boolean longMemoryIn) {
        super(enderDragonZombie, speedIn, longMemoryIn);
        this.enderDragonZombie = enderDragonZombie;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.setRaiseArmTicks(0);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.enderDragonZombie.setArmsRaised(false);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        this.raiseArmTicks++;

        this.enderDragonZombie.setArmsRaised(this.raiseArmTicks >= 5 && this.attackTick < 10);
    }

    private void setRaiseArmTicks(int raiseArmTicks) {
        this.raiseArmTicks = raiseArmTicks;
    }
}