/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.blocks.spawners.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nullable;
import java.util.Random;

public class SpawnerBase extends Block {


    public SpawnerBase(Material material, String name) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    public SpawnerBase(Material material, String name, float resistance, float hardness) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    public SpawnerBase(Material material, String name, float resistance, float hardness, String tool, int harvestLevel) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    public SpawnerBase(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, float lightLevel) {
        super(material);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        this.setLightLevel(lightLevel);
        setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isVisuallyOpaque() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    public MapColor getMapColor(IBlockState state) {
        return MapColor.CYAN;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float x, float y, float z) {
        return true;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        return 15 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * The type of render function called. 3 for standard block models, 2 for TESR's, 1 for liquids, -1 is no render
     */
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @Nullable
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
