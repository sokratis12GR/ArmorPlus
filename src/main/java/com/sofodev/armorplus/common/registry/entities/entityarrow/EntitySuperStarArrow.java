package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySuperStarArrow extends EntityModdedArrow {

    public EntitySuperStarArrow(World worldIn) {
        super(worldIn, ModdedArrows.SUPER_STAR_ARROW);
    }

    public EntitySuperStarArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.SUPER_STAR_ARROW);
    }

    public EntitySuperStarArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.SUPER_STAR_ARROW);
    }
}
