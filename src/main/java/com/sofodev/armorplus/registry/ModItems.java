package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.arrows.ArrowType;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.armors.APArmorProperties;
import com.sofodev.armorplus.registry.items.arrows.APArrowItem;
import com.sofodev.armorplus.registry.items.tools.APBattleAxeItem;
import com.sofodev.armorplus.registry.items.tools.APBowItem;
import com.sofodev.armorplus.registry.items.tools.APPickaxeItem;
import com.sofodev.armorplus.registry.items.tools.APSwordItem;
import com.sofodev.armorplus.registry.items.tools.properties.APToolMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sofodev.armorplus.ArmorPlus.*;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;
import static net.minecraft.inventory.EquipmentSlotType.*;
import static net.minecraft.inventory.EquipmentSlotType.Group.ARMOR;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Bus.MOD)
public class ModItems {

    public static void registerModItems(){

    }

    //Armors
    public static final Set<RegistryObject<APArmorItem>> HELMETS = registerArmorForSlot(HEAD);
    public static final Set<RegistryObject<APArmorItem>> CHESTPLATES = registerArmorForSlot(CHEST);
    public static final Set<RegistryObject<APArmorItem>> LEGGINGS = registerArmorForSlot(LEGS);
    public static final Set<RegistryObject<APArmorItem>> BOOTS = registerArmorForSlot(FEET);

    //ArmorBases (Souless)
    public static final Set<RegistryObject<Item>> SUPER_STAR_BASES = registerArmorBases(APArmorMaterial.SUPER_STAR);
    public static final Set<RegistryObject<Item>> GUARDIAN_BASES = registerArmorBases(APArmorMaterial.GUARDIAN);
    public static final Set<RegistryObject<Item>> ENDER_DRAGON_BASES = registerArmorBases(APArmorMaterial.ENDER_DRAGON);
    public static final Set<RegistryObject<Item>> SLAYER_BASES = registerArmorBases(APArmorMaterial.SLAYER);

    //Tools & Weapons
    public static final RegistryObject<Item>[] SWORDS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] BATTLE_AXES = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] PICKAXES = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] SHOVELS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] BOWS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];

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
        registerToolForType(SWORDS, BATTLE_AXES, PICKAXES, SHOVELS, BOWS);
    }

    /**
     * This function automatically registers item(s), in groups that represent an armor set.
     * <p>
     * Utilizes the {@link APArmorProperties} and uses its properties via the {@link APArmorMaterial} object.
     * Registry names are set on sight (first we put the material's name, then we put the slot's normalized name).
     *
     * @param slot the equipment slot we will be assigning the set to, which will help distinguishing different equipment from one another.
     * @return
     */
    public static Set<RegistryObject<APArmorItem>> registerArmorForSlot(EquipmentSlotType slot) {
        return Arrays.stream(APArmorMaterial.values())
                .map(mat -> ITEMS.register(String.format("%s_%s", mat.getName(), getNormalizedName(slot)),
                        () -> new APArmorItem(mat, slot)))
                .collect(Collectors.toSet());
    }

    public static void registerToolForType(RegistryObject<Item>[] swords, RegistryObject<Item>[] axes, RegistryObject<Item>[] pickaxes, RegistryObject<Item>[] shovels, RegistryObject<Item>[] bows) {
        IntStream.range(0, AP_TOOL_MATERIAL_LENGTH).forEach(i -> {
            APToolMaterial mat = APToolMaterial.values()[i];
            swords[i] = ITEMS.register(String.format("%s_sword", mat.getName()), () -> new APSwordItem(mat));
            axes[i] = ITEMS.register(String.format("%s_battle_axe", mat.getName()), () -> new APBattleAxeItem(mat));
            pickaxes[i] = ITEMS.register(String.format("%s_pickaxe", mat.getName()), () -> new APPickaxeItem(mat));
            //shovels[i] = ITEMS.register(String.format("%s_shovel", mat.getName()), () -> new APShovelItem(mat));
            bows[i] = ITEMS.register(String.format("%s_bow", mat.getName()), () -> new APBowItem(mat));
        });
    }

    public static Set<RegistryObject<BlockItem>> registerBlockItems() {
        return ModBlocks.blocks.stream()
                .map(block -> ITEMS.register(block.getId().getPath(),
                        () -> new BlockItem(block.get(), new Item.Properties().group(ArmorPlus.AP_BLOCK_GROUP))))
                .collect(Collectors.toSet());
    }

    public static RegistryObject<ArrowItem> registerArrow(ArrowType type) {
        return ITEMS.register(type.getItemArrowName(), () -> new APArrowItem(type));
    }

    private static Set<RegistryObject<Item>> registerArmorBases(APArmorMaterial material) {
        Stream<EquipmentSlotType> armorSlots = Arrays.stream(values()).filter((v) -> v.getSlotType() == ARMOR);
        return armorSlots.map(slot -> ITEMS.register(String.format("%s_%s_base", material.getName(), getNormalizedName(slot)),
                () -> new Item(new Item.Properties().group(AP_ITEM_GROUP).rarity(Rarity.UNCOMMON).maxStackSize(8))))
                .collect(Collectors.toSet());
    }
}