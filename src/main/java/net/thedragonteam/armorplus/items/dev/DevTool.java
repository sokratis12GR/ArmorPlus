/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.dev;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseItem;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class DevTool extends BaseItem {

    public DevTool() {
        super("dev_tool");
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target != null) {
            playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "[ " + target.getName() + " ]" + " - " + "Health: " + target.getHealth()));
            return true;
        }
        return true;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.COAL;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.BOLD + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rTracks Targets Health");
            tooltip.add("\2473Use: " + "\247rRight Click a Target");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.GOLD, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }
}
