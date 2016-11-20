/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.multiblock;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.Map;

public class MultiBlockSet {

    private final Map<EnumFacing, MultiBlock> mbs;

    public MultiBlockSet(MultiBlock mb) {
        mbs = Collections.unmodifiableMap(mb.createRotations());
    }

    public MultiBlock getForEntity(Entity e) {
        return getForFacing(e.getHorizontalFacing());
    }

    public MultiBlock getForFacing(EnumFacing facing) {
        return mbs.get(facing);
    }

}
