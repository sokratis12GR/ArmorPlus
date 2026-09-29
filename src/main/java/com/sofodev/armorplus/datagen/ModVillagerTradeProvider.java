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

public final class ModVillagerTradeProvider implements DataProvider {
    private final Path dataRoot;

    public ModVillagerTradeProvider(PackOutput output) {
        this.dataRoot = output.getOutputFolder().resolve("data").resolve(MODID);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();

        trade(writes, cache, 1, "lava_shards", "minecraft:emerald", 2, "armorplus:lava_shard", 4, 16, 2, null, 0, false, false);
        trade(writes, cache, 1, "frost_shards", "minecraft:emerald", 2, "armorplus:frost_shard", 4, 16, 2, null, 0, false, false);
        trade(writes, cache, 1, "blaze_powder", "minecraft:emerald", 1, "minecraft:blaze_powder", 4, 16, 2, null, 0, false, false);

        trade(writes, cache, 2, "lava_crystal", "minecraft:emerald", 5, "armorplus:lava_crystal", 1, 12, 5, null, 0, false, false);
        trade(writes, cache, 2, "frost_crystal", "minecraft:emerald", 5, "armorplus:frost_crystal", 1, 12, 5, null, 0, false, false);
        trade(writes, cache, 2, "blaze_rods", "minecraft:emerald", 3, "minecraft:blaze_rod", 2, 12, 5, null, 0, false, false);

        trade(writes, cache, 3, "guardian_soul", "minecraft:emerald", 8, "armorplus:soul_guardian", 1, 8, 10, null, 0, false, false);
        trade(writes, cache, 3, "wither_skeleton_soul", "minecraft:emerald", 8, "armorplus:soul_wither_skeleton", 1, 8, 10, null, 0, false, false);
        trade(writes, cache, 3, "enderman_soul", "minecraft:emerald", 8, "armorplus:soul_enderman", 1, 8, 10, null, 0, false, false);
        trade(writes, cache, 3, "blaze_soul", "minecraft:emerald", 8, "armorplus:soul_blaze", 1, 8, 10, null, 0, false, false);

        trade(writes, cache, 4, "elder_guardian_soul", "minecraft:emerald", 20, "armorplus:soul_elder_guardian", 1, 4, 15, "armorplus:soul_guardian", 4, false, false);
        trade(writes, cache, 4, "wither_boss_soul", "minecraft:emerald", 20, "armorplus:soul_wither_boss", 1, 4, 15, "armorplus:soul_wither_skeleton", 4, false, false);
        trade(writes, cache, 4, "ender_dragon_soul", "minecraft:emerald", 20, "armorplus:soul_ender_dragon", 1, 4, 15, "armorplus:soul_enderman", 4, false, false);

        trade(writes, cache, 5, "slayer_soul", "minecraft:emerald", 32, "armorplus:soul_slayer", 1, 2, 30, "armorplus:infused_frost_lava_crystal", 1, false, false);
        trade(writes, cache, 5, "soul_stealer_book", "minecraft:emerald", 24, "minecraft:enchanted_book", 1, 4, 30, null, 0, true, false);

        swordTrade(writes, cache, "wooden_sword", "minecraft:wooden_sword", 12, null, 0);
        swordTrade(writes, cache, "stone_sword", "minecraft:stone_sword", 14, null, 0);
        swordTrade(writes, cache, "golden_sword", "minecraft:golden_sword", 16, null, 0);
        swordTrade(writes, cache, "iron_sword", "minecraft:iron_sword", 20, null, 0);
        swordTrade(writes, cache, "diamond_sword", "minecraft:diamond_sword", 28, null, 0);
        swordTrade(writes, cache, "netherite_sword", "minecraft:netherite_sword", 36, null, 0);

        swordTrade(writes, cache, "coal_sword", "armorplus:coal_sword", 16, "minecraft:coal", 4);
        swordTrade(writes, cache, "redstone_sword", "armorplus:redstone_sword", 18, "minecraft:redstone", 4);
        swordTrade(writes, cache, "lapis_sword", "armorplus:lapis_sword", 18, "minecraft:lapis_lazuli", 4);
        swordTrade(writes, cache, "emerald_sword", "armorplus:emerald_sword", 24, "minecraft:emerald", 4);
        swordTrade(writes, cache, "obsidian_sword", "armorplus:obsidian_sword", 28, "minecraft:obsidian", 4);
        swordTrade(writes, cache, "infused_lava_sword", "armorplus:infused_lava_sword", 32, "armorplus:infused_lava_crystal", 2);
        swordTrade(writes, cache, "guardian_sword", "armorplus:guardian_sword", 36, "armorplus:guardian_scale", 4);
        swordTrade(writes, cache, "super_star_sword", "armorplus:super_star_sword", 36, "armorplus:wither_bone", 4);
        swordTrade(writes, cache, "ender_dragon_sword", "armorplus:ender_dragon_sword", 40, "armorplus:ender_dragon_scale", 4);
        swordTrade(writes, cache, "slayer_sword", "armorplus:slayer_sword", 48, "armorplus:the_ultimate_material", 2);

        tradeSet(writes, cache, 1, 2, "lava_shards", "frost_shards", "blaze_powder");
        tradeSet(writes, cache, 2, 2, "lava_crystal", "frost_crystal", "blaze_rods");
        tradeSet(writes, cache, 3, 2, "guardian_soul", "wither_skeleton_soul", "enderman_soul", "blaze_soul");
        tradeSet(writes, cache, 4, 2, "elder_guardian_soul", "wither_boss_soul", "ender_dragon_soul");
        levelFiveTradeSet(writes, cache);

        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private void swordTrade(List<CompletableFuture<?>> writes, CachedOutput cache, String name,
                            String sword, int emeralds, String material, int materialCount) {
        trade(writes, cache, 5, "swords/" + name, "minecraft:emerald", emeralds, sword, 1,
                2, 30, material, materialCount, true, true);
    }

    private void trade(List<CompletableFuture<?>> writes, CachedOutput cache, int level, String name,
                       String wantedItem, int wantedCount, String givenItem, int givenCount,
                       int maxUses, int xp, String additionalWantedItem, int additionalWantedCount,
                       boolean soulStealerEnchant, boolean randomEnchant) {
        JsonObject json = new JsonObject();
        json.add("wants", item(wantedItem, wantedCount));
        json.add("gives", item(givenItem, givenCount));
        json.addProperty("max_uses", maxUses);
        json.addProperty("reputation_discount", 0.05);
        json.addProperty("xp", xp);

        if (additionalWantedItem != null) {
            json.add("additional_wants", item(additionalWantedItem, additionalWantedCount));
        }

        JsonArray modifiers = new JsonArray();

        if (soulStealerEnchant) {
            modifiers.add(enchantWithLevels(MODID + ":soul_stealer", 1, 1, false));
        }

        if (randomEnchant) {
            modifiers.add(enchantWithLevels("#minecraft:on_traded_equipment", 5, 20, true));
        }

        if (!modifiers.isEmpty()) {
            modifiers.add(requireEnchantment(givenItem, givenItem.equals("minecraft:enchanted_book")));
            json.add("given_item_modifiers", modifiers);
        }

        Path path = dataRoot.resolve("villager_trade").resolve("soul_exchanger")
                .resolve(Integer.toString(level)).resolve(name + ".json");
        writes.add(DataProvider.saveStable(cache, json, path));
    }

    private JsonObject enchantWithLevels(String options, int minLevel, int maxLevel, boolean additionalCost) {
        JsonObject modifier = new JsonObject();
        modifier.addProperty("function", "minecraft:enchant_with_levels");
        modifier.addProperty("include_additional_cost_component", additionalCost);
        modifier.add("levels", uniform(minLevel, maxLevel));
        modifier.addProperty("options", options);
        return modifier;
    }

    private JsonObject requireEnchantment(String itemId, boolean stored) {
        JsonArray enchantments = new JsonArray();
        enchantments.add(new JsonObject());

        JsonObject predicates = new JsonObject();
        predicates.add(stored ? "minecraft:stored_enchantments" : "minecraft:enchantments", enchantments);

        JsonObject itemFilter = new JsonObject();
        itemFilter.addProperty("items", itemId);
        itemFilter.add("predicates", predicates);

        JsonObject discard = new JsonObject();
        discard.addProperty("function", "minecraft:discard");

        JsonObject filtered = new JsonObject();
        filtered.addProperty("function", "minecraft:filtered");
        filtered.add("item_filter", itemFilter);
        filtered.add("on_fail", discard);
        return filtered;
    }

    private JsonObject uniform(int min, int max) {
        JsonObject provider = new JsonObject();
        provider.addProperty("type", "minecraft:uniform");
        provider.addProperty("min", min);
        provider.addProperty("max", max);
        return provider;
    }

    private void tradeSet(List<CompletableFuture<?>> writes, CachedOutput cache, int level, int amount, String... trades) {
        JsonObject json = new JsonObject();
        JsonArray entries = new JsonArray();
        for (String trade : trades) {
            entries.add(MODID + ":soul_exchanger/" + level + "/" + trade);
        }
        json.add("trades", entries);
        json.addProperty("amount", amount);
        json.addProperty("random_sequence", MODID + ":trade_set/soul_exchanger/level_" + level);

        Path path = dataRoot.resolve("trade_set").resolve("soul_exchanger")
                .resolve("level_" + level + ".json");
        writes.add(DataProvider.saveStable(cache, json, path));
    }

    private void levelFiveTradeSet(List<CompletableFuture<?>> writes, CachedOutput cache) {
        String[] trades = {
                "slayer_soul",
                "soul_stealer_book",
                "swords/wooden_sword",
                "swords/stone_sword",
                "swords/golden_sword",
                "swords/iron_sword",
                "swords/diamond_sword",
                "swords/netherite_sword",
                "swords/coal_sword",
                "swords/redstone_sword",
                "swords/lapis_sword",
                "swords/emerald_sword",
                "swords/obsidian_sword",
                "swords/infused_lava_sword",
                "swords/guardian_sword",
                "swords/super_star_sword",
                "swords/ender_dragon_sword",
                "swords/slayer_sword"
        };

        JsonObject json = new JsonObject();
        JsonArray entries = new JsonArray();
        for (String trade : trades) {
            entries.add(MODID + ":soul_exchanger/5/" + trade);
        }
        json.add("trades", entries);
        json.addProperty("amount", 3);
        json.addProperty("random_sequence", MODID + ":trade_set/soul_exchanger/level_5");

        Path path = dataRoot.resolve("trade_set").resolve("soul_exchanger")
                .resolve("level_5.json");
        writes.add(DataProvider.saveStable(cache, json, path));
    }

    private JsonObject item(String id, int count) {
        JsonObject item = new JsonObject();
        item.addProperty("id", id);
        item.addProperty("count", count);
        return item;
    }

    @Override
    public String getName() {
        return "ArmorPlus Villager Trades";
    }
}
