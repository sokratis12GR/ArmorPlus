package net.thedragonteam.armorplus.api.crafting.utils;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ShapelessOreRecipeUtils {

    public static void createRecipe(ItemStack output, NonNullList<Object> input, Object... recipe) {
        Arrays.stream(recipe).forEachOrdered(in -> {
            if (in instanceof ItemStack) {
                input.add(((ItemStack) in).copy());
            } else if (in instanceof Item || in instanceof Block) {
                input.add(getItemStack(in));
            } else if (in instanceof String) {
                input.add(OreDictionary.getOres((String) in));
            } else {
                StringBuilder ret = new StringBuilder("Invalid shapeless ore recipe: ");
                Arrays.stream(recipe).forEachOrdered(tmp -> ret.append(tmp).append(", "));
                ret.append(output);
                throw new RuntimeException(ret.toString());
            }
        });
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @SuppressWarnings("unchecked")
    public static boolean matches(NonNullList<Object> input, InventoryCrafting inv) {
        NonNullList<Object> required = NonNullList.create();
        required.addAll(input);

        for (int x = 0; x < inv.getSizeInventory(); x++) {
            ItemStack slot = inv.getStackInSlot(x);

            if (!slot.isEmpty()) {
                boolean inRecipe = false;

                for (Object aRequired : required) {
                    boolean match = false;

                    if (aRequired instanceof ItemStack) {
                        match = OreDictionary.itemMatches((ItemStack) aRequired, slot, false);
                    } else if (aRequired instanceof List) {
                        Iterator<ItemStack> itr = ((List<ItemStack>) aRequired).iterator();
                        while (itr.hasNext() && !match) {
                            match = OreDictionary.itemMatches(itr.next(), slot, false);
                        }
                    }

                    if (match) {
                        inRecipe = true;
                        required.remove(aRequired);
                        break;
                    }
                }

                if (!inRecipe) {
                    return false;
                }
            }
        }

        return required.isEmpty();
    }
}
