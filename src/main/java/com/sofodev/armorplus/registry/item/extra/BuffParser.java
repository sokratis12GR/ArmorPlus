package com.sofodev.armorplus.registry.item.extra;

import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Parses buff entries shared by the code and the config files.
 * <p>
 * Format: <b>{@code "id|level|seconds"}</b>, where {@code level} and {@code seconds} are optional.
 * <ul>
 *     <li>{@code "minecraft:haste|1"} - Haste I (any vanilla or modded mob effect id works)</li>
 *     <li>{@code "minecraft:regeneration|2|200"} - Regeneration II lasting 200 seconds</li>
 *     <li>{@code "armorplus:flight"} - an ArmorPlus ability (flight, wither_immunity, sculk_immunity, ignite, ...)</li>
 * </ul>
 * The level starts at 1 (level 1 = amplifier 0). Levels are ignored by abilities.
 */
public final class BuffParser {

    public static final String FORMAT = "modid:effect|level";
    /** Default effect duration for weapon on-hit effects, in seconds. */
    public static final int DEFAULT_WEAPON_SECONDS = 10;

    private BuffParser() {
    }

    /** Syntax check for config validation. Does not require the effect to be registered (other mods may load later). */
    public static boolean isValid(Object value) {
        if (!(value instanceof String s)) return false;
        String[] parts = s.split("\\|", -1);
        if (parts.length > 3 || Identifier.tryParse(parts[0].trim()) == null) return false;
        for (int i = 1; i < parts.length; i++) {
            Integer number = parseInt(parts[i]);
            if (number == null || number < 1 || (i == 1 && number > 256)) return false;
        }
        return true;
    }

    /** @return the identifier part of an entry, e.g. "minecraft:haste" for "minecraft:haste|1" */
    public static String idOf(Object entry) {
        String id = String.valueOf(entry).split("\\|", -1)[0].trim();
        Identifier parsed = Identifier.tryParse(id);
        return parsed != null ? parsed.toString() : id;
    }

    /** Builds a single armor buff from an entry, e.g. {@code armor("minecraft:haste|2")}. */
    public static BuffInstance armor(String entry) {
        return parse(entry, false);
    }

    /** Builds a single weapon (on-hit) buff from an entry, e.g. {@code weapon("minecraft:wither|2|4")}. */
    public static BuffInstance weapon(String entry) {
        return parse(entry, true);
    }

    public static List<BuffInstance> armor(Collection<?> entries, Collection<?> disabled) {
        return parseAll(entries, disabled, false);
    }

    public static List<BuffInstance> weapon(Collection<?> entries, Collection<?> disabled) {
        return parseAll(entries, disabled, true);
    }

    /** Armor buffs are queried every tick, so parsed lists are cached until the config values change. */
    private record CacheKey(List<?> entries, List<?> disabled, boolean weapon) {
    }

    private static final Map<CacheKey, List<BuffInstance>> CACHE = new ConcurrentHashMap<>();

    private static List<BuffInstance> parseAll(Collection<?> entries, Collection<?> disabled, boolean weapon) {
        CacheKey key = new CacheKey(List.copyOf(entries), disabled == null ? List.of() : List.copyOf(disabled), weapon);
        return CACHE.computeIfAbsent(key, k -> List.copyOf(parseUncached(k.entries(), k.disabled(), k.weapon())));
    }

    private static List<BuffInstance> parseUncached(Collection<?> entries, Collection<?> disabled, boolean weapon) {
        Set<String> disabledIds = new HashSet<>();
        if (disabled != null) disabled.forEach(entry -> disabledIds.add(idOf(entry)));
        List<BuffInstance> result = new ArrayList<>();
        for (Object entry : entries) {
            if (!isValid(entry) || disabledIds.contains(idOf(entry))) continue;
            BuffInstance instance = parse((String) entry, weapon);
            if (instance != null) result.add(instance);
        }
        return result;
    }

    private static BuffInstance parse(String entry, boolean weapon) {
        if (!isValid(entry)) return null;
        String[] parts = entry.split("\\|", -1);
        Identifier id = Identifier.tryParse(parts[0].trim());
        int amplifier = parts.length > 1 ? parseInt(parts[1]) - 1 : 0;
        Integer seconds = parts.length > 2 ? parseInt(parts[2]) : null;

        IBuff buff = resolve(id, weapon);
        if (buff == null) return null;
        if (!buff.isEffect()) return new BuffInstance(buff);
        if (seconds != null) return new BuffInstance(buff, amplifier, seconds);
        return weapon ? new BuffInstance(buff, amplifier, DEFAULT_WEAPON_SECONDS) : new BuffInstance(buff, amplifier);
    }

    /**
     * ArmorPlus abilities live in the "armorplus" namespace; effects that ArmorPlus already knows keep using their enum
     * constant (and translations), every other effect id becomes a generic {@link EffectBuff}.
     */
    private static IBuff resolve(Identifier id, boolean weapon) {
        String constant = id.getPath().toUpperCase(Locale.ENGLISH);
        IBuff known = weapon ? find(DeBuff.class, constant) : find(Buff.class, constant);
        if (id.getNamespace().equals(MODID)) {
            return known != null && !known.isEffect() ? known : null;
        }
        if (id.getNamespace().equals(Identifier.DEFAULT_NAMESPACE) && known != null && known.isEffect()) return known;
        return new EffectBuff(id);
    }

    private static <E extends Enum<E> & IBuff> IBuff find(Class<E> type, String constant) {
        for (E value : type.getEnumConstants()) {
            if (value.name().equals(constant) && !value.name().equals("NONE")) return value;
        }
        return null;
    }

    private static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
