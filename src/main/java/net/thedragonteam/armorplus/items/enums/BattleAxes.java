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

import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.PotionUtils.localizePotion;

public enum BattleAxes implements IStringSerializable {
    COAL(battleAxeCoalMaterial, "coal", Items.COAL, COAL_BLOCK, getValueByName(coalWeaponItemNameColor), localizePotion(coalWeaponsAddPotionEffect) + " " + (coalWeaponsEffectLevel + 1), 8.0F),
    LAPIS(battleAxeLapisMaterial, "lapis", new ItemStack(Items.DYE, 1, 4), LAPIS_BLOCK, getValueByName(lapisWeaponItemNameColor), localizePotion(lapisWeaponsAddPotionEffect) + " " + (lapisWeaponsEffectLevel + 1), 9.0F),
    REDSTONE(battleAxeRedstoneMaterial, "redstone", Items.REDSTONE, REDSTONE_BLOCK, getValueByName(redstoneWeaponItemNameColor), localizePotion(redstoneWeaponsAddPotionEffect) + " " + (redstoneWeaponsEffectLevel + 1), 9.0F),
    EMERALD(battleAxeEmeraldMaterial, "emerald", Items.EMERALD, EMERALD_BLOCK, getValueByName(emeraldWeaponItemNameColor), localizePotion(emeraldWeaponsAddPotionEffect) + " " + (emeraldWeaponsEffectLevel + 1), 10.0F),
    OBSIDIAN(battleAxeObsidianMaterial, "obsidian", Blocks.OBSIDIAN, compressedObsidian, getValueByName(obsidianWeaponItemNameColor), localizePotion(obsidianWeaponsAddPotionEffect) + " " + (obsidianWeaponsEffectLevel + 1), 10.5F),
    LAVA(battleAxeLavaMaterial, "lava", lavaCrystal, new ItemStack(lavaCrystal, 1, 1), getValueByName(lavaWeaponItemNameColor), "Sets on Fire", 11.5F),
    GUARDIAN(battleAxeGuardianMaterial, "guardian", new ItemStack(materials, 1, 1), getValueByName(guardianWeaponItemNameColor), localizePotion(guardianWeaponsAddPotionEffect) + " " + (guardianWeaponsEffectLevel + 1), 14.0F),
    SUPER_STAR(battleAxeSuperStarMaterial, "super_star", new ItemStack(materials, 1, 2), getValueByName(superStarWeaponItemNameColor), localizePotion(superStarWeaponsAddPotionEffect) + " " + (superStarWeaponsEffectLevel + 1), 15.0F),
    ENDER_DRAGON(battleAxeEnderDragonMaterial, "ender_dragon", new ItemStack(materials, 1, 3), getValueByName(enderDragonWeaponItemNameColor), localizePotion(enderDragonWeaponsAddPotionEffect) + " " + (enderDragonWeaponsEffectLevel + 1), 16.0F);

    private final String name;

    private final Item.ToolMaterial material;

    private final Item repairEasy;

    private final Item repairExpert;

    private final TextFormatting textFormatting;

    private final String effect;

    private final float efficiency;

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Block repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, Item.getItemFromBlock(repairEasyIn), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, repairEasyIn.getItem(), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, repairEasyIn.getItem(), repairExpertIn.getItem(), textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairBoth, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, repairBoth, repairBoth, textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairBoth, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this(materialIn, nameIn, repairBoth.getItem(), repairBoth.getItem(), textFormattingIn, effectIn, efficiencyIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, String effectIn, float efficiencyIn) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairEasy = repairEasyIn;
        this.repairExpert = repairExpertIn;
        this.textFormatting = textFormattingIn;
        this.effect = effectIn;
        this.efficiency = efficiencyIn;
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

    public float getEfficiency() {
        return efficiency;
    }
}
