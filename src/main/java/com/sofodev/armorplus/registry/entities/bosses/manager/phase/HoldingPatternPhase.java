package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;

import javax.annotation.Nullable;

public class HoldingPatternPhase extends Phase {
    private static final EntityPredicate NEW_TARGET_TARGETING = (new EntityPredicate()).range(64.0D);
    private Path currentPath;
    private Vector3d targetLocation;
    private boolean clockwise;

    public HoldingPatternPhase(DemonicDragonEntity dragonIn) {
        super(dragonIn);
    }

    public PhaseType<HoldingPatternPhase> getPhase() {
        return PhaseType.HOLDING_PATTERN;
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void serverTick() {
        double d0 = this.targetLocation == null ? 0.0D : this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
        if (d0 < 100.0D || d0 > 22500.0D || this.dragon.horizontalCollision || this.dragon.verticalCollision) {
            this.findNewTarget();
        }

    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase() {
        this.currentPath = null;
        this.targetLocation = null;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    public Vector3d getTargetLocation() {
        return this.targetLocation;
    }

    private void findNewTarget() {
        if (this.currentPath != null && this.currentPath.isDone()) {
            BlockPos blockpos = this.dragon.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(EndPodiumFeature.END_PODIUM_LOCATION));
            int i = this.dragon.getDragonFight() == null ? 0 : this.dragon.getDragonFight().getNumAliveCrystals();
            if (this.dragon.getRandom().nextInt(i + 3) == 0) {
                this.dragon.getPhaseManager().setPhase(PhaseType.LANDING_APPROACH);
                return;
            }

            double d0 = 64.0D;
            PlayerEntity playerentity = this.dragon.level.getNearestPlayer(NEW_TARGET_TARGETING, (double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ());
            if (playerentity != null) {
                d0 = blockpos.distSqr(playerentity.position(), true) / 512.0D;
            }

            if (playerentity != null && !playerentity.abilities.invulnerable && (this.dragon.getRandom().nextInt(MathHelper.abs((int) d0) + 2) == 0 || this.dragon.getRandom().nextInt(i + 2) == 0)) {
                this.strafePlayer(playerentity);
                return;
            }
        }

        if (this.currentPath == null || this.currentPath.isDone()) {
            int j = this.dragon.findClosestNode();
            int k = j;
            if (this.dragon.getRandom().nextInt(8) == 0) {
                this.clockwise = !this.clockwise;
                k = j + 6;
            }

            if (this.clockwise) {
                ++k;
            } else {
                --k;
            }

            if (this.dragon.getDragonFight() != null && this.dragon.getDragonFight().getNumAliveCrystals() >= 0) {
                k = k % 12;
                if (k < 0) {
                    k += 12;
                }
            } else {
                k = k - 12;
                k = k & 7;
                k = k + 12;
            }

            this.currentPath = this.dragon.findPath(j, k, (PathPoint) null);
            if (this.currentPath != null) {
                this.currentPath.advance();
            }
        }

        this.navigateToNextPathNode();
    }

    private void strafePlayer(PlayerEntity player) {
        this.dragon.getPhaseManager().setPhase(PhaseType.STRAFE_PLAYER);
        this.dragon.getPhaseManager().getPhase(PhaseType.STRAFE_PLAYER).setTarget(player);
    }

    private void navigateToNextPathNode() {
        if (this.currentPath != null && !this.currentPath.isDone()) {
            Vector3i vector3i = this.currentPath.getNextNodePos();
            this.currentPath.advance();
            double d0 = (double) vector3i.getX();
            double d1 = (double) vector3i.getZ();

            double d2;
            do {
                d2 = (float) vector3i.getY() + this.dragon.getRandom().nextFloat() * 20.0F;
            } while (d2 < (double) vector3i.getY());

            this.targetLocation = new Vector3d(d0, d2, d1);
        }
    }
}
