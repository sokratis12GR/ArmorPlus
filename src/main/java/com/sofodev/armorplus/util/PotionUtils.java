/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.RegistryEvents.POTION_EMPTY;
import static com.sofodev.armorplus.util.TextUtils.translatedText;
import static com.sofodev.armorplus.util.Utils.isNotNull;
import static net.minecraftforge.registries.ForgeRegistries.POTIONS;

/**
 * @author Sokratis Fotkatzikis
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

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, boolean ambientIn, PotionValue potionValue) {
        if (isNotNull(potion)) {
            addPotion(entity, potion, duration, amplifier, ambientIn, potionValue.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int duration, int amplifier, PotionValue potionValue) {
        if (isNotNull(potion)) {
            addPotion(entity, potion, duration, amplifier, false, potionValue.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, Object potion, int amplifier, PotionValue potionValue) {
        if (isNotNull(potion)) {
            addPotion(entity, potion, 240, amplifier, false, potionValue.hasParticles());
        }
    }

    public static void addPotion(EntityLivingBase entity, List<String> potions, List<Integer> amplifiers, PotionValue potionValue) {
        IntStream.range(0, potions.size()).forEach(i -> {
            String potion = potions.get(i);
            int amplifier = amplifiers.get(i);
            if (isNotNull(potion)) {
                addPotion(entity, potion, 240, amplifier, false, potionValue.hasParticles());
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
        }
    }

    public static String localizePotion(String resourceLocation) {
        Potion potion = getPotion(resourceLocation);
        if (isNotNull(potion)) {
            return translatedText(potion.getName());
        } else {
            return "";
        }
    }

    public static Potion getPotion(ResourceLocation resourceLocation) {
        LogHelper.bigInfo("Attempting to get a potion from the given resource location: [" + resourceLocation.toString() + "]");
        Potion potion = POTIONS.getValue(resourceLocation);
        if (potion != null) {
            LogHelper.info("Attempting return potion: [" + translatedText(potion.getName()) + "]");
            return potion;
        } else {
            return POTION_EMPTY;
        }
    }

    public static Potion getPotion(String regName) {
        return getPotion(new ResourceLocation(regName));
    }

    public enum PotionValue {
        GOOD(false),
        BAD(true);

        private final boolean showParticles;

        PotionValue(boolean showParticles) {
            this.showParticles = showParticles;
        }

        public boolean hasParticles() {
            return showParticles;
        }
    }
}
