package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.thedragonlib.client.config.EntryPicker;
import com.sofodev.thedragonlib.client.config.RuleListContext;
import com.sofodev.thedragonlib.client.config.RuleType;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.sofodev.thedragonlib.client.config.RuleType.Kind;

/**
 * The config lists that open in a field by field editor instead of the plain text list, by config key.
 */
final class APRuleTypes {
    static final String UI_PREFIX = "armorplus.configuration.ui.";
    private static final String CONFIG_PREFIX = "armorplus.configuration.";

    /** @param picker adds several entries at once, if the list has one */
    record Editor(RuleType type, @Nullable EntryPicker picker) {
    }

    private static final RuleType.Field LEVEL = RuleType.integer("level", 1, 256).asOptional();
    private static final RuleType.Field SECONDS = RuleType.integer("seconds", 1, Double.POSITIVE_INFINITY).asOptional();
    private static final RuleType.Field ENABLED = RuleType.toggle("enabled");

    private static final Map<String, Editor> EDITORS = Map.of(
            "armorBuffs", new Editor(new RuleType("armorBuffs", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_ARMOR), LEVEL, SECONDS,
                    RuleType.custom("pieces", APRuleSuggestions.REQUIREMENT).asOptional(), ENABLED), APRuleFormats.ARMOR_BUFF),
                    APEntryPickers.buffs("|1", BuffParser.Use.ARMOR)),
            "weaponBuffs", new Editor(new RuleType("weaponBuffs", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_WEAPON), LEVEL, SECONDS, ENABLED),
                    APRuleFormats.WEAPON_BUFF),
                    APEntryPickers.buffs("|1|" + BuffParser.DEFAULT_WEAPON_SECONDS, BuffParser.Use.WEAPON)),
            "effectSlots", new Editor(new RuleType("effectSlots", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_ARMOR),
                    RuleType.choice("pieceCount", "1", "2", "3", "4").asOptional(), RuleType.toggle("head"), RuleType.toggle("chest"),
                    RuleType.toggle("legs"), RuleType.toggle("feet")), APRuleFormats.EFFECT_SLOT), null),
            "runeOverrides", new Editor(new RuleType("runeOverrides", List.of(RuleType.custom("buff", APRuleSuggestions.EFFECT_ANY),
                    RuleType.integer("socket", 1, 39).asOptional(), RuleType.integer("extract", 0, Double.POSITIVE_INFINITY).asOptional(),
                    RuleType.picker("catalyst", Kind.ITEM, false).asOptional(), RuleType.integer("count", 1, 64).asOptional(),
                    RuleType.toggle("extractable"), RuleType.toggle("disabled")), APRuleFormats.RUNE_OVERRIDE), null),
            "worldRunes", new Editor(new RuleType("worldRunes", List.of(RuleType.choice("kind", "armor", "weapon", "tool"),
                    RuleType.custom("effect", APRuleSuggestions.EFFECT_BY_KIND), LEVEL, SECONDS), APRuleFormats.WORLD_RUNE), null),
            "runeLootSources", new Editor(new RuleType("runeLootSources", List.of(RuleType.custom("table", APRuleSuggestions.LOOT_TABLE), RuleType.chance(false)),
                    APRuleFormats.checked(ArmorPlusConfig::isLootSource)), null),
            "smeltingRecipes", new Editor(new RuleType("smeltingRecipes", List.of(RuleType.picker("block", Kind.BLOCK, false), RuleType.picker("result", Kind.ITEM, false)),
                    APRuleFormats.checked(ArmorPlusConfig::isSmeltingRecipe)), APEntryPickers.smeltingRecipe()));

    private APRuleTypes() {
    }

    static @Nullable Editor of(String key) {
        return EDITORS.get(key);
    }

    static RuleListContext context() {
        return new RuleListContext(UI_PREFIX, CONFIG_PREFIX, new APRuleSuggestions());
    }
}
