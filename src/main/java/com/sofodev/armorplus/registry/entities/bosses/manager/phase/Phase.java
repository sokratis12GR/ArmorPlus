package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.manager.IPhase;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public abstract class Phase implements IPhase {
    protected final DemonicDragonEntity dragon;

    public Phase(DemonicDragonEntity dragonIn) {
        this.dragon = dragonIn;
    }

    public boolean isSitting() {
        return false;
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void clientTick() {
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void serverTick() {
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase() {
    }

    public void removeAreaEffect() {
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    public float getMaxRiseOrFall() {
        return 0.6F;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    public Vector3d getTargetLocation() {
        return null;
    }

    public float damageAmount(DamageSource source, float amount) {
        return amount;
    }

    public float getYawFactor() {
        float f = MathHelper.sqrt(Entity.getHorizontalDistanceSqr(this.dragon.getDeltaMovement())) + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return 0.7F / f1 / f;
    }
}
