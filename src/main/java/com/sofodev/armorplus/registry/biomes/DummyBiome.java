package com.sofodev.armorplus.registry.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class DummyBiome {

    public static Biome build() {
        BiomeAmbience.Builder ambience = new BiomeAmbience.Builder()
                .withSkyColor(10668270)
                .setFogColor(7133439)
                .setWaterColor(10869759)
                .setWaterFogColor(5538960);
        Biome.Builder builder = new Biome.Builder()
                .precipitation(Biome.RainType.SNOW)
                .category(Biome.Category.PLAINS)
                .scale(0.5f)
                .depth(0.125f)
                .temperature(0.0f)
                .downfall(0.4f)
                .setEffects(ambience.build())
                .withTemperatureModifier(Biome.TemperatureModifier.FROZEN)
                .withMobSpawnSettings(new MobSpawnInfo.Builder().copy())
                .withGenerationSettings(BiomeGenerationSettings.DEFAULT_SETTINGS);
        return builder.build();
    }
}
