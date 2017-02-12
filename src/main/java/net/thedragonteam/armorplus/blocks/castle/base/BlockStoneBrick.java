/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.iface.IModelHelper;

import javax.annotation.Nonnull;

public class BlockStoneBrick extends BlockBase implements IModelHelper {

    public MapColor color;

    private String name;

    public BlockStoneBrick(StoneBricks stoneBricks) {
        super(Material.ROCK, stoneBricks.getName() + "_stone_brick", 10.0F, 5.0F, ToolType.PICKAXE, 0);
        this.name = stoneBricks.getName() + "_stone_brick";
        this.color = stoneBricks.getMapColor();
    }

    public String getName() {
        return this.name;
    }

    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state) {
        return this.color;
    }
}
