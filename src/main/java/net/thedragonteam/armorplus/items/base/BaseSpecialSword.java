/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.Swords;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseSpecialSword extends ItemSword {

    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    public String effect;

    public Swords swords;

    public BaseSpecialSword(Swords swords) {
        super(swords.getToolMaterial());
        this.swords = swords;
        this.itemEasy = swords.getRepairEasy();
        this.itemExpert = swords.getRepairExpert();
        this.formatting = swords.getTextFormatting();
        this.effect = swords.getEffect();
        setRegistryName(swords.getName());
        setUnlocalizedName(ArmorPlus.MODID + "." + swords.getName());
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (swords.getId() == 0)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 180, 0, false, true));
            }
        if (swords.getId() == 1)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 120, 1, false, true));
            }
        if (swords.getId() == 2)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 1, false, true));
            }
        if (swords.getId() == 3)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 120, 1, false, true));
            }
        if (swords.getId() == 4)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 120, 1, false, true));
            }
        if (swords.getId() == 5)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).setFire(6);
            }
        if (swords.getId() == 6)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 120, 1, false, true));
            }
        if (swords.getId() == 7)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 120, 1, false, true));
            }
        if (swords.getId() == 8)
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 3, false, true));
            }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247r" + effect);
            tooltip.add("\2473Use: " + "\247rHit a Target");
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", formatting, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (formatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (ARPConfig.recipes == 0) {
            return repair.getItem() == itemEasy;
        }
        if (ARPConfig.recipes == 1) {
            return repair.getItem() == itemExpert;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
