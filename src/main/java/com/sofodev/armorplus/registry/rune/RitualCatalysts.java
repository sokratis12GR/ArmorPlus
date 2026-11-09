package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * What the ritual uses up from the pedestals by default, the "ritualCatalysts" config: <b>{@code "step|item"}</b> or
 * {@code "step|item|count"}, e.g. {@code "3|armorplus:rime_sigil"}. The step is {@value #SOCKET} for socketing, the level an
 * upgrade reaches, which also counts for all higher levels until the next entry, or a rune type, e.g.
 * {@code "bow|armorplus:bow_sigil"}, for what every upgrade of that type uses on top. "runeOverrides" can replace the first two
 * per buff, for socketing and for every or certain levels of upgrading, so each recipe can have its own.
 */
public final class RitualCatalysts {

    public static final String SOCKET = "socket";

    /** The type of a rune: modded buffs, else instant effects, else other effects, else the abilities of each kind of gear. */
    public enum Type {
        ARMOR, WEAPON, TOOL, BOW, EFFECT, INSTANT, MODDED;

        private static @Nullable Type byName(String name) {
            for (Type type : values()) {
                if (type.name().toLowerCase(Locale.ROOT).equals(name)) return type;
            }
            return null;
        }
    }

    /**
     * @param type  the rune type whose upgrades use this on top, or null for the other entries
     * @param level the level this entry starts at, or null for socketing and rune types
     * @param item  the catalyst item id
     * @param count 1 - {@value RuneOverrides#MAX_PEDESTAL_CATALYSTS}
     */
    public record Entry(@Nullable Type type, @Nullable Integer level, ResourceLocation item, int count) {

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

    /** @return the catalyst every upgrade of the rune type uses on top, or EMPTY */
    public static ItemStack forType(@Nullable Type type) {
        Entry entry = typeEntry(entries(), type);
        return entry == null ? ItemStack.EMPTY : entry.stack();
    }

    /** @return what's left of the type's catalyst next to the other one: nothing if they're the same item, and only what still fits on the pedestals */
    public static ItemStack onTop(ItemStack catalyst, ItemStack typeCatalyst) {
        if (typeCatalyst.isEmpty() || (!catalyst.isEmpty() && typeCatalyst.is(catalyst.getItem()))) return ItemStack.EMPTY;
        return typeCatalyst.copyWithCount(Math.min(typeCatalyst.getCount(), Math.max(0, RuneRitual.PEDESTAL_COUNT - catalyst.getCount())));
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
            if (entry.level() == null && entry.type() == null) return entry;
        }
        return null;
    }

    /** @return the entry with the highest level that the given level reaches */
    static @Nullable Entry levelEntry(List<Entry> entries, int level) {
        Entry best = null;
        for (Entry entry : entries) {
            if (entry.level() == null || entry.level() > level) continue;
            if (best == null || entry.level() > best.level()) best = entry;
        }
        return best;
    }

    static @Nullable Entry typeEntry(List<Entry> entries, @Nullable Type type) {
        if (type == null) return null;
        for (Entry entry : entries) {
            if (entry.type() == type) return entry;
        }
        return null;
    }

    static @Nullable Entry parse(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 2 || parts.length > 3) return null;
        String step = parts[0].trim();
        Integer level = null;
        Type type = Type.byName(step);
        if (type == null && !step.equals(SOCKET)) {
            level = parseInt(step);
            if (level == null || level < 2 || level > 256) return null;
        }
        ResourceLocation item = ResourceLocation.tryParse(parts[1].trim());
        Integer count = parts.length == 3 ? parseInt(parts[2]) : Integer.valueOf(1);
        if (item == null || count == null || count < 1 || count > RuneOverrides.MAX_PEDESTAL_CATALYSTS) return null;
        return new Entry(type, level, item, count);
    }

    private static @Nullable Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
