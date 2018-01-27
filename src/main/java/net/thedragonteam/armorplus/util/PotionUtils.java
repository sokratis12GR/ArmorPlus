/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.thedragonteam.armorplus.registry.ModPotions;

import java.util.List;
import java.util.stream.IntStream;

import static net.minecraft.potion.Potion.getPotionFromResourceLocation;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.Utils.isNotNull;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public final class PotionUtils {

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, boolean ambientIn, boolean showParticlesIn) {
        if (isNotNull(potion)) {
            if (potion instanceof String) {
                entity.addPotionEffect(new PotionEffect(getPotion((String) potion), duration, amplifier, ambientIn, showParticlesIn));
            } else if (potion instanceof Potion) {
                entity.addPotionEffect(new PotionEffect((Potion) potion, duration, amplifier, ambientIn, showParticlesIn));
            }
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, boolean ambientIn, PotionType potionType) {
        if (isNotNull(potion)) {
            addPotion(entity, potion, duration, amplifier, ambientIn, potionType.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, PotionType potionType) {
        if (isNotNull(potion)) {
            addPotion(entity, potion, duration, amplifier, false, potionType.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int amplifier, PotionType potionType) {
        if (isNotNull(potion)) {
            addPotion(entity, potion, 240, amplifier, false, potionType.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, List<String> potions, List<Integer> amplifiers, PotionType potionType) {
        IntStream.range(0, potions.size()).forEach(i -> {
            String potion = potions.get(i);
            int amplifier = amplifiers.get(i);
            if (isNotNull(potion)) {
                addPotion(entity, potion, 240, amplifier, false, potionType.hasParticles());
            }
        });
    }

    public static void removePotion(EntityLivingBase entity, Object potion) {
        if (isNotNull(potion)) {
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
        return (isNotNull(getPotion(resourceLocation))) ? formattedText(getPotion(resourceLocation).getName() + ".name") : "";
    }

    public static Potion getPotion(String resourceLocation) {
        return (isNotNull(getPotionFromResourceLocation(resourceLocation))) ? getPotionFromResourceLocation(resourceLocation) : ModPotions.EMPTY;
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
