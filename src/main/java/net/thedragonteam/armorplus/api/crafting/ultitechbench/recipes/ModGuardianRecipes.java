/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.enableGuardianArmorRecipes;
import static net.thedragonteam.armorplus.registry.ModItems.guardianBoots;
import static net.thedragonteam.armorplus.registry.ModItems.guardianChestplate;
import static net.thedragonteam.armorplus.registry.ModItems.guardianHelmet;

public class ModGuardianRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Guardian Armor */
        if (APConfig.enableGuardianArmor) {
            if (APConfig.gameMode == 0 && enableGuardianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianHelmet, 1),
                        "GGGGG",
                        "G   G",
                        "     ",
                        "     ",
                        "     ",
                        'G', ModItems.guardianScale));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "GGGGG",
                        "G   G",
                        'G', ModItems.guardianScale));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianChestplate, 1),
                        "G   G",
                        "G   G",
                        "GGGGG",
                        "GGGGG",
                        "GGGGG",
                        'G', ModItems.guardianScale));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                        "GGGGG",
                        "GGGGG",
                        "G   G",
                        "G   G",
                        "G   G",
                        'G', ModItems.guardianScale));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianBoots, 1),
                        "G   G",
                        "G   G",
                        "     ",
                        "     ",
                        "     ",
                        'G', ModItems.guardianScale));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianBoots, 1),
                        "     ",
                        "     ",
                        "     ",
                        "G   G",
                        "G   G",
                        'G', ModItems.guardianScale));
            }
            if (APConfig.gameMode == 1 && enableGuardianArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianHelmet, 1),
                        "GPPPG",
                        "GSLSG",
                        "     ",
                        "     ",
                        "     ",
                        'G', ModItems.guardianScale,
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "GPPPG",
                        "GSLSG",
                        'G', ModItems.guardianScale,
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianChestplate, 1),
                        "L   L",
                        "G   G",
                        "PGGGP",
                        "GSLSG",
                        "PGGGP",
                        'G', ModItems.guardianScale,
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                        "GPGPG",
                        "GSLSG",
                        "G   G",
                        "G   G",
                        "P   P",
                        'G', ModItems.guardianScale,
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianBoots, 1),
                        "G   G",
                        "G   G",
                        "G   G",
                        "S   S",
                        "     ",
                        'G', ModItems.guardianScale,
                        'S', Blocks.SPONGE));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(guardianBoots, 1),
                        "     ",
                        "G   G",
                        "G   G",
                        "G   G",
                        "S   S",
                        'G', ModItems.guardianScale,
                        'S', Blocks.SPONGE));
            }
        }
    }
}
