package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackGenerator extends DatapackBuiltinEntriesProvider {

    public ModDataPackGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries,
                                RegistrySetBuilder builder) {
        super(output, registries, builder, Set.of(ArmorPlus.MODID));
    }
}