/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static com.sofodev.armorplus.common.registry.entities.entityarrow.ModdedArrows.INFUSED_LAVA_ARROW;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityLavaArrow extends EntityModdedArrow {

    public EntityLavaArrow(World worldIn) {
        super(worldIn, INFUSED_LAVA_ARROW);
    }

    public EntityLavaArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, INFUSED_LAVA_ARROW);
    }

    public EntityLavaArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, INFUSED_LAVA_ARROW);
    }
}