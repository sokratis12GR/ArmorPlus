/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;

import static com.sofodev.armorplus.ArmorPlus.RegistryEvents.*;
import static com.sofodev.armorplus.util.PotionUtils.PotionValue.BAD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.thedragonlib.util.ItemStackUtils.getItem;
import static net.minecraft.init.Particles.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModdedArrows {

    public static final ArrowProperties COAL_ARROW = new ArrowProperties(ENTITY_COAL_ARROW, 3.0, CLOUD, getItem("coal_arrow")) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.BLINDNESS, 180, 0, BAD);
        }
    };
    public static final ArrowProperties LAPIS_ARROW = new ArrowProperties(ENTITY_LAPIS_ARROW, 3.5, SPLASH, getItem("lapis_arrow")) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.NAUSEA, 180, 0, BAD);
        }
    };
    public static final ArrowProperties REDSTONE_ARROW = new ArrowProperties(ENTITY_REDSTONE_ARROW, 3.5, EFFECT, getItem("redstone_arrow")) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.SLOWNESS, 180, 0, BAD);
        }
    };
    public static final ArrowProperties INFUSED_LAVA_ARROW = new ArrowProperties(ENTITY_INFUSED_LAVA_ARROW, 5.5, FLAME, getItem("lava_arrow")) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) living.setFire(6);
        }
    };
    public static final ArrowProperties ENDER_DRAGON_ARROW = new ArrowProperties(ENTITY_ENDER_DRAGON_ARROW, 10.5, DRAGON_BREATH, getItem("ender_dragon_arrow")) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.WITHER, 180, 4, BAD);
        }
    };

}
