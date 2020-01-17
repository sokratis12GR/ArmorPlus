/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.common.registry.constants.APItems;
import net.minecraft.init.Items;

import static com.sofodev.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.*;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.registry.ModItems.pickaxe;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModWeaponTierThreeRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY:
                if (recipes.enableSwordsRecipes) {
                    registerSwordRecipe(manager, "scaleGuardian", guardianSword);
                    registerSwordRecipe(manager, "witherBone", superStarSword);
                    registerSwordRecipe(manager, "scaleEnderDragon", enderDragonSword);
                }
                if (recipes.enableBattleAxesRecipes) {
                    registerBattleAxeRecipe(manager, "scaleGuardian", guardianBattleAxe);
                    registerBattleAxeRecipe(manager, "witherBone", superStarBattleAxe);
                    registerBattleAxeRecipe(manager, "scaleEnderDragon", enderDragonBattleAxe);
                }
                if (recipes.enableBowsRecipes) {
                    registerBowRecipes(manager, "scaleGuardian", "gemPrismarine", guardianBow);
                    registerBowRecipes(manager, "witherBone", "string", superStarBow);
                    registerBowRecipes(manager, "scaleEnderDragon", "string", enderDragonBow);
                }
                if (recipes.enablePickaxesRecipes) {
                    registerPickaxeRecipe(manager, "scaleGuardian", pickaxe[6]);
                    registerPickaxeRecipe(manager, "witherBone", pickaxe[7]);
                    registerPickaxeRecipe(manager, "scaleEnderDragon", pickaxe[8]);
                }
                break;
            case EXPERT:
            case HELLISH:
                if (recipes.enableSwordsRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(guardianSword),
                        "   E   ",
                        "   E   ",
                        "   E   ",
                        "   E   ",
                        "   E   ",
                        " SSESS ",
                        "   S   ",
                        'S', "scaleGuardian",
                        'E', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(superStarSword),
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        " EEHEE ",
                        "   E   ",
                        'E', "witherBone",
                        'S', Items.NETHER_STAR,
                        'H', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(enderDragonSword),
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        " EESEE ",
                        "   E   ",
                        'E', "scaleEnderDragon",
                        'S', Items.DRAGON_BREATH));
                }
                if (recipes.enableBattleAxesRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(guardianBattleAxe),
                        " E   E ",
                        "E  S  E",
                        " SSSSS ",
                        "E  S  E",
                        " E S E ",
                        "   S   ",
                        "   S   ",
                        'S', "scaleGuardian",
                        'E', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(superStarBattleAxe),
                        " E   E ",
                        "E  H  E",
                        " SSSSS ",
                        "E  S  E",
                        " E S E ",
                        "   S   ",
                        "   S   ",
                        'S', "witherBone",
                        'E', Items.NETHER_STAR,
                        'H', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(enderDragonBattleAxe),
                        " E   E ",
                        "E  S  E",
                        " SSSSS ",
                        "E  S  E",
                        " E S E ",
                        "   S   ",
                        "   S   ",
                        'S', "scaleEnderDragon",
                        'E', Items.DRAGON_BREATH));
                }
                if (recipes.enableBowsRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.guardianBow),
                        "    GGS",
                        "  GG  S",
                        " G    S",
                        "G     S",
                        " G    S",
                        "  GG  S",
                        "    GGS",
                        'S', "scaleGuardian",
                        'G', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.guardianBow),
                        "SGG    ",
                        "S  GG  ",
                        "S    G ",
                        "S     G",
                        "S    G ",
                        "S  GG  ",
                        "SGG    ",
                        'S', "scaleGuardian",
                        'G', "gemPrismarine"));

                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.superStarBow),
                        "    WWS",
                        "  NW  S",
                        " N    S",
                        "N     S",
                        " N    S",
                        "  NW  S",
                        "    WWS",
                        'W', "witherBone",
                        'S', "string",
                        'N', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.superStarBow),
                        "SWW    ",
                        "S  WN  ",
                        "S    N ",
                        "S     N",
                        "S    N ",
                        "S  WN  ",
                        "SWW    ",
                        'W', "witherBone",
                        'S', "string",
                        'N', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.enderDragonBow),
                        "    EES",
                        "  DE  S",
                        " D    S",
                        "D     S",
                        " D    S",
                        "  DE  S",
                        "    EES",
                        'E', "scaleEnderDragon",
                        'S', "string",
                        'D', Items.DRAGON_BREATH));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.enderDragonBow),
                        "SEE    ",
                        "S  ED  ",
                        "S    D ",
                        "S     D",
                        "S    D ",
                        "S  ED  ",
                        "SEE    ",
                        'E', "scaleEnderDragon",
                        'S', "string",
                        'D', Items.DRAGON_BREATH));
                }
                if (recipes.enablePickaxesRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(pickaxe[6]),
                        " EEEEE ",
                        "EEESEEE",
                        "EE S EE",
                        "E  S  E",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        'S', "scaleGuardian",
                        'E', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(pickaxe[7]),
                        " EEEEE ",
                        "EEEHEEE",
                        "EE S EE",
                        "E  S  E",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        'S', "witherBone",
                        'E', Items.NETHER_STAR,
                        'H', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(pickaxe[8]),
                        " EEEEE ",
                        "EEESEEE",
                        "EE S EE",
                        "E  S  E",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        'S', "scaleEnderDragon",
                        'E', Items.DRAGON_BREATH));
                }
                break;
        }
    }
}