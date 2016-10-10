/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.advarmorforge.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.advarmorforge.AdvancedArmorForgeCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.enableGuardianArmorRecipes;

public class ModGuardianRecipes {
    public void addRecipes(AdvancedArmorForgeCraftingManager manager) {

        /* Guardian Armor */
        if (ARPConfig.recipes == 0 && enableGuardianArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "GGGG",
                    "GXXG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "GGGG",
                    "GXXG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "GXXG",
                    "GGGG",
                    "GGGG",
                    "GGGG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GGGG",
                    "GGGG",
                    "GXXG",
                    "GXXG",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "GXXG",
                    "GXXG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "XXXX",
                    "XXXX",
                    "GXXG",
                    "GXXG",
                    'G', ModItems.guardianScale);
        }
        if (ARPConfig.recipes == 1 && enableGuardianArmorRecipes) {
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "GPPG",
                    "GLLG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS)
            ;
            manager.addRecipe(new ItemStack(ModItems.guardianHelmet, 1),
                    "XXXX",
                    "XXXX",
                    "GPPG",
                    "GLLG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            manager.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "GXXG",
                    "GPPG",
                    "PLLP",
                    "GPPG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            manager.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GPPG",
                    "GLLG",
                    "PXXP",
                    "GXXG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "GXXG",
                    "SXXS",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
            manager.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "XXXX",
                    "XXXX",
                    "GXXG",
                    "SXXS",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
        }
    }
}
