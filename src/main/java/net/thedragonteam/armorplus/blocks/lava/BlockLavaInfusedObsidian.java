/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.iface.IModelHelper;

public class BlockLavaInfusedObsidian extends BlockBase implements IModelHelper{

    public BlockLavaInfusedObsidian() {
        super(Material.ROCK, "lava_infused_obsidian", 2000.0f, 25.0f, ToolType.PICKAXE, 2, 0.8f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), 0, "normal");
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_) {
        return MapColor.RED;
    }
}
