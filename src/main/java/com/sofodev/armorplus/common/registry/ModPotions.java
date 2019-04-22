/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModPotions {
    public static final Potion EMPTY = ForgeRegistries.POTIONS.getValue(setRL("empty")); //To Prevent Nulls :D
}
