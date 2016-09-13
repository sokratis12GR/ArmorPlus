/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.v2.electrical;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.armors.v2.electrical
 * ArmorPlus created by sokratis12GR on 7/28/2016 9:19 AM.
 * - TheDragonTeam
 */
public class ElectricalBoots extends BaseArmor {

    public ElectricalBoots() {
        super(ModItems.electricalArmor, 0, EntityEquipmentSlot.FEET, "electrical_boots");
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