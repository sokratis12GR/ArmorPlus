package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Rarity;

public interface IAPTool {

    /**
     * @return The IITemTier properties of the tool
     */
    IItemTier get();

    /**
     * A list of all the BuffInstances for the tool
     *
     * A tool can contain multiple buff instances, which means it can utilize many custom effects.
     */
    BuffInstance[] getBuffInstances();

    /**
     * @return The name of the tool
     */
    String getName();

    /**
     * @return The rarity of the tool, (i.e the color of its name)
     */
    Rarity getRarity();

}