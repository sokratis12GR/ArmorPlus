package com.sofodev.armorplus.registry.items.tools.properties;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Rarity;

public interface IAPTool {

    IItemTier get();

    BuffInstance[] getBuffInstances();

    String getName();

    Rarity getRarity();
}
