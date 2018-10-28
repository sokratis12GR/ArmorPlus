/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.potions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.thedragonteam.armorplus.potions.base.PotionBaseGood;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 * <p>
 * Provides an Empty or a "null" type Potion effect that makes potion removal, setting & replacing easier.
 **/
public class PotionEmpty extends PotionBaseGood {

    public PotionEmpty() {
        super(0xffffff, "empty");
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
        return false;
    }

    @Override
    protected Potion setIconIndex(int width, int height) {
        return super.setIconIndex(width, height);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public void affectEntity(Entity source, Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
    }
}
