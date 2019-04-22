/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.registry.APBlocks.*;
import static com.sofodev.armorplus.common.registry.APItems.*;
import static com.sofodev.armorplus.common.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.common.registry.ModItems.*;
import static com.sofodev.armorplus.common.util.Utils.setUnbreakable;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModUltimateRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        /* Sets The Ultimate Armor Unbreakable */
        setUnbreakable(getItemStack(theUltimateHelmet));
        setUnbreakable(getItemStack(theUltimateChestplate));
        setUnbreakable(getItemStack(theUltimateLeggings));
        setUnbreakable(getItemStack(theUltimateBoots));

        manager.addRecipe(getItemStack(ultiTechBench),
            "       ",
            " LUUUL ",
            " OWOHO ",
            " OUUUO ",
            " O   O ",
            " O   O ",
            "       ",
            'U', getItemStack(materials, 4),
            'W', workbench,
            'H', highTechBench,
            'O', blockCompressedObsidian,
            'L', getItemStack(itemLavaCrystal, 1));
        /*The Ultimate Armor */
        if (recipes.enableTheUltimateArmorRecipes) {
            manager.addRecipe(getItemStack(theUltimateHelmet),
                " UUUUU ",
                "UCCLCCU",
                " UUUUU ",
                "UCCMCCU",
                " UUUUU ",
                "UCCRCCU",
                " UUUUU ",
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1),
                'L', getItemStack(theUltimateParts, 2),
                'M', getItemStack(theUltimateParts, 1),
                'R', getItemStack(theUltimateParts, 0)
            );
            manager.addRecipe(getItemStack(theUltimateChestplate),
                " UUUUU ",
                "UCCLCCU",
                " UUUUU ",
                "UCCMCCU",
                " UUUUU ",
                "UCCRCCU",
                " UUUUU ",
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1),
                'L', getItemStack(theUltimateParts, 5),
                'M', getItemStack(theUltimateParts, 4),
                'R', getItemStack(theUltimateParts, 3)
            );
            manager.addRecipe(getItemStack(theUltimateLeggings),
                " UUUUU ",
                "UCCLCCU",
                " UUUUU ",
                "UCCMCCU",
                " UUUUU ",
                "UCCRCCU",
                " UUUUU ",
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1),
                'L', getItemStack(theUltimateParts, 8),
                'M', getItemStack(theUltimateParts, 7),
                'R', getItemStack(theUltimateParts, 6)
            );
            manager.addRecipe(getItemStack(theUltimateBoots),
                " UUUUU ",
                "UCCLCCU",
                " UUUUU ",
                "UCCMCCU",
                " UUUUU ",
                "UCCRCCU",
                " UUUUU ",
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1),
                'L', getItemStack(theUltimateParts, 11),
                'M', getItemStack(theUltimateParts, 10),
                'R', getItemStack(theUltimateParts, 9)
            );
            ///Helmet Parts
            manager.addRecipe(getItemStack(theUltimateParts, 2),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', superStarHelmet,
                'B', getItemStack(materials, 2),
                'L', Blocks.SOUL_SAND,
                'R', Blocks.NETHERRACK,
                'T', Blocks.NETHER_BRICK,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 1),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', enderDragonHelmet,
                'B', getItemStack(materials, 3),
                'L', Items.ENDER_PEARL,
                'R', Items.ENDER_EYE,
                'T', Items.END_CRYSTAL,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 0),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', guardianHelmet,
                'B', getItemStack(materials, 1),
                'L', Items.PRISMARINE_CRYSTALS,
                'R', Items.PRISMARINE_SHARD,
                'T', Blocks.SPONGE,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            //Chestplate Parts
            manager.addRecipe(getItemStack(theUltimateParts, 5),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', superStarChestplate,
                'B', getItemStack(materials, 2),
                'L', Blocks.SOUL_SAND,
                'R', Blocks.NETHERRACK,
                'T', Blocks.NETHER_BRICK,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 4),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', enderDragonChestplate,
                'B', getItemStack(materials, 3),
                'L', Items.ENDER_PEARL,
                'R', Items.ENDER_EYE,
                'T', Items.END_CRYSTAL,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 3),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', guardianChestplate,
                'B', getItemStack(materials, 1),
                'L', Items.PRISMARINE_CRYSTALS,
                'R', Items.PRISMARINE_SHARD,
                'T', Blocks.SPONGE,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            //Leggings Parts
            manager.addRecipe(getItemStack(theUltimateParts, 8),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', superStarLeggings,
                'B', getItemStack(materials, 2),
                'L', Blocks.SOUL_SAND,
                'R', Blocks.NETHERRACK,
                'T', Blocks.NETHER_BRICK,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 7),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', enderDragonLeggings,
                'B', getItemStack(materials, 3),
                'L', Items.ENDER_PEARL,
                'R', Items.ENDER_EYE,
                'T', Items.END_CRYSTAL,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 6),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', guardianLeggings,
                'B', getItemStack(materials, 1),
                'L', Items.PRISMARINE_CRYSTALS,
                'R', Items.PRISMARINE_SHARD,
                'T', Blocks.SPONGE,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            //Boots Parts
            manager.addRecipe(getItemStack(theUltimateParts, 11),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', superStarBoots,
                'B', getItemStack(materials, 2),
                'L', Blocks.SOUL_SAND,
                'R', Blocks.NETHERRACK,
                'T', Blocks.NETHER_BRICK,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 10),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', enderDragonBoots,
                'B', getItemStack(materials, 3),
                'L', Items.ENDER_PEARL,
                'R', Items.ENDER_EYE,
                'T', Items.END_CRYSTAL,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
            manager.addRecipe(getItemStack(theUltimateParts, 9),
                "UUUCUUU",
                "UUCBCUU",
                "UCT RCU",
                "CL M LC",
                "UCR TCU",
                "UUCBCUU",
                "UUUCUUU",
                'M', guardianBoots,
                'B', getItemStack(materials, 1),
                'L', Items.PRISMARINE_CRYSTALS,
                'R', Items.PRISMARINE_SHARD,
                'T', Blocks.SPONGE,
                'U', getItemStack(materials, 4),
                'C', getItemStack(itemLavaCrystal, 1)
            );
        }
    }
}