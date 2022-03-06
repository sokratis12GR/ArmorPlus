//package com.sofodev.armorplus.registry.entities.bosses.manager.phase;
//
//import AreaEffectCloudEntity;
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.potion.Effects;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.world.effect.MobEffectInstance;
//
//public class FlamingSittingPhase extends SittingPhase {
//    private int flameTicks;
//    private int flameCount;
//    private AreaEffectCloudEntity areaEffectCloud;
//
//    public FlamingSittingPhase(DemonicDragonEntity dragonIn) {
//        super(dragonIn);
//    }
//
//    /**
//     * Generates particle effects appropriate to the phase (or sometimes sounds).
//     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
//     */
//    public void clientTick() {
//        ++this.flameTicks;
//        if (this.flameTicks % 2 == 0 && this.flameTicks < 10) {
//            Vector3d vector3d = this.dragon.getUpVector(1.0F).normalize();
//            vector3d.yRot((-(float) Math.PI / 4F));
//            double d0 = this.dragon.getX();
//            double d1 = this.dragon.getY(0.5D);
//            double d2 = this.dragon.getZ();
//
//            for (int i = 0; i < 8; ++i) {
//                double d3 = d0 + this.dragon.getRandom().nextGaussian() / 2.0D;
//                double d4 = d1 + this.dragon.getRandom().nextGaussian() / 2.0D;
//                double d5 = d2 + this.dragon.getRandom().nextGaussian() / 2.0D;
//
//                for (int j = 0; j < 6; ++j) {
//                    this.dragon.level.addParticle(ParticleTypes.DRAGON_BREATH, d3, d4, d5, -vector3d.x * (double) 0.08F * (double) j, -vector3d.y * (double) 0.6F, -vector3d.z * (double) 0.08F * (double) j);
//                }
//
//                vector3d.yRot(0.19634955F);
//            }
//        }
//
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void serverTick() {
//        ++this.flameTicks;
//        if (this.flameTicks >= 200) {
//            if (this.flameCount >= 4) {
//                this.dragon.getPhaseManager().setPhase(PhaseType.TAKEOFF);
//            } else {
//                this.dragon.getPhaseManager().setPhase(PhaseType.SITTING_SCANNING);
//            }
//        } else if (this.flameTicks == 10) {
//            Vector3d vector3d = (new Vector3d(0.0, 0.0D, 0.0)).normalize();
//            float f = 5.0F;
//            double d0 = this.dragon.getX() + vector3d.x * 5.0D / 2.0D;
//            double d1 = this.dragon.getZ() + vector3d.z * 5.0D / 2.0D;
//            double d2 = this.dragon.getY(0.5D);
//            double d3 = d2;
//            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(d0, d2, d1);
//
//            while (this.dragon.level.isEmptyBlock(blockpos$mutable)) {
//                --d3;
//                if (d3 < 0.0D) {
//                    d3 = d2;
//                    break;
//                }
//
//                blockpos$mutable.set(d0, d3, d1);
//            }
//
//            d3 = (double) (MathHelper.floor(d3) + 1);
//            this.areaEffectCloud = new AreaEffectCloudEntity(this.dragon.level, d0, d3, d1);
//            this.areaEffectCloud.setOwner(this.dragon);
//            this.areaEffectCloud.setRadius(5.0F);
//            this.areaEffectCloud.setDuration(200);
//            this.areaEffectCloud.setParticle(ParticleTypes.DRAGON_BREATH);
//            this.areaEffectCloud.addEffect(new MobEffectInstance(Effects.HARM));
//            this.dragon.level.addFreshEntity(this.areaEffectCloud);
//        }
//
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase() {
//        this.flameTicks = 0;
//        ++this.flameCount;
//    }
//
//    public void removeAreaEffect() {
//        if (this.areaEffectCloud != null) {
//            this.areaEffectCloud.remove();
//            this.areaEffectCloud = null;
//        }
//
//    }
//
//    public PhaseType<FlamingSittingPhase> getPhase() {
//        return PhaseType.SITTING_FLAMING;
//    }
//
//    public void resetFlameCount() {
//        this.flameCount = 0;
//    }
//}
