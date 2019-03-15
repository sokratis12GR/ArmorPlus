/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.ultitechbench.recipes;

import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.registry.APItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.sofodev.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.*;
import static com.sofodev.armorplus.config.ModConfig.getRD;
import static com.sofodev.armorplus.registry.APItems.*;
import static net.minecraft.init.Items.WITHER_SKELETON_SKULL;

public class ModWeaponTierThreeRecipes {
    public void addRecipes(BaseCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                registerSwordRecipe(manager, "scaleGuardian", guardianSword);
                registerSwordRecipe(manager, "witherBone", superStarSword);
                registerSwordRecipe(manager, "scaleEnderDragon", enderDragonSword);
                registerBattleAxeRecipe(manager, "scaleGuardian", guardianBattleAxe);
                registerBattleAxeRecipe(manager, "witherBone", superStarBattleAxe);
                registerBattleAxeRecipe(manager, "scaleEnderDragon", enderDragonBattleAxe);
                registerBowRecipes(manager, "scaleGuardian", "gemPrismarine", guardianBow);
                registerBowRecipes(manager, "witherBone", "string", superStarBow);
                registerBowRecipes(manager, "scaleEnderDragon", "string", enderDragonBow);
                break;
            }
            case EXPERT:
            case HELLISH: {
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(guardianSword),
                    "   E   ",
                    "  ESE  ",
                    " E S E ",
                    "E  S  E",
                    "   S   ",
                    "   S   ",
                    "   S   ",
                    'E', "scaleGuardian",
                    'S', "gemPrismarine"));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(superStarSword),
                    "   H   ",
                    "  ESE  ",
                    " E S E ",
                    "E  S  E",
                    "   S   ",
                    "   S   ",
                    "   S   ",
                    'E', "witherBone",
                    'S', Items.NETHER_STAR,
                    'H', new ItemStack(WITHER_SKELETON_SKULL)));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(enderDragonSword),
                    "   E   ",
                    "  ESE  ",
                    " E S E ",
                    "E  S  E",
                    "   S   ",
                    "   S   ",
                    "   S   ",
                    'E', "scaleEnderDragon",
                    'S', Items.DRAGON_BREATH));

                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(guardianBattleAxe),
                    " E   E ",
                    "E  S  E",
                    " EESEE ",
                    "E  S  E",
                    " E S E ",
                    "   S   ",
                    "   S   ",
                    'E', "scaleGuardian",
                    'S', "gemPrismarine"));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(superStarBattleAxe),
                    " E   E ",
                    "E  H  E",
                    " EESEE ",
                    "E  S  E",
                    " E S E ",
                    "   S   ",
                    "   S   ",
                    'E', "witherBone",
                    'S', Items.NETHER_STAR,
                    'H', new ItemStack(Items.WITHER_SKELETON_SKULL)));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.enderDragonBattleAxe),
                    " E   E ",
                    "E  S  E",
                    " EESEE ",
                    "E  S  E",
                    " E S E ",
                    "   S   ",
                    "   S   ",
                    'E', "scaleEnderDragon",
                    'S', Items.DRAGON_BREATH));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.guardianBow),
                    "    GGS",
                    "  GG  S",
                    " G    S",
                    "G     S",
                    " G    S",
                    "  GG  S",
                    "    GGS",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.guardianBow),
                    "SGG    ",
                    "S  GG  ",
                    "S    G ",
                    "S     G",
                    "S    G ",
                    "S  GG  ",
                    "SGG    ",
                    'G', "scaleGuardian",
                    'S', "gemPrismarine"));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.superStarBow),
                    "    WWS",
                    "  NW  S",
                    " N    S",
                    "N     S",
                    " N    S",
                    "  NW  S",
                    "    WWS",
                    'W', "witherBone",
                    'S', "string",
                    'N', new ItemStack(Items.WITHER_SKELETON_SKULL)));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.superStarBow),
                    "SWW    ",
                    "S  WN  ",
                    "S    N ",
                    "S     N",
                    "S    N ",
                    "S  WN  ",
                    "SWW    ",
                    'W', "witherBone",
                    'S', "string",
                    'N', new ItemStack(Items.WITHER_SKELETON_SKULL)));
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.enderDragonBow),
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
                manager.addRecipe(new BaseShapedOreRecipe(7, new ItemStack(APItems.enderDragonBow),
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