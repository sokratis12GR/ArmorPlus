/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

import static net.minecraft.util.text.TextFormatting.fromColorIndex;
import static net.minecraft.util.text.TextFormatting.getValueByName;

public class EnumHelperUtil {

    public static EnumRarity addRarity(String enumName, TextFormatting formatting, String displayName) {
        return EnumHelper.addRarity(enumName, formatting, displayName);
    }

    public static EnumRarity addRarity(String enumName, String formattingName, String displayName) {
        return addRarity(enumName, getValueByName(formattingName), displayName);
    }

    public static EnumRarity addRarity(String enumName, int colorIndex, String displayName) {
        return addRarity(enumName, fromColorIndex(colorIndex), displayName);
    }
}
