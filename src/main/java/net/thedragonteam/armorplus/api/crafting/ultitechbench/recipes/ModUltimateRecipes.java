/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.Utils;

import static net.thedragonteam.armorplus.APConfig.enableTheUltimateArmorRecipes;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

public class ModUltimateRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        /* Sets The Ultimate Armor Unbreakable */
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimate[0], 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimate[1], 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimate[2], 1));
        Utils.setUnbreakable(new ItemStack(ModItems.theUltimate[3], 1));

        manager.addRecipe(new ItemStack(ModBlocks.benches[2], 1),
                "LUUL ",
                "OWHO ",
                "OUUO ",
                "O  O ",
                "     ",
                'U', getItemStack(ModItems.materials, 4),
                'W', ModBlocks.benches[0],
                'H', ModBlocks.benches[1],
                'O', ModBlocks.compressedObsidian,
                'L', getItemStack(ModItems.lavaCrystal, 1));
        /* The Ultimate Armor */
        if (APConfig.enableTheUltimateArmor) {
            if (enableTheUltimateArmorRecipes) {
                manager.addRecipe(new ItemStack(ModItems.theUltimate[0], 1),
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
                manager.addRecipe(new ItemStack(ModItems.theUltimate[1], 1),
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
                manager.addRecipe(new ItemStack(ModItems.theUltimate[2], 1),
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
                manager.addRecipe(new ItemStack(ModItems.theUltimate[3], 1),
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
                //[0] Parts
                if (APConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 2),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStar[0],
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 1),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragon[0],
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 0),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardian[0],
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                //[1] Parts
                if (APConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 5),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStar[1],
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 4),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragon[1],
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 3),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardian[1],
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                //[2] Parts
                if (APConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 8),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStar[2],
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 7),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragon[2],
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 6),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardian[2],
                            'B', getItemStack(ModItems.materials, 1),
                            'L', Items.PRISMARINE_CRYSTALS,
                            'R', Items.PRISMARINE_SHARD,
                            'T', Blocks.SPONGE,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                //[3] Parts
                if (APConfig.enableSuperStarArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 11),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.superStar[3],
                            'B', getItemStack(ModItems.materials, 2),
                            'L', Blocks.SOUL_SAND,
                            'R', Blocks.NETHERRACK,
                            'T', Blocks.NETHER_BRICK,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableEnderDragonArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 10),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.enderDragon[3],
                            'B', getItemStack(ModItems.materials, 3),
                            'L', Items.ENDER_PEARL,
                            'R', Items.ENDER_EYE,
                            'T', Items.END_CRYSTAL,
                            'U', getItemStack(ModItems.materials, 4),
                            'C', getItemStack(ModItems.lavaCrystal, 1)
                    );
                if (APConfig.enableGuardianArmor)
                    manager.addRecipe(getItemStack(ModItems.theUltimateParts, 9),
                            "UUCUU",
                            "UCTCU",
                            "CLMRC",
                            "UCBCU",
                            "UUCUU",
                            'M', ModItems.guardian[3],
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