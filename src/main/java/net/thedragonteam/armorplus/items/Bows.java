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

import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;

public enum Bows implements IStringSerializable {
    COAL(coalBowDurability, "coal", coalBowArrowBonusDamage, Items.COAL, Blocks.COAL_BLOCK, getValueByName(coalWeaponItemNameColor), coalBow),
    LAPIS(lapisBowDurability, "lapis", lapisBowArrowBonusDamage, new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, getValueByName(lapisWeaponItemNameColor), lapisBow),
    REDSTONE(redstoneBowDurability, "redstone", redstoneBowArrowBonusDamage, Items.REDSTONE, Blocks.REDSTONE_BLOCK, getValueByName(redstoneWeaponItemNameColor), redstoneBow),
    EMERALD(emeraldBowDurability, "emerald", emeraldBowArrowBonusDamage, Items.EMERALD, Blocks.EMERALD_BLOCK, getValueByName(emeraldWeaponItemNameColor), emeraldBow),
    OBSIDIAN(obsidianBowDurability, "obsidian", obsidianBowArrowBonusDamage, Blocks.OBSIDIAN, ModBlocks.compressedObsidian, getValueByName(obsidianWeaponItemNameColor), obsidianBow),
    LAVA(lavaBowDurability, "lava", lavaBowArrowBonusDamage, lavaCrystal, new ItemStack(lavaCrystal, 1, 1), getValueByName(lavaWeaponItemNameColor), lavaBow),
    GUARDIAN(guardianBowDurability, "guardian", guardianBowArrowBonusDamage, new ItemStack(materials, 1, 1), getValueByName(guardianWeaponItemNameColor), guardianBow),
    SUPER_STAR(superStarBowDurability, "super_star", superStarBowArrowBonusDamage, new ItemStack(materials, 1, 2), getValueByName(superStarWeaponItemNameColor), superStarBow),
    ENDER_DRAGON(enderDragonBowDurability, "ender_dragon", enderDragonBowArrowBonusDamage, new ItemStack(materials, 1, 3), getValueByName(enderDragonWeaponItemNameColor), enderDragonBow);

    private final String name;

    private final int durability;

    private final double damage;

    private final Item repairEasy;

    private final Item repairExpert;

    private final TextFormatting textFormatting;

    private final Item bowItem;

    Bows(int durabilityIn, String nameIn, double damageIn, Block repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, Item.getItemFromBlock(repairEasyIn), Item.getItemFromBlock(repairExpertIn), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, ItemStack repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn.getItem(), Item.getItemFromBlock(repairExpertIn), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn.getItem(), repairExpertIn.getItem(), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, Item repairBoth, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairBoth, repairBoth, textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, ItemStack repairBoth, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairBoth.getItem(), repairBoth.getItem(), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, double damageIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this.name = nameIn;
        this.durability = durabilityIn;
        this.damage = damageIn;
        this.repairEasy = repairEasyIn;
        this.repairExpert = repairExpertIn;
        this.textFormatting = textFormattingIn;
        this.bowItem = bowItemIn;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public double getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public Item getBowItem() {
        return bowItem;
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
