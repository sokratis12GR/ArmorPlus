//package com.sofodev.armorplus.registry.entities.bosses.manager.phase;
//
//import Vector3d;
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.annotation.Nullable;
//
//public class ChargingPlayerPhase extends Phase {
//    private static final Logger LOGGER = LogManager.getLogger();
//    private Vector3d targetLocation;
//    private int timeSinceCharge;
//
//    public ChargingPlayerPhase(DemonicDragonEntity dragonIn) {
//        super(dragonIn);
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void serverTick() {
//        if (this.targetLocation == null) {
//            LOGGER.warn("Aborting charge player as no target was set.");
//            this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
//        } else if (this.timeSinceCharge > 0 && this.timeSinceCharge++ >= 10) {
//            this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
//        } else {
//            double d0 = this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
//            if (d0 < 100.0D || d0 > 22500.0D || this.dragon.horizontalCollision || this.dragon.verticalCollision) {
//                ++this.timeSinceCharge;
//            }
//
//        }
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase() {
//        this.targetLocation = null;
//        this.timeSinceCharge = 0;
//    }
//
//    public void setTarget(Vector3d p_188668_1_) {
//        this.targetLocation = p_188668_1_;
//    }
//
//    /**
//     * Returns the maximum amount dragon may rise or fall during this phase
//     */
//    public float getMaxRiseOrFall() {
//        return 3.0F;
//    }
//
//    /**
//     * Returns the location the dragon is flying toward
//     */
//    @Nullable
//    public Vector3d getTargetLocation() {
//        return this.targetLocation;
//    }
//
//    public PhaseType<ChargingPlayerPhase> getPhase() {
//        return PhaseType.CHARGING_PLAYER;
//    }
//}
