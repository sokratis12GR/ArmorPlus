package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
import net.minecraft.util.SoundEvents;

public class AttackingSittingPhase extends SittingPhase {
    private int attackingTicks;

    public AttackingSittingPhase(DemonicDragonEntity dragonIn) {
        super(dragonIn);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void clientTick() {
        this.dragon.level.playLocalSound(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ(), SoundEvents.ENDER_DRAGON_GROWL, this.dragon.getSoundSource(), 2.5F, 0.8F + this.dragon.getRandom().nextFloat() * 0.3F, false);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void serverTick() {
        if (this.attackingTicks++ >= 40) {
            this.dragon.getPhaseManager().setPhase(PhaseType.SITTING_FLAMING);
        }

    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase() {
        this.attackingTicks = 0;
    }

    public PhaseType<AttackingSittingPhase> getPhase() {
        return PhaseType.SITTING_ATTACKING;
    }
}
