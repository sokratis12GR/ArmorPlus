/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.thedragonteam.armorplus.registry.ModPotions;

import static net.minecraft.potion.Potion.getPotionFromResourceLocation;
import static net.minecraft.util.text.translation.I18n.translateToLocal;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class PotionUtils {


    public static void addPotion(EntityLivingBase entity, String potion, int duration, int amplifier, boolean ambientIn, boolean showParticlesIn) {
        if (potion != null)
            entity.addPotionEffect(new PotionEffect(getPotion(potion), duration, amplifier, ambientIn, showParticlesIn));
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int duration, int amplifier, boolean ambientIn, boolean showParticlesIn) {
        if (potion != null)
            entity.addPotionEffect(new PotionEffect(potion, duration, amplifier, ambientIn, showParticlesIn));
    }

    public static void addPotion(EntityLivingBase entity, String potion, int duration, int amplifier, boolean ambientIn, PotionType potionType) {
        switch (potionType) {
            case GOOD:
                if (potion != null)
                    addPotion(entity, getPotion(potion), duration, amplifier, ambientIn, false);
                break;
            case BAD:
                if (potion != null)
                    addPotion(entity, getPotion(potion), duration, amplifier, ambientIn, true);
                break;
        }
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int duration, int amplifier, boolean ambientIn, PotionType potionType) {
        switch (potionType) {
            case GOOD:
                if (potion != null)
                    addPotion(entity, potion, duration, amplifier, ambientIn, false);
                break;
            case BAD:
                if (potion != null)
                    addPotion(entity, potion, duration, amplifier, ambientIn, true);
                break;
        }
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int duration, int amplifier, PotionType potionType) {
        switch (potionType) {
            case GOOD:
                if (potion != null)
                    addPotion(entity, potion, duration, amplifier, false, false);
                break;
            case BAD:
                if (potion != null)
                    addPotion(entity, potion, duration, amplifier, false, true);
                break;
        }
    }

    public static void addPotion(EntityLivingBase entity, String potion, int amplifier, PotionType potionType) {
        switch (potionType) {
            case GOOD:
                if (potion != null)
                    addPotion(entity, getPotion(potion), 120, amplifier, false, false);
                break;
            case BAD:
                if (potion != null)
                    addPotion(entity, getPotion(potion), 120, amplifier, false, true);
                break;
        }
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int amplifier, PotionType potionType) {
        switch (potionType) {
            case GOOD:
                if (potion != null)
                    addPotion(entity, potion, 120, amplifier, false, false);
                break;
            case BAD:
                if (potion != null)
                    addPotion(entity, potion, 120, amplifier, false, true);
                break;
        }
    }

    public static void removePotion(EntityLivingBase entity, String potion) {
        if (potion != null)
            entity.removePotionEffect(getPotion(potion));
    }

    public static void removePotion(EntityLivingBase entity, Potion potion) {
        entity.removePotionEffect(potion);
    }

    public static String localizePotion(String resourceLocation) {
        return getPotion(resourceLocation) != null ? translateToLocal(getPotion(resourceLocation).getName() + ".name").trim() : "";
    }

    public static Potion getPotion(String resourceLocation) {
        return getPotionFromResourceLocation(resourceLocation) != null ? getPotionFromResourceLocation(resourceLocation) : ModPotions.EMPTY;
    }

    public enum PotionType {
        GOOD,
        BAD
    }
}
