/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.lavainfuser;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager.getInstance;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class LavaInfuserRegistry {

    /**
     * @param input the input ItemStack
     * @param output the result of what the input ItemStack will become
     * @param exp the amount of exp that will be gained when the infusing is done
     */
    public static void addInfusingRecipe(ItemStack input, ItemStack output, double exp) {
        getInstance().addInfusingRecipe(input, output, exp);
    }

    public static void addInfusingRecipe(ItemStack input, ItemStack output) {
        getInstance().addInfusing(input, output);
    }

    public static void addInfusingRecipe(Block input, ItemStack output, double exp) {
        addInfusingRecipe(getItemStack(input), output, exp);
    }

    public static void addInfusingRecipe(Block input, ItemStack output) {
        addInfusingRecipe(input, output, 0.0D);
    }

    public static void addInfusingRecipe(Item input, ItemStack output, double exp) {
        addInfusingRecipe(getItemStack(input), output, exp);
    }

    public static void addInfusingRecipe(Item input, ItemStack output) {
        addInfusingRecipe(input, output, 0.0D);
    }


    /**
     * @param recipe {@link LavaInfuserManager#removeRecipe(ItemStack)} (IRecipe)}
     */
    public static void removeInfusingRecipe(ItemStack recipe) {
        getInstance().getInfusingList().remove(recipe);
    }
}
