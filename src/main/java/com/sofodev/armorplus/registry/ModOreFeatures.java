package com.sofodev.armorplus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModOreFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LAVA_CRYSTAL = createConfiguredFeatureKey("ore_lava_crystal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LAVA_CRYSTAL_OBSIDIAN = createConfiguredFeatureKey("ore_lava_crystal_obsidian");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LAVA_CRYSTAL_STONE = createConfiguredFeatureKey("ore_lava_crystal_stone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FROST_CRYSTAL = createConfiguredFeatureKey("ore_frost_crystal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FROST_CRYSTAL_OBSIDIAN = createConfiguredFeatureKey("ore_frost_crystal_obsidian");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FROST_CRYSTAL_STONE = createConfiguredFeatureKey("ore_frost_crystal_stone");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        TagMatchTest deepslateOre = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        TagMatchTest stoneOre = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        registerOre(context, ORE_LAVA_CRYSTAL, ModBlocks.ORE_LAVA_CRYSTAL.get().defaultBlockState(), deepslateOre, 3, 0.1F);
        registerOre(context, ORE_LAVA_CRYSTAL_OBSIDIAN, ModBlocks.ORE_LAVA_CRYSTAL_OBSIDIAN.get().defaultBlockState(), deepslateOre, 4, 0.3F);
        registerOre(context, ORE_LAVA_CRYSTAL_STONE, ModBlocks.ORE_LAVA_CRYSTAL_STONE.get().defaultBlockState(), stoneOre, 5, 0.5F);

        registerOre(context, ORE_FROST_CRYSTAL, ModBlocks.ORE_FROST_CRYSTAL.get().defaultBlockState(), deepslateOre, 3, 0.1F);
        registerOre(context, ORE_FROST_CRYSTAL_OBSIDIAN, ModBlocks.ORE_FROST_CRYSTAL_OBSIDIAN.get().defaultBlockState(), deepslateOre, 4, 0.3F);
        registerOre(context, ORE_FROST_CRYSTAL_STONE, ModBlocks.ORE_FROST_CRYSTAL_STONE.get().defaultBlockState(), stoneOre, 5, 0.5F);
    }

    /**
     * Helper method to register an ore configured feature
     *
     * @param context       Bootstrap context
     * @param key           ResourceKey for the configured feature
     * @param state         BlockState to place
     * @param ruleTest      TagMatchTest or BlockMatchTest for what blocks it replaces
     * @param size          Vein size
     * @param discardChance Chance to discard exposed veins
     */
    private static void registerOre(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                    ResourceKey<ConfiguredFeature<?, ?>> key,
                                    net.minecraft.world.level.block.state.BlockState state,
                                    TagMatchTest ruleTest,
                                    int size,
                                    float discardChance) {

        OreConfiguration.TargetBlockState target = OreConfiguration.target(ruleTest, state);
        OreConfiguration config = new OreConfiguration(List.of(target), size, discardChance);

        context.register(key, new ConfiguredFeature<>(Feature.ORE, config));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MODID, name));
    }

}
