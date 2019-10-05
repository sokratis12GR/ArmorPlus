package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGuardianArrow extends EntityModdedArrow {

    public EntityGuardianArrow(World worldIn) {
        super(worldIn, ModdedArrows.GUARDIAN_ARROW);
    }

    public EntityGuardianArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.GUARDIAN_ARROW);
    }

    public EntityGuardianArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.GUARDIAN_ARROW);
    }
}
