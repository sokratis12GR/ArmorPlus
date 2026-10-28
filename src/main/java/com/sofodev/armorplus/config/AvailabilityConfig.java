package com.sofodev.armorplus.config;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

/**
 * Configurable availability, independent of vanilla feature flags.
 * These settings live in the COMMON config so they are available on both logical sides.
 */
public final class AvailabilityConfig {
    private AvailabilityConfig() {
    }

    public static boolean itemEnabled(Item item) {
        Identifier id = BuiltInRegistries.ITEM.getKey(item);
        if (id == null) return true;
        String key = id.toString();
        return !hiddenContains(ArmorPlusConfig.hiddenItems, key) && !hiddenContains(ArmorPlusConfig.hiddenBlocks, key);
    }

    public static boolean blockEnabled(Block block) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        return id == null || !hiddenContains(ArmorPlusConfig.hiddenBlocks, id.toString());
    }

    /**
     * Datagen (e.g. loot tables) runs block/item availability checks before the server config is loaded,
     * so {@code ConfigValue#get()} throws IllegalStateException there; treat that as "not hidden".
     */
    private static boolean hiddenContains(ModConfigSpec.ConfigValue<List<? extends String>> config, String key) {
        if (config == null) return false;
        try {
            return config.get().contains(key);
        } catch (IllegalStateException e) {
            return false;
        }
    }
}
