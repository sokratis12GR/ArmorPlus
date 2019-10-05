package com.sofodev.armorplus.common.registry.entities.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityObsidianArrow extends EntityModdedArrow {

    public EntityObsidianArrow(World worldIn) {
        super(worldIn, ModdedArrows.OBSIDIAN_ARROW);
    }

    public EntityObsidianArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, ModdedArrows.OBSIDIAN_ARROW);
    }

    public EntityObsidianArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, ModdedArrows.OBSIDIAN_ARROW);
    }
}
