/*
 * Copyright (c) TheDragonTeam 2016.
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

import static net.minecraft.util.text.TextFormatting.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialSword.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;

public enum Swords implements IStringSerializable {
    COAL(swordCoalMaterial, "coal", Items.COAL, Blocks.COAL_BLOCK, getValueByName(coalWeaponItemNameColor), localizePotion(coalWeaponsAddPotionEffect) + " " + (coalWeaponsEffectLevel + 1)),
    LAPIS(swordLapisMaterial, "lapis", new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, getValueByName(lapisWeaponItemNameColor), localizePotion(lapisWeaponsAddPotionEffect) + " " + (lapisWeaponsEffectLevel + 1)),
    REDSTONE(swordRedstoneMaterial, "redstone", Items.REDSTONE, Blocks.REDSTONE_BLOCK, getValueByName(redstoneWeaponItemNameColor), localizePotion(redstoneWeaponsAddPotionEffect) + " " + (redstoneWeaponsEffectLevel + 1)),
    EMERALD(swordEmeraldMaterial, "emerald", Items.EMERALD, Blocks.EMERALD_BLOCK, getValueByName(emeraldWeaponItemNameColor), localizePotion(emeraldWeaponsAddPotionEffect) + " " + (emeraldWeaponsEffectLevel + 1)),
    OBSIDIAN(swordObsidianMaterial, "obsidian", Blocks.OBSIDIAN, ModBlocks.compressedObsidian, getValueByName(obsidianWeaponItemNameColor), localizePotion(obsidianWeaponsAddPotionEffect) + " " + (obsidianWeaponsEffectLevel + 1)),
    LAVA(swordLavaMaterial, "lava", lavaCrystal, new ItemStack(lavaCrystal, 1, 1), getValueByName(lavaWeaponItemNameColor), "Sets on Fire"),
    GUARDIAN(swordGuardianMaterial, "guardian", new ItemStack(materials, 1, 1), getValueByName(guardianWeaponItemNameColor), localizePotion(guardianWeaponsAddPotionEffect) + " " + (guardianWeaponsEffectLevel + 1)),
    SUPER_STAR(swordSuperStarMaterial, "super_star", new ItemStack(materials, 1, 2), getValueByName(superStarWeaponItemNameColor), localizePotion(superStarWeaponsAddPotionEffect) + " " + (superStarWeaponsEffectLevel + 1)),
    ENDER_DRAGON(swordEnderDragonMaterial, "ender_dragon", new ItemStack(materials, 1, 3), getValueByName(enderDragonWeaponItemNameColor), localizePotion(enderDragonWeaponsAddPotionEffect) + " " + (enderDragonWeaponsEffectLevel + 1));

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

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn.getItem(), repairExpertIn.getItem(), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, effectIn);
    }

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairBoth, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairBoth, repairBoth, textFormattingIn, effectIn);
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
