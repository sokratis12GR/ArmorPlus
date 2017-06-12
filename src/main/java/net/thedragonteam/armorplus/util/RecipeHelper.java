package net.thedragonteam.armorplus.util;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class RecipeHelper {

    private static int j = 0;

    /*
     * This adds the recipe to the list of crafting recipes.  Since who cares about names, it adds it as recipesX, where X is the current recipe you are adding.
     */
    public static void addRecipe(int j, IRecipe rec) {
        CraftingManager.func_193372_a(new ResourceLocation(ArmorPlus.MODID, "recipes" + j), rec);
    }

    /*
     * This adds a shaped recipe to the list of crafting recipes, using the old format from 1.11 and before.
     */
    public static void addOldShaped(ItemStack output, Object... input) {
        addRecipe(j++, new FixedShapedOreRecipe(new ResourceLocation(ArmorPlus.MODID, "recipes" + j), output, input));
    }

    /*
     * This adds a shapeless recipe to the list of crafting recipes, using the old format from 1.11 and before.
     */
    public static void addOldShapeless(ItemStack output, Object... input) {
        addRecipe(j++, new ShapelessOreRecipe(new ResourceLocation(ArmorPlus.MODID, "recipes" + j), output, input));
    }

    /*
     * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.
     */
    public static void addShapeless(ItemStack output, Object... inputs) {
        addRecipe(j++, new ShapelessRecipes(String.valueOf(j), output, createInput(inputs)));
    }

    public static void addShapeless(Item output, Object... inputs) {
        addShapeless(new ItemStack(output), inputs);
    }

    public static void addShapeless(Block output, Object... inputs) {
        addShapeless(new ItemStack(output), inputs);
    }

    /*
     * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
     * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid.
     */
    public static void addShaped(ItemStack output, int width, int height, Object... input) {
        addRecipe(j++, genShaped(output, width, height, input));
    }

    public static void addShaped(Item output, int width, int height, Object... input) {
        addShaped(new ItemStack(output), width, height, input);
    }

    public static void addShaped(Block output, int width, int height, Object... input) {
        addShaped(new ItemStack(output), width, height, input);
    }


    public static ShapedRecipes genShaped(ItemStack output, int l, int w, Object[] input) {
        if (input[0] instanceof Object[]) input = (Object[]) input[0];
        if (l * w != input.length)
            throw new UnsupportedOperationException(
                    "Attempted to add invalid shaped recipe.  Complain to the author of  " + ArmorPlus.MODNAME);
        NonNullList<Ingredient> inputL = NonNullList.create();
        for (int i = 0; i < input.length; i++) {
            Object k = input[i];
            if (k instanceof String) {
                inputL.add(i, new OreIngredient((String) k));
            } else if (k instanceof ItemStack) {
                inputL.add(i, Ingredient.func_193369_a((ItemStack) k));
            } else if (k instanceof Item) {
                inputL.add(i, Ingredient.func_193367_a((Item) k));
            } else if (k instanceof Block) {
                inputL.add(i, Ingredient.func_193369_a(new ItemStack((Block) k)));
            } else {
                inputL.add(i, Ingredient.field_193370_a);
            }
        }

        return new ShapedRecipes(String.valueOf(j), l, w, inputL, output);
    }

    public static NonNullList<Ingredient> createInput(Object[] input) {
        if (input[0] instanceof Object[]) input = (Object[]) input[0];
        NonNullList<Ingredient> inputL = NonNullList.create();
        for (int i = 0; i < input.length; i++) {
            Object k = input[i];
            if (k instanceof String) {
                inputL.add(i, new OreIngredient((String) k));
            } else if (k instanceof ItemStack) {
                inputL.add(i, Ingredient.func_193369_a((ItemStack) k));
            } else if (k instanceof Item) {
                inputL.add(i, Ingredient.func_193367_a((Item) k));
            } else if (k instanceof Block) {
                inputL.add(i, Ingredient.func_193369_a(new ItemStack((Block) k)));
            } else {
                throw new UnsupportedOperationException(
                        "Attempted to add invalid shapeless recipe.  Complain to the author of  " + ArmorPlus.MODNAME);
            }
        }
        return inputL;
    }

    //This is ShapedOreRecipe modified to actually work until forge re-fixes it in an update.
    public static class FixedShapedOreRecipe implements IRecipe {
        //Added in for future ease of change, but hard coded for now.
        public static final int MAX_CRAFT_GRID_WIDTH = 3;
        public static final int MAX_CRAFT_GRID_HEIGHT = 3;

        @Nonnull
        protected ItemStack output = ItemStack.EMPTY;
        protected NonNullList<Ingredient> input = null;
        protected int width = 0;
        protected int height = 0;
        protected boolean mirrored = true;
        protected ResourceLocation group;

        public FixedShapedOreRecipe(ResourceLocation group, Block result, Object... recipe) {
            this(group, new ItemStack(result), recipe);
        }

        public FixedShapedOreRecipe(ResourceLocation group, Item result, Object... recipe) {
            this(group, new ItemStack(result), recipe);
        }

        public FixedShapedOreRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe) {
            this.group = group;
            output = result.copy();

            StringBuilder shape = new StringBuilder();
            int idx = 0;

            if (recipe[idx] instanceof Boolean) {
                mirrored = (Boolean) recipe[idx];
                if (recipe[idx + 1] instanceof Object[]) {
                    recipe = (Object[]) recipe[idx + 1];
                } else {
                    idx = 1;
                }
            }

            if (recipe[idx] instanceof String[]) {
                String[] parts = ((String[]) recipe[idx++]);

                for (String s : parts) {
                    width = s.length();
                    shape.append(s);
                }

                height = parts.length;
            } else {
                while (recipe[idx] instanceof String) {
                    String s = (String) recipe[idx++];
                    shape.append(s);
                    width = s.length();
                    height++;
                }
            }

            if (width * height != shape.length()) {
                StringBuilder ret = new StringBuilder("Invalid shaped ore recipe: ");
                for (Object tmp : recipe) {
                    ret.append(tmp).append(", ");
                }
                ret.append(output);
                throw new RuntimeException(ret.toString());
            }

            HashMap<Character, Ingredient> itemMap = Maps.newHashMap();

            for (; idx < recipe.length; idx += 2) {
                Character chr = (Character) recipe[idx];
                Object in = recipe[idx + 1];

                if (in instanceof ItemStack) {
                    itemMap.put(chr, Ingredient.func_193369_a(((ItemStack) in).copy()));
                } else if (in instanceof Item) {
                    itemMap.put(chr, Ingredient.func_193367_a((Item) in));
                } else if (in instanceof Block) {
                    itemMap.put(chr,
                            Ingredient.func_193369_a(new ItemStack((Block) in, 1, OreDictionary.WILDCARD_VALUE)));
                } else if (in instanceof String) {
                    itemMap.put(chr, new OreIngredient((String) in));
                } else if (in instanceof Ingredient) {
                    itemMap.put(chr, (Ingredient) in);
                } else {
                    String ret = "Invalid shaped ore recipe: ";
                    for (Object tmp : recipe) {
                        ret += tmp + ", ";
                    }
                    ret += output;
                    throw new RuntimeException(ret);
                }
            }

            this.input = NonNullList.withSize(width * height, Ingredient.field_193370_a);
            int x = 0;
            for (char chr : shape.toString().toCharArray()) {
                if (itemMap.get(chr) != null)
                    input.set(x, itemMap.get(chr));
                x++;
            }
        }

        /**
         * Returns an Item that is the result of this recipe
         */
        @Override
        @Nonnull
        public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
            return output.copy();
        }

        @Override
        @Nonnull
        public ItemStack getRecipeOutput() {
            return output;
        }

        /**
         * Used to check if a recipe matches current crafting inventory
         */
        @Override
        public boolean matches(InventoryCrafting inv, World world) {
            for (int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++) {
                for (int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y) {
                    if (checkMatch(inv, x, y, false)) {
                        return true;
                    }

                    if (mirrored && checkMatch(inv, x, y, true)) {
                        return true;
                    }
                }
            }

            return false;
        }

        protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
            for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++) {
                for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++) {
                    int subX = x - startX;
                    int subY = y - startY;
                    Ingredient target = null;

                    if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
                        if (mirror) {
                            target = input.get(width - subX - 1 + subY * width);
                        } else {
                            target = input.get(subX + subY * width);
                        }
                    }

                    if (!target.apply(inv.getStackInRowAndColumn(x, y))) {
                        return false;
                    }
                }
            }

            return true;
        }

        public FixedShapedOreRecipe setMirrored(boolean mirror) {
            mirrored = mirror;
            return this;
        }

        public NonNullList<Ingredient> func_192400_c() {
            return this.input;
        }

        @Override
        public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) //getRecipeLeftovers
        {
            return ForgeHooks.defaultRecipeGetRemainingItems(inv);
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String func_193358_e() {
            return this.group.toString();
        }

        public boolean func_194133_a(int p_194133_1_, int p_194133_2_) {
            return p_194133_1_ >= this.width && p_194133_2_ >= this.height;
        }
    }
}