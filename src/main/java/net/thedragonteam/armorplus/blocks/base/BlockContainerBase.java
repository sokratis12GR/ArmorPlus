/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class BlockContainerBase extends Block implements ITileEntityProvider {

    private TileEntity tileEntity;

    public BlockContainerBase(Material materialIn, TileEntity tileEntity, String name, double resistance, double hardness, BlockBase.ToolType toolType, int harvestLevel) {
        super(materialIn);
        this.tileEntity = tileEntity;
        this.setRegistryName(name);
        this.setUnlocalizedName(setName(name));
        this.setResistance((float) resistance);
        this.setHardness((float) hardness);
        this.setHarvestLevel(toolType.getTool(), harvestLevel);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return this.tileEntity;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
