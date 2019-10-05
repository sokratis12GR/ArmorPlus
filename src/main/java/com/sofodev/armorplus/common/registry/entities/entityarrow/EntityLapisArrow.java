package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityLapisArrow extends EntityModdedArrow {

    public EntityLapisArrow(World worldIn) {
        super(worldIn, ModdedArrows.LAPIS_ARROW);
    }

    public EntityLapisArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.LAPIS_ARROW);
    }

    public EntityLapisArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.LAPIS_ARROW);
    }
}
