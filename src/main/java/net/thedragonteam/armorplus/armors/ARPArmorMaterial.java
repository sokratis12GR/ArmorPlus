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

import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.armors.base.BaseArmor.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getTICItem;

public enum ARPArmorMaterial {
    COAL(coalArmor, "coal", Items.COAL, COAL_BLOCK, getValueByName(coalArmorItemNameColor)),
    LAPIS(lapisArmor, "lapis", getItemStack(Items.DYE, 4), LAPIS_BLOCK, getValueByName(lapisArmorItemNameColor)),
    REDSTONE(redstoneArmor, "redstone", Items.REDSTONE, REDSTONE_BLOCK, getValueByName(redstoneArmorItemNameColor)),
    EMERALD(emeraldArmor, "emerald", Items.EMERALD, EMERALD_BLOCK, getValueByName(emeraldArmorItemNameColor)),
    OBSIDIAN(obsidianArmor, "obsidian", Blocks.OBSIDIAN, compressedObsidian, getValueByName(obsidianArmorItemNameColor)),
    LAVA(lavaArmor, "lava", lavaCrystal, getItemStack(lavaCrystal, 1), getValueByName(lavaArmorItemNameColor)),
    GUARDIAN(guardianArmor, "guardian", getItemStack(materials, 1).getItem(), getValueByName(guardianArmorItemNameColor)),
    SUPER_STAR(superStarArmor, "super_star", getItemStack(materials, 2).getItem(), getValueByName(superStarArmorItemNameColor)),
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", getItemStack(materials, 3).getItem(), getValueByName(enderDragonArmorItemNameColor)),
    ARDITE(arditeArmor, "ardite", getTICItem("ingots", 1), getTICItem("metal", 1), getValueByName(arditeArmorItemNameColor)),
    COBALT(cobaltArmor, "cobalt", getTICItem("ingots", 0), getTICItem("metal", 0), getValueByName(cobaltArmorItemNameColor)),
    MANYULLYN(manyullynArmor, "manyullyn", getTICItem("ingots", 2), getTICItem("metal", 2), getValueByName(manyullynArmorItemNameColor)),
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", getTICItem("ingots", 3), getTICItem("metal", 3), getValueByName(knightSlimeArmorItemNameColor)),
    PIG_IRON(pigIronArmor, "pig_iron", getTICItem("ingots", 4), getTICItem("metal", 4), getValueByName(pigIronArmorItemNameColor)),
    SLIME(slimeArmor, "slime", Items.SLIME_BALL, SLIME_BLOCK, getValueByName(slimeArmorItemNameColor)),
    CHICKEN(chickenArmor, "chicken", Items.FEATHER, getValueByName(chickenArmorItemNameColor));

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
