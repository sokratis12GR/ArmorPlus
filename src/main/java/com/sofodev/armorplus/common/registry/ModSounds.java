/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModSounds {

    public static final SoundEvent TRAP_TRIGGERED = ForgeRegistries.SOUND_EVENTS.getValue(setRL("trap_triggered"));
}
