/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.resources;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBinds {
    public static KeyBinding nightVision = new KeyBinding("key.nightVision", Keyboard.KEY_N, "key.categories.armorplus");

    public static void register() {
        ClientRegistry.registerKeyBinding(nightVision);
    }
}