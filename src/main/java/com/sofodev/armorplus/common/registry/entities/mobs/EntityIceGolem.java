/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entities.mobs;

import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 */
public class EntityIceGolem extends EntityIronGolem {

    private ResourceLocation loot = Utils.setRL("entities/ice_golem");

    EntityIceGolem(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return loot;
    }

}
