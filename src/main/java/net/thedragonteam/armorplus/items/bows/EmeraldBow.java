/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class EmeraldBow extends BaseBow {

    public EmeraldBow() {
        super(1561, "emerald_bow", 5.0F, Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.DARK_GREEN, ModItems.emeraldBow);
    }
}
