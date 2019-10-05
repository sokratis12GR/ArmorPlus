package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityInfusedLavaArrow extends EntityModdedArrow {

    public EntityInfusedLavaArrow(World worldIn) {
        super(worldIn, ModdedArrows.INFUSED_LAVA_ARROW);
    }

    public EntityInfusedLavaArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.INFUSED_LAVA_ARROW);
    }

    public EntityInfusedLavaArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.INFUSED_LAVA_ARROW);
    }
}
