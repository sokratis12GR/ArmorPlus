/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.registry.ModBlocks;

import javax.annotation.Nonnull;

import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialSword.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.PotionUtils.localizePotion;

public enum Swords implements IStringSerializable {
    COAL(swordCoalMaterial, "coal", Items.COAL, Blocks.COAL_BLOCK, getValueByName(coalWeaponItemNameColor), localizePotion(coalWeaponsAddPotionEffect) + " " + (coalWeaponsEffectLevel + 1)),
    LAPIS(swordLapisMaterial, "lapis", getItemStack(Items.DYE, 4), Blocks.LAPIS_BLOCK, getValueByName(lapisWeaponItemNameColor), localizePotion(lapisWeaponsAddPotionEffect) + " " + (lapisWeaponsEffectLevel + 1)),
    REDSTONE(swordRedstoneMaterial, "redstone", Items.REDSTONE, Blocks.REDSTONE_BLOCK, getValueByName(redstoneWeaponItemNameColor), localizePotion(redstoneWeaponsAddPotionEffect) + " " + (redstoneWeaponsEffectLevel + 1)),
    EMERALD(swordEmeraldMaterial, "emerald", Items.EMERALD, Blocks.EMERALD_BLOCK, getValueByName(emeraldWeaponItemNameColor), localizePotion(emeraldWeaponsAddPotionEffect) + " " + (emeraldWeaponsEffectLevel + 1)),
    OBSIDIAN(swordObsidianMaterial, "obsidian", Blocks.OBSIDIAN, ModBlocks.compressedObsidian, getValueByName(obsidianWeaponItemNameColor), localizePotion(obsidianWeaponsAddPotionEffect) + " " + (obsidianWeaponsEffectLevel + 1)),
    LAVA(swordLavaMaterial, "lava", lavaCrystal, getItemStack(lavaCrystal, 1), getValueByName(lavaWeaponItemNameColor), "Sets on Fire"),
    GUARDIAN(swordGuardianMaterial, "guardian", getItemStack(materials, 1), getValueByName(guardianWeaponItemNameColor), localizePotion(guardianWeaponsAddPotionEffect) + " " + (guardianWeaponsEffectLevel + 1)),
    SUPER_STAR(swordSuperStarMaterial, "super_star", getItemStack(materials, 2), getValueByName(superStarWeaponItemNameColor), localizePotion(superStarWeaponsAddPotionEffect) + " " + (superStarWeaponsEffectLevel + 1)),
    ENDER_DRAGON(swordEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), getValueByName(enderDragonWeaponItemNameColor), localizePotion(enderDragonWeaponsAddPotionEffect) + " " + (enderDragonWeaponsEffectLevel + 1));

    private final String name;

    private final Item.ToolMaterial material;

    private final Item repairEasy;

    private final Item repairExpert;

    private final TextFormatting textFormatting;

    private final String effect;


    Swords(Item.ToolMaterial materialIn, String nameIn, Block repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, Item.getItemFromBlock(repairEasyIn), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn.getItem(), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairBoth, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairBoth.getItem(), repairBoth.getItem(), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairEasy = repairEasyIn;
        this.repairExpert = repairExpertIn;
        this.textFormatting = textFormattingIn;
        this.effect = effectIn;
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

    public Item getRepairEasy() {
        return repairEasy;
    }

    public Item getRepairExpert() {
        return repairExpert;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }
}
