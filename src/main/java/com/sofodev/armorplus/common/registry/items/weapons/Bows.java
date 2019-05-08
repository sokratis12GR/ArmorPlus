/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.weapons;

import com.sofodev.armorplus.api.properties.iface.IDurable;
import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.registry.APItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.TextFormatting;

import static com.sofodev.armorplus.common.util.Utils.getBlock;
import static com.sofodev.armorplus.common.util.Utils.getItem;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Bows implements IRepairable, IRemovable, IDurable {
    COAL(COAL_BLOCK, ModConfig.RegistryConfig.coal, APItems.coalBow),
    LAPIS(LAPIS_BLOCK, ModConfig.RegistryConfig.lapis, APItems.lapisBow),
    REDSTONE(REDSTONE_BLOCK, ModConfig.RegistryConfig.redstone, APItems.redstoneBow),
    EMERALD(EMERALD_BLOCK, ModConfig.RegistryConfig.emerald, APItems.emeraldBow),
    OBSIDIAN(getBlock("compressed_obsidian"), ModConfig.RegistryConfig.obsidian, APItems.obsidianBow),
    INFUSED_LAVA(getItem("infused_lava_crystal"), ModConfig.RegistryConfig.lava, APItems.lavaBow),
    GUARDIAN(getItem("guardian_scale"), ModConfig.RegistryConfig.guardian, APItems.guardianBow),
    SUPER_STAR(getItem("wither_bone"), ModConfig.RegistryConfig.super_star, APItems.superStarBow),
    ENDER_DRAGON(getItem("ender_dragon_scale"), ModConfig.RegistryConfig.ender_dragon, APItems.enderDragonBow);

    private final BowStats bowStats;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final Item bowItem;

    Bows(IItemProvider repairItem, ModConfig.RegistryConfig.OriginMaterial material, Item bowItemIn) {
        this.bowStats = new BowStats(material);
        this.repairStack = new ItemStack(repairItem);
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        this.bowItem = bowItemIn;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public double getDamage() {
        return this.bowStats.getBonusDamage();
    }

    @Override
    public int getDurability(boolean unbreakable) {
        return this.bowStats.getDurability();
    }

    public Item getBowItem() {
        return this.bowItem;
    }

    @Override
    public ItemStack getRepairStack() {
        return this.repairStack;
    }

    public TextFormatting getTextFormatting() {
        return this.textFormatting;
    }

    private static class BowStats {
        private final int durabilityIn;
        private final double bonusDamage;

        BowStats(ModConfig.RegistryConfig.OriginMaterial material) {
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
