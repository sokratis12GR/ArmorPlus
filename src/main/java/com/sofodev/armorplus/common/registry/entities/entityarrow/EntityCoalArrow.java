/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityCoalArrow extends EntityModdedArrow {

    public EntityCoalArrow(World worldIn) {
        super(worldIn, ModdedArrows.COAL_ARROW);
    }

    public EntityCoalArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.COAL_ARROW);
    }

    public EntityCoalArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.COAL_ARROW);
    }
}