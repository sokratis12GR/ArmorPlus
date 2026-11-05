package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorType;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusStatsConfig;
import com.sofodev.armorplus.config.ArmorPlusStatsConfig.ArmorStats;
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

import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_CHAIN;
import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_COPPER;
import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_DIAMOND;
import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GOLD;
import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_IRON;
import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_LEATHER;
import static net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_NETHERITE;
import static net.minecraft.world.item.Items.AIR;
import static net.minecraft.world.item.Items.AMETHYST_BLOCK;
import static net.minecraft.world.item.Items.AMETHYST_SHARD;
import static net.minecraft.world.item.Items.COAL;
import static net.minecraft.world.item.Items.COAL_BLOCK;
import static net.minecraft.world.item.Items.COPPER_INGOT;
import static net.minecraft.world.item.Items.DIAMOND;
import static net.minecraft.world.item.Items.EMERALD;
import static net.minecraft.world.item.Items.EMERALD_BLOCK;
import static net.minecraft.world.item.Items.FEATHER;
import static net.minecraft.world.item.Items.GLOWSTONE;
import static net.minecraft.world.item.Items.GLOWSTONE_DUST;
import static net.minecraft.world.item.Items.GOLD_INGOT;
import static net.minecraft.world.item.Items.HONEYCOMB;
import static net.minecraft.world.item.Items.HONEYCOMB_BLOCK;
import static net.minecraft.world.item.Items.IRON_INGOT;
import static net.minecraft.world.item.Items.LAPIS_BLOCK;
import static net.minecraft.world.item.Items.LAPIS_LAZULI;
import static net.minecraft.world.item.Items.NETHERITE_INGOT;
import static net.minecraft.world.item.Items.OBSIDIAN;
import static net.minecraft.world.item.Items.QUARTZ;
import static net.minecraft.world.item.Items.QUARTZ_BLOCK;
import static net.minecraft.world.item.Items.REDSTONE;
import static net.minecraft.world.item.Items.REDSTONE_BLOCK;
import static net.minecraft.world.item.Items.SLIME_BALL;
import static net.minecraft.world.item.Items.SLIME_BLOCK;

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
    WARDEN_PROP(80, new int[]{4, 7, 8, 3}, 30, ARMOR_EQUIP_NETHERITE, 2.0f, "warden_heart"),
    ARDITE_PROP(55, new int[]{2, 3, 4, 2}, 16, ARMOR_EQUIP_IRON, 1.0f, "ardite_ingot"),
    COBALT_PROP(44, new int[]{2, 3, 4, 2}, 16, ARMOR_EQUIP_IRON, 1.0f, "cobalt_ingot"),
    KNIGHT_SLIME_PROP(33, new int[]{2, 3, 4, 3}, 16, ARMOR_EQUIP_IRON, 1.0f, "knight_slime_crystal"),
    PIG_IRON_PROP(33, new int[]{2, 3, 4, 3}, 16, ARMOR_EQUIP_IRON, 1.0f, "pig_iron_ingot"),
    MANYULLYN_PROP(66, new int[]{3, 5, 5, 3}, 30, ARMOR_EQUIP_NETHERITE, 2.0f, "manyullyn_ingot"),
    SLAYER_PROP(169, new int[]{4, 8, 9, 4}, 60, ARMOR_EQUIP_NETHERITE, 5.0f, "the_ultimate_material"),
    /*Enhanced Vanilla Armor*/
    ENHANCED_CHAINMAIL_PROP(false, 20, new int[]{2, 5, 6, 3}, 15, ARMOR_EQUIP_CHAIN, 1.0f, "chainmail"),
    ENHANCED_GOLD_PROP(false, 10, new int[]{2, 4, 5, 2}, 30, ARMOR_EQUIP_GOLD, 1.0f, GOLD_INGOT),
    ENHANCED_COPPER_PROP(false, 20, new int[]{1, 3, 4, 2}, 15, ARMOR_EQUIP_COPPER, 2.0f, COPPER_INGOT),
    ENHANCED_IRON_PROP(false, 20, new int[]{2, 5, 6, 2}, 15, ARMOR_EQUIP_IRON, 2.0f, IRON_INGOT),
    ENHANCED_DIAMOND_PROP(false, 35, new int[]{3, 6, 7, 3}, 20, ARMOR_EQUIP_DIAMOND, 3.0f, DIAMOND),
    ENHANCED_NETHERITE_PROP(false, 47, new int[]{3, 6, 8, 3}, 25, ARMOR_EQUIP_NETHERITE, 4.0F, NETHERITE_INGOT),
    FROST_PROP(55, new int[]{3, 6, 8, 3}, 16, ARMOR_EQUIP_DIAMOND, 1.0f, "infused_frost_crystal"),
    FROST_LAVA_PROP(70, new int[]{4, 7, 8, 3}, 32, ARMOR_EQUIP_NETHERITE, 2.0f, AIR),
    AMETHYST_PROP(11, new int[]{1, 2, 3, 2}, 8, ARMOR_EQUIP_IRON, AMETHYST_SHARD, AMETHYST_BLOCK),
    QUARTZ_PROP(11, new int[]{1, 2, 3, 2}, 8, ARMOR_EQUIP_IRON, QUARTZ, QUARTZ_BLOCK),
    GLOWSTONE_PROP(1, new int[]{1, 1, 2, 1}, 8, ARMOR_EQUIP_LEATHER, GLOWSTONE_DUST, GLOWSTONE),
    HONEY_PROP(1, new int[]{1, 1, 2, 1}, 8, ARMOR_EQUIP_LEATHER, HONEYCOMB, HONEYCOMB_BLOCK);
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

    /**
     * Builds the material from the configured stats (see {@link ArmorPlusStatsConfig}), so it must not be called before
     * the STARTUP config is registered.
     */
    public ArmorMaterial toMaterial() {
        ArmorStats stats = ArmorPlusStatsConfig.armor(this);
        EnumMap<ArmorType, Integer> defenseMap = new EnumMap<>(ArmorType.class);
        for (ArmorType type : ArmorType.values()) {
            defenseMap.put(type, stats.defense(type));
        }

        String namespace = special ? ArmorPlus.MODID : "minecraft";
        TagKey<Item> repairTag = TagKey.create(BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(ArmorPlus.MODID, "repairs_" + name));
        ResourceKey<EquipmentAsset> assetKey = ResourceKey.create(EquipmentAssets.ROOT_ID,
                Identifier.fromNamespaceAndPath(namespace, name));

        return new ArmorMaterial(stats.durabilityMultiplier(), defenseMap, stats.enchantability(), soundEvent,
                stats.toughness(), stats.knockbackResistance(), repairTag, assetKey);
    }

    /** The configured durability multiplier. */
    public int getDurability() {
        return ArmorPlusStatsConfig.armor(this).durabilityMultiplier();
    }

    /** The hardcoded stats, used as the config defaults. */
    public ArmorStats defaultStats() {
        return new ArmorStats(durability, damageReduction.clone(), toughness,
                this == ENHANCED_NETHERITE_PROP ? 0.1f : 0.0f, enchantability);
    }

    /** Section name in the stats config, matching the names of the main config (e.g. "enhanced_golden"). */
    public String configName() {
        return name().toLowerCase(Locale.ENGLISH).replace("_prop", "").replace("enhanced_gold", "enhanced_golden");
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
