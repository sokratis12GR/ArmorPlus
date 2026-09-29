package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.List;
import java.util.function.Consumer;

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
    public static void showInfo(Consumer<Component> tooltip, KeyMapping keyBinding, ChatFormatting formatting) {
        tooltip.accept(translate(GRAY, "tooltip.armorplus.shift.showinfo", translate(formatting, keyBinding.getName())));
    }

    /**
     * Adds a basic damage information about arrows
     */
    public static void appendArrowHoverText(Consumer<Component> tooltip, Component effect, double damage, ChatFormatting formatting) {
        KeyMapping keyBindSneak = Minecraft.getInstance().options.keyShift;
        if ((InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT))) {
            tooltip.accept(translate("tooltip.armorplus.arrow.ability_desc", effect));
            tooltip.accept(translate("tooltip.armorplus.arrow.ability", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }

    /** Returns true while the player is holding Shift in a GUI tooltip. */
    public static boolean isSneakDown() {
        return (InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT));
    }

    /** Adds the standard ArmorPlus "Press Shift for more info" line. */
    public static void showSneakInfo(Consumer<Component> tooltip, ChatFormatting formatting) {
        showInfo(tooltip, Minecraft.getInstance().options.keyShift, formatting);
    }

    public static void addBuffInformation(IAPTool tool, Consumer<Component> tooltip, String condition, boolean applyToSelf, boolean enabled) {
        if (!tool.getBuffInstances().get().isEmpty()) {
            tooltip.accept(translate(YELLOW, "tooltip.armorplus.condition", enabled ? "" : "(DISABLED)"));
            tooltip.accept(translate(GOLD, "tooltip.armorplus.condition." + condition));
            tooltip.accept(translate(GREEN, "tooltip.armorplus." + (applyToSelf ? "provides" : "applies")));
            for (BuffInstance buff : tool.getBuffInstances().get()) {
                int lvl = buff.getAmplifier() + 1;
                String theLvl = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.accept(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), theLvl));
            }
        }
    }

    /**
     * Adds bow bonus damage information.
     */
    public static void addBowDamageInformation(Consumer<Component> tooltip, double damage) {
        tooltip.accept(translate(DARK_GREEN, "tooltip.armorplus.bow.damage", damage));
    }

    public static void addExperimentalItemInformation(Consumer<Component> tooltip) {
        tooltip.accept(translate(RED, "tooltip.armorplus.not_accessible"));
        tooltip.accept(translate(RED, "tooltip.armorplus.not_accessible.2"));
        tooltip.accept(translate(RED, "tooltip.armorplus.not_accessible.3"));
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