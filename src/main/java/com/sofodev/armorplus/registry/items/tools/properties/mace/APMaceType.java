package com.sofodev.armorplus.registry.items.tools.properties.mace;

public enum APMaceType {
    LIGHT(4.0f, -3.0f, 8f),
    NORMAL(6.0f, -3.2f, 22f),
    HEAVY(8.0f, -3.5f, 38f),
    ;

    private final float dmg;
    private final float attackSpeed;
    private final float chargeSpeed;

    APMaceType(float dmg, float attackSpeed, float chargeSpeed) {
        this.dmg = dmg;
        this.attackSpeed = attackSpeed;
        this.chargeSpeed = chargeSpeed;
    }

    public float getDmg() {
        return dmg;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getChargeSpeed() {
        return chargeSpeed;
    }

    public static float getMaceSweepingRatio(APMaceType mat) {
        return mat == APMaceType.LIGHT ? 1 : mat == APMaceType.NORMAL ? 3 : mat == APMaceType.HEAVY ? 5 : 1;
    }

    @Override
    public String toString() {
        return "APMaceType{" +
                "dmg=" + dmg +
                ", attackSpeed=" + attackSpeed +
                ", chargeSpeed=" + chargeSpeed +
                '}';
    }
}
