package com.sofodev.armorplus.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public final class ModEquipmentProvider implements DataProvider {
    private static final List<String> EQUIPMENT = List.of("ardite", "chicken", "coal", "cobalt", "emerald", "ender_dragon", "frost", "frost_lava", "guardian", "infused_lava", "knight_slime", "lapis", "manyullyn", "obsidian", "pig_iron", "redstone", "slayer", "slime", "super_star");

    private final Path equipmentRoot;

    public ModEquipmentProvider(PackOutput output) {
        this.equipmentRoot = output.getOutputFolder().resolve("assets").resolve(MODID).resolve("equipment");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();

        for (String equipment : EQUIPMENT) {
            JsonObject json = new JsonObject();
            JsonObject layers = new JsonObject();

            layers.add("humanoid", layer(MODID + ":" + equipment));

            layers.add("humanoid_leggings", layer(MODID + ":" + equipment));

            json.add("layers", layers);

            writes.add(DataProvider.saveStable(cache, json, equipmentRoot.resolve(equipment + ".json")));
        }

        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private JsonArray layer(String texture) {
        JsonObject entry = new JsonObject();
        entry.addProperty("texture", texture);

        JsonArray array = new JsonArray();
        array.add(entry);

        return array;
    }

    @Override
    public String getName() {
        return "ArmorPlus Equipment";
    }
}