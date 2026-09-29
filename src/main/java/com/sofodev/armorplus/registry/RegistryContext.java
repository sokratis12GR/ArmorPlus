package com.sofodev.armorplus.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/** Supplies the registry key to 26.1 item/block properties before construction. */
public final class RegistryContext {
    private static final ThreadLocal<ResourceKey<Item>> ITEM_KEY = new ThreadLocal<>();
    private static final ThreadLocal<ResourceKey<Block>> BLOCK_KEY = new ThreadLocal<>();

    private RegistryContext() {}

    public static <T extends Item> T withItemKey(ResourceKey<Item> key, java.util.function.Supplier<T> factory) {
        ITEM_KEY.set(key);
        try { return factory.get(); } finally { ITEM_KEY.remove(); }
    }

    public static <T extends Block> T withBlockKey(ResourceKey<Block> key, java.util.function.Supplier<T> factory) {
        BLOCK_KEY.set(key);
        try { return factory.get(); } finally { BLOCK_KEY.remove(); }
    }

    public static Item.Properties itemProperties() {
        ResourceKey<Item> key = ITEM_KEY.get();
        if (key == null) throw new IllegalStateException("ArmorPlus item constructed outside registration context");
        return new Item.Properties().setId(key);
    }

    public static Item.Properties itemProperties(Item.Properties properties) {
        ResourceKey<Item> key = ITEM_KEY.get();
        if (key == null) throw new IllegalStateException("ArmorPlus item constructed outside registration context");
        return properties.setId(key);
    }

    public static BlockBehaviour.Properties blockProperties(BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = BLOCK_KEY.get();
        if (key == null) throw new IllegalStateException("ArmorPlus block constructed outside registration context");
        return properties.setId(key);
    }
}
