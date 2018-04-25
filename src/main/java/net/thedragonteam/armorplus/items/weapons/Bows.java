/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.weapons;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;
import net.thedragonteam.armorplus.api.properties.iface.IRepairable;
import net.thedragonteam.armorplus.registry.ModBlocks;

import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Bows implements IRepairable, IRemovable {
    COAL("coal", new BowStats(coal), getItemStack(Blocks.COAL_BLOCK), getValueByName(coal.weapons.itemNameColor), coalBow, global_registry.enableCoalWeapons[2]),
    LAPIS("lapis", new BowStats(lapis), getItemStack(Blocks.LAPIS_BLOCK), getValueByName(lapis.weapons.itemNameColor), lapisBow, global_registry.enableEmeraldWeapons[2]),
    REDSTONE("redstone", new BowStats(redstone), getItemStack(Blocks.REDSTONE_BLOCK), getValueByName(redstone.weapons.itemNameColor), redstoneBow, global_registry.enableRedstoneWeapons[2]),
    EMERALD("emerald", new BowStats(emerald), getItemStack(Blocks.EMERALD_BLOCK), getValueByName(emerald.weapons.itemNameColor), emeraldBow, global_registry.enableEmeraldWeapons[2]),
    OBSIDIAN("obsidian", new BowStats(obsidian), getItemStack(ModBlocks.compressedObsidian), getValueByName(obsidian.weapons.itemNameColor), obsidianBow, global_registry.enableObsidianWeapons[2]),
    LAVA("infused_lava", new BowStats(lava), getItemStack(lavaCrystal, 1), getValueByName(lava.weapons.itemNameColor), lavaBow, global_registry.enableLavaWeapons[2]),
    GUARDIAN("guardian", new BowStats(guardian), getItemStack(materials, 1), getValueByName(guardian.weapons.itemNameColor), guardianBow, global_registry.enableGuardianWeapons[2]),
    SUPER_STAR("super_star", new BowStats(super_star), getItemStack(materials, 2), getValueByName(super_star.weapons.itemNameColor), superStarBow, global_registry.enableSuperStarWeapons[2]),
    ENDER_DRAGON("ender_dragon", new BowStats(ender_dragon), getItemStack(materials, 3), getValueByName(ender_dragon.weapons.itemNameColor), enderDragonBow, global_registry.enableEnderDragonWeapons[2]);

    private final String name;
    private final BowStats bowStats;
    private final boolean isEnabled;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final Item bowItem;

    Bows(String nameIn, BowStats bowStats, ItemStack repairStackIn, TextFormatting textFormattingIn, Item bowItemIn, boolean isEnabled) {
        this.name = nameIn;
        this.bowStats = bowStats;
        this.isEnabled = isEnabled;
        if (repairStackIn == null) repairStackIn = EMPTY;
        this.repairStack = repairStackIn;
        this.textFormatting = textFormattingIn;
        this.bowItem = bowItemIn;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public double getDamage() {
        return this.bowStats.getBonusDamage();
    }

    public int getDurability() {
        return this.bowStats.getDurability();
    }

    public Item getBowItem() {
        return this.bowItem;
    }

    public ItemStack getRepairStack() {
        return this.repairStack;
    }

    public TextFormatting getTextFormatting() {
        return this.textFormatting;
    }

    private static class BowStats {
        private final int durabilityIn;
        private final double bonusDamage;

        BowStats(OriginMaterial material) {
            this(material.weapons.bow.durability, material.weapons.bow.arrowBonusDamage);
        }

        private BowStats(int durability, double bonusDamage) {
            this.durabilityIn = durability;
            this.bonusDamage = bonusDamage;
        }

        double getBonusDamage() {
            return bonusDamage;
        }

        int getDurability() {
            return durabilityIn;
        }
    }
}
