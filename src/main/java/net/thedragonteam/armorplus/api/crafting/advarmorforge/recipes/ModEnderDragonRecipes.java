/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.advarmorforge.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.advarmorforge.AdvancedArmorForgeCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableEnderDragonArmorElytra;
import static net.thedragonteam.armorplus.ARPConfig.enableEnderDragonArmorRecipes;

public class ModEnderDragonRecipes {

    public void addRecipes(AdvancedArmorForgeCraftingManager manager) {
        /* Ender Dragon Armor */
        if (ARPConfig.recipes == 0 && enableEnderDragonArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "EEEE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "EEEE",
                    "EXXE",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                    "EXXE",
                    "EEEE",
                    "EEEE",
                    "EEEE",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                    "EEEE",
                    "EEEE",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.enderDragonScale);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "XXXX",
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.enderDragonScale);
        }
        if (ARPConfig.recipes == 1 && enableEnderDragonArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "CEEC",
                    "ESSE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addRecipe(new ItemStack(ModItems.enderDragonHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "CEEC",
                    "ESSE",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            if (!enableEnderDragonArmorElytra) {
                manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "SXXS",
                        "EEEE",
                        "ECCE",
                        "EEEE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            } else if (enableEnderDragonArmorElytra) {
                manager.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "SXXS",
                        "EEEE",
                        "ELLE",
                        "EEEE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'L', Items.ELYTRA);
            }
            manager.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                    "SEES",
                    "ECCE",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "SXXS",
                    "EXXE",
                    "CXXC",
                    "XXXX",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "XXXX",
                    "SXXS",
                    "EXXE",
                    "CXXC",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            manager.addShapelessRecipe(new ItemStack(ModItems.theUltimateMaterial, 1),
                    ModItems.enderDragonScale,
                    ModItems.guardianScale,
                    ModItems.witherBone,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
        }
    }

}
