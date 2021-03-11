package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.armors.APRepair;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import static com.sofodev.armorplus.utils.Utils.getRepairStacks;
import static net.minecraft.item.Items.*;

public enum APToolProperties implements IItemTier {
    COAL_PROP(1, 60, 2.0f, 0f, 10, COAL),
    REDSTONE_PROP(2, 250, 6.0f, 1f, 60, REDSTONE),
    LAPIS_PROP(2, 250, 6.0f, 1f, 60, LAPIS_LAZULI),
    EMERALD_PROP(3, 1561, 8.0f, 3f, 60, EMERALD),
    OBSIDIAN_PROP(3, 4200, 6.0f, 2f, 30, OBSIDIAN),
    INFUSED_LAVA_PROP(4, 3000, 10.0f, 3f, 60, "infused_lava_crystal"),
    GUARDIAN_PROP(4, 6000, 14.0f, 5f, 70, "guardian_scale"),
    SUPER_STAR_PROP(4, 6000, 14.0f, 5f, 70, "wither_bone"),
    ENDER_DRAGON_PROP(4, 6000, 14.0f, 5f, 70, "ender_dragon_scale"),
    SLAYER_PROP(5, 9001, 20.0f, 6.0f, 100, "the_ultimate_material"),
    ;

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final APRepair repair;

    APToolProperties(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, IItemProvider... repair) {
        this(harvestLevel, maxUses, efficiency, attackDamage, enchantability, new APRepair(repair));
    }

    APToolProperties(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, String... repair) {
        this(harvestLevel, maxUses, efficiency, attackDamage, enchantability, new APRepair(repair));
    }

    APToolProperties(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, APRepair repair) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repair = repair;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(getRepairStacks(repair).stream());
    }

    @Override
    public String toString() {
        return "APToolProperties{" +
                "harvestLevel=" + harvestLevel +
                ", maxUses=" + maxUses +
                ", efficiency=" + efficiency +
                ", attackDamage=" + attackDamage +
                ", enchantability=" + enchantability +
                ", repairMaterial=" + repair +
                '}';
    }
}