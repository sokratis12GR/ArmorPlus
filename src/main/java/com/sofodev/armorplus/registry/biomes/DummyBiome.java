package com.sofodev.armorplus.registry.biomes;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class DummyBiome {

    public static Biome build() {
        BiomeSpecialEffects spex = new BiomeSpecialEffects.Builder()
                .skyColor(10668270)
                .fogColor(7133439)
                .waterColor(10869759)
                .waterFogColor(5538960)
                .build();
        Biome.BiomeBuilder builder = new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.SNOW)
                .biomeCategory(Biome.BiomeCategory.PLAINS)
                .downfall(0.4f)
                .temperature(0.0f)
                .temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(spex)
                .generationSettings(BiomeGenerationSettings.EMPTY);
        return builder.build();
    }
}
