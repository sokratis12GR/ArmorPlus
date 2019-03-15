/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity;

import com.sofodev.armorplus.tileentity.base.TileBench;

import static com.sofodev.armorplus.registry.ModTileEntities.CHAMPION_BENCH;


/**
 * @author Sokratis Fotkatzikis
 **/
public class TileCB extends TileBench {

    public TileCB() {
        super(CHAMPION_BENCH.type(), "champion_bench", 82);
    }
}