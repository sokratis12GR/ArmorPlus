package net.thedragonteam.armorplus.api.properties;

public class CombinedWeaponProperties {

    private final WeaponProperties sword;
    private final WeaponProperties battleAxe;
    private final WeaponProperties bow;

    public CombinedWeaponProperties(double sdmg, double badmg, double bdmg, int dur) {
        this(new WeaponProperties(sdmg, dur), new WeaponProperties(badmg, dur), new WeaponProperties(bdmg, dur));
    }

    public CombinedWeaponProperties(WeaponProperties sword, WeaponProperties battleAxe, WeaponProperties bow) {
        this.sword = sword;
        this.battleAxe = battleAxe;
        this.bow = bow;
    }

    public WeaponProperties getSword() {
        return sword;
    }

    public WeaponProperties getBattleAxe() {
        return battleAxe;
    }

    public WeaponProperties getBow() {
        return bow;
    }
}
