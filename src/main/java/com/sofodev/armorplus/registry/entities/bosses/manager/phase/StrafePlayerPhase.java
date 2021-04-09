package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class StrafePlayerPhase extends Phase {
    private static final Logger LOGGER = LogManager.getLogger();
    private int fireballCharge;
    private Path currentPath;
    private Vector3d targetLocation;
    private LivingEntity attackTarget;
    private boolean holdingPatternClockwise;

    public StrafePlayerPhase(DemonicDragonEntity dragonIn) {
        super(dragonIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void serverTick() {
        if (this.attackTarget == null) {
            LOGGER.warn("Skipping player strafe phase because no player was found");
            this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
        } else {
            if (this.currentPath != null && this.currentPath.isDone()) {
                double d0 = this.attackTarget.getX();
                double d1 = this.attackTarget.getZ();
                double d2 = d0 - this.dragon.getX();
                double d3 = d1 - this.dragon.getZ();
                double d4 = (double) MathHelper.sqrt(d2 * d2 + d3 * d3);
                double d5 = Math.min((double) 0.4F + d4 / 80.0D - 1.0D, 10.0D);
                this.targetLocation = new Vector3d(d0, this.attackTarget.getY() + d5, d1);
            }

            double d12 = this.targetLocation == null ? 0.0D : this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
            if (d12 < 100.0D || d12 > 22500.0D) {
                this.findNewTarget();
            }

            double d13 = 64.0D;
            if (this.attackTarget.distanceToSqr(this.dragon) < 4096.0D) {
                if (this.dragon.canSee(this.attackTarget)) {
                    ++this.fireballCharge;
                    Vector3d vector3d1 = (new Vector3d(this.attackTarget.getX() - this.dragon.getX(), 0.0D, this.attackTarget.getZ() - this.dragon.getZ())).normalize();
                    Vector3d vector3d = (new Vector3d((double) MathHelper.sin(this.dragon.rotationYaw * ((float) Math.PI / 180F)), 0.0D, (double) (-MathHelper.cos(this.dragon.rotationYaw * ((float) Math.PI / 180F))))).normalize();
                    float f1 = (float) vector3d.dot(vector3d1);
                    float f = (float) (Math.acos((double) f1) * (double) (180F / (float) Math.PI));
                    f = f + 0.5F;
                    if (this.fireballCharge >= 5 && f >= 0.0F && f < 10.0F) {
                        double d14 = 1.0D;
                        Vector3d vector3d2 = this.dragon.getViewVector(1.0F);
                        double d6 = this.dragon.getX() - vector3d2.x;
                        double d7 = this.dragon.getY(0.5D) + 0.5D;
                        double d8 = this.dragon.getZ() - vector3d2.z;
                        double d9 = this.attackTarget.getX() - d6;
                        double d10 = this.attackTarget.getY(0.5D) - d7;
                        double d11 = this.attackTarget.getZ() - d8;
                        if (!this.dragon.isSilent()) {
                            this.dragon.level.levelEvent((PlayerEntity) null, 1017, this.dragon.blockPosition(), 0);
                        }

                        DragonFireballEntity dragonfireballentity = new DragonFireballEntity(this.dragon.level, this.dragon, d9, d10, d11);
                        dragonfireballentity.moveTo(d6, d7, d8, 0.0F, 0.0F);
                        this.dragon.level.addFreshEntity(dragonfireballentity);
                        this.fireballCharge = 0;
                        if (this.currentPath != null) {
                            while (!this.currentPath.isDone()) {
                                this.currentPath.advance();
                            }
                        }

                        this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
                    }
                } else if (this.fireballCharge > 0) {
                    --this.fireballCharge;
                }
            } else if (this.fireballCharge > 0) {
                --this.fireballCharge;
            }

        }
    }

    private void findNewTarget() {
        if (this.currentPath == null || this.currentPath.isDone()) {
            int i = this.dragon.findClosestNode();
            int j = i;
            if (this.dragon.getRandom().nextInt(8) == 0) {
                this.holdingPatternClockwise = !this.holdingPatternClockwise;
                j = i + 6;
            }

            if (this.holdingPatternClockwise) {
                ++j;
            } else {
                --j;
            }

            if (this.dragon.getDragonFight() != null && this.dragon.getDragonFight().getNumAliveCrystals() > 0) {
                j = j % 12;
                if (j < 0) {
                    j += 12;
                }
            } else {
                j = j - 12;
                j = j & 7;
                j = j + 12;
            }

            this.currentPath = this.dragon.findPath(i, j, (PathPoint) null);
            if (this.currentPath != null) {
                this.currentPath.advance();
            }
        }

        this.navigateToNextPathNode();
    }

    private void navigateToNextPathNode() {
        if (this.currentPath != null && !this.currentPath.isDone()) {
            Vector3i vector3i = this.currentPath.getNextNodePos();
            this.currentPath.advance();
            double d0 = (double) vector3i.getX();
            double d2 = (double) vector3i.getZ();

            double d1;
            do {
                d1 = (double) ((float) vector3i.getY() + this.dragon.getRandom().nextFloat() * 20.0F);
            } while (d1 < (double) vector3i.getY());

            this.targetLocation = new Vector3d(d0, d1, d2);
        }

    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase() {
        this.fireballCharge = 0;
        this.targetLocation = null;
        this.currentPath = null;
        this.attackTarget = null;
    }

    public void setTarget(LivingEntity p_188686_1_) {
        this.attackTarget = p_188686_1_;
        int i = this.dragon.findClosestNode();
        int j = this.dragon.findClosestNode(this.attackTarget.getX(), this.attackTarget.getY(), this.attackTarget.getZ());
        int k = MathHelper.floor(this.attackTarget.getX());
        int l = MathHelper.floor(this.attackTarget.getZ());
        double d0 = (double) k - this.dragon.getX();
        double d1 = (double) l - this.dragon.getZ();
        double d2 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1);
        double d3 = Math.min((double) 0.4F + d2 / 80.0D - 1.0D, 10.0D);
        int i1 = MathHelper.floor(this.attackTarget.getY() + d3);
        PathPoint pathpoint = new PathPoint(k, i1, l);
        this.currentPath = this.dragon.findPath(i, j, pathpoint);
        if (this.currentPath != null) {
            this.currentPath.advance();
            this.navigateToNextPathNode();
        }

    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    public Vector3d getTargetLocation() {
        return this.targetLocation;
    }

    public PhaseType<StrafePlayerPhase> getPhase() {
        return PhaseType.STRAFE_PLAYER;
    }
}
