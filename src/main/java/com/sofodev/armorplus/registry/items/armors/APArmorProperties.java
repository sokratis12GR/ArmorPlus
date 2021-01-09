package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.GlobalVars.*;
import static net.minecraft.item.Items.*;
import static net.minecraft.util.SoundEvents.*;

public enum APArmorProperties implements IArmorMaterial {
    COAL_PROP(2, new int[]{1, 1, 2, 1}, 8, ITEM_ARMOR_EQUIP_LEATHER, COAL),
    REDSTONE_PROP(11, new int[]{1, 2, 3, 2}, 8, ITEM_ARMOR_EQUIP_IRON, REDSTONE),
    LAPIS_PROP(11, new int[]{1, 2, 3, 2}, 8, ITEM_ARMOR_EQUIP_IRON, LAPIS_LAZULI),
    CHICKEN_PROP(1, new int[]{1, 1, 2, 1}, 8, ITEM_ARMOR_EQUIP_LEATHER, FEATHER),
    SLIME_PROP(1, new int[]{1, 1, 2, 1}, 8, ITEM_ARMOR_EQUIP_LEATHER, SLIME_BALL),
    EMERALD_PROP(35, new int[]{3, 6, 8, 3}, 16, ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, EMERALD, EMERALD_BLOCK),
    OBSIDIAN_PROP(40, new int[]{3, 6, 7, 3}, 16, ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, OBSIDIAN),
    INFUSED_LAVA_PROP(55, new int[]{3, 6, 8, 3}, 16, ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, INFUSED_LAVA_CRYSTAL),
    GUARDIAN_PROP(60, new int[]{4, 7, 8, 3}, 30, ITEM_ARMOR_EQUIP_NETHERITE, 2.0f, GUARDIAN_SCALE),
    SUPER_STAR_PROP(60, new int[]{4, 7, 8, 3}, 30, ITEM_ARMOR_EQUIP_NETHERITE, 2.0f, WITHER_BONE),
    ENDER_DRAGON_PROP(60, new int[]{4, 7, 8, 3}, 30, ITEM_ARMOR_EQUIP_NETHERITE, 2.0f, ENDER_DRAGON_SCALE),
    ARDITE_PROP(55, new int[]{2, 3, 4, 2}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f),
    COBALT_PROP(44, new int[]{2, 3, 4, 2}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f),
    KNIGHT_SLIME_PROP(33, new int[]{2, 3, 4, 3}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f),
    PIG_IRON_PROP(33, new int[]{2, 3, 4, 3}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f),
    MANYULLYN_PROP(66, new int[]{3, 5, 5, 3}, 30, ITEM_ARMOR_EQUIP_NETHERITE, 2.0f),
    SLAYER_PROP(160, new int[]{4, 8, 9, 4}, 60, ITEM_ARMOR_EQUIP_NETHERITE, 5.0f, THE_ULTIMATE_MATERIAL),
    /*Enhanced Vanilla Armor*/
    ENHANCED_CHAINMAIL_PROP(false, 20, new int[]{2, 5, 6, 3}, 15, ITEM_ARMOR_EQUIP_CHAIN, 1.0f, IRON_INGOT, CHAINMAIL),
    ENHANCED_GOLD_PROP(false, 10, new int[]{2, 4, 5, 2}, 30, ITEM_ARMOR_EQUIP_GOLD, 1.0f, GOLD_INGOT),
    ENHANCED_IRON_PROP(false, 20, new int[]{2, 5, 6, 2}, 15, ITEM_ARMOR_EQUIP_IRON, 2.0f, IRON_INGOT),
    ENHANCED_DIAMOND_PROP(false, 35, new int[]{3, 6, 7, 3}, 20, ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, DIAMOND),
    ENHANCED_NETHERITE_PROP(false, 47, new int[]{3, 6, 8, 3}, 25, ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, NETHERITE_INGOT),
    ;
    /**
     * Holds the 'base' maxDamage that each armorType have.
     */
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durability;
    private final float toughness;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final Ingredient repairMaterial;
    private final boolean special;

    APArmorProperties(int durability, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, IItemProvider... repairMaterial) {
        this(true, durability, damageReduction, enchantability, soundEvent, toughness, repairMaterial);
    }

    APArmorProperties(int durability, int[] damageReduction, int enchantability, SoundEvent soundEvent, IItemProvider... repairMaterial) {
        this(true, durability, damageReduction, enchantability, soundEvent, 0.0f, repairMaterial);
    }

    APArmorProperties(boolean special, int durability, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, IItemProvider... repairMaterial) {
        this.special = special;
        this.name = name().toLowerCase().replace("_prop", "").replace("enhanced_", "");
        this.durability = durability;
        this.toughness = toughness;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.repairMaterial = repairMaterial.length == 0 ? Ingredient.EMPTY : Ingredient.fromItems(repairMaterial);
    }

    @Override
    public int getDurability(EquipmentSlotType slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slot) {
        return damageReduction[slot.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return special ? (ArmorPlus.MODID + ":" + name) : ("minecraft:" + name);
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

    @Override
    public String toString() {
        return "APArmorProperties{" +
                "name='" + name + '\'' +
                ", durability=" + durability +
                ", toughness=" + toughness +
                ", damageReduction=" + Arrays.toString(damageReduction) +
                ", enchantability=" + enchantability +
                ", soundEvent=" + soundEvent +
                ", repairMaterial=" + repairMaterial +
                ", special=" + special +
                '}';
    }
}