/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableEnderDragonArmorElytra;
import static net.thedragonteam.armorplus.ARPConfig.enableEnderDragonArmorRecipes;

public class ModEnderDragonRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Ender Dragon Armor */
        if (ARPConfig.recipes == 0 && enableEnderDragonArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "EEEEE",
                    "EXXXE",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "XXXXX",
                    "XXXXX",
                    "EEEEE",
                    "EXXXE",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                    "EXXXE",
                    "EXXXE",
                    "EEEEE",
                    "EEEEE",
                    "EEEEE",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                    "EEEEE",
                    "EEEEE",
                    "EXXXE",
                    "EXXXE",
                    "EXXXE",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "EXXXE",
                    "EXXXE",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "EXXXE",
                    "EXXXE",
                    'E', ModItems.enderDragonScale);
        }
        if (ARPConfig.recipes == 1 && enableEnderDragonArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "CEEEC",
                    "ESXSE",
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "XXXXX",
                    "XXXXX",
                    "XXXXX",
                    "CEEEC",
                    "ESXSE",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            if (enableEnderDragonArmorElytra) {
                manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "SXXXS",
                        "EXXXE",
                        "EEEEE",
                        "ELSLE",
                        "EEEEE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'L', Items.ELYTRA);
            } else {
                manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "SXXXS",
                        "EXXXE",
                        "EEEEE",
                        "ECSCE",
                        "EEEEE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            }
            manager.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                    "SEEES",
                    "ECSCE",
                    "EXXXE",
                    "EXXXE",
                    "EXXXE",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "SXXXS",
                    "EXXXE",
                    "EXXXE",
                    "CXXXC",
                    "XXXXX",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "XXXXX",
                    "SXXXS",
                    "EXXXE",
                    "EXXXE",
                    "CXXXC",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
        }
    }
}
