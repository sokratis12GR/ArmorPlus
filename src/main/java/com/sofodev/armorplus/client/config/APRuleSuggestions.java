package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import com.sofodev.armorplus.registry.item.extra.BuffParser.Use;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.RuneEffectTypes;
import com.sofodev.armorplus.registry.rune.RuneOverrides;
import com.sofodev.thedragonlib.client.config.RegistrySuggestionSource;
import com.sofodev.thedragonlib.client.config.RuleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * The ArmorPlus lookups of the rule editors: effects by what they can be used on, armor pieces and loot tables.
 */
final class APRuleSuggestions extends RegistrySuggestionSource {
    static final String EFFECT_ARMOR = "effect_armor";
    static final String EFFECT_WEAPON = "effect_weapon";
    static final String EFFECT_RANGED = "effect_ranged";
    static final String EFFECT_ANY = "effect_any";
    /** Effects for the rune kind in the line's first field. */
    static final String EFFECT_BY_KIND = "effect_by_kind";
    /** Pieces or a piece count, the "@..." suffix of an armor buff. */
    static final String REQUIREMENT = "requirement";
    static final String LOOT_TABLE = "loot_table";
    /** The {@code upgrade_catalyst@level=...} of a rune override, listed as {@code "level=item,level=item"}. */
    static final String LEVEL_CATALYSTS = "level_catalysts";

    private static final List<String> SLOT_OPTIONS = List.of("head", "chest", "legs", "feet", "head,chest", "legs,feet", "head,chest,legs,feet",
            "head,chest:1", "legs,feet:1", "chest,legs,feet:2", "head,chest,legs:2");
    private static final List<String> COUNT_OPTIONS = List.of("1", "2", "3", "4");
    private static final List<String> LEVEL_CATALYST_OPTIONS = List.of("2=armorplus:ember_sigil", "3=armorplus:rime_sigil", "4=armorplus:equinox_sigil", "5=armorplus:astral_sigil");

    private final Map<@Nullable Use, List<Option>> effects = new HashMap<>();
    private @Nullable Map<String, String> effectNames;

    APRuleSuggestions() {
        super(APRuleTypes.UI_PREFIX);
    }

    private List<Option> effects(@Nullable Use use) {
        return effects.computeIfAbsent(use, u -> RegistryPickerScreen.collectEffects(u).stream()
                .map(c -> new Option(c.id(), c.name().getString(), c.icon())).toList());
    }

    private static @Nullable Use useOfKind(String[] values) {
        if (values.length == 0) return null;
        return switch (values[0]) {
            case "armor" -> Use.ARMOR;
            case "weapon" -> Use.WEAPON;
            case "tool" -> Use.TOOL;
            case "ranged" -> Use.RANGED;
            default -> null;
        };
    }

    private static @Nullable Use use(RuleType.Field field, String[] values) {
        return switch (field.source()) {
            case EFFECT_ARMOR -> Use.ARMOR;
            case EFFECT_WEAPON -> Use.WEAPON;
            case EFFECT_RANGED -> Use.RANGED;
            case EFFECT_BY_KIND -> useOfKind(values);
            default -> null;
        };
    }

    private static boolean isEffectSource(RuleType.Field field) {
        return switch (field.source()) {
            case EFFECT_ARMOR, EFFECT_WEAPON, EFFECT_RANGED, EFFECT_ANY, EFFECT_BY_KIND -> true;
            default -> false;
        };
    }

    /** Abilities only work for their use, the same way {@link BuffParser} resolves them. */
    private static boolean isEffect(String id, @Nullable Use use) {
        ResourceLocation parsed = ResourceLocation.tryParse(id);
        if (parsed == null) return false;
        RuneEffectType ability = RuneEffectTypes.get(parsed);
        if (ability != null) return use == null || ability.uses().contains(use);
        return (use == null || !parsed.getNamespace().equals(MODID)) && BuiltInRegistries.MOB_EFFECT.containsKey(parsed);
    }

    private static List<Option> pieces(List<String> ids) {
        return ids.stream().map(id -> new Option(id, pieceName(id))).toList();
    }

    /** E.g. "Leggings + Boots", "Any 2 of: Chestplate + Leggings + Boots" or "Any 2 of the set" */
    static String pieceName(String value) {
        if (!value.isEmpty() && Character.isDigit(value.charAt(0))) {
            return Component.translatable(APRuleTypes.UI_PREFIX + "pieces.count", value).getString();
        }
        int split = value.indexOf(SetRequirement.COUNT_SEPARATOR);
        if (split >= 0) {
            return Component.translatable(APRuleTypes.UI_PREFIX + "pieces.some", value.substring(split + 1), slotNames(value.substring(0, split))).getString();
        }
        return slotNames(value);
    }

    private static String slotNames(String slots) {
        return Arrays.stream(slots.split(",", -1))
                .map(slot -> Component.translatable(APRuleTypes.UI_PREFIX + "slot." + slot.trim().toLowerCase(Locale.ROOT)).getString())
                .collect(Collectors.joining(" + "));
    }

    @Override
    protected List<Option> customOptions(RuleType.Field field, String[] values) {
        if (isEffectSource(field)) return effects(use(field, values));
        return switch (field.source()) {
            case REQUIREMENT -> {
                List<Option> options = new ArrayList<>(pieces(SLOT_OPTIONS));
                options.addAll(pieces(COUNT_OPTIONS));
                yield options;
            }
            case LOOT_TABLE -> BuiltInLootTables.all().stream().map(key -> key.location().toString()).sorted()
                    .map(id -> new Option(id, id)).toList();
            case LEVEL_CATALYSTS -> LEVEL_CATALYST_OPTIONS.stream().map(id -> new Option(id, id)).toList();
            default -> List.of();
        };
    }

    @Override
    protected boolean customKnown(RuleType.Field field, String id, String[] values) {
        if (isEffectSource(field)) return isEffect(id, use(field, values));
        return switch (field.source()) {
            case REQUIREMENT -> SetRequirement.isValid(id);
            // Not every loot table is known here (datapacks only load with a world), so any id goes.
            case LOOT_TABLE -> ArmorPlusConfig.isIdentifier(id);
            case LEVEL_CATALYSTS -> RuneOverrides.isValidLevelCatalysts(id);
            default -> false;
        };
    }

    @Override
    protected String customName(RuleType.Field field, String id) {
        if (isEffectSource(field)) {
            if (effectNames == null) effectNames = effects(null).stream().collect(Collectors.toMap(Option::id, Option::name, (a, b) -> a));
            return effectNames.getOrDefault(normalize(id), id);
        }
        return switch (field.source()) {
            case REQUIREMENT -> pieceName(id);
            default -> id;
        };
    }
}
