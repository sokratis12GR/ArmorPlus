/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(WorkbenchCraftingManager manager) {
        if (enableSwordsRecipes && getRD() == EASY) {
            if (enableCoalSword)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalSword), " C ", " C ", " S ", 'C', "itemCoal", 'S', "stickWood"));
            if (enableLapisSword)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisSword), " L ", " L ", " S ", 'L', "gemLapis", 'S', "stickWood"));
            if (enableRedstoneSword)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneSword), " R ", " R ", " S ", 'R', "dustRedstone", 'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && getRD() == EASY) {
            if (enableCoalBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBattleAxe), "C C", "CSC", " S ", 'C', "itemCoal", 'S', "stickWood"));
            if (enableLapisBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBattleAxe), "L L", "LSL", " S ", 'L', "gemLapis", 'S', "stickWood"));
            if (enableRedstoneBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBattleAxe), "R R", "RSR", " S ", 'R', "dustRedstone", 'S', "stickWood"));
        }
        if (enableBowsRecipes && getRD() == EASY) {
            if (enableCoalBow) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBow), " CS", "C S", " CS", 'C', "itemCoal", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBow), "SC ", "S C", "SC ", 'C', "itemCoal", 'S', "string"));
            }
            if (enableLapisBow) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBow), " LS", "L S", " LS", 'L', "gemLapis", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBow), "SL ", "S L", "SL ", 'L', "gemLapis", 'S', "string"));
            }
            if (enableRedstoneBow) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBow), " RS", "R S", " RS", 'R', "dustRedstone", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBow), "SR ", "S R", "SR ", 'R', "dustRedstone", 'S', "string"));
            }
        }
        if (enableSwordsRecipes && getRD() == EXPERT) {
            if (enableCoalSword)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalSword), " C ", " C ", " S ", 'C', "blockCoal", 'S', "stickWood"));
            if (enableLapisSword)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisSword), " L ", " L ", " S ", 'L', "blockLapis", 'S', "stickWood"));
            if (enableRedstoneSword)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneSword), " R ", " R ", " S ", 'R', "blockRedstone", 'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && getRD() == EXPERT) {
            if (enableCoalBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBattleAxe), "C C", "CSC", " S ", 'C', "blockCoal", 'S', "stickWood"));
            if (enableLapisBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBattleAxe), "L L", "LSL", " S ", 'L', "blockLapis", 'S', "stickWood"));
            if (enableRedstoneBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBattleAxe), "R R", "RSR", " S ", 'R', "blockRedstone", 'S', "stickWood"));
        }
        if (enableBowsRecipes && getRD() == EXPERT) {
            if (enableCoalBow) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBow), " CS", "C S", " CS", 'C', "blockCoal", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBow), "SC ", "S C", "SC ", 'C', "blockCoal", 'S', "string"));
            }
            if (enableLapisBow) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBow), " LS", "L S", " LS", 'L', "blockLapis", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBow), "SL ", "S L", "SL ", 'L', "blockLapis", 'S', "string"));
            }
            if (enableRedstoneBow) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBow), " RS", "R S", " RS", 'R', "blockRedstone", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBow), "SR ", "S R", "SR ", 'R', "blockRedstone", 'S', "string"));
            }
        }
    }
}
