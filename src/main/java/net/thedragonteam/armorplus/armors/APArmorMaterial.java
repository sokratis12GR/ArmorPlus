/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.BaseArmor.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ItemStackUtils.*;

public enum APArmorMaterial {
    COAL(coalArmor, "coal", Items.COAL, COAL_BLOCK, getValueByName(coalArmorItemNameColor)),
    LAPIS(lapisArmor, "lapis", getItemStack(Items.DYE, 4), LAPIS_BLOCK, getValueByName(lapisArmorItemNameColor)),
    REDSTONE(redstoneArmor, "redstone", Items.REDSTONE, REDSTONE_BLOCK, REDSTONE_BLOCK, getValueByName(redstoneArmorItemNameColor)),
    EMERALD(emeraldArmor, "emerald", Items.EMERALD, EMERALD_BLOCK, getValueByName(emeraldArmorItemNameColor)),
    OBSIDIAN(obsidianArmor, "obsidian", Blocks.OBSIDIAN, compressedObsidian, getValueByName(obsidianArmorItemNameColor)),
    LAVA(lavaArmor, "lava", lavaCrystal, getItemStack(lavaCrystal, 1), getValueByName(lavaArmorItemNameColor)),
    GUARDIAN(guardianArmor, "guardian", getItemStack(materials, 1).getItem(), getValueByName(guardianArmorItemNameColor)),
    SUPER_STAR(superStarArmor, "super_star", getItemStack(materials, 2).getItem(), getValueByName(superStarArmorItemNameColor)),
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", getItemStack(materials, 3).getItem(), getValueByName(enderDragonArmorItemNameColor)),
    ARDITE(arditeArmor, "ardite", getTICItemStack("ingots", 1), getTICItemStack("metal", 1), getValueByName(arditeArmorItemNameColor)),
    COBALT(cobaltArmor, "cobalt", getTICItemStack("ingots", 0), getTICItemStack("metal", 0), getValueByName(cobaltArmorItemNameColor)),
    MANYULLYN(manyullynArmor, "manyullyn", getTICItemStack("ingots", 2), getTICItemStack("metal", 2), getValueByName(manyullynArmorItemNameColor)),
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", getTICItemStack("ingots", 3), getTICItemStack("metal", 3), getValueByName(knightSlimeArmorItemNameColor)),
    PIG_IRON(pigIronArmor, "pig_iron", getTICItemStack("ingots", 4), getTICItemStack("metal", 4), getValueByName(pigIronArmorItemNameColor)),
    SLIME(slimeArmor, "slime", Items.SLIME_BALL, SLIME_BLOCK, getValueByName(slimeArmorItemNameColor)),
    CHICKEN(chickenArmor, "chicken", Items.FEATHER, getValueByName(chickenArmorItemNameColor));

    private final ArmorMaterial armorMaterial;

    private final String name;

    private final Item itemEasy;

    private final Item itemExpert;

    private final Item itemHellish;

    private final TextFormatting formatting;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, Item repairHellishIn, TextFormatting textFormattingIn) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.itemEasy = repairEasyIn;
        this.itemExpert = repairExpertIn;
        this.itemHellish = repairHellishIn;
        this.formatting = textFormattingIn;
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item repairAll, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairAll, repairAll, repairAll, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item repairEasy, Item repairHard, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairEasy, repairHard, repairHard, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item repairEasy, Block repairHard, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairEasy, repairHard, repairHard, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack repairEasy, Block repairHard, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairEasy, repairHard, repairHard, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Block repairEasy, Block repairHard, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairEasy, repairHard, repairHard, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack repairEasy, ItemStack repairHard, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairEasy, repairHard, repairHard, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item repairEasy, ItemStack repairHard, TextFormatting textFormattingIn) {
        this(armorMaterialIn, nameIn, repairEasy, repairHard, repairHard, textFormattingIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterial, String name, ItemStack repairEasy, ItemStack repairExpert, ItemStack repairHellish, TextFormatting textFormatting) {
        this(armorMaterial, name, getItem(repairEasy), getItem(repairExpert), getItem(repairHellish), textFormatting);
    }

    APArmorMaterial(ArmorMaterial armorMaterial, String name, ItemStack repairEasy, Block repairExpert, Block repairHellish, TextFormatting textFormatting) {
        this(armorMaterial, name, getItem(repairEasy), getItem(repairExpert), getItem(repairHellish), textFormatting);
    }

    APArmorMaterial(ArmorMaterial armorMaterial, String name, Item repairEasy, ItemStack repairExpert, ItemStack repairHellish, TextFormatting textFormatting) {
        this(armorMaterial, name, repairEasy, getItem(repairExpert), getItem(repairHellish), textFormatting);
    }

    APArmorMaterial(ArmorMaterial armorMaterial, String name, Item repairEasy, Block repairExpert, Block repairHellish, TextFormatting textFormatting) {
        this(armorMaterial, name, repairEasy, getItem(repairExpert), getItem(repairHellish), textFormatting);
    }

    APArmorMaterial(ArmorMaterial armorMaterial, String name, Block repairEasy, Block repairExpert, Block repairHellish, TextFormatting textFormatting) {
        this(armorMaterial, name, getItem(repairEasy), getItem(repairExpert), getItem(repairHellish), textFormatting);
    }

    public ArmorMaterial getArmorMaterial() {
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

    public Item getItemHellish() {
        return itemHellish;
    }

    public TextFormatting getFormatting() {
        return formatting;
    }
}
