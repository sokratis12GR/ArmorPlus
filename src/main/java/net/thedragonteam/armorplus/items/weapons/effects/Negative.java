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
    private final String[] addNegativeEffect;
    private final int[] addNegativeEffectAmplifier;

    public Negative(OriginMaterial material) {
        this(material.weapons.enableEffects, material.weapons.addPotionEffects, material.weapons.effectLevels);
    }

    private Negative(boolean enableEffect, String[] addNegativeEffect, int[] addNegativeEffectAmplifier) {
        this.enableEffect = enableEffect;
        this.addNegativeEffect = addNegativeEffect;
        this.addNegativeEffectAmplifier = addNegativeEffectAmplifier;
    }

    @Override
    public boolean isEnabled() {
        return enableEffect;
    }

    public String[] getNegativeEffects() {
        return addNegativeEffect;
    }

    public int[] getNegativeEffectsAmplifier() {
        return addNegativeEffectAmplifier;
    }

}
