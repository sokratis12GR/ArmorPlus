package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ArmorProperties {

    private final double armorToughnessPoints;
    private final int headArmor;
    private final int chestArmor;
    private final int legsArmor;
    private final int feetArmor;

    public ArmorProperties(int headA, int chestA, int legsA, int feetA) {
        this(0.0, headA, chestA, legsA, feetA);
    }

    public ArmorProperties(double atPoints, int headA, int chestA, int legsA, int feetA) {
        this.armorToughnessPoints = atPoints;
        this.headArmor = headA;
        this.chestArmor = chestA;
        this.legsArmor = legsA;
        this.feetArmor = feetA;
    }

    public double getArmorToughnessPoints() {
        return this.armorToughnessPoints;
    }

    public int getHeadArmor() {
        return this.headArmor;
    }

    public int getChestArmor() {
        return this.chestArmor;
    }

    public int getLegsArmor() {
        return this.legsArmor;
    }

    public int getFeetArmor() {
        return this.feetArmor;
    }

    /**
     * @return An array of the armor points for the armor set, ordered. (Head, Chest, Legs, Feet)
     */
    public int[] getArmorPoints() {
        return new int[]{headArmor, chestArmor, legsArmor, feetArmor};
    }
}
