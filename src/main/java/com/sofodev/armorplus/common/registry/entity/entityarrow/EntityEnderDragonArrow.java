/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityEnderDragonArrow extends EntityModdedArrow {

    public EntityEnderDragonArrow(World worldIn) {
        super(worldIn, ModdedArrows.ENDER_DRAGON_ARROW);
    }

    public EntityEnderDragonArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.ENDER_DRAGON_ARROW);
    }

    public EntityEnderDragonArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.ENDER_DRAGON_ARROW);
    }
}