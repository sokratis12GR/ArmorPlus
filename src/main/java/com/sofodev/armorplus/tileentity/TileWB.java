/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.tileentity;

import com.sofodev.armorplus.tileentity.base.TileBench;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.BlockEntityTag;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

/**
 * @author Sokratis Fotkatzikis
 **/
public class TileWB extends TileBench {

    public TileWB() {
        super("workbench", 10);
    }

    public static void registerWBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileWB.class, "Items"));
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new BlockEntityTag());
    }
}