package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

public interface IAPArmor {

    /**
     * @return The name of the armor piece
     * <p>
     * Output would be something like this, if we use `coal`
     * `coal_helmet`, `coal_chestplate`, `coal_leggings`, `coal_boots`
     */
    String getName();

    /**
     * @return The IArmorMaterial of the armor set
     */
    ArmorMaterial get();

    /**
     * @return ChatFormatting colour for the rarity (item name) color
     */
    ChatFormatting getFormatting();

    /**
     * The item properties of each of the item armor piece, combined all and modified at once.
     */
    Item.Properties getProperties();

    boolean isImmuneToFire();

    /**
     * A list of all the BuffInstances for the armor set
     * <p>
     * An armor set can contain multiple buff instances, which means it can utilize many custom effects.
     */
    Supplier<List<BuffInstance>> getBuffInstances();

    default ArmorPlusConfig.MaterialConfig config() {
        return ArmorPlusConfig.enhancedMaterial;
    }
}