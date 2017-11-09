/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.WBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(WorkbenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (enableSwordsRecipes) {
                    if (enableCoalWeapons[0])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalSword), " C ", " C ", " S ", 'C', "itemCoal", 'S', "stickWood"));
                    if (enableLapisWeapons[0])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisSword), " L ", " L ", " S ", 'L', "gemLapis", 'S', "stickWood"));
                    if (enableRedstoneWeapons[0])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneSword), " R ", " R ", " S ", 'R', "dustRedstone", 'S', "stickWood"));
                }
                if (enableBattleAxesRecipes) {
                    if (enableCoalWeapons[1])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalBattleAxe), "C C", "CSC", " S ", 'C', "itemCoal", 'S', "stickWood"));
                    if (enableLapisWeapons[1])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisBattleAxe), "L L", "LSL", " S ", 'L', "gemLapis", 'S', "stickWood"));
                    if (enableRedstoneWeapons[1])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneBattleAxe), "R R", "RSR", " S ", 'R', "dustRedstone", 'S', "stickWood"));
                }
                if (enableBowsRecipes) {
                    if (enableCoalWeapons[2]) {
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalBow), " CS", "C S", " CS", 'C', "itemCoal", 'S', "string"));
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalBow), "SC ", "S C", "SC ", 'C', "itemCoal", 'S', "string"));
                    }
                    if (enableLapisWeapons[2]) {
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisBow), " LS", "L S", " LS", 'L', "gemLapis", 'S', "string"));
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisBow), "SL ", "S L", "SL ", 'L', "gemLapis", 'S', "string"));
                    }
                    if (enableRedstoneWeapons[2]) {
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneBow), " RS", "R S", " RS", 'R', "dustRedstone", 'S', "string"));
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneBow), "SR ", "S R", "SR ", 'R', "dustRedstone", 'S', "string"));
                    }
                }
                break;
            case EXPERT:
            case HELLISH:
                if (enableSwordsRecipes) {
                    if (enableCoalWeapons[0])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalSword), " C ", " C ", " S ", 'C', "blockCoal", 'S', "stickWood"));
                    if (enableLapisWeapons[0])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisSword), " L ", " L ", " S ", 'L', "blockLapis", 'S', "stickWood"));
                    if (enableRedstoneWeapons[0])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneSword), " R ", " R ", " S ", 'R', "blockRedstone", 'S', "stickWood"));
                }
                if (enableBattleAxesRecipes) {
                    if (enableCoalWeapons[1])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalBattleAxe), "C C", "CSC", " S ", 'C', "blockCoal", 'S', "stickWood"));
                    if (enableLapisWeapons[1])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisBattleAxe), "L L", "LSL", " S ", 'L', "blockLapis", 'S', "stickWood"));
                    if (enableRedstoneWeapons[1])
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneBattleAxe), "R R", "RSR", " S ", 'R', "blockRedstone", 'S', "stickWood"));
                }
                if (enableBowsRecipes) {
                    if (enableCoalWeapons[2]) {
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalBow), " CS", "C S", " CS", 'C', "blockCoal", 'S', "string"));
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(coalBow), "SC ", "S C", "SC ", 'C', "blockCoal", 'S', "string"));
                    }
                    if (enableLapisWeapons[2]) {
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisBow), " LS", "L S", " LS", 'L', "blockLapis", 'S', "string"));
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(lapisBow), "SL ", "S L", "SL ", 'L', "blockLapis", 'S', "string"));
                    }
                    if (enableRedstoneWeapons[2]) {
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneBow), " RS", "R S", " RS", 'R', "blockRedstone", 'S', "string"));
                        manager.addRecipe(new WBShapedOreRecipe(getItemStack(redstoneBow), "SR ", "S R", "SR ", 'R', "blockRedstone", 'S', "string"));
                    }
                }
                break;
        }
    }
}
