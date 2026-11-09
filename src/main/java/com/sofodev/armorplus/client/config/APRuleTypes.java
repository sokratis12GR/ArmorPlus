package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.RuneOverrides;
import com.sofodev.thedragonlib.client.config.EntryPicker;
import com.sofodev.thedragonlib.client.config.RuleListContext;
import com.sofodev.thedragonlib.client.config.RuleType;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.Nullable;

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

    private static final RuleType.Field LEVEL = RuleType.integer("level", 1, 256).asOptional().withFallback("1");
    private static final RuleType.Field SECONDS = RuleType.integer("seconds", 1, Double.POSITIVE_INFINITY).asOptional().asInline();
    private static final RuleType.Field ENABLED = RuleType.toggle("enabled");

    private static final Map<String, Editor> EDITORS = Map.of(
            "armorBuffs", new Editor(new RuleType("armorBuffs", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_ARMOR), LEVEL, SECONDS,
                    RuleType.custom("pieces", APRuleSuggestions.REQUIREMENT).asOptional(), ENABLED), APRuleFormats.ARMOR_BUFF),
                    APEntryPickers.buffs("|1", BuffParser.Use.ARMOR)),
            "weaponBuffs", new Editor(new RuleType("weaponBuffs", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_WEAPON), LEVEL, SECONDS, ENABLED),
                    APRuleFormats.WEAPON_BUFF),
                    APEntryPickers.buffs("|1|" + BuffParser.DEFAULT_WEAPON_SECONDS, BuffParser.Use.WEAPON)),
            "bowBuffs", new Editor(new RuleType("bowBuffs", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_RANGED), LEVEL, SECONDS, ENABLED),
                    APRuleFormats.WEAPON_BUFF),
                    APEntryPickers.buffs("|1|" + BuffParser.DEFAULT_WEAPON_SECONDS, BuffParser.Use.RANGED)),
            "effectSlots", new Editor(new RuleType("effectSlots", List.of(RuleType.custom("effect", APRuleSuggestions.EFFECT_ARMOR),
                    RuleType.choice("pieceCount", "1", "2", "3", "4").asOptional(),
                    RuleType.toggle("head").withIcon(InventoryMenu.EMPTY_ARMOR_SLOT_HELMET).withRowLabel("pieces"),
                    RuleType.toggle("chest").withIcon(InventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE).asInline(),
                    RuleType.toggle("legs").withIcon(InventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS).asInline(),
                    RuleType.toggle("feet").withIcon(InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS).asInline()), APRuleFormats.EFFECT_SLOT), null),
            "runeOverrides", new Editor(new RuleType("runeOverrides", List.of(RuleType.custom("buff", APRuleSuggestions.EFFECT_ANY),
                    RuleType.integer("socket", 1, 39).asOptional(), RuleType.integer("extract", 0, Double.POSITIVE_INFINITY).asOptional().asInline(),
                    RuleType.integer("max", 1, 256).asOptional(),
                    ENABLED, RuleType.toggle("extractable").asInline(),
                    RuleType.picker("socketCatalyst", Kind.ITEM, false).asOptional(), catalystCount("socketCatalystCount", RuneOverrides.MAX_PEDESTAL_CATALYSTS),
                    RuleType.picker("catalyst", Kind.ITEM, false).asOptional(), catalystCount("count", 64),
                    RuleType.picker("upgradeCatalyst", Kind.ITEM, false).asOptional(), catalystCount("upgradeCatalystCount", RuneOverrides.MAX_PEDESTAL_CATALYSTS),
                    RuleType.custom("levelCatalysts", APRuleSuggestions.LEVEL_CATALYSTS).asOptional()), APRuleFormats.RUNE_OVERRIDE), null),
            "ritualCatalysts", new Editor(new RuleType("ritualCatalysts", List.of(RuleType.choice("step", "socket", "2", "3", "4", "5", "6", "7", "8", "9", "10", "armor", "weapon", "tool", "bow", "effect", "instant", "modded"),
                    RuleType.picker("ritualCatalyst", Kind.ITEM, false), catalystCount("ritualCatalystCount", RuneOverrides.MAX_PEDESTAL_CATALYSTS)), APRuleFormats.RITUAL_CATALYST), null),
            "worldRunes", new Editor(new RuleType("worldRunes", List.of(RuleType.choice("kind", "armor", "weapon", "tool", "ranged"),
                    RuleType.custom("effect", APRuleSuggestions.EFFECT_BY_KIND), LEVEL, SECONDS), APRuleFormats.WORLD_RUNE), null),
            "runeLootSources", new Editor(new RuleType("runeLootSources", List.of(RuleType.custom("table", APRuleSuggestions.LOOT_TABLE), RuleType.chance(false)),
                    APRuleFormats.checked(ArmorPlusConfig::isLootSource)), null),
            "smeltingRecipes", new Editor(new RuleType("smeltingRecipes", List.of(RuleType.picker("block", Kind.BLOCK, false), RuleType.picker("result", Kind.ITEM, false)),
                    APRuleFormats.checked(ArmorPlusConfig::isSmeltingRecipe)), APEntryPickers.smeltingRecipe()));

    private APRuleTypes() {
    }

    /** Sits next to its catalyst; left empty it's one item. */
    private static RuleType.Field catalystCount(String name, int max) {
        return RuleType.integer(name, 1, max).asOptional().asInline().withFallback("1");
    }

    static @Nullable Editor of(String key) {
        return EDITORS.get(key);
    }

    static RuleListContext context() {
        return new RuleListContext(UI_PREFIX, CONFIG_PREFIX, new APRuleSuggestions());
    }
}
