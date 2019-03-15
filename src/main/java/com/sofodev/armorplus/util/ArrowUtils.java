/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.util.TextUtils.translatedText;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ArrowUtils {

    @OnlyIn(Dist.CLIENT)
    public static void addArrowInformation(List<ITextComponent> tooltip, String effect, double damage, TextFormatting formatting) {
        KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (InputMappings.isKeyDown(keyBindSneak.getKey().getKeyCode())) {
            tooltip.add(new TextComponentString(translatedText("item.armorplus.arrow.ability_desc", effect)));
            tooltip.add(new TextComponentString(translatedText("item.armorplus.arrow.ability", damage)));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }
}