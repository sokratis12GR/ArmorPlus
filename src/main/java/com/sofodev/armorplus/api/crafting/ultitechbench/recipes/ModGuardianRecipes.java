/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModGuardianRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (recipes.enableGuardianArmorRecipes) {
                    UTBRecipesHelper.registerEasyArmorSetRecipes(manager, 1, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (recipes.enableGuardianArmorRecipes) {
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