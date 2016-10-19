/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModTinkersConstructRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Ardite Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableArditeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1), "XXX", "CCC", "CXC", 'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1), "CCC", "CXC", "XXX", 'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeChestplate, 1), "CXC", "CCC", "CCC", 'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeLeggings, 1), "CCC", "CXC", "CXC", 'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1), "XXX", "CXC", "CXC", 'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1), "CXC", "CXC", "XXX", 'C', "ingotArdite"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableArditeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1), "XXX", "CCC", "AXA", 'C', "blockArdite", 'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1), "CCC", "AXA", "XXX", 'C', "blockArdite", 'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeChestplate, 1), "AXA", "CAC", "CCC", 'C', "blockArdite", 'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeLeggings, 1), "CAC", "CXC", "AXA", 'C', "blockArdite", 'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1), "XXX", "CXC", "AXA", 'C', "blockArdite", 'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1), "CXC", "AXA", "XXX", 'C', "blockArdite", 'A', "ingotArdite"));
        }
        /* Cobalt Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableCobaltArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1), "XXX", "CCC", "CXC", 'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1), "CCC", "CXC", "XXX", 'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltChestplate, 1), "CXC", "CCC", "CCC", 'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltLeggings, 1), "CCC", "CXC", "CXC", 'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1), "XXX", "CXC", "CXC", 'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1), "CXC", "CXC", "XXX", 'C', "ingotCobalt"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableCobaltArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1), "XXX", "CCC", "AXA", 'C', "blockCobalt", 'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1), "CCC", "AXA", "XXX", 'C', "blockCobalt", 'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltChestplate, 1), "AXA", "CAC", "CCC", 'C', "blockCobalt", 'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltLeggings, 1), "CAC", "CXC", "AXA", 'C', "blockCobalt", 'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1), "XXX", "CXC", "AXA", 'C', "blockCobalt", 'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1), "CXC", "AXA", "XXX", 'C', "blockCobalt", 'A', "ingotCobalt"));
        }
        /* Knight Slime Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableKnightSlimeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1), "XXX", "CCC", "CXC", 'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1), "CCC", "CXC", "XXX", 'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1), "CXC", "CCC", "CCC", 'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1), "CCC", "CXC", "CXC", 'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "XXX", "CXC", "CXC", 'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "CXC", "CXC", "XXX", 'C', "ingotKnightslime"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableKnightSlimeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1), "XXX", "CCC", "AXA", 'C', "blockKnightslime", 'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1), "CCC", "AXA", "XXX", 'C', "blockKnightslime", 'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1), "AXA", "CAC", "CCC", 'C', "blockKnightslime", 'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1), "CAC", "CXC", "AXA", 'C', "blockKnightslime", 'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "XXX", "CXC", "AXA", 'C', "blockKnightslime", 'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1), "CXC", "AXA", "XXX", 'C', "blockKnightslime", 'A', "ingotKnightslime"));
        }
        /* Manyullyn Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableManyullynArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1), "XXX", "CCC", "CXC", 'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1), "CCC", "CXC", "XXX", 'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynChestplate, 1), "CXC", "CCC", "CCC", 'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynLeggings, 1), "CCC", "CXC", "CXC", 'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1), "XXX", "CXC", "CXC", 'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1), "CXC", "CXC", "XXX", 'C', "ingotManyullyn"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableManyullynArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1), "XXX", "CCC", "AXA", 'C', "blockManyullyn", 'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1), "CCC", "AXA", "XXX", 'C', "blockManyullyn", 'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynChestplate, 1), "AXA", "CAC", "CCC", 'C', "blockManyullyn", 'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynLeggings, 1), "CCC", "CXC", "AXA", 'C', "blockManyullyn", 'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1), "XXX", "CXC", "AXA", 'C', "blockManyullyn", 'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1), "CXC", "AXA", "XXX", 'C', "blockManyullyn", 'A', "ingotManyullyn"));
        }
          /* Pig Iron Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enablePigIronArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1), "XXX", "CCC", "CXC", 'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1), "CCC", "CXC", "XXX", 'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronChestplate, 1), "CXC", "CCC", "CCC", 'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronLeggings, 1), "CCC", "CXC", "CXC", 'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1), "XXX", "CXC", "CXC", 'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1), "CXC", "CXC", "XXX", 'C', "ingotPigiron"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enablePigIronArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1), "XXX", "CCC", "AXA", 'C', "blockPigiron", 'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1), "CCC", "AXA", "XXX", 'C', "blockPigiron", 'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronChestplate, 1), "AXA", "CAC", "CCC", 'C', "blockPigiron", 'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronLeggings, 1), "CCC", "CXC", "AXA", 'C', "blockPigiron", 'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1), "XXX", "CXC", "AXA", 'C', "blockPigiron", 'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1), "CXC", "AXA", "XXX", 'C', "blockPigiron", 'A', "ingotPigiron"));
        }

    }
}