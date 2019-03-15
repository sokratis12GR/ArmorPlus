/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.util.Utils.setUnbreakable;

public class ModUltimateRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        /* Sets The Ultimate Armor Unbreakable */
        setUnbreakable(new ItemStack(theUltimateHelmet));
        setUnbreakable(new ItemStack(theUltimateChestplate));
        setUnbreakable(new ItemStack(theUltimateLeggings));
        setUnbreakable(new ItemStack(theUltimateBoots));

        //  manager.addRecipe(new ItemStack(ultiTechBench),
        //      "       ",
        //      " LUUUL ",
        //      " OWOHO ",
        //      " OUUUO ",
        //      " O   O ",
        //      " O   O ",
        //      "       ",
        //      'U', new ItemStack(theUltimateMaterial),
        //      'W', workbench,
        //      'H', highTechBench,
        //      'O', blockCompressedObsidian,
        //      'L', new ItemStack(itemInfusedLavaCrystal));
        //  /* The Ultimate Armor */
        //  if (global_registry.enableTheUltimateArmor && recipes.enableTheUltimateArmorRecipes) {
        //      manager.addRecipe(new ItemStack(theUltimateHelmet),
        //          " UUUUU ",
        //          "UCCLCCU",
        //          " UUUUU ",
        //          "UCCMCCU",
        //          " UUUUU ",
        //          "UCCRCCU",
        //          " UUUUU ",
        //          'U', new ItemStack(theUltimateMaterial),
        //          'C', new ItemStack(itemInfusedLavaCrystal),
        //          'L', new ItemStack(theUltimateParts[0]),
        //          'M', new ItemStack(theUltimateParts[1]),
        //          'R', new ItemStack(theUltimateParts[2])
        //      );
        //      manager.addRecipe(new ItemStack(theUltimateChestplate),
        //          " UUUUU ",
        //          "UCCLCCU",
        //          " UUUUU ",
        //          "UCCMCCU",
        //          " UUUUU ",
        //          "UCCRCCU",
        //          " UUUUU ",
        //          'U', new ItemStack(theUltimateMaterial),
        //          'C', new ItemStack(itemInfusedLavaCrystal),
        //          'L', new ItemStack(theUltimateParts[3]),
        //          'M', new ItemStack(theUltimateParts[4]),
        //          'R', new ItemStack(theUltimateParts[5])
        //      );
        //      manager.addRecipe(new ItemStack(theUltimateLeggings),
        //          " UUUUU ",
        //          "UCCLCCU",
        //          " UUUUU ",
        //          "UCCMCCU",
        //          " UUUUU ",
        //          "UCCRCCU",
        //          " UUUUU ",
        //          'U', new ItemStack(theUltimateMaterial),
        //          'C', new ItemStack(itemInfusedLavaCrystal),
        //          'L', new ItemStack(theUltimateParts[6]),
        //          'M', new ItemStack(theUltimateParts[7]),
        //          'R', new ItemStack(theUltimateParts[8])
        //      );
        //      manager.addRecipe(new ItemStack(theUltimateBoots),
        //          " UUUUU ",
        //          "UCCLCCU",
        //          " UUUUU ",
        //          "UCCMCCU",
        //          " UUUUU ",
        //          "UCCRCCU",
        //          " UUUUU ",
        //          'U', new ItemStack(theUltimateMaterial),
        //          'C', new ItemStack(itemInfusedLavaCrystal),
        //          'L', new ItemStack(theUltimateParts[9]),
        //          'M', new ItemStack(theUltimateParts[10]),
        //          'R', new ItemStack(theUltimateParts[11])
        //      );
        //      ////Helmet Parts
        //      if (global_registry.enableSuperStarArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[0]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', superStarHelmet,
        //              'B', new ItemStack(witherBone),
        //              'L', Blocks.SOUL_SAND,
        //              'R', Blocks.NETHERRACK,
        //              'T', Blocks.NETHER_BRICKS,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableEnderDragonArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[1]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', enderDragonHelmet,
        //              'B', new ItemStack(enderDragonScale),
        //              'L', Items.ENDER_PEARL,
        //              'R', Items.ENDER_EYE,
        //              'T', Items.END_CRYSTAL,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableGuardianArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[2]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', guardianHelmet,
        //              'B', new ItemStack(guardianScale),
        //              'L', Items.PRISMARINE_CRYSTALS,
        //              'R', Items.PRISMARINE_SHARD,
        //              'T', Blocks.SPONGE,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      //Chestplate Parts
        //      if (global_registry.enableSuperStarArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[3]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', superStarChestplate,
        //              'B', new ItemStack(witherBone),
        //              'L', Blocks.SOUL_SAND,
        //              'R', Blocks.NETHERRACK,
        //              'T', Blocks.NETHER_BRICKS,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableEnderDragonArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[4]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', enderDragonChestplate,
        //              'B', new ItemStack(enderDragonScale),
        //              'L', Items.ENDER_PEARL,
        //              'R', Items.ENDER_EYE,
        //              'T', Items.END_CRYSTAL,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableGuardianArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[5]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', guardianChestplate,
        //              'B', new ItemStack(guardianScale),
        //              'L', Items.PRISMARINE_CRYSTALS,
        //              'R', Items.PRISMARINE_SHARD,
        //              'T', Blocks.SPONGE,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      //Leggings Parts
        //      if (global_registry.enableSuperStarArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[6]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', superStarLeggings,
        //              'B', new ItemStack(witherBone),
        //              'L', Blocks.SOUL_SAND,
        //              'R', Blocks.NETHERRACK,
        //              'T', Blocks.NETHER_BRICKS,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableEnderDragonArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[7]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', enderDragonLeggings,
        //              'B', new ItemStack(enderDragonScale),
        //              'L', Items.ENDER_PEARL,
        //              'R', Items.ENDER_EYE,
        //              'T', Items.END_CRYSTAL,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableGuardianArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[8]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', guardianLeggings,
        //              'B', new ItemStack(guardianScale),
        //              'L', Items.PRISMARINE_CRYSTALS,
        //              'R', Items.PRISMARINE_SHARD,
        //              'T', Blocks.SPONGE,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      //Boots Parts
        //      if (global_registry.enableSuperStarArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[9]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', superStarBoots,
        //              'B', new ItemStack(witherBone),
        //              'L', Blocks.SOUL_SAND,
        //              'R', Blocks.NETHERRACK,
        //              'T', Blocks.NETHER_BRICKS,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableEnderDragonArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[10]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', enderDragonBoots,
        //              'B', new ItemStack(enderDragonScale),
        //              'L', Items.ENDER_PEARL,
        //              'R', Items.ENDER_EYE,
        //              'T', Items.END_CRYSTAL,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //      if (global_registry.enableGuardianArmor) {
        //          manager.addRecipe(new ItemStack(theUltimateParts[11]),
        //              "UUUCUUU",
        //              "UUCBCUU",
        //              "UCT RCU",
        //              "CL M LC",
        //              "UCR TCU",
        //              "UUCBCUU",
        //              "UUUCUUU",
        //              'M', guardianBoots,
        //              'B', new ItemStack(guardianScale),
        //              'L', Items.PRISMARINE_CRYSTALS,
        //              'R', Items.PRISMARINE_SHARD,
        //              'T', Blocks.SPONGE,
        //              'U', new ItemStack(theUltimateMaterial),
        //              'C', new ItemStack(itemInfusedLavaCrystal)
        //          );
        //      }
        //  }
    }
}