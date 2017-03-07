/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.util.Utils;

import static net.thedragonteam.armorplus.APConfig.enableTheUltimateArmor;
import static net.thedragonteam.armorplus.APConfig.enableTheUltimateArmorRecipes;
import static net.thedragonteam.armorplus.registry.APBlocks.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModUltimateRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Sets The Ultimate Armor Unbreakable */
        Utils.INSTANCE.setUnbreakable(getItemStack(theUltimateHelmet));
        Utils.INSTANCE.setUnbreakable(getItemStack(theUltimateChestplate));
        Utils.INSTANCE.setUnbreakable(getItemStack(theUltimateLeggings));
        Utils.INSTANCE.setUnbreakable(getItemStack(theUltimateBoots));

        manager.addRecipe(getItemStack(ultiTechBench),
                "LUUL ",
                "OWHO ",
                "OUUO ",
                "O  O ",
                "     ",
                'U', getItemStack(materials, 4),
                'W', workbench,
                'H', highTechBench,
                'O', compressedObsidian,
                'L', getItemStack(lavaCrystal, 1));
        /* The Ultimate Armor */
        if (enableTheUltimateArmor && enableTheUltimateArmorRecipes) {
            manager.addRecipe(getItemStack(theUltimateHelmet),
                    "UUCUU",
                    "UC CU",
                    "CLMRC",
                    "UC CU",
                    "UUCUU",
                    'U', getItemStack(materials, 4),
                    'C', getItemStack(lavaCrystal, 1),
                    'L', getItemStack(theUltimateParts, 2),
                    'M', getItemStack(theUltimateParts, 1),
                    'R', getItemStack(theUltimateParts, 0)
            );
            manager.addRecipe(getItemStack(theUltimateChestplate),
                    "UUCUU",
                    "UC CU",
                    "CLMRC",
                    "UC CU",
                    "UUCUU",
                    'U', getItemStack(materials, 4),
                    'C', getItemStack(lavaCrystal, 1),
                    'L', getItemStack(theUltimateParts, 5),
                    'M', getItemStack(theUltimateParts, 4),
                    'R', getItemStack(theUltimateParts, 3)
            );
            manager.addRecipe(getItemStack(theUltimateLeggings),
                    "UUCUU",
                    "UC CU",
                    "CLMRC",
                    "UC CU",
                    "UUCUU",
                    'U', getItemStack(materials, 4),
                    'C', getItemStack(lavaCrystal, 1),
                    'L', getItemStack(theUltimateParts, 8),
                    'M', getItemStack(theUltimateParts, 7),
                    'R', getItemStack(theUltimateParts, 6)
            );
            manager.addRecipe(getItemStack(theUltimateBoots),
                    "UUCUU",
                    "UC CU",
                    "CLMRC",
                    "UC CU",
                    "UUCUU",
                    'U', getItemStack(materials, 4),
                    'C', getItemStack(lavaCrystal, 1),
                    'L', getItemStack(theUltimateParts, 11),
                    'M', getItemStack(theUltimateParts, 10),
                    'R', getItemStack(theUltimateParts, 9)
            );
            //Helmet Parts
            if (APConfig.enableSuperStarArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 2),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', superStarHelmet,
                        'B', getItemStack(materials, 2),
                        'L', Blocks.SOUL_SAND,
                        'R', Blocks.NETHERRACK,
                        'T', Blocks.NETHER_BRICK,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableEnderDragonArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 1),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', enderDragonHelmet,
                        'B', getItemStack(materials, 3),
                        'L', Items.ENDER_PEARL,
                        'R', Items.ENDER_EYE,
                        'T', Items.END_CRYSTAL,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableGuardianArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 0),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', guardianHelmet,
                        'B', getItemStack(materials, 1),
                        'L', Items.PRISMARINE_CRYSTALS,
                        'R', Items.PRISMARINE_SHARD,
                        'T', Blocks.SPONGE,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            //Chestplate Parts
            if (APConfig.enableSuperStarArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 5),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', superStarChestplate,
                        'B', getItemStack(materials, 2),
                        'L', Blocks.SOUL_SAND,
                        'R', Blocks.NETHERRACK,
                        'T', Blocks.NETHER_BRICK,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableEnderDragonArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 4),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', enderDragonChestplate,
                        'B', getItemStack(materials, 3),
                        'L', Items.ENDER_PEARL,
                        'R', Items.ENDER_EYE,
                        'T', Items.END_CRYSTAL,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableGuardianArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 3),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', guardianChestplate,
                        'B', getItemStack(materials, 1),
                        'L', Items.PRISMARINE_CRYSTALS,
                        'R', Items.PRISMARINE_SHARD,
                        'T', Blocks.SPONGE,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            //Leggings Parts
            if (APConfig.enableSuperStarArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 8),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', superStarLeggings,
                        'B', getItemStack(materials, 2),
                        'L', Blocks.SOUL_SAND,
                        'R', Blocks.NETHERRACK,
                        'T', Blocks.NETHER_BRICK,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableEnderDragonArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 7),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', enderDragonLeggings,
                        'B', getItemStack(materials, 3),
                        'L', Items.ENDER_PEARL,
                        'R', Items.ENDER_EYE,
                        'T', Items.END_CRYSTAL,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableGuardianArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 6),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', guardianLeggings,
                        'B', getItemStack(materials, 1),
                        'L', Items.PRISMARINE_CRYSTALS,
                        'R', Items.PRISMARINE_SHARD,
                        'T', Blocks.SPONGE,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            //Boots Parts
            if (APConfig.enableSuperStarArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 11),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', superStarBoots,
                        'B', getItemStack(materials, 2),
                        'L', Blocks.SOUL_SAND,
                        'R', Blocks.NETHERRACK,
                        'T', Blocks.NETHER_BRICK,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableEnderDragonArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 10),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', enderDragonBoots,
                        'B', getItemStack(materials, 3),
                        'L', Items.ENDER_PEARL,
                        'R', Items.ENDER_EYE,
                        'T', Items.END_CRYSTAL,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
            if (APConfig.enableGuardianArmor)
                manager.addRecipe(getItemStack(theUltimateParts, 9),
                        "UUCUU",
                        "UCTCU",
                        "CLMRC",
                        "UCBCU",
                        "UUCUU",
                        'M', guardianBoots,
                        'B', getItemStack(materials, 1),
                        'L', Items.PRISMARINE_CRYSTALS,
                        'R', Items.PRISMARINE_SHARD,
                        'T', Blocks.SPONGE,
                        'U', getItemStack(materials, 4),
                        'C', getItemStack(lavaCrystal, 1)
                );
        }
    }
}