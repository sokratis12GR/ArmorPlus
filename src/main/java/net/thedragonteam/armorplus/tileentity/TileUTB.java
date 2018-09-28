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
public class TileUTB extends TileBench {

    public TileUTB() {
        super("ulti_tech_bench", 50);
    }

    public static void registerUTBFixes(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileUTB.class, "Items"));
    }
}