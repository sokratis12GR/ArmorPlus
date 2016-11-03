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
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

public enum BattleAxes implements IStringSerializable {
    COAL(ModItems.battleAxeCoalMaterial, "coal_battle_axe", Items.COAL, Blocks.COAL_BLOCK, TextFormatting.GRAY, "Applies Blindness"),
    LAPIS(ModItems.battleAxeLapisMaterial, "lapis_battle_axe", new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.DARK_BLUE, "Applies Nausea 2"),
    REDSTONE(ModItems.battleAxeRedstoneMaterial, "redstone_battle_axe", Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.DARK_RED, "Applies Slowness 2"),
    EMERALD(ModItems.battleAxeEmeraldMaterial, "emerald_battle_axe", Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.DARK_GREEN, "Applies Fatigue 2"),
    OBSIDIAN(ModItems.battleAxeObsidianMaterial, "obsidian_battle_axe", Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.DARK_GRAY, "Applies Weakness 2"),
    LAVA(ModItems.battleAxeLavaMaterial, "lava_battle_axe", ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.GOLD, "Sets on Fire"),
    GUARDIAN(ModItems.battleAxeGuardianMaterial, "guardian_battle_axe", ModItems.guardianScale, ModItems.guardianScale, TextFormatting.AQUA, "Applies Nausea 2"),
    SUPER_STAR(ModItems.battleAxeSuperStarMaterial, "super_star_battle_axe", ModItems.witherBone, ModItems.witherBone, TextFormatting.WHITE, "Applies Wither 2"),
    ENDER_DRAGON(ModItems.battleAxeEnderDragonMaterial, "ender_dragon_battle_axe", ModItems.enderDragonScale, ModItems.enderDragonScale, TextFormatting.DARK_PURPLE, "Applies Wither 4");

    private final String name;

    private final Item.ToolMaterial material;

    private final Item repairEasy;

    private final Item repairExpert;

    private final TextFormatting textFormatting;

    private final String effect;


    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Block repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, Item.getItemFromBlock(repairEasyIn), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn.getItem(), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn.getItem(), repairExpertIn.getItem(), textFormattingIn, effectIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
        this(materialIn, nameIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, effectIn);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, String effectIn) {
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
