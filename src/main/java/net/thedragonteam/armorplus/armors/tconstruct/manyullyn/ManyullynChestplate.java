/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.tconstruct.manyullyn;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

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
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rStrength 2");
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.DARK_PURPLE, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_PURPLE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}