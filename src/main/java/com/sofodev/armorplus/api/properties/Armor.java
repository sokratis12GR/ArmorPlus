/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis
 */
public class Armor {

    private final double toughnessPoints;
    private final int headArmor;
    private final int chestArmor;
    private final int legsArmor;
    private final int feetArmor;

    public Armor(int head, int chest, int legs, int feet) {
        this(0.0, head, chest, legs, feet);
    }

    public Armor(double toughness, int head, int chest, int legs, int feet) {
        this(toughness, ArmorPiece.create(head), ArmorPiece.create(chest), ArmorPiece.create(legs), ArmorPiece.create(feet));
    }

    public Armor(double toughness, ArmorPiece head, ArmorPiece chest, ArmorPiece legs, ArmorPiece feet) {
        this.toughnessPoints = toughness;
        this.headArmor = head.getArmor();
        this.chestArmor = chest.getArmor();
        this.legsArmor = legs.getArmor();
        this.feetArmor = feet.getArmor();
    }

    public double getToughnessPoints() {
        return this.toughnessPoints;
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
        return new int[]{this.getHeadArmor(), this.getChestArmor(), this.getLegsArmor(), this.getFeetArmor()};
    }
}
