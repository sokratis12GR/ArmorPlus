/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity;

import com.sofodev.armorplus.common.registry.tileentity.base.TileBench;

import static com.sofodev.armorplus.common.registry.ModTileEntities.ULTI_TECH_BENCH;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileUTB extends TileBench {

    public TileUTB() {
        super(ULTI_TECH_BENCH.type(), "ulti_tech_bench", 50);
    }
}