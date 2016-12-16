/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.armorplus.util.EnumHelperUtil;

import java.util.List;

public class DevTool extends BaseItem {

    private EnumRarity dev;

    public DevTool() {
        super("dev_tool");
        dev = EnumHelperUtil.addRarity("DEV", TextFormatting.BOLD, "Dev");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return dev;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target != null) {
            if (!playerIn.world.isRemote)
                playerIn.sendMessage(new TextComponentString(TextFormatting.GOLD +
                        "[" + target.getName() + "]"
                        + " - " + "Health: " + target.getHealth()
                        + " - " + "Max Health: " + target.getMaxHealth()
                        + " - " + "Class: " + target.getClass()
                        + " - " + "Held Item Off Hand: " + target.getHeldItemOffhand()
                        + " - " + "Held Item Main Hand: " + target.getHeldItemMainhand()
                        + " - " + "Position: " + target.getPosition()));
            return true;
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rGives Information about the Target");
            tooltip.add("\2473Use: " + "\247rRight Click a Target");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", TextFormatting.BOLD, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}
