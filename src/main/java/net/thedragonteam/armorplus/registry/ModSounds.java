package net.thedragonteam.armorplus.registry;

import net.minecraft.util.SoundEvent;
import net.thedragonteam.armorplus.util.Utils;

/**
 * Created by sokratis12GR on 5/10/2017.
 */
public class ModSounds {

    public static SoundEvent trap_triggered;

    public static void init() {
        trap_triggered = new SoundEvent(Utils.INSTANCE.setResourceLocation("trap_triggered"));
    }
}
