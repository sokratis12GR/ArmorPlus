/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.ModConfig.getRD;
import static net.thedragonteam.armorplus.api.crafting.workbench.recipes.WBRecipesHelper.createArmorRecipes;
import static net.thedragonteam.armorplus.registry.APItems.*;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case DISABLED:
                break;
            case EASY:
                if (global_registry.enableCoalArmor) {
                    if (recipes.enableCoalArmorRecipes) {
                        createArmorRecipes(manager, "itemCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                    } else if (recipes.enableCharcoalCoalArmorRecipe) {
                        createArmorRecipes(manager, "itemCharcoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                    }
                }
                if (global_registry.enableLapisArmor && recipes.enableLapisArmorRecipes) {
                    createArmorRecipes(manager, "gemLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
                }
                if (global_registry.enableRedstoneArmor && recipes.enableRedstoneArmorRecipes) {
                    createArmorRecipes(manager, "dustRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (global_registry.enableLapisArmor && recipes.enableLapisArmorRecipes) {
                    createArmorRecipes(manager, "blockLapis", lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
                }
                if (global_registry.enableRedstoneArmor && recipes.enableRedstoneArmorRecipes) {
                    createArmorRecipes(manager, "blockRedstone", redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
                }
                if (global_registry.enableCoalArmor && recipes.enableCoalArmorRecipes) {
                    createArmorRecipes(manager, "blockCoal", coalHelmet, coalChestplate, coalLeggings, coalBoots);
                }
                break;
        }
    }
}