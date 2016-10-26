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
public enum Bows implements IStringSerializable {
    COAL(59, "coal_bow", 2.0F, Items.COAL, Blocks.COAL_BLOCK, TextFormatting.GRAY, ModItems.coalBow),
    LAPIS(250, "lapis_bow", 3.5F, new ItemStack(Items.DYE, 1, 4), Blocks.LAPIS_BLOCK, TextFormatting.DARK_BLUE, ModItems.lapisBow),
    REDSTONE(200, "redstone_bow", 3.5F, Items.REDSTONE, Blocks.REDSTONE_BLOCK, TextFormatting.DARK_RED, ModItems.redstoneBow),
    EMERALD(1561, "emerald_bow", 5.0F, Items.EMERALD, Blocks.EMERALD_BLOCK, TextFormatting.DARK_GREEN, ModItems.emeraldBow),
    OBSIDIAN(1500, "obsidian_bow", 6.0F, Blocks.OBSIDIAN, ModBlocks.compressedObsidian, TextFormatting.DARK_GRAY, ModItems.obsidianBow),
    LAVA(1750, "lava_bow", 7.0F, ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.GOLD, ModItems.lavaBow),
    GUARDIAN(1000, "guardian_bow", 8.5F, ModItems.guardianScale, ModItems.guardianScale, TextFormatting.AQUA, ModItems.guardianBow),
    SUPER_STAR(1750, "super_star_bow", 10.0F, ModItems.witherBone, ModItems.witherBone, TextFormatting.WHITE, ModItems.superStarBow),
    ENDER_DRAGON(2000, "ender_dragon_bow", 12.0F, ModItems.enderDragonScale, ModItems.enderDragonScale, TextFormatting.DARK_PURPLE, ModItems.enderDragonBow);

    private final String name;

    private final int durability;

    private final float damage;

    private final Item repairEasy;

    private final Item repairExpert;

    private final TextFormatting textFormatting;

    private final Item bowItem;

    Bows(int durabilityIn, String nameIn, float damageIn, Block repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, Item.getItemFromBlock(repairEasyIn), Item.getItemFromBlock(repairExpertIn), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, float damageIn, ItemStack repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn.getItem(), Item.getItemFromBlock(repairExpertIn), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, float damageIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn.getItem(), repairExpertIn.getItem(), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, float damageIn, Item repairEasyIn, Block repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn, Item.getItemFromBlock(repairExpertIn), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, float damageIn, Item repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this(durabilityIn, nameIn, damageIn, repairEasyIn, repairExpertIn.getItem(), textFormattingIn, bowItemIn);
    }

    Bows(int durabilityIn, String nameIn, float damageIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
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

    public float getDamage() {
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
