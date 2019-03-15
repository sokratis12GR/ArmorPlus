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

public class ModSuperStarRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        ItemMaterial witherBone = (ItemMaterial) Utils.getItem("wither_bone");
        switch (getRD()) {
            case EASY: {
                UTBRecipesHelper.registerEasyArmorSetRecipes(manager, witherBone, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
                break;
            }
            case EXPERT:
            case HELLISH: {
                manager.addRecipe(new ItemStack(superStarHelmet),
                    "SWWNWWS",
                    "WN   NW",
                    "W     W",
                    "N     N",
                    "       ",
                    "       ",
                    "       ",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarHelmet),
                    "       ",
                    "SWWNWWS",
                    "WN   NW",
                    "W     W",
                    "N     N",
                    "       ",
                    "       ",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarHelmet),
                    "       ",
                    "       ",
                    "SWWNWWS",
                    "WN   NW",
                    "W     W",
                    "N     N",
                    "       ",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarHelmet),
                    "       ",
                    "       ",
                    "       ",
                    "SWWNWWS",
                    "WN   NW",
                    "W     W",
                    "N     N",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarChestplate),
                    "N     N",
                    "N     N",
                    "W     W",
                    "WNSNSNW",
                    "WWNHNWW",
                    "WWSNSWW",
                    "WWWWWWW",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND,
                    'H', new ItemStack(Items.WITHER_SKELETON_SKULL));
                manager.addRecipe(new ItemStack(superStarLeggings),
                    "SWWSWWS",
                    "WSSNSSW",
                    "N     N",
                    "W     W",
                    "N     N",
                    "W     W",
                    "N     N",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarBoots),
                    "N     N",
                    "W     W",
                    "W     W",
                    "S     S",
                    "       ",
                    "       ",
                    "       ",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarBoots),
                    "       ",
                    "N     N",
                    "W     W",
                    "W     W",
                    "S     S",
                    "       ",
                    "       ",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarBoots),
                    "       ",
                    "       ",
                    "N     N",
                    "W     W",
                    "W     W",
                    "S     S",
                    "       ",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                manager.addRecipe(new ItemStack(superStarBoots),
                    "       ",
                    "       ",
                    "       ",
                    "N     N",
                    "W     W",
                    "W     W",
                    "S     S",
                    'W', new ItemStack(witherBone),
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
                break;
            }
        }
    }
}