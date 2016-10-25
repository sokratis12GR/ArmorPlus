/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.benches;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.base.BaseBenchBlock;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;

import javax.annotation.Nullable;

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:46 PM.
 * - TheDragonTeam
 */
public class HighTechBench extends BaseBenchBlock {

    public HighTechBench(TileEntityHighTechBench tileEntityHighTechBench) {
        super("high_tech_bench", tileEntityHighTechBench);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(ArmorPlus.instance, GuiHandler.GUI_HIGH_TECH_BENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
