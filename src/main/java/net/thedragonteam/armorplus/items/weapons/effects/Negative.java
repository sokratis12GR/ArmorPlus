package net.thedragonteam.armorplus.items.weapons.effects;

import net.thedragonteam.armorplus.ModConfig.RegistryConfig.OriginMaterial;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 * <p>
 * Provides the ability of creating multiple negative effects.
 **/
public class Negative implements IRemovable {

    private final boolean enableEffect;
    private final String[] negativeEffects;
    private final int[] negativeEffectLevels;
    private final int[] negativeEffectDurations;

    public Negative(OriginMaterial material) {
        this(material.weapons.enableEffects, material.weapons.addPotionEffects, material.weapons.effectLevels, material.weapons.effectDurations);
    }

    private Negative(boolean enableEffect, String[] negativeEffects, int[] negativeEffectLevels, int[] negativeEffectDurations) {
        this.enableEffect = enableEffect;
        this.negativeEffects = negativeEffects;
        this.negativeEffectLevels = negativeEffectLevels;
        this.negativeEffectDurations = negativeEffectDurations;
    }

    @Override
    public boolean isEnabled() {
        return enableEffect;
    }

    public String[] getNegativeEffects() {
        return negativeEffects;
    }

    public int[] getNegativeEffectLevels() {
        return negativeEffectLevels;
    }

    public int[] getNegativeEffectDurations() {
        return negativeEffectDurations;
    }
}
