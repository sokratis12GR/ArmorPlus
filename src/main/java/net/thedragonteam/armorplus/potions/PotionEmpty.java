/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions;

import net.minecraft.potion.PotionEffect;
import net.thedragonteam.armorplus.potions.base.PotionBaseGood;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class PotionEmpty extends PotionBaseGood {

    public PotionEmpty() {
        super(0xfff, "empty");
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return false;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean isBadEffect() {
        return true;
    }
}
