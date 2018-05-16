package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SetWeaponProperties {

    private final WeaponProperty sword;
    private final WeaponProperty battleAxe;
    private final WeaponProperty bow;

    public SetWeaponProperties(double meleedmg, double bdmg, int dur) {
        this(meleedmg, meleedmg + 2, bdmg, dur);
    }

    public SetWeaponProperties(double sdmg, double badmg, double bdmg, int dur) {
        this(new WeaponProperty(sdmg, dur), new WeaponProperty(badmg, dur), new WeaponProperty(bdmg, dur));
    }

    /**
     * Creates a set of weapons for the type with the specified values which are the sword, battle axe and the bow
     *
     * @param sword     The weapon properties for the sword
     * @param battleAxe The weapon properties for the battle axe
     * @param bow       The weapon properties for the bow
     */
    public SetWeaponProperties(WeaponProperty sword, WeaponProperty battleAxe, WeaponProperty bow) {
        this.sword = sword;
        this.battleAxe = battleAxe;
        this.bow = bow;
    }

    public WeaponProperty getSword() {
        return sword;
    }

    public WeaponProperty getBattleAxe() {
        return battleAxe;
    }

    public WeaponProperty getBow() {
        return bow;
    }
}
