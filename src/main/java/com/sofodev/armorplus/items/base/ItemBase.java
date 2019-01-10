/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.items.enums.MetalItems;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemBase extends Item implements IModdedItem {

    public ItemBase(String itemName) {
        this.setRegistryName(Utils.setRL(itemName));
        this.setTranslationKey(Utils.setName(itemName));
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    public ItemBase(MetalItems item) {
        this(item.getName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0);
    }
}