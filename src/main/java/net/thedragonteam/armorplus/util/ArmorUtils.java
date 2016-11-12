/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ArmorUtils {
    public static void addArmorEffect(EntityPlayer entity, Potion potion, int duration, int amplifier, boolean ambientIn, boolean showParticlesIn) {
        entity.addPotionEffect(new PotionEffect(potion, duration, amplifier, ambientIn, showParticlesIn));
    }

    public static void addArmorEffect(EntityPlayer entity, Potion potion, int duration, int amplifier, boolean ambientIn) {
        addArmorEffect(entity, potion, duration, amplifier, ambientIn, false);
    }

    public static void addArmorEffect(EntityPlayer entity, Potion potion, int duration, int amplifier) {
        addArmorEffect(entity, potion, duration, amplifier, false, false);
    }

    public static void addArmorEffect(EntityPlayer entity, Potion potion, int amplifier) {
        addArmorEffect(entity, potion, 120, amplifier);
    }

    public static void removeArmorEffect(EntityPlayer entity, Potion potion) {
        entity.removePotionEffect(potion);
    }
}
