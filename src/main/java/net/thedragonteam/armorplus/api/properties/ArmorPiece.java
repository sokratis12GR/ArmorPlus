package net.thedragonteam.armorplus.api.properties;

import net.thedragonteam.armorplus.api.properties.iface.IArmor;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ArmorPiece implements IArmor {

    private final int armor;

    public ArmorPiece(int armor) {
        this.armor = armor;
    }

    public static ArmorPiece create(int armor) {
        return new ArmorPiece(armor);
    }

    @Override
    public int getArmor() {
        return this.armor;
    }
}
