package com.sofodev.armorplus.data;

import com.sofodev.armorplus.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.ModBlocks.*;

public class LootTables extends BaseLootTableProvider {

    public List<RegistryObject<Block>> blockList = new ArrayList<>();

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    protected static LootTable.Builder dropping(ItemLike block) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block))
        );
    }

//    protected static LootTable.Builder droppingWithContents(Block block, ResourceLocation key) {
//        return LootTable.lootTable().withPool(LootPool.lootPool()
//                .setRolls(ConstantValue.exactly(1))
//                .add(LootItem.lootTableItem(block)
//                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
//                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
//                                .copy("Lock", "BlockEntityTag.Lock")
//                                .copy("LootTable", "BlockEntityTag.LootTable")
//                                .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
//                        .apply(SetContainerContents.setContents(TROPHY_TYPE.get()).withEntry(DynamicLoot.dynamicEntry(key)))
//                ));
//    }

    //////////////////////////
    // From BlockLootTables //
    //////////////////////////

    @Override
    protected void addTables() {
        blockList = BLOCKS.getEntries().stream().toList();
        this.removeEntries(ORE_FROST_CRYSTAL, ORE_FROST_CRYSTAL_STONE, ORE_FROST_CRYSTAL_OBSIDIAN,
                ORE_LAVA_CRYSTAL, ORE_LAVA_CRYSTAL_STONE, ORE_LAVA_CRYSTAL_OBSIDIAN);

        blockList.stream().map(RegistryObject::get).forEach(this::registerDropSelfLootTable);
    }

    @SafeVarargs
    public final void removeEntries(RegistryObject<Block>... specialBlocks) {
        Arrays.stream(specialBlocks).forEach(block -> blockList.remove(block));
    }

    protected void registerLootTable(Block block, LootTable.Builder table) {
        this.lootTables.put(block, table);
    }

    public void registerDropping(Block block, ItemLike drop) {
        this.registerLootTable(block, dropping(drop));
    }

    public void registerDropSelfLootTable(Block block) {
        this.registerDropping(block, block);
    }

    public void registerStandardTable(String name, Block block, BlockEntityType<?> type) {
        this.registerLootTable(block, createStandardTable(name, block, type));
    }

}