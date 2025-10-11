package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModPoI;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModPOITagProvider extends TagsProvider<PoiType> {
    public ModPOITagProvider(PackOutput output,
                             CompletableFuture<HolderLookup.Provider> lookupProvider,
                             ExistingFileHelper existingFileHelper) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, lookupProvider, ArmorPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        TagKey<PoiType> MC_ACQUIRABLE_JOB_SITE =
                TagKey.create(Registries.POINT_OF_INTEREST_TYPE, ResourceLocation.withDefaultNamespace("acquirable_job_site"));

        tag(MC_ACQUIRABLE_JOB_SITE).add(ModPoI.EXCHANGER_POI.getKey());
    }
}