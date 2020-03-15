package com.sofodev.armorplus.registry.items.tools.properties;

import com.sofodev.armorplus.registry.items.APRarity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.function.Supplier;

import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.registry.items.APRarity.*;

public enum APToolProperties implements IItemTier {
    COAL_PROP(COAL, 1, 60, 2.0f, 0f, 10, Items.COAL),
    REDSTONE_PROP(REDSTONE, 2, 250, 6.0f, 1f, 60, Items.REDSTONE),
    LAPIS_PROP(LAPIS, 2, 250, 6.0f, 1f, 60, Items.LAPIS_LAZULI),
    EMERALD_PROP(EMERALD, 3, 1561, 8.0f, 3f, 60, Items.EMERALD),
    OBSIDIAN_PROP(OBSIDIAN, 3, 4200, 6.0f, 2f, 30, Items.OBSIDIAN),
    INFUSED_LAVA_PROP(INFUSED_LAVA, 4, 3000, 10.0f, 3f, 60, INFUSED_LAVA_CRYSTAL.get()),
    GUARDIAN_PROP(GUARDIAN, 4, 6000, 14.0f, 5f, 70, GUARDIAN_SCALE.get()),
    SUPER_STAR_PROP(SUPER_STAR, 4, 6000, 14.0f, 5f, 70, WITHER_BONE.get()),
    ENDER_DRAGON_PROP(ENDER_DRAGON, 4, 6000, 14.0f, 5f, 70, ENDER_DRAGON_SCALE.get()),
    SLAYER_PROP(SLAYER, 5, 9001, 20.0f, 6.0f, 100, THE_ULTIMATE_MATERIAL.get()),
    ;

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;
    private final Rarity rarity;

    APToolProperties(APRarity rarity, int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, IItemProvider... repairMaterial) {
        this.rarity = rarity.getRarity();
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = () -> Ingredient.fromItems(repairMaterial);
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    public Rarity getRarity() {
        return rarity;
    }
}
