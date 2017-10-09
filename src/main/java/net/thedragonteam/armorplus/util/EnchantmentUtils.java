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

    public Enchantment getEnchantment(Object nameOrId) {
        if (nameOrId instanceof Integer) {
            return getEnchantmentByID((Integer) nameOrId);
        } else if (nameOrId instanceof String) {
            return getEnchantmentByLocation((String) nameOrId);
        }
        return null;
    }

    public int getID(Enchantment enchant) {
        return getEnchantmentID(enchant);
    }
}
