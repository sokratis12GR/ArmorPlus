package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SetWeaponProperties {

    private final WeaponProperty sword;
    private final WeaponProperty battleAxe;
    private final WeaponProperty bow;

    public SetWeaponProperties(double sdmg, double badmg, double bdmg, int dur) {
        this(new WeaponProperty(sdmg, dur), new WeaponProperty(badmg, dur), new WeaponProperty(bdmg, dur));
    }

    public SetWeaponProperties(double meleedmg, double bdmg, int dur) {
        this(new WeaponProperty(meleedmg, dur), new WeaponProperty(meleedmg + 2, dur), new WeaponProperty(bdmg, dur));
    }

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
