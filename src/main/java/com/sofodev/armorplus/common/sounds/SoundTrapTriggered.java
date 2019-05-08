/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.sounds;

import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.util.SoundEvent;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SoundTrapTriggered extends SoundEvent {

    public SoundTrapTriggered() {
        super(Utils.setRL("trap_triggered"));
        this.setRegistryName(Utils.setRL("trap_triggered"));
    }
}
