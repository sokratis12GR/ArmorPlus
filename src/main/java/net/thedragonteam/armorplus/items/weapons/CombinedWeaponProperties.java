package net.thedragonteam.armorplus.items.weapons;

public class CombinedWeaponProperties {

    private final WeaponProperty sword;
    private final WeaponProperty battleAxe;
    private final WeaponProperty bow;

    public CombinedWeaponProperties(double sdmg, double badmg, double bdmg, int dur) {
        this(new WeaponProperty(sdmg, dur), new WeaponProperty(badmg, dur), new WeaponProperty(bdmg, dur));
    }

    public CombinedWeaponProperties(WeaponProperty sword, WeaponProperty battleAxe, WeaponProperty bow) {
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
