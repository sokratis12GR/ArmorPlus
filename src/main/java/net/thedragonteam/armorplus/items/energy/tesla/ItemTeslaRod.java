/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.energy.tesla;

import net.minecraft.creativetab.CreativeTabs;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;

/**
 * net.thedragonteam.armorplus.items.materials
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class ItemTeslaRod extends BaseItem {

    public ItemTeslaRod() {
        super("tesla_rod");
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }
}
