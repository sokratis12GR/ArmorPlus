/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModTinkersConstructRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        if (getRD() == EASY) {//Ardite
            if (enableArditeArmor) {
                if (enableArditeArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "C  C",
                            'C', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeHelmet),
                            "CCCC",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeChestplate),
                            "C  C",
                            "C  C",
                            "CCCC",
                            "CCCC",
                            'C', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeLeggings),
                            "CCCC",
                            "C  C",
                            "C  C",
                            "C  C",
                            'C', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "C  C",
                            'C', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeBoots),
                            "C  C",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotArdite"));
                }
            }
            //Cobalt
            if (enableCobaltArmor) {
                if (enableCobaltArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "C  C",
                            'C', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltHelmet),
                            "CCCC",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltChestplate),
                            "C  C",
                            "C  C",
                            "CCCC",
                            "CCCC",
                            'C', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltLeggings),
                            "CCCC",
                            "C  C",
                            "C  C",
                            "C  C",
                            'C', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "C  C",
                            'C', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltBoots),
                            "C  C",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotCobalt"));
                }
            }
            //KnightSlime
            if (enableKnightSlimeArmor) {
                if (enableKnightSlimeArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "C  C",
                            'C', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeHelmet),
                            "CCCC",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeChestplate),
                            "C  C",
                            "C  C",
                            "CCCC",
                            "CCCC",
                            'C', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeLeggings),
                            "CCCC",
                            "C  C",
                            "C  C",
                            "C  C",
                            'C', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "C  C",
                            'C', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeBoots),
                            "C  C",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotKnightslime"));
                }
            }
            //Manyullyn
            if (enableManyullynArmor) {
                if (enableManyullynArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "C  C",
                            'C', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynHelmet),
                            "CCCC",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynChestplate),
                            "C  C",
                            "C  C",
                            "CCCC",
                            "CCCC",
                            'C', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynLeggings),
                            "CCCC",
                            "C  C",
                            "C  C",
                            "C  C",
                            'C', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "C  C",
                            'C', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynBoots),
                            "C  C",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotManyullyn"));
                }
            }
            //PigIron
            if (enablePigIronArmor) {
                if (enablePigIronArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "C  C",
                            'C', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronHelmet),
                            "CCCC",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronChestplate),
                            "C  C",
                            "C  C",
                            "CCCC",
                            "CCCC",
                            'C', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronLeggings),
                            "CCCC",
                            "C  C",
                            "C  C",
                            "C  C",
                            'C', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "C  C",
                            'C', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronBoots),
                            "C  C",
                            "C  C",
                            "    ",
                            "    ",
                            'C', "ingotPigiron"));
                }
            }

        } else if (getRD() == RecipesDifficulty.EXPERT) {//Ardite
            if (enableArditeArmor) {
                if (enableArditeArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "A  A",
                            'C', "blockArdite",
                            'A', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeHelmet),
                            "CCCC",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockArdite",
                            'A', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeChestplate),
                            "A  A",
                            "A  A",
                            "CAAC",
                            "CCCC",
                            'C', "blockArdite",
                            'A', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeLeggings),
                            "CAAC",
                            "C  C",
                            "A  A",
                            "A  A",
                            'C', "blockArdite",
                            'A', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "A  A",
                            'C', "blockArdite",
                            'A', "ingotArdite"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(arditeBoots),
                            "C  C",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockArdite",
                            'A', "ingotArdite"));
                }
            }
            //Cobalt
            if (enableCobaltArmor) {
                if (enableCobaltArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "A  A",
                            'C', "blockCobalt",
                            'A', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltHelmet),
                            "CCCC",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockCobalt",
                            'A', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltChestplate),
                            "A  A",
                            "A  A",
                            "CAAC",
                            "CCCC",
                            'C', "blockCobalt",
                            'A', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltLeggings),
                            "CAAC",
                            "C  C",
                            "A  A",
                            "A  A",
                            'C', "blockCobalt",
                            'A', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "A  A",
                            'C', "blockCobalt",
                            'A', "ingotCobalt"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(cobaltBoots),
                            "C  C",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockCobalt",
                            'A', "ingotCobalt"));
                }
            }
            //KnightSlime
            if (enableKnightSlimeArmor) {
                if (enableKnightSlimeArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "A  A",
                            'C', "blockKnightslime",
                            'A', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeHelmet),
                            "CCCC",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockKnightslime",
                            'A', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeChestplate),
                            "A  A",
                            "A  A",
                            "CAAC",
                            "CCCC",
                            'C', "blockKnightslime",
                            'A', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeLeggings),
                            "CAAC",
                            "C  C",
                            "A  A",
                            "A  A",
                            'C', "blockKnightslime",
                            'A', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "A  A",
                            'C', "blockKnightslime",
                            'A', "ingotKnightslime"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlimeBoots),
                            "C  C",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockKnightslime",
                            'A', "ingotKnightslime"));
                }
            }
            //Manyullyn
            if (enableManyullynArmor) {
                if (enableManyullynArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "A  A",
                            'C', "blockManyullyn",
                            'A', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynHelmet),
                            "CCCC",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockManyullyn",
                            'A', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynChestplate),
                            "A  A",
                            "A  A",
                            "CAAC",
                            "CCCC",
                            'C', "blockManyullyn",
                            'A', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynLeggings),
                            "CAAC",
                            "C  C",
                            "A  A",
                            "A  A",
                            'C', "blockManyullyn",
                            'A', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "A  A",
                            'C', "blockManyullyn",
                            'A', "ingotManyullyn"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullynBoots),
                            "C  C",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockManyullyn",
                            'A', "ingotManyullyn"));
                }
            }
            //PigIron
            if (enablePigIronArmor) {
                if (enablePigIronArmorRecipes) {
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronHelmet),
                            "    ",
                            "    ",
                            "CCCC",
                            "A  A",
                            'C', "blockPigiron",
                            'A', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronHelmet),
                            "CCCC",
                            "A  A",
                            "    ",
                            "    ",
                            'C', "blockPigiron",
                            'A', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronChestplate),
                            "A  A",
                            "A  A",
                            "CAAC",
                            "CCCC",
                            'C', "blockPigiron",
                            'A', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronLeggings),
                            "CAAC",
                            "C  C",
                            "A  A",
                            "A  A",
                            'C', "blockPigiron",
                            'A', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronBoots),
                            "    ",
                            "    ",
                            "C  C",
                            "A  A",
                            'C', "blockPigiron",
                            'A', "ingotPigiron"));
                    manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIronBoots),
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
}