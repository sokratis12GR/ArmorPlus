/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;

public class ModGuardianRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        ItemMaterial guardianScale = (ItemMaterial) Utils.getItem("guardian_scale");
        switch (getRD()) {
            case EASY: {
                UTBRecipesHelper.registerEasyArmorSetRecipes(manager, guardianScale, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
                break;
            }
            case EXPERT:
            case HELLISH: {
                manager.addRecipe(new ItemStack(guardianHelmet),
                    "GPGPGPG",
                    "GS L SG",
                    "G     G",
                    "L     L",
                    "       ",
                    "       ",
                    "       ",
                    'G', new ItemStack(guardianScale),
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(guardianHelmet),
                    "       ",
                    "GPGPGPG",
                    "GS L SG",
                    "G     G",
                    "L     L",
                    "       ",
                    "       ",
                    'G', new ItemStack(guardianScale),
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(guardianHelmet),
                    "       ",
                    "       ",
                    "GPGPGPG",
                    "GS L SG",
                    "G     G",
                    "L     L",
                    "       ",
                    'G', new ItemStack(guardianScale),
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(guardianHelmet),
                    "       ",
                    "       ",
                    "       ",
                    "GPGPGPG",
                    "GS L SG",
                    "G     G",
                    "L     L",
                    'G', new ItemStack(guardianScale),
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(guardianChestplate),
                    "L     L",
                    "G     G",
                    "G     G",
                    "PGGSGGP",
                    "GGSLSGG",
                    "GGSLSGG",
                    "PGGSGGP",
                    'G', new ItemStack(guardianScale),
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(guardianLeggings),
                    "GPGGGPG",
                    "GSSLSSG",
                    "G     G",
                    "G     G",
                    "G     G",
                    "G     G",
                    "P     P",
                    'G', new ItemStack(guardianScale),
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS,
                    'S', Blocks.PRISMARINE);
                manager.addRecipe(new ItemStack(guardianBoots),
                    "G     G",
                    "G     G",
                    "G     G",
                    "S     S",
                    "       ",
                    "       ",
                    "       ",
                    'G', new ItemStack(guardianScale),
                    'S', Blocks.SPONGE);
                manager.addRecipe(new ItemStack(guardianBoots),
                    "       ",
                    "G     G",
                    "G     G",
                    "G     G",
                    "S     S",
                    "       ",
                    "       ",
                    'G', new ItemStack(guardianScale),
                    'S', Blocks.SPONGE);
                manager.addRecipe(new ItemStack(guardianBoots),
                    "       ",
                    "       ",
                    "G     G",
                    "G     G",
                    "G     G",
                    "S     S",
                    "       ",
                    'G', new ItemStack(guardianScale),
                    'S', Blocks.SPONGE);
                manager.addRecipe(new ItemStack(guardianBoots),
                    "       ",
                    "       ",
                    "       ",
                    "G     G",
                    "G     G",
                    "G     G",
                    "S     S",
                    'G', new ItemStack(guardianScale),
                    'S', Blocks.SPONGE);
            }
            break;
        }
    }
}