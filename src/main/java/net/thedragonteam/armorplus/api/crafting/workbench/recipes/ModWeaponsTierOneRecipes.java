/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.*;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(WorkbenchCraftingManager manager) {
        if (enableSwordsRecipes && gameMode == 0) {
            if (enableCoalSword)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sword[0], 1), " C ", " C ", " S ", 'C', "itemCoal", 'S', "stickWood"));
            if (enableLapisSword)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sword[1], 1), " L ", " L ", " S ", 'L', "gemLapis", 'S', "stickWood"));
            if (enableRedstoneSword)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sword[2], 1), " R ", " R ", " S ", 'R', "dustRedstone", 'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && gameMode == 0) {
            if (enableCoalBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "C C", "CSC", " S ", 'C', "itemCoal", 'S', "stickWood"));
            if (enableLapisBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "L L", "LSL", " S ", 'L', "gemLapis", 'S', "stickWood"));
            if (enableRedstoneBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "R R", "RSR", " S ", 'R', "dustRedstone", 'S', "stickWood"));
        }
        if (enableBowsRecipes && gameMode == 0) {
            if (enableCoalBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), " CS", "C S", " CS", 'C', "itemCoal", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "SC ", "S C", "SC ", 'C', "itemCoal", 'S', "string"));
            }
            if (enableLapisBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), " LS", "L S", " LS", 'L', "gemLapis", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "SL ", "S L", "SL ", 'L', "gemLapis", 'S', "string"));
            }
            if (enableRedstoneBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), " RS", "R S", " RS", 'R', "dustRedstone", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "SR ", "S R", "SR ", 'R', "dustRedstone", 'S', "string"));
            }
        }
        if (enableSwordsRecipes && gameMode == 1) {
            if (enableCoalSword)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sword[0], 1), " C ", " C ", " S ", 'C', "blockCoal", 'S', "stickWood"));
            if (enableLapisSword)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sword[1], 1), " L ", " L ", " S ", 'L', "blockLapis", 'S', "stickWood"));
            if (enableRedstoneSword)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sword[2], 1), " R ", " R ", " S ", 'R', "blockRedstone", 'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && gameMode == 1) {
            if (enableCoalBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "C C", "CSC", " S ", 'C', "blockCoal", 'S', "stickWood"));
            if (enableLapisBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "L L", "LSL", " S ", 'L', "blockLapis", 'S', "stickWood"));
            if (enableRedstoneBattleAxe)
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "R R", "RSR", " S ", 'R', "blockRedstone", 'S', "stickWood"));
        }
        if (enableBowsRecipes && gameMode == 1) {
            if (enableCoalBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), " CS", "C S", " CS", 'C', "blockCoal", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "SC ", "S C", "SC ", 'C', "blockCoal", 'S', "string"));
            }
            if (enableLapisBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), " LS", "L S", " LS", 'L', "blockLapis", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "SL ", "S L", "SL ", 'L', "blockLapis", 'S', "string"));
            }
            if (enableRedstoneBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), " RS", "R S", " RS", 'R', "blockRedstone", 'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "SR ", "S R", "SR ", 'R', "blockRedstone", 'S', "string"));
            }
        }
    }
}
