package com.sofodev.armorplus.utils;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class DataUtils {

    public static String getPath(RegistryObject<?> object) {
        return object.getId().getPath();
    }

    public static String getPath(IItemProvider item) {
        ResourceLocation rl = item.asItem().getRegistryName();

        if (rl != null) return rl.getPath();
        else throw new NullPointerException("INVALID PATH FOR ITEM | The item is not registered yet!!!");
    }

    public static Item quickModLookupItem(ResourceLocation loc) {
        return ForgeRegistries.ITEMS.getValue(setRL(loc.getPath().replace("_base", "")));
    }
}
