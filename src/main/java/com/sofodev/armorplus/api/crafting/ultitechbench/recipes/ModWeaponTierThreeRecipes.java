/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.registry.APItems;
import net.minecraft.init.Items;

import static com.sofodev.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.*;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModWeaponTierThreeRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
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
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (recipes.enableSwordsRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(guardianSword),
                        "   E   ",
                        "  ESE  ",
                        " E S E ",
                        "E  S  E",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        'E', "scaleGuardian",
                        'S', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(superStarSword),
                        "   H   ",
                        "  ESE  ",
                        " E S E ",
                        "E  S  E",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        'E', "witherBone",
                        'S', Items.NETHER_STAR,
                        'H', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(enderDragonSword),
                        "   E   ",
                        "  ESE  ",
                        " E S E ",
                        "E  S  E",
                        "   S   ",
                        "   S   ",
                        "   S   ",
                        'E', "scaleEnderDragon",
                        'S', Items.DRAGON_BREATH));
                }
                if (recipes.enableBattleAxesRecipes) {
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(guardianBattleAxe),
                        " E   E ",
                        "E  S  E",
                        " EESEE ",
                        "E  S  E",
                        " E S E ",
                        "   S   ",
                        "   S   ",
                        'E', "scaleGuardian",
                        'S', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(superStarBattleAxe),
                        " E   E ",
                        "E  H  E",
                        " EESEE ",
                        "E  S  E",
                        " E S E ",
                        "   S   ",
                        "   S   ",
                        'E', "witherBone",
                        'S', Items.NETHER_STAR,
                        'H', getItemStack(Items.SKULL, 1)));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.enderDragonBattleAxe),
                        " E   E ",
                        "E  S  E",
                        " EESEE ",
                        "E  S  E",
                        " E S E ",
                        "   S   ",
                        "   S   ",
                        'E', "scaleEnderDragon",
                        'S', Items.DRAGON_BREATH));
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
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
                    manager.addRecipe(new BaseShapedOreRecipe(7, getItemStack(APItems.guardianBow),
                        "SGG    ",
                        "S  GG  ",
                        "S    G ",
                        "S     G",
                        "S    G ",
                        "S  GG  ",
                        "SGG    ",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));

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
                break;
            }
        }
    }
}