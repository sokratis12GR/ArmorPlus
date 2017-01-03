/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.armors.base.BaseArmor;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

public enum ARPArmorMaterial {
    COAL(BaseArmor.coalArmor, "coal", Items.COAL, Blocks.COAL_BLOCK, TextFormatting.getValueByName(APConfig.coalArmorItemNameColor)),
    EMERALD(BaseArmor.emeraldArmor, "emerald", Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.getValueByName(APConfig.emeraldArmorItemNameColor)),
    LAPIS(BaseArmor.lapisArmor, "lapis", new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.getValueByName(APConfig.lapisArmorItemNameColor)),
    LAVA(BaseArmor.lavaArmor, "lava", ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.getValueByName(APConfig.lavaArmorItemNameColor)),
    REDSTONE(BaseArmor.redstoneArmor, "redstone", Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.getValueByName(APConfig.redstoneArmorItemNameColor)),
    OBSIDIAN(BaseArmor.obsidianArmor, "obsidian", Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.getValueByName(APConfig.obsidianArmorItemNameColor)),
    ENDER_DRAGON(BaseArmor.enderDragonArmor, "ender_dragon", ModItems.enderDragonScale, TextFormatting.getValueByName(APConfig.enderDragonArmorItemNameColor)),
    GUARDIAN(BaseArmor.guardianArmor, "guardian", ModItems.guardianScale, TextFormatting.getValueByName(APConfig.guardianArmorItemNameColor)),
    SLIME(BaseArmor.slimeArmor, "slime", Items.SLIME_BALL, Blocks.SLIME_BLOCK, TextFormatting.getValueByName(APConfig.slimeArmorItemNameColor)),
    CHICKEN(BaseArmor.chickenArmor, "chicken", Items.FEATHER, TextFormatting.getValueByName(APConfig.chickenArmorItemNameColor)),
    SUPER_STAR(BaseArmor.superStarArmor, "super_star", ModItems.witherBone, TextFormatting.getValueByName(APConfig.superStarArmorItemNameColor)),
    ARDITE(BaseArmor.arditeArmor, "ardite", new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1), new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1), TextFormatting.getValueByName(APConfig.arditeArmorItemNameColor)),
    COBALT(BaseArmor.cobaltArmor, "cobalt", new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0), new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0), TextFormatting.getValueByName(APConfig.cobaltArmorItemNameColor)),
    MANYULLYN(BaseArmor.manyullynArmor, "manyullyn", new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2), new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2), TextFormatting.getValueByName(APConfig.manyullynArmorItemNameColor)),
    KNIGHT_SLIME(BaseArmor.knightSlimeArmor, "knight_slime", new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3), new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3), TextFormatting.getValueByName(APConfig.knightSlimeArmorItemNameColor)),
    PIG_IRON(BaseArmor.pigIronArmor, "pig_iron", new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4), new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4), TextFormatting.getValueByName(APConfig.pigIronArmorItemNameColor));

    private final ItemArmor.ArmorMaterial armorMaterial;

    private final String name;

    private final Item itemEasy;

    private final Item itemExpert;

    private final TextFormatting formatting;

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.itemEasy = repairEasyIn;
        this.itemExpert = repairExpertIn;
        this.formatting = textFormattingIn;
    }

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterialIn, String nameIn, Item repairBoth, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairBoth, repairBoth, textFormattingIn);
    }

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterial, String name, ItemStack repairEasy, ItemStack repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, name, repairEasy.getItem(), repairExpert.getItem(), textFormatting);
    }

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterial, String name, ItemStack repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, name, repairEasy.getItem(), Item.getItemFromBlock(repairExpert), textFormatting);
    }

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterial, String name, Item repairEasy, ItemStack repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, name, repairEasy, repairExpert.getItem(), textFormatting);
    }

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterial, String name, Item repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, name, repairEasy, Item.getItemFromBlock(repairExpert), textFormatting);
    }

    ARPArmorMaterial(ItemArmor.ArmorMaterial armorMaterial, String name, Block repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, name, Item.getItemFromBlock(repairEasy), Item.getItemFromBlock(repairExpert), textFormatting);
    }

    public ItemArmor.ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    public String getName() {
        return name;
    }

    public Item getItemEasy() {
        return itemEasy;
    }

    public Item getItemExpert() {
        return itemExpert;
    }

    public TextFormatting getFormatting() {
        return formatting;
    }
}
