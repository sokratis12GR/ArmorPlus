/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.util.text.TextFormatting.GREEN;
import static net.minecraft.util.text.TextFormatting.RED;

/**
 * @author Sokratis Fotkatzikis
 **/
public final class TextUtils {

    public static TextComponentTranslation translate(String key, Object... args) {
        return new TextComponentTranslation(key, args);
    }

    public static String translatedText(TextFormatting color, String key, Object... args) {
        return translate(color, key, args).getFormattedText();
    }

    public static TextComponentTranslation translate(TextFormatting color, String key, Object... args) {
        TextComponentTranslation ret = new TextComponentTranslation(key, args);
        ret.getStyle().setColor(color);
        return ret;
    }

    public static String translatedText(String key, Object... args) {
        return translate(key, args).getFormattedText();
    }

    public static String errorText(String key, Object... args) {
        return translatedText(RED, key, args);
    }

    public static String successText(String key, Object... args) {
        return translatedText(GREEN, key, args);
    }

    public static TextComponentString setText(String text) {
        return new TextComponentString(text);
    }

    public static String getText(String text) {
        return setText(text).getFormattedText();
    }

}
