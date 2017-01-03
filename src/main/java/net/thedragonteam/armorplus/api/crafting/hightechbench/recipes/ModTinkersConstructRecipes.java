/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModTinkersConstructRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        /* Ardite Armor */
        if (APConfig.enableArditeArmor) {
            if (APConfig.recipes == 0 && APConfig.enableArditeArmorRecipes) {
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
            if (APConfig.recipes == 1 && APConfig.enableArditeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "A  A",
                        'C', "blockArdite",
                        'A', "ingotArdite"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeHelmet, 1),
                        "CCCC",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockArdite",
                        'A', "ingotArdite"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeChestplate, 1),
                        "A  A",
                        "A  A",
                        "CAAC",
                        "CCCC",
                        'C', "blockArdite",
                        'A', "ingotArdite"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeLeggings, 1),
                        "CAAC",
                        "C  C",
                        "A  A",
                        "A  A",
                        'C', "blockArdite",
                        'A', "ingotArdite"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "A  A",
                        'C', "blockArdite",
                        'A', "ingotArdite"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.arditeBoots, 1),
                        "C  C",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockArdite",
                        'A', "ingotArdite"));
            }
        }
        /* Cobalt Armor */
        if (APConfig.enableCobaltArmor) {
            if (APConfig.recipes == 0 && APConfig.enableCobaltArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "C  C",
                        'C', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                        "CCCC",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltChestplate, 1),
                        "C  C",
                        "C  C",
                        "CCCC",
                        "CCCC",
                        'C', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltLeggings, 1),
                        "CCCC",
                        "C  C",
                        "C  C",
                        "C  C",
                        'C', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "C  C",
                        'C', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                        "C  C",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotCobalt"));
            }
            if (APConfig.recipes == 1 && APConfig.enableCobaltArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "A  A",
                        'C', "blockCobalt",
                        'A', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltHelmet, 1),
                        "CCCC",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockCobalt",
                        'A', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltChestplate, 1),
                        "A  A",
                        "A  A",
                        "CAAC",
                        "CCCC",
                        'C', "blockCobalt",
                        'A', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltLeggings, 1),
                        "CAAC",
                        "C  C",
                        "A  A",
                        "A  A",
                        'C', "blockCobalt",
                        'A', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "A  A",
                        'C', "blockCobalt",
                        'A', "ingotCobalt"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobaltBoots, 1),
                        "C  C",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockCobalt",
                        'A', "ingotCobalt"));
            }
        }
        /* Knight Slime Armor */
        if (APConfig.enableKnightSlimeArmor) {
            if (APConfig.recipes == 0 && APConfig.enableKnightSlimeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "C  C",
                        'C', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                        "CCCC",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1),
                        "C  C",
                        "C  C",
                        "CCCC",
                        "CCCC",
                        'C', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1),
                        "CCCC",
                        "C  C",
                        "C  C",
                        "C  C",
                        'C', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "C  C",
                        'C', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                        "C  C",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotKnightslime"));
            }
            if (APConfig.recipes == 1 && APConfig.enableKnightSlimeArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "A  A",
                        'C', "blockKnightslime",
                        'A', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeHelmet, 1),
                        "CCCC",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockKnightslime",
                        'A', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeChestplate, 1),
                        "A  A",
                        "A  A",
                        "CAAC",
                        "CCCC",
                        'C', "blockKnightslime",
                        'A', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeLeggings, 1),
                        "CAAC",
                        "C  C",
                        "A  A",
                        "A  A",
                        'C', "blockKnightslime",
                        'A', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "A  A",
                        'C', "blockKnightslime",
                        'A', "ingotKnightslime"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightSlimeBoots, 1),
                        "C  C",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockKnightslime",
                        'A', "ingotKnightslime"));
            }
        }
        /* Manyullyn Armor */
        if (APConfig.enableManyullynArmor) {
            if (APConfig.recipes == 0 && APConfig.enableManyullynArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "C  C",
                        'C', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                        "CCCC",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynChestplate, 1),
                        "C  C",
                        "C  C",
                        "CCCC",
                        "CCCC",
                        'C', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynLeggings, 1),
                        "CCCC",
                        "C  C",
                        "C  C",
                        "C  C",
                        'C', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "C  C",
                        'C', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                        "C  C",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotManyullyn"));
            }
            if (APConfig.recipes == 1 && APConfig.enableManyullynArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "A  A",
                        'C', "blockManyullyn",
                        'A', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynHelmet, 1),
                        "CCCC",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockManyullyn",
                        'A', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynChestplate, 1),
                        "A  A",
                        "A  A",
                        "CAAC",
                        "CCCC",
                        'C', "blockManyullyn",
                        'A', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynLeggings, 1),
                        "CAAC",
                        "C  C",
                        "A  A",
                        "A  A",
                        'C', "blockManyullyn",
                        'A', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "A  A",
                        'C', "blockManyullyn",
                        'A', "ingotManyullyn"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullynBoots, 1),
                        "C  C",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockManyullyn",
                        'A', "ingotManyullyn"));
            }
        }
        /* Pig Iron Armor */
        if (APConfig.enablePigIronArmor) {
            if (APConfig.recipes == 0 && APConfig.enablePigIronArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "C  C",
                        'C', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                        "CCCC",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronChestplate, 1),
                        "C  C",
                        "C  C",
                        "CCCC",
                        "CCCC",
                        'C', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronLeggings, 1),
                        "CCCC",
                        "C  C",
                        "C  C",
                        "C  C",
                        'C', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "C  C",
                        'C', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                        "C  C",
                        "C  C",
                        "    ",
                        "    ",
                        'C', "ingotPigiron"));
            }
            if (APConfig.recipes == 1 && APConfig.enablePigIronArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                        "    ",
                        "    ",
                        "CCCC",
                        "A  A",
                        'C', "blockPigiron",
                        'A', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronHelmet, 1),
                        "CCCC",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockPigiron",
                        'A', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronChestplate, 1),
                        "A  A",
                        "A  A",
                        "CAAC",
                        "CCCC",
                        'C', "blockPigiron",
                        'A', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronLeggings, 1),
                        "CAAC",
                        "C  C",
                        "A  A",
                        "A  A",
                        'C', "blockPigiron",
                        'A', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                        "    ",
                        "    ",
                        "C  C",
                        "A  A",
                        'C', "blockPigiron",
                        'A', "ingotPigiron"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pigIronBoots, 1),
                        "C  C",
                        "A  A",
                        "    ",
                        "    ",
                        'C', "blockPigiron",
                        'A', "ingotPigiron"));
            }
        }
    }
}