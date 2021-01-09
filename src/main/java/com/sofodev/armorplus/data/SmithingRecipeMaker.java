package com.sofodev.armorplus.data;

import com.sofodev.armorplus.registry.items.APItem;
import com.sofodev.armorplus.registry.items.APItemBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static com.sofodev.armorplus.utils.DataUtils.quickModLookupItem;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.item.crafting.Ingredient.fromItems;

public class SmithingRecipeMaker extends RecipeProvider {

    public SmithingRecipeMaker(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public static SmithingRecipeMaker get(DataGenerator generator) {
        return new SmithingRecipeMaker(generator);
    }

    public void buildBaseToFullSmithing(Consumer<IFinishedRecipe> consumer, Set<RegistryObject<Item>> bases, IItemProvider soul) {
        bases.forEach(base -> this.buildBaseToFullSmithing(consumer, base, soul));
    }

    public void buildBaseToFullSmithing(Consumer<IFinishedRecipe> consumer, RegistryObject<Item> base, IItemProvider soul) {
        this.buildSmithing(consumer, base.get(), soul, quickModLookupItem(base.getId()));
    }

    public void buildBaseToFullSmithing(Consumer<IFinishedRecipe> consumer, IItemProvider soul, RegistryObject<Item>... bases) {
        Arrays.stream(bases).forEach(base -> this.buildSmithing(consumer, base.get(), soul, quickModLookupItem(base.getId())));
    }

    public void buildVanillaToEnhancedSmithing(Consumer<IFinishedRecipe> consumer, IItemProvider vanilla, RegistryObject<Item> mat) {
        this.buildSmithing(consumer, vanilla, mat.get(), getAPItem(getPath(vanilla)));
    }

    public void buildSmithing(Consumer<IFinishedRecipe> consumer, IItemProvider base, IItemProvider addition, IItemProvider result) {
        String path = getPath(base);
        SmithingRecipeBuilder.smithingRecipe(fromItems(base), //Base
                fromItems(addition), //Addition
                result.asItem() // Result
        ).addCriterion("has_req", hasItem(addition))
                .build(consumer, setRL("smithing/" + path));
    }
}
