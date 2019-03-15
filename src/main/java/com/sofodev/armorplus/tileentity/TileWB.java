/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity;

import com.sofodev.armorplus.tileentity.base.TileBench;

import static com.sofodev.armorplus.registry.ModTileEntities.WORKBENCH;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileWB extends TileBench {

    public TileWB() {
        super(WORKBENCH.type(), "workbench", 10);
    }
}