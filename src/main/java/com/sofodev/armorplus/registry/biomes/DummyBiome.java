package com.sofodev.armorplus.registry.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class DummyBiome {

    public static Biome build() {
        BiomeAmbience.Builder ambience = new BiomeAmbience.Builder()
                .skyColor(10668270)
                .fogColor(7133439)
                .waterColor(10869759)
                .waterFogColor(5538960);
        Biome.Builder builder = new Biome.Builder()
                .precipitation(Biome.RainType.SNOW)
                .biomeCategory(Biome.Category.PLAINS)
                .scale(0.5f)
                .depth(0.125f)
                .temperature(0.0f)
                .downfall(0.4f)
                .specialEffects(ambience.build())
                .temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
                .mobSpawnSettings(new MobSpawnInfo.Builder().build())
                .generationSettings(BiomeGenerationSettings.EMPTY);
        return builder.build();
    }
}
