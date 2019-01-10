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
public class TileCB extends TileBench {

    public TileCB() {
        super("champion_bench", 82);
    }

    public static void registerCBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileCB.class, "Items"));
    }
}