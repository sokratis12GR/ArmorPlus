package net.thedragonteam.armorplus.registry;

import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserRegistry;

import static net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserRegistry.addInfusingRecipe;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * Created by sokratis12GR on 5/7/2017.
 */
public class LavaInfuserRecipes {

    public static void init() {
        new LavaInfuserRegistry();
        addInfusingRecipe(getItemStack(ModItems.lavaCrystal, 0), getItemStack(ModItems.lavaCrystal, 1), 0.1D);
        addInfusingRecipe(getItemStack(ModBlocks.blockLavaCrystal), getItemStack(ModBlocks.blockInfusedLavaCrystal), 0.2D);
        addInfusingRecipe(ModBlocks.compressedObsidian, getItemStack(ModBlocks.lavaInfusedObsidian), 0.2D);
    }
}
