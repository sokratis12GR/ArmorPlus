package net.thedragonteam.armorplus.items.weapons;

public class WeaponProperty {

    private final double dmg;
    private final int dur;

    public WeaponProperty(double dmg, int dur) {
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
