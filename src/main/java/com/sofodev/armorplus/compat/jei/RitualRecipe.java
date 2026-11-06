package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.registry.rune.RitualMenu;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Something the Ritual screen of a Rune Altar does, shown in JEI.
 *
 * @param tab       the tab it's done on
 * @param primary   what goes in the first slot, lined up with the outputs
 * @param secondary the rune it uses up
 * @param catalyst  the catalyst and its count it uses up from the pedestals, or EMPTY
 * @param typeCatalyst the catalyst of the rune's type it uses up on top, or EMPTY
 * @param outputs   the result of each of the primary stacks
 * @param cost      the experience level cost
 */
public record RitualRecipe(Identifier id, RitualMenu.Tab tab, List<ItemStack> primary, ItemStack secondary, ItemStack catalyst, ItemStack typeCatalyst, List<ItemStack> outputs, int cost) {

    /** @return one stack for each pedestal the recipe needs, in order */
    List<ItemStack> pedestalCatalysts() {
        List<ItemStack> catalysts = new ArrayList<>();
        for (int i = 0; i < this.catalyst.getCount(); i++) catalysts.add(this.catalyst.copyWithCount(1));
        for (int i = 0; i < this.typeCatalyst.getCount(); i++) catalysts.add(this.typeCatalyst.copyWithCount(1));
        return catalysts;
    }
}
