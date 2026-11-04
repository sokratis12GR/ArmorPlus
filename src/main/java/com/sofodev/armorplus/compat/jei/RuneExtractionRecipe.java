package com.sofodev.armorplus.compat.jei;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * A Rune Altar extraction shown in JEI: any of the equipment and a Blank Rune (plus the catalyst, if the buff needs one)
 * give the rune for the experience level cost.
 *
 * @param gear     every equipment item the rune can be extracted from
 * @param catalyst the catalyst and its count, or EMPTY
 */
public record RuneExtractionRecipe(Identifier id, List<ItemStack> gear, ItemStack catalyst, ItemStack rune, int cost) {
}
