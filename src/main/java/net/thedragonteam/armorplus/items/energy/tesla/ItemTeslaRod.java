/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;

import java.util.List;

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

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            if (Loader.isModLoaded("tesla")) {
                tooltip.add(1, "Tesla is installed all the recipes should work");
            } else {
                tooltip.add(1, "Tesla isn't installed none of the recipes will work");
            }
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }
}
