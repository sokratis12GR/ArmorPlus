package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.registry.ModEnchantments;
import com.sofodev.armorplus.registry.ModOreFeatures;
import com.sofodev.armorplus.registry.ModPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;


public final class DataGenerators {
    private DataGenerators() {
    }

    private static final RegistrySetBuilder DYNAMIC_REGISTRY_BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModOreFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        ModBlockTagProvider blockTags = new ModBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(),
                new ModItemTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new Recipes.Runner(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ModVillagerTradeProvider(output));
        generator.addProvider(event.includeServer(), new ModPoiProvider(output));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output));
        generator.addProvider(event.includeClient(), new ModEquipmentProvider(output));
        generator.addProvider(event.includeClient(), new ModBlockModelProvider(output));
        generator.addProvider(event.includeServer(),
                new ModDataPackGenerator(output, lookupProvider, DYNAMIC_REGISTRY_BUILDER));
        generator.addProvider(event.includeServer(), new ModBiomeModifierProvider(output));
        generator.addProvider(event.includeServer(),
                new AdvancementProvider(output, lookupProvider, List.of(new ModAdvancementProvider())));
        generator.addProvider(event.includeServer(),
                new LootTableProvider(
                        output,
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTableProvider::new,
                                LootContextParamSets.BLOCK
                        )),
                        lookupProvider
                ));
    }
}
