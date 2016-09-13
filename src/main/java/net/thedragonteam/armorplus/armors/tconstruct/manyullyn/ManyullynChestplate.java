/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.tconstruct.manyullyn;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.tconstruct.manyullyn
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class ManyullynChestplate extends BaseArmor {

    public ManyullynChestplate() {
        super(ModItems.manyullynArmor, 0, EntityEquipmentSlot.CHEST, "manyullyn_chestplate");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("\2479Ability: " + "\247rStrength 2");
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_PURPLE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}