/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.util;

import com.sofodev.armorplus.common.registry.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.IntStream;

import static net.minecraft.potion.Potion.getPotionFromResourceLocation;

/**
 * @author Sokratis Fotkatzikis
 **/
public final class PotionUtils {

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, boolean ambientIn, boolean showParticlesIn) {
        if (Utils.isNotNull(potion)) {
            if (potion instanceof String) {
                entity.addPotionEffect(new PotionEffect(getPotion((String) potion), duration, amplifier, ambientIn, showParticlesIn));
            } else if (potion instanceof Potion) {
                entity.addPotionEffect(new PotionEffect((Potion) potion, duration, amplifier, ambientIn, showParticlesIn));
            }
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, boolean ambientIn, PotionType potionType) {
        if (Utils.isNotNull(potion)) {
            addPotion(entity, potion, duration, amplifier, ambientIn, potionType.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, PotionType potionType) {
        if (Utils.isNotNull(potion)) {
            addPotion(entity, potion, duration, amplifier, false, potionType.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int amplifier, PotionType potionType) {
        if (Utils.isNotNull(potion)) {
            addPotion(entity, potion, 240, amplifier, false, potionType.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, List<String> potions, List<Integer> amplifiers, PotionType potionType) {
        IntStream.range(0, potions.size()).forEach(i -> {
            String potion = potions.get(i);
            int amplifier = amplifiers.get(i);
            if (Utils.isNotNull(potion)) {
                addPotion(entity, potion, 240, amplifier, false, potionType.hasParticles());
            }
        });
    }

    public static void removePotion(EntityLivingBase entity, Object potion) {
        if (Utils.isNotNull(potion)) {
            if (potion instanceof String) {
                entity.removePotionEffect(getPotion((String) potion));
            } else if (potion instanceof Potion) {
                entity.removePotionEffect((Potion) potion);
            }
        } else {
            entity.removePotionEffect(ModPotions.EMPTY);
        }
    }

    public static String localizePotion(String resourceLocation) {
        return (Utils.isNotNull(getPotion(resourceLocation))) ? TextUtils.translatedText(getPotion(resourceLocation).getName() + ".name") : "";
    }

    public static Potion getPotion(String resourceLocation) {
        return (Utils.isNotNull(getPotionFromResourceLocation(resourceLocation))) ? getPotionFromResourceLocation(resourceLocation) : ModPotions.EMPTY;
    }

    public static Potion getPotion(ResourceLocation resourceLocation) {
        return getPotion(resourceLocation.toString());
    }

    public enum PotionType {
        GOOD(false),
        BAD(true);

        private final boolean showParticles;

        PotionType(boolean showParticles) {
            this.showParticles = showParticles;
        }

        public boolean hasParticles() {
            return showParticles;
        }
    }
}
