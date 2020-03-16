package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public interface IAPArmor {

    String getName();

    IArmorMaterial get();

    TextFormatting getFormatting();

    Item.Properties getProperties();

    BuffInstance[] getBuffInstances();
}
