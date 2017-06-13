/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;

import static net.thedragonteam.armorplus.registry.ModBlocks.benches;

public class APBlocks {

    public static Block workbench;
    public static Block highTechBench;
    public static Block ultiTechBench;
    public static Block championBench;
    public static Block lavaInfuser;

    public APBlocks() {
    }

    public static void registerBlocks() {
        workbench = benches[0];
        highTechBench = benches[1];
        ultiTechBench = benches[2];
        championBench = benches[3];
        lavaInfuser = ModBlocks.lavaInfuser;
    }
}
