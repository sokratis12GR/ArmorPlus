/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.tconstruct.pigiron;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.tconstruct.pigiron
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class PigIronLeggings extends BaseArmor {

    public PigIronLeggings() {
        super(ModItems.pigIronArmor, 0, EntityEquipmentSlot.LEGS, "pig_iron_leggings");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("\2479Ability: " + "\247rSaturation");
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.LIGHT_PURPLE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}