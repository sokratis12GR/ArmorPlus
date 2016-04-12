package sokratis12GR.ArmorPlus.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import sokratis12GR.ArmorPlus.armors.ObsidianArmor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ArmorWorkshopCraftingManager {
    /**
     * The static instance of this class
     */
    private static final ArmorWorkshopCraftingManager instance = new ArmorWorkshopCraftingManager();
    private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

    /**
     * Returns the static instance of this class
     */
    public static ArmorWorkshopCraftingManager getInstance() {
        /** The static instance of this class */
        return instance;
    }

    private ArmorWorkshopCraftingManager() {
        this.addRecipe(new ItemStack(Items.paper, 3), new Object[]{"###", '#', Items.reeds});
        this.addShapelessRecipe(new ItemStack(Items.book, 1), new Object[]{Items.paper, Items.paper, Items.paper, Items.leather});
        this.addShapelessRecipe(new ItemStack(Items.writable_book, 1), new Object[]{Items.book, new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeDamage()), Items.feather});
        this.addRecipe(new ItemStack(Blocks.oak_fence, 3), new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.OAK.getMetadata())});
        this.addRecipe(new ItemStack(Blocks.birch_fence, 3), new Object[]{"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.BIRCH.getMetadata())});
        this.addRecipe(new ItemStack(ObsidianArmor.helmet, 1), new Object[]
                {"XXX", "345", "6X8", Character.valueOf('3'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('4'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('5'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('6'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('8'),
                        new ItemStack(Blocks.obsidian, 1),});
        this.addRecipe(new ItemStack(ObsidianArmor.helmet, 1), new Object[]
                {"012", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('1'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('2'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('3'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('5'),
                        new ItemStack(Blocks.obsidian, 1),});
        this.addRecipe(new ItemStack(ObsidianArmor.chestplate, 1), new Object[]
                {"0X2", "345", "678", Character.valueOf('0'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('2'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('3'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('4'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('5'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('6'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('7'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('8'),
                        new ItemStack(Blocks.obsidian, 1),});
        this.addRecipe(new ItemStack(ObsidianArmor.legs, 1), new Object[]
                {"012", "3X5", "6X8", Character.valueOf('0'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('1'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('2'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('3'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('5'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('6'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('8'), new ItemStack(Blocks.obsidian, 1),});
        this.addRecipe(new ItemStack(ObsidianArmor.boots, 1), new Object[]
                {"XXX", "3X5", "6X8", Character.valueOf('3'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('5'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('6'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('8'), new ItemStack(Blocks.obsidian, 1),});
        this.addRecipe(new ItemStack(ObsidianArmor.boots, 1), new Object[]
                {"0X2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Blocks.obsidian, 1), Character.valueOf('2'),
                        new ItemStack(Blocks.obsidian, 1), Character.valueOf('3'), new ItemStack(Blocks.obsidian, 1),
                        Character.valueOf('5'), new ItemStack(Blocks.obsidian, 1),});
        Collections.sort(this.recipes, new Comparator<IRecipe>() {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
                return p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes ? 1 : (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes ? -1 : (p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() ? -1 : (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0)));
            }
        });
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
    public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(p_82787_1_, worldIn)) {
                return irecipe.getCraftingResult(p_82787_1_);
            }
        }

        return null;
    }

    public ItemStack[] func_180303_b(InventoryCrafting p_180303_1_, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(p_180303_1_, worldIn)) {
                return irecipe.getRemainingItems(p_180303_1_);
            }
        }

        ItemStack[] aitemstack = new ItemStack[p_180303_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            aitemstack[i] = p_180303_1_.getStackInSlot(i);
        }

        return aitemstack;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}
