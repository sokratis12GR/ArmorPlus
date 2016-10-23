/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.bows;

import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseBow;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.bows
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class EnderDragonBow extends BaseBow {

    public EnderDragonBow() {
        super(2000, "ender_dragon_bow", 12.0F, ModItems.enderDragonScale, ModItems.enderDragonScale, TextFormatting.DARK_PURPLE, ModItems.enderDragonBow);
    }
}
