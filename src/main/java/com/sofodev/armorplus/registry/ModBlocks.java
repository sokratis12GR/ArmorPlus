package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.blocks.APFrostBlock;
import com.sofodev.armorplus.registry.blocks.APLavaBlock;
import com.sofodev.armorplus.registry.blocks.castle.StoneBrickBlock;
import com.sofodev.armorplus.registry.blocks.castle.StoneBrickCornerBlock;
import com.sofodev.armorplus.registry.blocks.castle.StoneBrickTowerBlock;
import com.sofodev.armorplus.registry.blocks.castle.StoneBrickWallBlock;
import com.sofodev.armorplus.registry.blocks.ore.CrystalOreBlock;
import com.sofodev.armorplus.registry.blocks.special.SwordDisplayBlock;
import com.sofodev.armorplus.registry.blocks.special.SwordDisplayTile;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.*;
import static com.sofodev.armorplus.registry.blocks.castle.BrickColor.values;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static List<RegistryObject<Block>> blocks = new ArrayList<>();

    public static final RegistryObject<Block> COMPRESSED_OBSIDIAN = regWithItem("compressed_obsidian", () -> new Block(Block.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> ORE_LAVA_CRYSTAL = regWithItem("ore_lava_crystal", CrystalOreBlock::new);
    public static final RegistryObject<Block> LAVA_CRYSTAL = regWithItem("block_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> INFUSED_LAVA_CRYSTAL = regWithItem("block_infused_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> COMPRESSED_LAVA_CRYSTAL = regWithItem("compressed_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> COMPRESSED_INFUSED_LAVA_CRYSTAL = regWithItem("compressed_infused_lava_crystal", APLavaBlock::new);
    public static final RegistryObject<Block> LAVA_INFUSED_OBSIDIAN = regWithItem("lava_infused_obsidian", () ->
        new APLavaBlock(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).hardnessAndResistance(25.0f, 2000.0f)));
    public static final RegistryObject<Block> ORE_FROST_CRYSTAL = regWithItem("ore_frost_crystal", CrystalOreBlock::new);
    public static final RegistryObject<Block> FROST_CRYSTAL = regWithItem("block_frost_crystal", APFrostBlock::new);
    public static final RegistryObject<Block> INFUSED_FROST_CRYSTAL = regWithItem("block_infused_frost_crystal", APFrostBlock::new);
    public static final RegistryObject<Block>[] STONE_BRICKS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_TOWERS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_CORNERS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block>[] STONE_BRICK_WALLS = new RegistryObject[AP_STONE_BRICKS_LENGTH];
    public static final RegistryObject<Block> SWORD_DISPLAY = regWithItem("empty_sword_display", SwordDisplayBlock::new);
    public static final RegistryObject<TileEntityType<SwordDisplayTile>> SWORD_DISPLAY_TYPE = TILE_ENTITIES.register("sword_display",
        () -> build(Builder.create(SwordDisplayTile::new, SWORD_DISPLAY.get())));

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
        });
    }
}
