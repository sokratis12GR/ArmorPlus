/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ArmorPlus.MODID, false, true,
                formattedText("gui." + ArmorPlus.MODID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<>();

        /* adds sections declared in ConfigHandler. toLowerCase() is used because the configuration class automatically does this, so must we. */
        list.add(new ConfigElement(configuration.getCategory("Weapons".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("Armors".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("GameModes".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("Items".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("WhiteList".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("BlackList".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("WorldGeneration".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("MobDrops".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("Debug".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("EnderDragonZombie".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("TheGiftOfTheGods".toLowerCase(Locale.ENGLISH))));
        list.add(new ConfigElement(configuration.getCategory("EnergyItems".toLowerCase(Locale.ENGLISH))));
        return list;
    }
}