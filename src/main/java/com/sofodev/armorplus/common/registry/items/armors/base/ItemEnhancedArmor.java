package com.sofodev.armorplus.common.registry.items.armors.base;

import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemEnhancedArmor extends ItemArmorBase {

    public ItemEnhancedArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, slot, name, name);
    }
}
