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
import java.util.Arrays;
import java.util.List;

import static java.util.Locale.ROOT;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ArmorPlus.MODID, false, true,
                formattedText("gui." + ArmorPlus.MODID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<>();
        addCategories(list, "type", "items", "world", "mob_drops", "debug", "entities", "integrations");
        return list;
    }

    private static void addCategories(List<IConfigElement> list, String... categoryNames) {
        Arrays.stream(categoryNames).map(name -> new ConfigElement(configuration.getCategory(name.toLowerCase(ROOT)))).forEachOrdered(list::add);
    }
}