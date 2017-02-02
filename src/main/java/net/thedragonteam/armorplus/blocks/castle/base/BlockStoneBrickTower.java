/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;

import javax.annotation.Nonnull;

public class BlockStoneBrickTower extends BlockBase {

    private MapColor mapColor;

    public BlockStoneBrickTower(StoneBricks stoneBricks) {
        super(Material.ROCK, stoneBricks.getName() + "_stone_brick_tower", 10.0F, 5.0F, ToolType.PICKAXE, 0);
        this.mapColor = stoneBricks.getMapColor();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Nonnull
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state) {
        return this.mapColor;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
