/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.energy.electrical;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseElectricalArmor;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.armors.energy.electrical
 * ArmorPlus created by sokratis12GR on 7/28/2016 9:19 AM.
 * - TheDragonTeam
 */
public class ElectricalBoots extends BaseElectricalArmor {

    public ElectricalBoots() {
        super(0, EntityEquipmentSlot.FEET, "electrical_boots", 0, maxCapacityElectricalArmor, inputElectricalArmor, outputElectricalArmor);
        setMaxDamage(0);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (ARPConfig.recipes == 0) {
            return repair.getItem() == ModItems.electricalIngot;
        }
        if (ARPConfig.recipes == 1) {
            return repair.getItem() == Item.getItemFromBlock(ModBlocks.electricalBlock);
        }
        return true;
    }
}