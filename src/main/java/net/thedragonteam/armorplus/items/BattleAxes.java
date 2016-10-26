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


//TODO Finish later, after school
public enum BattleAxes implements IStringSerializable {
    COAL(ModItems.battleAxeCoalMaterial, "coal_battle_axe", Items.COAL, Blocks.COAL_BLOCK, TextFormatting.GRAY, "Applies Blindness", 0),
    LAPIS(ModItems.battleAxeLapisMaterial, "lapis_battle_axe", new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.DARK_BLUE, "Applies Nausea 2", 1),
    REDSTONE(ModItems.battleAxeRedstoneMaterial, "redstone_battle_axe", Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.DARK_RED, "Applies Slowness 2", 2),
    EMERALD(ModItems.battleAxeEmeraldMaterial, "emerald_battle_axe", Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.DARK_GREEN, "Applies Fatigue 2", 3),
    OBSIDIAN(ModItems.battleAxeObsidianMaterial, "obsidian_battle_axe", Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.DARK_GRAY, "Applies Weakness 2", 4),
    LAVA(ModItems.battleAxeLavaMaterial, "lava_battle_axe", ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.GOLD, "Sets on Fire", 5),
    GUARDIAN(ModItems.battleAxeGuardianMaterial, "guardian_battle_axe", ModItems.guardianScale, ModItems.guardianScale, TextFormatting.AQUA, "Applies Nausea 2", 6),
    SUPER_STAR(ModItems.battleAxeSuperStarMaterial, "super_star_battle_axe", ModItems.witherBone, ModItems.witherBone, TextFormatting.WHITE, "Applies Wither 2", 7),
    ENDER_DRAGON(ModItems.battleAxeEnderDragonMaterial, "ender_dragon_battle_axe", ModItems.enderDragonScale, ModItems.enderDragonScale, TextFormatting.DARK_PURPLE, "Applies Wither 4", 8);

    private final String name;

    private final Item.ToolMaterial material;

    private final Item repairEasy;

    private final Item repairExpert;

    private final TextFormatting textFormatting;

    private final String effect;

    private final int id;

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Block repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn, int ID) {
        this(materialIn, nameIn, Item.getItemFromBlock(repairEasyIn), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn, ID);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn, int ID) {
        this(materialIn, nameIn, repairEasyIn.getItem(), Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn, ID);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn, int ID) {
        this(materialIn, nameIn, repairEasyIn.getItem(), repairExpertIn.getItem(), textFormattingIn, effectIn, ID);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, String effectIn, int ID) {
        this(materialIn, nameIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, effectIn, ID);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, String effectIn, int ID) {
        this(materialIn, nameIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, effectIn, ID);
    }

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, String effectIn, int ID) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairEasy = repairEasyIn;
        this.repairExpert = repairExpertIn;
        this.textFormatting = textFormattingIn;
        this.effect = effectIn;
        this.id = ID;
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

    public int getId() {
        return id;
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
