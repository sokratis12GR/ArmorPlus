/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static com.sofodev.armorplus.common.registry.entity.entityarrow.ModdedArrows.LAPIS_ARROW;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityLapisArrow extends EntityModdedArrow {

    public EntityLapisArrow(World worldIn) {
        super(worldIn, LAPIS_ARROW);
    }

    public EntityLapisArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, LAPIS_ARROW);
    }

    public EntityLapisArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, LAPIS_ARROW);
    }
}