package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.materials.FrostCrystalItem;
import com.sofodev.armorplus.registry.items.materials.LavaCrystalItem;
import com.sofodev.armorplus.registry.items.materials.MaterialItem;
import com.sofodev.armorplus.registry.items.special.SoulItem;
import com.sofodev.armorplus.registry.items.special.ThankYouItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.ArmorPlus.ITEMS;
import static net.minecraft.util.text.TextFormatting.*;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class APItems {

    public static void registerAPItems() {

    }

    //Crystals
    public static final RegistryObject<Item> LAVA_CRYSTAL = ITEMS.register("lava_crystal", () -> new LavaCrystalItem(false));
    public static final RegistryObject<Item> INFUSED_LAVA_CRYSTAL = ITEMS.register("infused_lava_crystal", () -> new LavaCrystalItem(true));
    public static final RegistryObject<Item> FROST_CRYSTAL = ITEMS.register("frost_crystal", () -> new FrostCrystalItem(false));
    public static final RegistryObject<Item> INFUSED_FROST_CRYSTAL = ITEMS.register("infused_frost_crystal", () -> new FrostCrystalItem(true));

    //Materials
    public static final RegistryObject<Item> CHAINMAIL = ITEMS.register("chainmail", () -> new MaterialItem(GRAY, new Properties()));
    public static final RegistryObject<Item> GUARDIAN_SCALE = ITEMS.register("guardian_scale", () -> new MaterialItem(BLUE, new Properties()));
    public static final RegistryObject<Item> WITHER_BONE = ITEMS.register("wither_bone", () -> new MaterialItem(WHITE, new Properties().isImmuneToFire()));
    public static final RegistryObject<Item> ENDER_DRAGON_SCALE = ITEMS.register("ender_dragon_scale", () -> new MaterialItem(DARK_PURPLE, new Properties()));
    public static final RegistryObject<Item> THE_ULTIMATE_MATERIAL = ITEMS.register("the_ultimate_material", () -> new MaterialItem(DARK_PURPLE, new Properties().isImmuneToFire()));

    //Boss Souls
    public static final RegistryObject<Item> WITHER_BOSS_SOUL = ITEMS.register("soul_wither_boss", () -> new SoulItem("wither"));
    public static final RegistryObject<Item> ELDER_GUARDIAN_SOUL = ITEMS.register("soul_elder_guardian", () -> new SoulItem("elder_guardian"));
    public static final RegistryObject<Item> ENDER_DRAGON_SOUL = ITEMS.register("soul_ender_dragon", () -> new SoulItem("ender_dragon"));
    //Servants/Minion Souls
    public static final RegistryObject<Item> WITHER_SKELETON_SOUL = ITEMS.register("soul_wither_skeleton", () -> new SoulItem(false, "wither_skeleton"));
    public static final RegistryObject<Item> GUARDIAN_SOUL = ITEMS.register("soul_guardian", () -> new SoulItem(false, "guardian"));
    public static final RegistryObject<Item> ENDERMAN_SOUL = ITEMS.register("soul_enderman", () -> new SoulItem(false, "enderman"));
    public static final RegistryObject<Item> BLAZE_SOUL = ITEMS.register("soul_blaze", () -> new SoulItem(false, "blaze"));

    //Enhanced Materials
    public static final RegistryObject<Item> ENHANCED_CHAINMAIL = ITEMS.register("en_chainmail", () -> new MaterialItem(true, GRAY, new Properties()));
    public static final RegistryObject<Item> ENHANCED_IRON = ITEMS.register("en_iron", () -> new MaterialItem(true, GRAY, new Properties()));
    public static final RegistryObject<Item> ENHANCED_GOLD = ITEMS.register("en_gold", () -> new MaterialItem(true, GRAY, new Properties()));
    public static final RegistryObject<Item> ENHANCED_DIAMOND = ITEMS.register("en_diamond", () -> new MaterialItem(true, GRAY, new Properties()));
    public static final RegistryObject<Item> ENHANCED_NETHERITE = ITEMS.register("en_netherite", () -> new MaterialItem(true, GRAY, new Properties().isImmuneToFire()));

    //Other
    public static final RegistryObject<Item> OBSIDIAN_STICK = ITEMS.register("obsidian_stick", () -> new MaterialItem(false, DARK_PURPLE, new Properties().isImmuneToFire()));
    public static final RegistryObject<Item> WOODEN_ROD = ITEMS.register("wooden_rod", () -> new MaterialItem(false, GRAY, new Properties()));
    public static final RegistryObject<Item> THANK_YOU_6M = ITEMS.register("thank_you_6m", ThankYouItem::new);
    //public static final RegistryObject<Item> ARMORPLUS_BOOK = ITEMS.register("guide_book", () -> new APBookItem(new Properties()));

}