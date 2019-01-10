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
public class TileUTB extends TileBench {

    public TileUTB() {
        super("ulti_tech_bench", 50);
    }

    public static void registerUTBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileUTB.class, "Items"));
    }
}