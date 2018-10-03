package net.thedragonteam.armorplus.api.properties;

import net.thedragonteam.armorplus.api.properties.iface.IDurable;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class Weapon implements IDurable {

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
    @Override
    public int getDurability(boolean unbreakable) {
        return unbreakable ? -1 : dur;
    }
}
