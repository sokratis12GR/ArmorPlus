/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class WeaponSet {

    private final Weapon sword;
    private final Weapon battleAxe;
    private final Weapon bow;

    public WeaponSet(double meleedmg, double bdmg, int dur) {
        this(meleedmg, meleedmg + 2, bdmg, dur);
    }

    public WeaponSet(double sdmg, double badmg, double bdmg, int dur) {
        this(new Weapon(sdmg, dur), new Weapon(badmg, dur), new Weapon(bdmg, dur));
    }

    /**
     * Creates a set of weapons for the type with the specified values which are the sword, battle axe and the bow
     *
     * @param sword     The weapon properties for the sword
     * @param battleAxe The weapon properties for the battle axe
     * @param bow       The weapon properties for the bow
     */
    public WeaponSet(Weapon sword, Weapon battleAxe, Weapon bow) {
        this.sword = sword;
        this.battleAxe = battleAxe;
        this.bow = bow;
    }

    public Weapon getSword() {
        return sword;
    }

    public Weapon getBattleAxe() {
        return battleAxe;
    }

    public Weapon getBow() {
        return bow;
    }
}
