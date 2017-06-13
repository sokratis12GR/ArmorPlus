/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModBlocks;

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialSword.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public enum Swords implements IStringSerializable {
    COAL(swordCoalMaterial, "coal", getItemStack(Items.COAL), getItemStack(COAL_BLOCK),
            getValueByName(coalWeaponItemNameColor), setToolTip(coalWeaponsAddPotionEffect, coalWeaponsEffectLevel),
            enableCoalWeaponsEffects, coalWeaponsAddPotionEffect, coalWeaponsEffectLevel),
    LAPIS(swordLapisMaterial, "lapis", getItemStack(Items.DYE, 4), getItemStack(LAPIS_BLOCK),
            getValueByName(lapisWeaponItemNameColor), setToolTip(lapisWeaponsAddPotionEffect, lapisWeaponsEffectLevel),
            enableLapisWeaponsEffects, lapisWeaponsAddPotionEffect, lapisWeaponsEffectLevel),
    REDSTONE(swordRedstoneMaterial, "redstone", getItemStack(Items.REDSTONE), getItemStack(REDSTONE_BLOCK),
            getValueByName(redstoneWeaponItemNameColor), setToolTip(redstoneWeaponsAddPotionEffect, redstoneWeaponsEffectLevel),
            enableRedstoneWeaponsEffects, redstoneWeaponsAddPotionEffect, redstoneWeaponsEffectLevel),
    EMERALD(swordEmeraldMaterial, "emerald", getItemStack(Items.EMERALD), getItemStack(EMERALD_BLOCK),
            getValueByName(emeraldWeaponItemNameColor), setToolTip(emeraldWeaponsAddPotionEffect, emeraldWeaponsEffectLevel),
            enableEmeraldWeaponsEffects, emeraldWeaponsAddPotionEffect, emeraldWeaponsEffectLevel),
    OBSIDIAN(swordObsidianMaterial, "obsidian", getItemStack(Blocks.OBSIDIAN), getItemStack(ModBlocks.compressedObsidian),
            getValueByName(obsidianWeaponItemNameColor), setToolTip(obsidianWeaponsAddPotionEffect, obsidianWeaponsEffectLevel),
            enableObsidianWeaponsEffects, obsidianWeaponsAddPotionEffect, obsidianWeaponsEffectLevel),
    LAVA(swordLavaMaterial, "infused_lava", getItemStack(lavaCrystal), getItemStack(lavaCrystal, 1),
            getValueByName(lavaWeaponItemNameColor), setLavaToolTip(), true, "empty", 0) {
        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
            if (GameSettings.isKeyDown(keyBindSneak)) {
                tooltip.add("\2479Ability: " + "\247r" + this.getEffect());
                tooltip.add("\2473Use: " + "\247rHit a Target");
            } else {
                showInfo(tooltip, keyBindSneak, this.getTextFormatting());
            }
        }

        @Override
        public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
            stack.damageItem(1, attacker);
            target.setFire(8);
            return true;
        }
    },
    GUARDIAN(swordGuardianMaterial, "guardian", getItemStack(materials, 1),
            getValueByName(guardianWeaponItemNameColor), setToolTip(guardianWeaponsAddPotionEffect, guardianWeaponsEffectLevel),
            enableGuardianWeaponsEffects, guardianWeaponsAddPotionEffect, guardianWeaponsEffectLevel),
    SUPER_STAR(swordSuperStarMaterial, "super_star", getItemStack(materials, 2),
            getValueByName(superStarWeaponItemNameColor), setToolTip(superStarWeaponsAddPotionEffect, superStarWeaponsEffectLevel),
            enableSuperStarWeaponsEffects, superStarWeaponsAddPotionEffect, superStarWeaponsEffectLevel),
    ENDER_DRAGON(swordEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3),
            getValueByName(enderDragonWeaponItemNameColor), setToolTip(enderDragonWeaponsAddPotionEffect, enderDragonWeaponsEffectLevel),
            enableEnderDragonWeaponsEffects, enderDragonWeaponsAddPotionEffect, enderDragonWeaponsEffectLevel);

    private final String name;
    private final Item.ToolMaterial material;
    private final ItemStack repairEasy;
    private final ItemStack repairExpert;
    private final TextFormatting textFormatting;
    private final String effect;
    private final boolean enabledEffects;
    private final String addNegativePotionEffect;
    private final int addNegativePotionEffectAmplifier;

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairBoth, TextFormatting textFormattingIn, String effectIn,
           boolean enableEffect, String addNegativeEffect, int addNegativeEffectAmplifier) {
        this(materialIn, nameIn, repairBoth, repairBoth, textFormattingIn, effectIn, enableEffect, addNegativeEffect, addNegativeEffectAmplifier);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn,
           boolean enableEffect, String addNegativeEffect, int addNegativeEffectAmplifier) {
        this.material = materialIn;
        this.name = nameIn;
        if (repairEasyIn == null) repairEasyIn = EMPTY;
        this.repairEasy = repairEasyIn;
        if (repairExpertIn == null) repairExpertIn = EMPTY;
        this.repairExpert = repairExpertIn;
        this.textFormatting = textFormattingIn;
        this.effect = effectIn;
        this.enabledEffects = enableEffect;
        this.addNegativePotionEffect = addNegativeEffect;
        this.addNegativePotionEffectAmplifier = addNegativeEffectAmplifier;
    }

    public static String setToolTip(String effectName, int effectLevel) {
        return localizePotion(effectName) + " " + (effectLevel + 1);
    }

    public static String setLavaToolTip() {
        return "Sets On Fire";
    }

    public String toString() {
        return this.name;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    public Item.ToolMaterial getToolMaterial() {
        return material;
    }

    public String getEffect() {
        return effect;
    }

    public ItemStack getRepairEasy() {
        return repairEasy;
    }

    public ItemStack getRepairExpert() {
        return repairExpert;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public boolean hasEnabledEffects() {
        return enabledEffects;
    }

    public String getAddNegativeEffect() {
        return addNegativePotionEffect;
    }

    public int getAddNegativeEffectAmplifier() {
        return addNegativePotionEffectAmplifier;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.hasEnabledEffects())
            addPotion(target, getPotion(this.getAddNegativeEffect()), this.getAddNegativeEffectAmplifier(), BAD);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rApplies " + this.getEffect());
            tooltip.add("\2473Use: " + "\247rHit a Target");
        } else {
            showInfo(tooltip, keyBindSneak, this.getTextFormatting());
        }
    }
}
