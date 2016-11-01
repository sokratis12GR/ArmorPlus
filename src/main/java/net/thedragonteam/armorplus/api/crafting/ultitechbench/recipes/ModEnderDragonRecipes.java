/*
 * Copyright (c) TheDragonTeam 2016.
 */

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
        if (ARPConfig.enableEnderDragonArmor) {
            if (ARPConfig.recipes == 0 && enableEnderDragonArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                        "EEEEE",
                        "E   E",
                        "     ",
                        "     ",
                        "     ",
                        'E', ModItems.enderDragonScale);
                manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                        "     ",
                        "     ",
                        "EEEEE",
                        "E   E",
                        'E', ModItems.enderDragonScale);
                manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "E   E",
                        "E   E",
                        "EEEEE",
                        "EEEEE",
                        "EEEEE",
                        'E', ModItems.enderDragonScale);
                manager.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                        "EEEEE",
                        "EEEEE",
                        "E   E",
                        "E   E",
                        "E   E",
                        'E', ModItems.enderDragonScale);
                manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                        "E   E",
                        "E   E",
                        "     ",
                        "     ",
                        "     ",
                        'E', ModItems.enderDragonScale);
                manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                        "     ",
                        "     ",
                        "     ",
                        "E   E",
                        "E   E",
                        'E', ModItems.enderDragonScale);
            }
            if (ARPConfig.recipes == 1 && enableEnderDragonArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                        "CEEEC",
                        "ES SE",
                        "     ",
                        "     ",
                        "     ",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                        "     ",
                        "     ",
                        "     ",
                        "CEEEC",
                        "ES SE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                if (enableEnderDragonArmorElytra) {
                    manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                            "S   S",
                            "E   E",
                            "EEEEE",
                            "ELSLE",
                            "EEEEE",
                            'E', ModItems.enderDragonScale,
                            'S', Items.ENDER_EYE,
                            'L', Items.ELYTRA);
                } else {
                    manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                            "S   S",
                            "E   E",
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
                        "E   E",
                        "E   E",
                        "E   E",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                        "S   S",
                        "E   E",
                        "E   E",
                        "C   C",
                        "     ",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
                manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                        "     ",
                        "S   S",
                        "E   E",
                        "E   E",
                        "C   C",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            }
        }
    }
}
