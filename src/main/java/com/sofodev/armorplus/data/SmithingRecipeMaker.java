package com.sofodev.armorplus.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_LAVA_CRYSTAL;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static com.sofodev.armorplus.utils.DataUtils.quickModLookupItem;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.Items.NETHERITE_BOOTS;

public class SmithingRecipeMaker extends RecipeProvider {
    private DataGenerator generator;

    public SmithingRecipeMaker(DataGenerator generatorIn) {
        super(generatorIn.getPackOutput());
        generator = generatorIn;
    }

    public static SmithingRecipeMaker get(DataGenerator generator) {
        return new SmithingRecipeMaker(generator);
    }

    public void buildBaseToFullSmithing(Consumer<FinishedRecipe> consumer, Set<RegistryObject<Item>> bases, ItemLike soul) {
        bases.forEach(base -> this.buildBaseToFullSmithing(consumer, base, soul));
    }

    public void buildBaseToFullSmithing(Consumer<FinishedRecipe> consumer, RegistryObject<Item> base, ItemLike soul) {
        this.buildSmithing(consumer, base.get(), soul, RecipeCategory.COMBAT, quickModLookupItem(base.getId()));
    }

    @SafeVarargs
    public final void buildBaseToFullSmithing(Consumer<FinishedRecipe> consumer, ItemLike soul, RegistryObject<Item>... bases) {
        Arrays.stream(bases).forEach(base -> this.buildSmithing(consumer, base.get(), soul, RecipeCategory.COMBAT, quickModLookupItem(base.getId())));
    }

    public void buildVanillaToEnhancedSmithing(Consumer<FinishedRecipe> consumer, ItemLike vanilla, RegistryObject<Item> mat) {
        this.buildSmithing(consumer, vanilla, mat.get(), RecipeCategory.COMBAT, getAPItem(getPath(vanilla)));
    }

    public void buildSmithing(Consumer<FinishedRecipe> consumer, ItemLike base, ItemLike addition, RecipeCategory category, ItemLike result) {
        String path = getPath(base);
        UpgradeRecipeBuilder.smithing(Ingredient.of(base), //Base
                        Ingredient.of(addition), //Addition
                        category,
                        result.asItem() // Result
                ).unlocks("has_req", has(addition))
                .save(consumer, setRL("smithing/" + path));
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> con) {
        registerSmithingRecipes(con);
    }
    private void registerSmithingRecipes(Consumer<FinishedRecipe> con) {
        SmithingRecipeMaker smither = new SmithingRecipeMaker(generator);
        //ArmorBase + Soul = Complete Form
        smither.buildBaseToFullSmithing(con, SUPER_STAR_BASES, WITHER_BOSS_SOUL.get());
        smither.buildBaseToFullSmithing(con, GUARDIAN_BASES, ELDER_GUARDIAN_SOUL.get());
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_BASES, ENDER_DRAGON_SOUL.get());
        smither.buildBaseToFullSmithing(con, SLAYER_BASES, SLAYER_SOUL.get());

        smither.buildBaseToFullSmithing(con, WITHER_BOSS_SOUL.get(),
                SUPER_STAR_SWORD_BASE, SUPER_STAR_BATTLE_AXE_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, ELDER_GUARDIAN_SOUL.get(),
                GUARDIAN_SWORD_BASE, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_SOUL.get(),
                ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_BOW_BASE
        );
        smither.buildBaseToFullSmithing(con, SLAYER_SOUL.get(),
                SLAYER_SWORD_BASE, SLAYER_BATTLE_AXE_BASE, SLAYER_PICKAXE_BASE, SLAYER_BOW_BASE
        );

        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_HELMET, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_CHESTPLATE, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_LEGGINGS, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_BOOTS, ENHANCED_NETHERITE);
        smither.buildSmithing(con, INFUSED_LAVA_CRYSTAL.get(), INFUSED_FROST_CRYSTAL.get(), RecipeCategory.MISC, INFUSED_FROST_LAVA_CRYSTAL.get());
        smither.buildSmithing(con, INFUSED_FROST_CRYSTAL.get(), INFUSED_LAVA_CRYSTAL.get(), RecipeCategory.MISC, INFUSED_FROST_LAVA_CRYSTAL.get());
    }
}
