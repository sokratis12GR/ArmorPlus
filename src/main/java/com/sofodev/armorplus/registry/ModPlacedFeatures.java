package com.sofodev.armorplus.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModPlacedFeatures {

    // ResourceKeys for placed features - Lava
    public static final ResourceKey<PlacedFeature> ORE_LAVA_CRYSTAL_PLACED =
            createPlacedFeatureKey("ore_lava_crystal");
    public static final ResourceKey<PlacedFeature> ORE_LAVA_CRYSTAL_OBSIDIAN_PLACED =
            createPlacedFeatureKey("ore_lava_crystal_obsidian");
    public static final ResourceKey<PlacedFeature> ORE_LAVA_CRYSTAL_STONE_PLACED =
            createPlacedFeatureKey("ore_lava_crystal_stone");

    // ResourceKeys for placed features - Frost
    public static final ResourceKey<PlacedFeature> ORE_FROST_CRYSTAL_PLACED =
            createPlacedFeatureKey("ore_frost_crystal");
    public static final ResourceKey<PlacedFeature> ORE_FROST_CRYSTAL_OBSIDIAN_PLACED =
            createPlacedFeatureKey("ore_frost_crystal_obsidian");
    public static final ResourceKey<PlacedFeature> ORE_FROST_CRYSTAL_STONE_PLACED =
            createPlacedFeatureKey("ore_frost_crystal_stone");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Lava crystals
        registerOrePlacedFeature(context, configuredFeatures,
                ModOreFeatures.ORE_LAVA_CRYSTAL,
                ORE_LAVA_CRYSTAL_PLACED,
                15, -60, -30
        );
        registerOrePlacedFeature(context, configuredFeatures,
                ModOreFeatures.ORE_LAVA_CRYSTAL_OBSIDIAN,
                ORE_LAVA_CRYSTAL_OBSIDIAN_PLACED,
                20, -30, 0
        );
        registerOrePlacedFeature(context, configuredFeatures,
                ModOreFeatures.ORE_LAVA_CRYSTAL_STONE,
                ORE_LAVA_CRYSTAL_STONE_PLACED,
                25, 0, 200
        );

        registerOrePlacedFeature(context, configuredFeatures,
                ModOreFeatures.ORE_FROST_CRYSTAL,
                ORE_FROST_CRYSTAL_PLACED,
                15, -60, -30
        );
        registerOrePlacedFeature(context, configuredFeatures,
                ModOreFeatures.ORE_FROST_CRYSTAL_OBSIDIAN,
                ORE_FROST_CRYSTAL_OBSIDIAN_PLACED,
                20, -30, 0
        );
        registerOrePlacedFeature(context, configuredFeatures,
                ModOreFeatures.ORE_FROST_CRYSTAL_STONE,
                ORE_FROST_CRYSTAL_STONE_PLACED,
                25, 0, 200
        );
    }


    /**
     * Helper to bootstrap a placed ore feature.
     *
     * @param context          Bootstrap context for placed features
     * @param configuredGetter HolderGetter for configured features
     * @param configuredKey    The configured feature resource key
     * @param placedKey        The placed feature resource key
     * @param count            How many veins per chunk
     * @param minHeight        Minimum Y (above bottom)
     * @param maxHeight        Maximum Y (below top)
     */
    private static void registerOrePlacedFeature(
            BootstrapContext<PlacedFeature> context,
            HolderGetter<ConfiguredFeature<?, ?>> configuredGetter,
            ResourceKey<ConfiguredFeature<?, ?>> configuredKey,
            ResourceKey<PlacedFeature> placedKey,
            int count,
            int minHeight,
            int maxHeight
    ) {
        var feature = configuredGetter.getOrThrow(configuredKey);

        var modifiers = List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.aboveBottom(minHeight),
                        VerticalAnchor.belowTop(maxHeight)
                ),
                BiomeFilter.biome()
        );

        context.register(placedKey, new PlacedFeature(feature, modifiers));
    }

    public static ResourceKey<PlacedFeature> createPlacedFeatureKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MODID, name));
    }
}
