package net.thedragonteam.armorplus.api.properties;

public class WeaponProperties {

    private final double dmg;
    private final int dur;

    public WeaponProperties(double dmg, int dur) {
        this.dmg = dmg;
        this.dur = dur;
    }

    public double getDmg() {
        return dmg;
    }

    public int getDur() {
        return dur;
    }
}
