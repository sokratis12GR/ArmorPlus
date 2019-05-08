/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity;

import com.sofodev.armorplus.common.registry.tileentity.base.TileBench;

import static com.sofodev.armorplus.common.registry.ModTileEntities.WORKBENCH;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileWB extends TileBench {

    public TileWB() {
        super(WORKBENCH.type(), "workbench", 10);
    }
}