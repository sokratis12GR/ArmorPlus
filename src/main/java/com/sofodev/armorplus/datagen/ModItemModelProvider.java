package com.sofodev.armorplus.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModItems.ARDITE_INGOT;
import static com.sofodev.armorplus.registry.ModItems.ARMOR_RUNE;
import static com.sofodev.armorplus.registry.ModItems.BATTLE_AXES;
import static com.sofodev.armorplus.registry.ModItems.BLANK_RUNE;
import static com.sofodev.armorplus.registry.ModItems.ASTRAL_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.ARMOR_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.WEAPON_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.TOOL_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.BOW_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.EFFECT_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.INSTANT_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.MODDED_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.EMBER_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.EQUINOX_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.RIME_SIGIL;
import static com.sofodev.armorplus.registry.ModItems.BLAZE_SOUL;
import static com.sofodev.armorplus.registry.ModItems.BOOTS;
import static com.sofodev.armorplus.registry.ModItems.BOWS;
import static com.sofodev.armorplus.registry.ModItems.CHAINMAIL;
import static com.sofodev.armorplus.registry.ModItems.CHESTPLATES;
import static com.sofodev.armorplus.registry.ModItems.COBALT_INGOT;
import static com.sofodev.armorplus.registry.ModItems.ELDER_GUARDIAN_SOUL;
import static com.sofodev.armorplus.registry.ModItems.ENDERMAN_SOUL;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_BASES;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SCALE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SOUL;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_CHAINMAIL;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_COPPER;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_DIAMOND;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_GOLD;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_IRON;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_NETHERITE;
import static com.sofodev.armorplus.registry.ModItems.FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.FROST_SHARD;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_BASES;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_SCALE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_SOUL;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.HELMETS;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.ITEM_AMETHYST_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_COAL_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_EMERALD_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_ENDER_DRAGON_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_GLOWSTONE_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_GUARDIAN_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_HONEY_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_INFUSED_LAVA_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_LAPIS_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_OBSIDIAN_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_QUARTZ_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_REDSTONE_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_SUPER_STAR_ARROW;
import static com.sofodev.armorplus.registry.ModItems.ITEM_WARDEN_ARROW;
import static com.sofodev.armorplus.registry.ModItems.KNIGHT_SLIME_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.LAVA_SHARD;
import static com.sofodev.armorplus.registry.ModItems.LEGGINGS;
import static com.sofodev.armorplus.registry.ModItems.MACES;
import static com.sofodev.armorplus.registry.ModItems.MANYULLYN_INGOT;
import static com.sofodev.armorplus.registry.ModItems.OBSIDIAN_STICK;
import static com.sofodev.armorplus.registry.ModItems.PICKAXES;
import static com.sofodev.armorplus.registry.ModItems.PIG_IRON_INGOT;
import static com.sofodev.armorplus.registry.ModItems.RANGED_RUNE;
import static com.sofodev.armorplus.registry.ModItems.SHOVELS;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_BASES;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_SOUL;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.SOCKET_PUNCH;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_BASES;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.SWORDS;
import static com.sofodev.armorplus.registry.ModItems.THANK_YOU;
import static com.sofodev.armorplus.registry.ModItems.THE_ULTIMATE_MATERIAL;
import static com.sofodev.armorplus.registry.ModItems.TOOL_RUNE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_BASES;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_HEART;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.WEAPON_RUNE;
import static com.sofodev.armorplus.registry.ModItems.WITHER_BONE;
import static com.sofodev.armorplus.registry.ModItems.WITHER_BOSS_SOUL;
import static com.sofodev.armorplus.registry.ModItems.WITHER_SKELETON_SOUL;
import static com.sofodev.armorplus.registry.ModItems.WOODEN_ROD;


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
        List<CompletableFuture<?>> writes = new ArrayList<>();

        Set<DeferredHolder<Item, ? extends Item>> flatItems = new LinkedHashSet<>();

        collectSocketable();
        for (String state : SOCKET_STATES) {
            writes.add(DataProvider.saveStable(cache, socketOverlayModel(state), modelsRoot.resolve("socket_overlay_" + state + ".json")));
        }

        add(flatItems, BLANK_RUNE, ARMOR_RUNE, WEAPON_RUNE, TOOL_RUNE, RANGED_RUNE, SOCKET_PUNCH, EMBER_SIGIL, RIME_SIGIL, EQUINOX_SIGIL, ASTRAL_SIGIL, ARMOR_SIGIL, WEAPON_SIGIL, TOOL_SIGIL, BOW_SIGIL, EFFECT_SIGIL, INSTANT_SIGIL, MODDED_SIGIL);

        writes.add(DataProvider.saveStable(cache, handheldSword(), modelsRoot.resolve("handheld_sword.json")));

        writes.add(DataProvider.saveStable(cache, handheldBattleAxe(), modelsRoot.resolve("handheld_battle_axe.json")));

        add(flatItems, THANK_YOU);

        add(flatItems, CHAINMAIL, ENHANCED_CHAINMAIL, ENHANCED_COPPER, ENHANCED_IRON, ENHANCED_GOLD, ENHANCED_DIAMOND, ENHANCED_NETHERITE);

        add(flatItems, PIG_IRON_INGOT, COBALT_INGOT, ARDITE_INGOT, KNIGHT_SLIME_CRYSTAL, MANYULLYN_INGOT);

        add(flatItems, GUARDIAN_SOUL, ELDER_GUARDIAN_SOUL, GUARDIAN_SCALE, GUARDIAN_BOW_BASE, GUARDIAN_SWORD_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_SHOVEL_BASE, GUARDIAN_BATTLE_AXE_BASE);

        add(flatItems, WITHER_BOSS_SOUL, WITHER_SKELETON_SOUL, WITHER_BONE, SUPER_STAR_BOW_BASE, SUPER_STAR_SWORD_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_SHOVEL_BASE, SUPER_STAR_BATTLE_AXE_BASE);

        add(flatItems, ENDER_DRAGON_SOUL, ENDERMAN_SOUL, ENDER_DRAGON_SCALE, ENDER_DRAGON_BOW_BASE, ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_SHOVEL_BASE, ENDER_DRAGON_BATTLE_AXE_BASE);

        add(flatItems, WARDEN_HEART, WARDEN_BOW_BASE, WARDEN_SWORD_BASE, WARDEN_PICKAXE_BASE, WARDEN_SHOVEL_BASE, WARDEN_BATTLE_AXE_BASE);

        add(flatItems, LAVA_SHARD, LAVA_CRYSTAL, INFUSED_LAVA_CRYSTAL);

        add(flatItems, FROST_SHARD, FROST_CRYSTAL, INFUSED_FROST_CRYSTAL);

        add(flatItems, INFUSED_FROST_LAVA_CRYSTAL, THE_ULTIMATE_MATERIAL);

        add(flatItems, SLAYER_SOUL, SLAYER_BOW_BASE, SLAYER_SWORD_BASE, SLAYER_PICKAXE_BASE, SLAYER_SHOVEL_BASE, SLAYER_BATTLE_AXE_BASE);

        add(flatItems, BLAZE_SOUL, OBSIDIAN_STICK, WOODEN_ROD);

        add(flatItems, ITEM_COAL_ARROW, ITEM_LAPIS_ARROW, ITEM_REDSTONE_ARROW, ITEM_EMERALD_ARROW, ITEM_OBSIDIAN_ARROW, ITEM_INFUSED_LAVA_ARROW, ITEM_GUARDIAN_ARROW, ITEM_SUPER_STAR_ARROW, ITEM_ENDER_DRAGON_ARROW, ITEM_WARDEN_ARROW, ITEM_AMETHYST_ARROW, ITEM_QUARTZ_ARROW, ITEM_GLOWSTONE_ARROW, ITEM_HONEY_ARROW);

        Collections.addAll(flatItems, PICKAXES);

        flatItems.addAll(GUARDIAN_BASES);

        flatItems.addAll(SUPER_STAR_BASES);

        flatItems.addAll(ENDER_DRAGON_BASES);

        flatItems.addAll(WARDEN_BASES);

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

        for (DeferredHolder<Item, Item> shovel : SHOVELS) {
            writes.addAll(writeTexturedItem(cache, shovel, "minecraft:item/handheld", MODID + ":item/" + shovel.getId().getPath()));
        }

        for (DeferredHolder<Item, Item> mace : MACES) {
            writes.addAll(writeGeckoLibMace(cache, mace));
        }

        for (DeferredHolder<Item, ? extends APArmorItem> armor : allArmor()) {
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

    /** Values of the "armorplus:socket_state" item model property that show an overlay, see SocketStateProperty. */
    private static final String[] SOCKET_STATES = {"empty", "partial", "full"};

    /** Paths of the equipment that can hold runes, their client items get the socket overlay. */
    private final Set<String> socketable = new HashSet<>();

    private void collectSocketable() {
        for (DeferredHolder<Item, ? extends Item> armor : allArmor()) socketable.add(armor.getId().getPath());
        for (DeferredHolder<Item, Item>[] tools : List.of(SWORDS, BATTLE_AXES, PICKAXES, SHOVELS, MACES, BOWS)) {
            for (DeferredHolder<Item, Item> tool : tools) socketable.add(tool.getId().getPath());
        }
    }

    /**
     * The socket symbol, a flat quad in the top left corner just in front of the item (generated item models span z 7.5 - 8.5).
     */
    private JsonObject socketOverlayModel(String state) {
        String texture = MODID + ":item/socket_" + state;

        JsonObject textures = new JsonObject();
        textures.addProperty("overlay", texture);
        textures.addProperty("particle", texture);

        JsonObject face = new JsonObject();
        JsonArray uv = new JsonArray();
        uv.add(0);
        uv.add(0);
        uv.add(6);
        uv.add(6);
        face.add("uv", uv);
        face.addProperty("texture", "#overlay");
        JsonObject faces = new JsonObject();
        faces.add("south", face);

        JsonArray from = new JsonArray();
        from.add(0);
        from.add(10);
        from.add(8.6);
        JsonArray to = new JsonArray();
        to.add(6);
        to.add(16);
        to.add(8.6);
        JsonObject element = new JsonObject();
        element.add("from", from);
        element.add("to", to);
        element.add("faces", faces);
        JsonArray elements = new JsonArray();
        elements.add(element);

        JsonObject model = new JsonObject();
        model.addProperty("gui_light", "front");
        model.add("textures", textures);
        model.add("elements", elements);
        return model;
    }

    /**
     * Wraps the client item model of socketable equipment: in GUIs the "armorplus:socket_state" property layers the matching
     * socket overlay on top of the item, everywhere else (and for state "none") the item renders as before.
     */
    private JsonObject withSocketOverlay(String path, JsonObject model) {
        if (!socketable.contains(path)) return model;

        JsonArray stateCases = new JsonArray();
        for (String state : SOCKET_STATES) {
            JsonArray models = new JsonArray();
            models.add(model.deepCopy());
            models.add(modelRef("socket_overlay_" + state));

            JsonObject composite = new JsonObject();
            composite.addProperty("type", "minecraft:composite");
            composite.add("models", models);

            JsonObject entry = new JsonObject();
            entry.addProperty("when", state);
            entry.add("model", composite);
            stateCases.add(entry);
        }

        JsonObject guiCase = new JsonObject();
        guiCase.addProperty("when", "gui");
        guiCase.add("model", select(MODID + ":socket_state", stateCases, model));
        JsonArray contextCases = new JsonArray();
        contextCases.add(guiCase);

        return select("minecraft:display_context", contextCases, model);
    }

    private JsonObject select(String property, JsonArray cases, JsonObject fallback) {
        JsonObject select = new JsonObject();
        select.addProperty("type", "minecraft:select");
        select.addProperty("property", property);
        select.add("cases", cases);
        select.add("fallback", fallback.deepCopy());
        return select;
    }

    private Set<DeferredHolder<Item, ? extends APArmorItem>> allArmor() {
        Set<DeferredHolder<Item, ? extends APArmorItem>> result = new LinkedHashSet<>();

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

        if (item == ENHANCED_COPPER) {
            return "minecraft:item/copper_ingot";
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

    private List<CompletableFuture<?>> writeTrimmedArmor(CachedOutput cache, DeferredHolder<Item, ? extends Item> armor) {
        String path = armor.getId().getPath();

        String slot = path.substring(path.lastIndexOf('_') + 1);

        List<CompletableFuture<?>> writes = new ArrayList<>();

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

        clientItem.add("model", withSocketOverlay(path, select));

        writes.add(DataProvider.saveStable(cache, clientItem, itemsRoot.resolve(path + ".json")));

        return writes;
    }

    private String armorBaseTexture(String path) {
        if (path.startsWith("chainmail_") || path.startsWith("golden_") || path.startsWith("copper_") || path.startsWith("iron_") || path.startsWith("diamond_") || path.startsWith("netherite_")) {
            return "minecraft:item/" + path;
        }

        return MODID + ":item/" + path;
    }

    private List<CompletableFuture<?>> writeBow(CachedOutput cache, DeferredHolder<Item, Item> bow) {
        String path = bow.getId().getPath();

        List<CompletableFuture<?>> writes = new ArrayList<>();

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

        clientItem.add("model", withSocketOverlay(path, condition));

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

    private List<CompletableFuture<?>> writeTexturedItem(CachedOutput cache, DeferredHolder<Item, ? extends Item> item, String parent, String texture) {
        String path = item.getId().getPath();

        JsonObject textures = new JsonObject();

        textures.addProperty("layer0", texture);

        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        model.add("textures", textures);

        return writeModelAndClientItem(cache, path, model);
    }

    private List<CompletableFuture<?>> writeTexturedItem(CachedOutput cache, DeferredHolder<Item, ? extends Item> item, String parent) {
        return writeTexturedItem(cache, item, parent, MODID + ":item/" + item.getId().getPath());
    }

    private List<CompletableFuture<?>> writeParentOnlyItem(CachedOutput cache, DeferredHolder<Item, ? extends Item> item, String parent) {
        String path = item.getId().getPath();

        JsonObject model = new JsonObject();

        model.addProperty("parent", parent);

        return writeModelAndClientItem(cache, path, model);
    }

    private List<CompletableFuture<?>> writeGeckoLibMace(CachedOutput cache, DeferredHolder<Item, Item> mace) {
        String path = mace.getId().getPath();

        JsonObject specialRenderer = new JsonObject();
        specialRenderer.addProperty("type", "geckolib:geckolib");

        JsonObject special = new JsonObject();
        special.addProperty("type", "minecraft:special");
        special.addProperty("base", MODID + ":item/" + path);
        special.add("model", specialRenderer);

        JsonObject clientItem = new JsonObject();
        clientItem.add("model", withSocketOverlay(path, special));

        JsonObject baseModel = texturedModel("minecraft:item/handheld", MODID + ":item/" + path);
        baseModel.add("display", maceDisplayTransforms());

        return List.of(DataProvider.saveStable(cache, baseModel, modelsRoot.resolve(path + ".json")), DataProvider.saveStable(cache, clientItem, itemsRoot.resolve(path + ".json")));
    }

    private JsonObject maceDisplayTransforms() {
        JsonObject display = new JsonObject();

        // Third-person
        display.add("thirdperson_righthand", transform(90, 0, 0, 0.5, 6.5, 0, 1, 1, 1));

        display.add("thirdperson_lefthand", transform(90, 0, 0, -0.5, 6.5, 0, 1, 1, 1));

        // First-person
        display.add("firstperson_righthand", transform(45, 0, 0, -3, 1, 2, 0.5, 0.5, 0.5));

        display.add("firstperson_lefthand", transform(45, 0, 0, -3, 1, 2, 0.5, 0.5, 0.5));

        // Inventory / hotbar.
        display.add("gui", transform(0, -90, 0, -0.5, -7.75, 0, 0.75, 0.75, 0.75));

        // Dropped item.
        display.add("ground", transform(0, 0, 0, 0, 0, 0, 0.5, 0.5, 0.5));

        // Worn on the head.
        display.add("head", transform(45, 0, 0, 0.5, 0, -8, 1, 1, 1));

        // Item frame.
        display.add("fixed", transform(0, 90, 0, 0.5, -10, 0, 1, 1, 1));

        return display;
    }

    private List<CompletableFuture<?>> writeBlockItem(CachedOutput cache, DeferredHolder<Block, ? extends Block> block) {
        String path = block.getId().getPath();

        JsonObject model = new JsonObject();

        model.addProperty("parent", MODID + ":block/" + (path.endsWith("_wall") ? path + "_inventory" : path));

        return writeModelAndClientItem(cache, path, model);
    }

    private List<CompletableFuture<?>> writeModelAndClientItem(CachedOutput cache, String path, JsonObject model) {
        JsonObject modelRef = new JsonObject();

        modelRef.addProperty("type", "minecraft:model");

        modelRef.addProperty("model", MODID + ":item/" + path);

        JsonObject clientItem = new JsonObject();

        clientItem.add("model", withSocketOverlay(path, modelRef));

        return List.of(DataProvider.saveStable(cache, model, modelsRoot.resolve(path + ".json")), DataProvider.saveStable(cache, clientItem, itemsRoot.resolve(path + ".json")));
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
        Collections.addAll(target, values);
    }

    @Override
    public String getName() {
        return "ArmorPlus Client Item Models";
    }
}