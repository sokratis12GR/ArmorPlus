package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class HoverPhase extends Phase {
    private Vector3d targetLocation;

    public HoverPhase(DemonicDragonEntity dragonIn) {
        super(dragonIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void serverTick() {
        if (this.targetLocation == null) {
            this.targetLocation = this.dragon.getPositionVec();
        }

    }

    public boolean isSitting() {
        return true;
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase() {
        this.targetLocation = null;
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    public float getMaxRiseOrFall() {
        return 1.0F;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    public Vector3d getTargetLocation() {
        return this.targetLocation;
    }

    public PhaseType<HoverPhase> getPhase() {
        return PhaseType.HOVER;
    }
}
