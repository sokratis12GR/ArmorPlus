package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.blocks.APBlock;
import com.sofodev.armorplus.registry.blocks.APBlockItem;
import com.sofodev.armorplus.registry.blocks.APFrostBlock;
import com.sofodev.armorplus.registry.blocks.APLavaBlock;
import com.sofodev.armorplus.registry.blocks.castle.*;
import com.sofodev.armorplus.registry.blocks.ore.CrystalOreBlock;
import com.sofodev.armorplus.registry.blocks.special.SoulBox;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.blocks.castle.BrickColor.values;
import static com.sofodev.armorplus.registry.blocks.ore.Variant.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    public static final RegistryObject<Block>[] STONE_BRICKS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_TOWERS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_CORNERS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_WALLS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_STAIRS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_SLABS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] CASTLE_BLOCKS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] CASTLE_BLOCK_TOWERS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] CASTLE_BLOCK_CORNERS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] CASTLE_BLOCK_WALLS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] CASTLE_BLOCK_STAIRS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] CASTLE_BLOCK_SLABS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    //    public static final RegistryObject<Block> TROPHY = registerTrophyBlock("trophy", TrophyBlock::new);
    public static final RegistryObject<Block> COMPRESSED_OBSIDIAN = registerBlockWithItem("compressed_obsidian", () -> new Block(copy(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL = registerBlockWithItem("ore_lava_crystal", () -> new CrystalOreBlock(ORIGINAL));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL_STONE = registerBlockWithItem("ore_lava_crystal_stone", () -> new CrystalOreBlock(STONE));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL_OBSIDIAN = registerBlockWithItem("ore_lava_crystal_obsidian", () -> new CrystalOreBlock(OBSIDIAN));
    public static final RegistryObject<Block> LAVA_CRYSTAL = registerBlockWithItem("block_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> INFUSED_LAVA_CRYSTAL = registerBlockWithItem("block_infused_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> COMPRESSED_LAVA_CRYSTAL = registerBlockWithItem("compressed_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> COMPRESSED_INFUSED_LAVA_CRYSTAL = registerBlockWithItem("compressed_infused_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> LAVA_INFUSED_OBSIDIAN = registerBlockWithItem("lava_infused_obsidian", () -> new APLavaBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE)
            .strength(25.0f, 2000.0f)));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL = registerBlockWithItem("ore_frost_crystal", () -> new CrystalOreBlock(ORIGINAL));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL_STONE = registerBlockWithItem("ore_frost_crystal_stone", () -> new CrystalOreBlock(STONE));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL_OBSIDIAN = registerBlockWithItem("ore_frost_crystal_obsidian", () -> new CrystalOreBlock(OBSIDIAN));
    public static final RegistryObject<Block> FROST_CRYSTAL = registerBlockWithItem("block_frost_crystal", APFrostBlock::new);
    public static final RegistryObject<Block> INFUSED_FROST_CRYSTAL = registerBlockWithItem("block_infused_frost_crystal", APFrostBlock::new);
    public static final RegistryObject<Block> SNOW_BRICK = registerBlockWithItem("snow_brick", () -> new APBlock(Material.SNOW, 2.0f, 20f));
    public static final RegistryObject<Block> SNOW_BRICK_STAIRS = registerBlockWithItem("snow_brick_stairs", () -> new StoneBrickStairsBlock(() -> SNOW_BRICK.get()
            .defaultBlockState(), SNOW_BRICK.get()));
    public static final RegistryObject<Block> SNOW_BRICK_SLAB = registerBlockWithItem("snow_brick_slab", () -> new StoneBrickSlabBlock(SNOW_BRICK.get()));
    public static final RegistryObject<Block> PETRIFIED_SOULS = registerBlockWithItem("petrified_souls", () -> new APBlock(copy(Blocks.TERRACOTTA)));
    public static final RegistryObject<Block> SOUL_BOX = registerBlockWithItem("soul_box", SoulBox::new);
    //    public static final RegistryObject<BlockEntityType<TrophyTile>> TROPHY_TYPE = TILE_ENTITIES.register("trophy", () -> build(BlockEntityType.Builder.of(TrophyTile::new, TROPHY.get())));

    static {
        registerBlocks();
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, DeferredRegister<Block> blocks, DeferredRegister<Item> items, Supplier<BLOCK> blockSupplier, Function<BLOCK, Item> itemFactory) {
        RegistryObject<BLOCK> block = blocks.register(name, blockSupplier);
        items.register(name, () -> itemFactory.apply(block.get()));
        return block;
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier, Function<BLOCK, Item> itemFactory) {
        return registerBlockWithItem(name, BLOCKS, ITEMS, blockSupplier, itemFactory);
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier) {
        return registerBlockWithItem(name, BLOCKS, ITEMS, blockSupplier, APBlockItem::new);
    }

    //    public static RegistryObject<Block> registerTrophyBlock(String name, Supplier<Block> blockSupplier) {
    //        RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);
    //        Function<Block, Item> itemFunction = block1 -> new TrophyItem(blockSupplier);
    //        ITEMS.register(name, () -> itemFunction.apply(block.get()));
    //        return block;
    //    }

    private static <T extends BlockEntity> BlockEntityType<T> build(BlockEntityType.Builder<T> builder) {
        return builder.build(null);
    }

    public static void registerBlocks() {
        IntStream.range(0, AP_STONE_BRICKS_LENGTH).forEach(index -> {
            String color = values()[index].getName();
            STONE_BRICKS[index] = registerBlockWithItem(color + "_stone_brick", () -> new StoneBrickBlock(values()[index]));
            STONE_BRICK_CORNERS[index] = registerBlockWithItem(color + "_stone_brick_corner", () -> new StoneBrickCornerBlock(STONE_BRICKS[index].get()));
            STONE_BRICK_TOWERS[index] = registerBlockWithItem(color + "_stone_brick_tower", () -> new StoneBrickTowerBlock(STONE_BRICKS[index].get()));
            STONE_BRICK_WALLS[index] = registerBlockWithItem(color + "_stone_brick_wall", () -> new StoneBrickWallBlock(STONE_BRICKS[index].get()));
            STONE_BRICK_STAIRS[index] = registerBlockWithItem(color + "_stone_brick_stairs", () -> new StoneBrickStairsBlock(STONE_BRICKS[index].get()::defaultBlockState, STONE_BRICKS[index].get()));
            STONE_BRICK_SLABS[index] = registerBlockWithItem(color + "_stone_brick_slab", () -> new StoneBrickSlabBlock(STONE_BRICKS[index].get()));
            CASTLE_BLOCKS[index] = registerBlockWithItem(color + "_castle_block", () -> new CastleBlock(STONE_BRICKS[index].get()));
            CASTLE_BLOCK_CORNERS[index] = registerBlockWithItem(color + "_castle_block_corner", () -> new StoneBrickCornerBlock(CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_TOWERS[index] = registerBlockWithItem(color + "_castle_block_tower", () -> new StoneBrickTowerBlock(CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_WALLS[index] = registerBlockWithItem(color + "_castle_block_wall", () -> new StoneBrickWallBlock(CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_STAIRS[index] = registerBlockWithItem(color + "_castle_block_stairs", () -> new StoneBrickStairsBlock(CASTLE_BLOCKS[index].get()::defaultBlockState, CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_SLABS[index] = registerBlockWithItem(color + "_castle_block_slab", () -> new StoneBrickSlabBlock(CASTLE_BLOCKS[index].get()));
        });
    }
}