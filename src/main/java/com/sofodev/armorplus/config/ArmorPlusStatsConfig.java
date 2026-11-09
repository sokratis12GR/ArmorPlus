package com.sofodev.armorplus.config;

import com.sofodev.armorplus.registry.item.armor.APArmorProperties;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties;
import com.sofodev.thedragonlib.config.ConfigHelper;
import com.sofodev.thedragonlib.config.ConfigHolder;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

import java.util.EnumMap;
import java.util.Map;

/**
 * Equipment stats (durability, defense, enchantability, ...).
 * <p>
 * These are baked into the items while they register, which happens before the COMMON config is loaded, so they live in
 * their own STARTUP config ("armorplus/stats.toml"). Changes require a restart and the client and server should use the
 * same file, otherwise tooltips and durability bars will show the client's values.
 * The defaults are the values hardcoded in {@link APArmorProperties} and {@link APToolProperties}.
 */
public final class ArmorPlusStatsConfig {

    public static final String FILE_NAME = "armorplus/stats.toml";

    private static final Map<APArmorProperties, ArmorStatsConfig> ARMOR = new EnumMap<>(APArmorProperties.class);
    private static final Map<APToolProperties, ToolStatsConfig> TOOLS = new EnumMap<>(APToolProperties.class);
    private static ConfigHolder<ArmorPlusStatsConfig> holder;

    private ArmorPlusStatsConfig() {
    }

    /** Must be called before the items register (STARTUP configs load as soon as they are registered). */
    public static void register(ModContainer container) {
        holder = ConfigHelper.buildAndRegister(container, ModConfig.Type.STARTUP, FILE_NAME, ArmorPlusStatsConfig::create);
    }

    private static ArmorPlusStatsConfig create(ModConfigSpec.Builder builder) {
        builder.comment("Armor stats per material (requires restart). Defense is the armor points of each piece.")
                .push("armor");
        for (APArmorProperties props : APArmorProperties.values()) {
            ARMOR.put(props, ArmorStatsConfig.create(props, builder));
        }
        builder.pop();

        builder.comment("Tool and weapon stats per material (requires restart). Bows use half of the durability.")
                .push("tools");
        for (APToolProperties props : APToolProperties.values()) {
            TOOLS.put(props, ToolStatsConfig.create(props, builder));
        }
        builder.pop();
        return new ArmorPlusStatsConfig();
    }

    private static <V> V get(ConfigValue<V> value, V fallback) {
        return holder == null || value == null ? fallback : holder.getOrDefault(value, fallback);
    }

    /** @return the configured armor stats, or the hardcoded defaults if the config is not available (e.g. datagen) */
    public static ArmorStats armor(APArmorProperties props) {
        ArmorStats defaults = props.defaultStats();
        ArmorStatsConfig cfg = ARMOR.get(props);
        if (cfg == null) return defaults;
        return new ArmorStats(
                get(cfg.durabilityMultiplier(), defaults.durabilityMultiplier()),
                new int[]{
                        get(cfg.helmetDefense(), defaults.defense(ArmorItem.Type.HELMET)),
                        get(cfg.chestplateDefense(), defaults.defense(ArmorItem.Type.CHESTPLATE)),
                        get(cfg.leggingsDefense(), defaults.defense(ArmorItem.Type.LEGGINGS)),
                        get(cfg.bootsDefense(), defaults.defense(ArmorItem.Type.BOOTS))},
                get(cfg.toughness(), (double) defaults.toughness()).floatValue(),
                get(cfg.knockbackResistance(), (double) defaults.knockbackResistance()).floatValue(),
                get(cfg.enchantability(), defaults.enchantability()));
    }

    /** @return the configured tool stats, or the hardcoded defaults if the config is not available (e.g. datagen) */
    public static ToolStats tool(APToolProperties props) {
        ToolStats defaults = props.defaultStats();
        ToolStatsConfig cfg = TOOLS.get(props);
        if (cfg == null) return defaults;
        return new ToolStats(
                get(cfg.durability(), defaults.durability()),
                get(cfg.miningSpeed(), (double) defaults.miningSpeed()).floatValue(),
                get(cfg.attackDamage(), (double) defaults.attackDamage()).floatValue(),
                get(cfg.enchantability(), defaults.enchantability()));
    }

    /**
     * @param durabilityMultiplier multiplied with the vanilla base durability of each slot
     * @param defense              armor points as {helmet, chestplate, leggings, boots}
     */
    public record ArmorStats(int durabilityMultiplier, int[] defense, float toughness, float knockbackResistance,
                             int enchantability) {

        public int defense(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> defense[0];
                case CHESTPLATE, BODY -> defense[1];
                case LEGGINGS -> defense[2];
                case BOOTS -> defense[3];
            };
        }
    }

    public record ToolStats(int durability, float miningSpeed, float attackDamage, int enchantability) {
    }

    private record ArmorStatsConfig(ConfigValue<Integer> durabilityMultiplier, ConfigValue<Integer> helmetDefense,
                                    ConfigValue<Integer> chestplateDefense, ConfigValue<Integer> leggingsDefense,
                                    ConfigValue<Integer> bootsDefense, ConfigValue<Double> toughness,
                                    ConfigValue<Double> knockbackResistance, ConfigValue<Integer> enchantability) {

        static ArmorStatsConfig create(APArmorProperties props, ModConfigSpec.Builder builder) {
            ArmorStats d = props.defaultStats();
            String name = props.configName();
            builder.comment(name + " armor stats").push(name);
            ArmorStatsConfig cfg = new ArmorStatsConfig(
                    builder.comment("Durability multiplier, multiplied with the base durability of each slot (helmet 11, chestplate 16, leggings 15, boots 13)")
                            .defineInRange("durabilityMultiplier", d.durabilityMultiplier(), 1, 10000),
                    builder.comment("Helmet armor points").defineInRange("helmetDefense", d.defense(ArmorItem.Type.HELMET), 0, 100),
                    builder.comment("Chestplate armor points").defineInRange("chestplateDefense", d.defense(ArmorItem.Type.CHESTPLATE), 0, 100),
                    builder.comment("Leggings armor points").defineInRange("leggingsDefense", d.defense(ArmorItem.Type.LEGGINGS), 0, 100),
                    builder.comment("Boots armor points").defineInRange("bootsDefense", d.defense(ArmorItem.Type.BOOTS), 0, 100),
                    builder.comment("Armor toughness of each piece").defineInRange("toughness", (double) d.toughness(), 0.0, 100.0),
                    builder.comment("Knockback resistance of each piece (0.1 = 10%)").defineInRange("knockbackResistance", (double) d.knockbackResistance(), 0.0, 1.0),
                    builder.comment("Enchantability, higher values give better enchantments").defineInRange("enchantability", d.enchantability(), 0, 1000));
            builder.pop();
            return cfg;
        }
    }

    private record ToolStatsConfig(ConfigValue<Integer> durability, ConfigValue<Double> miningSpeed,
                                   ConfigValue<Double> attackDamage, ConfigValue<Integer> enchantability) {

        static ToolStatsConfig create(APToolProperties props, ModConfigSpec.Builder builder) {
            ToolStats d = props.defaultStats();
            String name = props.configName();
            builder.comment(name + " tool and weapon stats").push(name);
            ToolStatsConfig cfg = new ToolStatsConfig(
                    builder.comment("Durability (max uses)").defineInRange("durability", d.durability(), 1, 1000000),
                    builder.comment("Mining speed").defineInRange("miningSpeed", (double) d.miningSpeed(), 0.0, 1000.0),
                    builder.comment("Attack damage bonus, added to each tool type's own base damage").defineInRange("attackDamage", (double) d.attackDamage(), 0.0, 1000.0),
                    builder.comment("Enchantability, higher values give better enchantments").defineInRange("enchantability", d.enchantability(), 0, 1000));
            builder.pop();
            return cfg;
        }
    }
}
