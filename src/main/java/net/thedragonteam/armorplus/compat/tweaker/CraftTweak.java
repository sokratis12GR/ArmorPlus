/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.compat.tweaker;


import minetweaker.MineTweakerAPI;

public class CraftTweak {

    public static void register() {
        MineTweakerAPI.registerClass(WorkbenchCrafting.class);
        MineTweakerAPI.registerClass(HighTechBenchCrafting.class);
        MineTweakerAPI.registerClass(UltiTechBenchCrafting.class);
    }
}