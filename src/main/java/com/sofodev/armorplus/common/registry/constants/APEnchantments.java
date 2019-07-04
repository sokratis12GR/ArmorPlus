/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.constants;

import net.minecraft.enchantment.Enchantment;

import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.fml.common.registry.ForgeRegistries.ENCHANTMENTS;

/**
 * @author Sokratis Fotkatzikis
 **/
public class APEnchantments {

    public static Enchantment FURIOUS = ENCHANTMENTS.getValue(setRL("furious"));
    public static Enchantment LIFESTEAL = ENCHANTMENTS.getValue(setRL("lifesteal"));
    public static Enchantment ENHANCE = ENCHANTMENTS.getValue(setRL("enhanced"));
}
