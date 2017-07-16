/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Locale.ENGLISH;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ArmorPlus.MODID, false, true,
                formattedText("gui." + ArmorPlus.MODID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        String[] categoryNames = new String[]{
                "Weapons", "Armors", "GameModes", "Items", "WhiteList", "Blacklist", "WorkGeneration", "MobDrops", "Debug", "EnderDragonZombie", "TheGiftOfTheGods"
        };
        return Arrays.stream(categoryNames).map(name -> new ConfigElement(configuration.getCategory(name.toLowerCase(ENGLISH)))).collect(Collectors.toList());
    }
}