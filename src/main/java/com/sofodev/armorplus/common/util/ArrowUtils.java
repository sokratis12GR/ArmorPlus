/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.util;

import com.sofodev.armorplus.common.registry.items.arrows.ArrowType;
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
import static com.sofodev.armorplus.common.util.TextUtils.translatedText;

/**
 * @author Sokratis Fotkatzikis
 **/
@OnlyIn(Dist.CLIENT)
public class ArrowUtils {

    public static void addArrowInformation(List<ITextComponent> tooltip, String effect, double damage, TextFormatting formatting) {
        KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (InputMappings.isKeyDown(keyBindSneak.getKey().getKeyCode())) {
            String arrowDesc = translatedText("item.armorplus.arrow.ability_desc", effect);
            String ability = translatedText("item.armorplus.arrow.ability", damage);
            tooltip.add(new TextComponentString(arrowDesc));
            tooltip.add(new TextComponentString(ability));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }
}