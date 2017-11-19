package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.lavainfuser.LavaInfuserManager;
import net.thedragonteam.armorplus.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfuserRecipeMaker {

    public InfuserRecipeMaker() {
    }

    public static List<LavaInfuserRecipe> getInfuserRecipes() {
        LavaInfuserManager lavaInfuserManager = LavaInfuserManager.getInstance();
        Map<ItemStack, ItemStack> infusingMap = lavaInfuserManager.getInfusingList();

        List<LavaInfuserRecipe> recipes = new ArrayList<>();

        infusingMap.forEach((input, output) -> {
            LavaInfuserRecipe recipe = new LavaInfuserRecipe(input, output);
            recipes.add(recipe);
        });

        return recipes;
    }

}