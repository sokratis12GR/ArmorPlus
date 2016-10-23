/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.energy.steel;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.thedragonteam.armorplus.armors.base.BaseSteelArmor;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.armors.energy.steel
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class SteelBoots extends BaseSteelArmor {

    public SteelBoots() {
        super(0, EntityEquipmentSlot.FEET, "steel_boots", 0, maxCapacitySteelArmor, inputSteelArmor, outputSteelArmor);
        setMaxDamage(0);
    }
}