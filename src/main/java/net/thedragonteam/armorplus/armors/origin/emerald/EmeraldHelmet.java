/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.origin.emerald;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.origin.emerald
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class EmeraldHelmet extends BaseArmor {

    public EmeraldHelmet() {
        super(ModItems.emeraldArmor, 0, EntityEquipmentSlot.HEAD, "emerald_helmet");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            int emeraldArmorEffectLevel = ARPConfig.emeraldArmorEffectlevel + 1;
            if (ARPConfig.enableEmeraldHHaste) {
                tooltip.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                tooltip.add("\2473Use: " + "\247rEquip A Piece");
            }
            if (ARPConfig.enableFullEmeraldArmorEffect) {
                tooltip.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                tooltip.add("\2473Use: " + "\247rEquip The Full Set");
            }
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.DARK_GREEN, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableEmeraldHHaste && entity instanceof EntityLivingBase && !ARPConfig.enableFullEmeraldArmorEffect) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ARPConfig.emeraldArmorEffectlevel, true, true));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_GREEN + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (ARPConfig.recipes == 0) {
            return repair.getItem() == Items.EMERALD;
        }
        if (ARPConfig.recipes == 1) {
            return repair.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK);
        }
        return true;
    }
}