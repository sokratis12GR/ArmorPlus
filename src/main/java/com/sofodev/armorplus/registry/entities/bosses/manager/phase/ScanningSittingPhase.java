//package com.sofodev.armorplus.registry.entities.bosses.manager.phase;
//
//import EntityPredicate;
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import com.sofodev.armorplus.registry.entities.bosses.manager.PhaseType;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.world.entity.LivingEntity;
//
//public class ScanningSittingPhase extends SittingPhase {
//    private static final EntityPredicate CHARGE_TARGETING = (new EntityPredicate()).range(150.0D);
//    private final EntityPredicate scanTargeting;
//    private int scanningTime;
//
//    public ScanningSittingPhase(DemonicDragonEntity dragonIn) {
//        super(dragonIn);
//        this.scanTargeting = (new EntityPredicate()).range(20.0D).selector((p_221114_1_) -> {
//            return Math.abs(p_221114_1_.getY() - dragonIn.getY()) <= 10.0D;
//        });
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void serverTick() {
//        ++this.scanningTime;
//        LivingEntity livingentity = this.dragon.level.getNearestPlayer(this.scanTargeting, this.dragon, this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
//        if (livingentity != null) {
//            if (this.scanningTime > 25) {
//                this.dragon.getPhaseManager().setPhase(PhaseType.SITTING_ATTACKING);
//            } else {
//                Vector3d vector3d = (new Vector3d(livingentity.getX() - this.dragon.getX(), 0.0D, livingentity.getZ() - this.dragon.getZ())).normalize();
//                Vector3d vector3d1 = (new Vector3d((double) MathHelper.sin(this.dragon.rotationYaw * ((float) Math.PI / 180F)), 0.0D, (double) (-MathHelper.cos(this.dragon.rotationYaw * ((float) Math.PI / 180F))))).normalize();
//                float f = (float) vector3d1.dot(vector3d);
//                float f1 = (float) (Math.acos((double) f) * (double) (180F / (float) Math.PI)) + 0.5F;
//                if (f1 < 0.0F || f1 > 10.0F) {
//                    double d0 = livingentity.getX() - this.dragon.getX();
//                    double d1 = livingentity.getZ() - this.dragon.getZ();
//                    double d2 = MathHelper.clamp(MathHelper.wrapDegrees(180.0D - MathHelper.atan2(d0, d1) * (double) (180F / (float) Math.PI) - (double) this.dragon.rotationYaw), -100.0D, 100.0D);
//                    this.dragon.rotationYaw *= 0.8F;
//                    float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) + 1.0F;
//                    float f3 = f2;
//                    if (f2 > 40.0F) {
//                        f2 = 40.0F;
//                    }
//
//                    this.dragon.rotationYaw = (float) ((double) this.dragon.rotationYaw + d2 * (double) (0.7F / f2 / f3));
//                    this.dragon.rotationYaw += this.dragon.rotationYaw;
//                }
//            }
//        } else if (this.scanningTime >= 100) {
//            livingentity = this.dragon.level.getNearestPlayer(CHARGE_TARGETING, this.dragon, this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
//            this.dragon.getPhaseManager().setPhase(PhaseType.TAKEOFF);
//            if (livingentity != null) {
//                this.dragon.getPhaseManager().setPhase(PhaseType.CHARGING_PLAYER);
//                this.dragon.getPhaseManager().getPhase(PhaseType.CHARGING_PLAYER).setTarget(new Vector3d(livingentity.getX(), livingentity.getY(), livingentity.getZ()));
//            }
//        }
//
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase() {
//        this.scanningTime = 0;
//    }
//
//    public PhaseType<ScanningSittingPhase> getPhase() {
//        return PhaseType.SITTING_SCANNING;
//    }
//}
