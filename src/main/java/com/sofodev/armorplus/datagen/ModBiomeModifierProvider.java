package com.sofodev.armorplus.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public final class ModBiomeModifierProvider implements DataProvider {

    private final Path biomeModifierRoot;

    public ModBiomeModifierProvider(PackOutput output) {
        this.biomeModifierRoot = output.getOutputFolder().resolve("data").resolve(MODID).resolve("neoforge").resolve("biome_modifier");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();

        // Lava crystals - all Overworld biomes
        addFeature(writes, cache, "ore_lava_crystal", "#c:is_overworld", MODID + ":ore_lava_crystal");

        addFeature(writes, cache, "ore_lava_crystal_obsidian", "#c:is_overworld", MODID + ":ore_lava_crystal_obsidian");

        addFeature(writes, cache, "ore_lava_crystal_stone", "#c:is_overworld", MODID + ":ore_lava_crystal_stone");

        // Frost crystals - cold Overworld biomes
        addFeature(writes, cache, "ore_frost_crystal", "#c:is_cold/overworld", MODID + ":ore_frost_crystal");

        addFeature(writes, cache, "ore_frost_crystal_obsidian", "#c:is_cold/overworld", MODID + ":ore_frost_crystal_obsidian");

        addFeature(writes, cache, "ore_frost_crystal_stone", "#c:is_cold/overworld", MODID + ":ore_frost_crystal_stone");

        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private void addFeature(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String biomes, String feature) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "neoforge:add_features");
        json.addProperty("biomes", biomes);
        json.addProperty("features", feature);
        json.addProperty("step", "underground_ores");

        writes.add(DataProvider.saveStable(cache, json, biomeModifierRoot.resolve(name + ".json")));
    }

    @Override
    public String getName() {
        return "ArmorPlus Biome Modifiers";
    }
}
