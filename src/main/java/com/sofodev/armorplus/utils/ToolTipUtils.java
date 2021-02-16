package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.*;

import java.util.List;

import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;
import static net.minecraft.util.text.Style.EMPTY;
import static net.minecraft.util.text.TextFormatting.*;

public class ToolTipUtils {

    /**
     * This provides the "Press [Key] to show more" tooltip
     *
     * @param tooltip    the tooltip of the item
     * @param keyBinding the keybind that the users will need to press to display the more information (replaces [Key])
     * @param formatting the formatting of the tooltip, its color and style.
     */
    public static void showInfo(List<ITextComponent> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        tooltip.add(translate(GRAY, "tooltip.armorplus.shift.showinfo", translate(formatting, keyBinding.getTranslationKey())));
    }

    /**
     * Adds a basic damage information about arrows
     */
    public static void addArrowInformation(List<ITextComponent> tooltip, ITextComponent effect, double damage, TextFormatting formatting) {
        KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (Screen.hasShiftDown()) {
            tooltip.add(translate("tooltip.armorplus.arrow.ability_desc", effect));
            tooltip.add(translate("tooltip.armorplus.arrow.ability", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }

    public static void addBuffInformation(IAPTool tool, List<ITextComponent> tooltip, String condition, boolean applyToSelf) {
        if (tool.getBuffInstances().length > 0) {
            tooltip.add(translate(YELLOW, "tooltip.armorplus.condition"));
            tooltip.add(translate(GOLD, "tooltip.armorplus.condition." + condition));
            tooltip.add(translate(GREEN, "tooltip.armorplus." + (applyToSelf ? "provides" : "applies")));
            for (BuffInstance buff : tool.getBuffInstances()) {
                int lvl = buff.getAmplifier() + 1;
                String theLvl = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.add(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), theLvl));
            }
        }
        if (tool.equals(APToolMaterial.SLAYER_MAT)) {
            addExperimentalItemInformation(tooltip);
        }
    }

    public static void addExperimentalItemInformation(List<ITextComponent> tooltip) {
        tooltip.add(translate(RED, "tooltip.armorplus.not_accessible"));
        tooltip.add(translate(RED, "tooltip.armorplus.not_accessible.2"));
        tooltip.add(translate(RED, "tooltip.armorplus.not_accessible.3"));
    }

    public static IFormattableTextComponent translate(Color color, String key, Object... args) {
        return new TranslationTextComponent(key, args).setStyle(EMPTY.setColor(color));
    }


    public static IFormattableTextComponent translate(Style style, String key, Object... args) {
        return new TranslationTextComponent(key, args).setStyle(style);
    }

    public static IFormattableTextComponent translate(TextFormatting formatting, String key, Object... args) {
        return new TranslationTextComponent(key, args).mergeStyle(formatting);
    }

    public static TextComponent translate(String key, Object... args) {
        return new TranslationTextComponent(key, args);
    }

}