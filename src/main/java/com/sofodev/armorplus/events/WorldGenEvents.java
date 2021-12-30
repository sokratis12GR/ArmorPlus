package com.sofodev.armorplus.events;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.ModConfiguredFeatures.*;
import static java.util.Arrays.stream;
import static net.minecraft.world.level.biome.Biome.BiomeCategory.*;
import static net.minecraft.world.level.biome.Biome.Precipitation.SNOW;
import static net.minecraft.world.level.biome.Biome.TemperatureModifier.FROZEN;

public class WorldGenEvents {

    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        boolean isSnowy = matchesBiomes(event.getCategory(), ICY, TAIGA, EXTREME_HILLS);
        boolean isSnowing = event.getClimate().precipitation == SNOW;
        boolean isFreezing = event.getClimate().temperatureModifier == FROZEN;

        if (!matchesBiomes(event.getCategory(), ICY, THEEND, OCEAN, NONE)) {
            withConfiguration(event, ORE_LAVA_CR_STONE_PF, oreLavaCrystalStone.enabled.get());
            withConfiguration(event, ORE_LAVA_CR_OBSIDIAN_PF, oreLavaCrystalObsidian.enabled.get());
            withConfiguration(event, ORE_LAVA_CRYSTAL_PF, oreLavaCrystalCompressed.enabled.get());
        }

        if (isSnowy || isSnowing || isFreezing) {
            withConfiguration(event, ORE_FROST_CR_STONE_PF, oreFrostCrystalStone.enabled.get());
            withConfiguration(event, ORE_FROST_CR_OBSIDIAN_PF, oreFrostCrystalObsidian.enabled.get());
            withConfiguration(event, ORE_FROST_CRYSTAL_PF, oreFrostCrystalCompressed.enabled.get());
        }
    }

    private static void withConfiguration(BiomeLoadingEvent event, PlacedFeature placedFeature, boolean enabled) {
        if (enabled) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placedFeature);
        }
    }

    /**
     * A quick function that checks if selected biome category matches any of the categories we specified.
     */
    private static boolean matchesBiomes(Biome.BiomeCategory category, Biome.BiomeCategory... categories) {
        return stream(categories).anyMatch((s) -> category.getName().equals(s.getName()));
    }

}
