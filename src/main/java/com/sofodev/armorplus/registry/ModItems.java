package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.arrows.ArrowType;
import com.sofodev.armorplus.registry.items.APItemBase;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.armors.APArmorProperties;
import com.sofodev.armorplus.registry.items.arrows.APArrowItem;
import com.sofodev.armorplus.registry.items.materials.FrostCrystalItem;
import com.sofodev.armorplus.registry.items.materials.LavaCrystalItem;
import com.sofodev.armorplus.registry.items.materials.MaterialItem;
import com.sofodev.armorplus.registry.items.special.SoulItem;
import com.sofodev.armorplus.registry.items.special.ThankYouItem;
import com.sofodev.armorplus.registry.items.tools.*;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolMaterial;
import com.sofodev.armorplus.registry.items.tools.render.APMaceRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.ArmorPlus.*;
import static com.sofodev.armorplus.registry.items.armors.APArmorMaterial.SLAYER;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;
import static net.minecraft.inventory.EquipmentSlotType.*;
import static net.minecraft.inventory.EquipmentSlotType.Group.ARMOR;
import static net.minecraft.util.text.TextFormatting.*;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Bus.MOD)
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    //Armors
    public static final Set<RegistryObject<APArmorItem>> HELMETS = registerArmorForSlot(HEAD);
    public static final Set<RegistryObject<APArmorItem>> CHESTPLATES = registerArmorForSlot(CHEST);
    public static final Set<RegistryObject<APArmorItem>> LEGGINGS = registerArmorForSlot(LEGS);
    public static final Set<RegistryObject<APArmorItem>> BOOTS = registerArmorForSlot(FEET);

    //ArmorBases (Soulless)
    public static final Set<RegistryObject<Item>> SUPER_STAR_BASES = registerArmorBases(APArmorMaterial.SUPER_STAR);
    public static final Set<RegistryObject<Item>> GUARDIAN_BASES = registerArmorBases(APArmorMaterial.GUARDIAN);
    public static final Set<RegistryObject<Item>> ENDER_DRAGON_BASES = registerArmorBases(APArmorMaterial.ENDER_DRAGON);
    public static final Set<RegistryObject<Item>> SLAYER_BASES = registerArmorBases(SLAYER);
    //ToolBases (Soulless)
    public static final RegistryObject<Item> GUARDIAN_SWORD_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "sword");
    public static final RegistryObject<Item> GUARDIAN_BATTLE_AXE_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "battle_axe");
    public static final RegistryObject<Item> GUARDIAN_PICKAXE_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "pickaxe");
    public static final RegistryObject<Item> GUARDIAN_BOW_BASE = registerBase(APToolMaterial.GUARDIAN_MAT, "bow");
    public static final RegistryObject<Item> SUPER_STAR_SWORD_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "sword");
    public static final RegistryObject<Item> SUPER_STAR_BATTLE_AXE_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "battle_axe");
    public static final RegistryObject<Item> SUPER_STAR_PICKAXE_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "pickaxe");
    public static final RegistryObject<Item> SUPER_STAR_BOW_BASE = registerBase(APToolMaterial.SUPER_STAR_MAT, "bow");
    public static final RegistryObject<Item> ENDER_DRAGON_SWORD_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "sword");
    public static final RegistryObject<Item> ENDER_DRAGON_BATTLE_AXE_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "battle_axe");
    public static final RegistryObject<Item> ENDER_DRAGON_PICKAXE_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "pickaxe");
    public static final RegistryObject<Item> ENDER_DRAGON_BOW_BASE = registerBase(APToolMaterial.ENDER_DRAGON_MAT, "bow");

    //Tools & Weapons
    public static final RegistryObject<Item>[] SWORDS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] BATTLE_AXES = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] PICKAXES = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] SHOVELS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] BOWS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] MACES = new RegistryObject[AP_MACE_MAT_LENGTH];

    //Arrows
    public static final RegistryObject<ArrowItem> ITEM_COAL_ARROW = registerArrow(ArrowType.COAL);
    public static final RegistryObject<ArrowItem> ITEM_LAPIS_ARROW = registerArrow(ArrowType.LAPIS);
    public static final RegistryObject<ArrowItem> ITEM_REDSTONE_ARROW = registerArrow(ArrowType.REDSTONE);
    public static final RegistryObject<ArrowItem> ITEM_EMERALD_ARROW = registerArrow(ArrowType.EMERALD);
    public static final RegistryObject<ArrowItem> ITEM_OBSIDIAN_ARROW = registerArrow(ArrowType.OBSIDIAN);
    public static final RegistryObject<ArrowItem> ITEM_INFUSED_LAVA_ARROW = registerArrow(ArrowType.INFUSED_LAVA);
    public static final RegistryObject<ArrowItem> ITEM_GUARDIAN_ARROW = registerArrow(ArrowType.GUARDIAN);
    public static final RegistryObject<ArrowItem> ITEM_SUPER_STAR_ARROW = registerArrow(ArrowType.SUPER_STAR);
    public static final RegistryObject<ArrowItem> ITEM_ENDER_DRAGON_ARROW = registerArrow(ArrowType.ENDER_DRAGON);

    public static final Set<RegistryObject<BlockItem>> ITEM_BLOCKS = registerBlockItems();

    static {
        registerToolForType(SWORDS, BATTLE_AXES, PICKAXES, BOWS);
        registerMaceForType(MACES);
    }

    //Crystals
    public static final RegistryObject<Item> LAVA_CRYSTAL = ITEMS.register("lava_crystal", () -> new LavaCrystalItem(false));
    public static final RegistryObject<Item> INFUSED_LAVA_CRYSTAL = ITEMS.register("infused_lava_crystal", () -> new LavaCrystalItem(true));
    public static final RegistryObject<Item> FROST_CRYSTAL = ITEMS.register("frost_crystal", () -> new FrostCrystalItem(false));
    public static final RegistryObject<Item> INFUSED_FROST_CRYSTAL = ITEMS.register("infused_frost_crystal", () -> new FrostCrystalItem(true));

    //Materials
    public static final RegistryObject<Item> CHAINMAIL = ITEMS.register("chainmail", () -> new MaterialItem(GRAY, new Item.Properties()));
    public static final RegistryObject<Item> GUARDIAN_SCALE = ITEMS.register("guardian_scale", () -> new MaterialItem(BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WITHER_BONE = ITEMS.register("wither_bone", () -> new MaterialItem(WHITE, new Item.Properties().isImmuneToFire()));
    public static final RegistryObject<Item> ENDER_DRAGON_SCALE = ITEMS.register("ender_dragon_scale", () -> new MaterialItem(DARK_PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> THE_ULTIMATE_MATERIAL = ITEMS.register("the_ultimate_material", () -> new MaterialItem(DARK_PURPLE, new Item.Properties().isImmuneToFire()));

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
    public static final RegistryObject<Item> ENHANCED_CHAINMAIL = ITEMS.register("en_chainmail", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final RegistryObject<Item> ENHANCED_IRON = ITEMS.register("en_iron", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final RegistryObject<Item> ENHANCED_GOLD = ITEMS.register("en_gold", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final RegistryObject<Item> ENHANCED_DIAMOND = ITEMS.register("en_diamond", () -> new MaterialItem(true, GRAY, new Item.Properties()));
    public static final RegistryObject<Item> ENHANCED_NETHERITE = ITEMS.register("en_netherite", () -> new MaterialItem(true, GRAY, new Item.Properties().isImmuneToFire()));

    //Other
    public static final RegistryObject<Item> OBSIDIAN_STICK = ITEMS.register("obsidian_stick", () -> new MaterialItem(false, DARK_PURPLE, new Item.Properties().isImmuneToFire()));
    public static final RegistryObject<Item> WOODEN_ROD = ITEMS.register("wooden_rod", () -> new MaterialItem(false, GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LAVA_SHARD = ITEMS.register("lava_shard", () -> new MaterialItem(false, GOLD, new Item.Properties()));
    public static final RegistryObject<Item> FROST_SHARD = ITEMS.register("frost_shard", () -> new MaterialItem(false, AQUA, new Item.Properties()));
    public static final RegistryObject<Item> THANK_YOU_6M = ITEMS.register("thank_you_6m", ThankYouItem::new);
    //public static final RegistryObject<Item> ARMORPLUS_BOOK = ITEMS.register("guide_book", () -> new APBookItem(new Properties()));

    /**
     * This function automatically registers item(s), in groups that represent an armor set.
     * <p>
     * Utilizes the {@link APArmorProperties} and uses its properties via the {@link APArmorMaterial} object.
     * Registry names are set on sight (first we put the material's name, then we put the slot's normalized name).
     *
     * @param slot the equipment slot we will be assigning the set to, which will help distinguishing different equipment from one another.
     * @return a full registered armor set list that contains a set of all available {@link APArmorMaterial#values()} materials for that equipment slot.
     */
    public static Set<RegistryObject<APArmorItem>> registerArmorForSlot(EquipmentSlotType slot) {
        return Arrays.stream(APArmorMaterial.values())
                .map(mat -> ITEMS.register(String.format("%s_%s", mat.getName(), getNormalizedName(slot)),
                        () -> new APArmorItem(mat, slot)))
                .collect(Collectors.toSet());
    }

    /**
     * Registers each {@link APToolMaterial#values()} to have its own sword, battle axe, pickaxe and bow.
     *
     * @param swords   - Sword objects to be registered
     * @param axes     - Battle Axe objects to be registered
     * @param pickaxes - Pickaxe objects to be registered
     * @param bows     - Bow objects to be registered
     */
    public static void registerToolForType(RegistryObject<Item>[] swords, RegistryObject<Item>[] axes, RegistryObject<Item>[] pickaxes, RegistryObject<Item>[] bows) {
        IntStream.range(0, AP_TOOL_MATERIAL_LENGTH).forEach(i -> {
            APToolMaterial mat = APToolMaterial.values()[i];
            swords[i] = ITEMS.register(String.format("%s_sword", mat.getName()), () -> new APSwordItem(mat));
            axes[i] = ITEMS.register(String.format("%s_battle_axe", mat.getName()), () -> new APBattleAxeItem(mat));
            pickaxes[i] = ITEMS.register(String.format("%s_pickaxe", mat.getName()), () -> new APPickaxeItem(mat));
            bows[i] = ITEMS.register(String.format("%s_bow", mat.getName()), () -> new APBowItem(mat));
        });
    }

    /**
     * Registers the specified {@link APToolMaterial} to have its own sword, battle axe, pickaxe and bow <b>bases</b>.
     *
     * @param type - What kind of item will be registered for the base
     */
    public static RegistryObject<Item> registerBase(APToolMaterial mat, String type) {
        return ITEMS.register(String.format("%s_%s_base", mat.getName(), type), APItemBase::new);
    }

    /**
     * Registers each {@link APMaceMaterial#values()} to have its own mace.
     *
     * @param maces - Mace objects to be registered
     */
    public static void registerMaceForType(RegistryObject<Item>[] maces) {
        IntStream.range(0, AP_MACE_MAT_LENGTH).forEach(i -> {
            APMaceMaterial mat = APMaceMaterial.values()[i];
            maces[i] = ITEMS.register(String.format("%s_mace", mat.getName()), () -> new APMaceItem(mat, new Item.Properties()
                    .setISTER(() -> APMaceRenderer::new)));
        });
    }

    /**
     * @return Returns a set of all registered blocks as block items (Item forms).
     */
    public static Set<RegistryObject<BlockItem>> registerBlockItems() {
        return ModBlocks.blocks.stream()
                .map(block -> ITEMS.register(block.getId().getPath(),
                        () -> new BlockItem(block.get(), new Item.Properties().group(ArmorPlus.AP_BLOCK_GROUP))))
                .collect(Collectors.toSet());
    }

    /**
     * @param type - The material the arrow is made of
     * @return ArrowItem registry object for the specified arrow type.
     */
    public static RegistryObject<ArrowItem> registerArrow(ArrowType type) {
        return ITEMS.register(type.getItemArrowName(), () -> new APArrowItem(type));
    }

    /**
     * @param material - The material that we will be creating a base to (basically taking the material's name and adding it as a new item)
     * @return a set of item registry objects that consists of "base" items for the specified armor material.
     */
    private static Set<RegistryObject<Item>> registerArmorBases(APArmorMaterial material) {
        Stream<EquipmentSlotType> armorSlots = Arrays.stream(EquipmentSlotType.values()).filter((v) -> v.getSlotType() == ARMOR);
        return armorSlots.map(slot -> ITEMS.register(String.format("%s_%s_base", material.getName(), getNormalizedName(slot)),
                () -> (Item) new APItemBase()))
                .collect(Collectors.toSet());
    }
}