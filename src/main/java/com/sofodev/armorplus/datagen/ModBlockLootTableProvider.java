package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.block.ore.CrystalOreBlock;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

import static com.sofodev.armorplus.registry.ModBlocks.*;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        for (RegistryObject<Block> registryBlock : ModBlocks.BLOCKS.getEntries()) {
            Block block = registryBlock.get();
            if (!(block instanceof CrystalOreBlock)) {
                dropSelf(block);
            }

        }
        this.add(ORE_LAVA_CRYSTAL.get(), block ->
                createMultipleOreDrop(ORE_LAVA_CRYSTAL.get(), ModItems.LAVA_CRYSTAL.get(), 1, 2));
        this.add(ORE_LAVA_CRYSTAL_STONE.get(), block ->
                createMultipleOreDrop(ORE_LAVA_CRYSTAL_STONE.get(), ModItems.LAVA_SHARD.get(), 1, 2));
        this.add(ORE_LAVA_CRYSTAL_OBSIDIAN.get(), block ->
                createMultipleOreDrop(ORE_LAVA_CRYSTAL_OBSIDIAN.get(), ModItems.LAVA_SHARD.get(), 3, 7));
        this.add(ORE_FROST_CRYSTAL.get(), block ->
                createMultipleOreDrop(ORE_FROST_CRYSTAL.get(), ModItems.FROST_CRYSTAL.get(), 1, 2));
        this.add(ORE_FROST_CRYSTAL_STONE.get(), block ->
                createMultipleOreDrop(ORE_FROST_CRYSTAL_STONE.get(), ModItems.FROST_SHARD.get(), 1, 2));
        this.add(ORE_FROST_CRYSTAL_OBSIDIAN.get(), block ->
                createMultipleOreDrop(ORE_FROST_CRYSTAL_OBSIDIAN.get(), ModItems.FROST_SHARD.get(), 3, 7));
    }

    protected LootTable.Builder createMultipleOreDrop(Block block, Item item, int minDrops, int maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
