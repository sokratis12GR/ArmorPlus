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
        if (potion != null)
            addPotion(entity, getPotion(potion), duration, amplifier, ambientIn, potionType.hasParticles());
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int duration, int amplifier, boolean ambientIn, PotionType potionType) {
        if (potion != null) addPotion(entity, potion, duration, amplifier, ambientIn, potionType.hasParticles());
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int duration, int amplifier, PotionType potionType) {
        if (potion != null) addPotion(entity, potion, duration, amplifier, false, potionType.hasParticles());
    }

    public static void addPotion(EntityLivingBase entity, String potion, int amplifier, PotionType potionType) {
        if (potion != null) addPotion(entity, getPotion(potion), 240, amplifier, false, potionType.hasParticles());
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int amplifier, PotionType potionType) {
        if (potion != null) addPotion(entity, potion, 240, amplifier, false, potionType.hasParticles());
    }

    public static void removePotion(EntityLivingBase entity, String potion) {
        entity.removePotionEffect(potion != null ? getPotion(potion) : ModPotions.EMPTY);
    }

    public static void removePotion(EntityLivingBase entity, Potion potion) {
        entity.removePotionEffect(potion != null ? potion : ModPotions.EMPTY);
    }

    public static String localizePotion(String resourceLocation) {
        return getPotion(resourceLocation) != null ? translateToLocal(getPotion(resourceLocation).getName() + ".name").trim() : "";
    }

    public static Potion getPotion(String resourceLocation) {
        return getPotionFromResourceLocation(resourceLocation) != null ? getPotionFromResourceLocation(resourceLocation) : ModPotions.EMPTY;
    }

    public enum PotionType {
        GOOD(false),
        BAD(true),;

        private final boolean showParticles;

        PotionType(boolean showParticlesIn) {
            this.showParticles = showParticlesIn;
        }

        public boolean hasParticles() {
            return showParticles;
        }
    }
}
