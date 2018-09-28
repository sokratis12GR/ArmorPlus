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
    private final String[] effects;
    private final int[] effectLevels;
    private final int[] effectDurations;

    public Negative(OriginMaterial material) {
        this(material.weapons.enableEffects, material.weapons.addPotionEffects, material.weapons.effectLevels, material.weapons.effectDurations);
    }

    private Negative(boolean enableEffect, String[] effects, int[] effectLevels, int[] effectDurations) {
        this.enableEffect = enableEffect;
        this.effects = effects;
        this.effectLevels = effectLevels;
        this.effectDurations = effectDurations;
    }

    @Override
    public boolean isEnabled() {
        return enableEffect;
    }

    public String[] getEffects() {
        return effects;
    }

    public int[] getEffectLevels() {
        return effectLevels;
    }

    public int[] getEffectDurations() {
        return effectDurations;
    }
}
