package com.sofodev.armorplus.common.items.armors.base;

import com.sofodev.armorplus.common.iface.IModdedItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnhancedArmor extends ItemArmorBase {

    public ItemEnhancedArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, slot, name, name);
    }
}
