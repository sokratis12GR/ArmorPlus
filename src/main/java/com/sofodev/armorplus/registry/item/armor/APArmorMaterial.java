package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.ChatFormatting;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.RegistryContext;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.config;
import static com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.AMETHYST_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ARDITE_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.CHICKEN_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.COAL_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.COBALT_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.EMERALD_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENDER_DRAGON_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENHANCED_CHAINMAIL_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENHANCED_COPPER_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENHANCED_DIAMOND_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENHANCED_GOLD_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENHANCED_IRON_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.ENHANCED_NETHERITE_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.FROST_LAVA_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.FROST_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.GLOWSTONE_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.GUARDIAN_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.HONEY_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.INFUSED_LAVA_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.KNIGHT_SLIME_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.LAPIS_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.MANYULLYN_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.MAX_DAMAGE_ARRAY;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.OBSIDIAN_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.PIG_IRON_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.QUARTZ_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.REDSTONE_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.SLAYER_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.SLIME_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.SUPER_STAR_PROP;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.WARDEN_PROP;
import static net.minecraft.ChatFormatting.BLUE;
import static net.minecraft.ChatFormatting.DARK_AQUA;
import static net.minecraft.ChatFormatting.DARK_BLUE;
import static net.minecraft.ChatFormatting.DARK_GRAY;
import static net.minecraft.ChatFormatting.DARK_GREEN;
import static net.minecraft.ChatFormatting.DARK_PURPLE;
import static net.minecraft.ChatFormatting.DARK_RED;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;
import static net.minecraft.ChatFormatting.GREEN;
import static net.minecraft.ChatFormatting.LIGHT_PURPLE;
import static net.minecraft.ChatFormatting.RED;
import static net.minecraft.ChatFormatting.WHITE;
import static net.minecraft.ChatFormatting.YELLOW;

/**
 * Armor buffs are read from each material's config ("armorBuffs", formatted as "id|level", see {@link BuffParser}).
 * The defaults live in {@link ArmorPlusConfig}.
 */
public enum APArmorMaterial implements IAPArmor {
    /*ToolMaterial 1*/
    COAL(COAL_PROP, GRAY, () -> config.coalMaterial),
    REDSTONE(REDSTONE_PROP, DARK_RED, () -> config.redstoneMaterial),
    LAPIS(LAPIS_PROP, DARK_BLUE, () -> config.lapisMaterial),
    CHICKEN(CHICKEN_PROP, WHITE, () -> config.chickenMaterial),
    SLIME(SLIME_PROP, GREEN, () -> config.slimeMaterial),

    /*ToolMaterial 2*/
    EMERALD(EMERALD_PROP, DARK_GREEN, () -> config.emeraldMaterial),
    OBSIDIAN(OBSIDIAN_PROP, true, DARK_GRAY, () -> config.obsidianMaterial),
    INFUSED_LAVA(INFUSED_LAVA_PROP, true, GOLD, () -> config.infusedLavaMaterial),

    /*ToolMaterial 3*/
    GUARDIAN(GUARDIAN_PROP, true, BLUE, () -> config.guardianMaterial),
    SUPER_STAR(SUPER_STAR_PROP, true, WHITE, () -> config.superStarMaterial),
    ENDER_DRAGON(ENDER_DRAGON_PROP, true, DARK_PURPLE, () -> config.enderDragonMaterial),
    WARDEN(WARDEN_PROP, true, DARK_AQUA, () -> config.wardenMaterial),

    /*ToolMaterial TConstruct*/
    ARDITE(ARDITE_PROP, RED, () -> config.arditeMaterial),
    COBALT(COBALT_PROP, BLUE, () -> config.cobaltMaterial),
    KNIGHT_SLIME(KNIGHT_SLIME_PROP, LIGHT_PURPLE, () -> config.knightSlimeMaterial),
    PIG_IRON(PIG_IRON_PROP, LIGHT_PURPLE, () -> config.pigIronMaterial),
    MANYULLYN(MANYULLYN_PROP, DARK_PURPLE, () -> config.manyullynMaterial),

    /*ToolMaterial Slayer*/
    SLAYER(SLAYER_PROP, true, DARK_PURPLE, () -> config.slayerMaterial),

    /*Enhanced Vanilla Armor*/
    CHAINMAIL(ENHANCED_CHAINMAIL_PROP, GRAY, () -> config.enhancedChainmailMaterial),
    GOLDEN(ENHANCED_GOLD_PROP, GRAY, () -> config.enhancedGoldenMaterial),
    COPPER(ENHANCED_COPPER_PROP, GRAY, () -> config.enhancedCopperMaterial),
    IRON(ENHANCED_IRON_PROP, GRAY, () -> config.enhancedIronMaterial),
    DIAMOND(ENHANCED_DIAMOND_PROP, GRAY, () -> config.enhancedDiamondMaterial),
    NETHERITE(ENHANCED_NETHERITE_PROP, true, GRAY, () -> config.enhancedNetheriteMaterial),
    FROST(FROST_PROP, false, BLUE, () -> config.frostMaterial),
    FROST_LAVA(FROST_LAVA_PROP, true, YELLOW, () -> config.frostLavaMaterial),

    /*Amethyst/Quartz/Glowstone/Honey*/
    AMETHYST(AMETHYST_PROP, LIGHT_PURPLE, () -> config.amethystMaterial),
    QUARTZ(QUARTZ_PROP, WHITE, () -> config.quartzMaterial),
    GLOWSTONE(GLOWSTONE_PROP, YELLOW, () -> config.glowstoneMaterial),
    HONEY(HONEY_PROP, GOLD, () -> config.honeyMaterial);

    private final APArmorProperties properties;
    private final boolean isImmuneToFire;
    private final Supplier<MaterialConfig> materialConfig;
    private final Supplier<List<BuffInstance>> buffs;
    private final ChatFormatting formatting;
    /** Built on first use (item registration), once the stats config has loaded. */
    private ArmorMaterial armor;


    APArmorMaterial(APArmorProperties armor, ChatFormatting formatting, Supplier<MaterialConfig> materialConfig) {
        this(armor, false, formatting, materialConfig);
    }

    APArmorMaterial(APArmorProperties armor, boolean isImmuneToFire, ChatFormatting formatting, Supplier<MaterialConfig> materialConfig) {
        this.properties = armor;
        this.isImmuneToFire = isImmuneToFire;
        this.materialConfig = materialConfig;
        this.buffs = () -> {
            MaterialConfig cfg = materialConfig.get();
            return cfg == null ? List.of() : cfg.armorBuffInstances();
        };
        this.formatting = formatting;
    }

    /**
     * durability for each piece
     */
    public int getDurability(ArmorType type) {
        return MAX_DAMAGE_ARRAY[type.getSlot().getIndex()] * properties.getDurability();
    }

    /**
     * Builds buffs in code using the same format as the config files, e.g. {@code withBuffs("minecraft:haste|1", "armorplus:flight")}.
     */
    public static List<BuffInstance> withBuffs(String... entries) {
        return Arrays.stream(entries).map(BuffParser::armor).filter(Objects::nonNull).toList();
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public ArmorMaterial get() {
        if (this.armor == null) this.armor = this.properties.toMaterial();
        return this.armor;
    }

    @Override
    public Item.Properties getProperties() {
        return RegistryContext.itemProperties().rarity(Rarity.EPIC);
    }

    @Override
    public boolean isImmuneToFire() {
        return this.isImmuneToFire;
    }

    @Override
    public ChatFormatting getFormatting() {
        return this.formatting;
    }

    @Override
    public Supplier<List<BuffInstance>> getBuffInstances() {
        return this.buffs;
    }

    @Override
    public MaterialConfig config() {
        return this.materialConfig.get();
    }
}
