package com.sofodev.armorplus.common.registry.items.armors.base;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.common.registry.items.armors.ArmorMaterials.*;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.isItemRepairable;
import static net.minecraft.item.ItemStack.EMPTY;

public class ItemEnhancedArmor extends ItemArmorBase {

    private final ArmorMaterial material;

    public ItemEnhancedArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, slot, name, name);
        this.material = material;
    }

    public ItemStack getRepairStack() {
        if (material == ENHANCED_CHAIN_ARMOR || material == ENHANCED_IRON_ARMOR) {
            return new ItemStack(Items.IRON_INGOT);
        } else if (material == ENHANCED_GOLD_ARMOR) {
            return new ItemStack(Items.GOLD_INGOT);
        } else if (material == ENHANCED_DIAMOND_ARMOR) {
            return new ItemStack(Items.DIAMOND);
        }
        return EMPTY;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, this.getRepairStack());
    }
}
