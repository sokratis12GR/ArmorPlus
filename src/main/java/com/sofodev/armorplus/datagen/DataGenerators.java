package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.registry.ModEnchantments;
import com.sofodev.armorplus.registry.ModOreFeatures;
import com.sofodev.armorplus.registry.ModPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

public final class DataGenerators {

    private DataGenerators() {
    }

    private static final RegistrySetBuilder DYNAMIC_REGISTRY_BUILDER = new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, ModOreFeatures::bootstrap).add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap).add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

    public static void gatherClientData(GatherDataEvent.Client event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();

        generator.addProvider(true, new ModItemModelProvider(output));
        generator.addProvider(true, new ModEquipmentProvider(output));
        generator.addProvider(true, new ModBlockModelProvider(output));
    }

    public static void gatherServerData(GatherDataEvent.Server event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        ModBlockTagProvider blockTags = new ModBlockTagProvider(output, lookupProvider);

        generator.addProvider(true, blockTags);

        generator.addProvider(true, new ModItemTagProvider(output, lookupProvider));

        generator.addProvider(true, new Recipes.Runner(output, lookupProvider));

        generator.addProvider(true, new ModVillagerTradeProvider(output));

        generator.addProvider(true, new ModPoiProvider(output));

        generator.addProvider(true, new ModDataPackGenerator(output, lookupProvider, DYNAMIC_REGISTRY_BUILDER));

        generator.addProvider(true, new ModBiomeModifierProvider(output));

        generator.addProvider(true, new AdvancementProvider(output, lookupProvider, List.of(new ModAdvancementProvider())));

        generator.addProvider(true, new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
    }
}