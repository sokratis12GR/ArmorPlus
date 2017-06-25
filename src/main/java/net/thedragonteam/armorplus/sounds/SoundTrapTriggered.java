package net.thedragonteam.armorplus.sounds;

import net.minecraft.util.SoundEvent;

import static net.thedragonteam.armorplus.util.Utils.setRL;

public class SoundTrapTriggered extends SoundEvent {

    public SoundTrapTriggered() {
        super(setRL("trap_triggered"));
        this.setRegistryName(setRL("trap_triggered"));
    }
}
