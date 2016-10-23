/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.origin.redstone;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

/**
 * net.thedragonteam.armorplus.armors.origin.redstone
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class RedstoneHelmet extends BaseArmor {

    public RedstoneHelmet() {
        super(ModItems.redstoneArmor, 0, EntityEquipmentSlot.HEAD, "redstone_helmet", Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.DARK_RED);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            int redstoneArmorEffectlevel = ARPConfig.redstoneArmorEffectlevel + 1;
            if (ARPConfig.enableRedstoneHSpeed) {
                tooltip.add("\2479Ability: " + "\247rSpeed " + redstoneArmorEffectlevel);
                tooltip.add("\2473Use: " + "\247rEquip A Piece");
            }
            if (ARPConfig.enableFullRedstoneArmorEffect) {
                tooltip.add("\2479Ability: " + "\247rSpeed " + redstoneArmorEffectlevel);
                tooltip.add("\2473Use: " + "\247rEquip The Full Set");
            }
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.DARK_RED, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableRedstoneHSpeed && entity instanceof EntityLivingBase && !ARPConfig.enableFullRedstoneArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 240, ARPConfig.redstoneArmorEffectlevel, true, true));
        }
    }
}