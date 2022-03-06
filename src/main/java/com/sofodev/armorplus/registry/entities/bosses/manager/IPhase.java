//package com.sofodev.armorplus.registry.entities.bosses.manager;
//
//import Vector3d;
//import net.minecraft.world.damagesource.DamageSource;
//
//import javax.annotation.Nullable;
//
//public interface IPhase {
//    boolean isSitting();
//
//    /**
//     * Generates particle effects appropriate to the phase (or sometimes sounds).
//     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
//     */
//    void clientTick();
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    void serverTick();
//
//    /**
//     * Called when this phase is set to active
//     */
//    void initPhase();
//
//    void removeAreaEffect();
//
//    /**
//     * Returns the maximum amount dragon may rise or fall during this phase
//     */
//    float getMaxRiseOrFall();
//
//    float getYawFactor();
//
//    PhaseType<? extends IPhase> getPhase();
//
//    /**
//     * Returns the location the dragon is flying toward
//     */
//    @Nullable
//    Vector3d getTargetLocation();
//
//    float damageAmount(DamageSource source, float amount);
//}
