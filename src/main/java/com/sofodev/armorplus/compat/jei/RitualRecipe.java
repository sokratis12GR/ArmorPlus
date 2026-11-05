package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.registry.rune.RitualMenu;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Something the Ritual screen of a Rune Altar does, shown in JEI.
 *
 * @param tab       the tab it's done on
 * @param primary   what goes in the first slot, lined up with the outputs
 * @param secondary the rune it uses up
 * @param catalyst  the catalyst and its count it uses up from the pedestals, or EMPTY
 * @param outputs   the result of each of the primary stacks
 * @param cost      the experience level cost
 */
public record RitualRecipe(Identifier id, RitualMenu.Tab tab, List<ItemStack> primary, ItemStack secondary, ItemStack catalyst, List<ItemStack> outputs, int cost) {
}
