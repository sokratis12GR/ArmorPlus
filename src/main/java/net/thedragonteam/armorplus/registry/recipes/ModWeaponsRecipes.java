/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchRegistry;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ModWeaponsRecipes {
    public static void addEasyWeaponsRecipes() {
        ItemStack LAPIS_LAZULI = new ItemStack(Items.DYE, 1, 4);
        if (enableSwordsRecipes && recipes == 0) {
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalSword, 1), "XCX", "XCX", "XSX", 'C', Items.COAL, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisSword, 1), "XLX", "XLX", "XSX", 'L', LAPIS_LAZULI, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneSword, 1), "XRX", "XRX", "XSX", 'R', Items.REDSTONE, 'S', Items.STICK));

            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianSword, 1), "XGX", "XGX", "XSX", 'G', ModItems.guardianScale, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonSword, 1), "XEX", "XEX", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarSword, 1), "XWX", "XWX", "XSX", 'W', ModItems.witherBone, 'S', Items.STICK));
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "CXC", "CSC", "XSX", 'C', Items.COAL, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "LXL", "LSL", "XSX", 'L', LAPIS_LAZULI, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "RXR", "RSR", "XSX", 'R', Items.REDSTONE, 'S', Items.STICK));

            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBattleAxe, 1), "GXG", "GSG", "XSX", 'G', ModItems.guardianScale, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1), "EXE", "ESE", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBattleAxe, 1), "WXW", "WSW", "XSX", 'W', ModItems.witherBone, 'S', Items.STICK));
        }
        if (enableBowsRecipes && recipes == 0) {
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "XCS", "CXS", "XCS", 'C', Items.COAL, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "SCX", "SXC", "SCX", 'C', Items.COAL, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "XLS", "LXS", "XLS", 'L', LAPIS_LAZULI, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "SLX", "SXL", "SLX", 'L', LAPIS_LAZULI, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "XRS", "RXS", "XRS", 'R', Items.REDSTONE, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "SRX", "SXR", "SRX", 'R', Items.REDSTONE, 'S', Items.STRING));

            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "XGS", "GXS", "XGS", 'G', ModItems.guardianScale, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "SGX", "SXG", "SGX", 'G', ModItems.guardianScale, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "XES", "EXS", "XES", 'E', ModItems.enderDragonScale, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "SEX", "SXE", "SEX", 'E', ModItems.enderDragonScale, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "XWS", "WXS", "XWS", 'W', ModItems.witherBone, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "SWX", "SXW", "SWX", 'W', ModItems.witherBone, 'S', Items.STRING));

        }
    }

    public static void addExpertWeaponsRecipes() {
        if (enableSwordsRecipes && recipes == 1) {
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalSword, 1), "XCX", "XCX", "XSX", 'C', Blocks.COAL_BLOCK, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisSword, 1), "XLX", "XLX", "XSX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneSword, 1), "XRX", "XRX", "XSX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK));

            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianSword, 1), "XGX", "XGX", "XSX", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonSword, 1), "XEX", "XEX", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.DRAGON_BREATH));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarSword, 1), "XWX", "XWX", "XSX", 'W', ModItems.witherBone, 'S', Items.NETHER_STAR));
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "CXC", "CSC", "XSX", 'C', Blocks.COAL_BLOCK, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "LXL", "LSL", "XSX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STICK));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "RXR", "RSR", "XSX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK));

            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBattleAxe, 1), "GXG", "GSG", "XSX", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1), "EXE", "ESE", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.DRAGON_BREATH));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBattleAxe, 1), "WXW", "WSW", "XSX", 'W', ModItems.witherBone, 'S', Items.NETHER_STAR));
        }
        if (enableBowsRecipes && recipes == 1) {
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "XCS", "CXS", "XCS", 'C', Blocks.COAL_BLOCK, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBow, 1), "SCX", "SXC", "SCX", 'C', Blocks.COAL_BLOCK, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "XLS", "LXS", "XLS", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBow, 1), "SLX", "SXL", "SLX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "XRS", "RXS", "XRS", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBow, 1), "SRX", "SXR", "SRX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING));

            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "XGS", "GXS", "XGS", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "SGX", "SXG", "SGX", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "XES", "DXS", "XES", 'E', ModItems.enderDragonScale, 'S', Items.STRING, 'D', Items.DRAGON_BREATH));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "SEX", "SXD", "SEX", 'E', ModItems.enderDragonScale, 'S', Items.STRING, 'D', Items.DRAGON_BREATH));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "XWS", "NXS", "XWS", 'W', ModItems.witherBone, 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1)));
            WorkbenchRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "SWX", "SXN", "SWX", 'W', ModItems.witherBone, 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1)));
        }
    }
}
