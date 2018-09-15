package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class Weapon {

    private final double dmg;
    private final int dur;

    /**
     * Creates an object with the basic data needed to create a weapon
     *
     * @param dmg the damage that the weapon will inflict.
     * @param dur the durability of the weapon
     */
    public Weapon(double dmg, int dur) {
        this.dmg = dmg;
        this.dur = dur;
    }

    /**
     * @return The damage that the weapon will inflict (+4 Added by minecraft, excluded for bows)
     */
    public double getDmg() {
        return dmg;
    }

    /**
     * @return The durability that the weapon will have
     */
    public int getDur() {
        return dur;
    }
}
