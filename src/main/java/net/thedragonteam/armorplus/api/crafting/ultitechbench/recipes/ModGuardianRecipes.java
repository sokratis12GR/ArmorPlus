/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.APConfig.enableGuardianArmorRecipes;

public class ModGuardianRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Guardian Armor */
        if (APConfig.enableGuardianArmor) {
            if (APConfig.recipes == 0 && enableGuardianArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                        "GGGGG",
                        "G   G",
                        "     ",
                        "     ",
                        "     ",
                        'G', new ItemStack(ModItems.materials, 1, 1));
                manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "GGGGG",
                        "G   G",
                        'G', new ItemStack(ModItems.materials, 1, 1));
                manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                        "G   G",
                        "G   G",
                        "GGGGG",
                        "GGGGG",
                        "GGGGG",
                        'G', new ItemStack(ModItems.materials, 1, 1));
                manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                        "GGGGG",
                        "GGGGG",
                        "G   G",
                        "G   G",
                        "G   G",
                        'G', new ItemStack(ModItems.materials, 1, 1));
                manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                        "G   G",
                        "G   G",
                        "     ",
                        "     ",
                        "     ",
                        'G', new ItemStack(ModItems.materials, 1, 1));
                manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                        "     ",
                        "     ",
                        "     ",
                        "G   G",
                        "G   G",
                        'G', new ItemStack(ModItems.materials, 1, 1));
            }
            if (APConfig.recipes == 1 && enableGuardianArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                        "GPPPG",
                        "GSLSG",
                        "     ",
                        "     ",
                        "     ",
                        'G', new ItemStack(ModItems.materials, 1, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "GPPPG",
                        "GSLSG",
                        'G', new ItemStack(ModItems.materials, 1, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                        "L   L",
                        "G   G",
                        "PGGGP",
                        "GSLSG",
                        "PGGGP",
                        'G', new ItemStack(ModItems.materials, 1, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                        "GPGPG",
                        "GSLSG",
                        "G   G",
                        "G   G",
                        "P   P",
                        'G', new ItemStack(ModItems.materials, 1, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                        "G   G",
                        "G   G",
                        "G   G",
                        "S   S",
                        "     ",
                        'G', new ItemStack(ModItems.materials, 1, 1),
                        'S', Blocks.SPONGE);
                manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                        "     ",
                        "G   G",
                        "G   G",
                        "G   G",
                        "S   S",
                        'G', new ItemStack(ModItems.materials, 1, 1),
                        'S', Blocks.SPONGE);
            }
        }
    }
}
