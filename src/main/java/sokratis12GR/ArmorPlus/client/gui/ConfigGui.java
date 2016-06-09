package sokratis12GR.ArmorPlus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ArmorPlus.MODID, false, true,
                TextHelper.localize("gui." + ArmorPlus.MODID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        /** adds sections declared in ConfigHandler. toLowerCase() is used because the configuration class automatically does this, so must we. */
        list.add(new ConfigElement(ConfigHandler.config.getCategory("Recipes".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("FlightAbility".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("EffectLevel".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("TinkersEffects".toLowerCase())));

        list.add(new ConfigElement(ConfigHandler.config.getCategory("CoalArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("LapisArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("RedstoneArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("EmeraldArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("ObsidianArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("LavaArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("SuperStarArmor".toLowerCase())));
        list.add(new ConfigElement(ConfigHandler.config.getCategory("GuardianArmor".toLowerCase())));

        list.add(new ConfigElement(ConfigHandler.config.getCategory("GameModes".toLowerCase())));
        return list;
    }
}