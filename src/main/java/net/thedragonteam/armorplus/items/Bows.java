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

import static net.thedragonteam.armorplus.ARPConfig.*;

public enum Bows implements IStringSerializable {
    COAL(coalBowDurability, "coal_bow", coalBowArrowBonusDamage, Items.COAL, Blocks.COAL_BLOCK, TextFormatting.getValueByName(coalWeaponItemNameColor), ModItems.coalBow),
    LAPIS(lapisBowDurability, "lapis_bow", lapisBowArrowBonusDamage, new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.getValueByName(lapisWeaponItemNameColor), ModItems.lapisBow),
    REDSTONE(redstoneBowDurability, "redstone_bow", redstoneBowArrowBonusDamage, Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.getValueByName(redstoneWeaponItemNameColor), ModItems.redstoneBow),
    EMERALD(emeraldBowDurability, "emerald_bow", emeraldBowArrowBonusDamage, Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.getValueByName(emeraldWeaponItemNameColor), ModItems.emeraldBow),
    OBSIDIAN(obsidianBowDurability, "obsidian_bow", obsidianBowArrowBonusDamage, Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.getValueByName(obsidianWeaponItemNameColor), ModItems.obsidianBow),
    LAVA(lavaBowDurability, "lava_bow", lavaBowArrowBonusDamage, ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.getValueByName(lavaWeaponItemNameColor), ModItems.lavaBow),
    GUARDIAN(guardianBowDurability, "guardian_bow", guardianBowArrowBonusDamage, ModItems.guardianScale, ModItems.guardianScale, TextFormatting.getValueByName(guardianWeaponItemNameColor), ModItems.guardianBow),
    SUPER_STAR(superStarBowDurability, "super_star_bow", superStarBowArrowBonusDamage, ModItems.witherBone, ModItems.witherBone, TextFormatting.getValueByName(superStarWeaponItemNameColor), ModItems.superStarBow),
    ENDER_DRAGON(enderDragonBowDurability, "ender_dragon_bow", enderDragonBowArrowBonusDamage, ModItems.enderDragonScale, ModItems.enderDragonScale, TextFormatting.getValueByName(enderDragonWeaponItemNameColor), ModItems.enderDragonBow);

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
