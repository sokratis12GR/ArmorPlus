package com.sofodev.armorplus.registry.entities.bosses.manager.phase;

import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.DamageSource;

public abstract class SittingPhase extends Phase {
    public SittingPhase(DemonicDragonEntity dragon) {
        super(dragon);
    }

    public boolean isSitting() {
        return true;
    }

    public float onHurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof AbstractArrowEntity) {
            source.getDirectEntity().setSecondsOnFire(1);
            return 0.0F;
        } else {
            return super.damageAmount(source, amount);
        }
    }
}
