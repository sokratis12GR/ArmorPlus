//package com.sofodev.armorplus.registry.entities.bosses.manager.phase;
//
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import net.minecraft.entity.projectile.AbstractArrow;
//import net.minecraft.world.damagesource.DamageSource;
//
//public abstract class SittingPhase extends Phase {
//    public SittingPhase(DemonicDragonEntity dragon) {
//        super(dragon);
//    }
//
//    public boolean isSitting() {
//        return true;
//    }
//
//    public float onHurt(DamageSource source, float amount) {
//        if (source.getDirectEntity() instanceof AbstractArrow) {
//            source.getDirectEntity().setSecondsOnFire(1);
//            return 0.0F;
//        } else {
//            return super.damageAmount(source, amount);
//        }
//    }
//}
