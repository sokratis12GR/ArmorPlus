package com.sofodev.armorplus.registry.item.armor;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
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
    Supplier<Holder<ArmorMaterial>> get();

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

    default ArmorPlusConfig.IMaterialConfig config() {
        return ArmorPlus.config.enhancedMaterial();
    }
}