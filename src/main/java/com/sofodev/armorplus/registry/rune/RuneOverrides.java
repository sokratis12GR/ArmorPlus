package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The per-buff rune rules of the "runeOverrides" config, one entry per buff:
 * <b>{@code "id|key=value|key=value..."}</b>, e.g. {@code "armorplus:flight|socket=25|extract=30|catalyst=minecraft:nether_star*1"}.
 * <ul>
 *     <li>{@code socket=levels} - experience levels to socket the rune (instead of "socketXpCost", at most 39)</li>
 *     <li>{@code extract=levels} - fixed experience levels to extract it (instead of the base and per level cost)</li>
 *     <li>{@code catalyst=item} or {@code catalyst=item*count} - item the Rune Altar consumes in addition to extract it</li>
 *     <li>{@code extractable=false} - the buff can't be extracted</li>
 *     <li>{@code disabled=true} - runes of the buff are inactive (they are kept, but do nothing)</li>
 * </ul>
 */
public final class RuneOverrides {

    /**
     * @param catalyst      the catalyst item id, or null for none
     * @param catalystCount 1 - 64
     */
    public record Rules(@Nullable Integer socketCost, @Nullable Integer extractionCost, @Nullable Identifier catalyst,
                           int catalystCount, boolean extractable, boolean disabled) {

        public static final Rules NONE = new Rules(null, null, null, 1, true, false);

        /** @return the catalyst stack, or EMPTY if there is none or the item doesn't exist */
        public ItemStack catalystStack() {
            if (catalyst == null) return ItemStack.EMPTY;
            Item item = BuiltInRegistries.ITEM.getOptional(catalyst).orElse(null);
            return item == null ? ItemStack.EMPTY : new ItemStack(item, catalystCount);
        }
    }

    /** Parsed overrides of the current config list; the config hands out the same list until it reloads. */
    private static volatile List<?> parsedSource;
    private static volatile Map<String, Rules> parsed = Map.of();

    private RuneOverrides() {
    }

    /** @return the overrides of the buff, {@link Rules#NONE} if it has none */
    public static Rules of(String buffId) {
        List<? extends String> source = ArmorPlusConfig.runeOverrides.get();
        if (source != parsedSource) {
            Map<String, Rules> result = new HashMap<>();
            for (String entry : source) {
                Rules rules = parse(entry);
                if (rules != null) result.putIfAbsent(BuffParser.idOf(entry), rules);
            }
            parsed = Map.copyOf(result);
            parsedSource = source;
        }
        return parsed.getOrDefault(buffId, Rules.NONE);
    }

    /** Syntax check for config validation. */
    public static boolean isValid(Object value) {
        return value instanceof String s && parse(s) != null;
    }

    private static @Nullable Rules parse(String entry) {
        String[] parts = entry.split("\\|", -1);
        if (parts.length < 2 || Identifier.tryParse(parts[0].trim()) == null) return null;
        Integer socket = null, extract = null;
        Identifier catalyst = null;
        int count = 1;
        boolean extractable = true, disabled = false;
        for (int i = 1; i < parts.length; i++) {
            String[] pair = parts[i].split("=", -1);
            if (pair.length != 2) return null;
            String value = pair[1].trim();
            switch (pair[0].trim()) {
                case "socket" -> {
                    socket = parseInt(value);
                    if (socket == null || socket < 1 || socket > 39) return null;
                }
                case "extract" -> {
                    extract = parseInt(value);
                    if (extract == null || extract < 0) return null;
                }
                case "catalyst" -> {
                    String[] item = value.split("\\*", -1);
                    catalyst = Identifier.tryParse(item[0].trim());
                    Integer parsedCount = item.length == 2 ? parseInt(item[1]) : item.length == 1 ? Integer.valueOf(1) : null;
                    if (catalyst == null || parsedCount == null || parsedCount < 1 || parsedCount > 64) return null;
                    count = parsedCount;
                }
                case "extractable" -> {
                    if (!value.equals("true") && !value.equals("false")) return null;
                    extractable = Boolean.parseBoolean(value);
                }
                case "disabled" -> {
                    if (!value.equals("true") && !value.equals("false")) return null;
                    disabled = Boolean.parseBoolean(value);
                }
                default -> {
                    return null;
                }
            }
        }
        return new Rules(socket, extract, catalyst, count, extractable, disabled);
    }

    private static @Nullable Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
