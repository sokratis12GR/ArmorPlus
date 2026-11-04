package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import com.sofodev.armorplus.registry.rune.RuneEffectTypes;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

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
 * <p>
 * Armor entries can end with a {@link SetRequirement}, the pieces of the set the effect needs:
 * {@code "minecraft:night_vision|1@head"}, {@code "minecraft:conduit_power|2@legs,feet"} or {@code "minecraft:haste|2@2"}
 * (any 2 pieces).
 */
public final class BuffParser {

    public static final String FORMAT = "modid:effect|level";
    public static final char REQUIREMENT_SEPARATOR = '@';
    /** Entries starting with it are switched off but kept in the list, e.g. "!minecraft:haste|2". */
    public static final char DISABLED_PREFIX = '!';
    /** Default effect duration for weapon on-hit effects, in seconds. */
    public static final int DEFAULT_WEAPON_SECONDS = 10;

    private BuffParser() {
    }

    /** Syntax check for config validation. Does not require the effect to be registered (other mods may load later). */
    public static boolean isValid(Object value) {
        if (!(value instanceof String raw)) return false;
        String s = withoutDisabledPrefix(raw);
        int split = s.indexOf(REQUIREMENT_SEPARATOR);
        if (split >= 0 && !SetRequirement.isValid(s.substring(split + 1))) return false;
        String[] parts = withoutRequirement(s).split("\\|", -1);
        if (parts.length > 3 || Identifier.tryParse(parts[0].trim()) == null) return false;
        for (int i = 1; i < parts.length; i++) {
            Integer number = parseInt(parts[i]);
            if (number == null || number < 1 || (i == 1 && number > 256)) return false;
        }
        return true;
    }

    /** @return true if the entry is switched off ("!minecraft:haste|2") */
    public static boolean isDisabled(Object entry) {
        return String.valueOf(entry).trim().startsWith(String.valueOf(DISABLED_PREFIX));
    }

    /** @return the entry without the "!" of switched off entries */
    public static String withoutDisabledPrefix(String entry) {
        String trimmed = entry.trim();
        return trimmed.startsWith(String.valueOf(DISABLED_PREFIX)) ? trimmed.substring(1) : entry;
    }

    /** @return the entry without its requirement, e.g. "minecraft:haste|2" for "minecraft:haste|2@chest" */
    public static String withoutRequirement(String entry) {
        int split = entry.indexOf(REQUIREMENT_SEPARATOR);
        return split < 0 ? entry : entry.substring(0, split);
    }

    /** @return the requirement of an entry, e.g. the helmet for "minecraft:night_vision|1@head", or null if it has none */
    public static @Nullable SetRequirement requirementOf(String entry) {
        int split = entry.indexOf(REQUIREMENT_SEPARATOR);
        return split < 0 ? null : SetRequirement.parse(entry.substring(split + 1));
    }

    /** @return the identifier part of an entry, e.g. "minecraft:haste" for "minecraft:haste|1" */
    public static String idOf(Object entry) {
        String id = withoutRequirement(withoutDisabledPrefix(String.valueOf(entry))).split("\\|", -1)[0].trim();
        Identifier parsed = Identifier.tryParse(id);
        return parsed != null ? parsed.toString() : id;
    }

    /** @return the config id of a buff, e.g. "minecraft:haste" or "armorplus:flight" */
    public static String idOf(IBuff buff) {
        if (buff instanceof EffectBuff effect) return effect.id().toString();
        if (buff instanceof RuneEffectType type) return type.name();
        String name = buff.name().toLowerCase(Locale.ENGLISH);
        return (buff.isEffect() ? Identifier.DEFAULT_NAMESPACE : MODID) + ":" + name;
    }

    /** What an entry is used for, which decides the ArmorPlus abilities it can name and the default duration. */
    public enum Use {
        /** Worn effects and {@link Buff} abilities, with an optional {@link SetRequirement} */
        ARMOR,
        /** On-hit effects ({@value #DEFAULT_WEAPON_SECONDS} seconds unless given) and {@link DeBuff} abilities */
        WEAPON,
        /** Effects while the tool is held, {@link ToolBuff} abilities and on-hit {@link DeBuff}s (like weapons) */
        TOOL
    }

    /** Builds a single armor buff from an entry, e.g. {@code armor("minecraft:haste|2")}. */
    public static BuffInstance armor(String entry) {
        return parse(entry, Use.ARMOR);
    }

    /** Builds a single weapon (on-hit) buff from an entry, e.g. {@code weapon("minecraft:wither|2|4")}. */
    public static BuffInstance weapon(String entry) {
        return parse(entry, Use.WEAPON);
    }

    /** Builds a single tool (while held) buff from an entry, e.g. {@code tool("armorplus:auto_smelt")}. */
    public static BuffInstance tool(String entry) {
        return parse(entry, Use.TOOL);
    }

    /** @return the armor buffs of the entries, without the invalid and switched off ("!") ones */
    public static List<BuffInstance> armor(Collection<?> entries) {
        return parseAll(entries, Use.ARMOR);
    }

    /** @return the weapon buffs of the entries, without the invalid and switched off ("!") ones */
    public static List<BuffInstance> weapon(Collection<?> entries) {
        return parseAll(entries, Use.WEAPON);
    }

    /** Armor buffs are queried every tick, so parsed lists are cached until the config values change. */
    private record CacheKey(List<?> entries, Use use) {
    }

    private static final Map<CacheKey, List<BuffInstance>> CACHE = new ConcurrentHashMap<>();

    private static List<BuffInstance> parseAll(Collection<?> entries, Use use) {
        CacheKey key = new CacheKey(List.copyOf(entries), use);
        return CACHE.computeIfAbsent(key, k -> List.copyOf(parseUncached(k.entries(), k.use())));
    }

    private static List<BuffInstance> parseUncached(Collection<?> entries, Use use) {
        List<BuffInstance> result = new ArrayList<>();
        for (Object entry : entries) {
            if (!isValid(entry) || isDisabled(entry)) continue;
            BuffInstance instance = parse((String) entry, use);
            if (instance != null) result.add(instance);
        }
        return result;
    }

    private static BuffInstance parse(String rawEntry, Use use) {
        if (!isValid(rawEntry)) return null;
        String entry = withoutDisabledPrefix(rawEntry);
        String[] parts = withoutRequirement(entry).split("\\|", -1);
        Identifier id = Identifier.tryParse(parts[0].trim());
        int amplifier = parts.length > 1 ? parseInt(parts[1]) - 1 : 0;
        Integer seconds = parts.length > 2 ? parseInt(parts[2]) : null;

        IBuff buff = resolve(id, use);
        if (buff == null) return null;
        BuffInstance instance;
        if (!buff.isEffect()) instance = new BuffInstance(buff);
        else if (seconds != null) instance = new BuffInstance(buff, amplifier, seconds);
        else instance = use == Use.WEAPON || buff instanceof DeBuff ? new BuffInstance(buff, amplifier, DEFAULT_WEAPON_SECONDS) : new BuffInstance(buff, amplifier);
        return instance.setRequirement(use == Use.ARMOR ? requirementOf(entry) : null);
    }

    /**
     * ArmorPlus abilities live in the "armorplus" namespace and only resolve for their use (armor {@link Buff}, weapon
     * {@link DeBuff}, tool {@link ToolBuff} or {@link DeBuff}); effects that ArmorPlus already knows keep using their enum
     * constant (and translations), every other effect id becomes a generic {@link EffectBuff}. Tools read the effects known
     * as {@link DeBuff}s (wither, poison, ...) as on-hit effects, like weapons. Ids of the {@link RuneEffectTypes} registry
     * come first; the built-in wrappers resolve to their enum constant.
     */
    private static IBuff resolve(Identifier id, Use use) {
        if (!id.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            RuneEffectType type = RuneEffectTypes.get(id);
            if (type != null) {
                if (!type.uses().contains(use)) return null;
                return type instanceof RuneEffectTypes.Builtin builtin ? builtin.buff() : type;
            }
        }
        String constant = id.getPath().toUpperCase(Locale.ENGLISH);
        if (id.getNamespace().equals(MODID)) {
            IBuff ability = switch (use) {
                case ARMOR -> find(Buff.class, constant);
                case WEAPON -> find(DeBuff.class, constant);
                case TOOL -> {
                    IBuff toolBuff = find(ToolBuff.class, constant);
                    yield toolBuff != null ? toolBuff : find(DeBuff.class, constant);
                }
            };
            return ability != null && !ability.isEffect() ? ability : null;
        }
        IBuff known = switch (use) {
            case ARMOR -> find(Buff.class, constant);
            case WEAPON -> find(DeBuff.class, constant);
            case TOOL -> {
                IBuff deBuff = find(DeBuff.class, constant);
                yield deBuff != null ? deBuff : find(Buff.class, constant);
            }
        };
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
