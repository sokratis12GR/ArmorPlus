/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.createArmorRecipes;
import static net.thedragonteam.armorplus.registry.APItems.*;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case DISABLED:
                break;
            case EASY:
                if (enableCoalArmor) {
                    if (enableCoalArmorRecipes) {
                        createArmorRecipes(manager, "itemCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                    } else if (enableCharcoalCoalArmorRecipe) {
                        createArmorRecipes(manager, "itemCharcoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                    }
                }
                if (enableLapisArmor && enableLapisArmorRecipes) {
                    createArmorRecipes(manager, "gemLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
                }
                if (enableRedstoneArmor && enableRedstoneArmorRecipes) {
                    createArmorRecipes(manager, "dustRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (enableLapisArmor && enableLapisArmorRecipes) {
                    createArmorRecipes(manager, "blockLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
                }
                if (enableRedstoneArmor && enableRedstoneArmorRecipes) {
                    createArmorRecipes(manager, "blockRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
                }
                if (enableCoalArmor && enableCoalArmorRecipes) {
                    createArmorRecipes(manager, "blockCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                }
                break;
        }
    }
}