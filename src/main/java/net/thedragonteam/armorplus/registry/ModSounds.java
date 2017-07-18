package net.thedragonteam.armorplus.registry;

import net.minecraft.util.SoundEvent;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * Created by sokratis12GR on 5/10/2017.
 */
public class ModSounds {

    public static SoundEvent trap_triggered;

    public static void init() {
        trap_triggered = new SoundEvent(setRL("trap_triggered"));
    }
}
