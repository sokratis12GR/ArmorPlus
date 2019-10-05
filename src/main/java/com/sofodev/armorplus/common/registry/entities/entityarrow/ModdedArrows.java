/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;

import static com.sofodev.armorplus.common.registry.ModItems.*;
import static net.minecraft.init.MobEffects.*;
import static net.minecraft.util.EnumParticleTypes.*;
import static net.minecraft.util.text.TextFormatting.DARK_GRAY;
import static net.minecraft.util.text.TextFormatting.DARK_GREEN;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModdedArrows {

    public static final ArrowProperties COAL_ARROW = ArrowProperties.create(3.0, CLOUD, itemCoalArrow, BLINDNESS);
    public static final ArrowProperties LAPIS_ARROW = ArrowProperties.create(3.5, WATER_DROP, itemLapisArrow, NAUSEA);
    public static final ArrowProperties REDSTONE_ARROW = ArrowProperties.create(3.5, REDSTONE, itemRedstoneArrow, SLOWNESS);
    public static final ArrowProperties EMERALD_ARROW = ArrowProperties.create(5.0, VILLAGER_HAPPY, itemEmeraldArrow, MINING_FATIGUE);
    public static final ArrowProperties OBSIDIAN_ARROW = new ArrowProperties(6.0, SMOKE_NORMAL, itemObsidianArrow, WEAKNESS, 1);
    public static final ArrowProperties INFUSED_LAVA_ARROW = new ArrowProperties(7.0, FLAME, itemLavaArrow) {
        @Override
        public IArrow hit(EntityLivingBase living, Entity shootingEntity) {
            living.setFire(6);
            return this;
        }
    };
    public static final ArrowProperties GUARDIAN_ARROW = new ArrowProperties(10.5, WATER_WAKE, itemGuardianArrow, NAUSEA, 2);
    public static final ArrowProperties SUPER_STAR_ARROW = new ArrowProperties(10.5, CRIT_MAGIC, itemSuperStarArrow, WITHER, 2);
    public static final ArrowProperties ENDER_DRAGON_ARROW = new ArrowProperties(10.5, DRAGON_BREATH, itemEnderDragonArrow, WITHER, 4);

}
