package com.sofodev.armorplus.registry.rune;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds world runes to loot: datapack runes ({@link RuneDefinitions}) roll on their own "loot" tables, and one random rune
 * of the pool (datapack runes without "loot" plus the "worldRunes" config) can be added to the "runeLootSources" tables.
 * The pool's loot tables are configured in the config instead of the modifier's conditions, so they can be changed without a datapack.
 */
public class RuneLootModifier extends LootModifier {

    public static final MapCodec<RuneLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance).apply(instance, RuneLootModifier::new));

    public RuneLootModifier(LootItemCondition[] conditions, int priority) {
        super(conditions, priority);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!Runes.enabled()) return generatedLoot;
        RuneDefinitions.update(context.getLevel().registryAccess());
        Identifier table = context.getQueriedLootTableId();

        // datapack runes with their own loot tables roll on their own
        for (RuneDefinition definition : RuneDefinitions.all()) {
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

    private static double chanceFor(Identifier lootTable) {
        for (String source : ArmorPlusConfig.runeLootSources.get()) {
            String[] parts = source.split("\\|", -1);
            if (lootTable.equals(Identifier.tryParse(parts[0].trim()))) return Double.parseDouble(parts[1].trim());
        }
        return 0.0;
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
        // "armor|entry", "weapon|entry", "tool|entry"
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
