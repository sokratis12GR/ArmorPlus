/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.utils;

import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.items.weapons.effects.Ignite;
import com.sofodev.armorplus.items.weapons.effects.Negative;
import com.sofodev.armorplus.util.PotionUtils;
import com.sofodev.armorplus.util.RomanNumeralUtil;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.util.TextUtils.translatedText;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static net.minecraft.util.text.TextFormatting.GRAY;

/**
 * @author Sokratis Fotkatzikis
 **/
@OnlyIn(Dist.CLIENT)
public final class ToolTipUtils {

    /**
     * This provides the "Press [Key] to show more" tooltip
     *
     * @param tooltip    the tooltip of the item
     * @param keyBinding the keybind that the users will need to press to display the more information (replaces [Key])
     * @param formatting the formatting of the tooltip, its color and style.
     */
    public static void showInfo(List<ITextComponent> tooltip, KeyBinding keyBinding, TextFormatting formatting) {
        String msg = String.format("%s%s %s%s %s%s", GRAY, new TextComponentTranslation("tooltip.shift.showinfo.text_one").getFormattedText(), formatting, new TextComponentTranslation(keyBinding.getTranslationKey()).getFormattedText(), GRAY, new TextComponentTranslation("tooltip.shift.showinfo.text_two").getFormattedText());
        tooltip.add(new TextComponentString(msg));
    }

    /**
     * Writes the abilities of the armor sets with an ordered list from abilitySorter to the item's tooltip.
     * The abilities that will be written here, will only work when the full set is equipped.
     *
     * @param tooltip   the tooltip of the armor item
     * @param abilities provides the abilities that are going to be applied to the main entity
     * @param amplifier provides the levels of the abilities
     */
    public static void addToolTipFull(List<ITextComponent> tooltip, List<String> abilities, List<Integer> amplifier) {
        int colorIndex = 1;
        abilitySorter(tooltip, abilities, amplifier, colorIndex, true);
    }

    public static void addToolTipFull(List<ITextComponent> tooltip, IEffectHolder effectHolder) {
        int colorIndex = 1;
        AbilityProvider applicable = effectHolder.getApplicableAbilities();
        abilitySorter(tooltip, Utils.boxList(applicable.getAbilities().name), Utils.boxList(applicable.getAbilities().level), colorIndex, true);
    }

    /**
     * Writes the abilities of the armor pieces with an ordered list from abilitySorter to the item's tooltip.
     * The abilities that will be written here, will work even if one armor piece is equipped.
     *
     * @param tooltip   the tooltip of the armor item
     * @param abilities provides the abilities that are going to be applied to the main entity
     * @param amplifier provides the levels of the abilities
     */
    public static void addToolTipPiece(List<ITextComponent> tooltip, List<String> abilities, List<Integer> amplifier) {
        int colorIndex = 1;
        abilitySorter(tooltip, abilities, amplifier, colorIndex, false);
    }

    public static void addToolTipPiece(List<ITextComponent> tooltip, IEffectHolder effectHolder) {
        addToolTipPiece(tooltip, effectHolder, false);
    }

    public static void addToolTipPiece(List<ITextComponent> tooltip, IEffectHolder effectHolder, boolean areFullSet) {
        int colorIndex = 1;
        AbilityProvider applicable = effectHolder.getApplicableAbilities();
        abilitySorter(tooltip, Utils.boxList(applicable.getAbilities().name), Utils.boxList(applicable.getAbilities().level), colorIndex, false);
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
    private static void abilitySorter(List<ITextComponent> tooltip, List<String> abilities, List<Integer> amplifier, int colorIndex, boolean areFullSet) {
        addToolTip(tooltip, areFullSet ? translatedText("item.armorplus.ability_holder.full_set") : translatedText("item.armorplus.ability_holder.piece"));
        for (int abilityIndex = 0; abilityIndex < abilities.size(); abilityIndex++) {
            if (abilities.get(abilityIndex).contains("empty")) {
                continue;
            }
            List<String> localizedEffects = abilities.stream().map(PotionUtils::localizePotion).collect(toList());
            colorIndex++;
            TextFormatting abilityFormatting = TextFormatting.fromColorIndex(colorIndex % 15);
            addToolTip(tooltip, format("%s%s %s", valueOf(abilityFormatting), localizedEffects.get(abilityIndex), RomanNumeralUtil.generate(level(amplifier.get(abilityIndex)))));
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
    @OnlyIn(Dist.CLIENT)
    public static void addSpecialInformation(List<ITextComponent> tooltip, Negative negative, Ignite ignite, TextFormatting formatting) {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (!InputMappings.isKeyDown(keyBindSneak.getKey().getKeyCode())) {
            showInfo(tooltip, keyBindSneak, formatting);
        } else {
            tooltip.add(new TextComponentString("\2479Abilities"));
            boolean canIgnite = ignite.isEnabled();
            boolean isEffectEnabled = negative.isEnabled();
            if (!canIgnite && !isEffectEnabled) {
                tooltip.add(new TextComponentString("\u00a79" + "none"));
            }
            if (canIgnite) {
                tooltip.add(new TextComponentString("\u00a76" + "Sets the entities on fire for " + ignite.getFireSeconds() + " seconds"));
            }
            if (isEffectEnabled) {
                String[] negativeEffects = negative.getEffects();
                int[] effectLevels = negative.getEffectLevels();
                int colorIndex = 1;
                for (int abilityIndex = 0; abilityIndex < negativeEffects.length; abilityIndex++) {
                    if (negativeEffects[abilityIndex].contains("empty")) {
                        continue;
                    }
                    colorIndex++;
                    TextFormatting abilityFormatting = TextFormatting.fromColorIndex(colorIndex % 15);
                    tooltip.add(new TextComponentString(format("%s%s %s", abilityFormatting, PotionUtils.localizePotion(negativeEffects[abilityIndex]), RomanNumeralUtil.generate(effectLevels[abilityIndex] + 1))));
                }
            }
        }
    }

    /**
     * Checks if the Sneak (default: Shift) key is pressed
     *
     * @return true - if its pressed, false - if its not
     */
    public static boolean isKeyDown() {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        return InputMappings.isKeyDown(keyBindSneak.getKey().getKeyCode());
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
    public static void addToolTip(List<ITextComponent> tooltip, String... lines) {
        Arrays.stream(lines).map(TextComponentString::new).forEach(tooltip::add);
    }
}
