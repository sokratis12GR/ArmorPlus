package com.sofodev.armorplus.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static com.sofodev.armorplus.utils.DataUtils.quickModLookupItem;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class SmithingRecipeMaker extends RecipeProvider {

    public SmithingRecipeMaker(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public static SmithingRecipeMaker get(DataGenerator generator) {
        return new SmithingRecipeMaker(generator);
    }

    public void buildBaseToFullSmithing(Consumer<FinishedRecipe> consumer, Set<RegistryObject<Item>> bases, ItemLike soul) {
        bases.forEach(base -> this.buildBaseToFullSmithing(consumer, base, soul));
    }

    public void buildBaseToFullSmithing(Consumer<FinishedRecipe> consumer, RegistryObject<Item> base, ItemLike soul) {
        this.buildSmithing(consumer, base.get(), soul, quickModLookupItem(base.getId()));
    }

    @SafeVarargs
    public final void buildBaseToFullSmithing(Consumer<FinishedRecipe> consumer, ItemLike soul, RegistryObject<Item>... bases) {
        Arrays.stream(bases).forEach(base -> this.buildSmithing(consumer, base.get(), soul, quickModLookupItem(base.getId())));
    }

    public void buildVanillaToEnhancedSmithing(Consumer<FinishedRecipe> consumer, ItemLike vanilla, RegistryObject<Item> mat) {
        this.buildSmithing(consumer, vanilla, mat.get(), getAPItem(getPath(vanilla)));
    }

    public void buildSmithing(Consumer<FinishedRecipe> consumer, ItemLike base, ItemLike addition, ItemLike result) {
        String path = getPath(base);
        UpgradeRecipeBuilder.smithing(Ingredient.of(base), //Base
                        Ingredient.of(addition), //Addition
                        result.asItem() // Result
                ).unlocks("has_req", has(addition))
                .save(consumer, setRL("smithing/" + path));
    }
}
