package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static com.sofodev.armorplus.common.registry.entities.entityarrow.ModdedArrows.COAL_ARROW;

public class EntityCoalArrow extends EntityModdedArrow {

    public EntityCoalArrow(World worldIn) {
        super(worldIn, COAL_ARROW);
    }

    public EntityCoalArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, COAL_ARROW);
    }

    public EntityCoalArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, COAL_ARROW);
    }
}
