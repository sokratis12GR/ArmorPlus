/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

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
                    "GXXXG",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "GGGGG",
                    "GXXXG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "GXXXG",
                    "GXXXG",
                    "GGGGG",
                    "GGGGG",
                    "GGGGG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GGGGG",
                    "GGGGG",
                    "GXXXG",
                    "GXXXG",
                    "GXXXG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "GXXXG",
                    "GXXXG",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "GXXXG",
                    "GXXXG",
                    'G', ModItems.guardianScale);
        }
        if (ARPConfig.recipes == 1 && enableGuardianArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "GPPPG",
                    "GSLSG",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "GPPPG",
                    "GSLSG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "LXXXL",
                    "GXXXG",
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
                    "GXXXG",
                    "GXXXG",
                    "PXXXP",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "GXXXG",
                    "GXXXG",
                    "GXXXG",
                    "SXXXS",
                    "XXXXX",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "XXXXX",
                    "GXXXG",
                    "GXXXG",
                    "GXXXG",
                    "SXXXS",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
        }
    }
}
