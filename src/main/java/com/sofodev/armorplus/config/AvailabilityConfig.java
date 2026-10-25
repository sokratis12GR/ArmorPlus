package com.sofodev.armorplus.config;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Configurable availability, independent of vanilla feature flags.
 * These settings live in the COMMON config so they are available on both logical sides.
 */
public final class AvailabilityConfig {
    private AvailabilityConfig() {
    }

    public static boolean itemEnabled(Item item) {
        Identifier id = ForgeRegistries.ITEMS.getKey(item);
        if (id == null) return true;
        String key = id.toString();
        return (ArmorPlusConfig.hiddenItems == null || !ArmorPlusConfig.hiddenItems.get().contains(key))
                && (ArmorPlusConfig.hiddenBlocks == null || !ArmorPlusConfig.hiddenBlocks.get().contains(key));
    }

    public static boolean blockEnabled(Block block) {
        if (ArmorPlusConfig.hiddenBlocks == null) return true;
        Identifier id = ForgeRegistries.BLOCKS.getKey(block);
        return id == null || !ArmorPlusConfig.hiddenBlocks.get().contains(id.toString());
    }
}
