/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions.base;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class PotionBaseGood extends PotionBase {

    public PotionBaseGood(int liquidColor, String name) {
        super(false, liquidColor, name);
        this.setBeneficial();
    }
}
