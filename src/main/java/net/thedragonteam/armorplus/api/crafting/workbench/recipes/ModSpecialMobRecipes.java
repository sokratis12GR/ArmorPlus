/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.ModConfig.getRD;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModSpecialMobRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (global_registry.enableChickenArmor && recipes.enableChickenArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "feather", chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
                }
                if (global_registry.enableSlimeArmor && recipes.enableSlimeArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "slimeball", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (global_registry.enableChickenArmor && recipes.enableChickenArmorRecipes) {
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenHelmet), "   ", "FFF", "E E", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenHelmet), "FFF", "E E", "   ", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenChestplate), "E E", "FEF", "FFF", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenLeggings), "EFE", "F F", "F F", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenBoots), "   ", "F F", "E E", 'F', "feather", 'E', "egg"));
                    manager.addRecipe(new WBShapedOreRecipe(getItemStack(chickenBoots), "F F", "E E", "   ", 'F', "feather", 'E', "egg"));
                }
                if (global_registry.enableSlimeArmor && recipes.enableSlimeArmorRecipes) {
                    WBRecipesHelper.createArmorRecipes(manager, "blockSlime", slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
                }
                break;
        }
    }
}