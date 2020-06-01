package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public interface IAPArmor {

    /**
     * @return The name of the armor piece
     *
     * Output would be something like this, if we use `coal`
     * `coal_helmet`, `coal_chestplate`, `coal_leggings`, `coal_boots`
     */
    String getName();

    /**
     * @return The IArmorMaterial of the armor set
     */
    IArmorMaterial get();

    /**
     * @return TextFormatting colour for the rarity (item name) color
     */
    TextFormatting getFormatting();

    /**
     * The item properties of each of the item armor piece, combined all and modified at once.
     */
    Item.Properties getProperties();

    /**
     * A list of all the BuffInstances for the armor set
     *
     * An armor set can contain multiple buff instances, which means it can utilize many custom effects.
     */
    BuffInstance[] getBuffInstances();
}
