package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModEnchantments;
import com.sofodev.armorplus.registry.ModOreFeatures;
import com.sofodev.armorplus.registry.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ArmorPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModOreFeatures::bootstrap)
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Block tags
        ModBlockTagProvider blockTags = new ModBlockTagProvider(packOutput, provider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);

        // Item tags
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, provider, blockTags.contentsGetter(), existingFileHelper));

        // Recipes
        generator.addProvider(event.includeServer(), new Recipes(generator, provider));

        // Block Loot Tables
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), provider));

        // Registry data (enchantments, configured_feature, placed_feature)
        generator.addProvider(event.includeServer(), new ModDataPackGenerator(packOutput, provider, BUILDER));

        // POI tags
        ModPOITagProvider poiTags = new ModPOITagProvider(packOutput, provider, existingFileHelper);
        generator.addProvider(event.includeServer(), poiTags);

        generator.addProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput, provider, existingFileHelper, List.of(new ModAdvancementProvider())));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

    }
}