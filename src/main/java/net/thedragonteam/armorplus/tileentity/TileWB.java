/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.BlockEntityTag;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.thedragonteam.armorplus.tileentity.base.TileBench;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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