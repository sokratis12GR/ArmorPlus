/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import static net.thedragonteam.armorplus.ARPConfig.*;

public enum ARPArmorEffects {
    COAL(new PotionEffect(
            MobEffects.NIGHT_VISION, 240, 0, true, true),
            "Night Vision"),
    EMERALD(new PotionEffect(
            MobEffects.HASTE, 120, emeraldArmorEffectlevel, true, true),
            "Haste"),
    LAPIS(new PotionEffect(
            MobEffects.WATER_BREATHING, 120, 0, true, true),
            "Water Breathing"),
    LAVA(new PotionEffect(
            MobEffects.FIRE_RESISTANCE, 120, 0, true, true),
            "Fire Resistance"),
    OBSIDIAN(new PotionEffect(
            MobEffects.RESISTANCE, 120, obsidianArmorEffectlevel, true, true),
            "Resistance"),
    REDSTONE(new PotionEffect(
            MobEffects.SPEED, 120, redstoneArmorEffectlevel, true, true),
            "Speed"),
    /*GUARDIAN(new PotionEffect(
             MobEffects.WATER_BREATHING, 120, 0, true, true),
             "Water Breathing"),
    SLIME(new PotionEffect(
             MobEffects.NIGHT_VISION, 120, 0, true, true)),
    CHICKEN(new PotionEffect(
            MobEffects.NIGHT_VISION, 120, 0, true, true)),*/
    SUPER_STAR(new PotionEffect(
            MobEffects.REGENERATION, 120, superstarArmorEffectlevel, true, true),
            "Regeneration");
    /*ARDITE(new PotionEffect(
            MobEffects.NIGHT_VISION, 120, 0, true, true)),
    COBALT(new PotionEffect(
            MobEffects.NIGHT_VISION, 120, 0, true, true)),
    MANYULLYN(new PotionEffect(
            MobEffects.NIGHT_VISION, 120, 0, true, true)),
    KNIGHT_SLIME(new PotionEffect(
            MobEffects.NIGHT_VISION, 120, 0, true, true)),
    PIG_IRON(new PotionEffect(
            MobEffects.NIGHT_VISION, 120, 0, true, true));*/

    private final PotionEffect potionEffect;
    private final String potionEffectName;

    ARPArmorEffects(PotionEffect potionEffectIn, String potionEffectNameIn) {
        this.potionEffect = potionEffectIn;
        this.potionEffectName = potionEffectNameIn;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public String getPotionEffectName() {
        return potionEffectName;
    }
}
