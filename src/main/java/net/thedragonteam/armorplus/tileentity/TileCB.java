/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.thedragonteam.armorplus.tileentity.base.TileBench;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class TileCB extends TileBench {

    public TileCB() {
        super("champion_bench", 82);
    }

    public static void registerCBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileCB.class, "Items"));
    }
}