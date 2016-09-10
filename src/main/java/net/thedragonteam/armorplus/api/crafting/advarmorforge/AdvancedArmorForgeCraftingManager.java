/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.advarmorforge;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.Utils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.api.crafting.advarmorforge
 * ArmorPlus created by sokratis12GR on 6/19/2016 12:29PM.
 * - TheDragonTeam
 */
public class AdvancedArmorForgeCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final AdvancedArmorForgeCraftingManager INSTANCE = new AdvancedArmorForgeCraftingManager();
    private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

    private AdvancedArmorForgeCraftingManager() {

        // ===================================== Set Variables =====================================

        ItemStack enderDragonHelmet = new ItemStack(ModItems.enderDragonHelmet, 1);
        ItemStack guardianHelmet = new ItemStack(ModItems.guardianHelmet, 1);
        ItemStack superStarHelmet = new ItemStack(ModItems.superStarHelmet, 1);
        ItemStack theUltimateHelmet = new ItemStack(ModItems.theUltimateHelmet, 1);

        /* Sets The Ultimate Armor Unbreakable */
        Utils.setUnbreakable(theUltimateHelmet);
        ItemStack theUltimateChestplate = new ItemStack(ModItems.theUltimateChestplate, 1);
        Utils.setUnbreakable(theUltimateChestplate);
        ItemStack theUltimateLeggings = new ItemStack(ModItems.theUltimateLeggings, 1);
        Utils.setUnbreakable(theUltimateLeggings);
        ItemStack theUltimateBoots = new ItemStack(ModItems.theUltimateBoots, 1);
        Utils.setUnbreakable(theUltimateBoots);

        /* The Ultimate Armor */
        if (enableTheUltimateArmorRecipes) {
            this.addShapelessRecipe(theUltimateHelmet,
                    ModItems.theUltimateHelmetLeft,
                    ModItems.theUltimateHelmetMiddle,
                    ModItems.theUltimateHelmetRight,
                    ModItems.theUltimateMaterial);
            this.addShapelessRecipe(theUltimateChestplate,
                    ModItems.theUltimateChestplateLeft,
                    ModItems.theUltimateChestplateMiddle,
                    ModItems.theUltimateChestplateRight,
                    ModItems.theUltimateMaterial);
            this.addShapelessRecipe(theUltimateLeggings,
                    ModItems.theUltimateLeggingsLeft,
                    ModItems.theUltimateLeggingsMiddle,
                    ModItems.theUltimateLeggingsRight,
                    ModItems.theUltimateMaterial);
            this.addShapelessRecipe(theUltimateBoots,
                    ModItems.theUltimateBootsLeft,
                    ModItems.theUltimateBootsMiddle,
                    ModItems.theUltimateBootsRight,
                    ModItems.theUltimateMaterial);
            //Helmet Parts
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmetLeft, 1),
                    ModItems.superStarHelmet,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmetMiddle, 1),
                    ModItems.enderDragonHelmet,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateHelmetRight, 1),
                    ModItems.guardianHelmet,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            //Chestplate Parts
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplateLeft, 1),
                    ModItems.superStarChestplate,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplateMiddle, 1),
                    ModItems.enderDragonChestplate,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateChestplateRight, 1),
                    ModItems.guardianChestplate,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            //Leggings Parts
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggingsLeft, 1),
                    ModItems.superStarLeggings,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggingsMiddle, 1),
                    ModItems.enderDragonLeggings,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateLeggingsRight, 1),
                    ModItems.guardianLeggings,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            //Boots Parts
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateBootsLeft, 1),
                    ModItems.superStarBoots,
                    ModItems.witherBone,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateBootsMiddle, 1),
                    ModItems.enderDragonBoots,
                    ModItems.enderDragonScale,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateBootsRight, 1),
                    ModItems.guardianBoots,
                    ModItems.guardianScale,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    ModItems.theUltimateMaterial,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
        }

        /* Super Star Armor */
        if (ARPConfig.recipes == 0 && enableSuperStarArmorRecipes) {
            this.addRecipe(superStarHelmet,
                    "WWWW",
                    "WNNW",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            this.addRecipe(superStarHelmet,
                    "XXXX",
                    "XXXX",
                    "WWWW",
                    "WNNW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                    "WXXW",
                    "WWWW",
                    "WNNW",
                    "WWWW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                    "WWWW",
                    "WNNW",
                    "WXXW",
                    "WXXW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "WXXW",
                    "NXXN",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "XXXX",
                    "XXXX",
                    "WXXW",
                    "NXXN",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR);
        }
        if (ARPConfig.recipes == 1 && enableSuperStarArmorRecipes) {
            this.addRecipe(superStarHelmet,
                    "SWWS",
                    "WNNW",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(superStarHelmet,
                    "XXXX",
                    "XXXX",
                    "SWWS",
                    "WNNW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.superStarChestplate, 1),
                    "SXXS",
                    "WWWW",
                    "SNNS",
                    "WWWW",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.superStarLeggings, 1),
                    "SWWS",
                    "WNNW",
                    "WXXW",
                    "SXXS",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "SXXS",
                    "WXXW",
                    "NXXN",
                    "XXXX",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.superStarBoots, 1),
                    "XXXX",
                    "SXXS",
                    "WXXW",
                    "NXXN",
                    'W', ModItems.witherBone,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
        }

        /* Guardian Armor */
        if (ARPConfig.recipes == 0 && enableGuardianArmorRecipes) {
            this.addRecipe(guardianHelmet,
                    "GGGG",
                    "GXXG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale);
            this.addRecipe(guardianHelmet,
                    "XXXX",
                    "XXXX",
                    "GGGG",
                    "GXXG",
                    'G', ModItems.guardianScale);
            this.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "GXXG",
                    "GGGG",
                    "GGGG",
                    "GGGG",
                    'G', ModItems.guardianScale);
            this.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GGGG",
                    "GGGG",
                    "GXXG",
                    "GXXG",
                    'G', ModItems.guardianScale);
            this.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "GXXG",
                    "GXXG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale);
            this.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "XXXX",
                    "XXXX",
                    "GXXG",
                    "GXXG",
                    'G', ModItems.guardianScale);
        }
        if (ARPConfig.recipes == 1 && enableGuardianArmorRecipes) {
            this.addRecipe(guardianHelmet,
                    "GPPG",
                    "GLLG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS)
            ;
            this.addRecipe(guardianHelmet,
                    "XXXX",
                    "XXXX",
                    "GPPG",
                    "GLLG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            this.addRecipe(new ItemStack(ModItems.guardianChestplate, 1),
                    "GXXG",
                    "GPPG",
                    "PLLP",
                    "GPPG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            this.addRecipe(new ItemStack(ModItems.guardianLeggings, 1),
                    "GPPG",
                    "GLLG",
                    "PXXP",
                    "GXXG",
                    'G', ModItems.guardianScale,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            this.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "GXXG",
                    "SXXS",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
            this.addRecipe(new ItemStack(ModItems.guardianBoots, 1),
                    "XXXX",
                    "XXXX",
                    "GXXG",
                    "SXXS",
                    'G', ModItems.guardianScale,
                    'S', Blocks.SPONGE);
        }

        /* Ender Dragon Armor */
        if (ARPConfig.recipes == 0 && enableEnderDragonArmorRecipes) {
            this.addRecipe(enderDragonHelmet,
                    "EEEE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.enderDragonScale);
            this.addRecipe(enderDragonHelmet,
                    "XXXX",
                    "XXXX",
                    "EEEE",
                    "EXXE",
                    'E', ModItems.enderDragonScale);
            this.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                    "EXXE",
                    "EEEE",
                    "EEEE",
                    "EEEE",
                    'E', ModItems.enderDragonScale);
            this.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                    "EEEE",
                    "EEEE",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.enderDragonScale);
            this.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.enderDragonScale);
            this.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "XXXX",
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.enderDragonScale);
        }
        if (ARPConfig.recipes == 1 && enableEnderDragonArmorRecipes) {
            this.addRecipe(enderDragonHelmet,
                    "CEEC",
                    "ESSE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addRecipe(enderDragonHelmet,
                    "XXXX",
                    "XXXX",
                    "CEEC",
                    "ESSE",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            if (!enableEnderDragonArmorElytra) {
                this.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "SXXS",
                        "EEEE",
                        "ECCE",
                        "EEEE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            } else if (enableEnderDragonArmorElytra) {
                this.addRecipe(new ItemStack(ModItems.enderDragonChestplate, 1),
                        "SXXS",
                        "EEEE",
                        "ELLE",
                        "EEEE",
                        'E', ModItems.enderDragonScale,
                        'S', Items.ENDER_EYE,
                        'L', Items.ELYTRA);
            }
            this.addRecipe(new ItemStack(ModItems.enderDragonLeggings, 1),
                    "SEES",
                    "ECCE",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "SXXS",
                    "EXXE",
                    "CXXC",
                    "XXXX",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.enderDragonBoots, 1),
                    "XXXX",
                    "SXXS",
                    "EXXE",
                    "CXXC",
                    'E', ModItems.enderDragonScale,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.theUltimateMaterial, 1),
                    ModItems.enderDragonScale,
                    ModItems.guardianScale,
                    ModItems.witherBone,
                    new ItemStack(ModItems.lavaCrystal, 1, 1));
        }

        Collections.sort(this.recipes, new Comparator<IRecipe>() {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
                return p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes ? 1 : (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes ? -1 : (p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() ? -1 : (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0)));
            }
        });
    }

    /**
     * Returns the
     * static instance of
     * this class
     */

    public static AdvancedArmorForgeCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public ShapedRecipes addRecipe(ItemStack stack, Object... recipeComponents) {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[]) {
            String[] astring = (String[]) ((String[]) recipeComponents[i++]);

            for (int l = 0; l < astring.length; ++l) {
                String s2 = astring[l];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        } else {
            while (recipeComponents[i] instanceof String) {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2) {
            Character character = (Character) recipeComponents[i];
            ItemStack itemstack = null;

            if (recipeComponents[i + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[i + 1]);
            } else if (recipeComponents[i + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            } else if (recipeComponents[i + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1) {
            char c0 = s.charAt(i1);

            if (map.containsKey(Character.valueOf(c0))) {
                aitemstack[i1] = ((ItemStack) map.get(Character.valueOf(c0))).copy();
            } else {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */

    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.<ItemStack>newArrayList();

        for (Object object : recipeComponents) {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item) object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block) object));
            }
        }

        this.recipes.add(new ShapelessRecipes(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    @Nullable
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getCraftingResult(craftMatrix);
            }
        }

        return null;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        ItemStack[] aitemstack = new ItemStack[craftMatrix.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            aitemstack[i] = craftMatrix.getStackInSlot(i);
        }

        return aitemstack;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}