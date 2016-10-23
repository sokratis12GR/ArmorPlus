/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.origin.lava;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.armorplus.ARPConfig.enableFullLavaArmorEffect;

/**
 * net.thedragonteam.armorplus.armors.origin.lava
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class LavaBoots extends BaseArmor {

    public LavaBoots() {
        super(ModItems.lavaArmor, 0, EntityEquipmentSlot.FEET, "lava_boots", ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.GOLD);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            if (ARPConfig.enableLavaBEffects) {
                tooltip.add("\2479Ability: " + "\247rFire Resistance");
                tooltip.add("\2473Use: " + "\247rEquip A Piece");
            }
            if (enableFullLavaArmorEffect) {
                tooltip.add("\2479Ability: " + "\247rFire Resistance");
                tooltip.add("\2473Use: " + "\247rEquip The Full Set");
            }
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.GOLD, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableLavaBEffects && entity instanceof EntityLivingBase && !enableFullLavaArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
        }
        if (!enableFullLavaArmorEffect) {
            entity.extinguish();
            if (entity.isInLava()) {
                entity.setAbsorptionAmount(4.0F);
            } else
                entity.setAbsorptionAmount(0.0F);
        }
        if (entity.isInWater() && !enableFullLavaArmorEffect) {
            if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
                itemStack.damageItem(1, entity);
                entity.attackEntityFrom(DamageSource.drown, 1F);
            }
        }
    }
}