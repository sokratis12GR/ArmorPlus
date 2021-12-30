//package com.sofodev.armorplus.registry.entities.bosses.manager.phase;
//
//import EntityPredicate;
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
//import net.minecraft.pathfinding.Path;
//import net.minecraft.pathfinding.PathPoint;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.util.math.vector.Vector3i;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.feature.EndPodiumFeature;
//
//import javax.annotation.Nullable;
//
//public class LandingApproachPhase extends Phase {
//    private static final EntityPredicate NEAR_EGG_TARGETING = (new EntityPredicate()).range(128.0D);
//    private Path currentPath;
//    private Vector3d targetLocation;
//
//    public LandingApproachPhase(DemonicDragonEntity dragonIn) {
//        super(dragonIn);
//    }
//
//    public PhaseType<LandingApproachPhase> getPhase() {
//        return PhaseType.LANDING_APPROACH;
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase() {
//        this.currentPath = null;
//        this.targetLocation = null;
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void serverTick() {
//        double d0 = this.targetLocation == null ? 0.0D : this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
//        if (d0 < 100.0D || d0 > 22500.0D || this.dragon.horizontalCollision || this.dragon.verticalCollision) {
//            this.findNewTarget();
//        }
//
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
//    private void findNewTarget() {
//        if (this.currentPath == null || this.currentPath.isDone()) {
//            int i = this.dragon.findClosestNode();
//            BlockPos blockpos = this.dragon.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
//            Player Player = this.dragon.level.getNearestPlayer(NEAR_EGG_TARGETING, (double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ());
//            int j;
//            if (Player != null) {
//                Vector3d vector3d = (new Vector3d(Player.getX(), 0.0D, Player.getZ())).normalize();
//                j = this.dragon.findClosestNode(-vector3d.x * 40.0D, 105.0D, -vector3d.z * 40.0D);
//            } else {
//                j = this.dragon.findClosestNode(40.0D, (double) blockpos.getY(), 0.0D);
//            }
//
//            PathPoint pathpoint = new PathPoint(blockpos.getX(), blockpos.getY(), blockpos.getZ());
//            this.currentPath = this.dragon.findPath(i, j, pathpoint);
//            if (this.currentPath != null) {
//                this.currentPath.advance();
//            }
//        }
//
//        this.navigateToNextPathNode();
//        if (this.currentPath != null && this.currentPath.isDone()) {
//            this.dragon.getPhaseManager().setPhase(PhaseType.LANDING);
//        }
//
//    }
//
//    private void navigateToNextPathNode() {
//        if (this.currentPath != null && !this.currentPath.isDone()) {
//            Vector3i vector3i = this.currentPath.getNextNodePos();
//            this.currentPath.advance();
//            double d0 = (double) vector3i.getX();
//            double d1 = (double) vector3i.getZ();
//
//            double d2;
//            do {
//                d2 = (double) ((float) vector3i.getY() + this.dragon.getRandom().nextFloat() * 20.0F);
//            } while (d2 < (double) vector3i.getY());
//
//            this.targetLocation = new Vector3d(d0, d2, d1);
//        }
//
//    }
//}
