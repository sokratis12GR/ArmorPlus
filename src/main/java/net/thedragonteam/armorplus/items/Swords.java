/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseSpecialSword;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;

public enum Swords implements IStringSerializable {
    COAL(BaseSpecialSword.swordCoalMaterial, "coal_sword", Items.COAL, Blocks.COAL_BLOCK, TextFormatting.getValueByName(coalWeaponItemNameColor), localizePotion(coalWeaponsAddPotionEffect) + " " + (coalWeaponsEffectLevel + 1)),
    LAPIS(BaseSpecialSword.swordLapisMaterial, "lapis_sword", new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.getValueByName(lapisWeaponItemNameColor), localizePotion(lapisWeaponsAddPotionEffect) + " " + (lapisWeaponsEffectLevel + 1)),
    REDSTONE(BaseSpecialSword.swordRedstoneMaterial, "redstone_sword", Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.getValueByName(redstoneWeaponItemNameColor), localizePotion(redstoneWeaponsAddPotionEffect) + " " + (redstoneWeaponsEffectLevel + 1)),
    EMERALD(BaseSpecialSword.swordEmeraldMaterial, "emerald_sword", Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.getValueByName(emeraldWeaponItemNameColor), localizePotion(emeraldWeaponsAddPotionEffect) + " " + (emeraldWeaponsEffectLevel + 1)),
    OBSIDIAN(BaseSpecialSword.swordObsidianMaterial, "obsidian_sword", Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.getValueByName(obsidianWeaponItemNameColor), localizePotion(obsidianWeaponsAddPotionEffect) + " " + (obsidianWeaponsEffectLevel + 1)),
    LAVA(BaseSpecialSword.swordLavaMaterial, "lava_sword", ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.getValueByName(lavaWeaponItemNameColor), "Sets on Fire"),
    GUARDIAN(BaseSpecialSword.swordGuardianMaterial, "guardian_sword", ModItems.guardianScale, TextFormatting.getValueByName(guardianWeaponItemNameColor), localizePotion(guardianWeaponsAddPotionEffect) + " " + (guardianWeaponsEffectLevel + 1)),
    SUPER_STAR(BaseSpecialSword.swordSuperStarMaterial, "super_star_sword", ModItems.witherBone, TextFormatting.getValueByName(superStarWeaponItemNameColor), localizePotion(superStarWeaponsAddPotionEffect) + " " + (superStarWeaponsEffectLevel + 1)),
    ENDER_DRAGON(BaseSpecialSword.swordEnderDragonMaterial, "ender_dragon_sword", ModItems.enderDragonScale, TextFormatting.getValueByName(enderDragonWeaponItemNameColor), localizePotion(enderDragonWeaponsAddPotionEffect) + " " + (enderDragonWeaponsEffectLevel + 1));

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

    Swords(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairEasy = repairEasyIn;
        this.repairExpert = repairExpertIn;
        this.textFormatting = textFormattingIn;
        this.effect = effectIn;
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
