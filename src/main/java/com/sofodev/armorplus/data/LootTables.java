package com.sofodev.armorplus.data;

import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class LootTables extends BaseLootTableProvider {

    public List<RegistryObject<Block>> blockList = new ArrayList<>();

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        blockList.addAll(blocks);
        this.removeEntries(ORE_FROST_CRYSTAL, ORE_LAVA_CRYSTAL, SWORD_DISPLAY);

        blockList.stream().map(RegistryObject::get).forEach(this::registerDropSelfLootTable);
    }

    @SafeVarargs
    public final void removeEntries(RegistryObject<Block>... specialBlocks) {
        Arrays.stream(specialBlocks).forEach(block -> blockList.remove(block));
    }

    //////////////////////////
    // From BlockLootTables //
    //////////////////////////

    protected void registerLootTable(Block block, LootTable.Builder table) {
        this.lootTables.put(block, table);
    }

    protected static LootTable.Builder dropping(IItemProvider block) {
        return LootTable.builder().addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block))
        );
    }

    public void registerDropping(Block block, IItemProvider drop) {
        this.registerLootTable(block, dropping(drop));
    }

    public void registerDropSelfLootTable(Block block) {
        this.registerDropping(block, block);
    }

    public void registerStandardTable(String name, Block block) {
        this.registerLootTable(block, createStandardTable(name, block));
    }

    protected static LootTable.Builder droppingWithContents(Block block, ResourceLocation key) {
        return LootTable.builder().addLootPool(LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY)
                                .func_216056_a("Lock", "BlockEntityTag.Lock")
                                .func_216056_a("LootTable", "BlockEntityTag.LootTable")
                                .func_216056_a("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                        .acceptFunction(SetContents.func_215920_b().func_216075_a(DynamicLootEntry.func_216162_a(key)))
                ));
    }

}