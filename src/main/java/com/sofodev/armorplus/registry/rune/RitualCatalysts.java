package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * What the ritual uses up from the pedestals by default, the "ritualCatalysts" config: <b>{@code "step|item"}</b> or
 * {@code "step|item|count"}, e.g. {@code "3|armorplus:rime_sigil"}. The step is {@value #SOCKET} for socketing or the level an
 * upgrade reaches, which also counts for all higher levels until the next entry. "runeOverrides" can replace it per buff, for
 * socketing and for every or certain levels of upgrading, so each recipe can have its own.
 */
public final class RitualCatalysts {

    public static final String SOCKET = "socket";

    /**
     * @param level the level this entry starts at, or null for socketing
     * @param item  the catalyst item id
     * @param count 1 - {@value RuneOverrides#MAX_PEDESTAL_CATALYSTS}
     */
    public record Entry(@Nullable Integer level, Identifier item, int count) {

        /** @return the stack, or EMPTY if the item doesn't exist */
        ItemStack stack() {
            return BuiltInRegistries.ITEM.getOptional(item).map(found -> new ItemStack(found, count)).orElse(ItemStack.EMPTY);
        }
    }

    /** Parsed entries of the current config list; the config hands out the same list until it reloads. */
    private static volatile List<?> parsedSource;
    private static volatile List<Entry> parsed = List.of();

    private RitualCatalysts() {
    }

    /** @return the catalyst for socketing, or EMPTY */
    public static ItemStack forSocketing() {
        Entry entry = socketEntry(entries());
        return entry == null ? ItemStack.EMPTY : entry.stack();
    }

    /** @return the catalyst of an upgrade that reaches the level, or EMPTY */
    public static ItemStack forLevel(int level) {
        Entry entry = levelEntry(entries(), level);
        return entry == null ? ItemStack.EMPTY : entry.stack();
    }

    /** Syntax check for config validation. */
    public static boolean isValid(Object value) {
        return value instanceof String s && parse(s) != null;
    }

    private static List<Entry> entries() {
        List<? extends String> source = ArmorPlusConfig.ritualCatalysts.get();
        if (source != parsedSource) {
            List<Entry> result = new ArrayList<>();
            for (String line : source) {
                Entry entry = parse(line);
                if (entry != null) result.add(entry);
            }
            parsed = List.copyOf(result);
            parsedSource = source;
        }
        return parsed;
    }

    static @Nullable Entry socketEntry(List<Entry> entries) {
        for (Entry entry : entries) {
            if (entry.level() == null) return entry;
        }
        return null;
    }

    /** @return the entry with the highest level that the given level reaches */
    static @Nullable Entry levelEntry(List<Entry> entries, int level) {
        Entry best = null;
        for (Entry entry : entries) {
            if (entry.level() != null && entry.level() <= level && (best == null || entry.level() > best.level())) best = entry;
        }
        return best;
    }

    static @Nullable Entry parse(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 2 || parts.length > 3) return null;
        String step = parts[0].trim();
        Integer level = null;
        if (!step.equals(SOCKET)) {
            level = parseInt(step);
            if (level == null || level < 2 || level > 256) return null;
        }
        Identifier item = Identifier.tryParse(parts[1].trim());
        Integer count = parts.length == 3 ? parseInt(parts[2]) : Integer.valueOf(1);
        if (item == null || count == null || count < 1 || count > RuneOverrides.MAX_PEDESTAL_CATALYSTS) return null;
        return new Entry(level, item, count);
    }

    private static @Nullable Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
