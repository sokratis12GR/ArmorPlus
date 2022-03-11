package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.blocks.APBlock;
import com.sofodev.armorplus.registry.blocks.APFrostBlock;
import com.sofodev.armorplus.registry.blocks.APLavaBlock;
import com.sofodev.armorplus.registry.blocks.castle.*;
import com.sofodev.armorplus.registry.blocks.crafting.LavaInfuserBlock;
import com.sofodev.armorplus.registry.blocks.crafting.LavaInfuserTile;
import com.sofodev.armorplus.registry.blocks.ore.CrystalOreBlock;
import com.sofodev.armorplus.registry.blocks.special.SoulBox;
import com.sofodev.armorplus.registry.blocks.special.TrophyBlock;
import com.sofodev.armorplus.registry.blocks.special.TrophyTile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.blocks.castle.BrickColor.values;
import static com.sofodev.armorplus.registry.blocks.ore.Variant.*;
import static net.minecraft.block.AbstractBlock.Properties.copy;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);

    public static List<RegistryObject<Block>> blocks = new ArrayList<>();

    public static final RegistryObject<Block> COMPRESSED_OBSIDIAN = regWithItem("compressed_obsidian", () -> new Block(copy(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL = regWithItem("ore_lava_crystal", () -> new CrystalOreBlock(ORIGINAL));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL_STONE = regWithItem("ore_lava_crystal_stone", () -> new CrystalOreBlock(STONE));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL_OBSIDIAN = regWithItem("ore_lava_crystal_obsidian", () -> new CrystalOreBlock(OBSIDIAN));
    public static final RegistryObject<Block> LAVA_CRYSTAL = regWithItem("block_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> INFUSED_LAVA_CRYSTAL = regWithItem("block_infused_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> COMPRESSED_LAVA_CRYSTAL = regWithItem("compressed_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> COMPRESSED_INFUSED_LAVA_CRYSTAL = regWithItem("compressed_infused_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> LAVA_INFUSED_OBSIDIAN = regWithItem("lava_infused_obsidian", () ->
            new APLavaBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).strength(25.0f, 2000.0f)));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL = regWithItem("ore_frost_crystal", () -> new CrystalOreBlock(ORIGINAL));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL_STONE = regWithItem("ore_frost_crystal_stone", () -> new CrystalOreBlock(STONE));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL_OBSIDIAN = regWithItem("ore_frost_crystal_obsidian", () -> new CrystalOreBlock(OBSIDIAN));
    public static final RegistryObject<Block> FROST_CRYSTAL = regWithItem("block_frost_crystal", APFrostBlock::new);
    public static final RegistryObject<Block> INFUSED_FROST_CRYSTAL = regWithItem("block_infused_frost_crystal", APFrostBlock::new);
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
    public static final RegistryObject<Block> LAVA_INFUSER = regWithItem("lava_infuser", LavaInfuserBlock::new);
    public static final RegistryObject<TileEntityType<LavaInfuserTile>> LAVA_INFUSER_TYPE = TILE_ENTITIES.register("lava_infuser",
            () -> build(Builder.of(LavaInfuserTile::new, LAVA_INFUSER.get())));
    public static final RegistryObject<Block> SNOW_BRICK = regWithItem("snow_brick", () -> new APBlock(Material.SNOW, 2.0f, 20f, 1, ToolType.PICKAXE));
    public static final RegistryObject<Block> SNOW_BRICK_STAIRS = regWithItem("snow_brick_stairs", () -> new StoneBrickStairsBlock(() -> SNOW_BRICK.get().defaultBlockState(), SNOW_BRICK.get()));
    public static final RegistryObject<Block> SNOW_BRICK_SLAB = regWithItem("snow_brick_slab", () -> new StoneBrickSlabBlock(SNOW_BRICK.get()));
    public static final RegistryObject<Block> PETRIFIED_SOULS = regWithItem("petrified_souls", () -> new APBlock(copy(Blocks.TERRACOTTA)));
    public static final RegistryObject<Block> SOUL_BOX = regWithItem("soul_box", SoulBox::new);
    public static final RegistryObject<Block> TROPHY = register("trophy", TrophyBlock::new);
    public static final RegistryObject<TileEntityType<TrophyTile>> TROPHY_TYPE = TILE_ENTITIES.register("trophy",
            () -> build(Builder.of(TrophyTile::new, TROPHY.get())));

    static {
        registerBlocks();
    }

    public static RegistryObject<Block> register(String name, Supplier<? extends Block> sup) {
        return BLOCKS.register(name, sup);
    }

    public static RegistryObject<Block> regWithItem(String name, Supplier<? extends Block> sup) {
        RegistryObject<Block> block = register(name, sup);
        blocks.add(block);
        return block;
    }

    private static <T extends TileEntity> TileEntityType<T> build(Builder<T> builder) {
        return builder.build(null);
    }

    public static void registerBlocks() {
        IntStream.range(0, AP_STONE_BRICKS_LENGTH).forEach(index -> {
            String color = values()[index].getName();
            STONE_BRICKS[index] = regWithItem(color + "_stone_brick", () -> new StoneBrickBlock(values()[index]));
            STONE_BRICK_CORNERS[index] = regWithItem(color + "_stone_brick_corner", () -> new StoneBrickCornerBlock(STONE_BRICKS[index].get()));
            STONE_BRICK_TOWERS[index] = regWithItem(color + "_stone_brick_tower", () -> new StoneBrickTowerBlock(STONE_BRICKS[index].get()));
            STONE_BRICK_WALLS[index] = regWithItem(color + "_stone_brick_wall", () -> new StoneBrickWallBlock(STONE_BRICKS[index].get()));
            STONE_BRICK_STAIRS[index] = regWithItem(color + "_stone_brick_stairs", () -> new StoneBrickStairsBlock(STONE_BRICKS[index].get()::defaultBlockState, STONE_BRICKS[index].get()));
            STONE_BRICK_SLABS[index] = regWithItem(color + "_stone_brick_slab", () -> new StoneBrickSlabBlock(STONE_BRICKS[index].get()));
            CASTLE_BLOCKS[index] = regWithItem(color + "_castle_block", () -> new CastleBlock(STONE_BRICKS[index].get()));
            CASTLE_BLOCK_CORNERS[index] = regWithItem(color + "_castle_block_corner", () -> new StoneBrickCornerBlock(CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_TOWERS[index] = regWithItem(color + "_castle_block_tower", () -> new StoneBrickTowerBlock(CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_WALLS[index] = regWithItem(color + "_castle_block_wall", () -> new StoneBrickWallBlock(CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_STAIRS[index] = regWithItem(color + "_castle_block_stairs", () -> new StoneBrickStairsBlock(CASTLE_BLOCKS[index].get()::defaultBlockState, CASTLE_BLOCKS[index].get()));
            CASTLE_BLOCK_SLABS[index] = regWithItem(color + "_castle_block_slab", () -> new StoneBrickSlabBlock(CASTLE_BLOCKS[index].get()));
        });
    }
}