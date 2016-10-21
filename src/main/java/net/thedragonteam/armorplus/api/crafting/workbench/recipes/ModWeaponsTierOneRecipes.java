/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ModWeaponsTierOneRecipes {
    public void addRecipes(WorkbenchCraftingManager manager) {
        if (enableSwordsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalSword, 1), "XCX", "XCX", "XSX", 'C', "itemCoal", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisSword, 1), "XLX", "XLX", "XSX", 'L', "gemLapis", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneSword, 1), "XRX", "XRX", "XSX", 'R', "dustRedstone", 'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "CXC", "CSC", "XSX", 'C', "itemCoal", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "LXL", "LSL", "XSX", 'L', "gemLapis", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "RXR", "RSR", "XSX", 'R', "dustRedstone", 'S', "stickWood"));
        }
        if (enableBowsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "XCS", "CXS", "XCS", 'C', "itemCoal", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "SCX", "SXC", "SCX", 'C', "itemCoal", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "XLS", "LXS", "XLS", 'L', "gemLapis", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "SLX", "SXL", "SLX", 'L', "gemLapis", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "XRS", "RXS", "XRS", 'R', "dustRedstone", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "SRX", "SXR", "SRX", 'R', "dustRedstone", 'S', "string"));
        }
        if (enableSwordsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalSword, 1), "XCX", "XCX", "XSX", 'C', "blockCoal", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisSword, 1), "XLX", "XLX", "XSX", 'L', "blockLapis", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneSword, 1), "XRX", "XRX", "XSX", 'R', "blockRedstone", 'S', "stickWood"));
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "CXC", "CSC", "XSX", 'C', "blockCoal", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "LXL", "LSL", "XSX", 'L', "blockLapis", 'S', "stickWood"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "RXR", "RSR", "XSX", 'R', "blockRedstone", 'S', "stickWood"));
        }
        if (enableBowsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "XCS", "CXS", "XCS", 'C', "blockCoal", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "SCX", "SXC", "SCX", 'C', "blockCoal", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "XLS", "LXS", "XLS", 'L', "blockLapis", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "SLX", "SXL", "SLX", 'L', "blockLapis", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "XRS", "RXS", "XRS", 'R', "blockRedstone", 'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "SRX", "SXR", "SRX", 'R', "blockRedstone", 'S', "string"));
        }
    }
}
