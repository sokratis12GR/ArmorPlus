/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.armors.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.createPieces;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemArmorBase extends ItemArmor implements IModdedItem {

    private final String name;

    public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, String folderName) {
        super(material, 0, slot);
        this.name = folderName;
        this.setMaxStackSize(1);
        createPieces(this, slot, name);
        this.setCreativeTab(ArmorPlus.tabArmorPlus);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(name);
    }
}
