/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.Utils;

import static net.thedragonteam.armorplus.ARPConfig.enableTheUltimateArmorRecipes;

public class ModUltimateRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {

        /* Sets The Ultimate Armor Unbreakable */
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateHelmet, 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateChestplate, 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateLeggings, 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimateBoots, 1));

        manager.addRecipe(new ItemStack(ModBlocks.arpUltiTechBench, 1),
                "LUUL ",
                "OWHO ",
                "OUUO ",
                "O  O ",
                "     ",
                'U', ModItems.theUltimateMaterial,
                'W', ModBlocks.arpWorkbench,
                'H', ModBlocks.arpHighTechBench,
                'O', ModBlocks.compressedObsidian,
                'L', new ItemStack(ModItems.lavaCrystal, 1, 1));

        /* The Ultimate Armor */
        if (ARPConfig.enableTheUltimateArmor) {
            if (enableTheUltimateArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.theUltimateHelmet, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', ModItems.theUltimateMaterial,
                        'C', new ItemStack(ModItems.lavaCrystal, 1, 1),
                        'L', ModItems.theUltimateHelmetLeft,
                        'M', ModItems.theUltimateHelmetMiddle,
                        'R', ModItems.theUltimateHelmetRight);
                manager.addRecipe(new ItemStack(ModItems.theUltimateChestplate, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', ModItems.theUltimateMaterial,
                        'C', new ItemStack(ModItems.lavaCrystal, 1, 1),
                        'L', ModItems.theUltimateChestplateLeft,
                        'M', ModItems.theUltimateChestplateMiddle,
                        'R', ModItems.theUltimateChestplateRight);
                manager.addRecipe(new ItemStack(ModItems.theUltimateLeggings, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', ModItems.theUltimateMaterial,
                        'C', new ItemStack(ModItems.lavaCrystal, 1, 1),
                        'L', ModItems.theUltimateLeggingsLeft,
                        'M', ModItems.theUltimateLeggingsMiddle,
                        'R', ModItems.theUltimateLeggingsRight);
                manager.addRecipe(new ItemStack(ModItems.theUltimateBoots, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', ModItems.theUltimateMaterial,
                        'C', new ItemStack(ModItems.lavaCrystal, 1, 1),
                        'L', ModItems.theUltimateBootsLeft,
                        'M', ModItems.theUltimateBootsMiddle,
                        'R', ModItems.theUltimateBootsRight);
                //Helmet Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateHelmetLeft, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarHelmet,
                            'B', ModItems.witherBone,
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateHelmetMiddle, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonHelmet,
                            'B', ModItems.enderDragonScale,
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateHelmetRight, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianHelmet,
                            'B', ModItems.guardianScale,
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                //Chestplate Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateChestplateLeft, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarChestplate,
                            'B', ModItems.witherBone,
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateChestplateMiddle, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonChestplate,
                            'B', ModItems.enderDragonScale,
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateChestplateRight, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianChestplate,
                            'B', ModItems.guardianScale,
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                //Leggings Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateLeggingsLeft, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarLeggings,
                            'B', ModItems.witherBone,
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateLeggingsMiddle, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonLeggings,
                            'B', ModItems.enderDragonScale,
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateLeggingsRight, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianLeggings,
                            'B', ModItems.guardianScale,
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                //Boots Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateBootsLeft, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarBoots,
                            'B', ModItems.witherBone,
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateBootsMiddle, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonBoots,
                            'B', ModItems.enderDragonScale,
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(new ItemStack(ModItems.theUltimateBootsRight, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianBoots,
                            'B', ModItems.guardianScale,
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', ModItems.theUltimateMaterial,
                            'C', new ItemStack(ModItems.lavaCrystal, 1, 1));
            }
        }
    }
}