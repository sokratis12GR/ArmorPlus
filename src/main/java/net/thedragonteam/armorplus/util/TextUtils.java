package net.thedragonteam.armorplus.util;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.util.text.TextFormatting.GREEN;
import static net.minecraft.util.text.TextFormatting.RED;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public final class TextUtils {

    public static TextComponentTranslation setTextTranslation(String translationKey, Object... args) {
        return new TextComponentTranslation(translationKey, args);
    }

    public static TextComponentTranslation formatText(TextFormatting color, String key, Object... args) {
        TextComponentTranslation ret = new TextComponentTranslation(key, args);
        ret.getStyle().setColor(color);
        return ret;
    }

    public static String formattedText(TextFormatting color, String key, Object... args) {
        return formatText(color, key, args).getFormattedText();
    }

    public static TextComponentTranslation formatText(String key, Object... args) {
        return new TextComponentTranslation(key, args);
    }

    public static String formattedText(String key, Object... args) {
        return setTextTranslation(key, args).getFormattedText();
    }

    public static String errorText(String key, Object... args) {
        return formattedText(RED, key, args);
    }

    public static String successText(String key, Object... args) {
        return formattedText(GREEN, key, args);
    }

    public static TextComponentString setText(String text) {
        return new TextComponentString(text);
    }

    public static String getText(String text) {
        return setText(text).getFormattedText();
    }

}
