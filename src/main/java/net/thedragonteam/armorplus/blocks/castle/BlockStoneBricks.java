/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockStoneBricks extends Block {
    public static final PropertyEnum<BlockStoneBricks.EnumType> VARIANT = PropertyEnum.<BlockStoneBricks.EnumType>create("variant", BlockStoneBricks.EnumType.class);

    public BlockStoneBricks() {
        super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.WHITE));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state) {
        return ((BlockStoneBricks.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockStoneBricks.EnumType blockwhitestones$enumtype : BlockStoneBricks.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockwhitestones$enumtype.getMetadata()));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockStoneBricks.EnumType.byMetadata(meta));
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return ((BlockStoneBricks.EnumType) state.getValue(VARIANT)).getMapColor();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((BlockStoneBricks.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    public static enum EnumType implements IStringSerializable {
        WHITE(0, "white", MapColor.QUARTZ),
        RED(1, "red", MapColor.RED),
        BLACK(2, "black", MapColor.BLACK),
        BLUE(3, "blue", MapColor.BLUE),
        GREEN(4, "green", MapColor.GREEN),
        YELLOW(5, "yellow", MapColor.YELLOW),
        PURPLE(6, "purple", MapColor.PURPLE);

        private static final BlockStoneBricks.EnumType[] META_LOOKUP = new BlockStoneBricks.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /**
         * The color that represents this entry on a map.
         */
        private final MapColor mapColor;

        private EnumType(int metaIn, String nameIn, MapColor mapColorIn) {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn) {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata() {
            return this.meta;
        }

        /**
         * The color which represents this entry on a map.
         */
        public MapColor getMapColor() {
            return this.mapColor;
        }

        public String toString() {
            return this.name;
        }

        public static BlockStoneBricks.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockStoneBricks.EnumType blockwhitestones$enumtype : values()) {
                META_LOOKUP[blockwhitestones$enumtype.getMetadata()] = blockwhitestones$enumtype;
            }
        }
    }
}