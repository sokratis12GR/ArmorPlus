package com.sofodev.armorplus.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.MessageFormat;
import java.util.List;

import static java.text.MessageFormat.*;
import static net.minecraft.util.text.Style.EMPTY;
import static net.minecraft.util.text.TextFormatting.GRAY;

public class ToolTipUtils {

    /**
     * This provides the "Press [Key] to show more" tooltip
     *
     * @param tooltip    the tooltip of the item
     * @param keyBinding the keybind that the users will need to press to display the more information (replaces [Key])
     * @param formatting the formatting of the tooltip, its color and style.
     */
    public static void showInfo(List<ITextComponent> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        tooltip.add(translate("tooltip.shift.showinfo.text_one").setStyle(EMPTY.setFormatting(GRAY))
                .append(new TranslationTextComponent(keyBinding.getTranslationKey()).setStyle(EMPTY.setFormatting(formatting)))
                .append(translate("tooltip.shift.showinfo.text_two")).setStyle(EMPTY.setFormatting(GRAY)));
    }

    /**
     * Adds a basic damage information about arrows
     */
    public static void addArrowInformation(List<ITextComponent> tooltip, ITextComponent effect, double damage, TextFormatting formatting) {
        KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (Screen.hasShiftDown()) {
            tooltip.add(translate("item.armorplus.arrow.ability_desc", effect));
            tooltip.add(translate("item.armorplus.arrow.ability", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }



    public static TextComponent translate(String key, Object... args) {
        return new TranslationTextComponent(key, args);
    }

}