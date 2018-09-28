/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.items.weapons.effects.Ignite;
import net.thedragonteam.armorplus.items.weapons.effects.Negative;
import net.thedragonteam.armorplus.util.PotionUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;
import static net.thedragonteam.armorplus.util.RomanNumeralUtil.generate;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@SideOnly(Side.CLIENT)
public final class ToolTipUtils {

    /**
     * This provides the "Press [Key] to show more" tooltip
     *
     * @param tooltip    the tooltip of the item
     * @param keyBinding the keybind that the users will need to press to display the more information (replaces [Key])
     * @param formatting the formatting of the tooltip, its color and style.
     */
    public static void showInfo(List<String> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        tooltip.add(MessageFormat.format("{0}{1} {2}{3} {4}{5}", GRAY, new TextComponentTranslation("tooltip.shift.showinfo.text_one").getFormattedText(), formatting, keyBinding.getDisplayName(), GRAY, formattedText("tooltip.shift.showinfo.text_two")));
    }

    /**
     * Writes the abilities of the armor sets with an ordered list from abilitySorter to the item's tooltip.
     * The abilities that will be written here, will only work when the full set is equipped.
     *
     * @param tooltip   the tooltip of the armor item
     * @param abilities provides the abilities that are going to be applied to the main entity
     * @param amplifier provides the levels of the abilities
     */
    public static void addToolTipFull(List<String> tooltip, List<String> abilities, List<Integer> amplifier) {
        int colorIndex = 1;
        abilitySorter(tooltip, abilities, amplifier, colorIndex, true);
    }

    /**
     * Writes the abilities of the armor pieces with an ordered list from abilitySorter to the item's tooltip.
     * The abilities that will be written here, will work even if one armor piece is equipped.
     *
     * @param tooltip   the tooltip of the armor item
     * @param abilities provides the abilities that are going to be applied to the main entity
     * @param amplifier provides the levels of the abilities
     */
    public static void addToolTipPiece(List<String> tooltip, List<String> abilities, List<Integer> amplifier) {
        int colorIndex = 1;
        abilitySorter(tooltip, abilities, amplifier, colorIndex, false);
    }

    /**
     * Formats the abilities of the armor sets/pieces to ordered organized lists of lines per ability.
     *
     * @param tooltip    the tooltip of the armor
     * @param abilities  provides the abilities that are going to be applied to the main entity
     * @param amplifier  provides the levels of the abilities
     * @param colorIndex the color of the line (color index)
     * @param areFullSet checks if the abilities are for sets or for individual pieces
     */
    private static void abilitySorter(List<String> tooltip, List<String> abilities, List<Integer> amplifier, int colorIndex, boolean areFullSet) {
        addToolTip(tooltip, areFullSet ? "\u00a79Full set abilities" : "\u00a79Abilities");
        for (int abilityIndex = 0; abilityIndex < abilities.size(); abilityIndex++) {
            if (abilities.get(abilityIndex).contains("empty")) {
                continue;
            }
            List<String> localizedEffects = abilities.stream().map(PotionUtils::localizePotion).collect(toList());
            colorIndex++;
            TextFormatting abilityFormatting = TextFormatting.fromColorIndex(colorIndex % 15);
            addToolTip(tooltip, format("%s%s %s", abilityFormatting, localizedEffects.get(abilityIndex), generate(level(amplifier.get(abilityIndex)))));
        }
    }

    /**
     * Creates the tooltip information displayed on the weapons.
     *
     * @param tooltip    the tooltip of the weapon
     * @param negative   provides the negative effects that are going to be applied to the hit entity
     * @param ignite     provides information about if ignition is enabled and for how long will the entity burn
     * @param formatting the formatting (color) of the sneak key bind text.
     */
    @SideOnly(Side.CLIENT)
    public static void addSpecialInformation(List<String> tooltip, Negative negative, Ignite ignite, TextFormatting formatting) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Abilities");
            if (!ignite.isEnabled() && !negative.isEnabled()) {
                tooltip.add("\u00a79" + "none");
            }
            if (ignite.isEnabled()) {
                tooltip.add("\u00a76" + "Sets the entities on fire for " + ignite.getFireSeconds() + " seconds");
            }
            if (negative.isEnabled()) {
                String[] negativeEffects = negative.getEffects();
                int[] effectLevels = negative.getEffectLevels();
                int colorIndex = 1;
                for (int abilityIndex = 0; abilityIndex < negativeEffects.length; abilityIndex++) {
                    if (negativeEffects[abilityIndex].contains("empty")) {
                        continue;
                    }
                    colorIndex++;
                    TextFormatting abilityFormatting = TextFormatting.fromColorIndex(colorIndex % 15);
                    tooltip.add(format("%s%s %s", abilityFormatting, localizePotion(negativeEffects[abilityIndex]), generate(effectLevels[abilityIndex] + 1)));
                }
            }
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }

    /**
     * Checks if the Sneak (default: Shift) key is pressed
     *
     * @return true - if its pressed, false - if its not
     */
    public static boolean isKeyDown() {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        return GameSettings.isKeyDown(keyBindSneak);
    }

    /**
     * Normalizes the level of the ability.
     * In Minecraft: ability level 0 is equal to level 1. level 1 to level 2...
     *
     * @param amplifier the level of the ability
     * @return The "normalized" ability level name
     */
    private static int level(int amplifier) {
        return amplifier + 1;
    }

    /**
     * Adds simple tooltip lines util, each string is converted to exactly one line in the tooltip
     *
     * @param tooltip the tooltip of the item
     * @param lines   the lines that are written to the tooltip
     */
    public static void addToolTip(List<String> tooltip, String... lines) {
        tooltip.addAll(Arrays.asList(lines));
    }
}
