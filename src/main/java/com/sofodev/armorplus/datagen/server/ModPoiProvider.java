package com.sofodev.armorplus.datagen.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public final class ModPoiProvider implements DataProvider {
    private final PackOutput output;

    public ModPoiProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        JsonObject json = new JsonObject();
        JsonArray values = new JsonArray();
        values.add(MODID + ":soul_exchanger");
        json.add("values", values);

        Path path = output.getOutputFolder()
                .resolve("data")
                .resolve("minecraft")
                .resolve("tags")
                .resolve("point_of_interest_type")
                .resolve("acquirable_job_site.json");

        return DataProvider.saveStable(cache, json, path);
    }

    @Override
    public String getName() {
        return "ArmorPlus POI Tags";
    }
}
