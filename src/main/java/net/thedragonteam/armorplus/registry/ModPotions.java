/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.potions.PotionEmpty;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class ModPotions {

    public static final Potion EMPTY = new PotionEmpty(); //To Prevent Nulls :D

    public static void registerPotions() {
        GameRegistry.register(new PotionEmpty());
    }

}
