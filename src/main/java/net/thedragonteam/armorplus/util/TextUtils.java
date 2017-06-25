package net.thedragonteam.armorplus.util;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.util.text.TextFormatting.GREEN;
import static net.minecraft.util.text.TextFormatting.RED;

public class TextUtils {

    public static TextComponentTranslation setTextTranslation(String translationKey, Object... args) {
        return new TextComponentTranslation(translationKey, args);
    }

    public static TextComponentTranslation formatText(TextFormatting color, String translationKey, Object... args) {
        TextComponentTranslation ret = new TextComponentTranslation(translationKey, args);
        ret.getStyle().setColor(color);
        return ret;
    }

    public static String formattedText(TextFormatting color, String translationKey, Object... args) {
        return formatText(color, translationKey, args).getFormattedText();
    }

    public static TextComponentTranslation formatText(String translationKey, Object... args) {
        return new TextComponentTranslation(translationKey, args);
    }

    public static String formattedText(String translationKey, Object... args) {
        return setTextTranslation(translationKey, args).getFormattedText();
    }

    public static String errorText(String translationKey, Object... args) {
        return formattedText(RED, translationKey, args);
    }

    public static String successText(String translationKey, Object... args) {
        return formattedText(GREEN, translationKey, args);
    }

    public static TextComponentString setText(String text) {
        return new TextComponentString(text);
    }

    public static String getText(String text) {
        return setText(text).getFormattedText();
    }

}
