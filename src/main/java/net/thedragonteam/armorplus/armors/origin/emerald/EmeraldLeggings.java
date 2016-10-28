/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.origin.emerald;

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
 * net.thedragonteam.armorplus.armors.origin.emerald
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class EmeraldLeggings extends BaseArmor {

    public EmeraldLeggings() {
        super(ModItems.emeraldArmor, 0, EntityEquipmentSlot.LEGS, "emerald_leggings", Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.DARK_GREEN);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            int emeraldArmorEffectLevel = ARPConfig.emeraldArmorEffectlevel + 1;
            if (ARPConfig.enableEmeraldLHaste) {
                tooltip.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                tooltip.add("\2473Use: " + "\247rEquip A Piece");
            } else if (ARPConfig.enableFullEmeraldArmorEffect) {
                tooltip.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                tooltip.add("\2473Use: " + "\247rEquip The Full Set");
            }
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.DARK_GREEN, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableEmeraldLHaste && !ARPConfig.enableFullEmeraldArmorEffect)
            entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ARPConfig.emeraldArmorEffectlevel, true, true));
    }
}