package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.item.equipment.ArmorType;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;

import static net.minecraft.sounds.SoundEvents.*;
import static net.minecraft.world.item.Items.*;

public enum APArmorProperties {
    COAL_PROP(2, new int[]{1, 1, 2, 1}, 8, ARMOR_EQUIP_LEATHER, COAL, COAL_BLOCK),
    REDSTONE_PROP(11, new int[]{1, 2, 3, 2}, 8, ARMOR_EQUIP_IRON, REDSTONE, REDSTONE_BLOCK),
    LAPIS_PROP(11, new int[]{1, 2, 3, 2}, 8, ARMOR_EQUIP_IRON, LAPIS_LAZULI, LAPIS_BLOCK),
    CHICKEN_PROP(1, new int[]{1, 1, 2, 1}, 8, ARMOR_EQUIP_LEATHER, FEATHER),
    SLIME_PROP(1, new int[]{1, 1, 2, 1}, 8, ARMOR_EQUIP_LEATHER, SLIME_BALL, SLIME_BLOCK),
    EMERALD_PROP(35, new int[]{3, 6, 8, 3}, 16, ARMOR_EQUIP_DIAMOND, 1.0f, EMERALD, EMERALD_BLOCK),
    OBSIDIAN_PROP(40, new int[]{3, 6, 7, 3}, 16, ARMOR_EQUIP_DIAMOND, 2.0f, OBSIDIAN),
    INFUSED_LAVA_PROP(55, new int[]{3, 6, 8, 3}, 16, ARMOR_EQUIP_DIAMOND, 1.0f, "infused_lava_crystal"),
    GUARDIAN_PROP(80, new int[]{4, 7, 8, 3}, 30, ARMOR_EQUIP_NETHERITE, 2.0f, "guardian_scale"),
    SUPER_STAR_PROP(80, new int[]{4, 7, 8, 3}, 30, ARMOR_EQUIP_NETHERITE, 2.0f, "wither_bone"),
    ENDER_DRAGON_PROP(80, new int[]{4, 7, 8, 3}, 30, ARMOR_EQUIP_NETHERITE, 2.0f, "ender_dragon_scale"),
    ARDITE_PROP(55, new int[]{2, 3, 4, 2}, 16, ARMOR_EQUIP_IRON, 1.0f, AIR),
    COBALT_PROP(44, new int[]{2, 3, 4, 2}, 16, ARMOR_EQUIP_IRON, 1.0f, AIR),
    KNIGHT_SLIME_PROP(33, new int[]{2, 3, 4, 3}, 16, ARMOR_EQUIP_IRON, 1.0f, AIR),
    PIG_IRON_PROP(33, new int[]{2, 3, 4, 3}, 16, ARMOR_EQUIP_IRON, 1.0f, AIR),
    MANYULLYN_PROP(66, new int[]{3, 5, 5, 3}, 30, ARMOR_EQUIP_NETHERITE, 2.0f, AIR),
    SLAYER_PROP(169, new int[]{4, 8, 9, 4}, 60, ARMOR_EQUIP_NETHERITE, 5.0f, "the_ultimate_material"),
    /*Enhanced Vanilla Armor*/
    ENHANCED_CHAINMAIL_PROP(false, 20, new int[]{2, 5, 6, 3}, 15, ARMOR_EQUIP_CHAIN, 1.0f, "chainmail"),
    ENHANCED_GOLD_PROP(false, 10, new int[]{2, 4, 5, 2}, 30, ARMOR_EQUIP_GOLD, 1.0f, GOLD_INGOT),
    ENHANCED_IRON_PROP(false, 20, new int[]{2, 5, 6, 2}, 15, ARMOR_EQUIP_IRON, 2.0f, IRON_INGOT),
    ENHANCED_DIAMOND_PROP(false, 35, new int[]{3, 6, 7, 3}, 20, ARMOR_EQUIP_DIAMOND, 3.0f, DIAMOND),
    ENHANCED_NETHERITE_PROP(false, 47, new int[]{3, 6, 8, 3}, 25, ARMOR_EQUIP_NETHERITE, 4.0F, NETHERITE_INGOT),
    FROST_PROP(55, new int[]{3, 6, 8, 3}, 16, ARMOR_EQUIP_DIAMOND, 1.0f, "infused_frost_crystal"),
    FROST_LAVA_PROP(70, new int[]{4, 7, 8, 3}, 32, ARMOR_EQUIP_NETHERITE, 2.0f, AIR);
    /**
     * Holds the 'base' maxDamage that each armorType have.
     */
    protected static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durability;
    private final float toughness;
    private final int[] damageReduction;
    private final int enchantability;
    private final Holder<SoundEvent> soundEvent;
    private final APRepair repair;
    private final boolean special;

    APArmorProperties(int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, ItemLike... repair) {
        this(true, durability, damageReduction, enchantability, soundEvent, 0.0f, repair);
    }

    APArmorProperties(int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, float toughness, ItemLike... repair) {
        this(true, durability, damageReduction, enchantability, soundEvent, toughness, repair);
    }

    APArmorProperties(boolean special, int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, float toughness, ItemLike... repair) {
        this(special, durability, damageReduction, enchantability, soundEvent, toughness, new APRepair(repair));
    }

    APArmorProperties(int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, String... repair) {
        this(true, durability, damageReduction, enchantability, soundEvent, 0.0f, repair);
    }

    APArmorProperties(int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, float toughness, String... repair) {
        this(true, durability, damageReduction, enchantability, soundEvent, toughness, repair);
    }

    APArmorProperties(boolean special, int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, float toughness, String... repair) {
        this(special, durability, damageReduction, enchantability, soundEvent, toughness, new APRepair(repair));
    }

    APArmorProperties(boolean special, int durability, int[] damageReduction, int enchantability, Holder<SoundEvent> soundEvent, float toughness, APRepair repair) {
        this.special = special;
        this.name = name().toLowerCase(Locale.ENGLISH).replace("_prop", "").replace("enhanced_", "");
        this.durability = durability;
        this.toughness = toughness;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.repair = repair;
    }

    public ArmorMaterial toMaterial() {
        EnumMap<ArmorType, Integer> defenseMap = new EnumMap<>(ArmorType.class);
        defenseMap.put(ArmorType.HELMET, damageReduction[0]);
        defenseMap.put(ArmorType.CHESTPLATE, damageReduction[1]);
        defenseMap.put(ArmorType.LEGGINGS, damageReduction[2]);
        defenseMap.put(ArmorType.BOOTS, damageReduction[3]);
        defenseMap.put(ArmorType.BODY, damageReduction[1]);

        String namespace = special ? ArmorPlus.MODID : "minecraft";
        TagKey<Item> repairTag = TagKey.create(BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(ArmorPlus.MODID, "repairs_" + name));
        ResourceKey<EquipmentAsset> assetKey = ResourceKey.create(EquipmentAssets.ROOT_ID,
                Identifier.fromNamespaceAndPath(namespace, name));

        return new ArmorMaterial(durability, defenseMap, enchantability, soundEvent, toughness,
                this == ENHANCED_NETHERITE_PROP ? 0.1f : 0.0f, repairTag, assetKey);
    }

    public int getDurability() {
        return durability;
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
                ", repairMaterial=" + repair +
                ", special=" + special +
                '}';
    }
}
