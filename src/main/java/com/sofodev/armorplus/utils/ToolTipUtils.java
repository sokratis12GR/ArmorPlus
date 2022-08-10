package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;

import java.util.List;

import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;
import static net.minecraft.ChatFormatting.*;

public class ToolTipUtils {

    /**
     * This provides the "Press [Key] to show more" tooltip
     *
     * @param tooltip    the tooltip of the item
     * @param keyBinding the keybind that the users will need to press to display the more information (replaces [Key])
     * @param formatting the formatting of the tooltip, its color and style.
     */
    public static void showInfo(List<Component> tooltip, KeyMapping keyBinding, ChatFormatting formatting) {
        tooltip.add(translate(GRAY, "tooltip.armorplus.shift.showinfo", translate(formatting, keyBinding.getName())));
    }

    /**
     * Adds a basic damage information about arrows
     */
    public static void appendArrowHoverText(List<Component> tooltip, Component effect, double damage, ChatFormatting formatting) {
        KeyMapping keyBindSneak = Minecraft.getInstance().options.keyShift;
        if (Screen.hasShiftDown()) {
            tooltip.add(translate("tooltip.armorplus.arrow.ability_desc", effect));
            tooltip.add(translate("tooltip.armorplus.arrow.ability", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }

    public static void addBuffInformation(IAPTool tool, List<Component> tooltip, String condition, boolean applyToSelf, boolean enabled) {
        if (!tool.getBuffInstances().get().isEmpty()) {
            tooltip.add(translate(YELLOW, "tooltip.armorplus.condition", enabled ? "" : "(DISABLED)"));
            tooltip.add(translate(GOLD, "tooltip.armorplus.condition." + condition));
            tooltip.add(translate(GREEN, "tooltip.armorplus." + (applyToSelf ? "provides" : "applies")));
            for (BuffInstance buff : tool.getBuffInstances().get()) {
                int lvl = buff.getAmplifier() + 1;
                String theLvl = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.add(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), theLvl));
            }
        }
    }

    public static void addExperimentalItemInformation(List<Component> tooltip) {
        tooltip.add(translate(RED, "tooltip.armorplus.not_accessible"));
        tooltip.add(translate(RED, "tooltip.armorplus.not_accessible.2"));
        tooltip.add(translate(RED, "tooltip.armorplus.not_accessible.3"));
    }

    public static MutableComponent translate(TextColor color, String key, Object... args) {
        return Component.translatable(key, args).setStyle(Style.EMPTY.withColor(color));
    }


    public static MutableComponent translate(Style style, String key, Object... args) {
        return Component.translatable(key, args).setStyle(style);
    }

    public static MutableComponent translate(ChatFormatting formatting, String key, Object... args) {
        return Component.translatable(key, args).withStyle(formatting);
    }

    public static MutableComponent translate(String key, Object... args) {
        return Component.translatable(key, args);
    }

}