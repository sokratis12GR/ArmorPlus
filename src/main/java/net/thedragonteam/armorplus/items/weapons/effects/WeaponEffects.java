package net.thedragonteam.armorplus.items.weapons.effects;

import net.thedragonteam.armorplus.ModConfig.RegistryConfig.OriginMaterial;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class WeaponEffects {
    private final Negative negative;
    private final Ignite ignite;

    public WeaponEffects(OriginMaterial weapons) {
        this.negative = new Negative(weapons);
        this.ignite = new Ignite(weapons);
    }

    public WeaponEffects(Negative negative, Ignite ignite) {
        this.negative = negative;
        this.ignite = ignite;
    }

    public Negative getNegative() {
        return negative;
    }

    public Ignite getIgnite() {
        return ignite;
    }
}
