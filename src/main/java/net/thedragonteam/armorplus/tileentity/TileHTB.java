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
public class TileHTB extends TileBench {

    public TileHTB() {
        super("high_tech_bench",26);
    }

    public static void registerHTBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileHTB.class, "Items"));
    }
}