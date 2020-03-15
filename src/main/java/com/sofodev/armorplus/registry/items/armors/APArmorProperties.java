package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.sofodev.armorplus.registry.APItems.*;
import static net.minecraft.item.Items.*;
import static net.minecraft.util.SoundEvents.*;

public enum APArmorProperties implements IArmorMaterial {
    COAL_PROP(true, 2, new int[]{1, 1, 2, 1}, 8, ITEM_ARMOR_EQUIP_LEATHER, 0.0f, COAL),
    REDSTONE_PROP(true, 11, new int[]{1, 2, 3, 2}, 8, ITEM_ARMOR_EQUIP_IRON, 0.0f, REDSTONE),
    LAPIS_PROP(true, 11, new int[]{1, 2, 3, 2}, 8, ITEM_ARMOR_EQUIP_IRON, 0.0f, LAPIS_LAZULI),
    CHICKEN_PROP(true, 1, new int[]{1, 1, 2, 1}, 8, ITEM_ARMOR_EQUIP_LEATHER, 0.0f, FEATHER),
    SLIME_PROP(true, 1, new int[]{1, 1, 2, 1}, 8, ITEM_ARMOR_EQUIP_LEATHER, 0.0f, SLIME_BALL),
    EMERALD_PROP(true, 35, new int[]{3, 6, 8, 3}, 16, ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, EMERALD, EMERALD_BLOCK),
    OBSIDIAN_PROP(true, 40, new int[]{3, 6, 7, 3}, 16, ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, OBSIDIAN),
    INFUSED_LAVA_PROP(true, 55, new int[]{3, 6, 8, 3}, 16, ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, INFUSED_LAVA_CRYSTAL.get()),
    GUARDIAN_PROP(true, 60, new int[]{4, 7, 8, 3}, 30, ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, GUARDIAN_SCALE.get()),
    SUPER_STAR_PROP(true, 60, new int[]{4, 7, 8, 3}, 30, ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, WITHER_BONE.get()),
    ENDER_DRAGON_PROP(true, 60, new int[]{4, 7, 8, 3}, 30, ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, ENDER_DRAGON_SCALE.get()),
    ARDITE_PROP(true, 55, new int[]{2, 3, 4, 2}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f, AIR),
    COBALT_PROP(true, 44, new int[]{2, 3, 4, 2}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f, AIR),
    KNIGHT_SLIME_PROP(true, 33, new int[]{2, 3, 4, 3}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f, AIR),
    PIG_IRON_PROP(true, 33, new int[]{2, 3, 4, 3}, 16, ITEM_ARMOR_EQUIP_IRON, 1.0f, AIR),
    MANYULLYN_PROP(true, 66, new int[]{3, 5, 5, 3}, 30, ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, AIR),
    SLAYER_PROP(true, 160, new int[]{4, 8, 9, 4}, 60, ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, THE_ULTIMATE_MATERIAL.get()),
    /*Enhanced Vanilla Armor*/
    ENHANCED_CHAINMAIL_PROP(false, 20, new int[]{2, 5, 6, 3}, 15, ITEM_ARMOR_EQUIP_CHAIN, 1.0f, IRON_INGOT, CHAINMAIL.get()),
    ENHANCED_GOLD_PROP(false, 10, new int[]{2, 4, 6, 2}, 30, ITEM_ARMOR_EQUIP_GOLD, 1.0f, GOLD_INGOT),
    ENHANCED_IRON_PROP(false, 20, new int[]{2, 5, 6, 2}, 15, ITEM_ARMOR_EQUIP_IRON, 2.0f, IRON_INGOT),
    ENHANCED_DIAMOND_PROP(false, 35, new int[]{3, 6, 8, 3}, 20, ITEM_ARMOR_EQUIP_DIAMOND, 4.0f, DIAMOND);

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

    APArmorProperties(boolean special, int durability, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, IItemProvider... repairMaterial) {
        this.special = special;
        this.name = name().toLowerCase().replace("_prop", "").replace("enhanced_", "");
        this.durability = durability;
        this.toughness = toughness;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.repairMaterial = Ingredient.fromItems(repairMaterial);
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
}
