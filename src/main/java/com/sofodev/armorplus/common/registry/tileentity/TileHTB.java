/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity;

import com.sofodev.armorplus.common.registry.tileentity.base.TileBench;

import static com.sofodev.armorplus.common.registry.ModTileEntities.HIGH_TECH_BENCH;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileHTB extends TileBench {

    public TileHTB() {
        super(HIGH_TECH_BENCH.type(), "high_tech_bench", 26);
    }
}