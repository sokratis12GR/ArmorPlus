/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.resources;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import static net.thedragonteam.armorplus.ArmorPlus.isNightVisionEnabled;
import static net.thedragonteam.armorplus.ArmorPlus.nightVisionEnabled;


public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBinds.nightVision.isPressed()) {
            if (isNightVisionEnabled()) {
                nightVisionEnabled = false;
            } else {
                nightVisionEnabled = true;
            }
        }
    }
}