package net.thedragonteam.armorplus.api.crafting.utils;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.armorplus.api.crafting.IRecipe;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class CraftingUtils {

    /**
     * Adds a shapeless crafting recipe to the the game.
     */
    public static void addShapelessRecipe(NonNullList<ItemStack> list, Object... recipeComponents) {
        Arrays.stream(recipeComponents).forEachOrdered(object -> {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof Item) {
                list.add(getItemStack(object));
            } else {
                if (!(object instanceof Block)) {
                    throw new AssertionError("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(getItemStack(object));
            }
        });
    }

    public static Map<Character, ItemStack> getCharacterItemStackMap(int index, Object[] recipeComponents) {
        Map<Character, ItemStack> map;
        for (map = Maps.newHashMap(); index < recipeComponents.length; index += 2) {
            int secondary = index + 1;
            Character character = (Character) recipeComponents[index];
            ItemStack itemstack = ItemStack.EMPTY;

            if (recipeComponents[secondary] instanceof Item) {
                itemstack =getItemStack(recipeComponents[secondary]);
            } else if (recipeComponents[secondary] instanceof Block) {
                itemstack = getItemStack(recipeComponents[secondary], 1, OreDictionary.WILDCARD_VALUE);
            } else if (recipeComponents[secondary] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[secondary];
            }

            map.put(character, itemstack);
        }
        return map;
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    public static ItemStack findMatchingRecipe(List<IRecipe> recipes, InventoryCraftingImproved craftMatrix, World worldIn) {
        return recipes.stream().filter(irecipe -> irecipe.matches(craftMatrix, worldIn)).findFirst().map(irecipe -> irecipe.getCraftingResult(craftMatrix)).orElse(ItemStack.EMPTY);
    }

    public static NonNullList<ItemStack> getRemainingItems(List<IRecipe> recipes, InventoryCraftingImproved craftMatrix, World worldIn) {
        for (IRecipe irecipe : recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

        IntStream.range(0, nonnulllist.size()).forEach(i -> nonnulllist.set(i, craftMatrix.getStackInSlot(i)));

        return nonnulllist;
    }

    public static void onTake(EntityPlayer player, InventoryCraftingImproved craftMatrix, NonNullList<ItemStack> nonnulllist) {
        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = nonnulllist.get(i);

            if (!itemstack.isEmpty()) {
                craftMatrix.decrStackSize(i, 1);
                itemstack = craftMatrix.getStackInSlot(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    craftMatrix.setInventorySlotContents(i, itemstack1);
                } else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    craftMatrix.setInventorySlotContents(i, itemstack1);
                } else if (!player.inventory.addItemStackToInventory(itemstack1)) {
                    player.dropItem(itemstack1, false);
                }
            }
        }
    }
}
