/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityRedstoneArrow extends EntityModdedArrow {

    public EntityRedstoneArrow(World worldIn) {
        super(worldIn, ModdedArrows.REDSTONE_ARROW);
    }

    public EntityRedstoneArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.REDSTONE_ARROW);
    }

    public EntityRedstoneArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.REDSTONE_ARROW);
    }
}