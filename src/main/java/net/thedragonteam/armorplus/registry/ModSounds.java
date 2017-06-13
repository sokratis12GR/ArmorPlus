package net.thedragonteam.armorplus.registry;

import net.minecraft.util.SoundEvent;

import static net.thedragonteam.armorplus.util.Utils.setRL;

public class ModSounds {

    public static SoundEvent trap_triggered;

    public static void registerSounds() {
        trap_triggered = new SoundEvent(setRL("trap_triggered"));
    }
}
