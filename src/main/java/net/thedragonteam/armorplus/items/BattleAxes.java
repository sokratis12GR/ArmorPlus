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
import net.thedragonteam.armorplus.items.base.BaseBattleAxe;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;

public enum BattleAxes implements IStringSerializable {
    COAL(BaseBattleAxe.battleAxeCoalMaterial, "coal_battle_axe", Items.COAL, COAL_BLOCK, getValueByName(coalWeaponItemNameColor), localizePotion(coalWeaponsAddPotionEffect) + " " + coalWeaponsEffectLevel, 8.0F),
    LAPIS(BaseBattleAxe.battleAxeLapisMaterial, "lapis_battle_axe", new ItemStack(Items.DYE, 1, 4), LAPIS_BLOCK, getValueByName(lapisWeaponItemNameColor), localizePotion(lapisWeaponsAddPotionEffect) + " " + lapisWeaponsEffectLevel, 9.0F),
    REDSTONE(BaseBattleAxe.battleAxeRedstoneMaterial, "redstone_battle_axe", Items.REDSTONE, REDSTONE_BLOCK, getValueByName(redstoneWeaponItemNameColor), localizePotion(redstoneWeaponsAddPotionEffect) + " " + redstoneWeaponsEffectLevel, 9.0F),
    EMERALD(BaseBattleAxe.battleAxeEmeraldMaterial, "emerald_battle_axe", Items.EMERALD, EMERALD_BLOCK, getValueByName(emeraldWeaponItemNameColor), localizePotion(emeraldWeaponsAddPotionEffect) + " " + emeraldWeaponsEffectLevel, 10.0F),
    OBSIDIAN(BaseBattleAxe.battleAxeObsidianMaterial, "obsidian_battle_axe", Blocks.OBSIDIAN, compressedObsidian, getValueByName(obsidianWeaponItemNameColor), localizePotion(obsidianWeaponsAddPotionEffect) + " " + obsidianWeaponsEffectLevel, 10.5F),
    LAVA(BaseBattleAxe.battleAxeLavaMaterial, "lava_battle_axe", lavaCrystal, new ItemStack(lavaCrystal, 1, 1), getValueByName(lavaWeaponItemNameColor), "Sets on Fire", 11.5F),
    GUARDIAN(BaseBattleAxe.battleAxeGuardianMaterial, "guardian_battle_axe", guardianScale, getValueByName(guardianWeaponItemNameColor), localizePotion(guardianWeaponsAddPotionEffect) + " " + guardianWeaponsEffectLevel, 14.0F),
    SUPER_STAR(BaseBattleAxe.battleAxeSuperStarMaterial, "super_star_battle_axe", witherBone, getValueByName(superStarWeaponItemNameColor), localizePotion(superStarWeaponsAddPotionEffect) + " " + superStarWeaponsEffectLevel, 15.0F),
    ENDER_DRAGON(BaseBattleAxe.battleAxeEnderDragonMaterial, "ender_dragon_battle_axe", enderDragonScale, getValueByName(enderDragonWeaponItemNameColor), localizePotion(enderDragonWeaponsAddPotionEffect) + " " + enderDragonWeaponsEffectLevel, 16.0F);

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
