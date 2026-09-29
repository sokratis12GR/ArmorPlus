package com.sofodev.armorplus.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModItems.*;


public final class ModItemModelProvider implements DataProvider {
    private final Path modelsRoot;
    private final Path itemsRoot;

    public ModItemModelProvider(PackOutput output) {
        Path assets = output.getOutputFolder().resolve("assets").resolve(MODID);

        this.modelsRoot = assets.resolve("models").resolve("item");

        this.itemsRoot = assets.resolve("items");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        java.util.List<CompletableFuture<?>> writes = new java.util.ArrayList<>();

        Set<DeferredHolder<Item, ? extends Item>> flatItems = new LinkedHashSet<>();

        writes.add(DataProvider.saveStable(cache, handheldSword(), modelsRoot.resolve("handheld_sword.json")));

        writes.add(DataProvider.saveStable(cache, handheldBattleAxe(), modelsRoot.resolve("handheld_battle_axe.json")));

        add(flatItems, THANK_YOU);

        add(flatItems, CHAINMAIL, ENHANCED_CHAINMAIL, ENHANCED_IRON, ENHANCED_GOLD, ENHANCED_DIAMOND, ENHANCED_NETHERITE);

        add(flatItems, GUARDIAN_SOUL, ELDER_GUARDIAN_SOUL, GUARDIAN_SCALE, GUARDIAN_BOW_BASE, GUARDIAN_SWORD_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_BATTLE_AXE_BASE);

        add(flatItems, WITHER_BOSS_SOUL, WITHER_SKELETON_SOUL, WITHER_BONE, SUPER_STAR_BOW_BASE, SUPER_STAR_SWORD_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_BATTLE_AXE_BASE);

        add(flatItems, ENDER_DRAGON_SOUL, ENDERMAN_SOUL, ENDER_DRAGON_SCALE, ENDER_DRAGON_BOW_BASE, ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_BATTLE_AXE_BASE);

        add(flatItems, LAVA_SHARD, LAVA_CRYSTAL, INFUSED_LAVA_CRYSTAL);

        add(flatItems, FROST_SHARD, FROST_CRYSTAL, INFUSED_FROST_CRYSTAL);

        add(flatItems, INFUSED_FROST_LAVA_CRYSTAL, THE_ULTIMATE_MATERIAL);

        add(flatItems, SLAYER_SOUL, SLAYER_BOW_BASE, SLAYER_SWORD_BASE, SLAYER_PICKAXE_BASE, SLAYER_BATTLE_AXE_BASE);

        add(flatItems, BLAZE_SOUL, OBSIDIAN_STICK, WOODEN_ROD);

        add(flatItems, ITEM_COAL_ARROW, ITEM_LAPIS_ARROW, ITEM_REDSTONE_ARROW, ITEM_EMERALD_ARROW, ITEM_OBSIDIAN_ARROW, ITEM_INFUSED_LAVA_ARROW, ITEM_GUARDIAN_ARROW, ITEM_SUPER_STAR_ARROW, ITEM_ENDER_DRAGON_ARROW);

        java.util.Collections.addAll(flatItems, PICKAXES);

        flatItems.addAll(GUARDIAN_BASES);

        flatItems.addAll(SUPER_STAR_BASES);

        flatItems.addAll(ENDER_DRAGON_BASES);

        flatItems.addAll(SLAYER_BASES);

        for (DeferredHolder<Item, ? extends Item> item : flatItems) {
            writes.addAll(writeTexturedItem(cache, item, "minecraft:item/generated", materialTexture(item)));
        }

        for (DeferredHolder<Item, Item> sword : SWORDS) {
            writes.addAll(writeTexturedItem(cache, sword, MODID + ":item/handheld_sword", MODID + ":item/" + sword.getId().getPath()));
        }

        for (DeferredHolder<Item, Item> battleAxe : BATTLE_AXES) {
            writes.addAll(writeTexturedItem(cache, battleAxe, MODID + ":item/handheld_battle_axe", MODID + ":item/" + battleAxe.getId().getPath()));
        }

        for (DeferredHolder<Item, Item> mace : MACES) {
            writes.addAll(writeParentOnlyItem(cache, mace, MODID + ":item/mace.item"));
        }

        for (DeferredHolder<Item, ? extends com.sofodev.armorplus.registry.item.armor.APArmorItem> armor : allArmor()) {
            writes.addAll(writeTrimmedArmor(cache, armor));
        }

        for (DeferredHolder<Item, Item> bow : BOWS) {
            writes.addAll(writeBow(cache, bow));
        }

        for (DeferredHolder<Block, ? extends Block> block : ModBlocks.BLOCKS.getEntries()) {
            writes.addAll(writeBlockItem(cache, block));
        }

        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private static final String[] TRIM_MATERIALS = {"quartz", "iron", "netherite", "redstone", "copper", "gold", "emerald", "diamond", "lapis", "amethyst"};

    private Set<DeferredHolder<Item, ? extends com.sofodev.armorplus.registry.item.armor.APArmorItem>> allArmor() {
        Set<DeferredHolder<Item, ? extends com.sofodev.armorplus.registry.item.armor.APArmorItem>> result = new LinkedHashSet<>();

        result.addAll(HELMETS);

        result.addAll(CHESTPLATES);

        result.addAll(LEGGINGS);

        result.addAll(BOOTS);

        return result;
    }

    private String materialTexture(DeferredHolder<Item, ? extends Item> item) {
        if (item == CHAINMAIL || item == ENHANCED_CHAINMAIL) {
            return MODID + ":item/chainmail";
        }

        if (item == ENHANCED_IRON) {
            return "minecraft:item/iron_ingot";
        }

        if (item == ENHANCED_GOLD) {
            return "minecraft:item/gold_ingot";
        }

        if (item == ENHANCED_DIAMOND) {
            return "minecraft:item/diamond";
        }

        if (item == ENHANCED_NETHERITE) {
            return "minecraft:item/netherite_ingot";
        }

        return MODID + ":item/" + item.getId().getPath();
    }

    private java.util.List<CompletableFuture<?>> writeTrimmedArmor(CachedOutput cache, DeferredHolder<Item, ? extends Item> armor) {
        String path = armor.getId().getPath();

        String slot = path.substring(path.lastIndexOf('_') + 1);

        java.util.List<CompletableFuture<?>> writes = new java.util.ArrayList<>();

        String baseTexture = armorBaseTexture(path);

        JsonObject base = texturedModel("minecraft:item/generated", baseTexture);

        writes.add(DataProvider.saveStable(cache, base, modelsRoot.resolve(path + ".json")));

        JsonArray cases = new JsonArray();

        for (String trim : TRIM_MATERIALS) {
            String variant = path + "_" + trim + "_trim";

            JsonObject textures = new JsonObject();

            textures.addProperty("layer0", baseTexture);

            textures.addProperty("layer1", "minecraft:trims/items/" + slot + "_trim_" + trim);

            JsonObject model = new JsonObject();

            model.addProperty("parent", "minecraft:item/generated");

            model.add("textures", textures);

            writes.add(DataProvider.saveStable(cache, model, modelsRoot.resolve(variant + ".json")));

            JsonObject entry = new JsonObject();

            entry.addProperty("when", "minecraft:" + trim);

            entry.add("model", modelRef(variant));

            cases.add(entry);
        }

        JsonObject select = new JsonObject();

        select.addProperty("type", "minecraft:select");

        select.addProperty("property", "minecraft:trim_material");

        select.add("cases", cases);

        select.add("fallback", modelRef(path));

        JsonObject clientItem = new JsonObject();

        clientItem.add("model", select);

        writes.add(DataProvider.saveStable(cache, clientItem, itemsRoot.resolve(path + ".json")));

        return writes;
    }

    private String armorBaseTexture(String path) {
        if (path.startsWith("chainmail_") || path.startsWith("golden_") || path.startsWith("iron_") || path.startsWith("diamond_") || path.startsWith("netherite_")) {
            return "minecraft:item/" + path;
        }

        return MODID + ":item/" + path;
    }

    private java.util.List<CompletableFuture<?>> writeBow(CachedOutput cache, DeferredHolder<Item, Item> bow) {
        String path = bow.getId().getPath();

        java.util.List<CompletableFuture<?>> writes = new java.util.ArrayList<>();

        JsonObject base = texturedModel("minecraft:item/bow", MODID + ":item/" + path);

        writes.add(DataProvider.saveStable(cache, base, modelsRoot.resolve(path + ".json")));

        for (int i = 0; i < 3; i++) {
            String variant = path + "_pulling_" + i;

            JsonObject model = texturedModel("minecraft:item/bow", MODID + ":item/" + variant);

            writes.add(DataProvider.saveStable(cache, model, modelsRoot.resolve(variant + ".json")));
        }

        JsonObject range = new JsonObject();

        range.addProperty("type", "minecraft:range_dispatch");

        range.addProperty("property", "minecraft:use_duration");

        range.addProperty("scale", 0.05F);

        range.add("fallback", modelRef(path + "_pulling_0"));

        JsonArray entries = new JsonArray();

        entries.add(rangeEntry(0.65F, path + "_pulling_1"));

        entries.add(rangeEntry(0.9F, path + "_pulling_2"));

        range.add("entries", entries);

        JsonObject condition = new JsonObject();

        condition.addProperty("type", "minecraft:condition");

        condition.addProperty("property", "minecraft:using_item");

        condition.add("on_true", range);

        condition.add("on_false", modelRef(path));

        JsonObject clientItem = new JsonObject();

        clientItem.add("model", condition);

        writes.add(DataProvider.saveStable(cache, clientItem, itemsRoot.resolve(path + ".json")));

        return writes;
    }

    private JsonObject texturedModel(String parent, String layer0) {
        JsonObject textures = new JsonObject();

        textures.addProperty("layer0", layer0);

        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        model.add("textures", textures);

        return model;
    }

    private JsonObject modelRef(String path) {
        JsonObject ref = new JsonObject();

        ref.addProperty("type", "minecraft:model");

        ref.addProperty("model", MODID + ":item/" + path);

        return ref;
    }

    private JsonObject rangeEntry(float threshold, String path) {
        JsonObject entry = new JsonObject();

        entry.addProperty("threshold", threshold);

        entry.add("model", modelRef(path));

        return entry;
    }

    private java.util.List<CompletableFuture<?>> writeTexturedItem(CachedOutput cache, DeferredHolder<Item, ? extends Item> item, String parent, String texture) {
        String path = item.getId().getPath();

        JsonObject textures = new JsonObject();

        textures.addProperty("layer0", texture);

        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        model.add("textures", textures);

        return writeModelAndClientItem(cache, path, model);
    }

    private java.util.List<CompletableFuture<?>> writeTexturedItem(CachedOutput cache, DeferredHolder<Item, ? extends Item> item, String parent) {
        return writeTexturedItem(cache, item, parent, MODID + ":item/" + item.getId().getPath());
    }

    private java.util.List<CompletableFuture<?>> writeParentOnlyItem(CachedOutput cache, DeferredHolder<Item, ? extends Item> item, String parent) {
        String path = item.getId().getPath();

        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        return writeModelAndClientItem(cache, path, model);
    }

    private java.util.List<CompletableFuture<?>> writeBlockItem(CachedOutput cache, DeferredHolder<Block, ? extends Block> block) {
        String path = block.getId().getPath();

        JsonObject model = new JsonObject();

        model.addProperty("parent", MODID + ":block/" + (path.endsWith("_wall") ? path + "_inventory" : path));

        return writeModelAndClientItem(cache, path, model);
    }

    private java.util.List<CompletableFuture<?>> writeModelAndClientItem(CachedOutput cache, String path, JsonObject model) {
        JsonObject modelRef = new JsonObject();

        modelRef.addProperty("type", "minecraft:model");

        modelRef.addProperty("model", MODID + ":item/" + path);

        JsonObject clientItem = new JsonObject();

        clientItem.add("model", modelRef);

        return java.util.List.of(DataProvider.saveStable(cache, model, modelsRoot.resolve(path + ".json")), DataProvider.saveStable(cache, clientItem, itemsRoot.resolve(path + ".json")));
    }

    private JsonObject handheldSword() {
        JsonObject json = new JsonObject();

        json.addProperty("parent", "minecraft:item/handheld");

        JsonObject display = new JsonObject();

        display.add("thirdperson_righthand", transform(0, -90, 55, 0, 6.0, 0.5, 1.1, 1.1, 1.1));

        display.add("thirdperson_lefthand", transform(0, 90, -55, 0, 6.0, 0.5, 1.1, 1.1, 1.1));

        display.add("firstperson_righthand", transform(0, -90, 25, 1.13, 4.0, 1.13, 0.9, 0.9, 0.9));

        display.add("firstperson_lefthand", transform(0, 90, -25, 1.13, 4.0, 1.13, 0.9, 0.9, 0.9));

        json.add("display", display);

        return json;
    }

    private JsonObject handheldBattleAxe() {
        JsonObject json = new JsonObject();

        json.addProperty("parent", "minecraft:item/handheld");

        JsonObject display = new JsonObject();

        display.add("thirdperson_righthand", transform(0, -90, 55, 0, 8.0, 0.5, 1.5, 1.5, 1.5));

        display.add("thirdperson_lefthand", transform(0, 90, -55, 0, 8.0, 0.5, 1.5, 1.5, 1.5));

        display.add("firstperson_righthand", transform(0, -90, 25, 1.13, 3.2, 1.13, 0.9, 0.9, 0.9));

        display.add("firstperson_lefthand", transform(0, 90, -25, 1.13, 3.2, 1.13, 0.9, 0.9, 0.9));

        json.add("display", display);

        return json;
    }

    private JsonObject transform(double rotationX, double rotationY, double rotationZ, double translationX, double translationY, double translationZ, double scaleX, double scaleY, double scaleZ) {
        JsonObject transform = new JsonObject();

        JsonArray rotation = new JsonArray();

        rotation.add(rotationX);

        rotation.add(rotationY);

        rotation.add(rotationZ);

        JsonArray translation = new JsonArray();

        translation.add(translationX);

        translation.add(translationY);

        translation.add(translationZ);

        JsonArray scale = new JsonArray();

        scale.add(scaleX);

        scale.add(scaleY);

        scale.add(scaleZ);

        transform.add("rotation", rotation);

        transform.add("translation", translation);

        transform.add("scale", scale);

        return transform;
    }

    @SafeVarargs
    private static void add(Set<DeferredHolder<Item, ? extends Item>> target, DeferredHolder<Item, ? extends Item>... values) {
        java.util.Collections.addAll(target, values);
    }

    @Override
    public String getName() {
        return "ArmorPlus Client Item Models";
    }
}