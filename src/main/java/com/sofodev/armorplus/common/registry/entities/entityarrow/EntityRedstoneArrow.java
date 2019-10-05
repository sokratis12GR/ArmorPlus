package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

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
