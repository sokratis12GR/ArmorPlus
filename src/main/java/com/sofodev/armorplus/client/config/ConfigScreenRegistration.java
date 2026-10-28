package com.sofodev.armorplus.client.config;

import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Client-only; kept out of the main mod class so the dedicated server never has to load GUI classes.
 */
public final class ConfigScreenRegistration {

    private ConfigScreenRegistration() {
    }

    public static void register(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class,
                (mod, parent) -> new ConfigurationScreen(mod, parent, APConfigSectionScreen::new));
    }
}
