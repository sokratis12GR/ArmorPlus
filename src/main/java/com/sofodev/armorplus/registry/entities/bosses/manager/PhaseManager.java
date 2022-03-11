package com.sofodev.armorplus.registry.entities.bosses.manager;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;

public class PhaseManager {

    private final DemonicDragonEntity dragon;
    private final IPhase[] phases = new IPhase[PhaseType.getTotalPhases()];
    private IPhase phase;

    public PhaseManager(DemonicDragonEntity dragonIn) {
        this.dragon = dragonIn;
        this.setPhase(PhaseType.HOVER);
    }

    public void setPhase(PhaseType<?> phaseIn) {
        if (this.phase == null || phaseIn != this.phase.getPhase()) {
            if (this.phase != null) {
                this.phase.removeAreaEffect();
            }

            this.phase = this.getPhase(phaseIn);
            if (!this.dragon.level.isClientSide) {
                this.dragon.getEntityData().set(DemonicDragonEntity.PHASE, phaseIn.getId());
            }

            ArmorPlus.LOGGER.debug("Dragon is now in phase {} on the {}", phaseIn, this.dragon.level.isClientSide ? "client" : "server");
            this.phase.initPhase();
        }
    }

    public IPhase getCurrentPhase() {
        return this.phase;
    }

    public <T extends IPhase> T getPhase(PhaseType<T> phaseIn) {
        int i = phaseIn.getId();
        if (this.phases[i] == null) {
            this.phases[i] = phaseIn.createPhase(this.dragon);
        }

        return (T) this.phases[i];
    }
}
