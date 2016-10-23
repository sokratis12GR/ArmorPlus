/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModTinkersConstructRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        /* Ardite Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableArditeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1),
                    "    ",
                    "    ",
                    "CCCC",
                    "C  C",
                    'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1),
                    "CCCC",
                    "C  C",
                    "    ",
                    "    ",
                    'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeChestplate, 1),
                    "C  C",
                    "C  C",
                    "CCCC",
                    "CCCC",
                    'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeLeggings, 1),
                    "CCCC",
                    "C  C",
                    "C  C",
                    "C  C",
                    'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1),
                    "    ",
                    "    ",
                    "C  C",
                    "C  C",
                    'C', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1),
                    "C  C",
                    "C  C",
                    "    ",
                    "    ",
                    'C', "ingotArdite"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableArditeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "AXXA",
                    'C', "blockArdite",
                    'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1),
                    "CCCC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockArdite",
                    'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeChestplate, 1),
                    "AXXA",
                    "AXXA",
                    "CAAC",
                    "CCCC",
                    'C', "blockArdite",
                    'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeLeggings, 1),
                    "CAAC",
                    "CXXC",
                    "AXXA",
                    "AXXA",
                    'C', "blockArdite",
                    'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "AXXA",
                    'C', "blockArdite",
                    'A', "ingotArdite"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1),
                    "CXXC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockArdite",
                    'A', "ingotArdite"));
        }
        /* Cobalt Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableCobaltArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "CXXC",
                    'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                    "CCCC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltChestplate, 1),
                    "CXXC",
                    "CXXC",
                    "CCCC",
                    "CCCC",
                    'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltLeggings, 1),
                    "CCCC",
                    "CXXC",
                    "CXXC",
                    "CXXC",
                    'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "CXXC",
                    'C', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                    "CXXC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotCobalt"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableCobaltArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "AXXA",
                    'C', "blockCobalt",
                    'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                    "CCCC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockCobalt",
                    'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltChestplate, 1),
                    "AXXA",
                    "AXXA",
                    "CAAC",
                    "CCCC",
                    'C', "blockCobalt",
                    'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltLeggings, 1),
                    "CAAC",
                    "CXXC",
                    "AXXA",
                    "AXXA",
                    'C', "blockCobalt",
                    'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "AXXA",
                    'C', "blockCobalt",
                    'A', "ingotCobalt"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                    "CXXC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockCobalt",
                    'A', "ingotCobalt"));
        }
        /* Knight Slime Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableKnightSlimeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "CXXC",
                    'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                    "CCCC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1),
                    "CXXC",
                    "CXXC",
                    "CCCC",
                    "CCCC",
                    'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1),
                    "CCCC",
                    "CXXC",
                    "CXXC",
                    "CXXC",
                    'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "CXXC",
                    'C', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                    "CXXC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotKnightslime"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableKnightSlimeArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "AXXA",
                    'C', "blockKnightslime",
                    'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                    "CCCC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockKnightslime",
                    'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1),
                    "AXXA",
                    "AXXA",
                    "CAAC",
                    "CCCC",
                    'C', "blockKnightslime",
                    'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1),
                    "CAAC",
                    "CXXC",
                    "AXXA",
                    "AXXA",
                    'C', "blockKnightslime",
                    'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "AXXA",
                    'C', "blockKnightslime",
                    'A', "ingotKnightslime"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                    "CXXC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockKnightslime",
                    'A', "ingotKnightslime"));
        }
        /* Manyullyn Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableManyullynArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "CXXC",
                    'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                    "CCCC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynChestplate, 1),
                    "CXXC",
                    "CXXC",
                    "CCCC",
                    "CCCC",
                    'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynLeggings, 1),
                    "CCCC",
                    "CXXC",
                    "CXXC",
                    "CXXC",
                    'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "CXXC",
                    'C', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                    "CXXC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotManyullyn"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableManyullynArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "AXXA",
                    'C', "blockManyullyn",
                    'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                    "CCCC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockManyullyn",
                    'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynChestplate, 1),
                    "AXXA",
                    "AXXA",
                    "CAAC",
                    "CCCC",
                    'C', "blockManyullyn",
                    'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynLeggings, 1),
                    "CAAC",
                    "CXXC",
                    "AXXA",
                    "AXXA",
                    'C', "blockManyullyn",
                    'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "AXXA",
                    'C', "blockManyullyn",
                    'A', "ingotManyullyn"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                    "CXXC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockManyullyn",
                    'A', "ingotManyullyn"));
        }
          /* Pig Iron Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enablePigIronArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "CXXC",
                    'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                    "CCCC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronChestplate, 1),
                    "CXXC",
                    "CXXC",
                    "CCCC",
                    "CCCC",
                    'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronLeggings, 1),
                    "CCCC",
                    "CXXC",
                    "CXXC",
                    "CXXC",
                    'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "CXXC",
                    'C', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                    "CXXC",
                    "CXXC",
                    "XXXX",
                    "XXXX",
                    'C', "ingotPigiron"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enablePigIronArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CCCC",
                    "AXXA",
                    'C', "blockPigiron",
                    'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                    "CCCC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockPigiron",
                    'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronChestplate, 1),
                    "AXXA",
                    "AXXA",
                    "CAAC",
                    "CCCC",
                    'C', "blockPigiron",
                    'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronLeggings, 1),
                    "CAAC",
                    "CXXC",
                    "AXXA",
                    "AXXA",
                    'C', "blockPigiron",
                    'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                    "XXXX",
                    "XXXX",
                    "CXXC",
                    "AXXA",
                    'C', "blockPigiron",
                    'A', "ingotPigiron"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                    "CXXC",
                    "AXXA",
                    "XXXX",
                    "XXXX",
                    'C', "blockPigiron",
                    'A', "ingotPigiron"));
        }

    }
}