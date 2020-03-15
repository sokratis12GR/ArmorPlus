package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.armors.APArmorProperties;
import com.sofodev.armorplus.registry.items.tools.APBattleAxeItem;
import com.sofodev.armorplus.registry.items.tools.APPickaxeItem;
import com.sofodev.armorplus.registry.items.tools.APShovelItem;
import com.sofodev.armorplus.registry.items.tools.APSwordItem;
import com.sofodev.armorplus.registry.items.tools.properties.APToolMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.*;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;

@ObjectHolder(ArmorPlus.MODID)
@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Bus.MOD)
public class ModItems {

    public static final RegistryObject<Item>[] HELMETS = new RegistryObject[AP_ARMOR_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] CHESTPLATES = new RegistryObject[AP_ARMOR_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] LEGGINGS = new RegistryObject[AP_ARMOR_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] BOOTS = new RegistryObject[AP_ARMOR_MATERIAL_LENGTH];

    public static final RegistryObject<Item>[] SWORDS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] BATTLE_AXES = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] PICKAXES = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];
    public static final RegistryObject<Item>[] SHOVELS = new RegistryObject[AP_TOOL_MATERIAL_LENGTH];

    static {
        registerArmorForSlot(HELMETS, EquipmentSlotType.HEAD);
        registerArmorForSlot(CHESTPLATES, EquipmentSlotType.CHEST);
        registerArmorForSlot(LEGGINGS, EquipmentSlotType.LEGS);
        registerArmorForSlot(BOOTS, EquipmentSlotType.FEET);
        registerToolForType(SWORDS, BATTLE_AXES, PICKAXES, SHOVELS);
    }

    /**
     * This is a quick function that automatically registers item(s), in groups that represent an armor set.
     * <p>
     * Utilizes the {@link APArmorProperties} and uses its properties via the {@link APArmorMaterial} object.
     * Registry names are set on sight (first we put the material's name, then we put the slot's normalized name).
     *
     * @param set  the array set that we will be using
     * @param slot the equipment slot we will be assigning the set to, which will help distinguishing different equipment from one another.
     */
    public static void registerArmorForSlot(RegistryObject<Item>[] set, EquipmentSlotType slot) {
        IntStream.range(0, set.length).forEach(i -> {
            APArmorMaterial mat = APArmorMaterial.values()[i];
            set[i] = ITEMS.register(String.format("%s_%s", mat.getName(), getNormalizedName(slot)),
                () -> new APArmorItem(mat, slot));
        });
    }

    public static void registerToolForType(RegistryObject<Item>[] swords, RegistryObject<Item>[] axes, RegistryObject<Item>[] pickaxes, RegistryObject<Item>[] shovels) {
        IntStream.range(0, AP_TOOL_MATERIAL_LENGTH).forEach(i -> {
            APToolMaterial mat = APToolMaterial.values()[i];
            swords[i] = ITEMS.register(String.format("%s_sword", mat.getName()), () -> new APSwordItem(mat));
            axes[i] = ITEMS.register(String.format("%s_battle_axe", mat.getName()), () -> new APBattleAxeItem(mat));
            pickaxes[i] = ITEMS.register(String.format("%s_pickaxe", mat.getName()), () -> new APPickaxeItem(mat));
            shovels[i] = ITEMS.register(String.format("%s_shovel", mat.getName()), () -> new APShovelItem(mat));
        });
    }
}
