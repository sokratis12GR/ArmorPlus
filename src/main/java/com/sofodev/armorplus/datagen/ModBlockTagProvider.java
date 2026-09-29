package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.block.APLavaBlock;
import com.sofodev.armorplus.registry.block.ore.CrystalOreBlock;
import com.sofodev.armorplus.registry.block.ore.Variant;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider,
                               ExistingFileHelper helper) {
        super(output, lookupProvider, ArmorPlus.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        for (RegistryObject<Block> registryBlock : ModBlocks.BLOCKS.getEntries()) {
            Block block = registryBlock.get();
            String path = registryBlock.getId().getPath();

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);

            if (block instanceof CrystalOreBlock crystalOre) {
                Variant v = crystalOre.getVariant();
                switch (v) {
                    case STONE -> tag(BlockTags.NEEDS_IRON_TOOL).add(block);
                    case OBSIDIAN, ORIGINAL -> tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block);
                }

                if (path.contains("lava")) {
                    tag(modTag("ores/lava_crystal")).add(block);
                } else if (path.contains("frost")) {
                    tag(modTag("ores/frost_crystal")).add(block);
                }
            } else if (block instanceof APLavaBlock) {
                tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block);
            } else {
                tag(BlockTags.NEEDS_STONE_TOOL).add(block);
            }

            if (path.endsWith("_wall")) {
                tag(BlockTags.WALLS).add(block);
                tag(modTag("walls")).add(block);
            }

        }
        tag(modTag("ores"))
                .addTag(modTag("ores/lava_crystal"))
                .addTag(modTag("ores/frost_crystal"));

    }

    private TagKey<Block> modTag(String name) {
        return BlockTags.create(Identifier.fromNamespaceAndPath(ArmorPlus.MODID, name));
    }
}