/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.tinkers;

import com.sofodev.armorplus.compat.tinkers.modifiers.TiCModifiers;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;

/**
 * @author Sokratis Fotkatzikis
 */
@Mod.EventBusSubscriber(modid = "tconstruct")
public class TinkersConstructEventHandler {

    @SubscribeEvent
    public static void onModifierRegister(TinkerRegisterEvent.ModifierRegisterEvent event) {
        new TinkerRegisterEvent.ModifierRegisterEvent(TiCModifiers.theUltimateMaterial);
        new TinkerRegisterEvent.ModifierRegisterEvent(TiCModifiers.infusedLavaCrystalModifier);
    }
}
