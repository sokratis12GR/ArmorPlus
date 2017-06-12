/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.enchantment.Enchantment;

import static net.minecraft.enchantment.Enchantment.*;

public class EnchantmentUtils {

    public Enchantment getEnchantment(String name) {
        return getEnchantmentByLocation(name);
    }

    public Enchantment getEnchantment(int id) {
        return getEnchantmentByID(id);
    }

    public int getID(Enchantment enchant) {
        return getEnchantmentID(enchant);
    }
}
