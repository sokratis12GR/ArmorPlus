package com.sofodev.armorplus.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Availability is independent of vanilla feature flags. Entries are common, not server, config.
 */
public final class AvailabilityConfig {
    private AvailabilityConfig() {
    }

    public static boolean itemEnabled(Item item) {
        if (ArmorPlusConfig.hiddenItems == null) return true;
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        if (id == null) return true;
        return !ArmorPlusConfig.hiddenItems.get().contains(id.toString())
                && !ArmorPlusConfig.hiddenBlocks.get().contains(id.toString());
    }

    public static boolean blockEnabled(Block block) {
        if (ArmorPlusConfig.hiddenBlocks == null) return true;
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        return id == null || !ArmorPlusConfig.hiddenBlocks.get().contains(id.toString());
    }
}
