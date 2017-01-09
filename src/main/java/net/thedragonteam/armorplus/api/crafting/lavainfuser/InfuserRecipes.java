/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class InfuserRecipes {

    public void addRecipes(LavaInfuserCraftingManager manager) {
        manager.addShapelessRecipe(getItemStack(lavaCrystal, 1), lavaCrystal);
    }
}
