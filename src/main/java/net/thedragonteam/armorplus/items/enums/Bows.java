/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.registry.ModBlocks;

import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Bows {
    COAL(coalBowDurability, "coal", coalBowArrowBonusDamage, getItemStack(Blocks.COAL_BLOCK), getValueByName(coalWeaponItemNameColor), coalBow),
    LAPIS(lapisBowDurability, "lapis", lapisBowArrowBonusDamage, getItemStack(Blocks.LAPIS_BLOCK), getValueByName(lapisWeaponItemNameColor), lapisBow),
    REDSTONE(redstoneBowDurability, "redstone", redstoneBowArrowBonusDamage, getItemStack(Blocks.REDSTONE_BLOCK), getValueByName(redstoneWeaponItemNameColor), redstoneBow),
    EMERALD(emeraldBowDurability, "emerald", emeraldBowArrowBonusDamage, getItemStack(Blocks.EMERALD_BLOCK), getValueByName(emeraldWeaponItemNameColor), emeraldBow),
    OBSIDIAN(obsidianBowDurability, "obsidian", obsidianBowArrowBonusDamage, getItemStack(ModBlocks.compressedObsidian), getValueByName(obsidianWeaponItemNameColor), obsidianBow),
    LAVA(lavaBowDurability, "infused_lava", lavaBowArrowBonusDamage, getItemStack(lavaCrystal, 1), getValueByName(lavaWeaponItemNameColor), lavaBow),
    GUARDIAN(guardianBowDurability, "guardian", guardianBowArrowBonusDamage, getItemStack(materials, 1), getValueByName(guardianWeaponItemNameColor), guardianBow),
    SUPER_STAR(superStarBowDurability, "super_star", superStarBowArrowBonusDamage, getItemStack(materials, 2), getValueByName(superStarWeaponItemNameColor), superStarBow),
    ENDER_DRAGON(enderDragonBowDurability, "ender_dragon", enderDragonBowArrowBonusDamage, getItemStack(materials, 3), getValueByName(enderDragonWeaponItemNameColor), enderDragonBow);

    private final String name;

    private final int durability;

    private final double damage;

    private final ItemStack repairExpert;

    private final TextFormatting textFormatting;

    private final Item bowItem;

    Bows(int durabilityIn, String nameIn, double damageIn, ItemStack repairExpertIn, TextFormatting textFormattingIn, Item bowItemIn) {
        this.name = nameIn;
        this.durability = durabilityIn;
        this.damage = damageIn;
        if (repairExpertIn == null) repairExpertIn = EMPTY;
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

    public ItemStack getRepairExpert() {
        return repairExpert;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }
}
