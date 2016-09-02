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

        ItemStack ENDER_DRAGON_HELMET = new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1);
        ItemStack GUARDIAN_HELMET = new ItemStack(ModItems.GUARDIAN_HELMET, 1);
        ItemStack SUPER_STAR_HELMET = new ItemStack(ModItems.SUPER_STAR_HELMET, 1);
        ItemStack THE_ULTIMATE_HELMET = new ItemStack(ModItems.THE_ULTIMATE_HELMET, 1);

        /* Sets The Ultimate Armor Unbreakable */
        Utils.setUnbreakable(THE_ULTIMATE_HELMET);
        ItemStack THE_ULTIMATE_CHESTPLATE = new ItemStack(ModItems.THE_ULTIMATE_CHESTPLATE, 1);
        Utils.setUnbreakable(THE_ULTIMATE_CHESTPLATE);
        ItemStack THE_ULTIMATE_LEGGINGS = new ItemStack(ModItems.THE_ULTIMATE_LEGGINGS, 1);
        Utils.setUnbreakable(THE_ULTIMATE_LEGGINGS);
        ItemStack THE_ULTIMATE_BOOTS = new ItemStack(ModItems.THE_ULTIMATE_BOOTS, 1);
        Utils.setUnbreakable(THE_ULTIMATE_BOOTS);

        /* The Ultimate Armor */
        if (enableTheUltimateArmorRecipes) {
            this.addShapelessRecipe(THE_ULTIMATE_HELMET,
                    ModItems.THE_ULTIMATE_HELMET_LEFT,
                    ModItems.THE_ULTIMATE_HELMET_MIDDLE,
                    ModItems.THE_ULTIMATE_HELMET_RIGHT,
                    ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(THE_ULTIMATE_CHESTPLATE,
                    ModItems.THE_ULTIMATE_CHESTPLATE_LEFT,
                    ModItems.THE_ULTIMATE_CHESTPLATE_MIDDLE,
                    ModItems.THE_ULTIMATE_CHESTPLATE_RIGHT,
                    ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(THE_ULTIMATE_LEGGINGS,
                    ModItems.THE_ULTIMATE_LEGGINGS_LEFT,
                    ModItems.THE_ULTIMATE_LEGGINGS_MIDDLE,
                    ModItems.THE_ULTIMATE_LEGGINGS_RIGHT,
                    ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(THE_ULTIMATE_BOOTS,
                    ModItems.THE_ULTIMATE_BOOTS_LEFT,
                    ModItems.THE_ULTIMATE_BOOTS_MIDDLE,
                    ModItems.THE_ULTIMATE_BOOTS_RIGHT,
                    ModItems.THE_ULTIMATE_MATERIAL);
            //Helmet Parts
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_HELMET_LEFT, 1),
                    ModItems.SUPER_STAR_HELMET,
                    ModItems.WITHER_BONE,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_HELMET_MIDDLE, 1),
                    ModItems.ENDER_DRAGON_HELMET,
                    ModItems.ENDER_DRAGON_SCALE,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_HELMET_RIGHT, 1),
                    ModItems.GUARDIAN_HELMET,
                    ModItems.GUARDIAN_SCALE,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            //Chestplate Parts
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_CHESTPLATE_LEFT, 1),
                    ModItems.SUPER_STAR_CHESTPLATE,
                    ModItems.WITHER_BONE,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_CHESTPLATE_MIDDLE, 1),
                    ModItems.ENDER_DRAGON_CHESTPLATE,
                    ModItems.ENDER_DRAGON_SCALE,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_CHESTPLATE_RIGHT, 1),
                    ModItems.GUARDIAN_CHESTPLATE,
                    ModItems.GUARDIAN_SCALE,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            //Leggings Parts
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_LEGGINGS_LEFT, 1),
                    ModItems.SUPER_STAR_LEGGINGS,
                    ModItems.WITHER_BONE,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_LEGGINGS_MIDDLE, 1),
                    ModItems.ENDER_DRAGON_LEGGINGS,
                    ModItems.ENDER_DRAGON_SCALE,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_LEGGINGS_RIGHT, 1),
                    ModItems.GUARDIAN_LEGGINGS,
                    ModItems.GUARDIAN_SCALE,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            //Boots Parts
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_BOOTS_LEFT, 1),
                    ModItems.SUPER_STAR_BOOTS,
                    ModItems.WITHER_BONE,
                    Blocks.SOUL_SAND,
                    Blocks.NETHERRACK,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_BOOTS_MIDDLE, 1),
                    ModItems.ENDER_DRAGON_BOOTS,
                    ModItems.ENDER_DRAGON_SCALE,
                    Items.ENDER_PEARL,
                    Items.ENDER_EYE,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_BOOTS_RIGHT, 1),
                    ModItems.GUARDIAN_BOOTS,
                    ModItems.GUARDIAN_SCALE,
                    Items.PRISMARINE_CRYSTALS,
                    Items.PRISMARINE_SHARD,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.THE_ULTIMATE_MATERIAL,
                    ModItems.LAVA_CRYSTAL);
        }

        /* Super Star Armor */
        if (ARPConfig.recipes == 0 && enableSuperStarArmorRecipes) {
            this.addRecipe(SUPER_STAR_HELMET,
                    "WWWW",
                    "WNNW",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR);
            this.addRecipe(SUPER_STAR_HELMET,
                    "XXXX",
                    "XXXX",
                    "WWWW",
                    "WNNW",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_CHESTPLATE, 1),
                    "WXXW",
                    "WWWW",
                    "WNNW",
                    "WWWW",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_LEGGINGS, 1),
                    "WWWW",
                    "WNNW",
                    "WXXW",
                    "WXXW",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1),
                    "WXXW",
                    "NXXN",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1),
                    "XXXX",
                    "XXXX",
                    "WXXW",
                    "NXXN",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR);
        }
        if (ARPConfig.recipes == 1 && enableSuperStarArmorRecipes) {
            this.addRecipe(SUPER_STAR_HELMET,
                    "SWWS",
                    "WNNW",
                    "XXXX",
                    "XXXX",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(SUPER_STAR_HELMET,
                    "XXXX",
                    "XXXX",
                    "SWWS",
                    "WNNW",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_CHESTPLATE, 1),
                    "SXXS",
                    "WWWW",
                    "SNNS",
                    "WWWW",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_LEGGINGS, 1),
                    "SWWS",
                    "WNNW",
                    "WXXW",
                    "SXXS",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1),
                    "SXXS",
                    "WXXW",
                    "NXXN",
                    "XXXX",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1),
                    "XXXX",
                    "SXXS",
                    "WXXW",
                    "NXXN",
                    'W', ModItems.WITHER_BONE,
                    'N', Items.NETHER_STAR,
                    'S', Blocks.SOUL_SAND);
        }

        /* Guardian Armor */
        if (ARPConfig.recipes == 0 && enableGuardianArmorRecipes) {
            this.addRecipe(GUARDIAN_HELMET,
                    "GGGG",
                    "GXXG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(GUARDIAN_HELMET,
                    "XXXX",
                    "XXXX",
                    "GGGG",
                    "GXXG",
                    'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_CHESTPLATE, 1),
                    "GXXG",
                    "GGGG",
                    "GGGG",
                    "GGGG",
                    'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_LEGGINGS, 1),
                    "GGGG",
                    "GGGG",
                    "GXXG",
                    "GXXG",
                    'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1),
                    "GXXG",
                    "GXXG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1),
                    "XXXX",
                    "XXXX",
                    "GXXG",
                    "GXXG",
                    'G', ModItems.GUARDIAN_SCALE);
        }
        if (ARPConfig.recipes == 1 && enableGuardianArmorRecipes) {
            this.addRecipe(GUARDIAN_HELMET,
                    "GPPG",
                    "GLLG",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.GUARDIAN_SCALE,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS)
            ;
            this.addRecipe(GUARDIAN_HELMET,
                    "XXXX",
                    "XXXX",
                    "GPPG",
                    "GLLG",
                    'G', ModItems.GUARDIAN_SCALE,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_CHESTPLATE, 1),
                    "GXXG",
                    "GPPG",
                    "PLLP",
                    "GPPG",
                    'G', ModItems.GUARDIAN_SCALE,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_LEGGINGS, 1),
                    "GPPG",
                    "GLLG",
                    "PXXP",
                    "GXXG",
                    'G', ModItems.GUARDIAN_SCALE,
                    'L', Blocks.SEA_LANTERN,
                    'P', Items.PRISMARINE_CRYSTALS);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1),
                    "GXXG",
                    "SXXS",
                    "XXXX",
                    "XXXX",
                    'G', ModItems.GUARDIAN_SCALE,
                    'S', Blocks.SPONGE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1),
                    "XXXX",
                    "XXXX",
                    "GXXG",
                    "SXXS",
                    'G', ModItems.GUARDIAN_SCALE,
                    'S', Blocks.SPONGE);
        }

        /* Ender Dragon Armor */
        if (ARPConfig.recipes == 0 && enableEnderDragonArmorRecipes) {
            this.addRecipe(ENDER_DRAGON_HELMET,
                    "EEEE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(ENDER_DRAGON_HELMET,
                    "XXXX",
                    "XXXX",
                    "EEEE",
                    "EXXE",
                    'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_CHESTPLATE, 1),
                    "EXXE",
                    "EEEE",
                    "EEEE",
                    "EEEE",
                    'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_LEGGINGS, 1),
                    "EEEE",
                    "EEEE",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1),
                    "EXXE",
                    "EXXE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1),
                    "XXXX",
                    "XXXX",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.ENDER_DRAGON_SCALE);
        }
        if (ARPConfig.recipes == 1 && enableEnderDragonArmorRecipes) {
            this.addRecipe(ENDER_DRAGON_HELMET,
                    "CEEC",
                    "ESSE",
                    "XXXX",
                    "XXXX",
                    'E', ModItems.ENDER_DRAGON_SCALE,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addRecipe(ENDER_DRAGON_HELMET,
                    "XXXX",
                    "XXXX",
                    "CEEC",
                    "ESSE",
                    'E', ModItems.ENDER_DRAGON_SCALE,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            if (!enableEnderDragonArmorElytra) {
                this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_CHESTPLATE, 1),
                        "SXXS",
                        "EEEE",
                        "ECCE",
                        "EEEE",
                        'E', ModItems.ENDER_DRAGON_SCALE,
                        'S', Items.ENDER_EYE,
                        'C', Items.END_CRYSTAL);
            } else if (enableEnderDragonArmorElytra) {
                this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_CHESTPLATE, 1),
                        "SXXS",
                        "EEEE",
                        "ELLE",
                        "EEEE",
                        'E', ModItems.ENDER_DRAGON_SCALE,
                        'S', Items.ENDER_EYE,
                        'L', Items.ELYTRA);
            }
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_LEGGINGS, 1),
                    "SEES",
                    "ECCE",
                    "EXXE",
                    "EXXE",
                    'E', ModItems.ENDER_DRAGON_SCALE,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1),
                    "SXXS",
                    "EXXE",
                    "CXXC",
                    "XXXX",
                    'E', ModItems.ENDER_DRAGON_SCALE,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1),
                    "XXXX",
                    "SXXS",
                    "EXXE",
                    "CXXC",
                    'E', ModItems.ENDER_DRAGON_SCALE,
                    'S', Items.ENDER_EYE,
                    'C', Items.END_CRYSTAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1),
                    ModItems.ENDER_DRAGON_SCALE,
                    ModItems.GUARDIAN_SCALE,
                    ModItems.WITHER_BONE,
                    ModItems.LAVA_CRYSTAL);
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