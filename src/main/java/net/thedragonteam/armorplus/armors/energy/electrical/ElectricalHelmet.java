/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.energy.electrical;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.thedragonteam.armorplus.armors.base.BaseElectricalArmor;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.armors.energy.electrical
 * ArmorPlus created by sokratis12GR on 7/28/2016 9:19 AM.
 * - TheDragonTeam
 */
public class ElectricalHelmet extends BaseElectricalArmor {

    public ElectricalHelmet() {
        super(0, EntityEquipmentSlot.HEAD, "electrical_helmet", 0, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        setMaxDamage(0);
    }
}