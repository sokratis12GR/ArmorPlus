/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.APItems;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.*;

public class ModWeaponTierThreeRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        if (enableSwordsRecipes && getRD() == EASY) {
            if (enableGuardianSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianSword, 1),
                        "  G  ",
                        " GSG ",
                        "G S G",
                        "  S  ",
                        "  S  ",
                        'G', "scaleGuardian",
                        'S', Items.STICK));
            }
            if (enableSuperStarSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarSword, 1),
                        "  W  ",
                        " WSW ",
                        "W S W",
                        "  S  ",
                        "  S  ",
                        'W', "witherBone",
                        'S', Items.STICK));
            }
            if (enableEnderDragonSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonSword, 1),
                        "  E  ",
                        " ESE ",
                        "E S E",
                        "  S  ",
                        "  S  ",
                        'E', "scaleEnderDragon",
                        'S', Items.STICK));
            }
        }
        if (enableBattleAxesRecipes && getRD() == EASY) {
            if (enableGuardianBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianBattleAxe, 1),
                        " G G ",
                        "G S G",
                        " GSG ",
                        "  S  ",
                        "  S  ",
                        'G', "scaleGuardian",
                        'S', Items.STICK));
            }
            if (enableSuperStarBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarBattleAxe, 1),
                        " W W ",
                        "W S W",
                        " WSW ",
                        "  S  ",
                        "  S  ",
                        'W', "witherBone",
                        'S', Items.STICK));
            }
            if (enableEnderDragonBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonBattleAxe, 1),
                        " E E ",
                        "E S E",
                        " ESE ",
                        "  S  ",
                        "  S  ",
                        'E', "scaleEnderDragon",
                        'S', Items.STICK));
            }
        }
        if (enableBowsRecipes && getRD() == EASY) {
            if (enableGuardianBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianBow, 1),
                        "  GGS",
                        " GG S",
                        "G   S",
                        " GG S",
                        "  GGS",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianBow, 1),
                        "SGG  ",
                        "S GG ",
                        "S   G",
                        "S GG ",
                        "SGG  ",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
            }
            if (enableSuperStarBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarBow, 1),
                        "  WWS",
                        " WW S",
                        "N   S",
                        " WW S",
                        "  WWS",
                        'W', "witherBone",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarBow, 1),
                        "SWW  ",
                        "S WW ",
                        "S   W",
                        "S WW ",
                        "SWW  ",
                        'W', "witherBone",
                        'S', "string"));
            }
            if (enableEnderDragonBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonBow, 1),
                        "  EES",
                        " EE S",
                        "E   S",
                        " EE S",
                        "  EES",
                        'E', "scaleEnderDragon",
                        'S', "string"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonBow, 1),
                        "SEE  ",
                        "S EE ",
                        "S   E",
                        "S EE ",
                        "SEE  ",
                        'E', "scaleEnderDragon",
                        'S', "string"));
            }
        }
        if (enableSwordsRecipes && getRD() == EXPERT) {
            if (enableGuardianSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianSword, 1),
                        "  G  ",
                        " GSG ",
                        "G S G",
                        "  S  ",
                        "  S  ",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
            }
            if (enableSuperStarSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarSword, 1),
                        "  H  ",
                        " WSW ",
                        "W S W",
                        "  S  ",
                        "  S  ",
                        'W', "witherBone",
                        'S', Items.NETHER_STAR,
                        'H', new ItemStack(Items.SKULL, 1, 1)));
            }
            if (enableEnderDragonSword) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonSword, 1),
                        "  E  ",
                        " ESE ",
                        "E S E",
                        "  S  ",
                        "  S  ",
                        'E', "scaleEnderDragon",
                        'S', Items.DRAGON_BREATH));
            }
        }
        if (enableBattleAxesRecipes && getRD() == EXPERT) {
            if (enableGuardianBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianBattleAxe, 1),
                        " G G ",
                        "G S G",
                        " GSG ",
                        "  S  ",
                        "  S  ",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
            }
            if (enableSuperStarBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarBattleAxe, 1),
                        " W W ",
                        "W H W",
                        " WSW ",
                        "  S  ",
                        "  S  ",
                        'W', "witherBone",
                        'S', Items.NETHER_STAR,
                        'H', new ItemStack(Items.SKULL, 1, 1)));
            }
            if (enableEnderDragonBattleAxe) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonBattleAxe, 1),
                        " E E ",
                        "E S E",
                        " ESE ",
                        "  S  ",
                        "  S  ",
                        'E', "scaleEnderDragon",
                        'S', Items.DRAGON_BREATH));
            }
        }
        if (enableBowsRecipes && getRD() == EXPERT) {
            if (enableGuardianBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianBow, 1),
                        "  GGS",
                        " GG S",
                        "G   S",
                        " GG S",
                        "  GGS",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.guardianBow, 1),
                        "SGG  ",
                        "S GG ",
                        "S   G",
                        "S GG ",
                        "SGG  ",
                        'G', "scaleGuardian",
                        'S', "gemPrismarine"));
            }
            if (enableSuperStarBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarBow, 1),
                        "  WWS",
                        " NW S",
                        "N   S",
                        " NW S",
                        "  WWS",
                        'W', "witherBone",
                        'S', "string",
                        'N', new ItemStack(Items.SKULL, 1, 1)));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.superStarBow, 1),
                        "SWW  ",
                        "S WN ",
                        "S   N",
                        "S WN ",
                        "SWW  ",
                        'W', "witherBone",
                        'S', "string",
                        'N', new ItemStack(Items.SKULL, 1, 1)));
            }
            if (enableEnderDragonBow) {
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonBow, 1),
                        "  EES",
                        " DE S",
                        "D   S",
                        " DE S",
                        "  EES",
                        'E', "scaleEnderDragon",
                        'S', "string",
                        'D', Items.DRAGON_BREATH));
                manager.addRecipe(new ShapedOreRecipe(new ItemStack(APItems.enderDragonBow, 1),
                        "SEE  ",
                        "S ED ",
                        "S   D",
                        "S ED ",
                        "SEE  ",
                        'E', "scaleEnderDragon",
                        'S', "string",
                        'D', Items.DRAGON_BREATH));
            }
        }
    }
}
