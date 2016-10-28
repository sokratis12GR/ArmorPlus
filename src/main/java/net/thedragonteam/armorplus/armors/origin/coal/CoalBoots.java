/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.origin.coal;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
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
 * net.thedragonteam.armorplus.armors.origin.coal
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class CoalBoots extends BaseArmor {

    public CoalBoots() {
        super(ModItems.coalArmor, 0, EntityEquipmentSlot.FEET, "coal_boots", Items.COAL, Blocks.COAL_BLOCK, TextFormatting.GRAY);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) if (ARPConfig.enableCoalBNightVision) {
            tooltip.add("\2479Ability: " + "\247rNight Vision");
            tooltip.add("\2473Use: " + "\247rEquip A Piece");
        } else if (ARPConfig.enableFullCoalArmorEffect) {
            tooltip.add("\2479Ability: " + "\247rNight Vision");
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        }
        else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.GRAY, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableCoalBNightVision && !ARPConfig.enableFullCoalArmorEffect)
            entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
    }
}