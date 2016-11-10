/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.thedragonlib.util.TextHelper;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ArmorPlus.MODID, false, true,
                TextHelper.localize("gui." + ArmorPlus.MODID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        /* adds sections declared in ConfigHandler. toLowerCase() is used because the configuration class automatically does this, so must we. */
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("Weapons".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("Armors".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("GameModes".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("Items".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("WhiteList".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("BlackList".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("WorldGeneration".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("MobDrops".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("Debug".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("EnderDragonZombie".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("TheGiftOfTheGods".toLowerCase())));
        list.add(new ConfigElement(ArmorPlus.configuration.getCategory("EnergyItems".toLowerCase())));

        return list;
    }
}