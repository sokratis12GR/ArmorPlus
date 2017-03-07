/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModGuardianRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (enableGuardianArmor && enableGuardianArmorRecipes) {
                    manager.addRecipe(getItemStack(guardianHelmet),
                            "GGGGG",
                            "G   G",
                            "     ",
                            "     ",
                            "     ",
                            'G', getItemStack(materials, 1));
                    manager.addRecipe(getItemStack(guardianHelmet),
                            "     ",
                            "     ",
                            "     ",
                            "GGGGG",
                            "G   G",
                            'G', getItemStack(materials, 1));
                    manager.addRecipe(getItemStack(guardianChestplate),
                            "G   G",
                            "G   G",
                            "GGGGG",
                            "GGGGG",
                            "GGGGG",
                            'G', getItemStack(materials, 1));
                    manager.addRecipe(getItemStack(guardianLeggings),
                            "GGGGG",
                            "GGGGG",
                            "G   G",
                            "G   G",
                            "G   G",
                            'G', getItemStack(materials, 1));
                    manager.addRecipe(getItemStack(guardianBoots),
                            "G   G",
                            "G   G",
                            "     ",
                            "     ",
                            "     ",
                            'G', getItemStack(materials, 1));
                    manager.addRecipe(getItemStack(guardianBoots),
                            "     ",
                            "     ",
                            "     ",
                            "G   G",
                            "G   G",
                            'G', getItemStack(materials, 1));
                }
                break;
            case EXPERT:
                if (enableGuardianArmor && enableGuardianArmorRecipes) {
                    manager.addRecipe(getItemStack(guardianHelmet),
                            "GPPPG",
                            "GSLSG",
                            "     ",
                            "     ",
                            "     ",
                            'G', getItemStack(materials, 1),
                            'L', Blocks.SEA_LANTERN,
                            'P', Items.PRISMARINE_CRYSTALS,
                            'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianHelmet),
                            "     ",
                            "     ",
                            "     ",
                            "GPPPG",
                            "GSLSG",
                            'G', getItemStack(materials, 1),
                            'L', Blocks.SEA_LANTERN,
                            'P', Items.PRISMARINE_CRYSTALS,
                            'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianChestplate),
                            "L   L",
                            "G   G",
                            "PGGGP",
                            "GSLSG",
                            "PGGGP",
                            'G', getItemStack(materials, 1),
                            'L', Blocks.SEA_LANTERN,
                            'P', Items.PRISMARINE_CRYSTALS,
                            'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianLeggings),
                            "GPGPG",
                            "GSLSG",
                            "G   G",
                            "G   G",
                            "P   P",
                            'G', getItemStack(materials, 1),
                            'L', Blocks.SEA_LANTERN,
                            'P', Items.PRISMARINE_CRYSTALS,
                            'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianBoots),
                            "G   G",
                            "G   G",
                            "G   G",
                            "S   S",
                            "     ",
                            'G', getItemStack(materials, 1),
                            'S', Blocks.SPONGE);
                    manager.addRecipe(getItemStack(guardianBoots),
                            "     ",
                            "G   G",
                            "G   G",
                            "G   G",
                            "S   S",
                            'G', getItemStack(materials, 1),
                            'S', Blocks.SPONGE);
                }
                break;
        }
    }
}