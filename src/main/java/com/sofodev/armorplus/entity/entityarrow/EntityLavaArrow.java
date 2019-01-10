/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static com.sofodev.armorplus.entity.entityarrow.ModdedArrows.lavaArrow;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityLavaArrow extends EntityModdedArrow {

    public EntityLavaArrow(World worldIn) {
        super(worldIn, lavaArrow);
    }

    public EntityLavaArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, lavaArrow);
    }

    public EntityLavaArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, lavaArrow);
    }
}