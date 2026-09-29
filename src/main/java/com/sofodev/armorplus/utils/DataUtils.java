package com.sofodev.armorplus.utils;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class DataUtils {

    public static String getPath(RegistryObject<?> object) {
        return object.getId().getPath();
    }

    public static String getPath(ItemLike item) {
        Identifier rl = ForgeRegistries.ITEMS.getKey(item.asItem());

        if (rl != null) return rl.getPath();
        else throw new NullPointerException("INVALID PATH FOR ITEM | The item is not registered yet!!!");
    }

    public static Item quickModLookupItem(Identifier loc) {
        return ForgeRegistries.ITEMS.getValue(setRL(loc.getPath().replace("_base", "")));
    }
}
