package com.sofodev.armorplus.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class DataUtils {

    public static String getPath(DeferredHolder<?, ?> object) {
        return object.getId().getPath();
    }

    public static String getPath(ItemLike item) {
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item.asItem());

        if (rl != null) return rl.getPath();
        else throw new NullPointerException("INVALID PATH FOR ITEM | The item is not registered yet!!!");
    }

    public static Item quickModLookupItem(ResourceLocation loc) {
        return BuiltInRegistries.ITEM.get(setRL(loc.getPath().replace("_base", "")));
    }
}
