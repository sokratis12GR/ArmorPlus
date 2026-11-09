package com.sofodev.armorplus.registry.item.armor;

import com.sofodev.armorplus.config.ArmorPlusConfig.IMaterialConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.ModArmorMaterials.getHolder;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.*;
import static net.minecraft.ChatFormatting.*;

public enum APArmorMaterial implements IAPArmor {
    /*Tier 1*/
    COAL(COAL_PROP, GRAY, () -> coalMaterial),
    REDSTONE(REDSTONE_PROP, DARK_RED, () -> redstoneMaterial),
    LAPIS(LAPIS_PROP, DARK_BLUE, () -> lapisMaterial),
    CHICKEN(CHICKEN_PROP, WHITE, () -> chickenMaterial),
    SLIME(SLIME_PROP, GREEN, () -> slimeMaterial),

    /*Tier 2*/
    EMERALD(EMERALD_PROP, DARK_GREEN, () -> emeraldMaterial),
    OBSIDIAN(OBSIDIAN_PROP, true, DARK_GRAY, () -> obsidianMaterial),
    INFUSED_LAVA(INFUSED_LAVA_PROP, true, GOLD, () -> infusedLavaMaterial),

    /*Tier 3*/
    GUARDIAN(GUARDIAN_PROP, true, BLUE, () -> guardianMaterial),
    SUPER_STAR(SUPER_STAR_PROP, true, WHITE, () -> superStarMaterial),
    ENDER_DRAGON(ENDER_DRAGON_PROP, true, DARK_PURPLE, () -> enderDragonMaterial),
    WARDEN(WARDEN_PROP, true, DARK_AQUA, () -> wardenMaterial),

    /*Tier TConstruct*/
    ARDITE(ARDITE_PROP, RED, () -> arditeMaterial),
    COBALT(COBALT_PROP, BLUE, () -> cobaltMaterial),
    KNIGHT_SLIME(KNIGHT_SLIME_PROP, LIGHT_PURPLE, () -> knightSlimeMaterial),
    PIG_IRON(PIG_IRON_PROP, LIGHT_PURPLE, () -> pigIronMaterial),
    MANYULLYN(MANYULLYN_PROP, DARK_PURPLE, () -> manyullynMaterial),

    /*Tier Slayer*/
    SLAYER(SLAYER_PROP, true, DARK_PURPLE, () -> slayerMaterial),

    /*Enhanced Vanilla Armor*/
    CHAINMAIL(ENHANCED_CHAINMAIL_PROP, GRAY, () -> enhancedChainmailMaterial),
    GOLDEN(ENHANCED_GOLD_PROP, GRAY, () -> enhancedGoldenMaterial),
    //COPPER (ENHANCED_COPPER_PROP) is not registered on 1.21.1: vanilla copper armor textures only exist from 1.21.9
    IRON(ENHANCED_IRON_PROP, GRAY, () -> enhancedIronMaterial),
    DIAMOND(ENHANCED_DIAMOND_PROP, GRAY, () -> enhancedDiamondMaterial),
    NETHERITE(ENHANCED_NETHERITE_PROP, true, GRAY, () -> enhancedNetheriteMaterial),
    FROST(FROST_PROP, BLUE, () -> frostMaterial),
    FROST_LAVA(FROST_LAVA_PROP, true, YELLOW, () -> frostLavaMaterial),
    AMETHYST(AMETHYST_PROP, LIGHT_PURPLE, () -> amethystMaterial),
    QUARTZ(QUARTZ_PROP, WHITE, () -> quartzMaterial),
    GLOWSTONE(GLOWSTONE_PROP, YELLOW, () -> glowstoneMaterial),
    HONEY(HONEY_PROP, GOLD, () -> honeyMaterial);

    private final Supplier<Holder<ArmorMaterial>> armor;
    private final boolean isImmuneToFire;
    private final Supplier<List<BuffInstance>> buffs;
    private final ChatFormatting formatting;
    private final Supplier<MaterialConfig> materialConfig;
    private final APArmorProperties properties;


    APArmorMaterial(APArmorProperties armor, ChatFormatting formatting, Supplier<MaterialConfig> materialConfig) {
        this(armor, false, formatting, materialConfig);
    }

    APArmorMaterial(APArmorProperties armor, boolean isImmuneToFire, ChatFormatting formatting, Supplier<MaterialConfig> materialConfig) {
        this.properties = armor;
        this.armor = getHolder(armor);
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
    public int getDurability(ArmorItem.Type type) {
        return MAX_DAMAGE_ARRAY[type.getSlot().getIndex()] * properties.getDurability();
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public Supplier<Holder<ArmorMaterial>> get() {
        return this.armor;
    }

    @Override
    public Item.Properties getProperties() {
        return new Item.Properties().rarity(Rarity.EPIC);
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
    public IMaterialConfig config() {
        MaterialConfig cfg = materialConfig.get();
        return cfg != null ? cfg : IAPArmor.super.config();
    }
}
