package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.utils.GlobalVars;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.function.Supplier;

import static com.sofodev.armorplus.utils.GlobalVars.*;
import static com.sofodev.armorplus.utils.GlobalVars.GUARDIAN_SCALE;
import static net.minecraft.item.Items.*;

public enum APToolProperties implements IItemTier {
    COAL_PROP(1, 60, 2.0f, 0f, 10, COAL),
    REDSTONE_PROP(2, 250, 6.0f, 1f, 60, REDSTONE),
    LAPIS_PROP(2, 250, 6.0f, 1f, 60, LAPIS_LAZULI),
    EMERALD_PROP(3, 1561, 8.0f, 3f, 60, EMERALD),
    OBSIDIAN_PROP(3, 4200, 6.0f, 2f, 30, OBSIDIAN),
    INFUSED_LAVA_PROP(4, 3000, 10.0f, 3f, 60, INFUSED_LAVA_CRYSTAL),
    GUARDIAN_PROP(4, 6000, 14.0f, 5f, 70, GUARDIAN_SCALE),
    SUPER_STAR_PROP(4, 6000, 14.0f, 5f, 70, WITHER_BONE),
    ENDER_DRAGON_PROP(4, 6000, 14.0f, 5f, 70, ENDER_DRAGON_SCALE),
    SLAYER_PROP(5, 9001, 20.0f, 6.0f, 100, THE_ULTIMATE_MATERIAL),
    ;

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    APToolProperties(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, IItemProvider... repairMaterial) {
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

    @Override
    public String toString() {
        return "APToolProperties{" +
                "harvestLevel=" + harvestLevel +
                ", maxUses=" + maxUses +
                ", efficiency=" + efficiency +
                ", attackDamage=" + attackDamage +
                ", enchantability=" + enchantability +
                ", repairMaterial=" + repairMaterial +
                '}';
    }
}