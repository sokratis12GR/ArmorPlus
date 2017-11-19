/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes;

import net.minecraft.init.Items;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UTBShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.registry.APItems;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.api.crafting.ultitechbench.recipes.UTBRecipesHelper.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModWeaponTierThreeRecipes {
    public void addRecipes(UltiTechBenchCraftingManager manager) {
        switch (getRD()) {
            case EASY: {
                if (enableSwordsRecipes) {
                    if (enableGuardianWeapons[0]) {
                        registerSwordRecipe(manager, "scaleGuardian", guardianSword);
                    }
                    if (enableSuperStarWeapons[0]) {
                        registerSwordRecipe(manager, "witherBone", superStarSword);
                    }
                    if (enableEnderDragonWeapons[0]) {
                        registerSwordRecipe(manager, "scaleEnderDragon", enderDragonSword);
                    }
                }
                if (enableBattleAxesRecipes) {
                    if (enableGuardianWeapons[1]) {
                        registerBattleAxeRecipe(manager, "scaleGuardian", guardianBattleAxe);
                    }
                    if (enableSuperStarWeapons[1]) {
                        registerBattleAxeRecipe(manager, "witherBone", superStarBattleAxe);
                    }
                    if (enableEnderDragonWeapons[1]) {
                        registerBattleAxeRecipe(manager, "scaleEnderDragon", enderDragonBattleAxe);
                    }
                }
                if (enableBowsRecipes) {
                    if (enableGuardianWeapons[2]) {
                        registerBowRecipes(manager, "scaleGuardian", "gemPrismarine", guardianBow);
                    }
                    if (enableSuperStarWeapons[2]) {
                        registerBowRecipes(manager, "witherBone", "string", superStarBow);
                    }
                    if (enableEnderDragonWeapons[2]) {
                        registerBowRecipes(manager, "scaleEnderDragon", "string", enderDragonBow);
                    }
                }
                break;
            }
            case EXPERT:
            case HELLISH: {
                if (enableSwordsRecipes) {
                    if (enableGuardianWeapons[0]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(guardianSword),
                            "   E   ",
                            "  ESE  ",
                            " E S E ",
                            "E  S  E",
                            "   S   ",
                            "   S   ",
                            "   S   ",
                            'E', "scaleGuardian",
                            'S', "gemPrismarine"));
                    }
                    if (enableSuperStarWeapons[0]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(superStarSword),
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
                    }
                    if (enableEnderDragonWeapons[0]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(enderDragonSword),
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
                }
                if (enableBattleAxesRecipes) {
                    if (enableGuardianWeapons[1]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(guardianBattleAxe),
                            " E   E ",
                            "E  S  E",
                            " EESEE ",
                            "E  S  E",
                            " E S E ",
                            "   S   ",
                            "   S   ",
                            'E', "scaleGuardian",
                            'S', "gemPrismarine"));
                    }
                    if (enableSuperStarWeapons[1]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(superStarBattleAxe),
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
                    }
                    if (enableEnderDragonWeapons[1]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.enderDragonBattleAxe),
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
                }
                if (enableBowsRecipes) {
                    if (enableGuardianWeapons[2]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.guardianBow),
                            "    GGS",
                            "  GG  S",
                            " G    S",
                            "G     S",
                            " G    S",
                            "  GG  S",
                            "    GGS",
                            'G', "scaleGuardian",
                            'S', "gemPrismarine"));
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.guardianBow),
                            "SGG    ",
                            "S  GG  ",
                            "S    G ",
                            "S     G",
                            "S    G ",
                            "S  GG  ",
                            "SGG    ",
                            'G', "scaleGuardian",
                            'S', "gemPrismarine"));
                    }
                    if (enableSuperStarWeapons[2]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.superStarBow),
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
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.superStarBow),
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
                    }
                    if (enableEnderDragonWeapons[2]) {
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.enderDragonBow),
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
                        manager.addRecipe(new UTBShapedOreRecipe(getItemStack(APItems.enderDragonBow),
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
                }
                break;
            }
        }
    }
}