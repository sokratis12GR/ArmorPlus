/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ModWeaponTierThreeRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
     /*   if (enableSwordsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianSword, 1), "XGX", "XGX", "XSX", 'G', "scaleGuardian", 'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonSword, 1), "XEX", "XEX", "XSX", 'E', "scaleEnderDragon", 'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarSword, 1), "XWX", "XWX", "XSX", 'W', "witherBone", 'S', Items.STICK));
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBattleAxe, 1), "GXG", "GSG", "XSX", 'G', "scaleGuardian", 'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1), "EXE", "ESE", "XSX", 'E', "scaleEnderDragon", 'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBattleAxe, 1), "WXW", "WSW", "XSX", 'W', "witherBone", 'S', Items.STICK));
        }
        if (enableBowsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "XGS", "GXS", "XGS", 'G', "scaleGuardian", 'S', Items.STRING));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "SGX", "SXG", "SGX", 'G', "scaleGuardian", 'S', Items.STRING));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "XES", "EXS", "XES", 'E', "scaleEnderDragon", 'S', Items.STRING));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "SEX", "SXE", "SEX", 'E', "scaleEnderDragon", 'S', Items.STRING));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "XWS", "WXS", "XWS", 'W', "witherBone", 'S', Items.STRING));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "SWX", "SXW", "SWX", 'W', "witherBone", 'S', Items.STRING));
        }
*/
        if (enableSwordsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianSword, 1), "XXGXX", "XGSGX", "GXSXG", "XXSXX", "XXSXX", 'G', "scaleGuardian", 'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonSword, 1), "XXEXX", "XESEX", "EXSXE", "XXSXX", "XXSXX", 'E', "scaleEnderDragon", 'S', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarSword, 1), "XXHXX", "XWSWX", "WXSXW", "XXSXX", "XXSXX", 'W', "witherBone", 'S', Items.NETHER_STAR, 'H', new ItemStack(Items.SKULL, 1, 1)));
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBattleAxe, 1), "XGXGX", "GXSXG", "XGSGX", "XXSXX", "XXSXX", 'G', "scaleGuardian", 'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1), "XEXEX", "EXSXE", "XESEX", "XXSXX", "XXSXX", 'E', "scaleEnderDragon", 'S', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBattleAxe, 1), "XWXWX", "WXHXW", "XWSWX", "XXSXX", "XXSXX", 'W', "witherBone", 'S', Items.NETHER_STAR, 'H', new ItemStack(Items.SKULL, 1, 1)));
        }
       /* if (enableBowsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "XGS", "GXS", "XGS", 'G', "scaleGuardian", 'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1), "SGX", "SXG", "SGX", 'G', "scaleGuardian", 'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "XES", "DXS", "XES", 'E', "scaleEnderDragon", 'S', Items.STRING, 'D', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1), "SEX", "SXD", "SEX", 'E', "scaleEnderDragon", 'S', Items.STRING, 'D', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "XWS", "NXS", "XWS", 'W', "witherBone", 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1)));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1), "SWX", "SXN", "SWX", 'W', "witherBone", 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1)));
        }*/
    }
}
