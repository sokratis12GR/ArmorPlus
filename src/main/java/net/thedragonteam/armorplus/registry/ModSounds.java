package net.thedragonteam.armorplus.registry;

import net.minecraft.util.SoundEvent;

import static net.thedragonteam.armorplus.util.Utils.setResourceLocation;

public class ModSounds {

    public static SoundEvent trap_triggered;

    public static void init() {
        trap_triggered = new SoundEvent(setResourceLocation("trap_triggered"));
    }
}
