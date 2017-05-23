/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.thedragonlib.util.TextUtils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.DARK_AQUA;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;

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
    @Nonnull
    public String getItemStackDisplayName(ItemStack stack) {
        return TextUtils.INSTANCE.formattedText(this.getUnlocalizedNameInefficiently(stack) + ".name");
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak))
            tooltip.add(1, Loader.isModLoaded("tesla") ? "Tesla is installed all the recipes should work" : "Tesla isn't installed none of the recipes will work");
        else {
            showInfo(tooltip, keyBindSneak, DARK_AQUA);
        }
    }
}
