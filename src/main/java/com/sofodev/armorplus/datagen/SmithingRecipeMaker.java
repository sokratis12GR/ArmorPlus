package com.sofodev.armorplus.datagen;

import net.minecraft.core.HolderLookup;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Set;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModItems.ELDER_GUARDIAN_SOUL;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_BASES;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SOUL;
import static com.sofodev.armorplus.registry.ModItems.ENDER_DRAGON_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_NETHERITE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_BASES;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.GUARDIAN_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_FROST_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_BASES;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_SOUL;
import static com.sofodev.armorplus.registry.ModItems.SLAYER_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_BASES;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.SUPER_STAR_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_BASES;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_BATTLE_AXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_BOW_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_HEART;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_PICKAXE_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_SHOVEL_BASE;
import static com.sofodev.armorplus.registry.ModItems.WARDEN_SWORD_BASE;
import static com.sofodev.armorplus.registry.ModItems.WITHER_BOSS_SOUL;
import static com.sofodev.armorplus.utils.DataUtils.getPath;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static net.minecraft.world.item.Items.NETHERITE_BOOTS;
import static net.minecraft.world.item.Items.NETHERITE_CHESTPLATE;
import static net.minecraft.world.item.Items.NETHERITE_HELMET;
import static net.minecraft.world.item.Items.NETHERITE_LEGGINGS;

public class SmithingRecipeMaker extends RecipeProvider {
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public SmithingRecipeMaker(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public void buildBaseToFullSmithing(RecipeOutput consumer, Set<DeferredHolder<Item, Item>> bases, ItemLike soul) {
        for (DeferredHolder<Item, Item> base : bases) {
            this.buildBaseToFullSmithing(consumer, base, soul);
        }
    }

    public void buildBaseToFullSmithing(RecipeOutput consumer, DeferredHolder<Item, Item> base, ItemLike soul) {
        String path = base.getId().getPath();
        if (path.contains("_body")) return;

        String fullItem = path.replace("_base", "");
        LOGGER.info(fullItem);
        this.buildSmithing(consumer, base.get(), soul, RecipeCategory.COMBAT, getAPItem(fullItem));
    }

    @SafeVarargs
    public final void buildBaseToFullSmithing(RecipeOutput consumer, ItemLike soul, DeferredHolder<Item, Item>... bases) {
        Arrays.stream(bases).forEach(base -> {
            String path = base.getId().getPath();
            if (path.contains("_body")) return;
            String fullItem = path.replace("_base", "");
            LOGGER.info(fullItem);
            this.buildSmithing(consumer, base.get(), soul, RecipeCategory.COMBAT, getAPItem(fullItem));
        });
    }

    public void buildVanillaToEnhancedSmithing(RecipeOutput consumer, ItemLike vanilla, DeferredHolder<Item, Item> mat) {
        this.buildSmithing(consumer, vanilla, mat.get(), RecipeCategory.COMBAT, getAPItem(getPath(vanilla)));
    }

    public void buildSmithing(RecipeOutput consumer, ItemLike base, ItemLike addition, RecipeCategory category, ItemLike result) {
        String path = getPath(base);
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(INFUSED_LAVA_CRYSTAL.get()),
                        Ingredient.of(base),
                        Ingredient.of(addition),
                        category,
                        result.asItem()
                ).unlocks("has_req", has(addition))
                .save(consumer, Utils.setLocation("smithing/" + path));
    }

    @Override
    protected void buildRecipes() {
        registerSmithingRecipes(output);
    }

    public void registerSmithingRecipes(RecipeOutput con) {
        SmithingRecipeMaker smither = this;

        smither.buildBaseToFullSmithing(con, SUPER_STAR_BASES, WITHER_BOSS_SOUL.get());
        smither.buildBaseToFullSmithing(con, GUARDIAN_BASES, ELDER_GUARDIAN_SOUL.get());
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_BASES, ENDER_DRAGON_SOUL.get());
        smither.buildBaseToFullSmithing(con, WARDEN_BASES, WARDEN_HEART.get());
        smither.buildBaseToFullSmithing(con, SLAYER_BASES, SLAYER_SOUL.get());

        smither.buildBaseToFullSmithing(con, WITHER_BOSS_SOUL.get(), SUPER_STAR_SWORD_BASE, SUPER_STAR_BATTLE_AXE_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_SHOVEL_BASE, SUPER_STAR_BOW_BASE);
        smither.buildBaseToFullSmithing(con, ELDER_GUARDIAN_SOUL.get(), GUARDIAN_SWORD_BASE, GUARDIAN_BATTLE_AXE_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_SHOVEL_BASE, GUARDIAN_BOW_BASE);
        smither.buildBaseToFullSmithing(con, ENDER_DRAGON_SOUL.get(), ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_BATTLE_AXE_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_SHOVEL_BASE, ENDER_DRAGON_BOW_BASE);
        smither.buildBaseToFullSmithing(con, WARDEN_HEART.get(), WARDEN_SWORD_BASE, WARDEN_BATTLE_AXE_BASE, WARDEN_PICKAXE_BASE, WARDEN_SHOVEL_BASE, WARDEN_BOW_BASE);
        smither.buildBaseToFullSmithing(con, SLAYER_SOUL.get(), SLAYER_SWORD_BASE, SLAYER_BATTLE_AXE_BASE, SLAYER_PICKAXE_BASE, SLAYER_SHOVEL_BASE, SLAYER_BOW_BASE);

        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_HELMET, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_CHESTPLATE, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_LEGGINGS, ENHANCED_NETHERITE);
        smither.buildVanillaToEnhancedSmithing(con, NETHERITE_BOOTS, ENHANCED_NETHERITE);
        smither.buildSmithing(con, INFUSED_LAVA_CRYSTAL.get(), INFUSED_FROST_CRYSTAL.get(), RecipeCategory.MISC, INFUSED_FROST_LAVA_CRYSTAL.get());
        smither.buildSmithing(con, INFUSED_FROST_CRYSTAL.get(), INFUSED_LAVA_CRYSTAL.get(), RecipeCategory.MISC, INFUSED_FROST_LAVA_CRYSTAL.get());
    }
}
