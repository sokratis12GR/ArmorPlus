/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.iface;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

import static net.minecraft.util.text.TextFormatting.fromColorIndex;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis
 **/
public interface IRarityHelper {

    default IRarity getRarity(int formatting, String displayName) {
        return new IRarity() {
            @Override
            public TextFormatting getColor() {
                return fromColorIndex(formatting);
            }

            @Override
            public String getName() {
                return displayName;
            }
        };
    }

    default IRarity getRarity(TextFormatting formatting, String displayName) {
        return new IRarity() {
            @Override
            public TextFormatting getColor() {
                return formatting;
            }

            @Override
            public String getName() {
                return displayName;
            }
        };
    }

    default IRarity getRarity(String formatting, String displayName) {
        return new IRarity() {
            @Override
            public TextFormatting getColor() {
                return getValueByName(formatting);
            }

            @Override
            public String getName() {
                return displayName;
            }
        };
    }

    /**
     * @param stack the ItemStack that is getting the rarity
     * @return the color of the ItemStack (the `rarity`)
     */
    IRarity getRarity(ItemStack stack);

}
