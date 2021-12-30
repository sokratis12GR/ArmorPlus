//package com.sofodev.armorplus.registry.entities.bosses.manager.phase;
//
//import Vector3d;
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.feature.EndPodiumFeature;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//
//public class LandingPhase extends Phase {
//    private Vector3d targetLocation;
//
//    public LandingPhase(DemonicDragonEntity dragonIn) {
//        super(dragonIn);
//    }
//
//    /**
//     * Generates particle effects appropriate to the phase (or sometimes sounds).
//     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
//     */
//    public void clientTick() {
//        Vector3d vector3d = this.dragon.getUpVector(1.0F).normalize();
//        vector3d.yRot((-(float) Math.PI / 4F));
//        double d0 = this.dragon.getX();
//        double d1 = this.dragon.getY(0.5D);
//        double d2 = this.dragon.getZ();
//
//        for (int i = 0; i < 8; ++i) {
//            Random random = this.dragon.getRandom();
//            double d3 = d0 + random.nextGaussian() / 2.0D;
//            double d4 = d1 + random.nextGaussian() / 2.0D;
//            double d5 = d2 + random.nextGaussian() / 2.0D;
//            Vector3d vector3d1 = this.dragon.getDeltaMovement();
//            this.dragon.level.addParticle(ParticleTypes.DRAGON_BREATH, d3, d4, d5, -vector3d.x * (double) 0.08F + vector3d1.x, -vector3d.y * (double) 0.3F + vector3d1.y, -vector3d.z * (double) 0.08F + vector3d1.z);
//            vector3d.yRot(0.19634955F);
//        }
//
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void serverTick() {
//        if (this.targetLocation == null) {
//            this.targetLocation = Vector3d.atBottomCenterOf(this.dragon.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION));
//        }
//
//        if (this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ()) < 1.0D) {
//            this.dragon.getPhaseManager().getPhase(PhaseType.SITTING_FLAMING).resetFlameCount();
//            this.dragon.getPhaseManager().setPhase(PhaseType.SITTING_SCANNING);
//        }
//
//    }
//
//    /**
//     * Returns the maximum amount dragon may rise or fall during this phase
//     */
//    public float getMaxRiseOrFall() {
//        return 1.5F;
//    }
//
//    public float getYawFactor() {
//        float f = MathHelper.sqrt(Entity.getHorizontalDistanceSqr(this.dragon.getDeltaMovement())) + 1.0F;
//        float f1 = Math.min(f, 40.0F);
//        return f1 / f;
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase() {
//        this.targetLocation = null;
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
//    public PhaseType<LandingPhase> getPhase() {
//        return PhaseType.LANDING;
//    }
//}
