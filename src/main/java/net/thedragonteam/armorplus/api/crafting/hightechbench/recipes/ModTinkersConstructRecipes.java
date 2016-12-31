/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.hightechbench.recipes;

import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe;

import static net.thedragonteam.armorplus.APConfig.getRecipesDifficulty;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

public class ModTinkersConstructRecipes {

    public void addRecipes(HighTechBenchCraftingManager manager) {
        switch (getRecipesDifficulty()) {
            case EASY:
                //Ardite
                if (APConfig.enableArditeArmor) {
                    if (APConfig.enableArditeArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "C  C",
                                'C', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[0]),
                                "CCCC",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[1]),
                                "C  C",
                                "C  C",
                                "CCCC",
                                "CCCC",
                                'C', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[2]),
                                "CCCC",
                                "C  C",
                                "C  C",
                                "C  C",
                                'C', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "C  C",
                                'C', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[3]),
                                "C  C",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotArdite"));
                    }
                }
                //Cobalt
                if (APConfig.enableCobaltArmor) {
                    if (APConfig.enableCobaltArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "C  C",
                                'C', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[0]),
                                "CCCC",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[1]),
                                "C  C",
                                "C  C",
                                "CCCC",
                                "CCCC",
                                'C', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[2]),
                                "CCCC",
                                "C  C",
                                "C  C",
                                "C  C",
                                'C', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "C  C",
                                'C', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[3]),
                                "C  C",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotCobalt"));
                    }
                }
                //KnightSlime
                if (APConfig.enableKnightSlimeArmor) {
                    if (APConfig.enableKnightSlimeArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "C  C",
                                'C', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[0]),
                                "CCCC",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[1]),
                                "C  C",
                                "C  C",
                                "CCCC",
                                "CCCC",
                                'C', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[2]),
                                "CCCC",
                                "C  C",
                                "C  C",
                                "C  C",
                                'C', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "C  C",
                                'C', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[3]),
                                "C  C",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotKnightslime"));
                    }
                }
                //Manyullyn
                if (APConfig.enableManyullynArmor) {
                    if (APConfig.enableManyullynArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "C  C",
                                'C', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[0]),
                                "CCCC",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[1]),
                                "C  C",
                                "C  C",
                                "CCCC",
                                "CCCC",
                                'C', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[2]),
                                "CCCC",
                                "C  C",
                                "C  C",
                                "C  C",
                                'C', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "C  C",
                                'C', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[3]),
                                "C  C",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotManyullyn"));
                    }
                }
                //PigIron
                if (APConfig.enablePigIronArmor) {
                    if (APConfig.enablePigIronArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "C  C",
                                'C', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[0]),
                                "CCCC",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[1]),
                                "C  C",
                                "C  C",
                                "CCCC",
                                "CCCC",
                                'C', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[2]),
                                "CCCC",
                                "C  C",
                                "C  C",
                                "C  C",
                                'C', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "C  C",
                                'C', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[3]),
                                "C  C",
                                "C  C",
                                "    ",
                                "    ",
                                'C', "ingotPigiron"));
                    }
                }
                break;
            case EXPERT:
                //Ardite
                if (APConfig.enableArditeArmor) {
                    if (APConfig.enableArditeArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "A  A",
                                'C', "blockArdite",
                                'A', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[0]),
                                "CCCC",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockArdite",
                                'A', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[1]),
                                "A  A",
                                "A  A",
                                "CAAC",
                                "CCCC",
                                'C', "blockArdite",
                                'A', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[2]),
                                "CAAC",
                                "C  C",
                                "A  A",
                                "A  A",
                                'C', "blockArdite",
                                'A', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "A  A",
                                'C', "blockArdite",
                                'A', "ingotArdite"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(ardite[3]),
                                "C  C",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockArdite",
                                'A', "ingotArdite"));
                    }
                }
                //Cobalt
                if (APConfig.enableCobaltArmor) {
                    if (APConfig.enableCobaltArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "A  A",
                                'C', "blockCobalt",
                                'A', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[0]),
                                "CCCC",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockCobalt",
                                'A', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[1]),
                                "A  A",
                                "A  A",
                                "CAAC",
                                "CCCC",
                                'C', "blockCobalt",
                                'A', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[2]),
                                "CAAC",
                                "C  C",
                                "A  A",
                                "A  A",
                                'C', "blockCobalt",
                                'A', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "A  A",
                                'C', "blockCobalt",
                                'A', "ingotCobalt"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(cobalt[3]),
                                "C  C",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockCobalt",
                                'A', "ingotCobalt"));
                    }
                }
                //KnightSlime
                if (APConfig.enableKnightSlimeArmor) {
                    if (APConfig.enableKnightSlimeArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "A  A",
                                'C', "blockKnightslime",
                                'A', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[0]),
                                "CCCC",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockKnightslime",
                                'A', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[1]),
                                "A  A",
                                "A  A",
                                "CAAC",
                                "CCCC",
                                'C', "blockKnightslime",
                                'A', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[2]),
                                "CAAC",
                                "C  C",
                                "A  A",
                                "A  A",
                                'C', "blockKnightslime",
                                'A', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "A  A",
                                'C', "blockKnightslime",
                                'A', "ingotKnightslime"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(knightSlime[3]),
                                "C  C",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockKnightslime",
                                'A', "ingotKnightslime"));
                    }
                }
                //Manyullyn
                if (APConfig.enableManyullynArmor) {
                    if (APConfig.enableManyullynArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "A  A",
                                'C', "blockManyullyn",
                                'A', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[0]),
                                "CCCC",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockManyullyn",
                                'A', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[1]),
                                "A  A",
                                "A  A",
                                "CAAC",
                                "CCCC",
                                'C', "blockManyullyn",
                                'A', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[2]),
                                "CAAC",
                                "C  C",
                                "A  A",
                                "A  A",
                                'C', "blockManyullyn",
                                'A', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "A  A",
                                'C', "blockManyullyn",
                                'A', "ingotManyullyn"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(manyullyn[3]),
                                "C  C",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockManyullyn",
                                'A', "ingotManyullyn"));
                    }
                }
                //PigIron
                if (APConfig.enablePigIronArmor) {
                    if (APConfig.enablePigIronArmorRecipes) {
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[0]),
                                "    ",
                                "    ",
                                "CCCC",
                                "A  A",
                                'C', "blockPigiron",
                                'A', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[0]),
                                "CCCC",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockPigiron",
                                'A', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[1]),
                                "A  A",
                                "A  A",
                                "CAAC",
                                "CCCC",
                                'C', "blockPigiron",
                                'A', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[2]),
                                "CAAC",
                                "C  C",
                                "A  A",
                                "A  A",
                                'C', "blockPigiron",
                                'A', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[3]),
                                "    ",
                                "    ",
                                "C  C",
                                "A  A",
                                'C', "blockPigiron",
                                'A', "ingotPigiron"));
                        manager.addRecipe(new ShapedOreRecipe(getItemStack(pigIron[3]),
                                "C  C",
                                "A  A",
                                "    ",
                                "    ",
                                'C', "blockPigiron",
                                'A', "ingotPigiron"));
                    }
                }
                break;
            case HELLISH:
                break;
        }
    }
}