/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity;

import com.sofodev.armorplus.tileentity.base.TileBench;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileHTB extends TileBench {

    public TileHTB() {
        super("high_tech_bench", 26);
    }

    public static void registerHTBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileHTB.class, "Items"));
    }
}