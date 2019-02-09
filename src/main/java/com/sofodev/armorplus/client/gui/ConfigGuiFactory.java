/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.util.TextUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

/**
 * @author Sokratis Fotkatzikis
 */
public class ConfigGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen, ArmorPlus.MODID, TextUtils.translatedText("gui.armorplus.config.title"));
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}