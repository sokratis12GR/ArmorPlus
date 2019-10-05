package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityEmeraldArrow extends EntityModdedArrow {

    public EntityEmeraldArrow(World worldIn) {
        super(worldIn, ModdedArrows.EMERALD_ARROW);
    }

    public EntityEmeraldArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.EMERALD_ARROW);
    }

    public EntityEmeraldArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.EMERALD_ARROW);
    }
}
