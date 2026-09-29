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
        this.biomeModifierRoot = output.getOutputFolder()
                .resolve("data")
                .resolve(MODID)
                .resolve("forge")
                .resolve("biome_modifier");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();

        addFeature(writes, cache, "ore_lava_crystal",
                "#minecraft:is_overworld", MODID + ":ore_lava_crystal");
        addFeature(writes, cache, "ore_lava_crystal_obsidian",
                "#minecraft:is_overworld", MODID + ":ore_lava_crystal_obsidian");
        addFeature(writes, cache, "ore_lava_crystal_stone",
                "#minecraft:is_overworld", MODID + ":ore_lava_crystal_stone");

        addFeature(writes, cache, "ore_frost_crystal",
                "#forge:is_cold", MODID + ":ore_frost_crystal");
        addFeature(writes, cache, "ore_frost_crystal_obsidian",
                "#forge:is_cold", MODID + ":ore_frost_crystal_obsidian");
        addFeature(writes, cache, "ore_frost_crystal_stone",
                "#forge:is_cold", MODID + ":ore_frost_crystal_stone");

        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private void addFeature(List<CompletableFuture<?>> writes, CachedOutput cache,
                            String name, String biomes, String feature) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "forge:add_features");
        json.addProperty("biomes", biomes);
        json.addProperty("features", feature);
        json.addProperty("step", "underground_ores");

        writes.add(DataProvider.saveStable(
                cache,
                json,
                biomeModifierRoot.resolve(name + ".json")
        ));
    }

    @Override
    public String getName() {
        return "ArmorPlus Biome Modifiers";
    }
}
