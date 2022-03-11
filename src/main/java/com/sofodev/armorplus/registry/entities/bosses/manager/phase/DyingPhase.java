package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;

import javax.annotation.Nullable;

public class DyingPhase extends Phase {
    private Vector3d targetLocation;
    private int time;

    public DyingPhase(DemonicDragonEntity dragonIn) {
        super(dragonIn);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when levelObj.isRemote.
     */
    public void clientTick() {
        if (this.time++ % 10 == 0) {
            float f = (this.dragon.getRandom().nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.dragon.getRandom().nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.dragon.getRandom().nextFloat() - 0.5F) * 8.0F;
            this.dragon.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.dragon.getX() + (double) f, this.dragon.getY() + 2.0D + (double) f1, this.dragon.getZ() + (double) f2, 0.0D, 0.0D, 0.0D);
        }

    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !levelObj.isRemote.
     */
    public void serverTick() {
        ++this.time;
        if (this.targetLocation == null) {
            BlockPos blockpos = this.dragon.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION);
            this.targetLocation = Vector3d.atCenterOf(blockpos);
        }

        double d0 = this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
        if (!(d0 < 100.0D) && !(d0 > 22500.0D) && !this.dragon.horizontalCollision && !this.dragon.verticalCollision) {
            this.dragon.setHealth(1.0F);
        } else {
            this.dragon.setHealth(0.0F);
        }

    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase() {
        this.targetLocation = null;
        this.time = 0;
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    public float getMaxRiseOrFall() {
        return 3.0F;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    public Vector3d getTargetLocation() {
        return this.targetLocation;
    }

    public PhaseType<DyingPhase> getPhase() {
        return PhaseType.DYING;
    }
}
