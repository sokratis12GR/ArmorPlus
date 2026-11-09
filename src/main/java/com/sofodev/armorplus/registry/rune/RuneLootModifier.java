package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adds world runes to loot: datapack runes ({@link RuneDefinitions}) roll on their own "loot" tables, and one random rune
 * of the pool (datapack runes without "loot" plus the "worldRunes" config) can be added to the "runeLootSources" tables.
 * The pool's loot tables are configured in the config instead of the modifier's conditions, so they can be changed without a datapack.
 */
public class RuneLootModifier extends LootModifier {

    public static final MapCodec<RuneLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance).apply(instance, RuneLootModifier::new));

    /** This runs for every block broken and mob killed, so the "runeLootSources" config isn't parsed each time. */
    private record PoolChances(List<? extends String> sources, Map<ResourceLocation, Double> chances) {
    }

    private static volatile @Nullable PoolChances poolChances;

    public RuneLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!Runes.enabled()) return generatedLoot;
        RuneDefinitions.update(context.getLevel().registryAccess());
        ResourceLocation table = context.getQueriedLootTableId();

        // datapack runes with their own loot tables roll on their own
        for (RuneDefinition definition : RuneDefinitions.inLootTable(table)) {
            double chance = definition.lootChance(table);
            if (chance <= 0.0 || context.getRandom().nextDouble() >= chance) continue;
            RuneData rune = definition.toRuneData();
            if (Runes.resolve(rune) != null) generatedLoot.add(RuneItem.create(rune.kind(), rune.entry()));
        }

        double chance = chanceFor(table);
        if (chance <= 0.0 || context.getRandom().nextDouble() >= chance) return generatedLoot;
        List<RuneData> runes = poolRunes();
        if (runes.isEmpty()) return generatedLoot;
        RuneData rune = runes.get(context.getRandom().nextInt(runes.size()));
        generatedLoot.add(RuneItem.create(rune.kind(), rune.entry()));
        return generatedLoot;
    }

    private static double chanceFor(ResourceLocation lootTable) {
        List<? extends String> sources = ArmorPlusConfig.runeLootSources.get();
        PoolChances cached = poolChances;
        // the config returns the same list until it changes
        if (cached == null || cached.sources() != sources) {
            cached = new PoolChances(sources, parseChances(sources));
            poolChances = cached;
        }
        return cached.chances().getOrDefault(lootTable, 0.0);
    }

    /** @return the chance of each "table|chance" entry, the first entry of a table wins and malformed ones are skipped */
    static Map<ResourceLocation, Double> parseChances(List<? extends String> sources) {
        Map<ResourceLocation, Double> chances = new HashMap<>();
        for (String source : sources) {
            String[] parts = source.split("\\|", -1);
            ResourceLocation table = ResourceLocation.tryParse(parts[0].trim());
            if (parts.length != 2 || table == null) continue;
            try {
                chances.putIfAbsent(table, Double.parseDouble(parts[1].trim()));
            } catch (NumberFormatException ignored) {
            }
        }
        return chances;
    }

    /** @return the active world runes: every datapack rune ({@link RuneDefinitions}) and the "worldRunes" config */
    public static List<RuneData> worldRunes() {
        return activeRunes(false);
    }

    /** @return the runes of the "runeLootSources" pool: datapack runes without loot tables of their own and the "worldRunes" config */
    private static List<RuneData> poolRunes() {
        return activeRunes(true);
    }

    private static List<RuneData> activeRunes(boolean poolOnly) {
        List<RuneData> result = new ArrayList<>();
        for (RuneDefinition definition : RuneDefinitions.all()) {
            if (poolOnly && !definition.loot().isEmpty()) continue;
            RuneData rune = definition.toRuneData();
            if (Runes.resolve(rune) != null) result.add(rune);
        }
        // "armor|entry", "weapon|entry", "tool|entry", "ranged|entry"
        for (String value : ArmorPlusConfig.worldRunes.get()) {
            int split = value.indexOf('|');
            RuneKind kind = RuneKind.byName(value.substring(0, split).trim());
            RuneData rune = new RuneData(kind, value.substring(split + 1).trim());
            if (Runes.resolve(rune) != null) result.add(rune);
        }
        return result;
    }

    @Override
    public MapCodec<? extends LootModifier> codec() {
        return CODEC;
    }
}
