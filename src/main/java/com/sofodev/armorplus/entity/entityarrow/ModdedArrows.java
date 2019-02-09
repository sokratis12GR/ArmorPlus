/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;

import static com.sofodev.armorplus.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static net.minecraft.util.EnumParticleTypes.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModdedArrows {

    public static final ArrowProperties COAL_ARROW = new ArrowProperties(3.0, CLOUD, ModItems.itemCoalArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.BLINDNESS, 180, 0, BAD);
        }
    };
    public static final ArrowProperties LAPIS_ARROW = new ArrowProperties(3.5, WATER_DROP, ModItems.itemLapisArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.NAUSEA, 180, 0, BAD);
        }
    };
    public static final ArrowProperties REDSTONE_ARROW = new ArrowProperties(3.5, REDSTONE, ModItems.itemRedstoneArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.SLOWNESS, 180, 0, BAD);
        }
    };
    public static final ArrowProperties INFUSED_LAVA_ARROW = new ArrowProperties(5.5, FLAME, ModItems.itemLavaArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) living.setFire(6);
        }
    };
    public static final ArrowProperties ENDER_DRAGON_ARROW = new ArrowProperties(10.5, DRAGON_BREATH, ModItems.itemEnderDragonArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.WITHER, 180, 4, BAD);
        }
    };

}
