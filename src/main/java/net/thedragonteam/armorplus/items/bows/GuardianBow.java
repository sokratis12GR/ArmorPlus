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
public class GuardianBow extends BaseBow {

    public GuardianBow() {
        super(500, "guardian_bow", 8.5F, ModItems.guardianScale, ModItems.guardianScale, TextFormatting.AQUA, ModItems.guardianBow);
    }
}
