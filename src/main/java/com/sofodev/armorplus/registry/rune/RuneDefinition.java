package com.sofodev.armorplus.registry.rune;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

import java.util.List;
import java.util.Optional;

/**
 * A rune added by a datapack, loaded from {@code data/<namespace>/armorplus/rune/<name>.json} (see {@link RuneDefinitions}):
 * <pre>{@code
 * { "kind": "tool", "effect": "minecraft:haste", "level": 2,
 *   "socket_cost": 10,
 *   "extraction": { "cost": 15, "catalyst": { "item": "minecraft:nether_star", "count": 1 } },
 *   "loot": [ { "table": "minecraft:chests/ancient_city", "chance": 0.1 } ] }
 * }</pre>
 * The definition is turned into a regular {@link RuneData} entry ("id|level|seconds@slots", see {@link BuffParser}), so
 * runes keep working when the datapack changes.
 *
 * @param kind       armor, weapon or tool
 * @param effect     a mob effect id, or an ability id (e.g. "armorplus:auto_smelt")
 * @param level      the effect level, none for abilities
 * @param seconds    the duration of on-hit effects (weapon and tool debuffs)
 * @param slots      the armor pieces an armor rune fits into ("head", "chest", "legs", "feet"), empty for the effect's "effectSlots" default
 * @param socketCost experience levels to socket it (the config "runeOverrides" still wins)
 * @param extraction extraction rules of the buff (the config "runeOverrides" still wins)
 * @param loot       loot tables the rune can be found in on its own; runes without any join the "runeLootSources" pool
 */
public record RuneDefinition(RuneKind kind, Identifier effect, Optional<Integer> level, Optional<Integer> seconds,
                             List<String> slots, Optional<Integer> socketCost, Optional<Extraction> extraction,
                             List<LootSource> loot) {

    public record Catalyst(Identifier item, int count) {
        public static final Codec<Catalyst> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.fieldOf("item").forGetter(Catalyst::item),
                Codec.intRange(1, 64).optionalFieldOf("count", 1).forGetter(Catalyst::count)
        ).apply(instance, Catalyst::new));
    }

    public record Extraction(Optional<Integer> cost, Optional<Catalyst> catalyst, boolean extractable) {
        public static final Codec<Extraction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("cost").forGetter(Extraction::cost),
                Catalyst.CODEC.optionalFieldOf("catalyst").forGetter(Extraction::catalyst),
                Codec.BOOL.optionalFieldOf("extractable", true).forGetter(Extraction::extractable)
        ).apply(instance, Extraction::new));
    }

    public record LootSource(Identifier table, double chance) {
        public static final Codec<LootSource> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.fieldOf("table").forGetter(LootSource::table),
                Codec.doubleRange(0.0, 1.0).fieldOf("chance").forGetter(LootSource::chance)
        ).apply(instance, LootSource::new));
    }

    private static final Codec<String> SLOT_CODEC = Codec.STRING.validate(slot -> SetRequirement.isValid(slot) && !Character.isDigit(slot.charAt(0))
            && slot.indexOf(SetRequirement.COUNT_SEPARATOR) < 0
            ? DataResult.success(slot)
            : DataResult.error(() -> "Unknown armor slot " + slot + ", expected head, chest, legs or feet"));

    public static final Codec<RuneDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RuneKind.CODEC.fieldOf("kind").forGetter(RuneDefinition::kind),
            Identifier.CODEC.fieldOf("effect").forGetter(RuneDefinition::effect),
            ExtraCodecs.POSITIVE_INT.optionalFieldOf("level").forGetter(RuneDefinition::level),
            ExtraCodecs.POSITIVE_INT.optionalFieldOf("seconds").forGetter(RuneDefinition::seconds),
            SLOT_CODEC.listOf().optionalFieldOf("slots", List.of()).forGetter(RuneDefinition::slots),
            Codec.intRange(1, 39).optionalFieldOf("socket_cost").forGetter(RuneDefinition::socketCost),
            Extraction.CODEC.optionalFieldOf("extraction").forGetter(RuneDefinition::extraction),
            LootSource.CODEC.listOf().optionalFieldOf("loot", List.of()).forGetter(RuneDefinition::loot)
    ).apply(instance, RuneDefinition::new));

    /** @return the rune as stored on items, e.g. {@code tool|minecraft:haste|2} or {@code armor|minecraft:luck|1@head} */
    public RuneData toRuneData() {
        StringBuilder entry = new StringBuilder(effect.toString());
        if (level.isPresent() || seconds.isPresent()) entry.append('|').append(level.orElse(1));
        seconds.ifPresent(s -> entry.append('|').append(s));
        if (kind == RuneKind.ARMOR && !slots.isEmpty()) {
            entry.append(BuffParser.REQUIREMENT_SEPARATOR).append(String.join(",", slots));
        }
        return new RuneData(kind, entry.toString());
    }

    /** @return the chance to find the rune in the loot table on its own, 0 if it isn't one of its loot tables */
    public double lootChance(Identifier table) {
        for (LootSource source : loot) {
            if (source.table().equals(table)) return source.chance();
        }
        return 0.0;
    }
}
