package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.materials.FrostCrystalItem;
import com.sofodev.armorplus.registry.items.materials.LavaCrystalItem;
import com.sofodev.armorplus.registry.items.materials.MaterialItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.sofodev.armorplus.ArmorPlus.ITEMS;
import static net.minecraft.util.text.TextFormatting.*;

public class APItems {

    public static final RegistryObject<Item> LAVA_CRYSTAL = ITEMS.register("lava_crystal", () -> new LavaCrystalItem(false));
    public static final RegistryObject<Item> INFUSED_LAVA_CRYSTAL = ITEMS.register("infused_lava_crystal", () -> new LavaCrystalItem(true));
    public static final RegistryObject<Item> FROST_CRYSTAL = ITEMS.register("frost_crystal", () -> new FrostCrystalItem(false));
    public static final RegistryObject<Item> INFUSED_FROST_CRYSTAL = ITEMS.register("infused_frost_crystal", () -> new FrostCrystalItem(true));

    public static final RegistryObject<Item> CHAINMAIL = ITEMS.register("chainmail", () -> new MaterialItem(GRAY));
    public static final RegistryObject<Item> GUARDIAN_SCALE = ITEMS.register("guardian_scale", () -> new MaterialItem(BLUE));
    public static final RegistryObject<Item> WITHER_BONE = ITEMS.register("wither_bone", () -> new MaterialItem(WHITE));
    public static final RegistryObject<Item> ENDER_DRAGON_SCALE = ITEMS.register("ender_dragon_scale", () -> new MaterialItem(DARK_PURPLE));
    public static final RegistryObject<Item> THE_ULTIMATE_MATERIAL = ITEMS.register("the_ultimate_material", () -> new MaterialItem(DARK_PURPLE));

}
