/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.special.mob.chicken;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.special.mob.chicken
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class ChickenHelmet extends BaseArmor {

    public ChickenHelmet() {
        super(ModItems.chickenArmor, 0, EntityEquipmentSlot.HEAD, "chicken_helmet");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rSpeed 5");
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.AQUA, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.AQUA + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.FEATHER;
    }
}