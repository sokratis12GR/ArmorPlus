/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.entitygolem;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nullable;

public class EntityIceGolem extends EntityIronGolem {

    public static final ResourceLocation LOOT = new ResourceLocation(ArmorPlus.MODID, "entities/ice_golem");

    public EntityIceGolem(World worldIn) {
        super(worldIn);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }
}
