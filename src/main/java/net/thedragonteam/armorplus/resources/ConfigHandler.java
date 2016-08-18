/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.resources;

import java.io.File;

import static net.thedragonteam.armorplus.ArmorPlus.configuration;

public class ConfigHandler {

    public static void init(File file) {
        syncConfig();
    }

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }
}
