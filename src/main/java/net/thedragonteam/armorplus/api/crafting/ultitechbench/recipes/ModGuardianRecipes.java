/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableGuardianArmorRecipes;

public class ModGuardianRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {

        /* Guardian Armor */
        if (ARPConfig.recipes == 0 && enableGuardianArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "GGGGG",
                    "G   G",
                    "     ",
                    "     ",
                    "     ",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "     ",
                    "     ",
                    "     ",
                    "GGGGG",
                    "G   G",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "G   G",
                    "G   G",
                    "GGGGG",
                    "GGGGG",
                    "GGGGG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GGGGG",
                    "GGGGG",
                    "G   G",
                    "G   G",
                    "G   G",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "G   G",
                    "G   G",
                    "     ",
                    "     ",
                    "     ",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "     ",
                    "     ",
                    "     ",
                    "G   G",
                    "G   G",
                    'G', ModItems.guardianScale);
        }
        if (ARPConfig.recipes == 1 && enableGuardianArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "GPPPG",
                    "GSLSG",
                    "     ",
                    "     ",
                    "     ",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "     ",
                    "     ",
                    "     ",
                    "GPPPG",
                    "GSLSG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "L   L",
                    "G   G",
                    "PGGGP",
                    "GSLSG",
                    "PGGGP",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GPGPG",
                    "GSLSG",
                    "G   G",
                    "G   G",
                    "P   P",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "G   G",
                    "G   G",
                    "G   G",
                    "S   S",
                    "     ",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "     ",
                    "G   G",
                    "G   G",
                    "G   G",
                    "S   S",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
        }
    }
}
