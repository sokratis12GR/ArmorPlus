package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * The datapack rune registry {@code armorplus:rune} ({@code data/<namespace>/armorplus/rune/<name>.json}, see
 * {@link RuneDefinition}). It's synced to clients, so the creative tab and JEI list the same runes as the server.
 * <p>
 * The loaded runes are kept here when the tags update ({@link TagsUpdatedEvent}, after login and /reload), so code without
 * a level at hand (costs, tooltips) can use them. The registry itself only loads with the world, so /reload doesn't change it.
 */
@EventBusSubscriber(modid = MODID)
public final class RuneDefinitions {

    public static final ResourceKey<Registry<RuneDefinition>> REGISTRY =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(MODID, "rune"));

    private static volatile HolderLookup.@Nullable Provider source;
    private static volatile List<RuneDefinition> all = List.of();
    private static volatile Map<Key, RuneDefinition> byRune = Map.of();

    /** Runes of the same buff but another kind or level (e.g. Haste II and Haste III tool runes) keep their own rules. */
    private record Key(RuneKind kind, String buffId, int level) {
        static Key of(RuneData rune) {
            return new Key(rune.kind(), rune.buffId(), Runes.parseLevel(rune.entry()));
        }
    }

    private RuneDefinitions() {
    }

    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(REGISTRY, RuneDefinition.CODEC, RuneDefinition.CODEC);
    }

    @SubscribeEvent
    public static void onTagsUpdated(TagsUpdatedEvent event) {
        update(event.getRegistries());
    }

    /** Reloads the runes from the registries, unless they are the ones already loaded. */
    public static void update(HolderLookup.Provider registries) {
        if (registries == source) return;
        List<RuneDefinition> result = new ArrayList<>();
        Map<Key, RuneDefinition> index = new HashMap<>();
        registries.lookup(REGISTRY).ifPresent(lookup -> lookup.listElements().forEach(holder -> {
            RuneDefinition definition = holder.value();
            result.add(definition);
            RuneDefinition previous = index.putIfAbsent(Key.of(definition.toRuneData()), definition);
            if (previous != null) {
                ArmorPlus.LOGGER.warn("Datapack rune {} has the same kind, effect and level as another rune, its costs and extraction rules are ignored", holder.key().identifier());
            }
        }));
        all = List.copyOf(result);
        byRune = Map.copyOf(index);
        source = registries;
        Runes.clearCaches();
        ArmorPlus.LOGGER.info("Loaded {} datapack runes", result.size());
    }

    /** @return every loaded datapack rune */
    public static List<RuneDefinition> all() {
        return all;
    }

    /**
     * @return the datapack rune with the same kind, buff and level as the rune (slots don't matter), or null. Used for its
     * socket cost, and for the extraction rules of the rune the Rune Altar would make
     */
    public static @Nullable RuneDefinition of(RuneData rune) {
        return byRune.get(Key.of(rune));
    }
}
