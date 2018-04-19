/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.enchantment.Enchantment;

import static net.minecraft.enchantment.Enchantment.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class EnchantmentUtils {

    public static Enchantment getEnchantment(int nameOrId) {
        return getEnchantmentByID(nameOrId);
    }

    public static Enchantment getEnchantment(String nameOrId) {
        return getEnchantmentByLocation(nameOrId);
    }

    public static int getID(Enchantment enchant) {
        return getEnchantmentID(enchant);
    }
}
