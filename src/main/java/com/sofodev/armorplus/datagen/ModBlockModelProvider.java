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

public final class ModBlockModelProvider implements DataProvider {
    private static final List<String> COLORS = List.of("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");

    private final Path blockstateRoot;
    private final Path modelRoot;

    public ModBlockModelProvider(PackOutput output) {
        Path assetRoot = output.getOutputFolder().resolve("assets").resolve(MODID);

        this.blockstateRoot = assetRoot.resolve("blockstates");

        this.modelRoot = assetRoot.resolve("models").resolve("block");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();

        generateSimpleBlocks(writes, cache);
        generateSnowBrick(writes, cache);

        for (String color : COLORS) {
            generateStoneBrickFamily(writes, cache, color);

            generateCastleFamily(writes, cache, color);
        }

        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private void generateSimpleBlocks(List<CompletableFuture<?>> writes, CachedOutput cache) {
        simpleCube(writes, cache, "compressed_obsidian");

        simpleCube(writes, cache, "ore_lava_crystal");

        simpleCube(writes, cache, "ore_lava_crystal_stone");

        simpleCube(writes, cache, "ore_lava_crystal_obsidian");

        simpleCube(writes, cache, "block_lava_crystal");

        simpleCube(writes, cache, "block_infused_lava_crystal");

        simpleCube(writes, cache, "compressed_lava_crystal");

        simpleCube(writes, cache, "compressed_infused_lava_crystal");

        simpleCube(writes, cache, "lava_infused_obsidian");

        simpleCube(writes, cache, "ore_frost_crystal");

        simpleCube(writes, cache, "ore_frost_crystal_stone");

        simpleCube(writes, cache, "ore_frost_crystal_obsidian");

        simpleCube(writes, cache, "block_frost_crystal");

        simpleCube(writes, cache, "block_infused_frost_crystal");

        simpleCube(writes, cache, "petrified_souls");

        soulBox(writes, cache);
    }

    private void generateSnowBrick(List<CompletableFuture<?>> writes, CachedOutput cache) {
        simpleCube(writes, cache, "snow_brick");

        stairs(writes, cache, "snow_brick_stairs", "snow_brick");

        slab(writes, cache, "snow_brick_slab", "snow_brick");
    }

    private void generateStoneBrickFamily(List<CompletableFuture<?>> writes, CachedOutput cache, String color) {
        String base = color + "_stone_brick";

        simpleCube(writes, cache, base);

        corner(writes, cache, base + "_corner", base);

        tower(writes, cache, base + "_tower", base);

        wall(writes, cache, base + "_wall", base);

        stairs(writes, cache, base + "_stairs", base);

        slab(writes, cache, base + "_slab", base);
    }

    private void generateCastleFamily(List<CompletableFuture<?>> writes, CachedOutput cache, String color) {
        String base = color + "_castle_block";

        simpleCube(writes, cache, base);

        corner(writes, cache, base + "_corner", base);

        tower(writes, cache, base + "_tower", base);

        wall(writes, cache, base + "_wall", base);

        stairs(writes, cache, base + "_stairs", base);

        slab(writes, cache, base + "_slab", base);
    }

    private void simpleCube(List<CompletableFuture<?>> writes, CachedOutput cache, String name) {
        JsonObject model = new JsonObject();

        model.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();

        textures.addProperty("all", MODID + ":block/" + name);

        model.add("textures", textures);

        saveModel(writes, cache, name, model);

        simpleBlockstate(writes, cache, name, MODID + ":block/" + name);
    }

    private void corner(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String texture) {
        JsonObject model = new JsonObject();

        model.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();

        textures.addProperty("particle", MODID + ":block/" + texture);

        textures.addProperty("0", MODID + ":block/" + texture);

        textures.addProperty("all", MODID + ":block/" + texture);

        model.add("textures", textures);

        JsonArray elements = new JsonArray();

        elements.add(element(0, 0, 0, 16, 8, 16, 0, 8, 16, 16));

        elements.add(element(0, 8, 0, 8, 16, 8, 0, 0, 8, 8));

        model.add("elements", elements);

        saveModel(writes, cache, name, model);

        JsonObject state = new JsonObject();
        JsonObject variants = new JsonObject();

        variants.add("facing=east", model(MODID + ":block/" + name));

        variants.add("facing=south", rotatedModel(MODID + ":block/" + name, 90, true));

        variants.add("facing=west", rotatedModel(MODID + ":block/" + name, 180, true));

        variants.add("facing=north", rotatedModel(MODID + ":block/" + name, 270, true));

        state.add("variants", variants);

        saveBlockstate(writes, cache, name, state);
    }

    private JsonObject element(int fromX, int fromY, int fromZ, int toX, int toY, int toZ, int uvX1, int uvY1, int uvX2, int uvY2) {
        JsonObject element = new JsonObject();

        JsonArray from = new JsonArray();
        from.add(fromX);
        from.add(fromY);
        from.add(fromZ);

        JsonArray to = new JsonArray();
        to.add(toX);
        to.add(toY);
        to.add(toZ);

        element.add("from", from);
        element.add("to", to);

        JsonObject faces = new JsonObject();

        faces.add("north", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("east", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("south", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("west", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("up", face(0, 0, toX - fromX, toZ - fromZ));

        faces.add("down", face(0, 0, toX - fromX, toZ - fromZ));

        element.add("faces", faces);

        return element;
    }

    private JsonObject face(int x1, int y1, int x2, int y2) {
        JsonObject face = new JsonObject();

        JsonArray uv = new JsonArray();

        uv.add(x1);
        uv.add(y1);
        uv.add(x2);
        uv.add(y2);

        face.add("uv", uv);

        face.addProperty("texture", "#0");

        return face;
    }

    private void tower(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String texture) {
        JsonObject model = new JsonObject();

        model.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();

        textures.addProperty("0", MODID + ":block/" + texture);

        textures.addProperty("particle", MODID + ":block/" + texture);

        textures.addProperty("all", MODID + ":block/" + texture);

        model.add("textures", textures);

        JsonArray elements = new JsonArray();

        elements.add(towerElement(0, 0, 0, 16, 13, 16, 0, 3, 16, 16));

        elements.add(towerElement(0, 13, 0, 4, 16, 4, 0, 0, 4, 3));

        elements.add(towerElement(12, 13, 0, 16, 16, 4, 12, 0, 16, 3));

        elements.add(towerElement(0, 13, 12, 4, 16, 16, 0, 0, 4, 3));

        elements.add(towerElement(12, 13, 12, 16, 16, 16, 12, 0, 16, 3));

        model.add("elements", elements);

        saveModel(writes, cache, name, model);

        simpleBlockstate(writes, cache, name, MODID + ":block/" + name);
    }

    private JsonObject towerElement(int fromX, int fromY, int fromZ, int toX, int toY, int toZ, int uvX1, int uvY1, int uvX2, int uvY2) {
        JsonObject element = new JsonObject();

        JsonArray from = new JsonArray();
        from.add(fromX);
        from.add(fromY);
        from.add(fromZ);

        JsonArray to = new JsonArray();
        to.add(toX);
        to.add(toY);
        to.add(toZ);

        element.add("from", from);
        element.add("to", to);

        JsonObject faces = new JsonObject();

        faces.add("north", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("east", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("south", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("west", face(uvX1, uvY1, uvX2, uvY2));

        faces.add("up", face(fromX, fromZ, toX, toZ));

        faces.add("down", face(fromX, fromZ, toX, toZ));

        element.add("faces", faces);

        return element;
    }

    private void slab(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String texture) {
        JsonObject bottom = new JsonObject();

        bottom.addProperty("parent", "minecraft:block/slab");

        bottom.add("textures", slabTextures(texture));

        saveModel(writes, cache, name, bottom);

        JsonObject top = new JsonObject();

        top.addProperty("parent", "minecraft:block/slab_top");

        top.add("textures", slabTextures(texture));

        saveModel(writes, cache, name + "_top", top);

        JsonObject state = new JsonObject();

        JsonObject variants = new JsonObject();

        variants.add("type=bottom", model(MODID + ":block/" + name));

        variants.add("type=top", model(MODID + ":block/" + name + "_top"));

        variants.add("type=double", model(MODID + ":block/" + texture));

        state.add("variants", variants);

        saveBlockstate(writes, cache, name, state);
    }

    private JsonObject slabTextures(String texture) {
        JsonObject textures = new JsonObject();

        String location = MODID + ":block/" + texture;

        textures.addProperty("bottom", location);

        textures.addProperty("top", location);

        textures.addProperty("side", location);

        return textures;
    }

    private void stairs(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String texture) {
        saveStairModel(writes, cache, name, "minecraft:block/stairs", texture);

        saveStairModel(writes, cache, name + "_inner", "minecraft:block/inner_stairs", texture);

        saveStairModel(writes, cache, name + "_outer", "minecraft:block/outer_stairs", texture);

        JsonObject state = new JsonObject();

        JsonObject variants = new JsonObject();

        addStairVariants(variants, name, "east", 0);

        addStairVariants(variants, name, "south", 90);

        addStairVariants(variants, name, "west", 180);

        addStairVariants(variants, name, "north", 270);

        state.add("variants", variants);

        saveBlockstate(writes, cache, name, state);
    }

    private void saveStairModel(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String parent, String texture) {
        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        JsonObject textures = new JsonObject();

        String location = MODID + ":block/" + texture;

        textures.addProperty("bottom", location);

        textures.addProperty("top", location);

        textures.addProperty("side", location);

        model.add("textures", textures);

        saveModel(writes, cache, name, model);
    }

    private void addStairVariants(JsonObject variants, String name, String facing, int rotation) {
        stairVariant(variants, name, facing, "bottom", "straight", rotation, name);

        stairVariant(variants, name, facing, "bottom", "inner_left", rotation + 270, name + "_inner");

        stairVariant(variants, name, facing, "bottom", "inner_right", rotation, name + "_inner");

        stairVariant(variants, name, facing, "bottom", "outer_left", rotation + 270, name + "_outer");

        stairVariant(variants, name, facing, "bottom", "outer_right", rotation, name + "_outer");

        stairVariant(variants, name, facing, "top", "straight", rotation, name);

        stairVariant(variants, name, facing, "top", "inner_left", rotation, name + "_inner");

        stairVariant(variants, name, facing, "top", "inner_right", rotation + 90, name + "_inner");

        stairVariant(variants, name, facing, "top", "outer_left", rotation, name + "_outer");

        stairVariant(variants, name, facing, "top", "outer_right", rotation + 90, name + "_outer");
    }

    private void stairVariant(JsonObject variants, String block, String facing, String half, String shape, int rotation, String modelName) {
        String key = "facing=" + facing + ",half=" + half + ",shape=" + shape;

        JsonObject variant = model(MODID + ":block/" + modelName);

        int normalizedRotation = ((rotation % 360) + 360) % 360;

        if (normalizedRotation != 0) {
            variant.addProperty("y", normalizedRotation);
        }

        if (half.equals("top")) {
            variant.addProperty("x", 180);
        }

        variant.addProperty("uvlock", true);

        variants.add(key, variant);
    }

    private void wall(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String texture) {
        JsonObject post = wallModel("minecraft:block/template_wall_post", texture);

        JsonObject side = wallModel("minecraft:block/template_wall_side", texture);

        JsonObject sideTall = wallModel("minecraft:block/template_wall_side_tall", texture);

        JsonObject inventory = wallModel("minecraft:block/wall_inventory", texture);

        saveModel(writes, cache, name + "_post", post);

        saveModel(writes, cache, name + "_side", side);

        saveModel(writes, cache, name + "_side_tall", sideTall);

        saveModel(writes, cache, name + "_inventory", inventory);

        JsonObject state = new JsonObject();

        JsonArray multipart = new JsonArray();

        JsonObject postPart = new JsonObject();

        JsonObject postWhen = new JsonObject();

        postWhen.addProperty("up", "true");

        postPart.add("when", postWhen);

        postPart.add("apply", model(MODID + ":block/" + name + "_post"));

        multipart.add(postPart);

        wallSide(multipart, name, "north", 0);

        wallSide(multipart, name, "east", 90);

        wallSide(multipart, name, "south", 180);

        wallSide(multipart, name, "west", 270);

        state.add("multipart", multipart);

        saveBlockstate(writes, cache, name, state);
    }

    private JsonObject wallModel(String parent, String texture) {
        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        JsonObject textures = new JsonObject();

        textures.addProperty("wall", MODID + ":block/" + texture);

        model.add("textures", textures);

        return model;
    }

    private void wallSide(JsonArray multipart, String name, String direction, int rotation) {
        JsonObject low = new JsonObject();

        JsonObject lowWhen = new JsonObject();

        lowWhen.addProperty(direction, "low");

        low.add("when", lowWhen);

        JsonObject lowModel = model(MODID + ":block/" + name + "_side");

        if (rotation != 0) {
            lowModel.addProperty("y", rotation);
        }

        lowModel.addProperty("uvlock", true);

        low.add("apply", lowModel);

        multipart.add(low);


        JsonObject tall = new JsonObject();

        JsonObject tallWhen = new JsonObject();

        tallWhen.addProperty(direction, "tall");

        tall.add("when", tallWhen);

        JsonObject tallModel = model(MODID + ":block/" + name + "_side_tall");

        if (rotation != 0) {
            tallModel.addProperty("y", rotation);
        }

        tallModel.addProperty("uvlock", true);

        tall.add("apply", tallModel);

        multipart.add(tall);
    }

    private void simpleBlockstate(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String modelName) {
        JsonObject state = new JsonObject();

        JsonObject variants = new JsonObject();

        variants.add("", model(modelName));

        state.add("variants", variants);

        saveBlockstate(writes, cache, name, state);
    }

    private void soulBox(List<CompletableFuture<?>> writes, CachedOutput cache) {
        JsonObject model = new JsonObject();

        JsonArray textureSize = new JsonArray();
        textureSize.add(64);
        textureSize.add(64);
        model.add("texture_size", textureSize);

        JsonObject textures = new JsonObject();
        textures.addProperty("particle", MODID + ":block/soul_box");
        textures.addProperty("1", MODID + ":block/soul_box");
        model.add("textures", textures);

        JsonArray elements = new JsonArray();

        elements.add(soulBoxElement(0, 0, 0, 16, 5, 16, soulBoxFace(8, 3, 12, 4.25), soulBoxFace(8, 5.5, 12, 6.75), soulBoxFace(8, 6.75, 12, 8), soulBoxFace(8, 4.25, 12, 5.5), soulBoxFace(4, 4, 0, 0), soulBoxFace(4, 4, 0, 8)));

        elements.add(soulBoxElement(0, 5, 13, 3, 13, 16, soulBoxFace(7.5, 9, 8.25, 11), soulBoxFace(8.25, 9, 9, 11), soulBoxFace(6, 9, 6.75, 11), soulBoxFace(6.75, 9, 7.5, 11), soulBoxFace(13.5, 6, 12.75, 5.25), soulBoxFace(12.75, 5.25, 12, 6)));

        elements.add(soulBoxElement(13, 5, 13, 16, 13, 16, soulBoxFace(11.25, 9, 12, 11), soulBoxFace(9, 9, 9.75, 11), soulBoxFace(9.75, 9, 10.5, 11), soulBoxFace(10.5, 9, 11.25, 11), soulBoxFace(13.5, 4.75, 12.75, 4), soulBoxFace(13.5, 5.25, 12.75, 6)));

        elements.add(soulBoxElement(13, 5, 0, 16, 13, 3, soulBoxFace(4.5, 9, 5.25, 11), soulBoxFace(5.25, 9, 6, 11), soulBoxFace(3, 9, 3.75, 11), soulBoxFace(3.75, 9, 4.5, 11), soulBoxFace(12.75, 4.75, 12, 4), soulBoxFace(13.5, 4, 12.75, 4.75)));

        elements.add(soulBoxElement(0, 5, 0, 3, 13, 3, soulBoxFace(0.75, 9, 1.5, 11), soulBoxFace(1.5, 9, 2.25, 11), soulBoxFace(2.25, 9, 3, 11), soulBoxFace(0, 9, 0.75, 11), soulBoxFace(12.75, 6, 12, 5.25), soulBoxFace(12.75, 4, 12, 4.75)));

        elements.add(soulBoxElement(0, 13, 0, 16, 14, 16, soulBoxFace(4, 8.75, 8, 9), soulBoxFace(4, 8.25, 8, 8.5), soulBoxFace(4, 8, 8, 8.25), soulBoxFace(4, 8.5, 8, 8.75), soulBoxFace(8, 4, 4, 0), soulBoxFace(8, 4, 4, 8)));

        elements.add(soulBoxElement(2, 14, 2, 14, 15, 14, soulBoxFace(13, 3, 16, 3.25), soulBoxFace(13, 3.25, 16, 3.5), soulBoxFace(13, 3.75, 16, 4), soulBoxFace(13, 3.25, 16, 3.5), soulBoxFace(16, 3, 13, 0), soulBoxFace(11, 0, 8, 3)));

        elements.add(soulBoxElement(4, 15, 4, 12, 16, 12, soulBoxFace(11, 0, 13, 0.25), soulBoxFace(11, 0.5, 13, 0.75), soulBoxFace(11, 0.75, 13, 1), soulBoxFace(11, 0.25, 13, 0.5), soulBoxFace(15.5, 6, 13.5, 4), soulBoxFace(15.5, 0.5, 13.5, 2.5)));

        model.add("elements", elements);

        JsonObject display = new JsonObject();

        display.add("thirdperson_righthand", displayTransform(75, 45, 0, 0, 2.5, 0, 0.375, 0.375, 0.375));

        display.add("thirdperson_lefthand", displayTransform(75, 45, 0, 0, 2.5, 0, 0.375, 0.375, 0.375));

        display.add("head", displayTransform(0, 0, 0, 0, 13, 0, 1, 1, 1));

        display.add("ground", displayTransform(0, 0, 0, 0, 3, 0, 0.25, 0.25, 0.25));

        display.add("fixed", displayTransform(0, 0, 0, 0, 0, 0, 0.5, 0.5, 0.5));

        display.add("firstperson_righthand", displayTransform(0, 45, 0, 0, 0, 0, 0.4, 0.4, 0.4));

        display.add("firstperson_lefthand", displayTransform(0, 45, 0, 0, 0, 0, 0.4, 0.4, 0.4));

        display.add("gui", displayTransform(30, 225, 0, 0, 0, 0, 0.625, 0.625, 0.625));

        model.add("display", display);

        saveModel(writes, cache, "soul_box", model);

        simpleBlockstate(writes, cache, "soul_box", MODID + ":block/soul_box");
    }

    private JsonObject soulBoxElement(double fromX, double fromY, double fromZ, double toX, double toY, double toZ, JsonObject north, JsonObject east, JsonObject south, JsonObject west, JsonObject up, JsonObject down) {
        JsonObject element = new JsonObject();

        JsonArray from = new JsonArray();
        from.add(fromX);
        from.add(fromY);
        from.add(fromZ);
        element.add("from", from);

        JsonArray to = new JsonArray();
        to.add(toX);
        to.add(toY);
        to.add(toZ);
        element.add("to", to);

        JsonObject faces = new JsonObject();
        faces.add("north", north);
        faces.add("east", east);
        faces.add("south", south);
        faces.add("west", west);
        faces.add("up", up);
        faces.add("down", down);

        element.add("faces", faces);

        return element;
    }

    private JsonObject soulBoxFace(double x1, double y1, double x2, double y2) {
        JsonObject face = new JsonObject();

        JsonArray uv = new JsonArray();
        uv.add(x1);
        uv.add(y1);
        uv.add(x2);
        uv.add(y2);

        face.add("uv", uv);
        face.addProperty("texture", "#1");

        return face;
    }

    private JsonObject displayTransform(double rotationX, double rotationY, double rotationZ, double translationX, double translationY, double translationZ, double scaleX, double scaleY, double scaleZ) {
        JsonObject transform = new JsonObject();

        JsonArray rotation = new JsonArray();
        rotation.add(rotationX);
        rotation.add(rotationY);
        rotation.add(rotationZ);
        transform.add("rotation", rotation);

        JsonArray translation = new JsonArray();
        translation.add(translationX);
        translation.add(translationY);
        translation.add(translationZ);
        transform.add("translation", translation);

        JsonArray scale = new JsonArray();
        scale.add(scaleX);
        scale.add(scaleY);
        scale.add(scaleZ);
        transform.add("scale", scale);

        return transform;
    }

    private JsonObject model(String model) {
        JsonObject json = new JsonObject();

        json.addProperty("model", model);

        return json;
    }

    private JsonObject rotatedModel(String model, int y, boolean uvlock) {
        JsonObject json = model(model);

        json.addProperty("y", y);

        if (uvlock) {
            json.addProperty("uvlock", true);
        }

        return json;
    }

    private void saveModel(List<CompletableFuture<?>> writes, CachedOutput cache, String name, JsonObject json) {
        writes.add(DataProvider.saveStable(cache, json, modelRoot.resolve(name + ".json")));
    }

    private void saveBlockstate(List<CompletableFuture<?>> writes, CachedOutput cache, String name, JsonObject json) {
        writes.add(DataProvider.saveStable(cache, json, blockstateRoot.resolve(name + ".json")));
    }

    @Override
    public String getName() {
        return "ArmorPlus Block Models and Blockstates";
    }
}