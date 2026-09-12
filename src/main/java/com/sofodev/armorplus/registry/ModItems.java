package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.entity.arrow.ArrowType;
import com.sofodev.armorplus.registry.item.APItemBase;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.armor.APArmorMaterial;
import com.sofodev.armorplus.registry.item.armor.APArmorProperties;
import com.sofodev.armorplus.registry.item.arrow.APArrowItem;
import com.sofodev.armorplus.registry.item.material.FrostCrystalItem;
import com.sofodev.armorplus.registry.item.material.FrostLavaCrystalItem;
import com.sofodev.armorplus.registry.item.material.LavaCrystalItem;
import com.sofodev.armorplus.registry.item.material.MaterialItem;
import com.sofodev.armorplus.registry.item.special.SoulItem;
import com.sofodev.armorplus.registry.item.special.ThankYouItem;
import com.sofodev.armorplus.registry.item.tool.*;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolMaterial;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.*;
import static com.sofodev.armorplus.registry.item.armor.APArmorMaterial.SLAYER;
import static net.minecraft.ChatFormatting.*;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

    //Armors
    public static final Set<DeferredHolder<Item, ? extends APArmorItem>> HELMETS = registerArmorForSlot(ArmorItem.Type.HELMET);
    public static final Set<DeferredHolder<Item, ? extends APArmorItem>> CHESTPLATES = registerArmorForSlot(ArmorItem.Type.CHESTPLATE);
    public static final Set<DeferredHolder<Item, ? extends APArmorItem>> LEGGINGS = registerArmorForSlot(ArmorItem.Type.LEGGINGS);
    public static final Set<DeferredHolder<Item, ? extends APArmorItem>> BOOTS = registerArmorForSlot(ArmorItem.Type.BOOTS);

    //ArmorBases (Soulless)
    public static final Set<DeferredHolder<Item, Item>> SUPER_STAR_BASES = registerArmorBases(APArmorMaterial.SUPER_STAR);
    public static final Set<DeferredHolder<Item, Item>> GUARDIAN_BASES = registerArmorBases(APArmorMaterial.GUARDIAN);
    public static final Set<DeferredHolder<Item, Item>> ENDER_DRAGON_BASES = registerArmorBases(APArmorMaterial.ENDER_DRAGON);
    public static final Set<DeferredHolder<Item, Item>> SLAYER_BASES = registerArmorBases(SLAYER);
    //ToolBases (Soulless)
    public static final DeferredHolder<Item, Item> GUARDIAN_SWORD_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "sword");
    public static final DeferredHolder<Item, Item> GUARDIAN_BATTLE_AXE_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "battle_axe");
    public static final DeferredHolder<Item, Item> GUARDIAN_PICKAXE_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "pickaxe");
    public static final DeferredHolder<Item, Item> GUARDIAN_BOW_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "bow");
    public static final DeferredHolder<Item, Item> SUPER_STAR_SWORD_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "sword");
    public static final DeferredHolder<Item, Item> SUPER_STAR_BATTLE_AXE_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "battle_axe");
    public static final DeferredHolder<Item, Item> SUPER_STAR_PICKAXE_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "pickaxe");
    public static final DeferredHolder<Item, Item> SUPER_STAR_BOW_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "bow");
    public static final DeferredHolder<Item, Item> ENDER_DRAGON_SWORD_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "sword");
    public static final DeferredHolder<Item, Item> ENDER_DRAGON_BATTLE_AXE_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "battle_axe");
    public static final DeferredHolder<Item, Item> ENDER_DRAGON_PICKAXE_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "pickaxe");
    public static final DeferredHolder<Item, Item> ENDER_DRAGON_BOW_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "bow");
    //Slayer
    public static final DeferredHolder<Item, Item> SLAYER_SWORD_BASE = registerBase(APToolMaterial.SLAYER_MAT, "sword");
    public static final DeferredHolder<Item, Item> SLAYER_BATTLE_AXE_BASE = registerBase(APToolMaterial.SLAYER_MAT, "battle_axe");
    public static final DeferredHolder<Item, Item> SLAYER_PICKAXE_BASE = registerBase(APToolMaterial.SLAYER_MAT, "pickaxe");
    public static final DeferredHolder<Item, Item> SLAYER_BOW_BASE = registerBase(APToolMaterial.SLAYER_MAT, "bow");

    //Tools & Weapons
    public static final DeferredHolder<Item, Item>[] SWORDS = new DeferredHolder[AP_TOOL_MATERIAL_LENGTH];
    public static final DeferredHolder<Item, Item>[] BATTLE_AXES = new DeferredHolder[AP_TOOL_MATERIAL_LENGTH];
    public static final DeferredHolder<Item, Item>[] PICKAXES = new DeferredHolder[AP_TOOL_MATERIAL_LENGTH];
    public static final DeferredHolder<Item, Item>[] SHOVELS = new DeferredHolder[AP_TOOL_MATERIAL_LENGTH];
    public static final DeferredHolder<Item, Item>[] BOWS = new DeferredHolder[AP_TOOL_MATERIAL_LENGTH];
    public static final DeferredHolder<Item, Item>[] MACES = new DeferredHolder[AP_MACE_MAT_LENGTH];

    //Arrows
    public static final DeferredHolder<Item, ArrowItem> ITEM_COAL_ARROW = registerArrow(ArrowType.COAL);
    public static final DeferredHolder<Item, ArrowItem> ITEM_LAPIS_ARROW = registerArrow(ArrowType.LAPIS);
    public static final DeferredHolder<Item, ArrowItem> ITEM_REDSTONE_ARROW = registerArrow(ArrowType.REDSTONE);
    public static final DeferredHolder<Item, ArrowItem> ITEM_EMERALD_ARROW = registerArrow(ArrowType.EMERALD);
    public static final DeferredHolder<Item, ArrowItem> ITEM_OBSIDIAN_ARROW = registerArrow(ArrowType.OBSIDIAN);
    public static final DeferredHolder<Item, ArrowItem> ITEM_INFUSED_LAVA_ARROW = registerArrow(ArrowType.INFUSED_LAVA);
    public static final DeferredHolder<Item, ArrowItem> ITEM_GUARDIAN_ARROW = registerArrow(ArrowType.GUARDIAN);
    public static final DeferredHolder<Item, ArrowItem> ITEM_SUPER_STAR_ARROW = registerArrow(ArrowType.SUPER_STAR);
    public static final DeferredHolder<Item, ArrowItem> ITEM_ENDER_DRAGON_ARROW = registerArrow(ArrowType.ENDER_DRAGON);
    //Crystals
    public static final DeferredHolder<Item, Item> LAVA_CRYSTAL = register("lava_crystal", () -> new LavaCrystalItem(false));
    public static final DeferredHolder<Item, Item> INFUSED_LAVA_CRYSTAL = register("infused_lava_crystal", () -> new LavaCrystalItem(true));
    public static final DeferredHolder<Item, Item> FROST_CRYSTAL = register("frost_crystal", () -> new FrostCrystalItem(false));
    public static final DeferredHolder<Item, Item> INFUSED_FROST_CRYSTAL = register("infused_frost_crystal", () -> new FrostCrystalItem(true));
    public static final DeferredHolder<Item, Item> INFUSED_FROST_LAVA_CRYSTAL = register("infused_frost_lava_crystal", FrostLavaCrystalItem::new);
    //Materials
    public static final DeferredHolder<Item, Item> CHAINMAIL = register("chainmail", () -> new MaterialItem(GRAY, new Item.Properties()));
    public static final DeferredHolder<Item, Item> GUARDIAN_SCALE = register("guardian_scale", () -> new MaterialItem(BLUE, new Item.Properties()));
    public static final DeferredHolder<Item, Item> WITHER_BONE = register("wither_bone", () -> new MaterialItem(WHITE, new Item.Properties().fireResistant()));
    public static final DeferredHolder<Item, Item> ENDER_DRAGON_SCALE = register("ender_dragon_scale", () -> new MaterialItem(DARK_PURPLE, new Item.Properties()));
    public static final DeferredHolder<Item, Item> THE_ULTIMATE_MATERIAL = register("the_ultimate_material", () -> new MaterialItem(DARK_PURPLE, new Item.Properties().fireResistant()));
    //Boss Souls
    public static final DeferredHolder<Item, Item> WITHER_BOSS_SOUL = register("soul_wither_boss", () -> new SoulItem("wither"));
    public static final DeferredHolder<Item, Item> ELDER_GUARDIAN_SOUL = register("soul_elder_guardian", () -> new SoulItem("elder_guardian"));
    public static final DeferredHolder<Item, Item> ENDER_DRAGON_SOUL = register("soul_ender_dragon", () -> new SoulItem("ender_dragon"));
    //Servants/Minion Souls
    public static final DeferredHolder<Item, Item> WITHER_SKELETON_SOUL = register("soul_wither_skeleton", () -> new SoulItem(false, "wither_skeleton"));
    public static final DeferredHolder<Item, Item> GUARDIAN_SOUL = register("soul_guardian", () -> new SoulItem(false, "guardian"));
    public static final DeferredHolder<Item, Item> ENDERMAN_SOUL = register("soul_enderman", () -> new SoulItem(false, "enderman"));
    public static final DeferredHolder<Item, Item> BLAZE_SOUL = register("soul_blaze", () -> new SoulItem(false, "blaze"));
    public static final DeferredHolder<Item, Item> SLAYER_SOUL = register("soul_slayer", () -> new SoulItem(null));
    //Enhanced Materials
    public static final DeferredHolder<Item, Item> ENHANCED_CHAINMAIL = register("enhanced_chainmail", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ENHANCED_IRON = register("enhanced_iron", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ENHANCED_GOLD = register("enhanced_gold", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ENHANCED_DIAMOND = register("enhanced_diamond", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final DeferredHolder<Item, Item> ENHANCED_NETHERITE = register("enhanced_netherite", () -> new MaterialItem(true, GRAY, new Item.Properties().fireResistant()));
    //Other
    public static final DeferredHolder<Item, Item> OBSIDIAN_STICK = register("obsidian_stick", () -> new MaterialItem(false, DARK_PURPLE, new Item.Properties().fireResistant()));
    public static final DeferredHolder<Item, Item> WOODEN_ROD = register("wooden_rod", () -> new MaterialItem(false, GRAY, new Item.Properties()));
    public static final DeferredHolder<Item, Item> LAVA_SHARD = register("lava_shard", () -> new MaterialItem(false, GOLD, new Item.Properties()));
    public static final DeferredHolder<Item, Item> FROST_SHARD = register("frost_shard", () -> new MaterialItem(false, AQUA, new Item.Properties()));
    public static final DeferredHolder<Item, Item> THANK_YOU = register("thank_you", ThankYouItem::new);
    //public static final DeferredHolder<Item, Item> TROPHY = ITEMS.register("trophy", () -> new TrophyItem(ModBlocks.TROPHY));

    static {
        registerToolForType(SWORDS, BATTLE_AXES, PICKAXES, BOWS);
        registerMaceForType(MACES);
    }

    public static <ITEM extends Item> DeferredHolder<Item, ITEM> register(String name, DeferredRegister<Item> items, Supplier<ITEM> itemSupplier) {
        return items.register(name, itemSupplier);
    }

    public static <ITEM extends Item> DeferredHolder<Item, ITEM> register(String name, Supplier<ITEM> itemSupplier) {
        return register(name, ITEMS, itemSupplier);
    }

    /**
     * This function automatically registers item(s), in groups that represent an armor set.
     * <p>
     * Utilizes the {@link APArmorProperties} and uses its properties via the {@link APArmorMaterial} object.
     * Registry names are set on sight (first we put the material's name, then we put the slot's normalized name).
     *
     * @param slot the equipment slot we will be assigning the set to, which will help distinguishing different equipment from one another.
     * @return a full registered armor set list that contains a set of all available {@link APArmorMaterial#values()} materials for that equipment slot.
     */
    public static Set<DeferredHolder<Item, ? extends APArmorItem>> registerArmorForSlot(ArmorItem.Type slot) {
        return Arrays.stream(APArmorMaterial.values())
                .filter(type -> slot != ArmorItem.Type.BODY) // skip BODY
                .map(mat -> register(String.format("%s_%s", mat.getName(), slot.getName()), () -> new APArmorItem(mat, slot) {
                    @Override
                    public Component getName(ItemStack p_41458_) {
                        return super.getName(p_41458_).copy().withStyle(mat.getFormatting());
                    }
                })).collect(Collectors.toSet());
    }

    /**
     * Registers each {@link APToolMaterial#values()} to have its own sword, battleaxe, pickaxe and bow.
     *
     * @param swords   - Sword objects to be registered
     * @param axes     - Battle Axe objects to be registered
     * @param pickaxes - Pickaxe objects to be registered
     * @param bows     - Bow objects to be registered
     */
    public static void registerToolForType(DeferredHolder<Item, Item>[] swords, DeferredHolder<Item, Item>[] axes, DeferredHolder<Item, Item>[] pickaxes, DeferredHolder<Item, Item>[] bows) {
        IntStream.range(0, AP_TOOL_MATERIAL_LENGTH).forEach(i -> {
            APToolMaterial mat = APToolMaterial.values()[i];
            swords[i] = register(String.format("%s_sword", mat.getName()), () -> new APSwordItem(mat));
            axes[i] = register(String.format("%s_battle_axe", mat.getName()), () -> new APBattleAxeItem(mat));
            pickaxes[i] = register(String.format("%s_pickaxe", mat.getName()), () -> new APPickaxeItem(mat));
            bows[i] = register(String.format("%s_bow", mat.getName()), () -> new APBowItem(mat));
        });
    }

    /**
     * Registers the specified {@link APToolMaterial} to have its own sword, battleaxe, pickaxe and bow <b>bases</b>.
     *
     * @param type - What kind of item will be registered for the base
     */
    public static DeferredHolder<Item, Item> registerBase(APToolMaterial mat, String type) {
        return register(String.format("%s_%s_base", mat.getName(), type), APItemBase::new);
    }

    /**
     * Registers each {@link APMaceMaterial#values()} to have its own mace.
     *
     * @param maces - Mace objects to be registered
     */
    public static void registerMaceForType(DeferredHolder<Item, Item>[] maces) {
        IntStream.range(0, AP_MACE_MAT_LENGTH).forEach(i -> {
            APMaceMaterial mat = APMaceMaterial.values()[i];
            maces[i] = register(String.format("%s_mace", mat.getName()), () -> new APMaceItem(mat, new Item.Properties()));
        });
    }

    /**
     * @param type - The material the arrow is made of
     * @return ArrowItem registry object for the specified arrow type.
     */
    public static DeferredHolder<Item, ArrowItem> registerArrow(ArrowType type) {
        return register(type.getItemArrowName(), () -> new APArrowItem(type));
    }

    /**
     * @param material - The material that we will be creating a base to (basically taking the material's name and adding it as a new item)
     * @return a set of item registry objects that consists of "base" items for the specified armor material.
     */
    private static Set<DeferredHolder<Item, Item>> registerArmorBases(APArmorMaterial material) {
        return Arrays.stream(ArmorItem.Type.values())
                .filter(type -> type != ArmorItem.Type.BODY) // skip BODY
                .map(slot -> register(
                        String.format("%s_%s_base", material.getName(), slot.getName()),
                        () -> (Item) new APItemBase())
                )
                .collect(Collectors.toSet());
    }

}