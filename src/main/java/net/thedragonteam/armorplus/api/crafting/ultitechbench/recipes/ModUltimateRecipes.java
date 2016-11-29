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
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

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
                'U', getItemStack(ModItems.materials, 4),
                'W', ModBlocks.arpWorkbench,
                'H', ModBlocks.arpHighTechBench,
                'O', ModBlocks.compressedObsidian,
                'L', getItemStack(ModItems.lavaCrystal, 1));
        /* The Ultimate Armor */
        if (ARPConfig.enableTheUltimateArmor) {
            if (enableTheUltimateArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.theUltimateHelmet, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', getItemStack(ModItems.materials, 4),
                        'C', getItemStack(ModItems.lavaCrystal, 1),
                        'L', getItemStack(ModItems.theUltimateParts, 2),
                        'M', getItemStack(ModItems.theUltimateParts, 1),
                        'R', getItemStack(ModItems.theUltimateParts, 0)
                );
                manager.addRecipe(new ItemStack(ModItems.theUltimateChestplate, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', getItemStack(ModItems.materials, 4),
                        'C', getItemStack(ModItems.lavaCrystal, 1),
                        'L', getItemStack(ModItems.theUltimateParts, 5),
                        'M', getItemStack(ModItems.theUltimateParts, 4),
                        'R', getItemStack(ModItems.theUltimateParts, 3)
                );
                manager.addRecipe(new ItemStack(ModItems.theUltimateLeggings, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', getItemStack(ModItems.materials, 4),
                        'C', getItemStack(ModItems.lavaCrystal, 1),
                        'L', getItemStack(ModItems.theUltimateParts, 8),
                        'M', getItemStack(ModItems.theUltimateParts, 7),
                        'R', getItemStack(ModItems.theUltimateParts, 6)
                );
                manager.addRecipe(new ItemStack(ModItems.theUltimateBoots, 1),
                        "UUCUU",
                        "UC CU",
                        "CLMRC",
                        "UC CU",
                        "UUCUU",
                        'U', getItemStack(ModItems.materials, 4),
                        'C', getItemStack(ModItems.lavaCrystal, 1),
                        'L', getItemStack(ModItems.theUltimateParts, 11),
                        'M', getItemStack(ModItems.theUltimateParts, 10),
                        'R', getItemStack(ModItems.theUltimateParts, 9)
                );
                //Helmet Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 2),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarHelmet,
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonHelmet,
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 0),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianHelmet,
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                //Chestplate Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 5),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarChestplate,
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 4),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonChestplate,
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 3),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianChestplate,
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                //Leggings Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 8),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarLeggings,
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 7),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonLeggings,
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 6),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianLeggings,
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                //Boots Parts
                if (ARPConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 11),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStarBoots,
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 10),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragonBoots,
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (ARPConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 9),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardianBoots,
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
            }
        }
    }
}