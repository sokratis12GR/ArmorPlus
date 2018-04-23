package net.thedragonteam.armorplus.items.weapons.effects;

import net.thedragonteam.armorplus.ModConfig.RegistryConfig.OriginMaterial;
import net.thedragonteam.armorplus.api.properties.IRemovable;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 * <p>
 * Provides the ability to set an entity on fire, for a specified amount of time.
 * Also the ability to disable such effect
 **/
public class Ignite implements IRemovable {

    private final boolean shouldApplyFire;
    private final int fireSeconds;

    public Ignite(OriginMaterial material) {
        this(material.weapons.shouldApplyFire, material.weapons.onFireSeconds);
    }

    private Ignite(boolean shouldApplyFire, int fireSeconds) {
        this.shouldApplyFire = shouldApplyFire;
        this.fireSeconds = fireSeconds;
    }

    public int getFireSeconds() {
        return fireSeconds;
    }

    @Override
    public boolean isEnabled() {
        return shouldApplyFire;
    }
}
