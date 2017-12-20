/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.registerEasyArmorSetRecipes;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModGuardianRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (enableGuardianArmor && enableGuardianArmorRecipes) {
                    registerEasyArmorSetRecipes(manager, 1, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (enableGuardianArmor && enableGuardianArmorRecipes) {
                    manager.addRecipe(getItemStack(guardianHelmet),
                        "GPGPGPG",
                        "GS L SG",
                        "G     G",
                        "L     L",
                        "       ",
                        "       ",
                        "       ",
                        'G', getItemStack(materials, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianHelmet),
                        "       ",
                        "GPGPGPG",
                        "GS L SG",
                        "G     G",
                        "L     L",
                        "       ",
                        "       ",
                        'G', getItemStack(materials, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianHelmet),
                        "       ",
                        "       ",
                        "GPGPGPG",
                        "GS L SG",
                        "G     G",
                        "L     L",
                        "       ",
                        'G', getItemStack(materials, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianHelmet),
                        "       ",
                        "       ",
                        "       ",
                        "GPGPGPG",
                        "GS L SG",
                        "G     G",
                        "L     L",
                        'G', getItemStack(materials, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianChestplate),
                        "L     L",
                        "G     G",
                        "G     G",
                        "PGGSGGP",
                        "GGSLSGG",
                        "GGSLSGG",
                        "PGGSGGP",
                        'G', getItemStack(materials, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianLeggings),
                        "GPGGGPG",
                        "GSSLSSG",
                        "G     G",
                        "G     G",
                        "G     G",
                        "G     G",
                        "P     P",
                        'G', getItemStack(materials, 1),
                        'L', Blocks.SEA_LANTERN,
                        'P', Items.PRISMARINE_CRYSTALS,
                        'S', Blocks.PRISMARINE);
                    manager.addRecipe(getItemStack(guardianBoots),
                        "G     G",
                        "G     G",
                        "G     G",
                        "S     S",
                        "       ",
                        "       ",
                        "       ",
                        'G', getItemStack(materials, 1),
                        'S', Blocks.SPONGE);
                    manager.addRecipe(getItemStack(guardianBoots),
                        "       ",
                        "G     G",
                        "G     G",
                        "G     G",
                        "S     S",
                        "       ",
                        "       ",
                        'G', getItemStack(materials, 1),
                        'S', Blocks.SPONGE);
                    manager.addRecipe(getItemStack(guardianBoots),
                        "       ",
                        "       ",
                        "G     G",
                        "G     G",
                        "G     G",
                        "S     S",
                        "       ",
                        'G', getItemStack(materials, 1),
                        'S', Blocks.SPONGE);
                    manager.addRecipe(getItemStack(guardianBoots),
                        "       ",
                        "       ",
                        "       ",
                        "G     G",
                        "G     G",
                        "G     G",
                        "S     S",
                        'G', getItemStack(materials, 1),
                        'S', Blocks.SPONGE);
                }
                break;
            }
        }
    }
}