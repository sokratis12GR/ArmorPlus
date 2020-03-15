package com.sofodev.armorplus.registry.items.tools.properties;

public enum APToolType {
    SWORD(2f, -1.5f),
    BATTLE_AXE(4.0f, -3.0f),
    PICKAXE(0.5f, -2.8f),
    SHOVEL(1.0f, -3f)
    ;

    private final float dmg;
    private final float attackSpeed;

    APToolType(float dmg, float attackSpeed) {
        this.dmg = dmg;
        this.attackSpeed = attackSpeed;
    }

    public float getDmg() {
        return dmg;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public String getName() {
        return this.name().toLowerCase();
    }
}
