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
        if (enableSwordsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianSword, 1),
                    "XXGXX",
                    "XGSGX",
                    "GXSXG",
                    "XXSXX",
                    "XXSXX",
                    'G', "scaleGuardian",
                    'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonSword, 1),
                    "XXEXX",
                    "XESEX",
                    "EXSXE",
                    "XXSXX",
                    "XXSXX",
                    'E', "scaleEnderDragon",
                    'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarSword, 1),
                    "XXWXX",
                    "XWSWX",
                    "WXSXW",
                    "XXSXX",
                    "XXSXX",
                    'W', "witherBone",
                    'S', Items.STICK));
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBattleAxe, 1),
                    "XGXGX",
                    "GXSXG",
                    "XGSGX",
                    "XXSXX",
                    "XXSXX",
                    'G', "scaleGuardian",
                    'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1),
                    "XEXEX",
                    "EXSXE",
                    "XESEX",
                    "XXSXX",
                    "XXSXX",
                    'E', "scaleEnderDragon",
                    'S', Items.STICK));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBattleAxe, 1),
                    "XWXWX",
                    "WXSXW",
                    "XWSWX",
                    "XXSXX",
                    "XXSXX",
                    'W', "witherBone",
                    'S', Items.STICK));
        }
        if (enableBowsRecipes && recipes == 0) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1),
                    "XXGGS",
                    "XGGXS",
                    "GXXXS",
                    "XGGXS",
                    "XXGGS",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1),
                    "SGGXX",
                    "SXGGX",
                    "SXXXG",
                    "SXGGX",
                    "SGGXX",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1),
                    "XXEES",
                    "XEEXS",
                    "EXXXS",
                    "XEEXS",
                    "XXEES",
                    'E', "scaleEnderDragon",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1),
                    "SEEXX",
                    "SXEEX",
                    "SXXXE",
                    "SXEEX",
                    "SEEXX",
                    'E', "scaleEnderDragon",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1),
                    "XXWWS",
                    "XWWXS",
                    "NXXXS",
                    "XWWXS",
                    "XXWWS",
                    'W', "witherBone",
                    'S', "string"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1),
                    "SWWXX",
                    "SXWWX",
                    "SXXXW",
                    "SXWWX",
                    "SWWXX",
                    'W', "witherBone",
                    'S', "string"));
        }
        if (enableSwordsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianSword, 1),
                    "XXGXX",
                    "XGSGX",
                    "GXSXG",
                    "XXSXX",
                    "XXSXX",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonSword, 1),
                    "XXEXX",
                    "XESEX",
                    "EXSXE",
                    "XXSXX",
                    "XXSXX",
                    'E', "scaleEnderDragon",
                    'S', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarSword, 1),
                    "XXHXX",
                    "XWSWX",
                    "WXSXW",
                    "XXSXX",
                    "XXSXX",
                    'W', "witherBone",
                    'S', Items.NETHER_STAR,
                    'H', new ItemStack(Items.SKULL, 1, 1)));
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBattleAxe, 1),
                    "XGXGX",
                    "GXSXG",
                    "XGSGX",
                    "XXSXX",
                    "XXSXX",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1),
                    "XEXEX",
                    "EXSXE",
                    "XESEX",
                    "XXSXX",
                    "XXSXX",
                    'E', "scaleEnderDragon",
                    'S', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBattleAxe, 1),
                    "XWXWX",
                    "WXHXW",
                    "XWSWX",
                    "XXSXX",
                    "XXSXX",
                    'W', "witherBone",
                    'S', Items.NETHER_STAR,
                    'H', new ItemStack(Items.SKULL, 1, 1)));
        }
        if (enableBowsRecipes && recipes == 1) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1),
                    "XXGGS",
                    "XGGXS",
                    "GXXXS",
                    "XGGXS",
                    "XXGGS",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardianBow, 1),
                    "SGGXX",
                    "SXGGX",
                    "SXXXG",
                    "SXGGX",
                    "SGGXX",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1),
                    "XXEES",
                    "XDEXS",
                    "DXXXS",
                    "XDEXS",
                    "XXEES",
                    'E', "scaleEnderDragon",
                    'S', "string",
                    'D', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderDragonBow, 1),
                    "SEEXX",
                    "SXEDX",
                    "SXXXD",
                    "SXEDX",
                    "SEEXX",
                    'E', "scaleEnderDragon",
                    'S', "string",
                    'D', Items.DRAGON_BREATH));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1),
                    "XXWWS",
                    "XNWXS",
                    "NXXXS",
                    "XNWXS",
                    "XXWWS",
                    'W', "witherBone",
                    'S', "string",
                    'N', new ItemStack(Items.SKULL, 1, 1)));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superStarBow, 1),
                    "SWWXX",
                    "SXWNX",
                    "SXXXN",
                    "SXWNX",
                    "SWWXX",
                    'W', "witherBone",
                    'S', "string",
                    'N', new ItemStack(Items.SKULL, 1, 1)));
        }
    }
}
