package sokratis12gr.armorplus.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.util.TextHelper;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ArmorPlus.MODID, false, true,
                TextHelper.localize("gui." + ArmorPlus.MODID + ".config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();


        return list;
    }
}