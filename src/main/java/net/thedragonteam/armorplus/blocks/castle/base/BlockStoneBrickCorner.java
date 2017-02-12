/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.iface.IModelHelper;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.util.Utils.setName;

// TODO: fix CollisionBox to mach Shape (not any time soon)
public class BlockStoneBrickCorner extends BlockStairs implements IModelHelper {

    private StoneBricks stoneBricks;

    public BlockStoneBrickCorner(StoneBricks stoneBricks, IBlockState modelState) {
        super(modelState);
        this.setUnlocalizedName(setName(stoneBricks.getName() + "_stone_brick_corner"));
        this.setRegistryName(stoneBricks.getName() + "_stone_brick_corner");
        this.setHardness(10.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setLightOpacity(255);
        this.stoneBricks = stoneBricks;
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    @Nonnull
    public MapColor getMapColor(IBlockState state) {
        return this.stoneBricks.getMapColor();
    }
}
