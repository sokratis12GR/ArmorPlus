package net.thedragonteam.armorplus.api.crafting.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class ShapedOreRecipeUtils {

    public static void checkRecipeShape(int width, int height, StringBuilder shape, ItemStack output, Object... recipe) {
        if (width * height != shape.length()) {
            StringBuilder ret = new StringBuilder("Invalid shaped ore recipe: ");
            Arrays.stream(recipe).forEachOrdered(tmp -> ret.append(tmp).append(", "));
            ret.append(output);
            throw new RuntimeException(ret.toString());
        }
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public static boolean matches(int xWidth, int yHeight, int width, int height, Object[] input, InventoryCraftingImproved inv, boolean mirrored) {
        return IntStream.rangeClosed(0, xWidth - width).anyMatch(i -> IntStream.rangeClosed(0, yHeight - height).anyMatch(y -> checkMatch(xWidth, yHeight, input, inv, i, y, false) || mirrored && checkMatch(xWidth, yHeight, input, inv, i, y, true)));
    }

    @SuppressWarnings("unchecked")
    private static boolean checkMatch(int width, int height, Object[] input, InventoryCraftingImproved inv, int startX, int startY, boolean mirror) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
                    target = mirror ? input[width - subX - 1 + subY * width] : input[subX + subY * width];
                }

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if (target instanceof ItemStack) {
                    if (!OreDictionary.itemMatches((ItemStack) target, slot, false)) return false;
                } else if (target instanceof List) {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
                    while (itr.hasNext() && !matched) {
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
                    }

                    if (!matched) return false;
                } else if (target == null && !slot.isEmpty()) return false;
            }
        }

        return true;
    }
}
