package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
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
 *     <li>{@code max=level} - highest level the rune can be upgraded to (instead of "maxRuneLevel", 1 means no upgrading)</li>
 *     <li>{@code upgrade_catalyst=item} or {@code upgrade_catalyst=item*count} - item the ritual consumes from the pedestals to upgrade it, instead of
 *     the "ritualCatalysts" config ({@code none} for no item, at most {@value #MAX_PEDESTAL_CATALYSTS})</li>
 *     <li>{@code upgrade_catalyst@level=item} or {@code upgrade_catalyst@level=item*count} - the same for upgrades that reach the level, which also
 *     counts for all higher levels until the next such entry; wins over {@code upgrade_catalyst=}, e.g. {@code "armorplus:flight|upgrade_catalyst@3=armorplus:astral_sigil"}</li>
 *     <li>{@code socket_catalyst=item} or {@code socket_catalyst=item*count} - item the ritual consumes from the pedestals to socket it, instead of
 *     the "ritualCatalysts" config ({@code none} for no item, at most {@value #MAX_PEDESTAL_CATALYSTS})</li>
 * </ul>
 */
public final class RuneOverrides {

    /** Every pedestal holds one item, so the ritual can't use more of a catalyst than there are pedestals. */
    public static final int MAX_PEDESTAL_CATALYSTS = RuneRitual.PEDESTAL_COUNT;
    /** Ends with the level the entry starts at. */
    public static final String LEVEL_CATALYST_KEY = "upgrade_catalyst@";

    /**
     * @param catalyst      the catalyst item id, or null for none
     * @param catalystCount 1 - 64
     * @param upgradeCatalyst      the upgrade catalyst item id, or null for none
     * @param upgradeCatalystCount 1 - {@value #MAX_PEDESTAL_CATALYSTS}
     * @param socketCatalyst       the socket catalyst item id, or null for none
     * @param socketCatalystCount  1 - {@value #MAX_PEDESTAL_CATALYSTS}
     * @param levelCatalysts       the upgrade catalysts from a certain level on
     */
    public record Rules(@Nullable Integer socketCost, @Nullable Integer extractionCost, @Nullable Identifier catalyst,
                           int catalystCount, boolean extractable, boolean disabled, @Nullable Integer maxLevel,
                           @Nullable Identifier upgradeCatalyst, int upgradeCatalystCount,
                           @Nullable Identifier socketCatalyst, int socketCatalystCount, List<RitualCatalysts.Entry> levelCatalysts) {

        public static final Rules NONE = new Rules(null, null, null, 1, true, false, null, null, 1, null, 1, List.of());

        /** @return the catalyst stack, or EMPTY if there is none or the item doesn't exist */
        public ItemStack catalystStack() {
            return stack(catalyst, catalystCount);
        }

        /** @return the upgrade catalyst stack, or EMPTY if there is none or the item doesn't exist */
        public ItemStack upgradeCatalystStack() {
            return stack(upgradeCatalyst, upgradeCatalystCount);
        }

        /** @return the socket catalyst stack, or EMPTY if there is none or the item doesn't exist */
        public ItemStack socketCatalystStack() {
            return stack(socketCatalyst, socketCatalystCount);
        }

        private static ItemStack stack(@Nullable Identifier id, int count) {
            if (id == null) return ItemStack.EMPTY;
            Item item = BuiltInRegistries.ITEM.getOptional(id).orElse(null);
            return item == null ? ItemStack.EMPTY : new ItemStack(item, count);
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

    /** Syntax check of level catalysts as the config editor lists them, e.g. {@code "3=armorplus:rime_sigil,5=armorplus:astral_sigil*2"}. */
    public static boolean isValidLevelCatalysts(String list) {
        for (String part : list.split(",", -1)) {
            String[] pair = part.split("=", -1);
            if (pair.length != 2 || levelCatalyst(pair[0], pair[1]) == null) return false;
        }
        return true;
    }

    /** @return the catalyst of {@code upgrade_catalyst@level=value}, or null if it's invalid */
    private static RitualCatalysts.@Nullable Entry levelCatalyst(String level, String value) {
        RitualCatalysts.Entry entry = RitualCatalysts.parse(level + "|" + value.replace('*', '|'));
        return entry == null || entry.level() == null ? null : entry;
    }

    static @Nullable Rules parse(String entry) {
        String[] parts = entry.split("\\|", -1);
        if (parts.length < 2 || Identifier.tryParse(parts[0].trim()) == null) return null;
        Integer socket = null, extract = null, max = null;
        Identifier catalyst = null, upgradeCatalyst = null, socketCatalyst = null;
        int count = 1, upgradeCount = 1, socketCount = 1;
        boolean extractable = true, disabled = false;
        List<RitualCatalysts.Entry> levelCatalysts = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            String[] pair = parts[i].split("=", -1);
            if (pair.length != 2) return null;
            String key = pair[0].trim();
            String value = pair[1].trim();
            if (key.startsWith(LEVEL_CATALYST_KEY)) {
                RitualCatalysts.Entry levelCatalyst = levelCatalyst(key.substring(LEVEL_CATALYST_KEY.length()), value);
                if (levelCatalyst == null) return null;
                levelCatalysts.add(levelCatalyst);
                continue;
            }
            switch (key) {
                case "socket" -> {
                    socket = parseInt(value);
                    if (socket == null || socket < 1 || socket > 39) return null;
                }
                case "extract" -> {
                    extract = parseInt(value);
                    if (extract == null || extract < 0) return null;
                }
                case "max" -> {
                    max = parseInt(value);
                    if (max == null || max < 1 || max > 256) return null;
                }
                case "catalyst" -> {
                    String[] item = value.split("\\*", -1);
                    catalyst = Identifier.tryParse(item[0].trim());
                    Integer parsedCount = item.length == 2 ? parseInt(item[1]) : item.length == 1 ? Integer.valueOf(1) : null;
                    if (catalyst == null || parsedCount == null || parsedCount < 1 || parsedCount > 64) return null;
                    count = parsedCount;
                }
                case "upgrade_catalyst" -> {
                    String[] item = value.split("\\*", -1);
                    upgradeCatalyst = Identifier.tryParse(item[0].trim());
                    Integer parsedCount = item.length == 2 ? parseInt(item[1]) : item.length == 1 ? Integer.valueOf(1) : null;
                    if (upgradeCatalyst == null || parsedCount == null || parsedCount < 1 || parsedCount > MAX_PEDESTAL_CATALYSTS) return null;
                    upgradeCount = parsedCount;
                }
                case "socket_catalyst" -> {
                    String[] item = value.split("\\*", -1);
                    socketCatalyst = Identifier.tryParse(item[0].trim());
                    Integer parsedCount = item.length == 2 ? parseInt(item[1]) : item.length == 1 ? Integer.valueOf(1) : null;
                    if (socketCatalyst == null || parsedCount == null || parsedCount < 1 || parsedCount > MAX_PEDESTAL_CATALYSTS) return null;
                    socketCount = parsedCount;
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
        return new Rules(socket, extract, catalyst, count, extractable, disabled, max, upgradeCatalyst, upgradeCount, socketCatalyst, socketCount, List.copyOf(levelCatalysts));
    }

    private static @Nullable Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
